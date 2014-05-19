<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<c:url var="url" value="/IndexLogin.do?role=entp" />
<script type="text/javascript">//<![CDATA[
window.onload = function () {
	alert("尊敬的企业会员，很抱歉！您没有登录或已超时，请重新登录！");//\n如果您已经选择了记住密码，系统将自动重新登录。
	top.location.href = "${url}";
};
//]]></script>