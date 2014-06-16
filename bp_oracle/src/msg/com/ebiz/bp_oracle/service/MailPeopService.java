package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.MailPeop;

/**
 * Code by the CodeGenerator(Version 2.2)
 * 
 * @author Hui,Gang (mr.huig [at] gmail.com)
 * @datetime 2012-08-28 14:33:00
 */
public interface MailPeopService {

	Long createMailPeop(MailPeop t);

	int modifyMailPeop(MailPeop t);

	int removeMailPeop(MailPeop t);

	MailPeop getMailPeop(MailPeop t);

	List<MailPeop> getMailPeopList(MailPeop t);

	Long getMailPeopCount(MailPeop t);

	List<MailPeop> getMailPeopPaginatedList(MailPeop t);

}