<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
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
  <%@ include file="/commons/pages/messages.jsp" %>
  <html-el:form action="/admin/Profile" enctype="multipart/form-data">
    <html-el:hidden property="id" styleId="id" />
    <html-el:hidden property="method" value="save" />
    <html-el:hidden property="mod_id" styleId="mod_id" />
    <html-el:hidden property="queryString" styleId="queryString" />
    <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
      <tr>
        <th colspan="2">用户个人信息修改</th>
      </tr>
      <tr>
        <td width="15%" nowrap="nowrap" class="title_item" >登录名：</td>
        <td width="85%"><c:out value="${af.map.user_name}" /></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item" ><span style="color: #F00;">*</span>真实姓名：</td>
        <td><html-el:text property="real_name" maxlength="20" styleClass="webinput" styleId="real_name" style="width:200px" /></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item"><span style="color: #F00;">*</span>原密码：</td>
        <td><html-el:password property="old_password" styleId="old_password" maxlength="16" style="width:200px" styleClass="webinput" /></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item"><span style="color: #F00;">*</span>新密码：</td>
        <td><html-el:password property="new_password" styleId="new_password" maxlength="16" style="width:200px" styleClass="webinput" /></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item"><span style="color: #F00;">*</span>重复新密码：</td>
        <td><html-el:password property="repeat" styleId="repeat" maxlength="16" style="width:200px" styleClass="webinput" /></td>
      </tr>
      <tr>
        <td class="title_item">性别：</td>
        <td><html-el:select property="sex" styleId="sex">
            <html-el:option value="0">男</html-el:option>
            <html-el:option value="1">女</html-el:option>
          </html-el:select></td>
      </tr>
      <tr>
        <td class="title_item">生日：</td>
        <td><fmt:formatDate value="${af.map.birthday}" pattern="yyyy-MM-dd" var="_add_birthday" />
          <html-el:text property="birthday" size="10" maxlength="10" readonly="true" onclick="WdatePicker();" value="${_add_birthday}" style="cursor:pointer;" title="点击选择日期" styleClass="webinput" /></td>
      </tr>
      <tr>
        <td class="title_item">手机：</td>
        <td><html-el:text property="mobile" maxlength="80" styleClass="webinput" styleId="mobile" style="width:400px;" />
          多个手机请用逗号分隔</td>
      </tr>
      <tr>
        <td class="title_item">办公电话：</td>
        <td><html-el:text property="office_tel" maxlength="80" styleClass="webinput" styleId="office_tel" style="width:400px;" />
          多个办公电话请用逗号分隔</td>
      </tr>
      <tr>
        <td class="title_item">电子信箱：</td>
        <td><html-el:text property="email" maxlength="50" styleClass="webinput" styleId="email" /></td>
      </tr>
      <tr>
        <td colspan="2" align="center"><html-el:button property="" value="保 存" styleClass="bgButton" styleId="btn_submit" />
          &nbsp;
          <html-el:button property="" value="重 置" styleClass="bgButton" styleId="btn_reset" onclick="this.form.reset();" />
          &nbsp;
          <html-el:button property="" value="返 回" styleClass="bgButton" styleId="btn_back" onclick="history.back();" /></td>
      </tr>
    </table>
  </html-el:form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function() {
	
	$("#real_name").attr("dataType", "Require").attr("msg", "真实姓名不能为空");
	$("#old_password").attr("dataType", "Require").attr("msg", "原密码不能为空");
	$("#new_password").attr("dataType", "Require").attr("msg", "新密码不能为空");
	$("#email").attr("datatype","Email").attr("require","false").attr("msg","电子信箱填写有误！");
	$("#repeat").attr("datatype", "Repeat").attr("to", "new_password").attr("msg", "两次输入的密码不一致");

	$("#btn_submit").click(
			function() {
				if (Validator.Validate(this.form, 3)) {
					$("#btn_submit").attr("value", "正在提交...").attr(
							"disabled", "true");
					$("#btn_reset").attr("disabled", "true");
					$("#btn_back").attr("disabled", "true");
					this.form.submit();
				}
	});
});
//]]></script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true" />
</body>
</html>
