package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.BaseClassDao;
import com.ebiz.bp_oracle.domain.BaseClass;
import com.ebiz.bp_oracle.service.BaseClassService;

@Service
public class BaseClassServiceImpl implements BaseClassService {

	@Resource
	private BaseClassDao baseClassDao;

	public Long createBaseClass(BaseClass t) {
		return this.baseClassDao.insertEntity(t);
	}

	public BaseClass getBaseClass(BaseClass t) {
		return this.baseClassDao.selectEntity(t);
	}

	public Long getBaseClassCount(BaseClass t) {
		return this.baseClassDao.selectEntityCount(t);
	}

	public List<BaseClass> getBaseClassList(BaseClass t) {
		return this.baseClassDao.selectEntityList(t);
	}

	public int modifyBaseClass(BaseClass t) {
		return this.baseClassDao.updateEntity(t);
	}

	public int removeBaseClass(BaseClass t) {
		return this.baseClassDao.deleteEntity(t);
	}

	public List<BaseClass> getBaseClassPaginatedList(BaseClass t) {
		return this.baseClassDao.selectEntityPaginatedList(t);
	}

	// 根据参数类别ID、级别，递归查父类
	public List<BaseClass> getBaseClassToParByLevelList(BaseClass t) {
		return this.baseClassDao.selectBaseClassToParByLevelList(t);
	}

	// 查询子类别
	public List<BaseClass> getBaseClassSonList(BaseClass t) {
		return this.baseClassDao.selectBaseClassSonList(t);
	}
}
