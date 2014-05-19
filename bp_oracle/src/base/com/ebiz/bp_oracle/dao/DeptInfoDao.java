package com.ebiz.bp_oracle.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.bp_oracle.domain.DeptInfo;
import com.ebiz.ssi.dao.EntityDao;

public interface DeptInfoDao extends EntityDao<DeptInfo> {

	Long selectDeptInfoWithLevelNumber(DeptInfo t) throws DataAccessException;

	List<DeptInfo> selectDeptInfoParentList(DeptInfo t) throws DataAccessException;

}
