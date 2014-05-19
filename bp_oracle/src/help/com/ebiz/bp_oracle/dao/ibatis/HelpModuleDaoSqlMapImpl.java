package com.ebiz.bp_oracle.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.bp_oracle.dao.HelpModuleDao;
import com.ebiz.bp_oracle.domain.HelpModule;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Repository
public class HelpModuleDaoSqlMapImpl extends EntityDaoSqlMapImpl<HelpModule> implements HelpModuleDao {

	public Long selectHelpModuleWithLevelNumber(HelpModule t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectHelpModuleWithLevelNumber", t);
	}

	@SuppressWarnings("unchecked")
	public List<HelpModule> selectHelpModuleParentList(HelpModule t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectHelpModuleParentList", t);
	}

	public HelpModule selectHelpModuleForEqLevel2(HelpModule t) throws DataAccessException {
		return (HelpModule) super.getSqlMapClientTemplate().queryForObject("selectHelpModuleForEqLevel2", t);
	}

}
