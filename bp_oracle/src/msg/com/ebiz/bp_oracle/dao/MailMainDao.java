package com.ebiz.bp_oracle.dao;

import java.util.List;

import com.ebiz.bp_oracle.domain.MailMain;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Code by the CodeGenerator(Version 2.2)
 * 
 * @author Hui,Gang (mr.huig [at] gmail.com)
 * @datetime 2012-08-28 14:33:00
 */
public interface MailMainDao extends EntityDao<MailMain> {
	// 发件箱
	Long selectEntityCountForSend(MailMain t);

	List<MailMain> selectEntityPaginatedListForSend(MailMain t);
}