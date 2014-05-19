package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.PdImgs;

public interface PdImgsService {

	Long createPdImgs(PdImgs t);

	int modifyPdImgs(PdImgs t);

	int removePdImgs(PdImgs t);

	PdImgs getPdImgs(PdImgs t);

	List<PdImgs> getPdImgsList(PdImgs t);

	Long getPdImgsCount(PdImgs t);

	List<PdImgs> getPdImgsPaginatedList(PdImgs t);

}