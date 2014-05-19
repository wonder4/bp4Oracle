package com.ebiz.bp_oracle.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.DeptInfoDao;
import com.ebiz.bp_oracle.domain.DeptInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class DeptInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<DeptInfo> implements DeptInfoDao {

	public Long selectDeptInfoWithLevelNumber(DeptInfo t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectDeptInfoWithLevelNumber", t);
	}

	@SuppressWarnings("unchecked")
	public List<DeptInfo> selectDeptInfoParentList(DeptInfo t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectDeptInfoParentList", t);
	}

}
