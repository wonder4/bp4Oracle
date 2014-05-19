package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.NewsContentDao;
import com.ebiz.bp_oracle.domain.NewsContent;
import com.ebiz.bp_oracle.service.NewsContentService;

@Service
public class NewsContentServiceImpl implements NewsContentService {

	@Resource
	private NewsContentDao newsContentDao;

	public Long createNewsContent(NewsContent t) {
		return this.newsContentDao.insertEntity(t);
	}

	public NewsContent getNewsContent(NewsContent t) {
		return this.newsContentDao.selectEntity(t);
	}

	public Long getNewsContentCount(NewsContent t) {
		return this.newsContentDao.selectEntityCount(t);
	}

	public List<NewsContent> getNewsContentList(NewsContent t) {
		return this.newsContentDao.selectEntityList(t);
	}

	public int modifyNewsContent(NewsContent t) {
		return this.newsContentDao.updateEntity(t);
	}

	public int removeNewsContent(NewsContent t) {
		return this.newsContentDao.deleteEntity(t);
	}

	public List<NewsContent> getNewsContentPaginatedList(NewsContent t) {
		return this.newsContentDao.selectEntityPaginatedList(t);
	}

}
