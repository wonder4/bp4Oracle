package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.BaseData;

public interface BaseDataService {

	Long createBaseData(BaseData t);

	int modifyBaseData(BaseData t);

	int removeBaseData(BaseData t);

	BaseData getBaseData(BaseData t);

	List<BaseData> getBaseDataList(BaseData t);

	Long getBaseDataCount(BaseData t);

	List<BaseData> getBaseDataPaginatedList(BaseData t);

}
