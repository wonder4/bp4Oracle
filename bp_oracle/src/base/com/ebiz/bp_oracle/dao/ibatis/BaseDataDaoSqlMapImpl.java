package com.ebiz.bp_oracle.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.BaseDataDao;
import com.ebiz.bp_oracle.domain.BaseData;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class BaseDataDaoSqlMapImpl extends EntityDaoSqlMapImpl<BaseData> implements BaseDataDao {

}
