package com.ebiz.bp_oracle.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

public class PdInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long pd_id;

	private String uuid;

	private String pd_name;

	private Integer pd_type;

	private Integer pd_zt_type;

	private Long own_entp_id;

	private Long cls_id;

	private String cls_name;

	private Long par_cls_id;

	private String main_pic;

	private BigDecimal price_ref;

	private Long pd_num;

	private Integer is_sell;

	private Date up_date;

	private Date down_date;

	private Long p_index;

	private Long view_count;

	private Long order_min_num;

	private BigDecimal order_max_supply;

	private String pd_desc;

	private Integer is_commend;

	private Integer is_spec_price;

	private BigDecimal spec_price;

	private Integer is_promotion;

	private Integer order_value;

	private Integer is_del;

	private Date add_date;

	private Long add_user_id;

	private String add_user_name;

	private Date update_date;

	private Long update_user_id;

	private Date del_date;

	private Long del_user_id;

	private Date in_date;

	private Date out_date;

	private Integer audit_state;

	private Long audit_user_id;

	private Date audit_date;

	private String audit_desc;

	private Integer is_locked;

	private Long lock_user_id;

	private Integer pd_class_type;

	private List<PdImgs> pdImgsList;

	private String pd_content;

	private List<PdInfoCustomAttrContent> pdInfoCustomAttrContentList;

	private List<PdInfoCustomFieldContent> pdInfoCustomFieldContentList;

	public PdInfo() {

	}

	public Long getPd_id() {
		return pd_id;
	}

	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPd_name() {
		return pd_name;
	}

	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}

	public Integer getPd_type() {
		return pd_type;
	}

	public void setPd_type(Integer pd_type) {
		this.pd_type = pd_type;
	}

	public Integer getPd_zt_type() {
		return pd_zt_type;
	}

	public void setPd_zt_type(Integer pd_zt_type) {
		this.pd_zt_type = pd_zt_type;
	}

	public Long getOwn_entp_id() {
		return own_entp_id;
	}

	public void setOwn_entp_id(Long own_entp_id) {
		this.own_entp_id = own_entp_id;
	}

	public Long getCls_id() {
		return cls_id;
	}

	public void setCls_id(Long cls_id) {
		this.cls_id = cls_id;
	}

	public String getCls_name() {
		return cls_name;
	}

	public void setCls_name(String cls_name) {
		this.cls_name = cls_name;
	}

	public Long getPar_cls_id() {
		return par_cls_id;
	}

	public void setPar_cls_id(Long par_cls_id) {
		this.par_cls_id = par_cls_id;
	}

	public String getMain_pic() {
		return main_pic;
	}

	public void setMain_pic(String main_pic) {
		this.main_pic = main_pic;
	}

	public BigDecimal getPrice_ref() {
		return price_ref;
	}

	public void setPrice_ref(BigDecimal price_ref) {
		this.price_ref = price_ref;
	}

	public Long getPd_num() {
		return pd_num;
	}

	public void setPd_num(Long pd_num) {
		this.pd_num = pd_num;
	}

	public Integer getIs_sell() {
		return is_sell;
	}

	public void setIs_sell(Integer is_sell) {
		this.is_sell = is_sell;
	}

	public Date getUp_date() {
		return up_date;
	}

	public void setUp_date(Date up_date) {
		this.up_date = up_date;
	}

	public Date getDown_date() {
		return down_date;
	}

	public void setDown_date(Date down_date) {
		this.down_date = down_date;
	}

	public Long getP_index() {
		return p_index;
	}

	public void setP_index(Long p_index) {
		this.p_index = p_index;
	}

	public Long getView_count() {
		return view_count;
	}

	public void setView_count(Long view_count) {
		this.view_count = view_count;
	}

	public Long getOrder_min_num() {
		return order_min_num;
	}

	public void setOrder_min_num(Long order_min_num) {
		this.order_min_num = order_min_num;
	}

	public BigDecimal getOrder_max_supply() {
		return order_max_supply;
	}

	public void setOrder_max_supply(BigDecimal order_max_supply) {
		this.order_max_supply = order_max_supply;
	}

	public String getPd_desc() {
		return pd_desc;
	}

	public void setPd_desc(String pd_desc) {
		this.pd_desc = pd_desc;
	}

	public Integer getIs_commend() {
		return is_commend;
	}

	public void setIs_commend(Integer is_commend) {
		this.is_commend = is_commend;
	}

	public Integer getIs_spec_price() {
		return is_spec_price;
	}

	public void setIs_spec_price(Integer is_spec_price) {
		this.is_spec_price = is_spec_price;
	}

	public BigDecimal getSpec_price() {
		return spec_price;
	}

	public void setSpec_price(BigDecimal spec_price) {
		this.spec_price = spec_price;
	}

	public Integer getOrder_value() {
		return order_value;
	}

	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
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

	public Date getIn_date() {
		return in_date;
	}

	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}

	public Date getOut_date() {
		return out_date;
	}

	public void setOut_date(Date out_date) {
		this.out_date = out_date;
	}

	public Integer getAudit_state() {
		return audit_state;
	}

	public void setAudit_state(Integer audit_state) {
		this.audit_state = audit_state;
	}

	public Long getAudit_user_id() {
		return audit_user_id;
	}

	public void setAudit_user_id(Long audit_user_id) {
		this.audit_user_id = audit_user_id;
	}

	public Date getAudit_date() {
		return audit_date;
	}

	public void setAudit_date(Date audit_date) {
		this.audit_date = audit_date;
	}

	public String getAudit_desc() {
		return audit_desc;
	}

	public void setAudit_desc(String audit_desc) {
		this.audit_desc = audit_desc;
	}

	public Integer getIs_locked() {
		return is_locked;
	}

	public void setIs_locked(Integer is_locked) {
		this.is_locked = is_locked;
	}

	public Long getLock_user_id() {
		return lock_user_id;
	}

	public void setLock_user_id(Long lock_user_id) {
		this.lock_user_id = lock_user_id;
	}

	public Integer getPd_class_type() {
		return pd_class_type;
	}

	public void setPd_class_type(Integer pdClassType) {
		pd_class_type = pdClassType;
	}

	public List<PdImgs> getPdImgsList() {
		return pdImgsList;
	}

	public void setPdImgsList(List<PdImgs> pdImgsList) {
		this.pdImgsList = pdImgsList;
	}

	public String getPd_content() {
		return pd_content;
	}

	public void setPd_content(String pdContent) {
		pd_content = pdContent;
	}

	public List<PdInfoCustomAttrContent> getPdInfoCustomAttrContentList() {
		return pdInfoCustomAttrContentList;
	}

	public void setPdInfoCustomAttrContentList(List<PdInfoCustomAttrContent> pdInfoCustomAttrContentList) {
		this.pdInfoCustomAttrContentList = pdInfoCustomAttrContentList;
	}

	public List<PdInfoCustomFieldContent> getPdInfoCustomFieldContentList() {
		return pdInfoCustomFieldContentList;
	}

	public void setPdInfoCustomFieldContentList(List<PdInfoCustomFieldContent> pdInfoCustomFieldContentList) {
		this.pdInfoCustomFieldContentList = pdInfoCustomFieldContentList;
	}

	public Integer getIs_promotion() {
		return is_promotion;
	}

	public void setIs_promotion(Integer isPromotion) {
		is_promotion = isPromotion;
	}

}