<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>请选择类别</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link href="${ctx}/commons/styles/blue/base.css" rel="stylesheet" type="text/css" />
<jsp:include page="../_global_manager_page_head.jsp" flush="true" />
</head>
<body>
<div id="navTab" class="tabsPage" style="text-align:left;">
  <div class="navTab-panel tabsPageContent">
    <div class="pageContent">
      <html-el:form action="/admin/BaseBrandInfo">
        <html-el:hidden property="method" value="listSelectAllBrand" />
        <div style="height:5px;"></div>
        <table width="100%" border="0" align="left" class="list">
          <tr>
            <td>品牌名称：
              <html-el:text property="key_word" styleId="key_word" size="24" maxlength="100" style="width:120px;" title="可以输入关键字进行即时搜索" />
              &nbsp;
              品牌标签：
              <html-el:select property="brand_label" styleId="brand_label">
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${abcArray}">
                  <html-el:option value="${cur}">${cur}</html-el:option>
                </c:forEach>
              </html-el:select></td>
          </tr>
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td>待选择区
                    <html-el:select property="select1" multiple="true" style="width:290px;height:358px;" styleId="select1" onchange="moveSelected(this.form.select1, this.form.select2,'no');">
                      <c:forEach var="cur" items="${baseBrandInfoList}" varStatus="vs">
                        <option value="${cur.brand_id}_${cur.brand_name}">${fn:escapeXml(cur.brand_name)}</option>
                      </c:forEach>
                    </html-el:select></td>
                  <td>已选择区
                    <html-el:select property="select2" multiple="true" style="width:290px;height:358px;" styleId="select2" onchange="moveSelected(this.form.select2, this.form.select1);"> </html-el:select></td>
                </tr>
              </table></td>
          </tr>
        </table>
      </html-el:form>
    </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#btn_submit").click(function(){
		var valuesText =$("option", "#select2").map(function(){
			return this.value;
		}).get().join("#$");
		alert(valuesText);
		window.returnValue = valuesText;		
		window.close();
	});
	
    var bind_name = "input";
    if (navigator.userAgent.indexOf("MSIE") != -1){
        bind_name = "propertychange";
    }
    $("#key_word").bind(bind_name, function(){
    	getQueryNames();
    }).keypress(function (e) {
        var k = e.keyCode || e.which;
        if (k == 13) {
            return false;
        }
    });
    $("#brand_label").change(function(e){
    	getQueryNames();
    });
});

function getQueryNames() {
   	$("#select1").empty();
   	$.ajax({
		type: "POST",
		cache: false,
		url: "BaseBrandInfo.do",
		data: "method=listSelectAllBrand&isAjax=true&key_word=" + $("#key_word").val() + "&brand_label=" + $("#brand_label").val(),
		dataType: "json",
		error: function(request, settings){},
		success: function(data) {
			if (data.baseBrandInfoJsonList.length >= 1) {
				for(var i = 0; i < data.baseBrandInfoJsonList.length; i++) {
					var cur = data.baseBrandInfoJsonList[i];
					var opt = new Option(cur.brand_name, cur.id + "_" + cur.brand_name);
					$("#select1").get(0).add(opt);
				}
			}
		}
	});
}

function moveSelected(sourceSelect, targetSelect, isDelete){
	var cachOptionsArray = new Array();
	var index = 0;
	for (var i = sourceSelect.options.length - 1; i >= 0; i--){
		if (sourceSelect.options[i].selected){
			cachOptionsArray[index++] = new Option(sourceSelect.options[i].text, sourceSelect.options[i].value);
			if (isDelete == undefined || isDelete == true){
				sourceSelect.options[i] = null;
			}
		}
	}
	var exist = false;
	for (var i = cachOptionsArray.length - 1; i >= 0; i--){
		exist = false;
		for (var j = 0; j < targetSelect.options.length; j++){
			if (targetSelect.options[j].value.toString() == cachOptionsArray[i].value.toString()){
				exist = true; 
				break;
			}
		}
		if (!exist){
			targetSelect.options[targetSelect.options.length] = cachOptionsArray[i];
		}
	}

	var return_value = "";
	for(i = 0; i < targetSelect.options.length; i++){
		if(null != targetSelect.options[i]){
			return_value += "#$" + targetSelect.options[i].value;
		}
	}
	//alert(return_value);
   parent.$.returnValue = return_value;
   
}
//]]></script>
</body>
</html>
