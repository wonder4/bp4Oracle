package com.ebiz.bp_oracle.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

public class BaseClassLinkAttribute extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long cls_id;

	private Long attr_id;

	public BaseClassLinkAttribute() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCls_id() {
		return cls_id;
	}

	public void setCls_id(Long cls_id) {
		this.cls_id = cls_id;
	}

	public Long getAttr_id() {
		return attr_id;
	}

	public void setAttr_id(Long attr_id) {
		this.attr_id = attr_id;
	}

}