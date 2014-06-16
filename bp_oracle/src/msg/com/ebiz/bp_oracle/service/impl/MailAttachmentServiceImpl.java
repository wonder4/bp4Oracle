package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.MailAttachmentDao;
import com.ebiz.bp_oracle.domain.MailAttachment;
import com.ebiz.bp_oracle.service.MailAttachmentService;

/**
 * Code by the CodeGenerator(Version 2.2)
 * 
 * @author Hui,Gang (mr.huig [at] gmail.com)
 * @datetime 2012-08-28 14:33:00
 */
@Service
public class MailAttachmentServiceImpl implements MailAttachmentService {

	@Resource
	private MailAttachmentDao mailAttachmentDao;

	public Long createMailAttachment(MailAttachment t) {
		return this.mailAttachmentDao.insertEntity(t);
	}

	public MailAttachment getMailAttachment(MailAttachment t) {
		return this.mailAttachmentDao.selectEntity(t);
	}

	public Long getMailAttachmentCount(MailAttachment t) {
		return this.mailAttachmentDao.selectEntityCount(t);
	}

	public List<MailAttachment> getMailAttachmentList(MailAttachment t) {
		return this.mailAttachmentDao.selectEntityList(t);
	}

	public int modifyMailAttachment(MailAttachment t) {
		return this.mailAttachmentDao.updateEntity(t);
	}

	public int removeMailAttachment(MailAttachment t) {
		return this.mailAttachmentDao.deleteEntity(t);
	}

	public List<MailAttachment> getMailAttachmentPaginatedList(MailAttachment t) {
		return this.mailAttachmentDao.selectEntityPaginatedList(t);
	}

}