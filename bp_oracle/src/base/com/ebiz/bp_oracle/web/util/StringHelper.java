package com.ebiz.bp_oracle.web.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ebiz.bp_oracle.domain.BaseClass;
import com.ebiz.bp_oracle.domain.BaseProvince;
import com.ebiz.bp_oracle.domain.DeptInfo;
import com.ebiz.bp_oracle.domain.HelpModule;
import com.ebiz.bp_oracle.domain.SysModule;
import com.ebiz.bp_oracle.service.Facade;

public class StringHelper {

	/**
	 * @param list list of object SysModule
	 * @return String
	 */
	public static String getTreeNodesFromSysModuleList(List<SysModule> sysModuleList) {

		StringBuffer sb = new StringBuffer();

		for (SysModule sysModule : sysModuleList) {
			String _mod_id = String.valueOf(sysModule.getMod_id());
			String _par_id = String.valueOf(sysModule.getPar_id());
			if ("0".equals(_par_id)) {
				_par_id = "-1";
			}
			String _text = StringUtils.replace(sysModule.getMod_name(), ":", "&#58;");
			if (StringUtils.isEmpty(_text)) {
				continue;
			}
			String _hint = _text;
			String _url = StringUtils.replace(sysModule.getMod_url(), ":", "&#58;");

			sb.append("\ntree.nodes[\"").append(_par_id).append("_").append(_mod_id).append("\"]=\"");
			sb.append("text:").append(_text).append(";");
			if (_hint.length() > 0) {
				sb.append("hint:").append(_hint).append(";");
			}
			if ((_url != null) && (_url.length() > 0)) {
				sb.append("url:").append(_url).append(";");
			} else {
				sb.append("url:").append("Frames.do?method=main").append(";");
			}

			sb.append("data:").append("mod_id=").append(_mod_id).append(";");

			sb.append("\";");
		}
		return sb.toString();
	}

