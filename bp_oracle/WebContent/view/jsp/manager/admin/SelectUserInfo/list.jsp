<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择用户</title>
<link href="${ctx}/commons/styles/blue/base.css" rel="stylesheet" type="text/css" />
<base target="_self"/>
</head>
<body style="background-color:#fff;">                         
<div id="body_oarcont">
  <div >
    <html-el:form action="/admin/SelectUserInfo">
      <html-el:hidden property="disable_user_ids" styleId="disable_user_ids"/>
      <table style="width:100%;margin:0; padding:0;margin-top: 3px;" border="0" cellpadding="0">
      <tr style="margin:0;padding:0;">
	      <td style="margin:0;padding:0;">
		      <table width="99%" border="0" cellpadding="0" style="margin:0;padding:0;margin-left:5px;" class="tableClassSearch">
			      <tr>
			      	<td width="40%" style="align:right">用户搜索:&nbsp;<html-el:text property="key_word" styleId="key_word" maxlength="50" style="width:90px;align:left" styleClass="inputSelect"/></td>
			      	<td width="60%" >用户类型： <html-el:select property="user_type" styleId="user_type">
			            <html-el:option value="">请选择...</html-el:option>
						<html-el:option value="1020">服务中心主管部门</html-el:option>
						<html-el:option value="1030">服务机构</html-el:option>
						<html-el:option value="1040">服务网点</html-el:option>
						<html-el:option value="1050">企业</html-el:option>
						<html-el:option value="1060">招商部门</html-el:option>
		              </html-el:select>
		              &nbsp;<html-el:button property="" styleId="btn_search"  styleClass="bgButton" value="搜索"/></td>
			      </tr>
			      <!-- <tr>
			      	<td>地区选择：<html-el:select property="province" styleId="province" style="width:100px;">
			                	<html-el:option value="340000">安徽省</html-el:option>
			               </html-el:select>
			               <html-el:select property="city" styleId="city" style="width:100px;">
			                	<html-el:option value="">请选择...</html-el:option>
			               </html-el:select></td>
			      	<td></td>
			      </tr>	   -->    
		      </table>
	      </td>
      	</tr>
              <tr style="margin:0;padding:0;">
                <td style="margin:0;padding:0;">
                 <table width="99%" border="0" cellpadding="0" style="margin:0;padding:0;margin-left:5px;">
                    <tr>
                      <td><div align="center">待选列表</div></td>
                      <td>&nbsp;</td>
                      <td><div align="center">已选列表</div></td>
                    </tr>
                    <tr>
                      <td>
                        <html-el:select property="select1" multiple="true" style="width:190px;height:250px;" styleId="select1" >
                          <c:if test="${not empty entityList}">
                            <html-el:optionsCollection label="user_name" value="id"  name="entityList" />
                          </c:if>
                        </html-el:select>&nbsp;</td>
                      <td>
                        <div style="padding:5px 0;">&nbsp;<html-el:button property="" value="添加 &gt;&gt;" styleId="btn_add" styleClass="bgButton"/>&nbsp;</div>
                        <div style="padding:5px 0;">&nbsp;<html-el:button property="" value="&lt;&lt; 删除" styleId="btn_del" styleClass="bgButton"/>&nbsp;</div>
                        <div style="padding:5px 0;">&nbsp;<html-el:button property="" value="全部添加" styleId="btn_add_all" styleClass="bgButton"/>&nbsp;</div>
                        <div style="padding:5px 0;">&nbsp;<html-el:button property="" value="全部删除" styleId="btn_del_all" styleClass="bgButton"/>&nbsp;</div></td>
                      <td>&nbsp;
                        <html-el:select property="select2" multiple="true" style="width:190px;height:250px;" styleId="select2" >
                          <c:if test="${not empty selectList}">
                            <html-el:optionsCollection label="user_name" value="id" name="selectList" />
                          </c:if>
                        </html-el:select></td>
                    </tr>
                  </table>
                 </td>
              </tr>
              <tr>
                <td style="text-align:center;padding:10px; 0 0;">
                	<html-el:button property="" value=" 提 交 " styleId="btn_submit" styleClass=" bgButton"/>
                  	&nbsp;&nbsp;
                  	<html-el:button property="" value=" 返 回 " styleId="btn_back" onclick="window.close();" styleClass="bgButton"/></td>
              </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript"> !window.jQuery && document.write('<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"><\/script >'); </script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	 //$("#province").attr({"subElement": "city", "defaultText": "请选择...", "defaultValue": "", "selectedValue": "${af.map.province}"});
	 //$("#city"    ).attr({ "defaultText": "请选择...", "defaultValue": "", "selectedValue": "${af.map.city}"});
	 //$("#province").cs("${ctx}/CsAjax.do?method=getBaseProvinceList&azaz=" + Math.random(), "p_index", "0", false);
	 //$("#province").change(); 
	var f = document.forms[0];
	$("#key_word").attr("autocomplete", "off");
	$("#key_word").attr("disableautocomplete", "true");
	
	$("#btn_submit").click(function(){
		<c:if test="${af.map.selectype eq 'signal'}">if ($("#select2 option").length > 1) {
			alert("只能选择一个用户！");
		} else {
		</c:if>
		var selectedUsersId = "";
		var selectedUsersName = "";
		for(var i=0;i<f.select2.length;i++){
			selectedUsersId+=f.select2.options[i].value+",";
			selectedUsersName+=f.select2.options[i].text+",";
		}
		window.returnValue = {
				user_ids : selectedUsersId,
				user_names : selectedUsersName
		}
		window.close();
		<c:if test="${af.map.selectype eq 'signal'}">}</c:if>
	});
	
    var bind_name = 'input';
    if (navigator.userAgent.indexOf("MSIE") != -1){
        bind_name = 'propertychange';
    }
    $("#btn_search").click(function(){
    	getQueryUserNames($('#key_word').val());
    });
    
    $('#key_word').keypress(function (e) {
        var k = e.keyCode || e.which;
        if (k == 13) {
            return false;
        }
    });
    
    //单个添加
	$("#btn_add").click(function() {
		$("#select1 option:selected").remove().appendTo("#select2").attr("selected", false);
	});
  
	//单个删除
	$('#btn_del').click(function() {
		$('#select2 option:selected').remove().appendTo('#select1').attr("selected", false);
	});
	
   //全部添加
	$("#btn_add_all").click(function() {
		$("#select1 option").remove().appendTo("#select2");
	});
  
	//全部删除
	$('#btn_del_all').click(function() {
		$('#select2 option').remove().appendTo('#select1');
	});
		  
});

