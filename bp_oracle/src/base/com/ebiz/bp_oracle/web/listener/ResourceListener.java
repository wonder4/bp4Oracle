package com.ebiz.bp_oracle.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ebiz.bp_oracle.service.Facade;

public class ResourceListener implements ServletContextListener {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void contextDestroyed(ServletContextEvent sec) {
		ServletContext application = sec.getServletContext();
		application.removeAttribute("c_n");
		application.removeAttribute("html_br");
		application.removeAttribute("sys_encoding");
		application.removeAttribute("_global_website_name");
		application.removeAttribute("_global_manager_name");
	}

	public void contextInitialized(ServletContextEvent sec) {
		ServletContext servletContext = sec.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		Facade facade = (Facade) wac.getBean("facade");

		initInternalResource(sec, facade);
	}

	public void initInternalResource(ServletContextEvent sec, Facade facade) {
		ServletContext application = sec.getServletContext();
		application.setAttribute("c_n", "\n");
		application.setAttribute("html_br", "<br />");
		application.setAttribute("sys_encoding", "UTF-8");
		application.setAttribute("_global_website_name", "基础框架");
		application.setAttribute("_global_manager_name", "基础框架");

		// // 省列表(省份)
		// BaseProvince baseProvince = new BaseProvince();
		// baseProvince.setIs_del(0);
		// List<BaseProvince> baseProvinceList = facade.getBaseProvinceService().getBaseProvinceList(baseProvince);
		// application.setAttribute(Keys.BASE_APPLICATION_PROVINCE_LIST, baseProvinceList);
		//
		// // 省列表
		// List<BaseProvince> onlyProvinceList = new ArrayList<BaseProvince>();
		// for (BaseProvince bpl : baseProvinceList) {
		// if (1 == bpl.getP_level().intValue()) {
		// onlyProvinceList.add(bpl);
		// }
		// }
		// application.setAttribute(Keys.BASE_PROVINCE_LIST_ONLY_PROVINCE, onlyProvinceList);
	}

}
