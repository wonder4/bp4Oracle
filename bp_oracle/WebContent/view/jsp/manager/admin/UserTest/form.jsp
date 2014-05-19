<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
  <html-el:form action="/admin/UserTest" enctype="multipart/form-data">
  	<html-el:hidden property="id" styleId="id" />
    <html-el:hidden property="queryString" styleId="queryString" />
    <html-el:hidden property="method" styleId="method" value="save" />
    <html-el:hidden property="mod_id" styleId="mod_id" />
	<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
				<tr class="tt2c">
		          <td class="title_item" nowrap="nowrap" width="15%"><span style="color:#f00;">*</span>用户名：</td>
		          <td class="tt2c" align="left" width="85%"><html-el:text property="user_name" styleId="user_name" style="width:380px;" styleClass="webinput" maxlength="100" /></td>
				</tr>	
				<tr class="tt2c">
		          <td class="title_item" nowrap="nowrap" width="15%"><span style="color:#f00;">*</span>真实姓名：</td>
		          <td class="tt2c" align="left" width="85%"><html-el:text property="real_name" styleId="real_name" style="width:380px;" styleClass="webinput" maxlength="60" /></td>
				</tr>	
				<tr class="tt2c">
		          <td class="title_item" nowrap="nowrap" width="15%"><span style="color:#f00;">*</span>性别：</td>
		          <td class="tt2c" align="left" width="85%"><html-el:radio property="sex" styleId="sex_0" value="1" /> <label for="sex_0">女</label> <html-el:radio property="sex" styleId="sex_1" value="0" /> <label for="sex_1">男</label> </td>
				</tr>	
				<tr class="tt2c">
		          <td class="title_item" nowrap="nowrap" width="15%"><span style="color:#f00;">*</span>年龄：</td>
		          <td class="tt2c" align="left" width="85%"><html-el:text property="age" styleId="age" style="width:380px;" styleClass="webinput" maxlength="22" /></td>
				</tr>	
				<tr class="tt2c">
		          <td class="title_item" nowrap="nowrap" width="15%"><span style="color:#f00;">*</span>文化程度：</td>
		          <td class="tt2c" align="left" width="85%"><html-el:select property="education" styleId="education">
								<html-el:option value="">请选择...</html-el:option>							
								<html-el:option value="小学">小学</html-el:option>
								<html-el:option value="中学">中学</html-el:option>
								<html-el:option value="大学">大学</html-el:option>
							</html-el:select></td>
				</tr>	
				<tr class="tt2c">
		          <td class="title_item" nowrap="nowrap" width="15%"><span style="color:#f00;">*</span>出生年份：</td>
		          <td class="tt2c" align="left" width="85%"><fmt:formatDate value="${af.map.birth_year}" pattern="yyyy-MM-dd" var="_birth_year" /><html-el:text property="birth_year" styleId="birth_year" size="10" maxlength="20" readonly="true" styleClass="webinput" onclick="WdatePicker();" style="cursor:pointer;" value="${_birth_year}" /></td>
				</tr>	
				<tr class="tt2c">
		          <td class="title_item" nowrap="nowrap" width="15%"><span style="color:#f00;">*</span>备注：</td>
		          <td class="tt2c" align="left" width="85%"><html-el:textarea property="user_desc" styleId="user_desc" style="width:380px;height:50px;" styleClass="webinput"></html-el:textarea></td>
				</tr>	
	            <tr>
		          <td>&nbsp;</td>
		          <td><html-el:button property="" value="保 存" styleClass="bgButton" styleId="btn_submit" /> &nbsp;
		              <html-el:button property="" value="重 填" styleClass="bgButton" styleId="btn_reset" onclick="this.form.reset();" /> &nbsp;
		              <html-el:button property="" value="返 回" styleClass="bgButton" styleId="btn_back" onclick="history.back();" /></td>
		        </tr>		             
	</table>
  </html-el:form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#user_name").attr({"dataType":"Require","msg":"请填写用户名"});
	$("#real_name").attr({"dataType":"Require","msg":"请填写真实姓名"});
	$("#sex").attr({"dataType":"Require","msg":"请选择性别"});
	$("#age").attr({"dataType":"Require","msg":"请填写年龄"});
	$("#education").attr({"dataType":"Require","msg":"请选择文化程度"});
	$("#birth_year").attr({"dataType":"Require","msg":"请填写出生年份"});
	$("#user_desc").attr({"dataType":"Limit","max":"255","msg":"备注最多只能填写255个字符"});
	$("#age").focus(setOnlyNum);

	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 2)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
});

function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		//if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}
//]]></script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true"/>
</body>
</html>