<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>帮助中心树</title>
<link href="${ctx}/commons/styles/blue/base.css" rel="stylesheet" type="text/css" />
<jsp:include page="../_global_manager_page_head.jsp" flush="true" />
<style type="text/css">
<!--
body {
	margin: 5px;
	background-color:#FFFFFF;
	font-family: "微软雅黑", Tahoma, Verdana;
	font-size: 12px;
}
a:link, a:visited, a:active, a:hover {
	color: #000000;
	padding-left: 3px;
	font-family: "微软雅黑", Tahoma, Verdana;
	font-size: 12px;
	text-decoration: none;
}
div {
	width: 100%;
}
div.delimiter {
	height: 2px;
	line-height:2px;
}
td, th, label, div, span, p, input, textarea, select, option {
	font-family: "微软雅黑", Tahoma, Verdana;
	font-size: 12px;
}
-->
</style>
</head>
<body>
<div class="th">&nbsp;帮助中心文档管理</div>
<div class="mainbar3">
  <ul>
    <li>&nbsp;欢迎您：<span style="color:green; font-weight:bold;">${userInfo.user_name}</span></li>
    <li><a href="../../manager/admin/Frames.do" target="_self">返回上级</a></li>
  </ul>
</div>
<div class="delimiter"></div>
<script type="text/javascript" src="${ctx}/commons/scripts/mztree/MzTreeView10.js"></script>
<script type="text/javascript">//<![CDATA[
window.tree = new MzTreeView("tree");
tree.setIconPath("${ctx}/commons/scripts/mztree/images_help/");
tree.wordLine = false;
${treeNodes}
tree.setURL("../");
tree.setTarget("editFrame");
document.write(tree.toString());
tree.expandAll();
//]]></script>
<div class="delimiter"></div>
</body>
</html>
