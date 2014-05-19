package com.ebiz.bp_oracle.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.bp_oracle.domain.SysModule;
import com.ebiz.bp_oracle.domain.UserInfo;
import com.ebiz.bp_oracle.web.util.StringHelper;

public class FramesAction extends BaseAdminAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.index(mapping, form, request, response);
	}

	public ActionForward top(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("topFrame");
	}

	public ActionForward left(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		UserInfo userInfo = super.getUserInfoFromSession(request);

		SysModule entity = new SysModule();
		entity.getMap().put("is_admin", userInfo.getUser_type().intValue() == 1 ? "true" : null);
		entity.getMap().put("user_id", userInfo.getId());
		List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(entity);
		// String treeNodes = StringHelper.getTreeNodesFromSysModuleList(sysModuleList);
		String treeNodes = StringHelper.getjQzTreeNodesFromSysModuleList(sysModuleList);
		logger.info("treeNodes:{}", treeNodes);
		session.setAttribute("treeNodes", treeNodes);

		return mapping.findForward("leftFrame");
	}

	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("indexFrame");
	}

	public ActionForward main(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("mainFrame");
	}

	public ActionForward lr(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("lrFrame");
	}

	public ActionForward bottom(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("bottomFrame");
	}

}
