package com.ebiz.bp_oracle.web.struts.manager.admin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.ebiz.bp_oracle.web.struts.manager.admin.BaseAdminAction;
import com.ebiz.bp_oracle.domain.MailAttachment;
import com.ebiz.bp_oracle.domain.MailMain;
import com.ebiz.bp_oracle.domain.MailPeop;
import com.ebiz.bp_oracle.domain.UserInfo;
import com.ebiz.bp_oracle.web.Keys;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author ZHANG,CHAO
 * @version 2014-06-16 下午04:55:05
 */
public class MailMainAction extends BaseAdminAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.cclist(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(mod_id)) {
			this.saveError(request, "errors.long", new String[] { mod_id });
			return mapping.findForward("list");
		}

		saveToken(request);
		setNaviStringToRequestScope(request);

		return mapping.findForward("input");
	}

	// 发件箱
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Keys.SESSION_USERINFO_KEY);

		Pager pager = (Pager) dynaBean.get("pager");

		MailMain entity = new MailMain();
		super.copyProperties(entity, form);

		entity.setIs_float(0l); // 发件箱 不显示重要通知
		entity.getMap().put("rece_id", userInfo.getId().toString());

		Long recordCount = getFacade().getMailMainService().getMailMainCountForSend(entity);

		pager.init(recordCount, 15, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<MailMain> entityList = getFacade().getMailMainService().getMailMainPaginatedListForSend(entity);

		request.setAttribute("entityList", entityList);

		dynaBean.set("recordCount", recordCount.toString());

		return mapping.findForward("list");
	}

	// 发件保存
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String parid = (String) dynaBean.get("parid"); // 回复邮件时，parid表示的是回复父邮件ID
		String rec_user_ids = request.getParameter("rec_user_ids"); // 收件人 IDs
		String cc_user_ids = request.getParameter("cc_user_ids"); // 抄送人IDs
		String mail_state = (String) dynaBean.get("mail_state");

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Keys.SESSION_USERINFO_KEY);

		MailMain entity = new MailMain();
		entity.setPar_id(0l);
		entity.setSend_user_id(userInfo.getId());
		entity.setAdd_date(new Date());
		if (StringUtils.isNotBlank(mail_state)) {
			if (Integer.valueOf(mail_state) == 1) {
				entity.setSend_date(new Date());
			}
		}
		entity.getMap().put("mail_state", mail_state.toString());
		entity.getMap().put("current_user", userInfo.getId().toString());

		super.copyProperties(entity, form);
		// 附件
		String uploadDir = "files" + File.separator + "upload" + File.separator + "mail" + File.separator;
		List<UploadFile> uploadFileList = super.uploadFile(form, uploadDir, true, 0, new int[] { 120 });
		List<MailAttachment> attachmentList = new ArrayList<MailAttachment>();
		MailAttachment attachment = null;
		for (UploadFile uploadFile : uploadFileList) {
			if ((new Integer(uploadFile.getFileSize()) / 1024) / 1024 > 30) {
				super
						.renderJavaScript(response,
								"window.onload=function(){alert('附件大于只能在30M之内，请重新填写！');location.href='MailMain.do?method=add&mod_id=9100001000'}");
				return null;
			} else {
				attachment = new MailAttachment();
				attachment.setFile_name(uploadFile.getFileName());
				attachment.setFile_type(uploadFile.getContentType());
				attachment.setFile_size(Long.valueOf(uploadFile.getFileSize()).toString());
				attachment.setSave_path(uploadFile.getFileSavePath());
				attachment.setSave_name(uploadFile.getFileSaveName());
				attachment.setIs_del(0l);
				attachment.setLink_tab("MAIL_MAIN");
				attachmentList.add(attachment);
			}
		}

		entity.setAttachmentList(attachmentList);
		entity.getMap().put("rec_user_ids", rec_user_ids);
		entity.getMap().put("cc_user_ids", cc_user_ids);
		if (StringUtils.isNotBlank(parid)) {
			entity.setPar_id(Long.valueOf(parid));
		} else {
			entity.setPar_id(0l);
		}

		entity.setIs_float(0l);//

		StringBuffer pathBuffer = new StringBuffer();

		if (null != entity.getPar_id()) {
			super.getFacade().getMailMainService().createMailMain(entity);
			saveMessage(request, "entity.inserted");
			pathBuffer.append("/admin/MailMain.do?method=list");
		} else if (null == entity.getId()) {// insert
			super.getFacade().getMailMainService().createMailMain(entity);
			saveMessage(request, "entity.inserted");
			pathBuffer.append("/admin/MailMain.do?method=list");
		} else if (null != entity) {// update
			getFacade().getMailMainService().modifyMailMain(entity);
			saveMessage(request, "entity.updated");
			pathBuffer.append("/admin/MailMain.do?method=cclist");
		}
		// the line below is added for pagination
		// if (StringUtils.isNotBlank(parid)) {
		// pathBuffer.append("/admin/MailMain.do?method=cclist");
		// } else {
		// pathBuffer.append(mapping.findForward("success").getPath());
		// }
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	// 发件查看
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		if (!GenericValidator.isLong(mod_id)) {
			this.saveError(request, "errors.long", new String[] { mod_id });
			return mapping.findForward("list");
		}

		MailMain entity = new MailMain();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getMailMainService().getMailMain(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		// 人员处理
		// 发送人
		MailPeop mainPepo = new MailPeop();
		mainPepo.setMail_id(Long.valueOf(id));
		mainPepo.setIs_rece(0l);
		mainPepo.setIs_del(0l);
		List<MailPeop> mainPepoList = super.getFacade().getMailPeopService().getMailPeopList(mainPepo);
		request.setAttribute("mainPepoList", mainPepoList);

		// 收件人
		MailPeop mainPepoRec = new MailPeop();
		mainPepoRec.setMail_id(Long.valueOf(id));
		mainPepoRec.setIs_rece(1l);
		mainPepoRec.getMap().put("isreceive", 1);
		mainPepoRec.setIs_del(0l);
		List<MailPeop> mailPepoRecList = super.getFacade().getMailPeopService().getMailPeopList(mainPepoRec);
		request.setAttribute("mailPepoRecList", mailPepoRecList);

		// 抄送人
		MailPeop mainPepoCc = new MailPeop();
		mainPepoCc.setMail_id(Long.valueOf(id));
		mainPepoCc.setIs_rece(2l);
		mainPepoCc.getMap().put("isreceive", 1);
		mainPepoCc.setIs_del(0l);
		List<MailPeop> mainPepoCcList = super.getFacade().getMailPeopService().getMailPeopList(mainPepoCc);
		request.setAttribute("mainPepoCcList", mainPepoCcList);

		// 附件处理
		MailAttachment attachment = new MailAttachment();
		attachment.setLink_id(entity.getId());
		attachment.setLink_tab("MAIL_MAIN");
		attachment.setIs_del(0l);
		List<MailAttachment> attachmentList = getFacade().getMailAttachmentService().getMailAttachmentList(attachment);
		request.setAttribute("attachmentList", attachmentList);

		// the line below is added for pagination
		entity.setQueryString(super.serialize(request, "id", "method"));
		// end
		super.copyProperties(form, entity);

		dynaBean.set("can_re", 0); // 是否有回复功能 此处为否
		dynaBean.set("mod_id", mod_id);

		return mapping.findForward("view");
	}

	// 收件箱查看时点击回复
	public ActionForward replay(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(request);
		saveToken(request);
		setNaviStringToRequestScope(request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		if (!GenericValidator.isLong(mod_id)) {
			this.saveError(request, "errors.long", new String[] { mod_id });
			return mapping.findForward("list");
		}
		dynaBean.set("mod_id", mod_id);

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Keys.SESSION_USERINFO_KEY);

		MailMain entity = new MailMain();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getMailMainService().getMailMain(entity);
		StringBuffer sb = new StringBuffer();

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		dynaBean.set("parid", entity.getId().toString());

		SimpleDateFormat oSdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");

		// 人员处理
		// 发送人为登陆人
		dynaBean.set("fasong_ids", userInfo.getId().toString());
		dynaBean.set("fasong_names", userInfo.getUser_name().toString());

		// 收件人
		MailPeop mainPepoRec = new MailPeop();
		mainPepoRec.setMail_id(Long.valueOf(id));
		mainPepoRec.setIs_rece(0l);
		mainPepoRec.setIs_del(0l);
		MailPeop mailPepoRc = super.getFacade().getMailPeopService().getMailPeop(mainPepoRec);
		if (mailPepoRc != null) {
			dynaBean.set("recids", mailPepoRc.getRece_id());
			dynaBean.set("recnames", mailPepoRc.getMap().get("user_name").toString());
			dynaBean.set("recrealnames", mailPepoRc.getMap().get("real_name").toString());
		}
		request.setAttribute("mailPepoRc", mailPepoRc);
		// 父邮件收件人信息
		MailPeop mPepoRec = new MailPeop();
		mPepoRec.setMail_id(Long.valueOf(id));
		mPepoRec.setIs_rece(1l);
		mPepoRec.getMap().put("isreceive", 1);
		mPepoRec.setIs_del(0l);
		List<MailPeop> mailPepoRecList = super.getFacade().getMailPeopService().getMailPeopList(mPepoRec);
		StringBuffer ss = new StringBuffer();
		if (mailPepoRecList != null) {
			for (int i = 0; i < mailPepoRecList.size(); i++) {
				if (i < mailPepoRecList.size() - 1) {
					ss.append(mailPepoRecList.get(i).getMap().get("user_name")).append("; ");
				} else {
					ss.append(mailPepoRecList.get(i).getMap().get("user_name"));
				}
			}
		}

		// 父邮件抄送人
		MailPeop mainPepoCc = new MailPeop();
		mainPepoCc.setMail_id(Long.valueOf(id));
		mainPepoCc.setIs_rece(2l);
		mainPepoCc.getMap().put("isreceive", 1);
		mainPepoCc.setIs_del(0l);
		List<MailPeop> mainPepoCcList = super.getFacade().getMailPeopService().getMailPeopList(mainPepoCc);

		StringBuffer sbf = new StringBuffer();
		if (mainPepoCcList != null) {
			for (int i = 0; i < mainPepoCcList.size(); i++) {
				if (i < mailPepoRecList.size() - 1) {
					sbf.append(mainPepoCcList.get(i).getMap().get("user_name")).append("   ;  ");
				} else {
					sbf.append(mainPepoCcList.get(i).getMap().get("user_name"));
				}
			}
		}

		sb.append("<br/><br/><br/><br/>");
		sb.append("-------------------------------------------------------------------------------------");
		sb.append("<br/>");
		if (entity != null && entity.getContent() != null) {
			dynaBean.set("re_title", "RE：" + entity.getTitle());
			sb.append("发件人：").append(entity.getMap().get("user_name")).append("<br/>");
			sb.append("发件时间：").append(oSdf.format(entity.getSend_date())).append("<br/>");
			sb.append("收件人：").append(ss).append("<br/>");
			if (StringUtils.isNotBlank(sbf.toString())) {
				sb.append("抄送人：").append(sbf).append("<br/>");
			}
			sb.append("主题：").append(entity.getTitle()).append("<br/>");
			sb.append("-------------------------------------------------------------------------------------");
			sb.append("<br/>");
			sb.append(entity.getContent());
		}
		dynaBean.set("content", sb.toString());

		// the line below is added for pagination
		entity.setQueryString(super.serialize(request, "id", "method"));
		// end
		return new ActionForward("/../view/jsp/manager/admin/MailMain/edit.jsp");
	}

	// 发件箱删除
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Keys.SESSION_USERINFO_KEY);
		if (!ArrayUtils.isEmpty(pks)) {
			for (int i = 0; i < pks.length; i++) {
				MailPeop mailPepo = new MailPeop();
				mailPepo.setMail_id(new Long(pks[i]));
				mailPepo.setRece_id(userInfo.getId());
				mailPepo.setIs_del(0l);
				mailPepo.setIs_rece(0l);
				List<MailPeop> mPepolist = super.getFacade().getMailPeopService().getMailPeopList(mailPepo);
				if (mPepolist != null) {
					for (MailPeop j : mPepolist) {
						MailPeop entity = new MailPeop();
						entity.setMail_id(new Long(pks[i]));
						entity.setRece_id(userInfo.getId());
						entity.setMail_state(4l); // 删除到垃圾箱中
						entity.setId(j.getId());
						super.getFacade().getMailPeopService().modifyMailPeop(entity);
					}
				}
			}
			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	// 收件箱
	public ActionForward cclist(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		setNaviStringToRequestScope(request);

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Keys.SESSION_USERINFO_KEY);

		Pager pager = (Pager) dynaBean.get("pager");

		MailMain entity = new MailMain();
		super.copyProperties(entity, form);
		entity.getMap().put("rece_id", userInfo.getId());
		Long recordCount = getFacade().getMailMainService().getMailMainCount(entity);
		pager.init(Long.valueOf(recordCount), pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<MailMain> entityList = getFacade().getMailMainService().getMailMainPaginatedList(entity);

		request.setAttribute("entityList", entityList);
		dynaBean.set("recordCount", recordCount.toString());

		return new ActionForward("/../view/jsp/manager/admin/MailMain/cclist.jsp");
	}

	// 收件箱删除
	public ActionForward deleteRec(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Keys.SESSION_USERINFO_KEY);
		if (!ArrayUtils.isEmpty(pks)) {
			for (int i = 0; i < pks.length; i++) {
				MailPeop mailPepo = new MailPeop();
				mailPepo.setMail_id(new Long(pks[i]));
				mailPepo.setRece_id(userInfo.getId());
				mailPepo.setIs_del(0l);
				mailPepo.getMap().put("receive_mail", "receive_mail");
				List<MailPeop> mPepolist = super.getFacade().getMailPeopService().getMailPeopList(mailPepo);
				if (mPepolist != null) {
					for (MailPeop j : mPepolist) {
						MailPeop entity = new MailPeop();
						entity.setMail_id(new Long(pks[i]));
						entity.setRece_id(userInfo.getId());
						entity.setMail_state(4l); // 删除到垃圾箱中
						entity.setId(j.getId());
						super.getFacade().getMailPeopService().modifyMailPeop(entity);
					}
				}
			}
			saveMessage(request, "entity.deleted");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("/../view/jsp/manager/admin/MailMain.do?method=cclist");
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	// 收件箱查看
	public ActionForward viewReceive(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		saveToken(request);
		setNaviStringToRequestScope(request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		if (!GenericValidator.isLong(mod_id)) {
			this.saveError(request, "errors.long", new String[] { mod_id });
			return mapping.findForward("list");
		}

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Keys.SESSION_USERINFO_KEY);
		MailMain entity = new MailMain();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getMailMainService().getMailMain(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		// 人员处理
		// 发送人
		MailPeop mainPepo = new MailPeop();
		mainPepo.setMail_id(Long.valueOf(id));
		mainPepo.setIs_rece(0l);
		mainPepo.setIs_del(0l);
		List<MailPeop> mainPepoList = super.getFacade().getMailPeopService().getMailPeopList(mainPepo);
		request.setAttribute("mainPepoList", mainPepoList);

		// 更新该用户下该邮件的状态：从未查看 到 已查看
		MailPeop mailEntity = new MailPeop();
		mailEntity.setMail_id(Long.valueOf(id));
		mailEntity.setIs_rece(1l);
		mailEntity.setRece_id(userInfo.getId());
		mailEntity.setIs_del(0l);
		List<MailPeop> mailEntityList = super.getFacade().getMailPeopService().getMailPeopList(mailEntity);
		if (mailEntityList != null) {
			for (MailPeop tt : mailEntityList) {
				MailPeop pp = new MailPeop();
				pp.setId(tt.getId());
				pp.setMail_state(2l); // 修改状态为已查看
				super.getFacade().getMailPeopService().modifyMailPeop(pp);
			}
		}

		// 收件人
		MailPeop mainPepoRec = new MailPeop();
		mainPepoRec.setMail_id(Long.valueOf(id));
		mainPepoRec.setIs_rece(1l);
		mainPepoRec.getMap().put("isreceive", 1);
		mainPepoRec.setIs_del(0l);
		List<MailPeop> mailPepoRecList = super.getFacade().getMailPeopService().getMailPeopList(mainPepoRec);
		if (mailPepoRecList != null) {
			StringBuffer recids = new StringBuffer();
			StringBuffer recnames = new StringBuffer();
			for (int i = 0; i <= mailPepoRecList.size() - 1; i++) {
				if (i < (mailPepoRecList.size() - 1)) {
					recids.append(mailPepoRecList.get(i).getRece_id()).append(",");
					recnames.append(mailPepoRecList.get(i).getMap().get("user_name").toString()).append(",");
				} else if (i == (mailPepoRecList.size() - 1)) {
					recids.append(mailPepoRecList.get(i).getRece_id());
					if (mailPepoRecList.get(i).getMap().get("user_name").toString() != null) {
						recnames.append(mailPepoRecList.get(i).getMap().get("user_name").toString());
					} else {
						recnames.append("");
					}
				}
			}
			dynaBean.set("recids", recids);
			dynaBean.set("recnames", recnames);
		}
		request.setAttribute("mailPepoRecList", mailPepoRecList);

		// 更新该用户下该邮件的状态：从未查看 到 已查看
		MailPeop mailEntity1 = new MailPeop();
		mailEntity1.setMail_id(Long.valueOf(id));
		mailEntity1.setIs_rece(2l);
		mailEntity1.setRece_id(userInfo.getId());
		mailEntity1.setIs_del(0l);
		List<MailPeop> mailEntity1List = super.getFacade().getMailPeopService().getMailPeopList(mailEntity1);
		if (mailEntity1List != null) {
			for (MailPeop tt : mailEntity1List) {
				MailPeop pp = new MailPeop();
				pp.setId(tt.getId());
				pp.setMail_state(2l); // 修改状态为已查看
				super.getFacade().getMailPeopService().modifyMailPeop(pp);
			}
		}
		// 抄送人
		MailPeop mainPepoCc = new MailPeop();
		mainPepoCc.setMail_id(Long.valueOf(id));
		mainPepoCc.setIs_rece(2l);
		mainPepoCc.getMap().put("isreceive", 1);
		mainPepoCc.setIs_del(0l);
		List<MailPeop> mainPepoCcList = super.getFacade().getMailPeopService().getMailPeopList(mainPepoCc);
		if (mainPepoCcList != null) {
			StringBuffer ccids = new StringBuffer();
			StringBuffer ccnames = new StringBuffer();
			for (int i = 0; i <= mainPepoCcList.size() - 1; i++) {
				if (i < (mailPepoRecList.size() - 1)) {
					ccids.append(mainPepoCcList.get(i).getRece_id()).append(",");
					ccnames.append(mainPepoCcList.get(i).getMap().get("user_name").toString()).append(",");
				} else if (i == (mailPepoRecList.size() - 1)) {
					ccids.append(mainPepoCcList.get(i).getRece_id());
					ccnames.append(mainPepoCcList.get(i).getMap().get("user_name").toString());
				}
			}
			dynaBean.set("ccids", ccids);
			dynaBean.set("ccnames", ccnames);
		}
		request.setAttribute("mainPepoCcList", mainPepoCcList);

		// 附件处理
		MailAttachment attachment = new MailAttachment();
		attachment.setLink_id(entity.getId());
		attachment.setLink_tab("MAIL_MAIN");
		attachment.setIs_del(0l);
		List<MailAttachment> attachmentList = getFacade().getMailAttachmentService().getMailAttachmentList(attachment);
		request.setAttribute("attachmentList", attachmentList);

		// the line below is added for pagination
		entity.setQueryString(super.serialize(request, "id", "method"));
		// end
		super.copyProperties(form, entity);
		dynaBean.set("can_re", 1); // 是否有回复功能 此处为是
		dynaBean.set("mod_id", mod_id);

		return mapping.findForward("view");
	}
}