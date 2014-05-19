package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.PdInfo;

public interface PdInfoService {

	Long createPdInfo(PdInfo t);

	int modifyPdInfo(PdInfo t);

	int removePdInfo(PdInfo t);

	PdInfo getPdInfo(PdInfo t);

	List<PdInfo> getPdInfoList(PdInfo t);

	Long getPdInfoCount(PdInfo t);

	List<PdInfo> getPdInfoPaginatedList(PdInfo t);

	// 热门搜索 Li,Ka 2012-3-31
	List<PdInfo> getPdInfoForHotSeacherList(PdInfo t);

	/**
	 * @desc 根据属性组合查询
	 */
	Long getPdInfoForSearchAttrCount(PdInfo t);

	/**
	 * @desc 根据属性组合查询分页
	 */
	List<PdInfo> getPdInfoForSearchAttrPaginatedList(PdInfo t);

}