package com.ebiz.bp_oracle.web.struts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.bp_oracle.domain.SysModule;

public class IndexAction extends BaseWebAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.index(mapping, form, request, response);
	}

	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String mod_id = "";

		mod_id = "1003002000";// 最新活动
		request.setAttribute("newsInfo" + mod_id + "List", super.getNewsInfoList(request, mod_id, 5));

		mod_id = "1003003000";// 专题销售区
		request.setAttribute("newsInfo" + mod_id + "List", super.getNewsInfoList(request, mod_id, 5));

		mod_id = "1003001000";// 公告
		super.getNewsInfoImageList(request, mod_id, 5);// 取图片新闻
		request.setAttribute("newsInfo" + mod_id + "List", super.getNewsInfoList(request, mod_id, 5));

		String par_id = "1007100000";// 友情链接
		SysModule sysModule = new SysModule();
		sysModule.setPar_id(Long.valueOf(par_id));
		sysModule.setIs_del(0);
		List<SysModule> sysModuleList = super.getFacade().getSysModuleService().getSysModuleList(sysModule);
		for (SysModule sm : sysModuleList) {
			mod_id = sm.getMod_id().toString();
			request.setAttribute("friendLink" + mod_id + "List", super.getNewsInfoList(request, mod_id, 70));
		}
		return mapping.findForward("success");
	}

}
