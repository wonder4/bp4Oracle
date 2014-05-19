package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.RoleUser;

public interface RoleUserService {

	Long createRoleUser(RoleUser t);

	int modifyRoleUser(RoleUser t);

	int removeRoleUser(RoleUser t);

	RoleUser getRoleUser(RoleUser t);

	List<RoleUser> getRoleUserList(RoleUser t);

	Long getRoleUserCount(RoleUser t);

	List<RoleUser> getRoleUserPaginatedList(RoleUser t);

}
