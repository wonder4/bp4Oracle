package com.ebiz.bp_oracle.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

public class PdContent extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long pd_id;

	private Integer type;

	private String content;

	public PdContent() {

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}