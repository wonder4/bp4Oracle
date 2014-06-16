package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.MailAttachmentDao;
import com.ebiz.bp_oracle.dao.MailMainDao;
import com.ebiz.bp_oracle.dao.MailPeopDao;
import com.ebiz.bp_oracle.domain.MailAttachment;
import com.ebiz.bp_oracle.domain.MailMain;
import com.ebiz.bp_oracle.domain.MailPeop;
import com.ebiz.bp_oracle.service.MailMainService;

/**
 * Code by the CodeGenerator(Version 2.2)
 * 
 * @author Hui,Gang (mr.huig [at] gmail.com)
 * @datetime 2012-08-28 14:33:00
 */
@Service
public class MailMainServiceImpl implements MailMainService {

	@Resource
	private MailMainDao mailMainDao;

	@Resource
	private MailPeopDao mailPeopDao;

	@Resource
	private MailAttachmentDao mailAttachmentDao;

	public Long createMailMain(MailMain t) {
		Long mmId = this.mailMainDao.insertEntity(t);
		String[] rec_user_id_array = null;
		String[] cc_user_id_array = null;

		String rec_user_ids = (String) t.getMap().get("rec_user_ids"); // 收件人IDs
		String cc_user_ids = (String) t.getMap().get("cc_user_ids"); // 抄送人IDs
		String mail_state = (String) t.getMap().get("mail_state"); // 1：已发送 or :0：暂存
		String current_user = (String) t.getMap().get("current_user"); // 当前用户ID

		// 如果为回复邮件，将该用户下父邮件的状态改为已回复 即更新该用户在MAIL_PEPO表中的父邮件状态
		if (t.getPar_id() != 0) {
			MailPeop huifu = new MailPeop();
			huifu.setMail_id(t.getPar_id());
			huifu.setRece_id(Long.valueOf(current_user));
			huifu.getMap().put("isreceive", 0);
			huifu.setIs_del(0l);
			List<MailPeop> huifuList = this.mailPeopDao.selectEntityList(huifu);
			if (huifuList != null) {
				for (MailPeop mpo : huifuList) {
					MailPeop opep = new MailPeop();
					opep.setId(mpo.getId());
					opep.setMail_state(3l);
					this.mailPeopDao.updateEntity(opep);
				}
			}
		}
		// 发送处理
		MailPeop mailPepoSend = new MailPeop();
		mailPepoSend.setMail_id(mmId);
		mailPepoSend.setIs_del(0l);
		mailPepoSend.setIs_rece(0l); // 接收方式:发送
		if (mail_state != null) {
			if (Integer.valueOf(mail_state) == 1) {
				mailPepoSend.setMail_state(1l);// 针对发件人信息状态1 表示 已发送
				mailPepoSend.setRe_state(1l); // 恢复状态位 在1：发件箱中
			} else if (Integer.valueOf(mail_state) == 0) {
				mailPepoSend.setMail_state(-1l);// 针对发件人信息状态-1 表示暂存
				mailPepoSend.setRe_state(-1l); // 恢复状态位 -1：草稿箱中
			}

		}
		mailPepoSend.setRece_id(t.getSend_user_id());
		this.mailPeopDao.insertEntity(mailPepoSend);

		// 收件处理
		MailPeop mailPepoRece;
		if (StringUtils.isNotBlank(rec_user_ids)) { // 收件人,增加收件邮件信息
			rec_user_id_array = rec_user_ids.split(",");
			for (int i = 0; i < rec_user_id_array.length; i++) {
				mailPepoRece = new MailPeop();
				mailPepoRece.setMail_id(mmId);
				mailPepoRece.setIs_del(0l);
				mailPepoRece.setIs_rece(1l); // 接收方式:收件
				mailPepoRece.setMail_state(0l); // 针对收件人信息状态0 表示 已接收未查看
				mailPepoRece.setRe_state(0l); // 恢复状态位 在收件箱中 并且为已接收未查看
				mailPepoRece.setRece_id(Long.valueOf(rec_user_id_array[i])); // 收件人id
				// 保存到 内部邮件人员表
				this.mailPeopDao.insertEntity(mailPepoRece);
			}
		}

		// 抄送处理
		MailPeop mailPepo;
		if (StringUtils.isNotBlank(cc_user_ids)) { // 抄送人,增加抄送邮件信息
			cc_user_id_array = cc_user_ids.split(",");
			for (int i = 0; i < cc_user_id_array.length; i++) {
				mailPepo = new MailPeop();
				mailPepo.setMail_id(mmId);
				mailPepo.setIs_rece(2l); // 接收方式:抄送
				if (mail_state != null) {
					mailPepo.setMail_state(0l); // 针对抄送人信息状态0 表示 已接收未查看
				}
				mailPepo.setRe_state(0l); // 恢复状态位 在收件箱中 并且为已接收未查看
				mailPepo.setIs_del(0l);
				mailPepo.setRece_id(Long.valueOf(cc_user_id_array[i])); // 接收人id
				// 保存到 内部邮件人员表
				this.mailPeopDao.insertEntity(mailPepo);

			}
		}

		// 附件
		List<MailAttachment> attachmentList = t.getAttachmentList();
		if (null != attachmentList) {
			for (MailAttachment attachment : attachmentList) {
				attachment.setLink_id(mmId); // 附件id为对应的 站内信息表的id
				attachment.setLink_tab("MAIL_MAIN"); // 关联表：站内信息表
				attachment.setIs_del(0l);
				this.mailAttachmentDao.insertEntity(attachment);
			}
		}

		return mmId;
	}

