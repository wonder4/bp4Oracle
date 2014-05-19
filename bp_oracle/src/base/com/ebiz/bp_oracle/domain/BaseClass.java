package com.ebiz.bp_oracle.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

public class BaseClass extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long cls_id;

	private Integer cls_scope;

	private String cls_name;

	private Long par_id;

	private Long root_id;

	private Integer order_value;

	private Integer is_lock;

	private Integer is_del;

	private Date add_date;

	private Long add_user_id;

	private String add_user_name;

	private Date update_date;

	private Long update_user_id;

	private Date del_date;

	private Long del_user_id;

	private Integer template_type;

	public BaseClass() {

	}

	public Long getCls_id() {
		return cls_id;
	}

	public void setCls_id(Long cls_id) {
		this.cls_id = cls_id;
	}

	public Integer getCls_scope() {
		return cls_scope;
	}

	public void setCls_scope(Integer cls_scope) {
		this.cls_scope = cls_scope;
	}

	public String getCls_name() {
		return cls_name;
	}

	public void setCls_name(String cls_name) {
		this.cls_name = cls_name;
	}

	public Long getPar_id() {
		return par_id;
	}

	public void setPar_id(Long par_id) {
		this.par_id = par_id;
	}

	public Long getRoot_id() {
		return root_id;
	}

	public void setRoot_id(Long root_id) {
		this.root_id = root_id;
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

	public String getAdd_user_name() {
		return add_user_name;
	}

	public void setAdd_user_name(String add_user_name) {
		this.add_user_name = add_user_name;
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

	public Integer getTemplate_type() {
		return template_type;
	}

	public void setTemplate_type(Integer template_type) {
		this.template_type = template_type;
	}

}