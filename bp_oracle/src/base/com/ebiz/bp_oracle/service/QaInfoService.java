package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.QaInfo;

public interface QaInfoService {

	Long createQaInfo(QaInfo t);

	int modifyQaInfo(QaInfo t);

	int removeQaInfo(QaInfo t);

	QaInfo getQaInfo(QaInfo t);

	List<QaInfo> getQaInfoList(QaInfo t);

	Long getQaInfoCount(QaInfo t);

	List<QaInfo> getQaInfoPaginatedList(QaInfo t);

}