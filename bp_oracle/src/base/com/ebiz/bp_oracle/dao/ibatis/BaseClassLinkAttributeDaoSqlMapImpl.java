package com.ebiz.bp_oracle.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.BaseClassLinkAttributeDao;
import com.ebiz.bp_oracle.domain.BaseClassLinkAttribute;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class BaseClassLinkAttributeDaoSqlMapImpl extends EntityDaoSqlMapImpl<BaseClassLinkAttribute> implements
		BaseClassLinkAttributeDao {

}
