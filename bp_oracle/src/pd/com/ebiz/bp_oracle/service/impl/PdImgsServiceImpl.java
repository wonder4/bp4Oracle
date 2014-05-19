package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.PdImgsDao;
import com.ebiz.bp_oracle.domain.PdImgs;
import com.ebiz.bp_oracle.service.PdImgsService;

@Service
public class PdImgsServiceImpl implements PdImgsService {

	@Resource
	private PdImgsDao pdImgsDao;

	public Long createPdImgs(PdImgs t) {
		return this.pdImgsDao.insertEntity(t);
	}

	public PdImgs getPdImgs(PdImgs t) {
		return this.pdImgsDao.selectEntity(t);
	}

	public Long getPdImgsCount(PdImgs t) {
		return this.pdImgsDao.selectEntityCount(t);
	}

	public List<PdImgs> getPdImgsList(PdImgs t) {
		return this.pdImgsDao.selectEntityList(t);
	}

	public int modifyPdImgs(PdImgs t) {
		return this.pdImgsDao.updateEntity(t);
	}

	public int removePdImgs(PdImgs t) {
		return this.pdImgsDao.deleteEntity(t);
	}

	public List<PdImgs> getPdImgsPaginatedList(PdImgs t) {
		return this.pdImgsDao.selectEntityPaginatedList(t);
	}

}