	public static String getjQzTreeNodesFromSysModuleList(List<SysModule> sysModuleList) {

		StringBuffer sb = new StringBuffer("[");
		String target = "mainFrame";
		for (SysModule sysModule : sysModuleList) {
			String mod_id = String.valueOf(sysModule.getMod_id());
			String par_id = String.valueOf(sysModule.getPar_id());
			String mod_url = sysModule.getMod_url();
			String mod_name = sysModule.getMod_name();
			if (StringUtils.isBlank(mod_url)) {
				mod_url = "Frames.do?method=main";
			}
			if (!"0".equals(par_id)) {
				// { mod_id:1, par_id:0, name:"基本功能演示", "url":"", "target":"mainFrame"},
				sb.append("{");
				sb.append("\"mod_id\":").append(mod_id).append(",");
				sb.append("\"par_id\":").append(par_id).append(",");
				sb.append("\"name\":").append("\"").append(mod_name).append("\"").append(",");
				String linkUrl = "?";
				if (StringUtils.contains(mod_url, linkUrl)) {
					linkUrl = "&";
				}
				sb.append("\"url\":").append("\"").append(mod_url).append(linkUrl).append("mod_id=").append(mod_id)
						.append("\"").append(",");
				sb.append("\"target\":").append("\"").append(target).append("\"");
				sb.append("},");
			}
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.lastIndexOf(","));
		}
		sb.append("];");
		return sb.toString();
	}

	public static String getNaviStringFromSysModuleList(List<SysModule> sysModuleList) {
		return getNaviStringFromSysModuleList(sysModuleList, " -&gt; ");
	}

	public static String getNaviStringFromSysModuleList(List<SysModule> sysModuleList, String separator) {
		ArrayList<String> arrayList = new ArrayList<String>();
		for (SysModule sysModule : sysModuleList) {
			arrayList.add(sysModule.getMod_name());
		}
		return (StringUtils.join(arrayList, separator));
	}

	public static String getAreaStringFromBaseProvince(List<BaseProvince> baseProvinceList) {
		return getAreaStringFromBaseProvince(baseProvinceList, "");
	}

	public static String getAreaStringFromBaseProvince(List<BaseProvince> baseProvinceList, String separator) {
		ArrayList<String> arrayList = new ArrayList<String>();
		for (BaseProvince baseProvince : baseProvinceList) {
			if (!"0".equals(String.valueOf(baseProvince.getP_index()))) {
				arrayList.add(baseProvince.getP_name());
			}
		}
		return (StringUtils.join(arrayList, separator));
	}

	/**
	 * @param pd_class_type -1 普通产品 -2便民产品 -3 农畜产品
	 * @param isCanSelectedAll true都可以选择，false如果是锁定的选项不让选择
	 * @return String
	 */
	public static String getTreeNodesFromBaseClassList(Facade facade, Integer cls_scope, String pd_class_type,
			boolean isCanSelectedAll) {
		StringBuffer sb = new StringBuffer("[");
		String target = "_self";
		BaseClass baseClass = new BaseClass();
		baseClass.setIs_del(0);// par_id
		baseClass.setCls_scope(cls_scope);
		baseClass.getMap().put("par_id", pd_class_type);
		List<BaseClass> baseClassList = facade.getBaseClassService().getBaseClassSonList(baseClass);
		for (BaseClass entity : baseClassList) {
			String cls_id = String.valueOf(entity.getCls_id());
			String par_id = String.valueOf(entity.getPar_id());
			String mod_url = "#";
			String cls_name = StringUtils.replace((entity.getCls_name()).trim(), "\r\n", "");
			if (!pd_class_type.equals(par_id)) {
				// { cls_id:1, par_id:0, name:"基本功能演示", "url":"", "target":"mainFrame"},
				sb.append("{");
				sb.append("\"cls_id\":").append(cls_id).append(",");
				sb.append("\"par_id\":").append(par_id).append(",");
				sb.append("\"name\":").append("\"").append(cls_name).append("\"").append(",");
				sb.append("\"is_lock\":").append("\"").append(entity.getIs_lock()).append("\"").append(",");
				if (entity.getIs_lock().intValue() == 1 && !isCanSelectedAll) {
					sb.append("\"nocheck\":").append(true).append(",");
				}
				sb.append("\"url\":").append("\"").append(mod_url).append("\"").append(",");
				sb.append("\"target\":").append("\"").append(target).append("\"");
				sb.append("},");
			}
		}

		if (sb.length() > 1) {
			sb.deleteCharAt(sb.lastIndexOf(","));
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * ********************帮助中心 Begin************************
	 */

	public static String getTreeNodesForHelpModule(List<HelpModule> sysModuleList) {
		StringBuffer sb = new StringBuffer();

		for (HelpModule sysModule : sysModuleList) {
			String _mod_id = String.valueOf(sysModule.getH_mod_id());
			String _par_id = String.valueOf(sysModule.getPar_id());
			if ("0".equals(_par_id)) {
				_par_id = "-1";
			}
			String _text = StringUtils.replace(sysModule.getMod_name(), ":", "&#58;");
			if (StringUtils.isEmpty(_text)) {
				continue;
			}
			String _hint = _text;
			String _url = StringUtils.replace(sysModule.getMod_url(), ":", "&#58;");

			sb.append("\ntree.nodes[\"").append(_par_id).append("_").append(_mod_id).append("\"]=\"");
			sb.append("text:").append(_text).append(";");
			if (_hint.length() > 0) {
				sb.append("hint:").append(_hint).append(";");
			}
			if ((_url != null) && (_url.length() > 0)) {
				sb.append("url:").append(_url).append(";");
			} else {
				sb.append("url:").append("HelpInfo.do?method=helpMain").append(";");
			}

			sb.append("data:").append("h_mod_id=").append(_mod_id).append(";");
			sb.append("\";");
		}
		return sb.toString();

	}

	public static String getTreeNodesFromHelpModuleList(List<HelpModule> helpModuleList) {

		StringBuffer sb = new StringBuffer();

		for (HelpModule helpModule : helpModuleList) {

			String _id = String.valueOf(helpModule.getH_mod_id());
			String _par_id = String.valueOf(helpModule.getPar_id());

			if ("0".equals(_par_id)) {
				_par_id = "-1";
			}
			String _text = StringUtils.replace(helpModule.getMod_name(), ":", "&#58;");
			if (StringUtils.isEmpty(_text)) {
				continue;
			}
			String _hint = _text;
			// String _url = StringUtils.replace(sysModule.getMod_url(), ":",
			// "&#58;");

			sb.append("\ntree.nodes[\"").append(_par_id).append("_").append(_id).append("\"]=\"");
			sb.append("text:").append(_text).append(";");
			if (_hint.length() > 0) {
				sb.append("hint:").append(_hint).append(";");
			}

			sb.append("url:").append("HelpModule.do").append(";");

			sb.append("data:").append("method=edit");
			sb.append("&par_id=").append(_par_id);
			sb.append("&h_mod_id=").append(_id).append(";");

			sb.append("\";");
		}
		return sb.toString();
	}

	public static String getNaviStringForHelpModule(List<HelpModule> helpModuleList) {
		return getNaviStringForHelpModule(helpModuleList, " &gt; ");
	}

	public static String getNaviStringForHelpModule(List<HelpModule> helpModuleList, String separator) {
		ArrayList<String> modNameList = new ArrayList<String>();
		for (HelpModule helpModule : helpModuleList) {
			modNameList.add(helpModule.getMod_name());
		}
		return (StringUtils.join(modNameList, separator));
	}

	/**
	 * ********************帮助中心 End************************
	 */

	/**
	 * ********************部门信息 Begin************************
	 */

	public static String getTreeNodesFromDeptInfoList(List<DeptInfo> deptInfoList) {

		StringBuffer sb = new StringBuffer();
		for (DeptInfo deptInfo : deptInfoList) {
			String _id = String.valueOf(deptInfo.getDept_id());
			String _par_id = String.valueOf(deptInfo.getPar_id());
			if ("0".equals(_par_id)) {
				_par_id = "-1";
			}
			String _text = StringUtils.replace(deptInfo.getDept_name(), ":", "&#58;");
			if (StringUtils.isEmpty(_text)) {
				continue;
			}
			String _hint = _text;
			// String _url = StringUtils.replace(sysModule.getMod_url(), ":",
			// "&#58;");

			sb.append("\ntree.nodes[\"").append(_par_id).append("_").append(_id).append("\"]=\"");
			sb.append("text:").append(_text).append(";");
			if (_hint.length() > 0) {
				sb.append("hint:").append(_hint).append(";");
			}

			sb.append("url:").append("DeptInfo.do").append(";");

			sb.append("data:").append("method=edit");
			sb.append("&par_id=").append(_par_id);
			sb.append("&dept_id=").append(_id).append(";");

			sb.append("\";");
		}
		return sb.toString();
	}

	public static String getjQzTreeNodesFromDeptInfoList(List<DeptInfo> deptInfoList, String dept_id) {

		StringBuffer sb = new StringBuffer("[");
		String target = "_self";
		for (DeptInfo entity : deptInfoList) {
			String cls_id = String.valueOf(entity.getDept_id());
			String par_id = String.valueOf(entity.getPar_id());
			String mod_url = "#";
			String cls_name = StringUtils.replace((entity.getDept_name()).trim(), "\r\n", "");
			// { dept_id:1, par_id:0, name:"基本功能演示", "url":"", "target":"mainFrame"},
			sb.append("{");
			sb.append("\"dept_id\":").append(cls_id).append(",");
			sb.append("\"par_id\":").append(par_id).append(",");
			sb.append("\"name\":").append("\"").append(cls_name).append("\"").append(",");
			sb.append("\"url\":").append("\"").append(mod_url).append("\"").append(",");
			sb.append("\"target\":").append("\"").append(target).append("\"").append(",");
			if (StringUtils.isNotBlank(dept_id) && StringUtils.contains(cls_id, dept_id)) { // by wuxs
				sb.append("\"checked\":").append("\"true\"");
			} else {
				sb.deleteCharAt(sb.lastIndexOf(","));
			}
			sb.append("},");
		}

		if (sb.length() > 1) {
			sb.deleteCharAt(sb.lastIndexOf(","));
		}
		sb.append("]");
		return sb.toString();

	}

	public static String getNaviStringForDeptInfo(List<DeptInfo> helpModuleList) {
		return getNaviStringForDeptInfo(helpModuleList, " &gt; ");
	}

	public static String getNaviStringForDeptInfo(List<DeptInfo> helpModuleList, String separator) {
		ArrayList<String> modNameList = new ArrayList<String>();
		for (DeptInfo helpModule : helpModuleList) {
			modNameList.add(helpModule.getDept_name());
		}
		return (StringUtils.join(modNameList, separator));
	}
	/**
	 * ********************部门信息 End************************
	 */
}
