package com.ebiz.bp_oracle.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.UserInfoDao;
import com.ebiz.bp_oracle.domain.UserInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class UserInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<UserInfo> implements UserInfoDao {

}
