package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.NewsContent;

public interface NewsContentService {

	Long createNewsContent(NewsContent t);

	int modifyNewsContent(NewsContent t);

	int removeNewsContent(NewsContent t);

	NewsContent getNewsContent(NewsContent t);

	List<NewsContent> getNewsContentList(NewsContent t);

	Long getNewsContentCount(NewsContent t);

	List<NewsContent> getNewsContentPaginatedList(NewsContent t);

}