package com.ebiz.bp_oracle.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.bp_oracle.domain.SysModule;
import com.ebiz.ssi.dao.EntityDao;

public interface SysModuleDao extends EntityDao<SysModule> {
	/**
	 * @desc 取父节点list
	 */
	List<SysModule> selectSysModuleParentList(SysModule sysModule) throws DataAccessException;

	/**
	 * @desc 授权用
	 */
	List<SysModule> selectSysModuleListForModPopedom(SysModule sysModule) throws DataAccessException;
}
