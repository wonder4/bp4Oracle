package com.ebiz.bp_oracle.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

public class Role extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String role_name;

	private Integer order_value;

	private Integer is_lock;

	private Integer is_del;

	private Date add_date;

	private Long add_user_id;

	private Date update_date;

	private Long update_user_id;

	private Date del_date;

	private Long del_user_id;

	private Integer sys_sign;

	private List<RoleUser> roleUserList;

	public Role() {

	}

	public List<RoleUser> getRoleUserList() {
		return roleUserList;
	}

	public void setRoleUserList(List<RoleUser> roleUserList) {
		this.roleUserList = roleUserList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public Integer getOrder_value() {
		return order_value;
	}

	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}

	public Integer getIs_lock() {
		return is_lock;
	}

	public void setIs_lock(Integer is_lock) {
		this.is_lock = is_lock;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public Long getAdd_user_id() {
		return add_user_id;
	}

	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public Long getUpdate_user_id() {
		return update_user_id;
	}

	public void setUpdate_user_id(Long update_user_id) {
		this.update_user_id = update_user_id;
	}

	public Date getDel_date() {
		return del_date;
	}

	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}

	public Long getDel_user_id() {
		return del_user_id;
	}

	public void setDel_user_id(Long del_user_id) {
		this.del_user_id = del_user_id;
	}

	public Integer getSys_sign() {
		return sys_sign;
	}

	public void setSys_sign(Integer sysSign) {
		sys_sign = sysSign;
	}

}