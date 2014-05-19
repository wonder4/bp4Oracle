package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.BaseBrandInfo;

public interface BaseBrandInfoService {

	Long createBaseBrandInfo(BaseBrandInfo t);

	int modifyBaseBrandInfo(BaseBrandInfo t);

	int removeBaseBrandInfo(BaseBrandInfo t);

	BaseBrandInfo getBaseBrandInfo(BaseBrandInfo t);

	List<BaseBrandInfo> getBaseBrandInfoList(BaseBrandInfo t);

	Long getBaseBrandInfoCount(BaseBrandInfo t);

	List<BaseBrandInfo> getBaseBrandInfoPaginatedList(BaseBrandInfo t);

}