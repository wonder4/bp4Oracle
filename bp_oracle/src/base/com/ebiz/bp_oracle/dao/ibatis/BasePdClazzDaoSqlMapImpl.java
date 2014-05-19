package com.ebiz.bp_oracle.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.BasePdClazzDao;
import com.ebiz.bp_oracle.domain.BasePdClazz;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class BasePdClazzDaoSqlMapImpl extends EntityDaoSqlMapImpl<BasePdClazz> implements BasePdClazzDao {

	/**
	 * @desc 根据 产品 关键字搜索的所有分类
	 */
	@SuppressWarnings("unchecked")
	public List<BasePdClazz> selectBasePdClazzByKeyWordList(BasePdClazz t) {
		return super.getSqlMapClientTemplate().queryForList("selectBasePdClazzByKeyWordList", t);
	}

}
