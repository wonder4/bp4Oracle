package com.ebiz.bp_oracle.dao;

import java.util.List;

import com.ebiz.bp_oracle.domain.BasePdClazz;
import com.ebiz.ssi.dao.EntityDao;

public interface BasePdClazzDao extends EntityDao<BasePdClazz> {

	public List<BasePdClazz> selectBasePdClazzByKeyWordList(BasePdClazz t);
}
