package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.PdInfoCustomFieldContentDao;
import com.ebiz.bp_oracle.domain.PdInfoCustomFieldContent;
import com.ebiz.bp_oracle.service.PdInfoCustomFieldContentService;

@Service
public class PdInfoCustomFieldContentServiceImpl implements PdInfoCustomFieldContentService {

	@Resource
	private PdInfoCustomFieldContentDao pdInfoCustomFieldContentDao;

	public Long createPdInfoCustomFieldContent(PdInfoCustomFieldContent t) {
		return this.pdInfoCustomFieldContentDao.insertEntity(t);
	}

	public PdInfoCustomFieldContent getPdInfoCustomFieldContent(PdInfoCustomFieldContent t) {
		return this.pdInfoCustomFieldContentDao.selectEntity(t);
	}

	public Long getPdInfoCustomFieldContentCount(PdInfoCustomFieldContent t) {
		return this.pdInfoCustomFieldContentDao.selectEntityCount(t);
	}

	public List<PdInfoCustomFieldContent> getPdInfoCustomFieldContentList(PdInfoCustomFieldContent t) {
		return this.pdInfoCustomFieldContentDao.selectEntityList(t);
	}

	public int modifyPdInfoCustomFieldContent(PdInfoCustomFieldContent t) {
		return this.pdInfoCustomFieldContentDao.updateEntity(t);
	}

	public int removePdInfoCustomFieldContent(PdInfoCustomFieldContent t) {
		return this.pdInfoCustomFieldContentDao.deleteEntity(t);
	}

	public List<PdInfoCustomFieldContent> getPdInfoCustomFieldContentPaginatedList(PdInfoCustomFieldContent t) {
		return this.pdInfoCustomFieldContentDao.selectEntityPaginatedList(t);
	}

}
