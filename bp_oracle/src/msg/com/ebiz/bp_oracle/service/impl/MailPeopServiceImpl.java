package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.MailPeopDao;
import com.ebiz.bp_oracle.domain.MailPeop;
import com.ebiz.bp_oracle.service.MailPeopService;

/**
 * Code by the CodeGenerator(Version 2.2)
 * 
 * @author Hui,Gang (mr.huig [at] gmail.com)
 * @datetime 2012-08-28 14:33:00
 */
@Service
public class MailPeopServiceImpl implements MailPeopService {

	@Resource
	private MailPeopDao mailPeopDao;

	public Long createMailPeop(MailPeop t) {
		return this.mailPeopDao.insertEntity(t);
	}

	public MailPeop getMailPeop(MailPeop t) {
		return this.mailPeopDao.selectEntity(t);
	}

	public Long getMailPeopCount(MailPeop t) {
		return this.mailPeopDao.selectEntityCount(t);
	}

	public List<MailPeop> getMailPeopList(MailPeop t) {
		return this.mailPeopDao.selectEntityList(t);
	}

	public int modifyMailPeop(MailPeop t) {
		return this.mailPeopDao.updateEntity(t);
	}

	public int removeMailPeop(MailPeop t) {
		return this.mailPeopDao.deleteEntity(t);
	}

	public List<MailPeop> getMailPeopPaginatedList(MailPeop t) {
		return this.mailPeopDao.selectEntityPaginatedList(t);
	}

}