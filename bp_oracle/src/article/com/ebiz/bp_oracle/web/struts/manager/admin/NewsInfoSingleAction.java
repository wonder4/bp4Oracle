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
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.bp_oracle.domain.NewsAttachment;
import com.ebiz.bp_oracle.domain.NewsInfo;
import com.ebiz.bp_oracle.domain.SysModule;
import com.ebiz.bp_oracle.domain.UserInfo;
import com.ebiz.bp_oracle.web.Keys;
import com.ebiz.bp_oracle.web.util.FileTools;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class NewsInfoSingleAction extends BaseAdminAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.add(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(mod_id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}

		SysModule sm = new SysModule();
		sm.setMod_id(new Long(mod_id));
		sm = super.getFacade().getSysModuleService().getSysModule(sm);
		if (null != sm) {
			dynaBean.set("module_name", sm.getMod_name());
		}

		NewsInfo newsInfoentity = new NewsInfo();
		newsInfoentity.setMod_id((mod_id));
		newsInfoentity.setIs_del(0);
		newsInfoentity = getFacade().getNewsInfoService().getNewsInfo(newsInfoentity);
		if (null != newsInfoentity) {
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append("/admin/NewsInfoSingle.do?method=edit");
			pathBuffer.append("&mod_id=").append(mod_id);
			pathBuffer.append("&id=").append(newsInfoentity.getId());
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			return forward;
		}

		NewsInfo entity = new NewsInfo();
		entity.setMod_id(mod_id);
		entity.setOrder_value(0);
		entity.setIs_use_direct_uri(0);
		entity.setIs_use_invalid_date(0);
		entity.setInfo_state(0);
		entity.setPub_time(new Date());

		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (isCancelled(request)) {
			return mapping.findForward("input");
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return mapping.findForward("input");
		}
		resetToken(request);

		HttpSession session = request.getSession(false);
		UserInfo ui = (UserInfo) session.getAttribute(Keys.SESSION_USERINFO_KEY);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		// 修改、添加
		NewsInfo entity = new NewsInfo();
		entity.setMod_id(mod_id);
		super.copyProperties(entity, form);

		// 添加和修改
		if (null == entity.getTitle_is_strong()) {
			entity.setTitle_is_strong(0);
		}
		entity.setImage_path(null);
		entity.setVideo_path(null);

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
			if ("image_path".equalsIgnoreCase(uploadFile.getFormName())) {
				entity.setImage_path(uploadFile.getFileSavePath());
			} else if ("video_path".equalsIgnoreCase(uploadFile.getFormName())) {
				entity.setVideo_path(uploadFile.getFileSavePath());
			} else {
				newsAttachmentList.add(newsAttachment);
			}

		}
		entity.setNewsAttachmentList(newsAttachmentList);
		if (null == entity.getId()) {
			Date sysDate = new Date();
			entity.setView_count(Long.valueOf("0"));
			entity.setIs_del(0);
			entity.setAdd_time(sysDate);
			entity.setAdd_uid(new Long(new Long(ui.getId())));
			entity.setUpdate_time(sysDate);
			entity.setUpdate_uid(new Long(ui.getId()));
			entity.setUuid(UUID.randomUUID().toString());
			getFacade().getNewsInfoService().createNewsInfo(entity);
			saveMessage(request, "entity.inserted");

		} else if (null != entity) {
			NewsInfo newsInfo = new NewsInfo();
			newsInfo.setId(entity.getId());
			entity.setUpdate_time(new Date());
			entity.setUpdate_uid(new Long(ui.getId()));
			newsInfo = getFacade().getNewsInfoService().getNewsInfo(newsInfo);

			entity.getMap().put("update_content", "true");
			entity.getMap().put("update_attachment", "true");

			getFacade().getNewsInfoService().modifyNewsInfo(entity);
			saveMessage(request, "entity.updated");

		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("/admin/NewsInfoSingle.do?method=edit");
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&id=").append(entity.getId());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		logger.info("======={}", pathBuffer.toString());
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
		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(mod_id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		SysModule sm = new SysModule();
		sm.setMod_id(new Long(mod_id));
		sm = super.getFacade().getSysModuleService().getSysModule(sm);
		if (null != sm) {
			dynaBean.set("module_name", sm.getMod_name());
		}

		NewsInfo newsInfo = new NewsInfo();
		newsInfo.setId(Long.valueOf(id));
		newsInfo = getFacade().getNewsInfoService().getNewsInfo(newsInfo);

		NewsAttachment attachment = new NewsAttachment();
		attachment.setLink_id(Long.valueOf(id));
		request
				.setAttribute("attachmentList", getFacade().getNewsAttachmentService()
						.getNewsAttachmentList(attachment));

		if (null == newsInfo) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		// the line below is added for pagination
		newsInfo.setQueryString(super.serialize(request, "id", "method"));
		// end
		super.copyProperties(form, newsInfo);
		return mapping.findForward("input");
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

		NewsAttachment entity = new NewsAttachment();
		entity.setId(Long.valueOf(id));
		getFacade().getNewsAttachmentService().removeNewsAttachment(entity);

		String ctx = request.getSession().getServletContext().getRealPath(String.valueOf(File.separatorChar));
		String realFilePath = ctx + file_path;
		FileTools.deleteFile(realFilePath);

		super.renderText(response, "success");
		return null;
	}
}