package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.PdInfoCustomAttrContentDao;
import com.ebiz.bp_oracle.domain.PdInfoCustomAttrContent;
import com.ebiz.bp_oracle.service.PdInfoCustomAttrContentService;

@Service
public class PdInfoCustomAttrContentServiceImpl implements PdInfoCustomAttrContentService {

	@Resource
	private PdInfoCustomAttrContentDao pdInfoCustomAttrContentDao;

	public Long createPdInfoCustomAttrContent(PdInfoCustomAttrContent t) {
		return this.pdInfoCustomAttrContentDao.insertEntity(t);
	}

	public PdInfoCustomAttrContent getPdInfoCustomAttrContent(PdInfoCustomAttrContent t) {
		return this.pdInfoCustomAttrContentDao.selectEntity(t);
	}

	public Long getPdInfoCustomAttrContentCount(PdInfoCustomAttrContent t) {
		return this.pdInfoCustomAttrContentDao.selectEntityCount(t);
	}

	public List<PdInfoCustomAttrContent> getPdInfoCustomAttrContentList(PdInfoCustomAttrContent t) {
		return this.pdInfoCustomAttrContentDao.selectEntityList(t);
	}

	public int modifyPdInfoCustomAttrContent(PdInfoCustomAttrContent t) {
		return this.pdInfoCustomAttrContentDao.updateEntity(t);
	}

	public int removePdInfoCustomAttrContent(PdInfoCustomAttrContent t) {
		return this.pdInfoCustomAttrContentDao.deleteEntity(t);
	}

	public List<PdInfoCustomAttrContent> getPdInfoCustomAttrContentPaginatedList(PdInfoCustomAttrContent t) {
		return this.pdInfoCustomAttrContentDao.selectEntityPaginatedList(t);
	}

}
