package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.BaseProvinceDao;
import com.ebiz.bp_oracle.domain.BaseProvince;
import com.ebiz.bp_oracle.service.BaseProvinceService;

@Service
public class BaseProvinceServiceImpl implements BaseProvinceService {

	@Resource
	private BaseProvinceDao baseProvinceDao;

	public Long createBaseProvince(BaseProvince t) {
		return this.baseProvinceDao.insertEntity(t);
	}

	public int modifyBaseProvince(BaseProvince t) {
		return this.baseProvinceDao.updateEntity(t);
	}

	public int removeBaseProvince(BaseProvince t) {
		return this.baseProvinceDao.deleteEntity(t);
	}

	public BaseProvince getBaseProvince(BaseProvince t) {
		return this.baseProvinceDao.selectEntity(t);
	}

	public Long getBaseProvinceCount(BaseProvince t) {
		return this.baseProvinceDao.selectEntityCount(t);
	}

	public List<BaseProvince> getBaseProvinceList(BaseProvince t) {
		return this.baseProvinceDao.selectEntityList(t);
	}

	public List<BaseProvince> getBaseProvincePaginatedList(BaseProvince t) {
		return this.baseProvinceDao.selectEntityPaginatedList(t);
	}

	public List<BaseProvince> getBaseProvinceParentList(BaseProvince baseProvince) {
		return this.baseProvinceDao.selectBaseProvinceParentList(baseProvince);
	}
}
