package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.BaseAttributeSonDao;
import com.ebiz.bp_oracle.domain.BaseAttributeSon;
import com.ebiz.bp_oracle.service.BaseAttributeSonService;

@Service
public class BaseAttributeSonServiceImpl implements BaseAttributeSonService {

	@Resource
	private BaseAttributeSonDao baseAttributeSonDao;

	public Long createBaseAttributeSon(BaseAttributeSon t) {
		return this.baseAttributeSonDao.insertEntity(t);
	}

	public BaseAttributeSon getBaseAttributeSon(BaseAttributeSon t) {
		return this.baseAttributeSonDao.selectEntity(t);
	}

	public Long getBaseAttributeSonCount(BaseAttributeSon t) {
		return this.baseAttributeSonDao.selectEntityCount(t);
	}

	public List<BaseAttributeSon> getBaseAttributeSonList(BaseAttributeSon t) {
		return this.baseAttributeSonDao.selectEntityList(t);
	}

	public int modifyBaseAttributeSon(BaseAttributeSon t) {
		return this.baseAttributeSonDao.updateEntity(t);
	}

	public int removeBaseAttributeSon(BaseAttributeSon t) {
		return this.baseAttributeSonDao.deleteEntity(t);
	}

	public List<BaseAttributeSon> getBaseAttributeSonPaginatedList(BaseAttributeSon t) {
		return this.baseAttributeSonDao.selectEntityPaginatedList(t);
	}

}
