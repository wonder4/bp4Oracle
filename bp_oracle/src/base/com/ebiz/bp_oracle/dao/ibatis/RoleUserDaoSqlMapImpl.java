package com.ebiz.bp_oracle.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.RoleUserDao;
import com.ebiz.bp_oracle.domain.RoleUser;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class RoleUserDaoSqlMapImpl extends EntityDaoSqlMapImpl<RoleUser> implements RoleUserDao {

}
