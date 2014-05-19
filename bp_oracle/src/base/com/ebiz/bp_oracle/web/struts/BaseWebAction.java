package com.ebiz.bp_oracle.web.struts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.ebiz.bp_oracle.domain.BaseProvince;
import com.ebiz.bp_oracle.domain.NewsInfo;
import com.ebiz.bp_oracle.domain.SysModule;
import com.ebiz.bp_oracle.web.util.ScriptUtils;
import com.ebiz.bp_oracle.web.util.StringHelper;

public class BaseWebAction extends BaseAction {

	/**
	 * @desc 首页 公用数据
	 */
	public void setPublicInfoList(HttpServletRequest request) {
		String par_code = request.getParameter("par_code");
		SysModule sysModulePar = new SysModule();
		sysModulePar.setMod_id(Long.valueOf(par_code));
		sysModulePar.setIs_del(0);
		sysModulePar = super.getFacade().getSysModuleService().getSysModule(sysModulePar);
		if (null != sysModulePar) {
			request.setAttribute("par_mode_name", sysModulePar.getMod_name().replaceAll("管理", ""));
		}
		SysModule sysModuleSon = new SysModule();
		sysModuleSon.setPar_id(Long.valueOf(par_code));
		sysModuleSon.setIs_del(0);
		List<SysModule> sysModuleSonList = super.getFacade().getSysModuleService().getSysModuleList(sysModuleSon);
		request.setAttribute("sysModuleSonList", sysModuleSonList);
	}

	/**
	 * @desc 首页公用数据,新闻信息
	 */
	public List<NewsInfo> getNewsInfoList(HttpServletRequest request, String mod_id, Integer count) {
		NewsInfo entity = new NewsInfo();
		// entity.setP_index(pIndex)
		entity.setMod_id(mod_id);
		entity.getMap().put("is_pub", "0");
		entity.getMap().put("no_invalid", "no_invalid_date");
		entity.setIs_del(0);
		entity.getRow().setCount(count);
		List<NewsInfo> newsInfoList = getFacade().getNewsInfoService().getNewsInfoList(entity);
		return newsInfoList;
	}

	/**
	 * 取图片新闻
	 * 
	 * @param count
	 * @param mod_id
	 */
	public void getNewsInfoImageList(HttpServletRequest request, String mod_id, Integer count) throws Exception {
		NewsInfo entity = new NewsInfo();
		entity.setIs_del(0);
		entity.getRow().setCount(count);
		entity.setInfo_state(3);
		entity.setMod_id(mod_id);
		entity.getMap().put("image_path", "true");
		List<NewsInfo> newsInfoList = super.getFacade().getNewsInfoService().getNewsInfoList(entity);
		request.setAttribute("imageListJsonString", ScriptUtils.getPptJsonString(newsInfoList, "image_path", "title",
				"uuid", "NewsInfo.do"));
	}

	/**
	 * @desc 获取省份列表
	 */
	public List<BaseProvince> getProvinceList() {
		BaseProvince province = new BaseProvince();
		province.setIs_del(0);
		province.setPar_index(0L);
		List<BaseProvince> provinceList = super.getFacade().getBaseProvinceService().getBaseProvinceList(province);
		return provinceList;
	}

	/**
	 * 前台导航
	 * 
	 * @param request
	 */
	public void setNaviStringToRequestScopeForHomePage(HttpServletRequest request) {
		String mod_id = request.getParameter("mod_code");
		if (StringUtils.isNotBlank(mod_id)) {
			String naviString = " ";
			if (StringUtils.isNotBlank(mod_id)) {
				SysModule sysModule = new SysModule();
				sysModule.setMod_id(new Long(mod_id));
				List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleParentList(sysModule);
				naviString = StringHelper.getNaviStringFromSysModuleList(sysModuleList);
			}
			request.setAttribute("naviString", naviString.replaceAll("管理", "").replaceAll("后台系统", "首页"));
		}
	}

	/**
	 * @desc 根据地区 p_index 获取所在省份
	 */
	public BaseProvince getProvinceAccordP_index(Long p_index) {
		BaseProvince baseProvince = new BaseProvince();
		baseProvince.setIs_del(0);
		baseProvince.setP_index(Long.valueOf(p_index.toString().substring(0, 2) + "0000"));
		baseProvince = super.getFacade().getBaseProvinceService().getBaseProvince(baseProvince);
		return baseProvince;
	}

}
