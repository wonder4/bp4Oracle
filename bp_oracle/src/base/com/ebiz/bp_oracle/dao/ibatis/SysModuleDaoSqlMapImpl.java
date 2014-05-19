package com.ebiz.bp_oracle.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.SysModuleDao;
import com.ebiz.bp_oracle.domain.SysModule;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class SysModuleDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysModule> implements SysModuleDao {
	/**
	 * @desc 取父节点list
	 */
	@SuppressWarnings("unchecked")
	public List<SysModule> selectSysModuleParentList(SysModule sysModule) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectSysModuleParentList", sysModule);
	}

	/**
	 * @desc 授权用
	 */
	@SuppressWarnings("unchecked")
	public List<SysModule> selectSysModuleListForModPopedom(SysModule sysModule) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectSysModuleListForModPopedom", sysModule);
	}
}
