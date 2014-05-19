package com.ebiz.bp_oracle.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

public class BaseAttribute extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Integer attr_scope;

	private String attr_name;

	private String attr_show_name;

	private Integer type;

	private String remark;

	private Integer is_required;

	private Integer is_show;

	private Integer is_brand;

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

	private List<BaseAttributeSon> baseAttributeSonList;

	public BaseAttribute() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAttr_scope() {
		return attr_scope;
	}

	public void setAttr_scope(Integer attr_scope) {
		this.attr_scope = attr_scope;
	}

	public String getAttr_name() {
		return attr_name;
	}

	public void setAttr_name(String attr_name) {
		this.attr_name = attr_name;
	}

	public String getAttr_show_name() {
		return attr_show_name;
	}

	public void setAttr_show_name(String attr_show_name) {
		this.attr_show_name = attr_show_name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIs_required() {
		return is_required;
	}

	public void setIs_required(Integer is_required) {
		this.is_required = is_required;
	}

	public Integer getIs_show() {
		return is_show;
	}

	public void setIs_show(Integer is_show) {
		this.is_show = is_show;
	}

	public Integer getIs_brand() {
		return is_brand;
	}

	public void setIs_brand(Integer is_brand) {
		this.is_brand = is_brand;
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

	public List<BaseAttributeSon> getBaseAttributeSonList() {
		return baseAttributeSonList;
	}

	public void setBaseAttributeSonList(List<BaseAttributeSon> baseAttributeSonList) {
		this.baseAttributeSonList = baseAttributeSonList;
	}

}