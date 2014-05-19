package com.ebiz.bp_oracle.web.struts.manager.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

import com.ebiz.bp_oracle.domain.BaseAttributeSon;
import com.ebiz.bp_oracle.domain.BaseClass;
import com.ebiz.bp_oracle.domain.BasePdClazz;
import com.ebiz.bp_oracle.domain.BaseProvince;
import com.ebiz.bp_oracle.domain.PdImgs;
import com.ebiz.bp_oracle.domain.PdInfo;
import com.ebiz.bp_oracle.domain.PdInfoCustomAttrContent;
import com.ebiz.bp_oracle.domain.PdInfoCustomFieldContent;
import com.ebiz.bp_oracle.domain.UserInfo;
import com.ebiz.bp_oracle.web.Keys;
import com.ebiz.bp_oracle.web.util.FileTools;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class PdInfoAction extends BaseAdminAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String is_del = (String) dynaBean.get("is_del");
		String audit_state = (String) dynaBean.get("audit_state");
		String cls_id = (String) dynaBean.get("cls_id");

		String country = (String) dynaBean.get("country");
		String city = (String) dynaBean.get("city");
		String province = (String) dynaBean.get("province");

		PdInfo entity = new PdInfo();

		entity.setP_index(null);
		if (StringUtils.isNotBlank(country)) {
			entity.setP_index(Long.valueOf(country));
		} else if (StringUtils.isNotBlank(city)) {
			entity.getMap().put("p_index_city", StringUtils.substring(city, 0, 4));
		} else if (StringUtils.isNotBlank(province)) {
			entity.getMap().put("p_index_province", StringUtils.substring(province, 0, 2));
		}

		if (StringUtils.isNotBlank(audit_state)) {
			entity.setAudit_state(new Integer(audit_state));
		}
		if (StringUtils.isNotBlank(is_del)) {
			if ("-1".equals(is_del)) {
				entity.setIs_del(null);
			}
			if ("0".equals(is_del)) {
				entity.setIs_del(0);
			}
			if ("1".equals(is_del)) {
				entity.setIs_del(1);
			}
		} else {
			entity.setIs_del(0);
			dynaBean.set("is_del", "0");
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}

		if (StringUtils.isNotBlank(cls_id)) {
			entity.setCls_id(null);
			entity.getMap().put("allPd", "true");
			entity.getMap().put("par_cls_id", cls_id);
		}
		entity.setPd_type(0);

		Long recordCount = getFacade().getPdInfoService().getPdInfoCount(entity);
		pager.init(recordCount.longValue(), Integer.valueOf("10"), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<PdInfo> entityList = super.getFacade().getPdInfoService().getPdInfoPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		BasePdClazz basePdClazz = new BasePdClazz();
		basePdClazz.setIs_del(0);
		basePdClazz.getMap().put("no_have_self", "1");
		List<BasePdClazz> basePdClazzTreeList = super.getFacade().getBasePdClazzService().getBasePdClazzList(
				basePdClazz);
		request.setAttribute("basePdClazzTreeList", basePdClazzTreeList);

		return mapping.findForward("list");

	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		saveToken(request);
		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("order_value", 0);

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
		String pd_id = (String) dynaBean.get("pd_id");
		String cls_id = (String) dynaBean.get("cls_id");
		String is_audit = (String) dynaBean.get("is_audit");
		String country = (String) dynaBean.get("country");
		String city = (String) dynaBean.get("city");
		String province = (String) dynaBean.get("province");

		Date date = new Date();

		PdInfo entity = new PdInfo();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(cls_id)) {// 保存par_cls_id
			BaseClass bc = new BaseClass();
			bc.setCls_id(Long.valueOf(cls_id));
			bc = super.getFacade().getBaseClassService().getBaseClass(bc);
			if (null != bc) {
				entity.setPar_cls_id(bc.getCls_id());
			}
		}

		List<PdImgs> pdImgsList = new ArrayList<PdImgs>();

		if (StringUtils.isNotBlank(country)) {
			entity.setP_index(Long.valueOf(country));
		} else if (StringUtils.isNotBlank(city)) {
			entity.setP_index(Long.valueOf(city));
		} else if (StringUtils.isNotBlank(province)) {
			entity.setP_index(Long.valueOf(province));
		}
		entity.setMain_pic(null);
		List<UploadFile> uploadFileList = super.uploadFile(form, true, false, Keys.NEWS_INFO_IMAGE_SIZE);
		for (UploadFile uploadFile : uploadFileList) {
			if ("main_pic".equalsIgnoreCase(uploadFile.getFormName())) {
				entity.setMain_pic(uploadFile.getFileSavePath());
			} else {
				PdImgs pdImgs = new PdImgs();
				pdImgs.setFile_path(uploadFile.getFileSavePath());
				pdImgsList.add(pdImgs);
			}
		}
		if (pdImgsList.size() > 0) {
			if (StringUtils.isNotBlank(pd_id)) {
				PdImgs pdImgs2 = new PdImgs();
				pdImgs2.setPd_id(Long.valueOf(pd_id));
				List<PdImgs> pdImgs2List = super.getFacade().getPdImgsService().getPdImgsList(pdImgs2);
				if (null != pdImgs2List) {
					pdImgsList.addAll(pdImgs2List);
				}
			}
			entity.setPdImgsList(pdImgsList);
		}

		// 自定义属性
		List<PdInfoCustomAttrContent> zdyAttrList = new ArrayList<PdInfoCustomAttrContent>();
		List<PdInfoCustomFieldContent> zdyList = new ArrayList<PdInfoCustomFieldContent>();
		String zdy[] = request.getParameterValues("zdy_column");
		if (ArrayUtils.isNotEmpty(zdy)) {
			for (String temp : zdy) {
				String zdy_column[] = temp.split(",");
				String zdy_content = null;
				if (Long.valueOf(zdy_column[1].trim()).intValue() == 4) {// checkbox
					String[] zdy_content_checkbox = request.getParameterValues("zdy_content_" + zdy_column[0]);
					zdy_content = StringUtils.join(zdy_content_checkbox, ",");
				} else {
					zdy_content = request.getParameter("zdy_content_" + zdy_column[0]);
				}
				PdInfoCustomFieldContent costomFieldContent = new PdInfoCustomFieldContent();
				String zdy_content2 = null;// 文本框
				if (Long.valueOf(zdy_column[1].trim()).intValue() == 1) {
					zdy_content2 = request.getParameter("zdy_content2_" + zdy_column[0]);
					costomFieldContent.setCustom_field_content(zdy_content2);
				} else {
					costomFieldContent.setCustom_field_content(zdy_content);
				}
				costomFieldContent.setCustom_field_id(Long.valueOf(zdy_column[0].trim()));
				costomFieldContent.setType(Integer.valueOf(zdy_column[1].trim()));
				costomFieldContent.setCustom_field_name(zdy_column[2].trim());
				costomFieldContent.setIs_required(Integer.valueOf(zdy_column[3].trim()));
				costomFieldContent.setIs_show(Integer.valueOf(zdy_column[4].trim()));
				costomFieldContent.setOrder_value(Integer.valueOf(zdy_column[5].trim()));
				zdyList.add(costomFieldContent);

				if (Long.valueOf(zdy_column[1].trim()).intValue() == 4) {// checkbox
					String[] zdy_content_checkbox = request.getParameterValues("zdy_content_" + zdy_column[0]);
					if (ArrayUtils.isNotEmpty(zdy_content_checkbox)) {
						for (String attr_son : zdy_content_checkbox) {
							PdInfoCustomAttrContent costomAttrContent = new PdInfoCustomAttrContent();
							costomAttrContent.setAttr_id(Long.valueOf(attr_son));
							costomAttrContent.setPar_attr_id(Long.valueOf(zdy_column[0].trim()));
							costomAttrContent.setCls_id(entity.getCls_id());

							BaseAttributeSon attr_son_entity = new BaseAttributeSon();
							attr_son_entity.setId(Long.valueOf(attr_son));
							attr_son_entity = super.getFacade().getBaseAttributeSonService().getBaseAttributeSon(
									attr_son_entity);
							if (null != attr_son_entity) {
								costomAttrContent.setAttr_name(attr_son_entity.getAttr_name());
								costomAttrContent.setBrand_id(attr_son_entity.getBrand_id());
							}

							zdyAttrList.add(costomAttrContent);
						}
					}
				} else {
					PdInfoCustomAttrContent costomAttrContent = new PdInfoCustomAttrContent();
					// logger.info("zdy_content:====================================={}", zdy_content);
					if (null != zdy_content) {
						costomAttrContent.setAttr_id(Long.valueOf(zdy_content));
					}
					costomAttrContent.setPar_attr_id(Long.valueOf(zdy_column[0].trim()));
					costomAttrContent.setCls_id(entity.getCls_id());

					BaseAttributeSon attr_son_entity = new BaseAttributeSon();
					attr_son_entity.setId(Long.valueOf(zdy_content));
					attr_son_entity = super.getFacade().getBaseAttributeSonService().getBaseAttributeSon(
							attr_son_entity);
					if (null != attr_son_entity) {
						costomAttrContent.setAttr_name(attr_son_entity.getAttr_name());
						costomAttrContent.setBrand_id(attr_son_entity.getBrand_id());
					}

					zdyAttrList.add(costomAttrContent);
				}
			}
		}
		entity.setPdInfoCustomFieldContentList(zdyList);
		entity.setPdInfoCustomAttrContentList(zdyAttrList);

		if (StringUtils.isNotBlank(pd_id)) {
			entity.setUpdate_date(date);
			entity.setUpdate_user_id(ui.getId());
			if (StringUtils.isNotBlank(is_audit)) {
				entity.setAudit_user_id(ui.getId());
				entity.setAudit_date(new Date());
			} else {
				entity.getMap().put("update_attr", "true");
				entity.getMap().put("update_content", "true");
			}
			super.getFacade().getPdInfoService().modifyPdInfo(entity);
			saveMessage(request, "entity.updated");
		} else {
			entity.setAdd_date(date);
			entity.setAdd_user_id(ui.getId());
			entity.setAdd_user_name(ui.getUser_name());
			entity.setIs_del(0);
			entity.setUuid(UUID.randomUUID().toString());
			super.getFacade().getPdInfoService().createPdInfo(entity);
			saveMessage(request, "entity.inserted");
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
		String pd_id = (String) dynaBean.get("pd_id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (StringUtils.isBlank(pd_id) && null == pks) {
			String msg = super.getMessage(request, "参数不能为空");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		HttpSession session = request.getSession(false);
		UserInfo ui = (UserInfo) session.getAttribute(Keys.SESSION_USERINFO_KEY);

		PdInfo entity = new PdInfo();
		Date date = new Date();
		entity.setIs_del(1);
		entity.setDel_date(date);
		entity.setDel_user_id(ui.getId());
		if (StringUtils.isNotBlank(pd_id) && GenericValidator.isLong(pd_id)) {

			entity.setPd_id(Long.valueOf(pd_id));

			entity.getMap().put("delete_attr", "true");
			super.getFacade().getPdInfoService().modifyPdInfo(entity);

			saveMessage(request, "entity.deleted");

		} else if (ArrayUtils.isNotEmpty(pks)) {

			String[] pd_ids = new String[pks.length];
			for (int i = 0; i < pks.length; i++) {
				pd_ids[i] = pks[i].split("_")[0];
			}

			entity.getMap().put("pks", pd_ids);
			entity.getMap().put("delete_attr", "true");
			super.getFacade().getPdInfoService().modifyPdInfo(entity);

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

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		saveToken(request);
		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;
		String pd_id = (String) dynaBean.get("pd_id");
		if (StringUtils.isBlank(pd_id)) {
			String msg = super.getMessage(request, "参数不能为空");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		if (GenericValidator.isLong(pd_id)) {
			PdInfo entity = new PdInfo();
			entity.setPd_id(Long.valueOf(pd_id));
			entity = getFacade().getPdInfoService().getPdInfo(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			} else {
				if (null != entity.getP_index()) {
					BaseProvince baseProvince = new BaseProvince();
					baseProvince.setP_index(new Long(entity.getP_index()));
					baseProvince = super.getFacade().getBaseProvinceService().getBaseProvince(baseProvince);
					if (null != entity.getP_index()) {
						super.setprovinceAndcityAndcountryToFrom(dynaBean, entity.getP_index());
					}
				}
				// 取自定义内容
				PdInfoCustomFieldContent customFieldContent = new PdInfoCustomFieldContent();
				customFieldContent.setPd_id(Long.valueOf(pd_id));
				List<PdInfoCustomFieldContent> pdInfoCustomFieldContentList = super.getFacade()
						.getPdInfoCustomFieldContentService().getPdInfoCustomFieldContentList(customFieldContent);
				if (null != pdInfoCustomFieldContentList && pdInfoCustomFieldContentList.size() > 0) {
					for (PdInfoCustomFieldContent temp : pdInfoCustomFieldContentList) {
						int type = temp.getType().intValue();
						if (type == 1 || type == 3 || type == 4 || type == 5) {
							BaseAttributeSon pd_attr_son = new BaseAttributeSon();
							pd_attr_son.setAttr_id(temp.getCustom_field_id());
							List<BaseAttributeSon> pdAttrSonList = super.getFacade().getBaseAttributeSonService()
									.getBaseAttributeSonList(pd_attr_son);
							temp.setBaseAttributeSonList(pdAttrSonList);

						}
						if (type == 1) {
							PdInfoCustomAttrContent atrr_cont = new PdInfoCustomAttrContent();
							atrr_cont.setPd_id(Long.valueOf(pd_id));
							atrr_cont.setPar_attr_id(temp.getCustom_field_id());
							atrr_cont = super.getFacade().getPdInfoCustomAttrContentService()
									.getPdInfoCustomAttrContent(atrr_cont);
							if (null != atrr_cont) {
								temp.getMap().put("attr_son_id", atrr_cont.getAttr_id());
							}
						}
					}
					request.setAttribute("pdInfoCustomFieldContentList", pdInfoCustomFieldContentList);
				}

				request.setAttribute("entity", entity);
			}

			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);
			request.setAttribute("shopList", getPdInfo());
			return mapping.findForward("input");
		} else {
			saveError(request, "errors.Long", pd_id);
			return mapping.findForward("list");
		}
	}

	public ActionForward audit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		saveToken(request);
		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;
		String pd_id = (String) dynaBean.get("pd_id");
		if (StringUtils.isBlank(pd_id)) {
			String msg = super.getMessage(request, "参数不能为空");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		if (GenericValidator.isLong(pd_id)) {
			PdInfo entity = new PdInfo();
			entity.setPd_id(Long.valueOf(pd_id));
			entity = getFacade().getPdInfoService().getPdInfo(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			} else {
				if (null != entity.getP_index()) {
					super.setprovinceAndcityAndcountryToFrom(dynaBean, entity.getP_index());
				}
				request.setAttribute("entity", entity);
			}

			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);
			request.setAttribute("shopList", getPdInfo());
			// 取自定义内容
			PdInfoCustomFieldContent customFieldContent = new PdInfoCustomFieldContent();
			customFieldContent.setPd_id(Long.valueOf(pd_id));
			List<PdInfoCustomFieldContent> pdInfoCustomFieldContentList = super.getFacade()
					.getPdInfoCustomFieldContentService().getPdInfoCustomFieldContentList(customFieldContent);
			if (null != pdInfoCustomFieldContentList && pdInfoCustomFieldContentList.size() > 0) {
				for (PdInfoCustomFieldContent temp : pdInfoCustomFieldContentList) {
					int type = temp.getType().intValue();
					if (type == 1 || type == 3 || type == 4 || type == 5) {
						BaseAttributeSon pd_attr_son = new BaseAttributeSon();
						pd_attr_son.setAttr_id(temp.getCustom_field_id());
						List<BaseAttributeSon> pdAttrSonList = super.getFacade().getBaseAttributeSonService()
								.getBaseAttributeSonList(pd_attr_son);
						temp.setBaseAttributeSonList(pdAttrSonList);

					}
					if (type == 1) {
						PdInfoCustomAttrContent atrr_cont = new PdInfoCustomAttrContent();
						atrr_cont.setPd_id(Long.valueOf(pd_id));
						atrr_cont.setPar_attr_id(temp.getCustom_field_id());
						atrr_cont = super.getFacade().getPdInfoCustomAttrContentService().getPdInfoCustomAttrContent(
								atrr_cont);
						if (null != atrr_cont) {
							temp.getMap().put("attr_son_id", atrr_cont.getAttr_id());
						}
					}
				}
				request.setAttribute("pdInfoCustomFieldContentList", pdInfoCustomFieldContentList);
			}
			return new ActionForward("/../view/jsp/manager/admin/PdInfo/audit.jsp");
		} else {
			saveError(request, "errors.long", pd_id);
			return mapping.findForward("list");
		}
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(request);
		DynaBean dynaBean = (DynaBean) form;
		String pd_id = (String) dynaBean.get("pd_id");
		if (StringUtils.isBlank(pd_id)) {
			String msg = super.getMessage(request, "参数不能为空");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		if (GenericValidator.isLong(pd_id)) {
			PdInfo entity = new PdInfo();
			entity.setPd_id(Long.valueOf(pd_id));
			entity = getFacade().getPdInfoService().getPdInfo(entity);
			super.copyProperties(form, entity);
			// 取自定义内容
			PdInfoCustomFieldContent customFieldContent = new PdInfoCustomFieldContent();
			customFieldContent.setPd_id(Long.valueOf(pd_id));
			List<PdInfoCustomFieldContent> pdInfoCustomFieldContentList = super.getFacade()
					.getPdInfoCustomFieldContentService().getPdInfoCustomFieldContentList(customFieldContent);
			if (null != pdInfoCustomFieldContentList && pdInfoCustomFieldContentList.size() > 0) {
				for (PdInfoCustomFieldContent temp : pdInfoCustomFieldContentList) {
					int type = temp.getType().intValue();
					if (type == 1 || type == 3 || type == 4 || type == 5) {
						BaseAttributeSon pd_attr_son = new BaseAttributeSon();
						pd_attr_son.setAttr_id(temp.getCustom_field_id());
						List<BaseAttributeSon> pdAttrSonList = super.getFacade().getBaseAttributeSonService()
								.getBaseAttributeSonList(pd_attr_son);
						temp.setBaseAttributeSonList(pdAttrSonList);

					}
					if (type == 1) {
						PdInfoCustomAttrContent atrr_cont = new PdInfoCustomAttrContent();
						atrr_cont.setPd_id(Long.valueOf(pd_id));
						atrr_cont.setPar_attr_id(temp.getCustom_field_id());
						atrr_cont = super.getFacade().getPdInfoCustomAttrContentService().getPdInfoCustomAttrContent(
								atrr_cont);
						if (null != atrr_cont) {
							temp.getMap().put("attr_son_id", atrr_cont.getAttr_id());
						}
					}
				}
				request.setAttribute("pdInfoCustomFieldContentList", pdInfoCustomFieldContentList);
			}
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", pd_id);
			return mapping.findForward("list");
		}
	}

	public List<PdInfo> getPdInfo() {
		PdInfo entity = new PdInfo();
		entity.setIs_del(0);
		return super.getFacade().getPdInfoService().getPdInfoList(entity);
	}

	public ActionForward deleteFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String file_path = (String) dynaBean.get("file_path");

		if (StringUtils.isBlank(id) || StringUtils.isBlank(file_path)) {
			super.renderText(response, "fail");
			return null;
		}
		logger.info("id:{}", id);
		logger.info("file_path:{}", file_path);

		PdImgs entity = new PdImgs();
		entity.setId(new Long(id));
		super.getFacade().getPdImgsService().removePdImgs(entity);

		String ctx = request.getSession().getServletContext().getRealPath(String.valueOf(File.separatorChar));
		String realFilePath = ctx + file_path;
		FileTools.deleteFile(realFilePath);

		super.renderText(response, "success");
		return null;
	}
}