package com.ebiz.bp_oracle.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Hui,Gang
 * @version 2013-12-11 下午04:41:38
 */
public class MailPeop extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 站内信ID
	 */
	private Long mail_id;

	/**
	 * 发送或接收0：发送1：接收2：抄送
	 */
	private Long is_rece;

	/**
	 * 接收人员或部门ID
	 */
	private Long rece_id;

	/**
	 * 接收对象【保留字段】0：人员ID1：部门ID
	 */
	private Long rece_obj;

	/**
	 * 信息状态：-1-暂存，0-已接收未查看，1-已发送，2-已查看，3-已回复，4-已删除这个里面的删除状态是放在垃圾箱里的。如果在垃圾箱里再点删除，将IS_DEL=1如果改为删除，则需要把删除前的状态记录到RE_STATE中去。
	 */
	private Long mail_state;

	/**
	 * 恢复状态
	 */
	private Long re_state;

	/**
	 * 是否删除： 0：未删除 1：已删除
	 */
	private Long is_del;

	/**
	 * 删除时间
	 */
	private Date del_date;

	public MailPeop() {

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
	 * 站内信ID
	 */
	public void setMail_id(Long mail_id) {
		this.mail_id = mail_id;
	}

	/**
	 * 站内信ID
	 */
	public Long getMail_id() {
		return mail_id;
	}

	/**
	 * 发送或接收0：发送1：接收2：抄送
	 */
	public void setIs_rece(Long is_rece) {
		this.is_rece = is_rece;
	}

	/**
	 * 发送或接收0：发送1：接收2：抄送
	 */
	public Long getIs_rece() {
		return is_rece;
	}

	/**
	 * 接收人员或部门ID
	 */
	public void setRece_id(Long rece_id) {
		this.rece_id = rece_id;
	}

	/**
	 * 接收人员或部门ID
	 */
	public Long getRece_id() {
		return rece_id;
	}

	/**
	 * 接收对象【保留字段】0：人员ID1：部门ID
	 */
	public void setRece_obj(Long rece_obj) {
		this.rece_obj = rece_obj;
	}

	/**
	 * 接收对象【保留字段】0：人员ID1：部门ID
	 */
	public Long getRece_obj() {
		return rece_obj;
	}

	/**
	 * 信息状态：-1-暂存，0-已接收未查看，1-已发送，2-已查看，3-已回复，4-已删除这个里面的删除状态是放在垃圾箱里的。如果在垃圾箱里再点删除，将IS_DEL=1如果改为删除，则需要把删除前的状态记录到RE_STATE中去。
	 */
	public void setMail_state(Long mail_state) {
		this.mail_state = mail_state;
	}

	/**
	 * 信息状态：-1-暂存，0-已接收未查看，1-已发送，2-已查看，3-已回复，4-已删除这个里面的删除状态是放在垃圾箱里的。如果在垃圾箱里再点删除，将IS_DEL=1如果改为删除，则需要把删除前的状态记录到RE_STATE中去。
	 */
	public Long getMail_state() {
		return mail_state;
	}

	/**
	 * 恢复状态
	 */
	public void setRe_state(Long re_state) {
		this.re_state = re_state;
	}

	/**
	 * 恢复状态
	 */
	public Long getRe_state() {
		return re_state;
	}

	/**
	 * 是否删除： 0：未删除 1：已删除
	 */
	public void setIs_del(Long is_del) {
		this.is_del = is_del;
	}

	/**
	 * 是否删除： 0：未删除 1：已删除
	 */
	public Long getIs_del() {
		return is_del;
	}

	/**
	 * 删除时间
	 */
	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}

	/**
	 * 删除时间
	 */
	public Date getDel_date() {
		return del_date;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}