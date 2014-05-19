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
  <html-el:form action="/admin/UserInfo.do">
    <html-el:hidden property="queryString" />
    <html-el:hidden property="method" value="save" />
    <html-el:hidden property="id" styleId="id"/>
    <html-el:hidden property="mod_id" />
    <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
      <tr>
        <th colspan="2">用户基本信息</th>
      </tr>
      <tr>
        <td width="15%" class="title_item"><span style="color: #F00;">*</span>登录名：</td>
        <td width="85%"><html-el:text property="user_name" maxlength="20" styleClass="webinput" styleId="user_name" style="width:200px" />
          &nbsp;<span id="user_name_tip" style="display:none;"></span></td>
      </tr>
      <tr>
        <td class="title_item" nowrap="nowrap"><span style="color: #F00;">*</span>真实姓名：</td>
        <td><html-el:text property="real_name" maxlength="20" styleClass="webinput" styleId="real_name" style="width:200px" /></td>
      </tr>
      <tr>
        <td class="title_item"><span style="color: #F00;">*</span>用户类型：</td>
        <td><html-el:select property="user_type" styleId="user_type">
            <html-el:option value="">请选择...</html-el:option>
            <c:forEach items="${baseData10List}" var="cur">
              <html-el:option value="${cur.type_value}">${fn:escapeXml(cur.type_name)}</html-el:option>
            </c:forEach>
          </html-el:select></td>
      </tr>
      <tr>
        <td class="title_item" >所属部门：</td>
        <html-el:hidden property="dept_id" styleId="dept_id"/>
        <td><html-el:text property="dept_name" styleId="dept_name" readonly="true" onclick="getDeptInfoList($('#dept_id').val());" style="cursor:pointer;" styleClass="webinput"/></td>
      </tr>
      <tr>
        <td class="title_item"><span style="color: #F00;">*</span>密码：</td>
        <td><html-el:password property="password" maxlength="40" styleClass="webinput" styleId="password" style="width:200px" /></td>
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
          <html-el:text property="birthday" size="8" maxlength="10" readonly="true" onclick="WdatePicker();" value="${_add_birthday}" style="cursor:pointer;" title="点击选择日期" /></td>
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
        <td><html-el:text property="email"  styleClass="webinput" styleId="email" maxlength="50"/></td>
      </tr>
      <tr>
        <td class="title_item">排序值：</td>
        <td><html-el:text property="order_value"  styleClass="webinput" styleId="order_value" size="4" maxlength="4"/>
          值越大，显示越靠前，范围：0-9999</td>
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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/asyncbox/asyncbox.js"></script>
<script type="text/javascript">//<![CDATA[
                                          
var user_exits = '<span id="tip" style="color:red;">对不起，此用户名已被注册</span>';
var user_not_exits = '<span id="tip" style="color:#33CC33;">恭喜你，该用户名可以使用</span>';

var f = document.forms[0];

$("#user_name").attr("datatype","Require").attr("msg","登录名必须填写");
$("#real_name").attr("datatype","Require").attr("msg","真实姓名必须填写");
$("#user_type").attr("datatype","Require").attr("msg","请选择用户类型");
$("#password").attr("datatype","Require").attr("msg","密码必须填写");
$("#email").attr("datatype","Email").attr("require","false").attr("msg","电子信箱填写有误！");
$("#order_value").attr("datatype","Number").attr("msg","排序值必须在0~9999之间的正整数");

function getDeptInfoList(deptId) {
	var url = "CsAjax.do?method=getDeptInfoList&dept_id=" + deptId + "&time=" + new Date().getTime();
	asyncbox.open({
		id : 'iframe_' + new Date().getTime(),
		url : url,
		title : '请选择部门',
		modal : true,
		width : 360,
		height : 500,
		btnsbar : $.btn.OKCANCEL,
		callback : function(action,iframe,returnValue){
			if (action == "ok" && returnValue != null) {
				$("#dept_id").val(returnValue.dept_id);
				$("#dept_name").val(returnValue.dept_name);
			}else {
				$("#dept_id").val("");
				$("#dept_name").val("");
			}
		}
	});
};
// 提交
$("#btn_submit").click(function(){
	if(Validator.Validate(f, 1)){
		var u_name = $.trim($("#user_name").val());
		var u_id = $("#id").val();
		if("" == u_name) {
			alert("请填写登录名");
			return false;
		}
		$("#tip").remove();
		var val = this.value;
		$.post("UserInfo.do?method=checkLoginName",{user_name : u_name, id : u_id},function(data){
			if(data == "1"){
				$("#user_name_tip").show().append(user_exits);
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
