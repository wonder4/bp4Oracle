package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.HelpContent;

public interface HelpContentService {

	Long createHelpContent(HelpContent t);

	int modifyHelpContent(HelpContent t);

	int removeHelpContent(HelpContent t);

	HelpContent getHelpContent(HelpContent t);

	List<HelpContent> getHelpContentList(HelpContent t);

	Long getHelpContentCount(HelpContent t);

	List<HelpContent> getHelpContentPaginatedList(HelpContent t);

}
