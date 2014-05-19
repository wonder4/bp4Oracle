<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${applicationScope._global_website_name}</title>
<style type="text/css">
<!--
.console-menu-dragbar{width:6px;height:100%;position:absolute;right:0;top:0;background:url(${ctx}/commons/scripts/jqztree/styles/green/switch/s_bg_0.jpg) 0 0 ;overflow:hidden;}
.drag-menu-contraction{cursor:pointer;width:6px;height:78px;position:absolute;right:0px;background:url(${ctx}/commons/scripts/jqztree/styles/green/switch/s_0.gif);z-index:10;}
.drag-menu-contraction:hover{background:url(${ctx}/commons/scripts/jqztree/styles/green/switch/s_0_hover.gif);}
.drag-menu-contraction.up{background:url(${ctx}/commons/scripts/jqztree/styles/green/switch/s_1.gif);}
.drag-menu-contraction.up:hover{background:url(${ctx}/commons/scripts/jqztree/styles/green/switch/s_1_hover.gif);}
-->
</style>

</head>
<body>
<div class="drag-menu-contraction" title="隐藏列表"></div>
<div class="console-menu-dragbar"></div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
var mainFrameset = top.document.getElementById("mainFrameset");
var $switch_div = $(".drag-menu-contraction");
$(".console-menu-dragbar").css({"height":$(window).height()});
$switch_div.css({
    "top": ($(window).height() / 2 - 39) + "px" //减去收缩按钮的高度
});
$switch_div.toggle(
	function () {
		$(this).addClass("up").attr("title","展开列表");
		mainFrameset.cols = "0,6,*";
	},
	function () {
		 $(this).removeClass("up").attr("title","隐藏列表");
		 mainFrameset.cols = "173,6,*";
	}
);
//]]></script>
</body>
</html>
