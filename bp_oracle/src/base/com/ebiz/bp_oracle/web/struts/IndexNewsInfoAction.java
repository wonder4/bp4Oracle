package com.ebiz.bp_oracle.web.struts;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.bp_oracle.domain.NewsAttachment;
import com.ebiz.bp_oracle.domain.NewsInfo;
import com.ebiz.bp_oracle.domain.SysModule;
import com.ebiz.ssi.web.struts.bean.Pager;

public class IndexNewsInfoAction extends BaseWebAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.view(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScopeForHomePage(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_code = (String) dynaBean.get("mod_code");
		String par_code = (String) dynaBean.get("par_code");
		String title_like = (String) dynaBean.get("title_like");
		Pager pager = (Pager) dynaBean.get("pager");

		NewsInfo entity = new NewsInfo();
		super.copyProperties(entity, form);

		if (StringUtils.isBlank(mod_code)) {// 当没有mod_code时，查询每日更新
			String msg = "参数mod_code为空。";
			super.renderJavaScript(response, "alert('".concat(msg).concat("');history.back();"));
			return null;
		}
		if (StringUtils.isBlank(par_code)) {
			String msg = "参数par_code为空。";
			super.renderJavaScript(response, "alert('".concat(msg).concat("');history.back();"));
			return null;
		}
		// entity.setP_index(pIndex)
		entity.setMod_id(mod_code);
		entity.setIs_del(0);
		entity.getMap().put("is_pub", "0"); // for info_state > 0
		entity.getMap().put("no_invalid", "no_invalid");
		entity.getMap().put("title_like", title_like);

		long recordCount = getFacade().getNewsInfoService().getNewsInfoCount(entity);

		pager.init(recordCount, 20, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<NewsInfo> newsInfoList = getFacade().getNewsInfoService().getNewsInfoPaginatedList(entity);
		request.setAttribute("newsInfoList", newsInfoList);

		super.setPublicInfoList(request);

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String uuid = (String) dynaBean.get("uuid");

		if (StringUtils.isBlank(uuid)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}

		NewsInfo entity = new NewsInfo();
		entity.setUuid(uuid);
		entity.getMap().put("is_pub", "0"); // for info_state > 0
		entity.getMap().put("no_invalid", "no_invalid");
		entity = getFacade().getNewsInfoService().getNewsInfo(entity);
		if (null == entity) {
			String msg = super.getMessage(request, "news.missing");
			super.renderJavaScript(response, "alert('".concat(msg).concat("');history.back();"));
			return null;
		}

		// 前台导航
		setNaviStringToRequestScopeForHomePage(request);
		// news.refresh.too.frequency=对不起，2次刷新页面时间间隔过小，请勿恶意刷新页面！
		Date now = new Date();
		long timeNow = now.getTime();
		if (null != entity.getView_datetime()) {
			long timeDb = entity.getView_datetime().getTime();
			if (timeNow - timeDb < 1500) {
				String msg = super.getMessage(request, "news.refresh.too.frequency");
				super.renderJavaScript(response, "alert('" + msg + "');self.close();");
				return null;
			}

		}
		entity.setView_count(entity.getView_count() + 1);
		entity.setView_datetime(now);
		entity.setId(entity.getId());
		getFacade().getNewsInfoService().modifyNewsInfo(entity);

		NewsAttachment attachment = new NewsAttachment();
		attachment.setLink_id(entity.getId());
		request
				.setAttribute("attachmentList", getFacade().getNewsAttachmentService()
						.getNewsAttachmentList(attachment));

		super.copyProperties(form, entity);

		// super.setPublicInfoWithSearchList(request);
		// super.setPublicInfoList(request);

		return mapping.findForward("view");
	}

	/**
	 * 根据子节点显示列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward typeList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScopeForHomePage(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_code = (String) dynaBean.get("mod_code");
		String title_like = (String) dynaBean.get("title_like");
		Pager pager = (Pager) dynaBean.get("pager");

		SysModule sysModule = new SysModule();
		List<SysModule> sysModuleList;
		if (StringUtils.isNotBlank(mod_code)) {
			sysModule.setIs_del(0);
			sysModule.setPar_id(Long.valueOf(mod_code));
			sysModule.getMap().put("is_order", true);
			sysModuleList = super.getFacade().getSysModuleService().getSysModuleList(sysModule);
		} else {
			String msg = super.getMessage(request, "entity.missing");
			super.renderJavaScript(response, "alert('".concat(msg).concat("');history.back();"));
			return null;
		}

		if (null != sysModuleList && sysModuleList.size() > 0) {

			List<Map<SysModule, List<NewsInfo>>> newsInfoList = new ArrayList<Map<SysModule, List<NewsInfo>>>();

			for (SysModule sysModuleForList : sysModuleList) {
				Map<SysModule, List<NewsInfo>> moduleMap = new HashMap<SysModule, List<NewsInfo>>();

				NewsInfo entity = new NewsInfo();
				super.copyProperties(entity, form);
				entity.setMod_id(sysModuleForList.getMod_id().toString());
				entity.setIs_del(0);
				entity.getMap().put("is_pub", "0"); // for info_state > 0
				entity.getMap().put("no_invalid", "no_invalid");
				entity.getMap().put("title_like", title_like);

				long recordCount = getFacade().getNewsInfoService().getNewsInfoCount(entity);
				pager.init(recordCount, 5, pager.getRequestPage());
				entity.getRow().setFirst(pager.getFirstRow());
				entity.getRow().setCount(pager.getRowCount());
				List<NewsInfo> entityList = getFacade().getNewsInfoService().getNewsInfoPaginatedList(entity);

				moduleMap.put(sysModuleForList, entityList);
				newsInfoList.add(moduleMap);
			}

			request.setAttribute("newsInfoList", newsInfoList);
		}

		// super.setPublicInfoWithSearchList(request);
		// super.setPublicInfoList(request);

		return new ActionForward("/../view/jsp/index/NewsInfo/typeList.jsp");
	}
}
