package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.NewsAttachmentDao;
import com.ebiz.bp_oracle.domain.NewsAttachment;
import com.ebiz.bp_oracle.service.NewsAttachmentService;

@Service
public class NewsAttachmentServiceImpl implements NewsAttachmentService {

	@Resource
	private NewsAttachmentDao newsAttachmentDao;

	public Long createNewsAttachment(NewsAttachment t) {
		return this.newsAttachmentDao.insertEntity(t);
	}

	public NewsAttachment getNewsAttachment(NewsAttachment t) {
		return this.newsAttachmentDao.selectEntity(t);
	}

	public Long getNewsAttachmentCount(NewsAttachment t) {
		return this.newsAttachmentDao.selectEntityCount(t);
	}

	public List<NewsAttachment> getNewsAttachmentList(NewsAttachment t) {
		return this.newsAttachmentDao.selectEntityList(t);
	}

	public int modifyNewsAttachment(NewsAttachment t) {
		return this.newsAttachmentDao.updateEntity(t);
	}

	public int removeNewsAttachment(NewsAttachment t) {
		return this.newsAttachmentDao.deleteEntity(t);
	}

	public List<NewsAttachment> getNewsAttachmentPaginatedList(NewsAttachment t) {
		return this.newsAttachmentDao.selectEntityPaginatedList(t);
	}

}
