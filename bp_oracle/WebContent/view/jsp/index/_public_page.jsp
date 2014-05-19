<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<script type="text/javascript">//<![CDATA[
document.oncontextmenu=function(){
	// return false;
};
if (typeof(jQuery) != "undefined") {  
	$("input[type='text'][readonly],textarea[readonly]").css({color:"#999"});
}
 //]]></script>
<jsp:include page="_analytics.jsp" flush="true"/>
