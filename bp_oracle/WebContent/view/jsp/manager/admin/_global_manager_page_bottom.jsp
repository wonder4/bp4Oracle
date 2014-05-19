<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
 <script type="text/javascript" src="${ctx}/commons/scripts/jquery.cookie.js"></script> 
 <script type="text/javascript">//<![CDATA[
	if (typeof(jQuery) != "undefined") {  
			$("input[type='text'][readonly],textarea[readonly]").css({color:"#999"});
			if ($.isFunction($.cookie)){
				var themeName = $.cookie("projectTheme");
				if (themeName) {
			 	 	var _theme = $("head").find("link[href$='base.css']");
			 	 	var cssPath = _theme.attr("href");
			 	 	var temp = cssPath.substring(0, cssPath.lastIndexOf('/'));
			 	 	var baseCssPath = temp.substring(0, temp.lastIndexOf('/') + 1);
			 	 	var _themeHref = baseCssPath + themeName +"/base.css";
			 	 	_theme.attr("href", _themeHref);
				}
			}
	}
 //]]></script>
