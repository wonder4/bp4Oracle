package com.ebiz.bp_oracle.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

public class BaseProvince extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long p_index;

	private String p_name;

	private String s_name;

	private Long par_index;

	private Integer p_level;

	private Integer alone;

	private Long root_code;

	private Integer p_mag;

	private String p_code;

	private Integer is_west;

	private String full_name;

	private Long order_value;

	private Date add_date;

	private Integer is_del;

	private Date del_date;

	public BaseProvince() {

	}

	public Long getP_index() {
		return p_index;
	}

	public void setP_index(Long p_index) {
		this.p_index = p_index;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public Long getPar_index() {
		return par_index;
	}

	public void setPar_index(Long par_index) {
		this.par_index = par_index;
	}

	public Integer getP_level() {
		return p_level;
	}

	public void setP_level(Integer p_level) {
		this.p_level = p_level;
	}

	public Integer getAlone() {
		return alone;
	}

	public void setAlone(Integer alone) {
		this.alone = alone;
	}

	public Long getRoot_code() {
		return root_code;
	}

	public void setRoot_code(Long root_code) {
		this.root_code = root_code;
	}

	public Integer getP_mag() {
		return p_mag;
	}

	public void setP_mag(Integer p_mag) {
		this.p_mag = p_mag;
	}

	public String getP_code() {
		return p_code;
	}

	public void setP_code(String p_code) {
		this.p_code = p_code;
	}

	public Integer getIs_west() {
		return is_west;
	}

	public void setIs_west(Integer is_west) {
		this.is_west = is_west;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public Long getOrder_value() {
		return order_value;
	}

	public void setOrder_value(Long order_value) {
		this.order_value = order_value;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public Date getDel_date() {
		return del_date;
	}

	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}

}