package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.NewsAttachment;

public interface NewsAttachmentService {

	Long createNewsAttachment(NewsAttachment t);

	int modifyNewsAttachment(NewsAttachment t);

	int removeNewsAttachment(NewsAttachment t);

	NewsAttachment getNewsAttachment(NewsAttachment t);

	List<NewsAttachment> getNewsAttachmentList(NewsAttachment t);

	Long getNewsAttachmentCount(NewsAttachment t);

	List<NewsAttachment> getNewsAttachmentPaginatedList(NewsAttachment t);

}