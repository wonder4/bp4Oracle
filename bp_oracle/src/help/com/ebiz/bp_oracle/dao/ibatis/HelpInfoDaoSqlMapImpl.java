package com.ebiz.bp_oracle.dao.ibatis;

import org.springframework.stereotype.Repository;

import com.ebiz.bp_oracle.dao.HelpInfoDao;
import com.ebiz.bp_oracle.domain.HelpInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Repository
public class HelpInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<HelpInfo> implements HelpInfoDao {

}
