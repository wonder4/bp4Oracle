package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.ModPopedomDao;
import com.ebiz.bp_oracle.domain.ModPopedom;
import com.ebiz.bp_oracle.service.ModPopedomService;

@Service
public class ModPopedomServiceImpl implements ModPopedomService {

	@Resource
	private ModPopedomDao modPopedomDao;

	public Long createModPopedom(ModPopedom t) {
		this.modPopedomDao.deleteEntity(t);

		List<ModPopedom> modPopedomList = t.getModPopedomList();
		if (null != modPopedomList) {
			for (ModPopedom modPopedom : modPopedomList) {
				modPopedom.setUser_id(t.getUser_id());
				modPopedom.setRole_id(t.getRole_id());
				this.modPopedomDao.insertEntity(modPopedom);
			}
		}

		return 1L;
	}

	public int modifyModPopedom(ModPopedom t) {
		return this.modPopedomDao.updateEntity(t);
	}

	public int removeModPopedom(ModPopedom t) {
		return this.modPopedomDao.deleteEntity(t);
	}

	public ModPopedom getModPopedom(ModPopedom t) {
		return this.modPopedomDao.selectEntity(t);
	}

	public Long getModPopedomCount(ModPopedom t) {
		return this.modPopedomDao.selectEntityCount(t);
	}

	public List<ModPopedom> getModPopedomList(ModPopedom t) {
		return this.modPopedomDao.selectEntityList(t);
	}

	public List<ModPopedom> getModPopedomPaginatedList(ModPopedom t) {
		return this.modPopedomDao.selectEntityPaginatedList(t);
	}

}
