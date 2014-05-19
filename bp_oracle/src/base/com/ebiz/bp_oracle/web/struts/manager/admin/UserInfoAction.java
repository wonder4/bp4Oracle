package com.ebiz.bp_oracle.web.struts.manager.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.bp_oracle.domain.UserInfo;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

public class UserInfoAction extends BaseAdminAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(request);
		super.setBaseDataListToSession(10, request);// 用户类型
		saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("order_value", "0");
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(request);
		super.setBaseDataListToSession(10, request);// 用户类型

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String is_del = (String) dynaBean.get("is_del");
		String user_name_like = (String) dynaBean.get("user_name_like");

		UserInfo entity = new UserInfo();
		super.copyProperties(entity, form);
		entity.getMap().put("user_name_like", user_name_like);
		if (null == is_del) {
			entity.setIs_del(0);
			dynaBean.set("is_del", "0");
		}
		Long recordCount = getFacade().getUserInfoService().getUserInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<UserInfo> list = getFacade().getUserInfoService().getUserInfoPaginatedList(entity);

		request.setAttribute("entityList", list);

		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		saveToken(request);
		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			UserInfo entity = getFacade().getUserInfoService().getUserInfo(new UserInfo(new Long(id)));
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			entity.setQueryString(super.serialize(request, "id", "method"));

			super.copyProperties(form, entity);
			super.setBaseDataListToSession(10, request);// 用户类型

			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);

		UserInfo sessionUi = super.getUserInfoFromSession(request);

		DynaBean dynaBean = (DynaBean) form;
		String user_name = (String) dynaBean.get("user_name");
		String mod_id = (String) dynaBean.get("mod_id");
		UserInfo entity = new UserInfo();
		super.copyProperties(entity, form);
		if (null != user_name) {
			entity.setUser_name(user_name.trim());
		}
		String password = entity.getPassword();

		UserInfo userInfo = new UserInfo();
		if (null == entity.getId()) {// insert
			entity.setPassword(EncryptUtils.MD5Encode(password));
			entity.setLogin_count(new Long("0"));
			entity.setIs_del(0);
			entity.setAdd_date(new Date());
			entity.setAdd_user_id(sessionUi.getId());
			getFacade().getUserInfoService().createUserInfo(entity);
			saveMessage(request, "entity.inserted");
		} else {// update
			userInfo = getFacade().getUserInfoService().getUserInfo(new UserInfo(entity.getId()));
			if (null != userInfo) {
				if (!password.equalsIgnoreCase(userInfo.getPassword())) {
					entity.setPassword(EncryptUtils.MD5Encode(password));
				}
				if (null != entity.getIs_del() && entity.getIs_del().intValue() == 0) {
					entity.setDel_date(null);
				}
				entity.setUpdate_date(new Date());
				entity.setUpdate_user_id(sessionUi.getId());
				getFacade().getUserInfoService().modifyUserInfo(entity);
				saveMessage(request, "entity.updated");
			} else {
				saveError(request, "entity.missing");
			}
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		// String mod_id = (String) dynaBean.get("mod_id");

		UserInfo sessionUi = super.getUserInfoFromSession(request);

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			UserInfo entity = new UserInfo();
			entity.setIs_del(1);
			entity.setId(new Long(id));
			entity.setDel_date(new Date());
			entity.setDel_user_id(sessionUi.getId());
			getFacade().getUserInfoService().modifyUserInfo(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			UserInfo entity = new UserInfo();
			entity.setIs_del(1);
			entity.setDel_date(new Date());
			entity.setDel_user_id(sessionUi.getId());
			entity.getMap().put("pks", pks);
			getFacade().getUserInfoService().modifyUserInfo(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "ids", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(request);
		super.setBaseDataListToSession(10, request);// 用户类型

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			UserInfo userInfo = new UserInfo();
			userInfo.setId(new Long(id));
			UserInfo entity = getFacade().getUserInfoService().getUserInfo(userInfo);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			super.copyProperties(form, entity);
			super.setBaseDataListToSession(10, request);// 用户类型
			return mapping.findForward("view");
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
	}

	public ActionForward getQueryUserNames(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String query = (String) dynaBean.get("query");
		String pageCount = (String) dynaBean.get("pageCount");

		StringBuffer sb = new StringBuffer();
		int count = 20;

		if (StringUtils.isNotBlank(pageCount)) {
			count = Integer.valueOf(pageCount);
		}

		if (StringUtils.isNotBlank(query)) {

			UserInfo userInfo = new UserInfo();
			userInfo.setIs_del(0);
			userInfo.getMap().put("user_name_like", query);
			userInfo.getRow().setCount(count);// 默认取前20调符合匹配数据
			List<UserInfo> userInfoList = getFacade().getUserInfoService().getUserInfoList(userInfo);
			if (null != userInfoList && userInfoList.size() > 0) {
				sb.append("{");
				sb.append("\"query\":\"").append(query).append("\",");
				sb.append("\"suggestions\":[");

				String[] userIdsArray = new String[userInfoList.size()];
				String[] userNamesArray = new String[userInfoList.size()];
				for (int i = 0; i < userInfoList.size(); i++) {
					userNamesArray[i] = "\"" + userInfoList.get(i).getUser_name() + "\"";
					userIdsArray[i] = "\"" + userInfoList.get(i).getId() + "\"";
				}

				sb.append(StringUtils.join(userNamesArray, ","));
				sb.append("],");
				sb.append("\"data\":[").append(StringUtils.join(userIdsArray, ",")).append("]");

				sb.append("}");

			}

		}
		logger.info(sb.toString());
		super.renderJson(response, sb.toString());

		return null;
	}

	public ActionForward checkLoginName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_name = (String) dynaBean.get("user_name");
		String id = (String) dynaBean.get("id");
		UserInfo userInfo = new UserInfo();
		userInfo.setIs_del(0);
		userInfo.getMap().put("not_in_id", id);
		userInfo.setUser_name(user_name);
		Long recordCount = super.getFacade().getUserInfoService().getUserInfoCount(userInfo);
		String flag = "1";
		if (recordCount.intValue() == 0) {
			flag = "0";
		}
		super.renderJson(response, flag);
		return null;
	}

}