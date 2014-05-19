package com.ebiz.bp_oracle.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ebiz.bp_oracle.dao.PdContentDao;
import com.ebiz.bp_oracle.dao.PdImgsDao;
import com.ebiz.bp_oracle.dao.PdInfoCustomAttrContentDao;
import com.ebiz.bp_oracle.dao.PdInfoCustomFieldContentDao;
import com.ebiz.bp_oracle.dao.PdInfoDao;
import com.ebiz.bp_oracle.domain.PdContent;
import com.ebiz.bp_oracle.domain.PdImgs;
import com.ebiz.bp_oracle.domain.PdInfo;
import com.ebiz.bp_oracle.domain.PdInfoCustomAttrContent;
import com.ebiz.bp_oracle.domain.PdInfoCustomFieldContent;
import com.ebiz.bp_oracle.service.PdInfoService;

@Service
public class PdInfoServiceImpl implements PdInfoService {

	@Resource
	private PdInfoDao pdInfoDao;

	@Resource
	private PdImgsDao pdImgsDao;

	@Resource
	private PdContentDao pdContentDao;

	@Resource
	private PdInfoCustomFieldContentDao pdInfoCustomFieldContentDao;

	@Resource
	private PdInfoCustomAttrContentDao pdInfoCustomAttrContentDao;

	public Long createPdInfo(PdInfo t) {
		Long pd_id = this.pdInfoDao.insertEntity(t);
		// 插入产品轮播图
		List<PdImgs> list = t.getPdImgsList();
		if (list != null && list.size() > 0) {
			for (PdImgs pdImges : list) {
				pdImges.setPd_id(pd_id);
				this.pdImgsDao.insertEntity(pdImges);
			}
		}
		// 插入产品详细信息
		String pd_content = t.getPd_content();
		PdContent pdContent = new PdContent();
		pdContent.setType(0);
		pdContent.setPd_id(pd_id);
		pdContent.setContent(pd_content);
		this.pdContentDao.insertEntity(pdContent);

		List<PdInfoCustomFieldContent> zdyList = t.getPdInfoCustomFieldContentList();
		if (null != zdyList && zdyList.size() > 0) {
			for (PdInfoCustomFieldContent temp : zdyList) {
				temp.setPd_id(pd_id);
				this.pdInfoCustomFieldContentDao.insertEntity(temp);
			}
		}

		List<PdInfoCustomAttrContent> zdyAttrList = t.getPdInfoCustomAttrContentList();
		if (null != zdyAttrList && zdyAttrList.size() > 0) {
			for (PdInfoCustomAttrContent temp : zdyAttrList) {
				temp.setPd_id(pd_id);
				this.pdInfoCustomAttrContentDao.insertEntity(temp);
			}
		}

		return pd_id;
	}

	public PdInfo getPdInfo(PdInfo t) {
		return this.pdInfoDao.selectEntity(t);
	}

	public Long getPdInfoCount(PdInfo t) {
		return this.pdInfoDao.selectEntityCount(t);
	}

	public List<PdInfo> getPdInfoList(PdInfo t) {
		return this.pdInfoDao.selectEntityList(t);
	}

	public int modifyPdInfo(PdInfo t) {
		// 插入产品轮播图
		List<PdImgs> list = t.getPdImgsList();
		// 先删后增
		if (list != null && list.size() > 0) {
			PdImgs entity = new PdImgs();
			entity.setPd_id(t.getPd_id());
			this.pdImgsDao.deleteEntity(entity);

			for (PdImgs pdImges : list) {
				pdImges.setPd_id(t.getPd_id());
				this.pdImgsDao.insertEntity(pdImges);
			}
		}
		// 先删后增
		String update_content = (String) t.getMap().get("update_content");
		if (StringUtils.isNotBlank(update_content)) {
			PdContent pdContent = new PdContent();
			pdContent.setType(0);
			pdContent.setPd_id(t.getPd_id());
			pdContent = pdContentDao.selectEntity(pdContent);
			if (null != pdContent) {
				this.pdContentDao.deleteEntity(pdContent);
			}
			String pd_content = t.getPd_content();
			PdContent pdContent2 = new PdContent();
			pdContent2.setType(0);
			pdContent2.setPd_id(t.getPd_id());
			pdContent2.setContent(pd_content);
			this.pdContentDao.insertEntity(pdContent2);
		}
		String update_attr = (String) t.getMap().get("update_attr");
		if (StringUtils.isNotBlank(update_attr)) {
			// 先删后增
			PdInfoCustomFieldContent zdy_entity = new PdInfoCustomFieldContent();
			zdy_entity.setPd_id(t.getPd_id());
			this.pdInfoCustomFieldContentDao.deleteEntity(zdy_entity);

			PdInfoCustomAttrContent son_attr = new PdInfoCustomAttrContent();
			son_attr.setPd_id(t.getPd_id());
			this.pdInfoCustomAttrContentDao.deleteEntity(son_attr);

			List<PdInfoCustomFieldContent> zdyList = t.getPdInfoCustomFieldContentList();
			if (null != zdyList && zdyList.size() > 0) {
				for (PdInfoCustomFieldContent temp : zdyList) {
					temp.setPd_id(t.getPd_id());
					this.pdInfoCustomFieldContentDao.insertEntity(temp);
				}
			}

			List<PdInfoCustomAttrContent> zdyAttrList = t.getPdInfoCustomAttrContentList();
			if (null != zdyAttrList && zdyAttrList.size() > 0) {
				for (PdInfoCustomAttrContent temp : zdyAttrList) {
					temp.setPd_id(t.getPd_id());
					this.pdInfoCustomAttrContentDao.insertEntity(temp);
				}
			}
		}

		String delete_attr = (String) t.getMap().get("delete_attr");
		if (StringUtils.isNotBlank(delete_attr)) {
			PdInfoCustomFieldContent zdy_entity = new PdInfoCustomFieldContent();
			PdInfoCustomAttrContent son_attr = new PdInfoCustomAttrContent();
			String[] pks = (String[]) t.getMap().get("pks");
			if (null == pks) {
				Long pd_id = t.getPd_id();
				zdy_entity.setPd_id(pd_id);
				son_attr.setPd_id(pd_id);

			} else {
				zdy_entity.getMap().put("pks", pks);
				son_attr.getMap().put("pks", pks);
			}
			this.pdInfoCustomFieldContentDao.deleteEntity(zdy_entity);
			this.pdInfoCustomAttrContentDao.deleteEntity(son_attr);
		}

		return this.pdInfoDao.updateEntity(t);
	}

	public int removePdInfo(PdInfo t) {
		return this.pdInfoDao.deleteEntity(t);
	}

	public List<PdInfo> getPdInfoPaginatedList(PdInfo t) {
		return this.pdInfoDao.selectEntityPaginatedList(t);
	}

	// 热门搜索 Li,Ka 2012-3-31
	public List<PdInfo> getPdInfoForHotSeacherList(PdInfo t) {
		return this.pdInfoDao.selectPdInfoForHotSeacherList(t);
	}

	/**
	 * @desc 根据属性组合查询
	 */
	public Long getPdInfoForSearchAttrCount(PdInfo t) {
		return this.pdInfoDao.selectPdInfoForSearchAttrCount(t);
	}

	/**
	 * @desc 根据属性组合查询分页
	 */
	public List<PdInfo> getPdInfoForSearchAttrPaginatedList(PdInfo t) {
		return this.pdInfoDao.selectPdInfoForSearchAttrPaginatedList(t);
	}

}
