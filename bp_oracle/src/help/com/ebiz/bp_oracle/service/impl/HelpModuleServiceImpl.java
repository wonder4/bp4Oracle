package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.HelpInfoDao;
import com.ebiz.bp_oracle.dao.HelpModuleDao;
import com.ebiz.bp_oracle.domain.HelpInfo;
import com.ebiz.bp_oracle.domain.HelpModule;
import com.ebiz.bp_oracle.service.HelpModuleService;

@Service
public class HelpModuleServiceImpl implements HelpModuleService {

	@Resource
	private HelpModuleDao helpModuleDao;

	@Resource
	private HelpInfoDao helpInfoDao;

	public Long createHelpModule(HelpModule t) {
		return this.helpModuleDao.insertEntity(t);
	}

	public HelpModule getHelpModule(HelpModule t) {
		return this.helpModuleDao.selectEntity(t);
	}

	public Long getHelpModuleCount(HelpModule t) {
		return this.helpModuleDao.selectEntityCount(t);
	}

	public List<HelpModule> getHelpModuleList(HelpModule t) {
		return this.helpModuleDao.selectEntityList(t);
	}

	public int modifyHelpModule(HelpModule t) {
		return this.helpModuleDao.updateEntity(t);
	}

	public int removeHelpModule(HelpModule t) {
		int rows = this.helpModuleDao.deleteEntity(t);

		if (null != t.getH_mod_id()) {
			HelpModule helpModule = new HelpModule();
			helpModule.setPar_id(t.getH_mod_id());
			this.helpModuleDao.deleteEntity(helpModule);

			HelpInfo HelpInfo = new HelpInfo();
			HelpInfo.setIs_del(0);
			HelpInfo.setH_mod_id(t.getH_mod_id());
			helpInfoDao.deleteEntity(HelpInfo);
		}

		return rows;
	}

	public List<HelpModule> getHelpModulePaginatedList(HelpModule t) {
		return this.helpModuleDao.selectEntityPaginatedList(t);
	}

	public Long getHelpModuleWithLevelNumber(HelpModule t) {
		return this.helpModuleDao.selectHelpModuleWithLevelNumber(t);
	}

	public List<HelpModule> getHelpModuleParentList(HelpModule t) {
		return this.helpModuleDao.selectHelpModuleParentList(t);
	}

	public HelpModule getHelpModuleForEqLevel2(HelpModule t) {
		return this.helpModuleDao.selectHelpModuleForEqLevel2(t);
	}

}
