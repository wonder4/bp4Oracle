package com.ebiz.bp_oracle.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.NewsAttachmentDao;
import com.ebiz.bp_oracle.domain.NewsAttachment;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class NewsAttachmentDaoSqlMapImpl extends EntityDaoSqlMapImpl<NewsAttachment> implements NewsAttachmentDao {

}
