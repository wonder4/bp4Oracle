package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.BaseClassLinkAttribute;

public interface BaseClassLinkAttributeService {

	Long createBaseClassLinkAttribute(BaseClassLinkAttribute t);

	int modifyBaseClassLinkAttribute(BaseClassLinkAttribute t);

	int removeBaseClassLinkAttribute(BaseClassLinkAttribute t);

	BaseClassLinkAttribute getBaseClassLinkAttribute(BaseClassLinkAttribute t);

	List<BaseClassLinkAttribute> getBaseClassLinkAttributeList(BaseClassLinkAttribute t);

	Long getBaseClassLinkAttributeCount(BaseClassLinkAttribute t);

	List<BaseClassLinkAttribute> getBaseClassLinkAttributePaginatedList(BaseClassLinkAttribute t);

}