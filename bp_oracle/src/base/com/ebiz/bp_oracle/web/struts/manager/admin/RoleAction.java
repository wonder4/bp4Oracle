package com.ebiz.bp_oracle.web.struts.manager.admin;

import java.util.ArrayList;
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

import com.ebiz.bp_oracle.domain.Role;
import com.ebiz.bp_oracle.domain.RoleUser;
import com.ebiz.bp_oracle.domain.UserInfo;
import com.ebiz.ssi.web.struts.bean.Pager;

public class RoleAction extends BaseAdminAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String is_del = (String) dynaBean.get("is_del");
		String role_name_like = (String) dynaBean.get("role_name_like");

		Role entity = new Role();
		if (null == is_del) {
			entity.setIs_del(0);
			dynaBean.set("is_del", "0");
		}
		super.copyProperties(entity, dynaBean);
		entity.getMap().put("role_name_like", role_name_like);

		Long recordCount = getFacade().getRoleService().getRoleCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<Role> list = getFacade().getRoleService().getRolePaginatedList(entity);
		request.setAttribute("roleList", list);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(request);
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("order_value", "0");
		return mapping.findForward("input");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");
		UserInfo sessionUi = super.getUserInfoFromSession(request);

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			Role entity = new Role();
			entity.setIs_del(1);
			entity.setId(new Long(id));
			entity.setDel_date(new Date());
			entity.setDel_user_id(sessionUi.getId());
			getFacade().getRoleService().modifyRole(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			Role entity = new Role();
			entity.setIs_del(1);
			entity.setDel_date(new Date());
			entity.setDel_user_id(sessionUi.getId());
			entity.getMap().put("pks", pks);
			getFacade().getRoleService().modifyRole(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "ids", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		Role role = new Role();
		role.setId(new Long(id));

		Role entity = getFacade().getRoleService().getRole(role);
		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		Role entity = new Role();
		super.copyProperties(entity, dynaBean);
		entity.setIs_lock(0);
		UserInfo sessionUi = super.getUserInfoFromSession(request);
		if (null == entity.getId()) {
			entity.setIs_del(0);
			entity.setAdd_date(new Date());
			entity.setAdd_user_id(sessionUi.getId());
			getFacade().getRoleService().createRole(entity);
			saveMessage(request, "entity.inserted");
		} else {
			entity.setUpdate_date(new Date());
			entity.setUpdate_user_id(sessionUi.getId());
			if (null != entity.getIs_del() && entity.getIs_del().intValue() == 0) {
				entity.setDel_date(null);
			}
			getFacade().getRoleService().modifyRole(entity);
			saveMessage(request, "entity.updated");
		}
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(request);
		super.setBaseDataListToSession(10, request);// 用户类型
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		Role role = new Role();
		role.setId(new Long(id));
		Role entity = getFacade().getRoleService().getRole(role);
		// 获取该角色的相应用户列表
		RoleUser role_user = new RoleUser();
		role_user.setRole_id(entity.getId());
		List<RoleUser> roleUserList = getFacade().getRoleUserService().getRoleUserList(role_user);
		request.setAttribute("roleUserList", roleUserList);
		// 获取所以的用户列表
		UserInfo ui = new UserInfo();
		ui.setIs_del(0);
		super.copyProperties(form, entity);
		List<UserInfo> userInfoList = new ArrayList<UserInfo>();
		userInfoList = getFacade().getUserInfoService().getUserInfoList(ui);
		request.setAttribute("userInfoList", userInfoList);

		return mapping.findForward("view");
	}

	public ActionForward saveRoleUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String role_id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String[] user_ids = request.getParameterValues("user_ids");
		if (StringUtils.isBlank(role_id)) {
			saveError(request, "errors.parm");
			return mapping.findForward("list");
		}
		// 删除该角色的相应用户
		RoleUser roleUser = new RoleUser();
		roleUser.setRole_id(Long.valueOf(role_id));
		getFacade().getRoleUserService().removeRoleUser(roleUser);
		// 添加该角色新的用户
		if (ArrayUtils.isNotEmpty(user_ids)) {
			for (String user_id : user_ids) {
				RoleUser entity = new RoleUser();
				entity.setRole_id(Long.valueOf(role_id));
				entity.setUser_id(Long.valueOf(user_id));
				getFacade().getRoleUserService().createRoleUser(entity);
			}
		}
		saveMessage(request, "entity.inserted");
		ActionForward forward = new ActionForward(mapping.findForward("success").getPath()
				+ response.encodeURL("&mod_id=" + mod_id), true);
		return forward;
	}

	public ActionForward checkRoleName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String role_name = (String) dynaBean.get("role_name");
		String id = (String) dynaBean.get("id");
		Role entity = new Role();
		entity.getMap().put("not_in_id", id);
		entity.setRole_name(role_name);
		entity.setIs_del(0);
		Long recordCount = super.getFacade().getRoleService().getRoleCount(entity);
		String flag = "1";
		if (recordCount.intValue() == 0) {
			flag = "0";
		}
		super.renderJson(response, flag);
		return null;
	}
}