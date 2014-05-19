package com.ebiz.bp_oracle.domain.chart;

import java.io.Serializable;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

public class MSColumn3DChart extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1482092324728295042L;

	private String series_name;

	private String color;

	private Integer show_values = new Integer(0);

	private List<BaseChart> baseChartList;

	public String getSeries_name() {
		return series_name;
	}

	public void setSeries_name(String series_name) {
		this.series_name = series_name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getShow_values() {
		return show_values;
	}

	public void setShow_values(Integer show_values) {
		this.show_values = show_values;
	}

	public List<BaseChart> getBaseChartList() {
		return baseChartList;
	}

	public void setBaseChartList(List<BaseChart> baseChartList) {
		this.baseChartList = baseChartList;
	}

}