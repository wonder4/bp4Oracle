package com.ebiz.bp_oracle.domain;

import java.io.Serializable;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

public class BasePdClazz extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long cls_id;

	private String cls_name;

	private String tree_name;

	private Integer is_leaf;

	private Integer cls_level;

	private Long par_id;

	private String par_name;

	private Long root_id;

	private String root_name;

	private String full_name;

	private Integer order_value;

	private Integer is_del;

	List<BasePdClazz> basePdClazzList;

	private List<PdInfo> pdInfoList;

	public BasePdClazz() {

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

	public String getTree_name() {
		return tree_name;
	}

	public void setTree_name(String tree_name) {
		this.tree_name = tree_name;
	}

	public Integer getIs_leaf() {
		return is_leaf;
	}

	public void setIs_leaf(Integer is_leaf) {
		this.is_leaf = is_leaf;
	}

	public Integer getCls_level() {
		return cls_level;
	}

	public void setCls_level(Integer cls_level) {
		this.cls_level = cls_level;
	}

	public Long getPar_id() {
		return par_id;
	}

	public void setPar_id(Long par_id) {
		this.par_id = par_id;
	}

	public String getPar_name() {
		return par_name;
	}

	public void setPar_name(String par_name) {
		this.par_name = par_name;
	}

	public Long getRoot_id() {
		return root_id;
	}

	public void setRoot_id(Long root_id) {
		this.root_id = root_id;
	}

	public String getRoot_name() {
		return root_name;
	}

	public void setRoot_name(String root_name) {
		this.root_name = root_name;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
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

	public List<BasePdClazz> getBasePdClazzList() {
		return basePdClazzList;
	}

	public void setBasePdClazzList(List<BasePdClazz> basePdClazzList) {
		this.basePdClazzList = basePdClazzList;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public List<PdInfo> getPdInfoList() {
		return pdInfoList;
	}

	public void setPdInfoList(List<PdInfo> pdInfoList) {
		this.pdInfoList = pdInfoList;
	}

}