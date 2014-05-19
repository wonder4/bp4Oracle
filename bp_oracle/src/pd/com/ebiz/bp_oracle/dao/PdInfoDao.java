package com.ebiz.bp_oracle.dao;

import java.util.List;

import com.ebiz.bp_oracle.domain.PdInfo;
import com.ebiz.ssi.dao.EntityDao;

public interface PdInfoDao extends EntityDao<PdInfo> {

	// 热门搜索 Li,Ka 2012-3-31
	public List<PdInfo> selectPdInfoForHotSeacherList(PdInfo pdInfo);

	/**
	 * @desc 根据属性组合查询
	 */
	Long selectPdInfoForSearchAttrCount(PdInfo t);

	/**
	 * @desc 根据属性组合查询分页
	 */
	List<PdInfo> selectPdInfoForSearchAttrPaginatedList(PdInfo t);
}
