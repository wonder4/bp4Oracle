package com.ebiz.bp_oracle.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Hui,Gang
 * @version 2013-12-11 下午04:41:46
 */
public class MailAttachment extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 关联ID
	 */
	private Long link_id;

	/**
	 * 关联表
	 */
	private String link_tab;

	/**
	 * 源文件名
	 */
	private String file_name;

	/**
	 * 文件类型
	 */
	private String file_type;

	/**
	 * 文件大小
	 */
	private String file_size;

	/**
	 * 存储路径
	 */
	private String save_path;

	/**
	 * 存储名称
	 */
	private String save_name;

	/**
	 * 文件说明
	 */
	private String file_desc;

	/**
	 * 是否删除
	 */
	private Long is_del;

	public MailAttachment() {

	}

	/**
	 * ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 关联ID
	 */
	public void setLink_id(Long link_id) {
		this.link_id = link_id;
	}

	/**
	 * 关联ID
	 */
	public Long getLink_id() {
		return link_id;
	}

	/**
	 * 关联表
	 */
	public void setLink_tab(String link_tab) {
		this.link_tab = link_tab;
	}

	/**
	 * 关联表
	 */
	public String getLink_tab() {
		return link_tab;
	}

	/**
	 * 源文件名
	 */
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	/**
	 * 源文件名
	 */
	public String getFile_name() {
		return file_name;
	}

	/**
	 * 文件类型
	 */
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	/**
	 * 文件类型
	 */
	public String getFile_type() {
		return file_type;
	}

	/**
	 * 文件大小
	 */
	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}

	/**
	 * 文件大小
	 */
	public String getFile_size() {
		return file_size;
	}

	/**
	 * 存储路径
	 */
	public void setSave_path(String save_path) {
		this.save_path = save_path;
	}

	/**
	 * 存储路径
	 */
	public String getSave_path() {
		return save_path;
	}

	/**
	 * 存储名称
	 */
	public void setSave_name(String save_name) {
		this.save_name = save_name;
	}

	/**
	 * 存储名称
	 */
	public String getSave_name() {
		return save_name;
	}

	/**
	 * 文件说明
	 */
	public void setFile_desc(String file_desc) {
		this.file_desc = file_desc;
	}

	/**
	 * 文件说明
	 */
	public String getFile_desc() {
		return file_desc;
	}

	/**
	 * 是否删除
	 */
	public void setIs_del(Long is_del) {
		this.is_del = is_del;
	}

	/**
	 * 是否删除
	 */
	public Long getIs_del() {
		return is_del;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}