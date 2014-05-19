package com.ebiz.bp_oracle.web.struts.manager.admin;

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

import com.ebiz.bp_oracle.domain.NewsAttachment;
import com.ebiz.bp_oracle.domain.NewsInfo;
import com.ebiz.bp_oracle.domain.UserInfo;
import com.ebiz.bp_oracle.web.Keys;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class NewsInfoAction extends BaseAdminAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		saveToken(request);
		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("order_value", "0");
		dynaBean.set("is_use_direct_uri", "0");
		dynaBean.set("is_use_invalid_date", "0");
		dynaBean.set("pub_time", new Date());

		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;

		String pub_state = (String) dynaBean.get("pub_state");
		String mod_id = (String) dynaBean.get("mod_id");
		String st_pub_date = (String) dynaBean.get("st_pub_date");
		String en_pub_date = (String) dynaBean.get("en_pub_date");
		String title_like = (String) dynaBean.get("title_like");

		Pager pager = (Pager) dynaBean.get("pager");

		NewsInfo entity = new NewsInfo();
		entity.setIs_del(0);
		entity.setMod_id(mod_id);

		if (null != pub_state) {
			if ("0".equalsIgnoreCase(pub_state)) {
				entity.getMap().put("no_pub", "0");
			} else if ("1".equalsIgnoreCase(pub_state)) {
				entity.getMap().put("is_pub", "0");
			}
		}

		entity.getMap().put("title_like", title_like);
		entity.getMap().put("st_pub_date", st_pub_date);
		entity.getMap().put("en_pub_date", en_pub_date);
		entity.getMap().put("invalid_date", "invalid_date");
		
		super.copyProperties(entity, form);

		Long recordCount = getFacade().getNewsInfoService().getNewsInfoCount(entity);
		pager.init(recordCount.longValue(), Integer.valueOf("10"), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<NewsInfo> newsInfoList = getFacade().getNewsInfoService().getNewsInfoPaginatedList(entity);

		request.setAttribute("entityList", newsInfoList);
		
		return mapping.findForward("list");

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

		String is_audit = (String) dynaBean.get("is_audit");// 区别审核(只需要修改一个状态) 和 修改、添加
		NewsInfo entity = new NewsInfo();
		super.copyProperties(entity, form);
		if (StringUtils.isBlank(is_audit)) {// 添加和修改
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
				entity.setUpdate_time(new Date());
				entity.setUpdate_uid(new Long(ui.getId()));
				entity.getMap().put("update_content", "true");
				entity.getMap().put("update_attachment", "true");
				getFacade().getNewsInfoService().modifyNewsInfo(entity);
				saveMessage(request, "entity.updated");
			}
		} else {// 审核，只修改状态
			entity.setUpdate_time(new Date());
			entity.setUpdate_uid(new Long(ui.getId()));

			getFacade().getNewsInfoService().modifyNewsInfo(entity);
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
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		HttpSession session = request.getSession(false);
		UserInfo ui = (UserInfo) session.getAttribute(Keys.SESSION_USERINFO_KEY);

		NewsInfo entity = new NewsInfo();
		entity.setUpdate_uid(ui.getId());
		entity.setDel_time(new Date());
		entity.setDel_uid(ui.getId());
		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			entity.setId(Long.valueOf(id));
			getFacade().getNewsInfoService().removeNewsInfo(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			entity.getMap().put("pks", pks);
			getFacade().getNewsInfoService().removeNewsInfo(entity);
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
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		saveToken(request);
		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (GenericValidator.isLong(id)) {
			NewsInfo newsInfo = new NewsInfo();
			newsInfo.setId(Long.valueOf(id));
			newsInfo = getFacade().getNewsInfoService().getNewsInfo(newsInfo);

			NewsAttachment attachment = new NewsAttachment();
			attachment.setLink_id(Long.valueOf(id));
			request.setAttribute("attachmentList", getFacade().getNewsAttachmentService().getNewsAttachmentList(
					attachment));

			if (null == newsInfo) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			// the line below is added for pagination
			newsInfo.setQueryString(super.serialize(request, "id", "method"));
			// end
			super.copyProperties(form, newsInfo);
			
			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
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

		NewsAttachment entity = new NewsAttachment();
		entity.setId(Long.valueOf(id));
		getFacade().getNewsAttachmentService().removeNewsAttachment(entity);

		// 删除物理文件，暂时不删除
		// String ctx = request.getSession().getServletContext().getRealPath(String.valueOf(File.separatorChar));
		// String realFilePath = ctx + file_path;
		// FileTools.deleteFile(realFilePath);

		super.renderText(response, "success");
		return null;
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
			NewsInfo newsInfo = new NewsInfo();
			newsInfo.setId(Long.valueOf(id));
			newsInfo = getFacade().getNewsInfoService().getNewsInfo(newsInfo);

			NewsAttachment attachment = new NewsAttachment();
			attachment.setLink_id(Long.valueOf(id));
			request.setAttribute("attachmentList", getFacade().getNewsAttachmentService().getNewsAttachmentList(
					attachment));

			if (null == newsInfo) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			super.copyProperties(form, newsInfo);

			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public ActionForward audit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "8")) {
			return super.checkPopedomInvalid(request, response);
		}

		saveToken(request);
		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (GenericValidator.isLong(id)) {
			NewsInfo newsInfo = new NewsInfo();
			newsInfo.setId(Long.valueOf(id));
			newsInfo = getFacade().getNewsInfoService().getNewsInfo(newsInfo);

			NewsAttachment attachment = new NewsAttachment();
			attachment.setLink_id(Long.valueOf(id));
			request.setAttribute("attachmentList", getFacade().getNewsAttachmentService().getNewsAttachmentList(
					attachment));

			if (null == newsInfo) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			// the line below is added for pagination
			newsInfo.setQueryString(super.serialize(request, "id", "method"));
			// end
			super.copyProperties(form, newsInfo);
			
			return new ActionForward("/../view/jsp/manager/admin/NewsInfo/audit.jsp");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	public ActionForward deleteImageOrVideo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String type = (String) dynaBean.get("type");
		String file_path = (String) dynaBean.get("file_path");

		if (StringUtils.isBlank(id) || StringUtils.isBlank(type) || StringUtils.isBlank(file_path)) {
			super.renderText(response, "fail");
			return null;
		}

		logger.info("id:{}", id);
		logger.info("type:{}", type);
		logger.info("file_path:{}", file_path);

		NewsInfo newsInfo = new NewsInfo();
		newsInfo.setId(Long.valueOf(id));
		newsInfo.getMap().put("clear_" + type, "true");// clear_image_path和clear_video_path
		getFacade().getNewsInfoService().modifyNewsInfo(newsInfo);

		// 删除物理文件，暂时不删除
		// String ctx = request.getSession().getServletContext().getRealPath(String.valueOf(File.separatorChar));
		// String realFilePath = ctx + file_path;
		// FileTools.deleteFile(realFilePath);

		super.renderText(response, "success");
		return null;
	}
}