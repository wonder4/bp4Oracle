package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.BasePdClazz;

public interface BasePdClazzService {

	Long createBasePdClazz(BasePdClazz t);

	int modifyBasePdClazz(BasePdClazz t);

	int removeBasePdClazz(BasePdClazz t);

	BasePdClazz getBasePdClazz(BasePdClazz t);

	List<BasePdClazz> getBasePdClazzList(BasePdClazz t);

	Long getBasePdClazzCount(BasePdClazz t);

	List<BasePdClazz> getBasePdClazzPaginatedList(BasePdClazz t);

	public List<BasePdClazz> getBasePdClazzByKeyWordList(BasePdClazz t);

}