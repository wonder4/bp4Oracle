<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/commons/styles/blue/base.css" rel="stylesheet" type="text/css" />
<jsp:include page="../_global_manager_page_head.jsp" flush="true" />
<link href="${ctx}/commons/scripts/jqztree/styles/customer/zTreeStyle.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.spanbtn {
	padding-left: 10px;
	cursor: pointer;
}
.spanbtn img {
	vertical-align: text-bottom;
}
</style>
</head>
<body>
<div class="divContent">
  <div class="subtitle">
    <h3>${naviString}</h3>
  </div>
  <fieldset style="padding: 10px;margin: 5px;">
    <legend style="color:#F00;font-weight: bold;">说明：</legend>
    <ul>
      <li style="color:#0066FF;">1、[<img src="${ctx}/commons/scripts/jqztree/styles/yellow/img/view.png" width="16" height="16"></img>：查看]&nbsp;[<img src="${ctx}/commons/scripts/jqztree/styles/yellow/img/edit.png" width="16" height="16"></img>：修改]&nbsp;[<img src="${ctx}/commons/scripts/jqztree/styles/yellow/img/remove.png" width="16" height="16"></img>：删除]&nbsp;[<img src="${ctx}/commons/scripts/jqztree/styles/customer/img/perm.png" width="16" height="16"></img>：属性授权]</li>
      <li style="color:#F00;">2、已经锁定的类别，不能进行修改和删除操作。</li>
      <li style="color:#F00;">3、新增、修改、删除的类别，在首页需要第二天凌晨2点才能正常显示。</li>
    </ul>
  </fieldset>
  <%@ include file="/commons/pages/messages.jsp" %>
  <form id="listForm" name="listForm" method="post" action="BasePdClass.do?method=delete&pd_class_type=${af.map.pd_class_type}">
    <div style="text-align: left; padding: 5px;">
      <input type="button" name="delete" id="delete" class="bgButton" value="删除所选" onclick="this.form.action += '&' + $('#bottomPageForm').serialize();confirmDeleteAllForClazz(this.form);" />
      <input type="button" name="add" id="add" class="bgButton" value="添加" onclick="location.href='BasePdClass.do?method=add&pd_class_type=${af.map.pd_class_type}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize();" />
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <input type="checkbox" name="pksString" id="pksString" style="display: none;" />
    </div>
  </form>
  <div class="pageClass">
    <ul id="treePd" class="tree">
    </ul>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/cs.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jqztree/ztree.min.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script> 
<script type="text/javascript">//<![CDATA[
var zTree;
$(document).ready(function(){
	var setting = {
		isSimpleData: true,
		checkable : true,
		//checkStyle: "radio",
		//checkRadioType: "all",
		treeNodeKey: "cls_id",
		treeNodeParentKey: "par_id",
		showLine: true,
		callback: {change:	zTreeOnChange},
		addDiyDom: addDiyDom,
		root:{ isRoot:true,nodes:[]}
	};
	zNodes =${clazzTree};
	zTree = $("#treePd").zTree(setting, zNodes);
	zTree.expandAll(true);
	
});

function zTreeOnChange() {
	var checkedNodes = zTree.getCheckedNodes();
    //var valuesText = $.map(checkedNodes, function(treeObj){
	//	return treeObj.name;
    //}).join(",");
    var values = $.map(checkedNodes, function(treeObj){
		if (!treeObj.isParent) {
			return treeObj.cls_id;
		}
    }).join(",");
    //$("#state_type_name").val(valuesText);
    //alert(values);
    $("#pksString").val(values);
    if (values.length == 0) {
    	$("#pksString").removeAttr("checked");
    } else {
    	$("#pksString").attr("checked","checked");
    }
}

function addDiyDom(treeId, zTree) {
	var aObj = $("#" + zTree.tId + "_a");
	
	var viewHtml = '<span title="查看" class="spanbtn" onclick="location.href=\'BasePdClass.do?method=view&cls_id='+zTree.cls_id+'&pd_class_type=${af.map.pd_class_type}&mod_id=${af.map.mod_id}\'"><img src="${ctx}/commons/scripts/jqztree/styles/yellow/img/view.png" width="16" height="16"></img></span>' ;
	var editHtml = '<span title="修改" class="spanbtn" onclick="location.href=\'BasePdClass.do?method=edit&cls_id='+zTree.cls_id+'&pd_class_type=${af.map.pd_class_type}&mod_id=${af.map.mod_id}\'"><img src="${ctx}/commons/scripts/jqztree/styles/yellow/img/edit.png" width="16" height="16"></img></span>' ;
	var pompHtml = '<span title="属性授权" class="spanbtn" onclick="location.href=\'BasePdClass.do?method=linkAttribute&cls_id='+zTree.cls_id+'&attr_scope=${af.map.attr_scope}&pd_class_type=${af.map.pd_class_type}&mod_id=${af.map.mod_id}\'"><img src="${ctx}/commons/scripts/jqztree/styles/customer/img/perm.png" width="16" height="16"></img></span>' ;
	var delHtml = '<span title="删除" class="spanbtn" style="cursor:pointer;" onclick="confirmDelete(null, \'BasePdClass.do\', \'cls_id='+zTree.cls_id+'&pd_class_type=${af.map.pd_class_type}&mod_id=${af.map.mod_id}\')"><img src="${ctx}/commons/scripts/jqztree/styles/yellow/img/remove.png" width="16" height="16"></img></span>' ;
	var editStr =  viewHtml + editHtml + delHtml;
	if (zTree.nocheck) {
	    editStr =  viewHtml;
	} else {
		editStr =  viewHtml + editHtml + delHtml;
	}
	if(!zTree.isParent){
	    editStr += pompHtml;
	}
	aObj.after(editStr);
}

//]]></script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true"/>
</body>
</html>