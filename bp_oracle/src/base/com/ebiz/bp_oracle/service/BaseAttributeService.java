package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.BaseAttribute;

public interface BaseAttributeService {

	Long createBaseAttribute(BaseAttribute t);

	int modifyBaseAttribute(BaseAttribute t);

	int removeBaseAttribute(BaseAttribute t);

	BaseAttribute getBaseAttribute(BaseAttribute t);

	List<BaseAttribute> getBaseAttributeList(BaseAttribute t);

	Long getBaseAttributeCount(BaseAttribute t);

	List<BaseAttribute> getBaseAttributePaginatedList(BaseAttribute t);

}