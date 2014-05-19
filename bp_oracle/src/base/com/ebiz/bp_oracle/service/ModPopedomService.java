package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.ModPopedom;

public interface ModPopedomService {

	Long createModPopedom(ModPopedom t);

	int modifyModPopedom(ModPopedom t);

	int removeModPopedom(ModPopedom t);

	ModPopedom getModPopedom(ModPopedom t);

	List<ModPopedom> getModPopedomList(ModPopedom t);

	Long getModPopedomCount(ModPopedom t);

	List<ModPopedom> getModPopedomPaginatedList(ModPopedom t);

}
