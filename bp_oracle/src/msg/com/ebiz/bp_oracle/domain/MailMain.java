package com.ebiz.bp_oracle.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Hui,Gang
 * @version 2013-12-11 下午04:41:42
 */
public class MailMain extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * 站内信ID
	 */
	private Long id;

	/**
	 * 父站内信ID：首次发送为0。回复则为回复的邮件ID
	 */
	private Long par_id;

	/**
	 * 部门ID【保留字段】
	 */
	private Long dept_id;

	/**
	 * 信息标题：不能超过30个汉字
	 */
	private String title;

	/**
	 * 信息内容：不超过2000个汉字
	 */
	private String content;

	/**
	 * 发送人ID
	 */
	private Long send_user_id;

	/**
	 * 是否浮动
	 */
	private Long is_float;

	/**
	 * 发送时间
	 */
	private Date send_date;

	/**
	 * 添加时间
	 */
	private Date add_date;

	public MailMain() {

	}

	/**
	 * 站内信ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 站内信ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 父站内信ID：首次发送为0。回复则为回复的邮件ID
	 */
	public void setPar_id(Long par_id) {
		this.par_id = par_id;
	}

	/**
	 * 父站内信ID：首次发送为0。回复则为回复的邮件ID
	 */
	public Long getPar_id() {
		return par_id;
	}

	/**
	 * 部门ID【保留字段】
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 * 部门ID【保留字段】
	 */
	public Long getDept_id() {
		return dept_id;
	}

	/**
	 * 信息标题：不能超过30个汉字
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 信息标题：不能超过30个汉字
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 信息内容：不超过2000个汉字
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 信息内容：不超过2000个汉字
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 发送人ID
	 */
	public void setSend_user_id(Long send_user_id) {
		this.send_user_id = send_user_id;
	}

	/**
	 * 发送人ID
	 */
	public Long getSend_user_id() {
		return send_user_id;
	}

	/**
	 * 发送时间
	 */
	public void setSend_date(Date send_date) {
		this.send_date = send_date;
	}

	/**
	 * 发送时间
	 */
	public Date getSend_date() {
		return send_date;
	}

	/**
	 * 添加时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	/**
	 * 添加时间
	 */
	public Date getAdd_date() {
		return add_date;
	}

	public Long getIs_float() {
		return is_float;
	}

	public void setIs_float(Long isFloat) {
		is_float = isFloat;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	private List<MailAttachment> attachmentList;

	public List<MailAttachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<MailAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

}