<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link href="${ctx}/commons/styles/blue/base.css" rel="stylesheet" type="text/css" />
<jsp:include page="../_global_manager_page_head.jsp" flush="true" />
<title>topFrame</title>
</head>
<body>
<c:url var="urlindex" value="/index.do" />
<div class="topFrameLayout">
  <div class="topFrameHeader">
    <div class="topFrameHeaderNav"> <a class="logo" href="${urlindex}" target="_blank">${applicationScope._global_website_name}</a>
      <ul class="nav">
        <li><a href="javascript:void(0);">欢迎您！${fn:escapeXml(userInfo.user_name)}(${fn:escapeXml(userInfo.real_name)})</a></li>
        <li><a href="${urlindex}" target="_blank">首页</a></li>
        <li><a href="../login.do?method=logout" target="_parent">退出</a></li>
      </ul>
      <ul class="themeList" id="themeList">
        <li class="green">
          <div>绿色</div>
        </li>
        <li class="blue">
          <div class="selected">蓝色</div>
        </li>
        <li class="yellow">
          <div>黄色</div>
        </li>
        <!--        <li class="purple"><div>紫色</div></li>--> 
        <!--        <li class="silver"><div>银色</div></li>--> 
        <!--        <li class="azure"><div>天蓝</div></li>-->
      </ul>
    </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cookie.js"></script> 
<script type="text/javascript">//<![CDATA[

$(document).ready(function(){
 	$("li", "#themeList").click(function(){
 	 	var _this = $(this);
 	 	var themeName = _this.attr("class");
 	 	var _parent = _this.parent();
 	 	
 	 	var _theme = $("head").find("link[href$='base.css']");
 	 	var cssPath = _theme.attr("href");
 	 	var temp = cssPath.substring(0, cssPath.lastIndexOf('/'));
 	 	var baseCssPath = temp.substring(0, temp.lastIndexOf('/') + 1);
 	 	var _themeHref = baseCssPath + themeName +"/base.css";
 	 	_theme.attr("href", _themeHref);
 	 	
		_this.parent().find("div").removeClass("selected");
		_this.find("div").addClass("selected");

		$("head", window.parent.leftFrame.document).find("link[href$='base.css']").attr("href", _themeHref);
		$("head", window.parent.mainFrame.document).find("link[href$='base.css']").attr("href", _themeHref);
		if ($.isFunction($.cookie)) $.cookie("projectTheme", themeName);
 	 });

	if ($.isFunction($.cookie)){
		var themeName = $.cookie("projectTheme");
		if (themeName) {
	 	 	var _theme = $("head").find("link[href$='base.css']");
	 	 	var cssPath = _theme.attr("href");
	 	 	var temp = cssPath.substring(0, cssPath.lastIndexOf('/'));
	 	 	var baseCssPath = temp.substring(0, temp.lastIndexOf('/') + 1);
	 	 	var _themeHref = baseCssPath + themeName +"/base.css";
	 	 	_theme.attr("href", _themeHref);
	 	 	$("li", "#themeList").each(function(){
	 	 	 	var _this = $(this);
	 	 	 	var cssThemeName = _this.attr("class");
	 			_this.find("div").removeClass("selected");
	 			if (cssThemeName == themeName) {
	 				_this.find("div").addClass("selected");
	 			}
		 	});
		}
	}

});
	
//]]>
</script>
</body>
</html>