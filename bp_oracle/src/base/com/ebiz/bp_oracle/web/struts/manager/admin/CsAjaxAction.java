package com.ebiz.bp_oracle.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ebiz.bp_oracle.domain.BaseAttribute;
import com.ebiz.bp_oracle.domain.BaseAttributeSon;
import com.ebiz.bp_oracle.domain.BaseClassLinkAttribute;
import com.ebiz.bp_oracle.domain.BasePdClazz;
import com.ebiz.bp_oracle.domain.BaseProvince;
import com.ebiz.bp_oracle.domain.DeptInfo;
import com.ebiz.bp_oracle.domain.UserInfo;
import com.ebiz.bp_oracle.web.util.StringHelper;
import com.ebiz.ssi.util.EncryptUtils;

public class CsAjaxAction extends BaseAdminAction {

	/**
	 * Ajax asynchronous request to get BaseProvince List
	 * 
	 * @return json: [[key, value],[key, value]..]
	 */
	public ActionForward getBaseProvinceList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String par_index = (String) dynaBean.get("p_index");

		if (!GenericValidator.isLong(par_index)) {
			return null;
		}

		BaseProvince baseProvince = new BaseProvince();
		baseProvince.setPar_index(Long.valueOf(par_index));
		baseProvince.setIs_del(new Integer("0"));

		List<BaseProvince> baseProvinceList = getFacade().getBaseProvinceService().getBaseProvinceList(baseProvince);
		List<String> dataList = new ArrayList<String>();

		for (BaseProvince entity : baseProvinceList) {
			dataList.add(StringUtils.join(new String[] { "[\"", entity.getP_name(), "\",\"",
					String.valueOf(entity.getP_index()), "\"]" }));
		}

