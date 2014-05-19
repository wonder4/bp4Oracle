package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.BasePdClazzDao;
import com.ebiz.bp_oracle.domain.BasePdClazz;
import com.ebiz.bp_oracle.service.BasePdClazzService;

@Service
public class BasePdClazzServiceImpl implements BasePdClazzService {

	@Resource
	private BasePdClazzDao basePdClazzDao;

	public Long createBasePdClazz(BasePdClazz t) {
		return this.basePdClazzDao.insertEntity(t);
	}

	public BasePdClazz getBasePdClazz(BasePdClazz t) {
		return this.basePdClazzDao.selectEntity(t);
	}

	public Long getBasePdClazzCount(BasePdClazz t) {
		return this.basePdClazzDao.selectEntityCount(t);
	}

	public List<BasePdClazz> getBasePdClazzList(BasePdClazz t) {
		return this.basePdClazzDao.selectEntityList(t);
	}

	public int modifyBasePdClazz(BasePdClazz t) {
		return this.basePdClazzDao.updateEntity(t);
	}

	public int removeBasePdClazz(BasePdClazz t) {
		return this.basePdClazzDao.deleteEntity(t);
	}

	public List<BasePdClazz> getBasePdClazzPaginatedList(BasePdClazz t) {
		return this.basePdClazzDao.selectEntityPaginatedList(t);
	}

	/**
	 * @desc 根据 产品 关键字搜索的所有分类
	 */
	public List<BasePdClazz> getBasePdClazzByKeyWordList(BasePdClazz t) {
		return this.basePdClazzDao.selectBasePdClazzByKeyWordList(t);
	}
}
