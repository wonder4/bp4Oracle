package com.ebiz.bp_oracle.web.struts.manager.admin;

import java.util.ArrayList;
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
import org.json.JSONObject;

import com.ebiz.bp_oracle.domain.BaseAttribute;
import com.ebiz.bp_oracle.domain.BaseAttributeSon;
import com.ebiz.bp_oracle.domain.BaseClass;
import com.ebiz.bp_oracle.domain.BaseClassLinkAttribute;
import com.ebiz.bp_oracle.domain.PdInfoCustomAttrContent;
import com.ebiz.bp_oracle.domain.UserInfo;
import com.ebiz.bp_oracle.web.Keys;
import com.ebiz.ssi.web.struts.bean.Pager;

public class BasePdAttributeAction extends BaseAdminAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		setNaviStringToRequestScope(request);
		saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String cls_id = (String) dynaBean.get("cls_id");
		String class_mod_id = (String) dynaBean.get("mod_id");
		String attr_scope = (String) dynaBean.get("attr_scope");
		if (StringUtils.isNotBlank(class_mod_id)) {
			request.setAttribute("class_mod_id", class_mod_id);
		}
		if (StringUtils.isNotBlank(attr_scope)) {
			dynaBean.set("attr_scope", attr_scope);
		}
		if (StringUtils.isNotBlank(cls_id)) {
			request.setAttribute("have_cls_id", cls_id);
			BaseClass baseClass = new BaseClass();
			baseClass.setCls_id(Long.valueOf(cls_id));
			request.setAttribute("baseClass", super.getFacade().getBaseClassService().getBaseClass(baseClass));
		}
		dynaBean.set("order_value", "0");
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;
		String is_del = (String) dynaBean.get("is_del");
		String attr_name_like = (String) dynaBean.get("attr_name_like");
		String attr_show_name_like = (String) dynaBean.get("attr_show_name_like");
		String attr_scope = (String) dynaBean.get("attr_scope");
		Pager pager = (Pager) dynaBean.get("pager");

		BaseAttribute entity = new BaseAttribute();
		super.copyProperties(entity, dynaBean);

		if (null == is_del) {
			entity.setIs_del(0);
			dynaBean.set("is_del", "0");
		}
		if (StringUtils.isNotBlank(attr_name_like)) {
			entity.getMap().put("attr_name_like", attr_name_like);
		}
		if (StringUtils.isNotBlank(attr_show_name_like)) {
			entity.getMap().put("attr_show_name_like", attr_show_name_like);
		}
		if (StringUtils.isNotBlank(attr_scope)) {
			entity.setAttr_scope(Integer.valueOf(attr_scope));
			dynaBean.set("attr_scope", attr_scope);
		}
		Long recordCount = getFacade().getBaseAttributeService().getBaseAttributeCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<BaseAttribute> list = getFacade().getBaseAttributeService().getBaseAttributePaginatedList(entity);
		for (BaseAttribute bpa : list) {
			BaseAttributeSon son = new BaseAttributeSon();
			son.setAttr_id(bpa.getId());
			List<BaseAttributeSon> sonList = super.getFacade().getBaseAttributeSonService()
					.getBaseAttributeSonList(son);
			bpa.getMap().put("son_list", sonList);
		}
		request.setAttribute("entityList", list);
		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(request);
		saveToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		BaseAttribute entity = new BaseAttribute();
		BaseAttributeSon son = new BaseAttributeSon();
		if (null != id) {
			entity.setId(Long.parseLong(id));
			son.setAttr_id(Long.parseLong(id));
		}
		entity = getFacade().getBaseAttributeService().getBaseAttribute(entity);
		logger.info("=={}", entity.getIs_brand());
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);
		List<BaseAttributeSon> sonList = super.getFacade().getBaseAttributeSonService().getBaseAttributeSonList(son);
		request.setAttribute("sonList", sonList);
		request.setAttribute("edit", "edit");
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

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String cls_id = (String) dynaBean.get("cls_id");
		String class_mod_id = (String) dynaBean.get("class_mod_id");
		String attr_scope = (String) dynaBean.get("attr_scope");

		HttpSession session = request.getSession(false);
		UserInfo ui = (UserInfo) session.getAttribute(Keys.SESSION_USERINFO_KEY);

		BaseAttribute entity = new BaseAttribute();
		super.copyProperties(entity, form);

		String[] attr_name = request.getParameterValues("type_name");
		String[] attr_show_name = request.getParameterValues("type_show_name");
		String[] order_value = request.getParameterValues("order_value_son");
		String[] brand_ids = request.getParameterValues("brand_ids");
		String del_attr_id = (String) dynaBean.get("del_attr_id");
		if (StringUtils.isNotBlank(del_attr_id)) {
			entity.getMap().put("del_attr_id", del_attr_id);
		}
		if (ArrayUtils.isNotEmpty(attr_name) && ArrayUtils.isNotEmpty(attr_show_name)
				&& ArrayUtils.isNotEmpty(order_value)) {
			List<BaseAttributeSon> BaseAttributeSonList = new ArrayList<BaseAttributeSon>();
			for (int i = 0; i < attr_name.length; i++) {
				BaseAttributeSon bpa_son = new BaseAttributeSon();
				bpa_son.setOrder_value(Integer.valueOf(order_value[i]));
				bpa_son.setAttr_name(attr_name[i]);
				bpa_son.setAttr_show_name(attr_show_name[i]);
				if (StringUtils.isNotBlank(brand_ids[i])) {
					bpa_son.setBrand_id(Long.valueOf(brand_ids[i]));
				}
				BaseAttributeSonList.add(bpa_son);
			}
			entity.setBaseAttributeSonList(BaseAttributeSonList);
		}

		if (null != entity.getId()) {// update
			entity.setUpdate_date(new Date());
			entity.setUpdate_user_id(new Long(ui.getId()));
			getFacade().getBaseAttributeService().modifyBaseAttribute(entity);
			saveMessage(request, "entity.updated");
		} else if (null != entity) {// add
			entity.setIs_del(0);
			entity.setAdd_date(new Date());
			entity.setAdd_user_id(new Long(ui.getId()));
			Long id = getFacade().getBaseAttributeService().createBaseAttribute(entity);
			if (StringUtils.isNotBlank(cls_id)) {
				BaseClassLinkAttribute baseClassLinkAttribute = new BaseClassLinkAttribute();
				baseClassLinkAttribute.setCls_id(Long.valueOf(cls_id));
				baseClassLinkAttribute.setAttr_id(id);
				Long link_id = super.getFacade().getBaseClassLinkAttributeService().createBaseClassLinkAttribute(
						baseClassLinkAttribute);
				if (link_id > 0) {
					saveMessage(request, "entity.inserted");
					return new ActionForward("/admin/BasePdClass.do?method=linkAttribute&cls_id=" + cls_id
							+ "&attr_scope=" + attr_scope + "&mod_id=" + class_mod_id, true);

				} else {
					saveError(request, "errors.parm");
					return null;
				}
			}
			saveMessage(request, "entity.inserted");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id).append("&attr_scope=").append(attr_scope);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		logger.info("===pathBuffer.toString():{}", pathBuffer.toString());
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		UserInfo sessionUi = super.getUserInfoFromSession(request);

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			BaseAttribute entity = new BaseAttribute();
			entity.setIs_del(1);
			entity.setId(new Long(id));
			entity.setDel_date(new Date());
			entity.setDel_user_id(sessionUi.getId());
			getFacade().getBaseAttributeService().modifyBaseAttribute(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			BaseAttribute entity = new BaseAttribute();
			entity.setIs_del(1);
			entity.setDel_date(new Date());
			entity.setDel_user_id(sessionUi.getId());
			entity.getMap().put("pks", pks);
			getFacade().getBaseAttributeService().modifyBaseAttribute(entity);
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
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			// 取得此属性关联的类别
			BaseClassLinkAttribute baseClassLinkAttribute = new BaseClassLinkAttribute();
			baseClassLinkAttribute.setAttr_id(Long.valueOf(id));
			List<BaseClassLinkAttribute> pdClassList = super.getFacade().getBaseClassLinkAttributeService()
					.getBaseClassLinkAttributeList(baseClassLinkAttribute);
			List<BaseClassLinkAttribute> showPdClassList = new ArrayList<BaseClassLinkAttribute>();
			if (null != pdClassList && pdClassList.size() > 0) {
				for (BaseClassLinkAttribute t : pdClassList) {
					BaseClass pd = new BaseClass();
					pd.setCls_id(t.getCls_id());
					pd.setIs_del(0);
					pd = super.getFacade().getBaseClassService().getBaseClass(pd);
					if (null != pd) {
						showPdClassList.add(t);
						BaseClass pdForpar = new BaseClass();
						pdForpar.getMap().put("cls_id", t.getCls_id());
						List<BaseClass> pdList = super.getFacade().getBaseClassService().getBaseClassToParByLevelList(
								pdForpar);
						StringBuffer buffer = new StringBuffer();
						if (null != pdList && pdList.size() > 0) {
							for (int i = 0; i < pdList.size() - 1; i++) {
								BaseClass p = pdList.get(i);
								buffer.append(p.getCls_name()).append("->");
								if (null != p.getPar_id() && p.getPar_id() < 0) {
									t.getMap().put("pd_class_type", p.getPar_id());
								}
							}
							String str = buffer.toString();
							t.getMap().put("par_cls_name", str);
							t.getMap().put("cls_name", pdList.get(pdList.size() - 1).getCls_name());
						}
					}
				}
				request.setAttribute("pdClassList", showPdClassList);
			}
			BaseAttribute entity = new BaseAttribute();
			BaseAttributeSon entity_son = new BaseAttributeSon();
			entity.setId(new Long(id));
			entity_son.setAttr_id(new Long(id));
			entity = getFacade().getBaseAttributeService().getBaseAttribute(entity);
			List<BaseAttributeSon> sonList = super.getFacade().getBaseAttributeSonService().getBaseAttributeSonList(
					entity_son);
			request.setAttribute("sonList", sonList);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			super.copyProperties(form, entity);
			return mapping.findForward("view");
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
	}

	public ActionForward checkDel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		PdInfoCustomAttrContent pc = new PdInfoCustomAttrContent();
		JSONObject result = new JSONObject();
		if (StringUtils.isNotBlank(id)) {
			pc.setAttr_id(Long.parseLong(id));
			Long pd_count = super.getFacade().getPdInfoCustomAttrContentService().getPdInfoCustomAttrContentCount(pc);
			if (pd_count > 0) {
				result.put("have_pd_record", "true");
			}
		} else {
			result.put("have_record", "false");
		}
		logger.info(result.toString());
		super.render(response, result.toString(), "text/x-json;charset=UTF-8");

		return null;
	}
}
