package com.ebiz.bp_oracle.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.PdInfoCustomFieldContentDao;
import com.ebiz.bp_oracle.domain.PdInfoCustomFieldContent;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class PdInfoCustomFieldContentDaoSqlMapImpl extends EntityDaoSqlMapImpl<PdInfoCustomFieldContent> implements
		PdInfoCustomFieldContentDao {

}
