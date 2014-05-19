package com.ebiz.bp_oracle.dao;

import java.util.List;

import com.ebiz.bp_oracle.domain.BaseClass;
import com.ebiz.ssi.dao.EntityDao;

public interface BaseClassDao extends EntityDao<BaseClass> {

	public List<BaseClass> selectBaseClassToParByLevelList(BaseClass baseClass);

	public List<BaseClass> selectBaseClassSonList(BaseClass baseClass);
}
