package com.ebiz.bp_oracle.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

public class SimpleAuthenticateFilter extends OncePerRequestFilter {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private String sessionKey;

	private String loginPage;

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		if (StringUtils.isBlank(this.loginPage)) {

			logger.warn("loginPage is empty, it will redirect to welcome page.");
		}
		if (!this.isAuthenticated(request, this.sessionKey)) {
			String contextPath = request.getContextPath();
			if (contextPath.endsWith("/")) {
				contextPath = contextPath.substring(0, contextPath.length() - 1);
			}
			response.sendRedirect(contextPath + this.loginPage);
			return;
		}

		chain.doFilter(request, response);
	}

	private boolean isAuthenticated(HttpServletRequest request, String sessionKey) {
		if (StringUtils.isBlank(sessionKey)) {
			logger.info("session key is empty!");
			return false;
		}
		HttpSession session = request.getSession(false);
		if (null == session) {
			return false;
		}
		if (null == request.getSession().getAttribute(sessionKey)) {
			return false;
		}
		return true;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

}
