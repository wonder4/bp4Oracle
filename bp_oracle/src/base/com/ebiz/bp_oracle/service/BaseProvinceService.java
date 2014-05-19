package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.BaseProvince;

public interface BaseProvinceService {

	Long createBaseProvince(BaseProvince t);

	int modifyBaseProvince(BaseProvince t);

	int removeBaseProvince(BaseProvince t);

	BaseProvince getBaseProvince(BaseProvince t);

	List<BaseProvince> getBaseProvinceList(BaseProvince t);

	Long getBaseProvinceCount(BaseProvince t);

	List<BaseProvince> getBaseProvincePaginatedList(BaseProvince t);

	List<BaseProvince> getBaseProvinceParentList(BaseProvince BaseProvince);
}
