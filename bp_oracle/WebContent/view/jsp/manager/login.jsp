<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>登陆 - ${applicationScope._global_website_name}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Description" content="${applicationScope._global_website_name}" />
<meta name="Keywords" content="${applicationScope._global_website_name}" />
<link href="${ctx}/styles/login/blue/default.css" rel="stylesheet" type="text/css" />
<style>
.welcome {
	font-family: "微软雅黑", Tahoma, sans-serif;
	font-size: 18px;
}
</style>
</head>
<body class="loginPageBody">
<div class="themeContent">
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
  </ul>
</div>
<div class="LoginWrapbg">
  <div style="width: 100%; height: 50%; left: 0; top: 0; z-index: -1;"></div>
  <div style="width: 442px; height: 440px; margin: 0 auto; margin-top: -260px;">
    <div class="loginLogo"></div>
    <div class="welcome">欢迎登录  ${applicationScope._global_website_name}</div>
    <div class="loginBar">
      <html-el:form action="/login">
        <html-el:hidden property="method" value="login" />
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableLogin">
          <tr class="fieldWrap">
            <td width="23%"><div class="lable">用户名：</div></td>
            <td width="77%"><div class="inputWrap">
                <input class="inputText" type="text" name="login_name" id="login_name" />
              </div></td>
          </tr>
          <tr>
            <td><div class="lable">密  码：</div></td>
            <td><div class="inputWrap">
                <input class="inputText" type="password" name="password" id="password" />
              </div></td>
          </tr>
          <tr id="tr_verificationCode">
            <td><div class="lable">验证码：</div></td>
            <td><div class="inputWrap">
                <html-el:text property="verificationCode" style="width:110px;" styleId="veri_code" maxlength="4" styleClass="inputText" />
                &nbsp;&nbsp;<span style="cursor:pointer;"><img style="display:none" src="${ctx}/images/loading_login.gif" id="veri_img" /></span></div></td>
          </tr>
          <tr>
            <td colspan="2" align="center"><input type="submit" class="btnBg" name="login" value="登 录" />
              <input class="btnBg" type="button" onclick="this.form.reset();" value="重 置" /></td>
          </tr>
        </table>
      </html-el:form>
    </div>
  </div>
  <div class="copyright">
    <div align="center">Copyright &copy; 1997-2012 www.ahtech.cn Inc. All rights reserved.安徽易商数码科技有限公司©版权所有 </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cookie.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#login_name").attr("dataType", "Require").attr("msg", "请填写您的用户名！").focus();
	$("#password"  ).attr("dataType", "Require").attr("msg", "请填写您的密码！");
	if ("${isEnabledCode}" == "1") {
		$("#veri_code" ).attr("dataType", "Require").attr("msg", "验证码不能为空！");
	} else {
		$("#tr_verificationCode" ).hide();
	}
	$("#veri_code").focus(function(){
		$("#veri_img").attr("src", "${ctx}/images/VerificationCode.jpg?t=" + new Date().getTime()).attr({"width":60,"height":20}).show();
	}).blur(function(){
		$("#veri_img").hide().attr("src", "${ctx}/images/loading_login.gif").removeAttr("width").removeAttr("height");;
	});
	$(document.forms[0]).submit(function(){
		var flag = Validator.Validate(this, 1);
		if(!flag){
			return false;
		}
		return flag;
	});
	
	$("li", "#themeList").click(function(){
 	 	var _this = $(this);
 	 	var themeName = _this.attr("class");
 	 	var _parent = _this.parent();
 	 	
 	 	var _theme = $("head").find("link[href$='default.css']");
 	 	var cssPath = _theme.attr("href");
 	 	var temp = cssPath.substring(0, cssPath.lastIndexOf('/'));
 	 	var baseCssPath = temp.substring(0, temp.lastIndexOf('/') + 1);
 	 	var _themeHref = baseCssPath + themeName +"/default.css";
 	 	_theme.attr("href", _themeHref);
 	 	
		_this.parent().find("div").removeClass("selected");
		_this.find("div").addClass("selected");

		if ($.isFunction($.cookie)) {
			$.cookie("projectTheme", themeName);
		}
 	 });
	if ($.isFunction($.cookie)){
		var themeName = $.cookie("projectTheme");
		if (themeName) {
	 	 	var _theme = $("head").find("link[href$='default.css']");
	 	 	var cssPath = _theme.attr("href");
	 	 	var temp = cssPath.substring(0, cssPath.lastIndexOf('/'));
	 	 	var baseCssPath = temp.substring(0, temp.lastIndexOf('/') + 1);
	 	 	var _themeHref = baseCssPath + themeName +"/default.css";
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

//]]></script>
</body>
</html>
