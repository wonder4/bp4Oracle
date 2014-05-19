<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/commons/styles/blue/base.css" rel="stylesheet" type="text/css" />
<jsp:include page="../_global_manager_page_head.jsp" flush="true" />
</head>
<body>
<div class="divContent">
  <div class="subtitle">
    <h3>${naviString}</h3>
  </div>
  <html-el:form action="/admin/BasePdClass" >
    <html-el:hidden property="queryString" styleId="queryString" />
    <html-el:hidden property="method" styleId="method" value="save" />
    <html-el:hidden property="mod_id" styleId="mod_id" />
    <html-el:hidden property="cls_id" styleId="cls_id" />
    <html-el:hidden property="pd_class_type" styleId="pd_class_type" />
    <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
      <tr>
        <th colspan="3">产品类别基本信息</th>
      </tr>
      <tr>
        <td width="12%" nowrap="nowrap" class="title_item"><span style="color: #F00;">*</span>产品名称：</td>
        <td colspan="2" width="88%"><html-el:text property="cls_name" styleId="cls_name" maxlength="125" style="width:280px" styleClass="webinput" /></td>
      </tr>
      <c:set value="false" var="var_disabled"/>
      <c:if test="${not empty af.map.cls_id}">
      	<c:set value="true" var="var_disabled"/>
      </c:if>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item"><span style="color: #F00;">*</span>父类产品名称：</td>
          <td colspan="2" width="88%"><html-el:hidden property="par_id" styleId="par_id"/>
            <html-el:text property="par_name" styleId="par_name" maxlength="125" style="width:280px" styleClass="webinput" onclick="getBaseClassList()" readonly="true" disabled="${var_disabled}"/>
          </td>
        </tr>
      <tr>
        <td nowrap="nowrap" class="title_item">排序值：</td>
        <td colspan="2"><html-el:text property="order_value" styleId="order_value" maxlength="4" size="4" styleClass="webinput" />
          值越大，显示越靠前。</td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item">是否锁定：</td>
        <td colspan="2"><html-el:select property="is_lock" styleId="is_lock">
            <html-el:option value="0">否</html-el:option>
            <html-el:option value="1">是</html-el:option>
          </html-el:select></td>
      </tr>
      <tr>
        <td colspan="3" align="center"><html-el:button property="" value="保 存" styleClass="bgButton" styleId="btn_submit" />
          &nbsp;
          <html-el:button property="" value="重 填" styleClass="bgButton" styleId="btn_reset" onclick="this.form.reset();" />
          &nbsp;
          <html-el:button property="" value="返 回" styleClass="bgButton" styleId="btn_back" onclick="history.back();" /></td>
      </tr>
    </table>
  </html-el:form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/asyncbox/asyncbox.js"></script>
<script type="text/javascript">//<![CDATA[
var f = document.forms[0];
$(document).ready(function(){
	$("#order_value").focus(setOnlyNum2);

	$("#cls_name").attr("dataType", "Require").attr("msg", "请输入产品名称");
	$("#par_name").attr("dataType", "Require").attr("msg", "请输入父类产品名称");
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(f, 3)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			f.submit();
		}
	});
	});

function getBaseClassList() {
	var url = "CsAjax.do?method=getBaseClassList&isSelectAll=true&pd_class_type="  + '${af.map.pd_class_type}' + "&azaz=" + Math.random();
	asyncbox.open({
		id : 'iframe_' + new Date().getTime(),
		url : url,
		title : '选择父类产品类别',
		modal : true,
		width : 620,
		height : 500,
		btnsbar : $.btn.OK,
		callback : function(action,iframe,returnValue){
			if (action == "ok" && returnValue != null) {
				$("#par_id").val(returnValue.cls_id);
				$("#par_name").val(returnValue.cls_name);
			}
		}
	});
};

function setOnlyNum2() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+]?\d+(?:\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^$/))this.value=0;this.o_value=this.value};
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}
//]]></script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true"/>
</body>
</html>
