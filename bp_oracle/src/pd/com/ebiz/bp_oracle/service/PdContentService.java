package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.PdContent;

public interface PdContentService {

	Long createPdContent(PdContent t);

	int modifyPdContent(PdContent t);

	int removePdContent(PdContent t);

	PdContent getPdContent(PdContent t);

	List<PdContent> getPdContentList(PdContent t);

	Long getPdContentCount(PdContent t);

	List<PdContent> getPdContentPaginatedList(PdContent t);

}