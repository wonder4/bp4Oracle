package com.ebiz.bp_oracle.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.PdInfoDao;
import com.ebiz.bp_oracle.domain.PdInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
@SuppressWarnings("unchecked")
public class PdInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<PdInfo> implements PdInfoDao {

	// 热门搜索 Li,Ka 2012-3-31
	public List<PdInfo> selectPdInfoForHotSeacherList(PdInfo pdInfo) {
		return super.getSqlMapClientTemplate().queryForList("selectPdInfoForHotSeacherList", pdInfo);
	}

	/**
	 * @desc 根据属性组合查询
	 */
	public Long selectPdInfoForSearchAttrCount(PdInfo t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectPdInfoForSearchAttrCount", t);
	}

	/**
	 * @desc 根据属性组合查询分页
	 */
	public List<PdInfo> selectPdInfoForSearchAttrPaginatedList(PdInfo t) {
		return this.getSqlMapClientTemplate().queryForList("selectPdInfoForSearchAttrPaginatedList", t);
	}

}
