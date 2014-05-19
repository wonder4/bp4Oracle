package com.ebiz.bp_oracle.web.struts.manager.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import com.ebiz.bp_oracle.domain.SysSetting;

public class SysSettingAction extends BaseAdminAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return this.edit(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.edit(mapping, form, request, response);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(request);
		saveToken(request);
		DynaBean dynaBean = (DynaBean) form;

		SysSetting sysSetting = new SysSetting();

		sysSetting.setTitle("isEnabledCode");
		dynaBean.set("isEnabledCode", getFacade().getSysSettingService().getSysSetting(sysSetting).getContent());

		sysSetting.setTitle("isEnabledUpdateSystemTip");
		dynaBean.set("isEnabledUpdateSystemTip", getFacade().getSysSettingService().getSysSetting(sysSetting)
				.getContent());

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
		resetToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String isEnabledCode = (String) dynaBean.get("isEnabledCode");

		SysSetting sysSetting = new SysSetting();

		sysSetting.setTitle("isEnabledCode");
		sysSetting.setContent(isEnabledCode);
		super.getFacade().getSysSettingService().modifySysSetting(sysSetting);

		super.getFacade().getSysSettingService().modifySysSetting(sysSetting);

		saveMessage(request, "entity.updated");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward updateIsEnabledUpdateSystemTip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String elementValue = (String) dynaBean.get("elementValue");

		JSONObject obj = new JSONObject();
		obj.put("method", "updateIsEnabledUpdateSystemTip");

		if (StringUtils.isBlank(elementValue)) {
			obj.put("results", "0");
			super.renderJson(response, obj.toString());
			return null;
		}

		SysSetting sysSetting = new SysSetting();
		sysSetting.setTitle("isEnabledUpdateSystemTip");
		sysSetting.setContent(elementValue);
		super.getFacade().getSysSettingService().modifySysSetting(sysSetting);
		obj.put("results", "1");
		super.renderJson(response, obj.toString());
		return null;
	}
}
