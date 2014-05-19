<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>请选择部门</title>
<base target="_self" />
<link href="${ctx}/commons/styles/blue/base.css" rel="stylesheet" type="text/css" />
<jsp:include page="../_global_manager_page_head.jsp" flush="true" />
<link href="${ctx}/commons/scripts/jqztree/styles/customer/zTreeStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="pageClass">
  <ul id="treePd" class="tree">
  </ul>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jqztree/ztree.min.js"></script> 
<script type="text/javascript">//<![CDATA[
var zTreeObj;
$(document).ready(function(){
	var setting = {
		isSimpleData: true,
		checkable : true,
		checkStyle: "radio",
		checkRadioType: "all",
		treeNodeKey: "dept_id",
		treeNodeParentKey: "par_id",
		showLine: true,
		//addDiyDom: addDiyDom,
		callback: {change:	zTreeOnChange},
		root:{ isRoot:true,nodes:[]}
	};
	zNodes =${treeNodes};
	zTreeObj = $("#treePd").zTree(setting, zNodes);
	zTreeObj.expandAll(true);
	
});

function zTreeOnChange() {
	var checkedNodes = zTreeObj.getCheckedNodes();
	var dept_name;
	var dept_id;
   	var valuesText = $.map(checkedNodes, function(treeObj){
   		dept_name = treeObj.name;
		return treeObj.name;
    }).join(",");
    var values = $.map(checkedNodes, function(treeObj){
    	dept_id = treeObj.dept_id;
		if (!treeObj.isParent) {
			return treeObj.dept_id;
		}
    }).join(",");
    //$("#state_type_name").val(valuesText);
    //alert(values);
    
    
    parent.$.returnValue = {"dept_name": dept_name, "dept_id" : dept_id};
}
//treeObj.setChkDisabled(nodes[i], true);
function addDiyDom(treeId, zTreeNode) {

	//var aObj = $("#" + zTreeNode.tId + "_a");
	if(zTreeNode.isParent){
		//alert(zTreeNode.name);
		zTreeNode.name = "testName";
		zTreeObj.updateNode(zTreeNode, true);
	}

}

//]]></script>
</body>
</html>
