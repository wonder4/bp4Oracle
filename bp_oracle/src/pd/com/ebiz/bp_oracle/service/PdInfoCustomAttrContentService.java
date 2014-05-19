package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.PdInfoCustomAttrContent;

public interface PdInfoCustomAttrContentService {

	Long createPdInfoCustomAttrContent(PdInfoCustomAttrContent t);

	int modifyPdInfoCustomAttrContent(PdInfoCustomAttrContent t);

	int removePdInfoCustomAttrContent(PdInfoCustomAttrContent t);

	PdInfoCustomAttrContent getPdInfoCustomAttrContent(PdInfoCustomAttrContent t);

	List<PdInfoCustomAttrContent> getPdInfoCustomAttrContentList(PdInfoCustomAttrContent t);

	Long getPdInfoCustomAttrContentCount(PdInfoCustomAttrContent t);

	List<PdInfoCustomAttrContent> getPdInfoCustomAttrContentPaginatedList(PdInfoCustomAttrContent t);

}