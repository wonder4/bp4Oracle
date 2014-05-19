package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.RoleUserDao;
import com.ebiz.bp_oracle.domain.RoleUser;
import com.ebiz.bp_oracle.service.RoleUserService;

@Service
public class RoleUserServiceImpl implements RoleUserService {

	@Resource
	private RoleUserDao roleUserDao;

	public Long createRoleUser(RoleUser t) {
		return this.roleUserDao.insertEntity(t);
	}

	public int modifyRoleUser(RoleUser t) {
		return this.roleUserDao.updateEntity(t);
	}

	public int removeRoleUser(RoleUser t) {
		return this.roleUserDao.deleteEntity(t);
	}

	public RoleUser getRoleUser(RoleUser t) {
		return this.roleUserDao.selectEntity(t);
	}

	public Long getRoleUserCount(RoleUser t) {
		return this.roleUserDao.selectEntityCount(t);
	}

	public List<RoleUser> getRoleUserList(RoleUser t) {
		return this.roleUserDao.selectEntityList(t);
	}

	public List<RoleUser> getRoleUserPaginatedList(RoleUser t) {
		return this.roleUserDao.selectEntityPaginatedList(t);
	}

}
