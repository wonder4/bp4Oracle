package com.ebiz.bp_oracle.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.BaseAttributeDao;
import com.ebiz.bp_oracle.domain.BaseAttribute;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class BaseAttributeDaoSqlMapImpl extends EntityDaoSqlMapImpl<BaseAttribute> implements BaseAttributeDao {

}
