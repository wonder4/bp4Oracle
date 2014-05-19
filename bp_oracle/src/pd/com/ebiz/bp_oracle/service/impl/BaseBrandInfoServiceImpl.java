package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.BaseBrandInfoDao;
import com.ebiz.bp_oracle.domain.BaseBrandInfo;
import com.ebiz.bp_oracle.service.BaseBrandInfoService;

@Service
public class BaseBrandInfoServiceImpl implements BaseBrandInfoService {

	@Resource
	private BaseBrandInfoDao baseBrandInfoDao;

	public Long createBaseBrandInfo(BaseBrandInfo t) {
		return this.baseBrandInfoDao.insertEntity(t);
	}

	public BaseBrandInfo getBaseBrandInfo(BaseBrandInfo t) {
		return this.baseBrandInfoDao.selectEntity(t);
	}

	public Long getBaseBrandInfoCount(BaseBrandInfo t) {
		return this.baseBrandInfoDao.selectEntityCount(t);
	}

	public List<BaseBrandInfo> getBaseBrandInfoList(BaseBrandInfo t) {
		return this.baseBrandInfoDao.selectEntityList(t);
	}

	public int modifyBaseBrandInfo(BaseBrandInfo t) {
		return this.baseBrandInfoDao.updateEntity(t);
	}

	public int removeBaseBrandInfo(BaseBrandInfo t) {
		return this.baseBrandInfoDao.deleteEntity(t);
	}

	public List<BaseBrandInfo> getBaseBrandInfoPaginatedList(BaseBrandInfo t) {
		return this.baseBrandInfoDao.selectEntityPaginatedList(t);
	}

}
