package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.SysSetting;

public interface SysSettingService {

	Long createSysSetting(SysSetting t);

	int modifySysSetting(SysSetting t);

	int removeSysSetting(SysSetting t);

	SysSetting getSysSetting(SysSetting t);

	List<SysSetting> getSysSettingList(SysSetting t);

	Long getSysSettingCount(SysSetting t);

	List<SysSetting> getSysSettingPaginatedList(SysSetting t);

}
