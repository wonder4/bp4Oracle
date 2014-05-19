package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.BasePopedomDao;
import com.ebiz.bp_oracle.domain.BasePopedom;
import com.ebiz.bp_oracle.service.BasePopedomService;

@Service
public class BasePopedomServiceImpl implements BasePopedomService {

	@Resource
	private BasePopedomDao basePopedomDao;

	public Long createBasePopedom(BasePopedom t) {
		return this.basePopedomDao.insertEntity(t);
	}

	public BasePopedom getBasePopedom(BasePopedom t) {
		return this.basePopedomDao.selectEntity(t);
	}

	public Long getBasePopedomCount(BasePopedom t) {
		return this.basePopedomDao.selectEntityCount(t);
	}

	public List<BasePopedom> getBasePopedomList(BasePopedom t) {
		return this.basePopedomDao.selectEntityList(t);
	}

	public int modifyBasePopedom(BasePopedom t) {
		return this.basePopedomDao.updateEntity(t);
	}

	public int removeBasePopedom(BasePopedom t) {
		return this.basePopedomDao.deleteEntity(t);
	}

	public List<BasePopedom> getBasePopedomPaginatedList(BasePopedom t) {
		return this.basePopedomDao.selectEntityPaginatedList(t);
	}

}
