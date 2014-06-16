package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.MailMain;

/**
 * @author Hui,Gang
 * @version 2013-12-11 下午04:54:47
 */
public interface MailMainService {

	Long createMailMain(MailMain t);

	int modifyMailMain(MailMain t);

	int removeMailMain(MailMain t);

	MailMain getMailMain(MailMain t);

	List<MailMain> getMailMainList(MailMain t);

	Long getMailMainCount(MailMain t);

	List<MailMain> getMailMainPaginatedList(MailMain t);

	// 发件箱
	Long getMailMainCountForSend(MailMain t);

	List<MailMain> getMailMainPaginatedListForSend(MailMain t);

}