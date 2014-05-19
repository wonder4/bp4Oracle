package com.ebiz.bp_oracle.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.bp_oracle.domain.HelpModule;
import com.ebiz.ssi.dao.EntityDao;

public interface HelpModuleDao extends EntityDao<HelpModule> {

	Long selectHelpModuleWithLevelNumber(HelpModule t) throws DataAccessException;

	List<HelpModule> selectHelpModuleParentList(HelpModule t) throws DataAccessException;

	HelpModule selectHelpModuleForEqLevel2(HelpModule t) throws DataAccessException;
}
