package com.ebiz.bp_oracle.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.PdInfoCustomAttrContentDao;
import com.ebiz.bp_oracle.domain.PdInfoCustomAttrContent;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class PdInfoCustomAttrContentDaoSqlMapImpl extends EntityDaoSqlMapImpl<PdInfoCustomAttrContent> implements
		PdInfoCustomAttrContentDao {

}
