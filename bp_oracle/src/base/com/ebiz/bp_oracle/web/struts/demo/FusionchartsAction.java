package com.ebiz.bp_oracle.web.struts.demo;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.bp_oracle.web.struts.BaseWebAction;

public class FusionchartsAction extends BaseWebAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("list");
	}

	public ActionForward toComplex(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ActionForward("/Fusioncharts/complex.jsp");
	}

	public ActionForward MSColumn3D(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String method = (String) dynaBean.get("method");
		chart1(form, request, response, method);
		return null;
	}

	public ActionForward Column3D(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String method = (String) dynaBean.get("method");
		chart1(form, request, response, method);
		return null;
	}

	public ActionForward MSColumn2D(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String method = (String) dynaBean.get("method");
		chart1(form, request, response, method);
		return null;
	}

	public ActionForward Column2D(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String method = (String) dynaBean.get("method");
		chart1(form, request, response, method);
		return null;
	}

	public ActionForward MSLine(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String method = (String) dynaBean.get("method");
		chart1(form, request, response, method);
		return null;
	}

	public ActionForward Line(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String method = (String) dynaBean.get("method");
		chart1(form, request, response, method);
		return null;
	}

	public ActionForward MSArea(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String method = (String) dynaBean.get("method");
		chart1(form, request, response, method);
		return null;
	}

	public ActionForward Area(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String method = (String) dynaBean.get("method");
		chart1(form, request, response, method);
		return null;
	}

	public ActionForward Pie3D(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String method = (String) dynaBean.get("method");
		chart1(form, request, response, method);
		return null;
	}

	public ActionForward Pie2D(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String method = (String) dynaBean.get("method");
		chart1(form, request, response, method);
		return null;
	}

	public ActionForward Doughnut3D(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String method = (String) dynaBean.get("method");
		chart1(form, request, response, method);
		return null;
	}

	public ActionForward Doughnut2D(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String method = (String) dynaBean.get("method");
		chart1(form, request, response, method);
		return null;
	}

	public ActionForward AngularGauge(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String method = (String) dynaBean.get("method");
		chart1(form, request, response, method);
		return null;
	}

	public ActionForward AngularGauge1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String method = (String) dynaBean.get("method");
		chart1(form, request, response, method);
		return null;
	}

	public ActionForward MSCombi3D(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String method = (String) dynaBean.get("method");
		chart1(form, request, response, method);
		return null;
	}

	public ActionForward ScrollCombi2D(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String method = (String) dynaBean.get("method");
		chart1(form, request, response, method);
		return null;
	}

	public ActionForward ZoomLine(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String method = (String) dynaBean.get("method");
		chart1(form, request, response, method);
		return null;
	}

	public ActionForward Bubble(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String method = (String) dynaBean.get("method");
		chart1(form, request, response, method);
		return null;
	}

	public ActionForward Scatter(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String method = (String) dynaBean.get("method");
		chart1(form, request, response, method);
		return null;
	}

	public void chart1(ActionForm form, HttpServletRequest request, HttpServletResponse response, String chartType) {
		DynaBean dynaBean = (DynaBean) form;

		String method = (String) dynaBean.get("method");
		String id = (String) dynaBean.get("id" + method);
		logger.info("======id{}:{}", method, id);
		logger.info("======fusionchart chart method:{}", method);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", (id));
		model.put("exportFileName", method);
		model.put("ctx", super.getCtxPath(request));
		String xmlData = getFacade().getTemplateService().getContent("chart/staticXml/" + chartType + ".ftl", model);
		renderXmlWithEncoding(response, xmlData, "GBK");
	}

}
