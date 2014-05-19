package com.ebiz.bp_oracle.web.struts.manager.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.bp_oracle.domain.QaInfo;
import com.ebiz.bp_oracle.domain.UserInfo;
import com.ebiz.bp_oracle.web.Keys;
import com.ebiz.ssi.web.struts.bean.Pager;

public class QaInfoAction extends BaseAdminAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;

		// encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String st_q_date = (String) dynaBean.get("st_q_date");
		String en_q_date = (String) dynaBean.get("en_q_date");

		String st_a_date = (String) dynaBean.get("st_a_date");
		String en_a_date = (String) dynaBean.get("en_a_date");
		String q_title_like = (String) dynaBean.get("q_title_like");// 快速搜索中

		QaInfo entity = new QaInfo();
		super.copyProperties(entity, form);

		entity.getMap().put("st_q_date", st_q_date);
		entity.getMap().put("en_q_date", en_q_date);

		entity.getMap().put("st_a_date", st_a_date);
		entity.getMap().put("en_a_date", en_a_date);

		entity.getMap().put("q_title_like", q_title_like);

		entity.getMap().put("qa_state_gt", -1);

		long recordCount = getFacade().getQaInfoService().getQaInfoCount(entity);
		pager.init(recordCount, Integer.valueOf("10"), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<QaInfo> qaInfoList = getFacade().getQaInfoService().getQaInfoPaginatedList(entity);
		request.setAttribute("entityList", qaInfoList);

		return mapping.findForward("list");

	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		if (!StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;

		HttpSession session = request.getSession(false);
		UserInfo ui = (UserInfo) session.getAttribute(Keys.SESSION_USERINFO_KEY);

		dynaBean.set("user_name", ui.getReal_name());
		QaInfo entity = new QaInfo();
		entity.setOrder_value(0);
		entity.setA_date(new Date());
		entity.setQa_state(0);
		super.copyProperties(form, entity);

		return mapping.findForward("input");
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

		HttpSession session = request.getSession(false);
		UserInfo ui = (UserInfo) session.getAttribute(Keys.SESSION_USERINFO_KEY);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String q_type = (String) dynaBean.get("q_type");

		QaInfo entity = new QaInfo();
		super.copyProperties(entity, form);

		if (null == entity.getId()) {
			entity.setA_uid(ui.getId());
			entity.setA_uname(ui.getReal_name());
			entity.setQ_ip(super.getIpAddr(request));
			entity.setQ_date(new Date());
			getFacade().getQaInfoService().createQaInfo(entity);

			saveMessage(request, "entity.inserted");

		} else if (null != entity) {

			entity.setA_date(new Date());
			getFacade().getQaInfoService().modifyQaInfo(entity);

			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&q_type=").append(q_type);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		if (!StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		HttpSession session = request.getSession(false);
		UserInfo ui = (UserInfo) session.getAttribute(Keys.SESSION_USERINFO_KEY);

		if (GenericValidator.isLong(id)) {
			QaInfo qaInfo = new QaInfo();
			qaInfo.setId(Long.valueOf(id));
			qaInfo = getFacade().getQaInfoService().getQaInfo(qaInfo);

			dynaBean.set("user_name", ui.getReal_name());

			if (null == qaInfo) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			// the line below is added for pagination
			qaInfo.setQueryString(super.serialize(request, "id", "method"));
			// end
			super.copyProperties(form, qaInfo);
			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");

		}

	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		if (!StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		QaInfo entity = new QaInfo();

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {

			entity.setId(Long.valueOf(id));
			entity.setQa_state(-1);
			getFacade().getQaInfoService().modifyQaInfo(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {

			entity.getMap().put("pks", pks);
			entity.setQa_state(-1);
			getFacade().getQaInfoService().modifyQaInfo(entity);
			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		setNaviStringToRequestScope(request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (GenericValidator.isInt(id)) {
			QaInfo qaInfo = new QaInfo();
			qaInfo.setId(Long.valueOf(id));
			qaInfo = getFacade().getQaInfoService().getQaInfo(qaInfo);

			if (null == qaInfo) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			super.copyProperties(form, qaInfo);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}
}