package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.SysModuleDao;
import com.ebiz.bp_oracle.domain.SysModule;
import com.ebiz.bp_oracle.service.SysModuleService;

@Service
public class SysModuleServiceImpl implements SysModuleService {

	@Resource
	private SysModuleDao sysModuleDao;

	public Long createSysModule(SysModule t) {
		return this.sysModuleDao.insertEntity(t);
	}

	public int modifySysModule(SysModule t) {
		return this.sysModuleDao.updateEntity(t);
	}

	public int removeSysModule(SysModule t) {
		return this.sysModuleDao.deleteEntity(t);
	}

	public SysModule getSysModule(SysModule t) {
		return this.sysModuleDao.selectEntity(t);
	}

	public Long getSysModuleCount(SysModule t) {
		return this.sysModuleDao.selectEntityCount(t);
	}

	public List<SysModule> getSysModuleList(SysModule t) {
		return this.sysModuleDao.selectEntityList(t);
	}

	public List<SysModule> getSysModulePaginatedList(SysModule t) {
		return this.sysModuleDao.selectEntityPaginatedList(t);
	}

	/**
	 * @desc 取父节点list
	 */
	public List<SysModule> getSysModuleParentList(SysModule t) {
		return sysModuleDao.selectSysModuleParentList(t);
	}

	/**
	 * @desc 授权用
	 */
	public List<SysModule> getSysModuleListForModPopedom(SysModule t) {
		return sysModuleDao.selectSysModuleListForModPopedom(t);
	}
}
