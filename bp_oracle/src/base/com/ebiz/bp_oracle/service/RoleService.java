package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.Role;

public interface RoleService {

	Long createRole(Role t);

	int modifyRole(Role t);

	int removeRole(Role t);

	Role getRole(Role t);

	List<Role> getRoleList(Role t);

	Long getRoleCount(Role t);

	List<Role> getRolePaginatedList(Role t);

}
