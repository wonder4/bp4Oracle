package com.ebiz.bp_oracle.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

public class DeptInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long dept_id;

	private Long par_id;

	private String dept_name;

	private String dept_desc;

	private String dept_url;

	private String dept_master;

	private String dept_tel;

	private String dept_fax;

	private String dept_addr;

	private Integer is_virtual;

	private Integer order_value;

	private Integer is_lock;

	private Integer is_del;

	public DeptInfo() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDept_id() {
		return dept_id;
	}

	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	public Long getPar_id() {
		return par_id;
	}

	public void setPar_id(Long par_id) {
		this.par_id = par_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getDept_desc() {
		return dept_desc;
	}

	public void setDept_desc(String dept_desc) {
		this.dept_desc = dept_desc;
	}

	public String getDept_url() {
		return dept_url;
	}

	public void setDept_url(String dept_url) {
		this.dept_url = dept_url;
	}

	public String getDept_master() {
		return dept_master;
	}

	public void setDept_master(String dept_master) {
		this.dept_master = dept_master;
	}

	public String getDept_tel() {
		return dept_tel;
	}

	public void setDept_tel(String dept_tel) {
		this.dept_tel = dept_tel;
	}

	public String getDept_fax() {
		return dept_fax;
	}

	public void setDept_fax(String dept_fax) {
		this.dept_fax = dept_fax;
	}

	public String getDept_addr() {
		return dept_addr;
	}

	public void setDept_addr(String dept_addr) {
		this.dept_addr = dept_addr;
	}

	public Integer getIs_virtual() {
		return is_virtual;
	}

	public void setIs_virtual(Integer is_virtual) {
		this.is_virtual = is_virtual;
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

}