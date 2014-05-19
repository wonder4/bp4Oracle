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
  <html-el:form action="/admin/BaseData.do">
    <html-el:hidden property="queryString" />
    <html-el:hidden property="method" value="save" />
    <html-el:hidden property="id" styleId="id"/>
    <html-el:hidden property="mod_id" />
    <html-el:hidden property="type" />
    <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
      <tr>
        <th colspan="2"><c:if test="${af.map.type eq 10}">用户类型信息</c:if>
          <c:if test="${af.map.type eq 20}">仓库类型信息</c:if>
        </th>
      </tr>
      <tr>
        <td width="15%" class="title_item" nowrap="nowrap"><span style="color: #F00;">*</span>
          <c:if test="${af.map.type eq 10}">用户类型：</c:if>
          <c:if test="${af.map.type eq 20}">仓库类型：</c:if></td>
        <td width="85%"><html-el:text property="type_name" maxlength="20" styleClass="webinput" styleId="type_name" style="width:200px" />
          &nbsp;<span id="type_name_tip" style="display:none;"></span></td>
      </tr>
      <tr>
        <td class="title_item">备注：</td>
        <td><html-el:text property="remark" maxlength="20"  styleClass="webinput" styleId="real_name" style="width:200px" /></td>
      </tr>
      <tr>
        <td class="title_item">排序值：</td>
        <td><html-el:text property="order_value" maxlength="4" size="4" styleClass="webinput" styleId="order_value"  />
          值越大，显示越靠前，范围：0-9999 </td>
      </tr>
      <c:if test="${af.map.is_del eq 1}">
        <tr>
          <td class="title_item">是否删除：</td>
          <td><html-el:select property="is_del" styleId="is_del">
              <html-el:option value="0">否</html-el:option>
              <html-el:option value="1">是</html-el:option>
            </html-el:select></td>
        </tr>
      </c:if>
      <tr>
        <td colspan="2" style="text-align:center"><html-el:button property="" value="保 存" styleClass="bgButton" styleId="btn_submit" />
          &nbsp;
          <html-el:button property="" value="重 填" styleClass="bgButton" styleId="btn_reset" onclick="this.form.reset();" />
          &nbsp;
          <html-el:button property="" value="返 回" styleClass="bgButton" styleId="btn_back" onclick="history.back();" /></td>
      </tr>
    </table>
  </html-el:form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
var type_name_exits = '<span id="tip" style="color:red;">对不起，此类型已被注册</span>';
var type_name_not_exits = '<span id="tip" style="color:#33CC33;">恭喜你，该类型名可以使用</span>';

var f = document.forms[0];

$("#type_name").attr("datatype","Require").attr("msg","用户类型必须填写");
$("#order_value").attr("datatype","Number").attr("msg","排序值必须在0~9999之间的正整数");
// 提交
$("#btn_submit").click(function(){
	if(Validator.Validate(f, 3)){
		var t_name = $.trim($("#type_name").val());
		var t_id = $("#id").val();
		if("" == t_name) {
			alert("请填写用户类型");
			return false;
		}
		$("#tip").remove();
		$.post("BaseData.do?method=checkTypeName",{type_name : t_name, id : t_id},function(data){
			if(data == "1"){
				$("#type_name_tip").show().append(type_name_exits);
			} else {
		        $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
		        $("#btn_reset").attr("disabled", "true");
		        $("#btn_back").attr("disabled", "true");
				f.submit();
			}
		});
	}
});
//]]></script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true" />
</body>
</html>
