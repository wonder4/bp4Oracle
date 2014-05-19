package com.ebiz.bp_oracle.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

public class NewsAttachment extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long link_id;

	private String link_tab;

	private String file_name;

	private String file_type;

	private Long file_size;

	private String save_path;

	private String save_name;

	private String file_desc;

	private Integer is_del;

	public NewsAttachment() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLink_id() {
		return link_id;
	}

	public void setLink_id(Long link_id) {
		this.link_id = link_id;
	}

	public String getLink_tab() {
		return link_tab;
	}

	public void setLink_tab(String link_tab) {
		this.link_tab = link_tab;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public Long getFile_size() {
		return file_size;
	}

	public void setFile_size(Long file_size) {
		this.file_size = file_size;
	}

	public String getSave_path() {
		return save_path;
	}

	public void setSave_path(String save_path) {
		this.save_path = save_path;
	}

	public String getSave_name() {
		return save_name;
	}

	public void setSave_name(String save_name) {
		this.save_name = save_name;
	}

	public String getFile_desc() {
		return file_desc;
	}

	public void setFile_desc(String file_desc) {
		this.file_desc = file_desc;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

}