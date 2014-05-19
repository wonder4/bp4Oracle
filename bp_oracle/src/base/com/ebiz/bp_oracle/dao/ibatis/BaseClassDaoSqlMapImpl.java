package com.ebiz.bp_oracle.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.BaseClassDao;
import com.ebiz.bp_oracle.domain.BaseClass;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class BaseClassDaoSqlMapImpl extends EntityDaoSqlMapImpl<BaseClass> implements BaseClassDao {

	// 根据参数类别ID、级别，递归查父类
	@SuppressWarnings("unchecked")
	public List<BaseClass> selectBaseClassToParByLevelList(BaseClass baseClass) {
		return super.getSqlMapClientTemplate().queryForList("selectBaseClassToParByLevelList", baseClass);
	}

	@SuppressWarnings("unchecked")
	public List<BaseClass> selectBaseClassSonList(BaseClass baseClass) {
		return super.getSqlMapClientTemplate().queryForList("selectBaseClassSonList", baseClass);
	}

}
