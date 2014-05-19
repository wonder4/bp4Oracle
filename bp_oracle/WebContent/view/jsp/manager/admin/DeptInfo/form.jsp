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
<div style="text-align:left;">
  <%@ include file="/commons/pages/messages.jsp" %>
  <br/>
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="32"><c:if test="${not empty af.map.level_4}" var="level_4"><span style="color:#ccc;">增加〖${af.map.dept_name}〗的子部门</span></c:if>
        <c:if test="${not level_4}"><a href='javascript:showTable("addDept");'>增加<span style="color:#FF0000;">〖${af.map.dept_name}〗</span>的子部门</a></c:if></td>
    </tr>
    <tr>
      <td><html-el:form action="/admin/DeptInfo">
          <html-el:hidden property="method" value="save" />
          <html-el:hidden property="dept_id" />
          <table id="addDept" width="98%" border="0" align="left" cellpadding="0" cellspacing="1" class="tableClass" style="display:none;">
            <tr>
              <th colspan="2">增加部门详细信息</th>
            </tr>
            <tr>
              <td width="17%" class="title_item"><span style="color: #F00;">*</span>部门名称：</td>
              <td width="83%"><html-el:text property="dept_name" maxlength="20" style="width:240px;" value="" /></td>
            </tr>
            <tr>
              <td class="title_item">部门负责人：</td>
              <td><html-el:text property="dept_master" maxlength="100" style="width:240px;" value="" /></td>
            </tr>
            <tr>
              <td class="title_item">部门电话：</td>
              <td><html-el:text property="dept_tel" maxlength="100" style="width:240px;" value="" /></td>
            </tr>
            <tr>
              <td class="title_item">部门传真：</td>
              <td><html-el:text property="dept_fax" maxlength="100" style="width:240px;" value="" /></td>
            </tr>
            <tr>
              <td class="title_item">部门地址：</td>
              <td><html-el:text property="dept_fax" maxlength="100" style="width:240px;" value="" /></td>
            </tr>
            <tr>
              <td class="title_item">部门说明：</td>
              <td><html-el:text property="mod_desc" maxlength="100" style="width:240px;" value="" /></td>
            </tr>
            <tr>
              <td class="title_item">排序值：</td>
              <td><html-el:text property="order_value" size="4" maxlength="4" />
                值越大，显示越靠前，范围：0-9999</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td><html-el:submit value=" 保存 " styleClass="bgButton"/>
                &nbsp;<html-el:reset value=" 重填 " styleClass="bgButton"/>
                &nbsp;<html-el:button property="back" value=" 返回 " onclick="history.back();" styleClass="bgButton"/></td>
            </tr>
          </table>
        </html-el:form></td>
    </tr>
    <tr>
      <td height="32"><a href='javascript:showTable("editDept");'>修改<span style="color:#FF0000;">〖${af.map.dept_name}〗</span>部门信息</a></td>
    </tr>
    <tr>
      <td><html-el:form action="/admin/DeptInfo.do">
          <html-el:hidden property="method" value="save" />
          <html-el:hidden property="id" />
          <html-el:hidden property="dept_id" />
          <table id="editDept" width="98%" border="0" align="left" cellpadding="0" cellspacing="1" class="tableClass" style="display:none;">
            <tr>
              <th colspan="2">修改部门详细信息</th>
            </tr>
            <tr>
              <td width="17%" class="title_item"><span style="color: #F00;">*</span>部门名称：</td>
              <td width="83%"><html-el:text property="dept_name" maxlength="20" style="width:240px;" /></td>
            </tr>
            <tr>
              <td class="title_item">部门负责人：</td>
              <td><html-el:text property="dept_master" maxlength="100" style="width:240px;" /></td>
            </tr>
            <tr>
              <td class="title_item">部门电话：</td>
              <td><html-el:text property="dept_tel" maxlength="100" style="width:240px;" /></td>
            </tr>
            <tr>
              <td class="title_item">部门传真：</td>
              <td><html-el:text property="dept_fax" maxlength="100" style="width:240px;" /></td>
            </tr>
            <tr>
              <td class="title_item">部门地址：</td>
              <td><html-el:text property="dept_fax" maxlength="100" style="width:240px;" /></td>
            </tr>
            <tr>
              <td class="title_item">部门说明：</td>
              <td><html-el:text property="dept_desc" maxlength="100" style="width:240px;" /></td>
            </tr>
            <tr>
              <td class="title_item">排序值：</td>
              <td><html-el:text property="order_value" size="4" maxlength="4" />
                值越大，显示越靠前，范围：0-9999</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td><html-el:submit value=" 保存 " styleClass="bgButton" />
                &nbsp;<html-el:reset value=" 重填 " styleClass="bgButton"/>
                &nbsp;<html-el:button property="back" value=" 返回 " onclick="history.back();" styleClass="bgButton"/></td>
            </tr>
          </table>
        </html-el:form></td>
    </tr>
    <tr>
      <td height="32"><c:if test="${af.map.is_lock eq 1}"><span style="color:#ccc;">删除部门〖${af.map.dept_name}〗</span></c:if>
        <c:if test="${af.map.is_lock eq 0}"><a href='javascript:if(confirm("确认删除？如有子部门也将会被全部删除！"))location.href="DeptInfo.do?method=delete&amp;dept_id=${af.map.dept_id}&amp;id=${af.map.id}";'>删除部门<span style="color:#FF0000;">〖${af.map.dept_name}〗</span></a></c:if></td>
    </tr>
  </table>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">
//<![CDATA[
$("input[type='text'],input[type='password']").css("border","1px solid #ccc");
$("input[type='text']").not("[readonly]").focus(function(){
	$(this).addClass("row_focus");
}).blur(function(){
	$(this).removeClass("row_focus");
});

var f_0 = document.forms[0];
f_0.dept_name.setAttribute("datatype","Require");
f_0.dept_name.setAttribute("msg","部门名称必须填写");

f_0.order_value.setAttribute("datatype","Number");
f_0.order_value.setAttribute("require","true");
f_0.order_value.setAttribute("msg","排序值填写有误！必须为数字！");

f_0.onsubmit = function () {
	return Validator.Validate(this, 3);
}

var f_1 = document.forms[1];
f_1.dept_name.setAttribute("datatype","Require");
f_1.dept_name.setAttribute("msg","部门名称必须填写");

f_1.order_value.setAttribute("datatype","Number");
f_1.order_value.setAttribute("require","true");
f_1.order_value.setAttribute("msg","排序值填写有误！必须为数字！");

f_1.onsubmit = function () {
	return Validator.Validate(this, 3);
}

function showTable(id) {
	$("#" + id).toggle();
}
//]]>
</script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true"/>
</body>
</html>
