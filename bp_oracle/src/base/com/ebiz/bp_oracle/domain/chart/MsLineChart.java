package com.ebiz.bp_oracle.domain.chart;

import java.io.Serializable;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

public class MsLineChart extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1482092324728295042L;

	private String series_name;

	private String color;

	private String anchor_border_color;

	private String anchor_bg_color;

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

	public String getAnchor_border_color() {
		return anchor_border_color;
	}

	public void setAnchor_border_color(String anchor_border_color) {
		this.anchor_border_color = anchor_border_color;
	}

	public String getAnchor_bg_color() {
		return anchor_bg_color;
	}

	public void setAnchor_bg_color(String anchor_bg_color) {
		this.anchor_bg_color = anchor_bg_color;
	}

	public List<BaseChart> getBaseChartList() {
		return baseChartList;
	}

	public void setBaseChartList(List<BaseChart> baseChartList) {
		this.baseChartList = baseChartList;
	}

}