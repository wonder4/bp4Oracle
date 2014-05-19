package com.ebiz.bp_oracle.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.BaseBrandInfoDao;
import com.ebiz.bp_oracle.domain.BaseBrandInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class BaseBrandInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<BaseBrandInfo> implements BaseBrandInfoDao {

}
