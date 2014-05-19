package com.ebiz.bp_oracle.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.NewsContentDao;
import com.ebiz.bp_oracle.domain.NewsContent;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class NewsContentDaoSqlMapImpl extends EntityDaoSqlMapImpl<NewsContent> implements NewsContentDao {

}
