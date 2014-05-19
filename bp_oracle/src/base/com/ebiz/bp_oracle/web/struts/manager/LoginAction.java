package com.ebiz.bp_oracle.web.struts.manager;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.util.CookieGenerator;
import org.springframework.web.util.WebUtils;

import com.ebiz.bp_oracle.domain.UserInfo;
import com.ebiz.bp_oracle.web.Keys;
import com.ebiz.bp_oracle.web.struts.BaseAction;
import com.ebiz.bp_oracle.web.util.EncryptUtilsV2;

public class LoginAction extends BaseAction {
	private static final String DEFAULT_PASSWORD = "......";

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.showLoginForm(mapping, form, request, response);
	}

	public ActionForward showLoginForm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setAttribute("isEnabledCode", super.getSysSetting("isEnabledCode"));
		setCookies2RequestScope(request);
		return mapping.findForward("login");
	}

	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean lazyForm = (DynaBean) form;

		String login_name = (String) lazyForm.get("login_name");
		String password = (String) lazyForm.get("password");
		String verificationCode = (String) lazyForm.get("verificationCode");
		String is_remember = (String) lazyForm.get("is_remember");

		String msg = null;
		if (StringUtils.isBlank(login_name)) {
			msg = super.getMessage(request, "login.failed.username.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}

		if (StringUtils.isBlank(password)) {
			msg = super.getMessage(request, "login.failed.password.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}

		HttpSession session = request.getSession();
		if ("1".equals(super.getSysSetting("isEnabledCode"))) {
			if (StringUtils.isBlank(verificationCode)) {
				msg = super.getMessage(request, "login.failed.verificationCode.isEmpty");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg
						+ "');location.href='login.do'}");
				return null;
			}

			if (!verificationCode.equalsIgnoreCase((String) session.getAttribute("verificationCode"))) {
				msg = super.getMessage(request, "login.failed.verificationCode.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg
						+ "');location.href='login.do'}");
				return null;
			}
		}

		login_name = login_name.trim();
		UserInfo entity = new UserInfo();
		entity.setUser_name(login_name);
		entity.setIs_del(0);
		entity.getRow().setCount(10);
		List<UserInfo> userInfoList = getFacade().getUserInfoService().getUserInfoList(entity);
		if (null == userInfoList || userInfoList.size() == 0) {
			msg = super.getMessage(request, "login.failed.username.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		} else if (userInfoList.size() > 1) {
			msg = super.getMessage(request, "login.failed.username.repeat");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}

		Cookie passwordCookie = WebUtils.getCookie(request, "password");
		if (null != passwordCookie && DEFAULT_PASSWORD.equals(password)) {
			entity.setPassword(passwordCookie.getValue());
		} else {
			entity.setPassword(EncryptUtilsV2.MD5Encode(password));
		}
		UserInfo userInfo = getFacade().getUserInfoService().getUserInfo(entity);
		if (null == userInfo) {
			msg = super.getMessage(request, "login.failed.password.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		} else {
			CookieGenerator cg = new CookieGenerator();
			if (is_remember != null) {
				cg.setCookieMaxAge(60 * 60 * 24 * 14);
				cg.setCookieName("login_name");
				cg.addCookie(response, URLEncoder.encode(login_name, "UTF-8"));
				cg.setCookieName("password");
				cg.addCookie(response, URLEncoder.encode(entity.getPassword(), "UTF-8"));
				cg.setCookieName("is_remember");
				cg.addCookie(response, URLEncoder.encode(is_remember, "UTF-8"));
			} else {
				cg.setCookieMaxAge(0);
				cg.setCookieName("login_name");
				cg.removeCookie(response);
				cg.setCookieName("password");
				cg.removeCookie(response);
				cg.setCookieName("is_remember");
				cg.removeCookie(response);
			}

			// update login count
			UserInfo ui = new UserInfo();
			ui.setId(userInfo.getId());
			ui.setLogin_count(userInfo.getLogin_count() + 1);
			ui.setLast_login_time(new Date());
			ui.setLast_login_ip(this.getIpAddr(request));
			ui.setLogin_count(userInfo.getLogin_count() + 1);
			ui.setLast_login_time(ui.getLast_login_time());
			ui.setLast_login_ip(ui.getLast_login_ip());
			getFacade().getUserInfoService().modifyUserInfo(ui);

			session.setAttribute(Keys.SESSION_USERINFO_KEY, userInfo);

			return mapping.findForward("success");

		}
	}

	public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(false);
		if (null != session) {
			session.removeAttribute(Keys.SESSION_USERINFO_KEY);
			session.invalidate();
		}
		request.setAttribute("isEnabledCode", super.getSysSetting("isEnabledCode"));
		setCookies2RequestScope(request);
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		int cur_year = now.get(Calendar.YEAR);
		request.setAttribute("cur_year", String.valueOf(cur_year));
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("year", String.valueOf(cur_year));
		return mapping.findForward("login");
	}

	private void setCookies2RequestScope(HttpServletRequest request) throws Exception {
		Cookie login_name = WebUtils.getCookie(request, "login_name");
		Cookie password = WebUtils.getCookie(request, "password");
		Cookie is_remember = WebUtils.getCookie(request, "is_remember");

		if (null != login_name) {
			request.setAttribute("login_name", URLDecoder.decode(login_name.getValue(), "UTF-8"));
		}
		if (null != password) {
			request.setAttribute("password", DEFAULT_PASSWORD);
		}
		if (null != is_remember) {
			request.setAttribute("is_remember", URLDecoder.decode(is_remember.getValue(), "UTF-8"));
		}
	}

	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}