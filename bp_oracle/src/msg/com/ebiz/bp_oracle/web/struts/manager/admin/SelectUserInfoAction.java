package com.ebiz.bp_oracle.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.bp_oracle.web.struts.manager.admin.BaseAdminAction;
import com.ebiz.bp_oracle.domain.UserInfo;

/**
 * @author Hui,Gang
 * @version 2013-12-11 下午06:19:42
 */
public class SelectUserInfoAction extends BaseAdminAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String selectedUserIds = (String) dynaBean.get("selectedUserIds");// 已经选择的人员ID字符串，格式：***,**,**
		String key_word = (String) dynaBean.get("key_word"); // 搜索关键字 姓名
		String user_type = (String) dynaBean.get("user_type");
		String disable_user_ids = (String) dynaBean.get("disable_user_ids");// 不可被选择的ids,格式：***,**,**
		// String province = (String) dynaBean.get("province");// 企业用户 or 管理用户 所在的市
		// String city = (String) dynaBean.get("city");// 企业用户 or 管理用户 所在的市
		// String country = (String) dynaBean.get("country");// 企业用户 or 管理用户 所在的县

		UserInfo userInfo = new UserInfo();
		userInfo.setIs_del(0);

		if (StringUtils.isNotBlank(key_word)) {
			userInfo.getMap().put("user_name_like", key_word);
		}

		if (GenericValidator.isLong(user_type)) {
			userInfo.setUser_type(new Long(user_type));
		}

		// if (StringUtils.isBlank(country)) {
		// if (StringUtils.isNotBlank(city)) {
		// userInfo.getMap().put("city_p_index", city);
		// } else {
		// userInfo.getMap().put("province_p_index", province);
		// }
		// } else if (StringUtils.isNotBlank(country)) {
		// userInfo.getMap().put("country_p_index", country);
		// }

		if (StringUtils.isNotBlank(selectedUserIds)) {
			if (StringUtils.isNotBlank(disable_user_ids))
				userInfo.getMap().put("id_not_in", selectedUserIds + "," + disable_user_ids);
			else
				userInfo.getMap().put("id_not_in", selectedUserIds);
		} else {
			if (StringUtils.isNotBlank(disable_user_ids))
				userInfo.getMap().put("id_not_in", disable_user_ids);
		}

		userInfo.getRow().setCount(100);
		userInfo.getMap().put("select_user_info", "select_user_info");
		userInfo.getMap().put("user_type_ne", "0");// 把管理员屏蔽掉
		List<UserInfo> userInfoList = super.getFacade().getUserInfoService().getUserInfoList(userInfo);
		request.setAttribute("entityList", userInfoList);

		if (StringUtils.isNotBlank(selectedUserIds)) {
			userInfo.getMap().put("ids", selectedUserIds);
			userInfo.getMap().put("id_not_in", null);
			if (StringUtils.isNotBlank(disable_user_ids)) {
				userInfo.getMap().put("id_not_in", disable_user_ids);
			}
			List<UserInfo> selectUserInfoList = super.getFacade().getUserInfoService().getUserInfoList(userInfo);
			request.setAttribute("selectList", selectUserInfoList);
		}

		return mapping.findForward("list");
	}

	public ActionForward getQueryUserNames(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String key_word = (String) dynaBean.get("key_word");
		String user_type = (String) dynaBean.get("user_type");
		String selectedUserIds = (String) dynaBean.get("selectedUserIds");// 已经选择的人员ID字符串，格式：***,**,**
		String disable_user_ids = (String) dynaBean.get("disable_user_ids");// 不可被选择的ids,格式：***,**,**
		// String province = (String) dynaBean.get("province");// 企业用户 or 管理用户 所在的市
		// String city = (String) dynaBean.get("city");// 企业用户 or 管理用户 所在的市
		StringBuffer sb = new StringBuffer("[");

		UserInfo userInfo = new UserInfo();

		if (GenericValidator.isLong(user_type)) {
			userInfo.setUser_type(new Long(user_type));
		}

		userInfo.setIs_del(0);
		userInfo.getMap().put("user_name_like", key_word);
		if (StringUtils.isNotBlank(disable_user_ids)) {
			userInfo.getMap().put("id_not_in", selectedUserIds + "," + disable_user_ids);
		} else {
			userInfo.getMap().put("id_not_in", selectedUserIds);
		}

		// if (StringUtils.isNotBlank(city)) {
		// userInfo.getMap().put("city_p_index", city);
		// } else {
		// userInfo.getMap().put("province_p_index", province);
		// }

		userInfo.getRow().setCount(100);
		// userInfo.getMap().put("select_user_info", "select_user_info");
		userInfo.getMap().put("user_type_ne", "0");// 把管理员屏蔽掉
		List<UserInfo> userInfoList = getFacade().getUserInfoService().getUserInfoList(userInfo);
		for (UserInfo entity : userInfoList) {
			sb.append("{\"user_id\":\"").append(String.valueOf(entity.getId())).append("\",");
			sb.append("\"user_name\":\"");
			sb.append(StringEscapeUtils.escapeJavaScript(entity.getUser_name()));
			sb.append("\"},");
		}
		sb.append("{\"end\":\"\"}]");

		super.renderJson(response, sb.toString());

		return null;
	}
}