	public Long createMailMainForImportant(MailMain t) {
		Long mmId = this.mailMainDao.insertEntity(t);
		String[] rec_user_id_array = null;

		String rec_user_ids = (String) t.getMap().get("rec_user_ids"); // 收件人IDs

		// 发送处理
		MailPeop mailPepoSend = new MailPeop();
		mailPepoSend.setMail_id(mmId);
		mailPepoSend.setIs_del(0l);
		mailPepoSend.setIs_rece(0l); // 接收方式:发送
		mailPepoSend.setMail_state(1l);// 针对发件人信息状态1 表示 已发送
		mailPepoSend.setRe_state(1l); // 恢复状态位 在1：发件箱中

		mailPepoSend.setRece_id(t.getSend_user_id());
		this.mailPeopDao.insertEntity(mailPepoSend);

		// 收件处理
		MailPeop mailPepoRece;
		if (StringUtils.isNotBlank(rec_user_ids)) { // 收件人,增加收件邮件信息
			rec_user_id_array = rec_user_ids.split(",");
			for (int i = 0; i < rec_user_id_array.length; i++) {
				mailPepoRece = new MailPeop();
				mailPepoRece.setMail_id(mmId);
				mailPepoRece.setIs_del(0l);
				mailPepoRece.setIs_rece(1l); // 接收方式:收件
				mailPepoRece.setMail_state(0l); // 针对收件人信息状态0 表示 已接收未查看
				mailPepoRece.setRe_state(0l); // 恢复状态位 在收件箱中 并且为已接收未查看
				mailPepoRece.setRece_id(Long.valueOf(rec_user_id_array[i])); // 收件人id

				// 保存到 内部邮件人员表
				this.mailPeopDao.insertEntity(mailPepoRece);
			}
		}

		// 附件
		List<MailAttachment> attachmentList = t.getAttachmentList();
		if (null != attachmentList) {
			for (MailAttachment attachment : attachmentList) {
				attachment.setLink_id(mmId); // 附件id为对应的 站内信息表的id
				attachment.setLink_tab("MAIL_MAIN"); // 关联表：站内信息表
				attachment.setIs_del(0l);
				this.mailAttachmentDao.insertEntity(attachment);
			}
		}

		return mmId;
	}

	public MailMain getMailMain(MailMain t) {
		return this.mailMainDao.selectEntity(t);
	}

	public Long getMailMainCount(MailMain t) {
		return this.mailMainDao.selectEntityCount(t);
	}

	public List<MailMain> getMailMainList(MailMain t) {
		return this.mailMainDao.selectEntityList(t);
	}

	public int modifyMailMain(MailMain t) {
		return this.mailMainDao.updateEntity(t);
	}

	public int removeMailMain(MailMain t) {
		return this.mailMainDao.deleteEntity(t);
	}

	public List<MailMain> getMailMainPaginatedList(MailMain t) {
		return this.mailMainDao.selectEntityPaginatedList(t);
	}

	// 发件箱
	public Long getMailMainCountForSend(MailMain t) {
		return this.mailMainDao.selectEntityCountForSend(t);
	}

	public List<MailMain> getMailMainPaginatedListForSend(MailMain t) {
		return this.mailMainDao.selectEntityPaginatedListForSend(t);
	}
}