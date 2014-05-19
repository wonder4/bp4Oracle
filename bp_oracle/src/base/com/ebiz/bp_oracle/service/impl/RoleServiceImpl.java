package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.RoleDao;
import com.ebiz.bp_oracle.domain.Role;
import com.ebiz.bp_oracle.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao roleDao;

	public Long createRole(Role t) {
		return this.roleDao.insertEntity(t);
	}

	public int modifyRole(Role t) {
		return this.roleDao.updateEntity(t);
	}

	public int removeRole(Role t) {
		return this.roleDao.deleteEntity(t);
	}

	public Role getRole(Role t) {
		return this.roleDao.selectEntity(t);
	}

	public Long getRoleCount(Role t) {
		return this.roleDao.selectEntityCount(t);
	}

	public List<Role> getRoleList(Role t) {
		return this.roleDao.selectEntityList(t);
	}

	public List<Role> getRolePaginatedList(Role t) {
		return this.roleDao.selectEntityPaginatedList(t);
	}

}
