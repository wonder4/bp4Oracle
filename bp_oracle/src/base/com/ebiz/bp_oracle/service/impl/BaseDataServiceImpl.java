package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.BaseDataDao;
import com.ebiz.bp_oracle.domain.BaseData;
import com.ebiz.bp_oracle.service.BaseDataService;

@Service
public class BaseDataServiceImpl implements BaseDataService {

	@Resource
	private BaseDataDao baseDataDao;

	public Long createBaseData(BaseData t) {
		return this.baseDataDao.insertEntity(t);
	}

	public int modifyBaseData(BaseData t) {
		return this.baseDataDao.updateEntity(t);
	}

	public int removeBaseData(BaseData t) {
		return this.baseDataDao.deleteEntity(t);
	}

	public BaseData getBaseData(BaseData t) {
		return this.baseDataDao.selectEntity(t);
	}

	public Long getBaseDataCount(BaseData t) {
		return this.baseDataDao.selectEntityCount(t);
	}

	public List<BaseData> getBaseDataList(BaseData t) {
		return this.baseDataDao.selectEntityList(t);
	}

	public List<BaseData> getBaseDataPaginatedList(BaseData t) {
		return this.baseDataDao.selectEntityPaginatedList(t);
	}

}
