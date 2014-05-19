package com.ebiz.bp_oracle.domain.chart;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

public class BaseChart extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1064800117493765956L;

	private String value;

	private String label;

	private String category_label;

	private Integer is_sliced = new Integer(0);

	public BaseChart() {

	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getIs_sliced() {
		return is_sliced;
	}

	public void setIs_sliced(Integer is_sliced) {
		this.is_sliced = is_sliced;
	}

	public String getCategory_label() {
		return category_label;
	}

	public void setCategory_label(String category_label) {
		this.category_label = category_label;
	}

}