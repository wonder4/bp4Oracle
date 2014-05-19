package com.ebiz.bp_oracle.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

public class PdInfoCustomAttrContent extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long pd_id;

	private Long cls_id;

	private Long par_attr_id;

	private Long attr_id;

	private String attr_name;

	private Long brand_id;

	public PdInfoCustomAttrContent() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPd_id() {
		return pd_id;
	}

	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
	}

	public Long getCls_id() {
		return cls_id;
	}

	public void setCls_id(Long clsId) {
		cls_id = clsId;
	}

	public Long getAttr_id() {
		return attr_id;
	}

	public void setAttr_id(Long attr_id) {
		this.attr_id = attr_id;
	}

	public String getAttr_name() {
		return attr_name;
	}

	public Long getPar_attr_id() {
		return par_attr_id;
	}

	public void setPar_attr_id(Long parAttrId) {
		par_attr_id = parAttrId;
	}

	public void setAttr_name(String attr_name) {
		this.attr_name = attr_name;
	}

	public Long getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Long brandId) {
		brand_id = brandId;
	}

}