		super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		return null;
	}

	/**
	 * @desc 取得始发地的省市县信息
	 */
	public ActionForward getSrcBaseProvinceList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String par_index = (String) dynaBean.get("src_p_index");

		if (!GenericValidator.isLong(par_index)) {
			return null;
		}

		BaseProvince baseProvince = new BaseProvince();
		baseProvince.setPar_index(Long.valueOf(par_index));
		baseProvince.setIs_del(new Integer("0"));

		List<BaseProvince> baseProvinceList = getFacade().getBaseProvinceService().getBaseProvinceList(baseProvince);
		List<String> dataList = new ArrayList<String>();

		for (BaseProvince entity : baseProvinceList) {
			dataList.add(StringUtils.join(new String[] { "[\"", entity.getP_name(), "\",\"",
					String.valueOf(entity.getP_index()), "\"]" }));
		}

		super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		return null;
	}

	/**
	 * @desc 取得目的地的省市县信息
	 */
	public ActionForward getDestBaseProvinceList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String par_index = (String) dynaBean.get("dest_p_index");

		if (!GenericValidator.isLong(par_index)) {
			return null;
		}

		BaseProvince baseProvince = new BaseProvince();
		baseProvince.setPar_index(Long.valueOf(par_index));
		baseProvince.setIs_del(new Integer("0"));

		List<BaseProvince> baseProvinceList = getFacade().getBaseProvinceService().getBaseProvinceList(baseProvince);
		List<String> dataList = new ArrayList<String>();

		for (BaseProvince entity : baseProvinceList) {
			dataList.add(StringUtils.join(new String[] { "[\"", entity.getP_name(), "\",\"",
					String.valueOf(entity.getP_index()), "\"]" }));
		}

		super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		return null;
	}

	public ActionForward getBaseClassList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String key_word = (String) dynaBean.get("key_word");
		String ajax = (String) dynaBean.get("ajax");
		String pd_class_type = (String) dynaBean.get("pd_class_type");

		if (StringUtils.equals("-1", pd_class_type)) {
			// 商网产品视图
			BasePdClazz entityPd = new BasePdClazz();
			entityPd.setIs_del(0);
			if ("1".equals(ajax)) {
				JSONObject result = new JSONObject();
				JSONArray list = new JSONArray();
				if (StringUtils.isNotBlank(key_word)) {
					entityPd.getMap().put("cls_name_like", key_word);
				}
				List<BasePdClazz> entityList = super.getFacade().getBasePdClazzService().getBasePdClazzList(entityPd);
				for (BasePdClazz bpd : entityList) {
					JSONObject obj = new JSONObject();
					obj.put("cls_id", bpd.getCls_id());
					obj.put("tree_name", bpd.getTree_name());
					obj.put("cls_name", bpd.getCls_name());
					obj.put("is_leaf", bpd.getIs_leaf());
					list.put(obj);
				}
				result.put("list", list);
				super.render(response, result.toString(), "text/x-json;charset=UTF-8");
				return null;
			}
			request.setAttribute("entityList", super.getFacade().getBasePdClazzService().getBasePdClazzList(entityPd));
		}

		return new ActionForward("/../view/jsp/manager/admin/CsAjax/list_base_class.jsp");
	}

	public ActionForward getPdAttrbute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String cls_id = (String) dynaBean.get("cls_id");

		logger.info("cls_id:{}", cls_id);

		if (StringUtils.isBlank(cls_id)) {
			return null;
		}
		JSONObject result = new JSONObject();
		BaseClassLinkAttribute pdclass_link_attr = new BaseClassLinkAttribute();
		pdclass_link_attr.setCls_id(Long.valueOf(cls_id));
		List<BaseClassLinkAttribute> pdClassLinkAttrList = super.getFacade().getBaseClassLinkAttributeService()
				.getBaseClassLinkAttributeList(pdclass_link_attr);
		if (null != pdClassLinkAttrList) {
			JSONArray list = new JSONArray();
			for (BaseClassLinkAttribute link : pdClassLinkAttrList) {
				// 取主属性
				BaseAttribute pd_attr = new BaseAttribute();
				pd_attr.setId(link.getAttr_id());
				pd_attr = super.getFacade().getBaseAttributeService().getBaseAttribute(pd_attr);
				// 取子属性
				if (null != pd_attr) {
					JSONObject obj = new JSONObject();
					obj.put("id", pd_attr.getId());
					obj.put("attr_name", pd_attr.getAttr_name());
					obj.put("attr_show_name", pd_attr.getAttr_show_name());
					obj.put("type", pd_attr.getType());
					obj.put("is_required", pd_attr.getIs_required());
					obj.put("is_show", pd_attr.getIs_show());
					obj.put("order_value", pd_attr.getOrder_value());

					BaseAttributeSon pd_attr_son = new BaseAttributeSon();
					pd_attr_son.setAttr_id(pd_attr.getId());
					List<BaseAttributeSon> pdAttrSonList = super.getFacade().getBaseAttributeSonService()
							.getBaseAttributeSonList(pd_attr_son);
					if (null != pdAttrSonList) {
						JSONArray son_list = new JSONArray();
						for (BaseAttributeSon attr_son : pdAttrSonList) {
							JSONObject son_obj = new JSONObject();
							son_obj.put("id", attr_son.getId());
							son_obj.put("attr_name", attr_son.getAttr_name());
							son_obj.put("attr_show_name", attr_son.getAttr_show_name());
							son_list.put(son_obj);
						}
						obj.put("son_list", son_list);
					}
					list.put(obj);
				}
			}
			result.put("list", list);
		}
		super.render(response, result.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward getDeptInfoList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id"); // by wuxs

		List<DeptInfo> deptInfoList = getFacade().getDeptInfoService().getDeptInfoList(new DeptInfo());

		String treeNodes = StringHelper.getjQzTreeNodesFromDeptInfoList(deptInfoList, dept_id); // by wuxs

		request.setAttribute("treeNodes", treeNodes);

		return new ActionForward("/../view/jsp/manager/admin/CsAjax/list_dept_info.jsp");
	}

	public ActionForward initPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String uid = request.getParameter("uid");
		String password = request.getParameter("password");

		UserInfo entity = new UserInfo();
		entity.setId(new Long(uid));
		entity.setPassword(EncryptUtils.MD5Encode(password));
		JSONObject result = new JSONObject();
		int rows = super.getFacade().getUserInfoService().modifyUserInfo(entity);
		String msg = getMessage(request, "password.updated.success");

		result.put("result", rows);
		result.put("msg", msg);

		super.render(response, result.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

}
