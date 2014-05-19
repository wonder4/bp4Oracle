package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.SysModule;

public interface SysModuleService {

	Long createSysModule(SysModule t);

	int modifySysModule(SysModule t);

	int removeSysModule(SysModule t);

	SysModule getSysModule(SysModule t);

	List<SysModule> getSysModuleList(SysModule t);

	Long getSysModuleCount(SysModule t);

	List<SysModule> getSysModulePaginatedList(SysModule t);

	/**
	 * @desc 取父节点list
	 */
	List<SysModule> getSysModuleParentList(SysModule t);

	/**
	 * @desc 授权用
	 */
	List<SysModule> getSysModuleListForModPopedom(SysModule t);

}
