package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.NewsInfo;

public interface NewsInfoService {

	Long createNewsInfo(NewsInfo t);

	int modifyNewsInfo(NewsInfo t);

	int removeNewsInfo(NewsInfo t);

	NewsInfo getNewsInfo(NewsInfo t);

	List<NewsInfo> getNewsInfoList(NewsInfo t);

	Long getNewsInfoCount(NewsInfo t);

	List<NewsInfo> getNewsInfoPaginatedList(NewsInfo t);

}