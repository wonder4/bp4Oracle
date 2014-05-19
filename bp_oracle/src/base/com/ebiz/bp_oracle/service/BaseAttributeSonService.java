package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.BaseAttributeSon;

public interface BaseAttributeSonService {

	Long createBaseAttributeSon(BaseAttributeSon t);

	int modifyBaseAttributeSon(BaseAttributeSon t);

	int removeBaseAttributeSon(BaseAttributeSon t);

	BaseAttributeSon getBaseAttributeSon(BaseAttributeSon t);

	List<BaseAttributeSon> getBaseAttributeSonList(BaseAttributeSon t);

	Long getBaseAttributeSonCount(BaseAttributeSon t);

	List<BaseAttributeSon> getBaseAttributeSonPaginatedList(BaseAttributeSon t);

}