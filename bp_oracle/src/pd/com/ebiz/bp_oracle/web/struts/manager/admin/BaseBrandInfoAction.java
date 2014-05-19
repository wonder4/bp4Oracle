package com.ebiz.bp_oracle.web.struts.manager.admin;

import java.util.ArrayList;
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
import org.json.JSONArray;
import org.json.JSONObject;

import com.ebiz.bp_oracle.domain.BaseBrandInfo;
import com.ebiz.bp_oracle.domain.NewsAttachment;
import com.ebiz.bp_oracle.web.Keys;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class BaseBrandInfoAction extends BaseAdminAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(request);
		saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("order_sort", "0");
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String is_del = (String) dynaBean.get("is_del");
		String brand_name_like = (String) dynaBean.get("brand_name_like");

		BaseBrandInfo entity = new BaseBrandInfo();
		super.copyProperties(entity, form);
		entity.getMap().put("brand_name_like", brand_name_like);
		if (null == is_del) {
			entity.setIs_del(0);
			dynaBean.set("is_del", "0");
		}

		Long recordCount = getFacade().getBaseBrandInfoService().getBaseBrandInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<BaseBrandInfo> list = getFacade().getBaseBrandInfoService().getBaseBrandInfoPaginatedList(entity);

		request.setAttribute("entityList", list);

		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(request);
		saveToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (GenericValidator.isLong(id)) {
			BaseBrandInfo entity = new BaseBrandInfo();
			entity.setBrand_id(Long.valueOf(id));
			entity = super.getFacade().getBaseBrandInfoService().getBaseBrandInfo(entity);
			// the line below is added for pagination
			entity.setQueryString(super.serialize(request, "id", "method"));
			// end
			super.copyProperties(form, entity);
			return mapping.findForward("input");
		} else {
			this.saveError(request, "errors.long", new String[] { id });
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

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		BaseBrandInfo entity = new BaseBrandInfo();
		super.copyProperties(entity, form);

		entity.setBrand_logo(null);

		List<UploadFile> uploadFileList = super.uploadFile(form, true, false, Keys.NEWS_INFO_IMAGE_SIZE);
		List<NewsAttachment> newsAttachmentList = new ArrayList<NewsAttachment>();
		NewsAttachment newsAttachment = null;
		for (UploadFile uploadFile : uploadFileList) {

			newsAttachment = new NewsAttachment();
			newsAttachment.setFile_name(uploadFile.getFileName());
			newsAttachment.setFile_type(uploadFile.getContentType());
			newsAttachment.setFile_size(Long.valueOf(uploadFile.getFileSize()));
			newsAttachment.setSave_path(uploadFile.getFileSavePath());
			newsAttachment.setSave_name(uploadFile.getFileSaveName());
			newsAttachment.setIs_del(0);
			newsAttachment.setLink_tab(uploadFile.getFormName());
			if ("brand_logo".equalsIgnoreCase(uploadFile.getFormName())) {
				entity.setBrand_logo(uploadFile.getFileSavePath());
			} else {
				newsAttachmentList.add(newsAttachment);
			}

		}

		if (StringUtils.isBlank(id)) {// insert
			entity.setIs_del(0);
			getFacade().getBaseBrandInfoService().createBaseBrandInfo(entity);
			saveMessage(request, "entity.inserted");
		} else {// update
			getFacade().getBaseBrandInfoService().modifyBaseBrandInfo(entity);
			saveMessage(request, "entity.updated");
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

		BaseBrandInfo entity = new BaseBrandInfo();
		entity.setIs_del(1);

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			entity.setBrand_id(Long.valueOf(id));
		} else if (!ArrayUtils.isEmpty(pks)) {
			entity.getMap().put("pks", pks);
		}
		getFacade().getBaseBrandInfoService().modifyBaseBrandInfo(entity);
		saveMessage(request, "entity.deleted");
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
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			BaseBrandInfo entity = new BaseBrandInfo();
			entity.setBrand_id(new Long(id));
			entity = super.getFacade().getBaseBrandInfoService().getBaseBrandInfo(entity);
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

	public ActionForward checkBrandName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String brand_name = (String) dynaBean.get("brand_name");
		String id = (String) dynaBean.get("id");

		BaseBrandInfo baseBrandInfo = new BaseBrandInfo();
		baseBrandInfo.getMap().put("not_in_id", id);
		baseBrandInfo.setBrand_name(brand_name);
		Long recordCount = super.getFacade().getBaseBrandInfoService().getBaseBrandInfoCount(baseBrandInfo);
		String flag = "1";
		if (recordCount.intValue() == 0) {
			flag = "0";
		}
		super.renderJson(response, flag);
		return null;
	}

	public ActionForward listSelectAllBrand(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String key_word = (String) dynaBean.get("key_word");
		String brand_label = (String) dynaBean.get("brand_label");
		String isAjax = (String) dynaBean.get("isAjax");
		BaseBrandInfo baseBrandInfo = new BaseBrandInfo();

		logger.info("=========key_word:{}", key_word);
		logger.info("=========brand_label:{}", brand_label);
		logger.info("=========isAjax:{}", isAjax);

		baseBrandInfo.setIs_del(0);
		if (StringUtils.isNotBlank(key_word)) {
			baseBrandInfo.getMap().put("brand_name_like", (key_word));
		}
		if (StringUtils.isNotBlank(brand_label)) {
			baseBrandInfo.setBrand_label(brand_label);
		}
		List<BaseBrandInfo> baseBrandInfoList = getFacade().getBaseBrandInfoService().getBaseBrandInfoList(
				baseBrandInfo);
		if ("true".equals(isAjax)) {
			JSONObject result = new JSONObject();
			JSONArray baseBrandInfoJsonList = new JSONArray();
			for (BaseBrandInfo bbi : baseBrandInfoList) {
				JSONObject obj = new JSONObject();
				obj.put("brand_id", bbi.getBrand_id());
				obj.put("brand_name", bbi.getBrand_name());
				baseBrandInfoJsonList.put(obj);
			}
			logger.info("=========baseBrandInfoJsonList:{}", baseBrandInfoJsonList.length());
			result.put("baseBrandInfoJsonList", baseBrandInfoJsonList);
			super.render(response, result.toString(), "text/x-json;charset=UTF-8");
			return null;
		} else {
			request.setAttribute("baseBrandInfoList", baseBrandInfoList);
		}
		String abc = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,OTHER";
		request.setAttribute("abcArray", StringUtils.split(abc, ","));
		request.setAttribute("baseBrandInfoList", baseBrandInfoList);

		return new ActionForward("/../view/jsp/manager/admin/BaseBrandInfo/listSelectAllBrand.jsp");
	}

}
