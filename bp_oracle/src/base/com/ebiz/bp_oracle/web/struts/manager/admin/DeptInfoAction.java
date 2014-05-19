package com.ebiz.bp_oracle.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.bp_oracle.domain.DeptInfo;
import com.ebiz.bp_oracle.web.util.StringHelper;

public class DeptInfoAction extends BaseAdminAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("list");
	}

	public ActionForward showDeptInfoTree(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<DeptInfo> deptInfoList = getFacade().getDeptInfoService().getDeptInfoList(new DeptInfo());

		String treeNodes = StringHelper.getTreeNodesFromDeptInfoList(deptInfoList);

		request.setAttribute("treeNodes", treeNodes);

		return new ActionForward("/../view/jsp/manager/admin/DeptInfo/deptInfoTree.jsp");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");

		if (StringUtils.isNotBlank(dept_id)) {
			DeptInfo DeptInfo = new DeptInfo();
			if (StringUtils.isNotBlank(dept_id)) {
				DeptInfo.setDept_id(Long.valueOf(dept_id));
				String level_4 = StringUtils.substring(dept_id, 6, 8);
				if (StringUtils.isNotBlank(level_4) && Long.valueOf(level_4) > 0) {
					dynaBean.set("level_4", "true");
				}
			}
			DeptInfo entity = getFacade().getDeptInfoService().getDeptInfo(DeptInfo);
			super.copyProperties(form, entity);
		} else {
			super.saveMessage(request, "entity.missing");
		}

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");

		DeptInfo entity = new DeptInfo();
		super.copyProperties(entity, dynaBean);

		String save_level_1 = "00";
		String save_level_2 = "00";
		String save_level_3 = "00";
		String save_level_4 = "00";

		String save_level_mod_id = "";
		String msg = "success";
		Long level_number_99 = 0l;
		if (StringUtils.isNotBlank(dept_id) && null == entity.getId()) {

			save_level_mod_id = setHmodID(dept_id, save_level_1, save_level_2, save_level_3, save_level_4)[0];
			level_number_99 = Long
					.valueOf(setHmodID(dept_id, save_level_1, save_level_2, save_level_3, save_level_4)[1]);

			if (level_number_99 > 99) {
				String msg_99 = "最大支持子节点的数目为99";
				super.renderJavaScript(response, "alert('" + msg_99 + "');history.back();");
				return null;
			}

			entity.setDept_id(Long.valueOf(save_level_mod_id));

			entity.setPar_id(new Long(dept_id));
			getFacade().getDeptInfoService().createDeptInfo(entity);
			msg = getMessage(request, "entity.inserted");
		} else {
			getFacade().getDeptInfoService().modifyDeptInfo(entity);
			msg = getMessage(request, "entity.updated");
		}
		super.renderJavaScript(response, "alert('" + msg + "');window.parent.frames[0].location.reload(true);");
		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String dept_id = (String) dynaBean.get("dept_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			DeptInfo entity = new DeptInfo();
			entity.setId(new Long(id));
			entity.setDept_id(Long.valueOf(dept_id));
			getFacade().getDeptInfoService().removeDeptInfo(entity);
		}

		super.renderJavaScript(response, "alert(\"" + super.getMessage(request, "entity.deleted")
				+ "\");window.parent.frames[0].location.reload(true);");
		return null;
	}

	private String[] setHmodID(String dept_id, String save_level_1, String save_level_2, String save_level_3,
			String save_level_4) {
		String[] returnData = new String[2];
		Long level_number_99 = 10l;
		if ("1".equals(dept_id)) {
			DeptInfo deptInfo_level = new DeptInfo();
			deptInfo_level.setPar_id(Long.valueOf(1));
			deptInfo_level.getMap().put("start_index", "1");
			deptInfo_level.getMap().put("setp", "2");
			Long level_number = getFacade().getDeptInfoService().getDeptInfoWithLevelNumber(deptInfo_level);
			if (null != level_number) {
				level_number_99 = level_number;
				save_level_1 = String.valueOf(level_number);
				save_level_1 = StringUtils.rightPad(save_level_1, 2, "0");
			}
		} else {
			String level_1 = StringUtils.substring(dept_id, 0, 2);
			String level_2 = StringUtils.substring(dept_id, 2, 4);
			String level_3 = StringUtils.substring(dept_id, 4, 6);
			String level_4 = StringUtils.substring(dept_id, 6, 8);
			save_level_1 = level_1;
			save_level_2 = level_2;
			save_level_3 = level_3;
			save_level_4 = level_4;

			if (Long.valueOf(level_1) > 0) {
				save_level_1 = level_1;
			}
			if (Long.valueOf(level_2) == 0) {
				DeptInfo deptInfo_level = new DeptInfo();
				deptInfo_level.setPar_id(Long.valueOf(dept_id));
				deptInfo_level.getMap().put("start_index", "3");
				deptInfo_level.getMap().put("setp", "2");
				Long level_number = getFacade().getDeptInfoService().getDeptInfoWithLevelNumber(deptInfo_level);
				if (null != level_number) {
					level_number_99 = level_number;
					save_level_2 = String.valueOf(level_number);
					save_level_2 = StringUtils.leftPad(save_level_2, 2, "0");

				}
			} else if (Long.valueOf(level_3) == 0) {
				DeptInfo deptInfo_level = new DeptInfo();
				deptInfo_level.setPar_id(Long.valueOf(dept_id));
				deptInfo_level.getMap().put("start_index", "5");
				deptInfo_level.getMap().put("setp", "2");
				Long level_number = getFacade().getDeptInfoService().getDeptInfoWithLevelNumber(deptInfo_level);
				if (null != level_number) {
					level_number_99 = level_number;
					save_level_3 = String.valueOf(level_number);
					save_level_3 = StringUtils.leftPad(save_level_3, 2, "0");
				}
			} else if (Long.valueOf(level_4) == 0) {
				DeptInfo deptInfo_level = new DeptInfo();
				deptInfo_level.setPar_id(Long.valueOf(dept_id));
				deptInfo_level.getMap().put("start_index", "7");
				deptInfo_level.getMap().put("setp", "2");
				Long level_number = getFacade().getDeptInfoService().getDeptInfoWithLevelNumber(deptInfo_level);
				if (null != level_number) {
					level_number_99 = level_number;
					save_level_4 = String.valueOf(level_number);
					save_level_4 = StringUtils.leftPad(save_level_4, 2, "0");
				}
			}
		}
		returnData[0] = save_level_1 + save_level_2 + save_level_3 + save_level_4;
		returnData[1] = String.valueOf(level_number_99);
		return returnData;
	}
}
