package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.BaseClass;

public interface BaseClassService {

	Long createBaseClass(BaseClass t);

	int modifyBaseClass(BaseClass t);

	int removeBaseClass(BaseClass t);

	BaseClass getBaseClass(BaseClass t);

	List<BaseClass> getBaseClassList(BaseClass t);

	Long getBaseClassCount(BaseClass t);

	List<BaseClass> getBaseClassPaginatedList(BaseClass t);

	public List<BaseClass> getBaseClassToParByLevelList(BaseClass t);

	// 查询子类别
	public List<BaseClass> getBaseClassSonList(BaseClass t);

}