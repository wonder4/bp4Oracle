package com.ebiz.bp_oracle.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.bp_oracle.dao.MailMainDao;
import com.ebiz.bp_oracle.domain.MailMain;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Code by the CodeGenerator(Version 2.2)
 * 
 * @author Hui,Gang (mr.huig [at] gmail.com)
 * @datetime 2012-08-28 14:33:00
 */
@Repository
public class MailMainDaoSqlMapImpl extends EntityDaoSqlMapImpl<MailMain> implements MailMainDao {
	// 发件箱
	public Long selectEntityCountForSend(MailMain t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectMailMainCountForSend", t);
	}

	@SuppressWarnings("unchecked")
	public List<MailMain> selectEntityPaginatedListForSend(MailMain t) {
		return super.getSqlMapClientTemplate().queryForList("selectMailMainPaginatedListForSend", t);
	}
}