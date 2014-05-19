package com.ebiz.bp_oracle.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.SysSettingDao;
import com.ebiz.bp_oracle.domain.SysSetting;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class SysSettingDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysSetting> implements SysSettingDao {

}
