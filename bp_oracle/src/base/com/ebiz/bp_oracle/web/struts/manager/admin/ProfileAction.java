package com.ebiz.bp_oracle.web.struts.manager.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.bp_oracle.domain.UserInfo;
import com.ebiz.bp_oracle.web.Keys;
import com.ebiz.ssi.util.EncryptUtils;

//import com.ebiz.tjgis.web.util.DESPlus;

public class ProfileAction extends BaseAdminAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(request);
		saveToken(request);
		HttpSession session = request.getSession();
		UserInfo ui = (UserInfo) session.getAttribute(Keys.SESSION_USERINFO_KEY);

		// DynaBean dynaBean = (DynaBean) form;
		// dynaBean.set("user_name", ui.getUser_name());

		UserInfo entity = new UserInfo();
		entity.setId(ui.getId());
		entity = getFacade().getUserInfoService().getUserInfo(entity);
		if (null == entity) {
			saveError(request, "entity.missing");
			return mapping.findForward("input");
		}
		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (isCancelled(request))
			return unspecified(mapping, form, request, response);
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return mapping.findForward("input");
		}

		// setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String old_password = (String) dynaBean.get("old_password");
		String new_password = (String) dynaBean.get("new_password");
		String mod_id = (String) dynaBean.get("mod_id");

		UserInfo ui = super.getUserInfoFromSession(request);

		UserInfo entity = new UserInfo();
		entity.setId(ui.getId());

		// DESPlus des = new DESPlus();
		entity.setPassword(EncryptUtils.MD5Encode(old_password));

		if (null == super.getFacade().getUserInfoService().getUserInfo(entity)) {
			String msg = super.getMessage(request, "password.old.invalid");
			super.renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}
		super.copyProperties(entity, form);
		entity.setId(ui.getId());
		entity.setPassword(EncryptUtils.MD5Encode(new_password));
		resetToken(request);
		super.getFacade().getUserInfoService().modifyUserInfo(entity);

		saveMessage(request, "entity.updated");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
}
