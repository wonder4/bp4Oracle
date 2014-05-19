package com.ebiz.bp_oracle.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.RoleDao;
import com.ebiz.bp_oracle.domain.Role;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class RoleDaoSqlMapImpl extends EntityDaoSqlMapImpl<Role> implements RoleDao {

}
