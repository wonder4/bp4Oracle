package com.ebiz.bp_oracle.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.BaseProvinceDao;
import com.ebiz.bp_oracle.domain.BaseProvince;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class BaseProvinceDaoSqlMapImpl extends EntityDaoSqlMapImpl<BaseProvince> implements BaseProvinceDao {

	@SuppressWarnings("unchecked")
	public List<BaseProvince> selectBaseProvinceParentList(BaseProvince baseProvince) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectBaseProvinceParentList", baseProvince);
	}
}
