<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${applicationScope._global_website_name}</title>

</head>
<body style="background:#fff url(${ctx}/commons/scripts/jqztree/styles/green/switch/s_bg_0.jpg) repeat-y;margin:0;cursor:pointer" onclick="switchToolBar()" title="展开/隐藏列表">
<table style="height:100%;width:100%;" border="0" cellpadding="0" cellspacing="0" id="imageTab">
  <tr>
    <td valign="middle"><img src="${ctx}/commons/scripts/jqztree/styles/green/switch/s_0.gif" id="switchImage" /></td>
  </tr>
</table>
<script type="text/javascript">//<![CDATA[
var lrFrame = top.document.getElementById("lrFrame");
document.getElementById("imageTab").style.height = lrFrame.offsetHeight + 'px';

var isHiddenFlag = false;
function switchToolBar(){
	var mainFrameset = top.document.getElementById("mainFrameset");
	var switchImage = document.getElementById("switchImage");
	if(!isHiddenFlag) {
		isHiddenFlag = true;
		switchImage.src = "${ctx}/commons/scripts/jqztree/styles/green/switch/s_1.gif";
		document.body.style.background = "#fff url(${ctx}/commons/scripts/jqztree/styles/green/switch/s_bg_0.jpg) repeat-y";
		mainFrameset.cols = "0,6,*";
	} else {
		isHiddenFlag = false;
		switchImage.src = "${ctx}/commons/scripts/jqztree/styles/green/switch/s_0.gif";
		document.body.style.background = "#fff url(${ctx}/commons/scripts/jqztree/styles/green/switch/s_bg_0.jpg) repeat-y";
		mainFrameset.cols = "173,6,*";
	}
}
//]]></script>
</body>
</html>
