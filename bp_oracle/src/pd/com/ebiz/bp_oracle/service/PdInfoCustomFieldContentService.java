package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.PdInfoCustomFieldContent;

public interface PdInfoCustomFieldContentService {

	Long createPdInfoCustomFieldContent(PdInfoCustomFieldContent t);

	int modifyPdInfoCustomFieldContent(PdInfoCustomFieldContent t);

	int removePdInfoCustomFieldContent(PdInfoCustomFieldContent t);

	PdInfoCustomFieldContent getPdInfoCustomFieldContent(PdInfoCustomFieldContent t);

	List<PdInfoCustomFieldContent> getPdInfoCustomFieldContentList(PdInfoCustomFieldContent t);

	Long getPdInfoCustomFieldContentCount(PdInfoCustomFieldContent t);

	List<PdInfoCustomFieldContent> getPdInfoCustomFieldContentPaginatedList(PdInfoCustomFieldContent t);

}