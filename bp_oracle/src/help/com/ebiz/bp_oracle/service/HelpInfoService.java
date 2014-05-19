package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.HelpInfo;

public interface HelpInfoService {

	Long createHelpInfo(HelpInfo t);

	int modifyHelpInfo(HelpInfo t);

	int removeHelpInfo(HelpInfo t);

	HelpInfo getHelpInfo(HelpInfo t);

	List<HelpInfo> getHelpInfoList(HelpInfo t);

	Long getHelpInfoCount(HelpInfo t);

	List<HelpInfo> getHelpInfoPaginatedList(HelpInfo t);

}
