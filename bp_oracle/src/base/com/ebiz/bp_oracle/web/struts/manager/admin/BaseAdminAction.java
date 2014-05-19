package com.ebiz.bp_oracle.web.struts.manager.admin;

import java.beans.PropertyDescriptor;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.lang.StringUtils;

import com.ebiz.bp_oracle.web.struts.BaseAction;

public abstract class BaseAdminAction extends BaseAction {

	/**
	 * @author Hui,Gang
	 * @version Build 2009.12.11
	 */
	public void encodeCharacterForGetMethod(Object object, HttpServletRequest request) throws Exception {
		if (!StringUtils.equalsIgnoreCase(request.getMethod(), "GET")) {
			return;
		}

		if (object instanceof DynaBean) {
			DynaBean dynaBean = (DynaBean) object;
			DynaProperty origDescriptors[] = dynaBean.getDynaClass().getDynaProperties();
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				if (getBeanUtilsBean().getPropertyUtils().isWriteable(dynaBean, name)) {
					Object value = dynaBean.get(name);
					if (value instanceof String) {
						getBeanUtilsBean().copyProperty(dynaBean, name, URLDecoder.decode(value.toString(), "UTF-8"));
					}
				}
			}
		} else {// is a standard JavaBean
			PropertyDescriptor origDescriptors[] = getBeanUtilsBean().getPropertyUtils().getPropertyDescriptors(object);
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				if ("class".equals(name)) {
					continue; // No point in trying to set an object's class
				}
				if (getBeanUtilsBean().getPropertyUtils().isReadable(object, name)
						&& getBeanUtilsBean().getPropertyUtils().isWriteable(object, name)) {
					Object value = getBeanUtilsBean().getPropertyUtils().getSimpleProperty(object, name);
					if (value instanceof String) {
						getBeanUtilsBean().copyProperty(object, name, URLDecoder.decode(value.toString(), "UTF-8"));
					}
				}
			}
		}
	}

	/**
	 * @author Hui,Gang
	 * @version Build 2009.12.11
	 */
	public BeanUtilsBean getBeanUtilsBean() {
		return BeanUtilsBean.getInstance();
	}

}