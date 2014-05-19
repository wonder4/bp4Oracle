package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.PdContentDao;
import com.ebiz.bp_oracle.domain.PdContent;
import com.ebiz.bp_oracle.service.PdContentService;

@Service
public class PdContentServiceImpl implements PdContentService {

	@Resource
	private PdContentDao pdContentDao;

	public Long createPdContent(PdContent t) {
		return this.pdContentDao.insertEntity(t);
	}

	public PdContent getPdContent(PdContent t) {
		return this.pdContentDao.selectEntity(t);
	}

	public Long getPdContentCount(PdContent t) {
		return this.pdContentDao.selectEntityCount(t);
	}

	public List<PdContent> getPdContentList(PdContent t) {
		return this.pdContentDao.selectEntityList(t);
	}

	public int modifyPdContent(PdContent t) {
		return this.pdContentDao.updateEntity(t);
	}

	public int removePdContent(PdContent t) {
		return this.pdContentDao.deleteEntity(t);
	}

	public List<PdContent> getPdContentPaginatedList(PdContent t) {
		return this.pdContentDao.selectEntityPaginatedList(t);
	}

}
