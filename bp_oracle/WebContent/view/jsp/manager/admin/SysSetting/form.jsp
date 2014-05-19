<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/commons/styles/blue/base.css" rel="stylesheet" type="text/css" />
<jsp:include page="../_global_manager_page_head.jsp" flush="true" />
<link href="${ctx}/scripts/jgrowl/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="divContent">
  <div class="subtitle">
    <h3>${naviString}</h3>
  </div>
  <%@ include file="/commons/pages/messages.jsp" %>
  <html-el:form action="/admin/SysSetting">
    <html-el:hidden property="method" value="save" />
    <html-el:hidden property="id" />
    <html-el:hidden property="mod_id" />
    <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
      <tr>
        <th colspan="2">系统设置</th>
      </tr>
      <tr>
        <td width="15%" class="title_item" nowrap="nowrap">是否启用验证码：</td>
        <td width="85%" align="left"><html-el:select property="isEnabledCode" styleId="isEnabledCode"style="width:128px;" styleClass="webinput">
            <html-el:option value="1">启用验证码</html-el:option>
            <html-el:option value="0">不启用验证码</html-el:option>
          </html-el:select>
          <br />
          启用验证码会使得部分操作变得繁琐，但是可以避免恶意暴力破解密码，请选择是否启用验证码</td>
      </tr>
      <tr>
        <td class="title_item">是否开启系统更新提示：</td>
        <td align="left"><html-el:select property="isEnabledUpdateSystemTip" styleId="isEnabledUpdateSystemTip"style="width:128px;" styleClass="webinput">
            <html-el:option value="1">开启更新提示</html-el:option>
            <html-el:option value="0">关闭更新提示</html-el:option>
          </html-el:select>
          <br />
         开启系统更新提示后，系统页面中将会显示系统更新提示的内容</td>
      </tr>
      <tr id="td_isEnabledUpdateSystemTip" style="display:${(af.map.isEnabledUpdateSystemTip eq 1) ? '' : 'none'};">
        <td class="title_item">系统更新提示内容：</td>
        <td align="left">
        <html-el:text property="UpdateSystemTip" styleId="UpdateSystemTip" style="width:60%" maxlength="255" value="系统将在1分钟后更新，请及时保存数据以免数据丢失!"></html-el:text>
        &nbsp;<html-el:button styleClass="bgButton" property="sendMsg" value="发送更新信息" onclick="sendUpdateSystemTip()"></html-el:button>
        </td>
      </tr>
      <tr>
        <td colspan="2" align="center"><html-el:submit value="保 存" styleClass="bgButton"/>
          &nbsp;
          <html-el:reset value="重 填" styleClass="bgButton"/>
          &nbsp;
          <html-el:button property="back" value="返 回" onclick="history.back();" styleClass="bgButton"/></td>
      </tr>
    </table>
  </html-el:form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jgrowl/min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready( function() {

	$("#isEnabledUpdateSystemTip").change(function(){
		var this_value = $(this).val();
		doAjax("SysSetting.do", "updateIsEnabledUpdateSystemTip", this_value);
	});
	
	/*addAuthorisedIpEvent("ip");

	$("#isAuthorisedIp").change(function(){
		var isAuthorisedIp = $(this).val();
		if ("1" == isAuthorisedIp) {
			$("#tr_ip").show();
		} else {
			$("#tr_ip").hide();
		}
	});

	if ("" != "${af.map.isAuthorisedIp}") {
		$("#isAuthorisedIp").trigger("change");
	}
	*/
});

function addAuthorisedIpEvent(item) {
	$("#add_" + item + "_td").click(function (){
    	$("#hidden_" + item + "_tr").clone().appendTo($("#" + item + "_tbody")).show();
    	var lastTR = $("tr:last", "#" + item + "_tbody");
    	$("#ip_s", lastTR).attr("datatype", "IP").attr("msg", "请填写正确IP！");
    	
    	$("td:last", lastTR).click(function (){
        	$(this).parent().remove();
        }).css("cursor", "pointer");
    }).css("cursor", "pointer");
    
    //编辑页面增加删除事件
	$("tr", "#" + item + "_tbody").each(function(){
    	$("#ip_s", $(this)).attr("datatype", "IP").attr("msg", "请填写正确IP！");
		$("td:last", $(this)).click(function (){
			$(this).parent().remove();
		}).css("cursor", "pointer");
	});
}

function doAjax(url, methodValue, elementValue){
		var TrimParentElementValue = $.trim(elementValue);
		$.ajax({type: "POST", 
			    url: url,
				data: "method=" + methodValue + "&elementValue=" + elementValue,
				dataType: "json",
				error: function(request, settings) {alert("数据加载请求失败！"); },
				success: function(results) {
					if (results.results == "1" && results.method == "updateIsEnabledUpdateSystemTip") {
						$.jGrowl("恭喜，信息修改成功！",{theme:  'success'});
						if (elementValue == 1) {
							$("#td_isEnabledUpdateSystemTip").show();
						} else {
							$("#td_isEnabledUpdateSystemTip").hide();
							if ($("#updateSysTipDiv").is(":visible")) {
								p_publish("/updateSysListen", "action", "closeUpdateSystemTip");
							}
						}
					}
				}
		});
}
//]]>
</script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true" />
<script type="text/javascript">//<![CDATA[
                                  
function sendUpdateSystemTip(){
	var msg = encodeURIComponent(encodeURIComponent($("#UpdateSystemTip").val()));
	p_publish("/updateSysListen", "action", "sendUpdateSystemTip", "msg", msg);
}

//]]>
</script>
</body>
</html>