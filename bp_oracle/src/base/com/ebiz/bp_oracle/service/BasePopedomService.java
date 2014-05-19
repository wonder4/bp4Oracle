package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.BasePopedom;

public interface BasePopedomService {

	Long createBasePopedom(BasePopedom t);

	int modifyBasePopedom(BasePopedom t);

	int removeBasePopedom(BasePopedom t);

	BasePopedom getBasePopedom(BasePopedom t);

	List<BasePopedom> getBasePopedomList(BasePopedom t);

	Long getBasePopedomCount(BasePopedom t);

	List<BasePopedom> getBasePopedomPaginatedList(BasePopedom t);

}