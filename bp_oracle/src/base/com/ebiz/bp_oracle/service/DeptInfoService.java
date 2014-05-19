package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.DeptInfo;

public interface DeptInfoService {

	Long createDeptInfo(DeptInfo t);

	int modifyDeptInfo(DeptInfo t);

	int removeDeptInfo(DeptInfo t);

	DeptInfo getDeptInfo(DeptInfo t);

	List<DeptInfo> getDeptInfoList(DeptInfo t);

	Long getDeptInfoCount(DeptInfo t);

	List<DeptInfo> getDeptInfoPaginatedList(DeptInfo t);

	Long getDeptInfoWithLevelNumber(DeptInfo t);

	List<DeptInfo> getDeptInfoParentList(DeptInfo t);
}