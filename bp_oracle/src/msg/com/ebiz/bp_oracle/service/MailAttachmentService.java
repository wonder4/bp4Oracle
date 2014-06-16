package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.MailAttachment;

/**
 * Code by the CodeGenerator(Version 2.2)
 * 
 * @author Hui,Gang (mr.huig [at] gmail.com)
 * @datetime 2012-08-28 14:33:00
 */
public interface MailAttachmentService {

	Long createMailAttachment(MailAttachment t);

	int modifyMailAttachment(MailAttachment t);

	int removeMailAttachment(MailAttachment t);

	MailAttachment getMailAttachment(MailAttachment t);

	List<MailAttachment> getMailAttachmentList(MailAttachment t);

	Long getMailAttachmentCount(MailAttachment t);

	List<MailAttachment> getMailAttachmentPaginatedList(MailAttachment t);

}