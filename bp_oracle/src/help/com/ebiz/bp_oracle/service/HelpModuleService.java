package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.HelpModule;

public interface HelpModuleService {

	Long createHelpModule(HelpModule t);

	int modifyHelpModule(HelpModule t);

	int removeHelpModule(HelpModule t);

	HelpModule getHelpModule(HelpModule t);

	List<HelpModule> getHelpModuleList(HelpModule t);

	Long getHelpModuleCount(HelpModule t);

	List<HelpModule> getHelpModulePaginatedList(HelpModule t);

	Long getHelpModuleWithLevelNumber(HelpModule t);

	List<HelpModule> getHelpModuleParentList(HelpModule t);

	HelpModule getHelpModuleForEqLevel2(HelpModule t);
}
