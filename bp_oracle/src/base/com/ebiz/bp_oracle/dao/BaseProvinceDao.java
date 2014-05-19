package com.ebiz.bp_oracle.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.bp_oracle.domain.BaseProvince;
import com.ebiz.ssi.dao.EntityDao;

public interface BaseProvinceDao extends EntityDao<BaseProvince> {

	List<BaseProvince> selectBaseProvinceParentList(BaseProvince baseProvince) throws DataAccessException;
}