function getQueryUserNames(key_word) {
   	$("#select1").empty();
   	$.ajax({
		type: "POST",
		cache: false,
		url: "SelectUserInfo.do",
		data: "method=" + "getQueryUserNames" + "&key_word=" + $("#key_word").val()+ "&disable_user_ids=" + $("#disable_user_ids").val()+ "&selectedUserIds=" + getSelect2Value()+"&user_type="+$("#user_type").val()+"&province="+$("#province").val()+"&city="+$("#city").val()+"&country="+$("#country").val(),
		dataType: "json",
		error: function(request, settings){},
		success: function(data) {
			if (data.length > 1) {
				for(var i = 0; i < data.length - 1; i++) {
					var opt = new Option( data[i].user_name,data[i].user_id); 
					$("#select1").get(0).options.add(opt);
				}
			}
		}
	});
}

function getSelect2Value(){
	var str = "";
	var obj = document.getElementById("select2");
	
    for(var i = 0; i < obj.options.length; i++) {
    	str += obj.options[i].value + ",";
    }
	
	if(str != "") {
		return str.substr(0, str.length - 1);
	} else {
		return "";
	}
}
/**
function getQueryUserName(user_id){
 $.ajax({
	type: "POST",
	cache: false,
	url: "${ctx}/manager/admin/SelectUserInfo.do",
	data: "method=" + "getQueryUserName" + "&user_id=" + user_id,
	//dataType: "json",
	error: function(request, settings){},
	success: function(data) {
		if(data != null){
			alert(" == "+data);
			$("#information").empty();
			$("#information").show();
			$("#information").append('描述：' + data);
		}
	}
 });
}
*/
//]]></script>
</body>
</html>
