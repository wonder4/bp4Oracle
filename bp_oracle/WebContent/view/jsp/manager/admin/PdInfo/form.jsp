<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/commons/styles/blue/base.css" rel="stylesheet" type="text/css" />
<jsp:include page="../_global_manager_page_head.jsp" flush="true" />
<link href="${ctx}/scripts/jquery-ui/themes/base/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="divContent">
  <div class="subtitle">
    <h3>${naviString}</h3>
  </div>
  <html-el:form action="/admin/PdInfo" enctype="multipart/form-data">
    <html-el:hidden property="queryString" styleId="queryString" />
    <html-el:hidden property="method" styleId="method" value="save" />
    <html-el:hidden property="mod_id" styleId="mod_id" />
    <html-el:hidden property="pd_id" styleId="pd_id" />
    <html-el:hidden property="par_cls_id"/>
    <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0" class="tableClassTbody">
      <tr>
        <th colspan="3">产品基本信息</th>
      </tr>
      <tr>
        <td width="12%" nowrap="nowrap" class="title_item"><span style="color: #F00;">*</span>产品名称：</td>
        <td colspan="2" width="88%"><html-el:text property="pd_name" styleId="pd_name" maxlength="125" style="width:280px" styleClass="webinput" /></td>
      </tr>
      <tr>
        <td width="12%" nowrap="nowrap" class="title_item" ><span style="color: #F00;">*</span>产品类别：</td>
        <html-el:hidden property="cls_id" styleId="cls_id"/>
        <td colspan="2" width="88%"><html-el:text property="cls_name" styleId="cls_name" readonly="true" onclick="getBaseClassList();" style="cursor:pointer;" styleClass="webinput"/></td>
      </tr>
      <jsp:include page="form_attr.jsp"></jsp:include>
      <tr>
        <td width="12%" nowrap="nowrap" class="title_item">产品简介：</td>
        <td colspan="2" width="88%"><html-el:textarea property="pd_desc" styleId="pd_desc" style="width:450px;height:80px;" styleClass="webinput"></html-el:textarea></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item"><span style="color: #F00;">*</span>所属区域：</td>
        <td><select name="province" id="province" style="width:120px;" >
            <option value="">请选择...</option>
          </select>
          <select name="city" id="city" style="width:120px;">
            <option value="">请选择...</option>
          </select>
          <select name="country" id="country" style="width:120px;">
            <option value="">请选择...</option>
          </select></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item"><span style="color: #F00" id="span_main_pic">*</span>主图地址：</td>
        <td colspan="2"><c:if test="${not empty (af.map.main_pic)}"><br/>
            <img src="${ctx}/${fn:substringBefore(af.map.main_pic, '.')}_240.${fn:substringAfter(af.map.main_pic, '.')}"}" /><br />
            <input type="checkbox" name="chkReUploadImage" id="chkReUploadImage" value="1" onclick="$('#main_pic').toggle();" />
            <label for="chkReUploadImage">重新上传主图</label>
            <br/>
            <html-el:file property="main_pic" style="display:none;width:500px;" styleId="main_pic" />
          </c:if>
          <c:if test="${empty (af.map.main_pic)}">
            <html-el:file property="main_pic" style="width:500px;" styleId="main_pic" />
          </c:if>
          <div>上传的主图会自动缩放成合适的尺寸,图片格式仅限于bmp, gif, jpeg, jpg, png。</div></td>
      </tr>
      <tr id="trFile">
        <td width="12%" class="title_item">产品轮播图片：</td>
        <td colspan="2" width="88%"><div id="divFileHidden" style="display: none;">
            <input name="file_hidden" type="file" id="file_hidden" style="width: 500px;" />
            <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelTr" title="删除"/></div>
          <div id="divFile">
            <input name="file_show" type="file" id="file_show" style="width: 500px;" />
            <img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" title="再添加一个" /></div>
          <c:forEach var="cur" items="${af.map.pdImgsList}" varStatus="vs"> <span> <a href="${ctx}/${cur.file_path}" target="_blank"> <img src="${ctx}/${cur.file_path}" style="width:100px;height:58px;"/></a> &nbsp;
            (<a href="javascript:void(0);" id="del_${cur.id}" onclick= "deleteFile('${cur.id}','${cur.file_path}');">删</a>)
            <c:if test="${vs.count%4 eq 0}"><br/>
            </c:if>
            <c:if test="${vs.count%4 ne 0}">&nbsp;&nbsp;</c:if>
            </span></c:forEach></td>
      </tr>
      <tr>
        <td width="12%" nowrap="nowrap" class="title_item"><span style="color: #F00;">*</span>参考价格：</td>
        <td colspan="2" width="88%"><html-el:text property="price_ref" styleId="price_ref" maxlength="10" style="width:100px" styleClass="webinput" />
          &nbsp;&nbsp;<span style="color:#FF0000;">元</span></td>
      </tr><!--
      <tr>
        <td width="12%" nowrap="nowrap" class="title_item">供求量：</td>
        <td colspan="2" width="88%"><html-el:text property="pd_num" styleId="pd_num" maxlength="10" style="width:100px" styleClass="webinput" value="${af.map.pd_num}"/>
          &nbsp;&nbsp;<span style="color:#FF0000;">单位：千克、个</span></td>
      </tr>
      --><tr>
        <td width="12%" nowrap="nowrap" class="title_item"><span style="color: #F00;">*</span>是否上架：</td>
        <td colspan="2" width="88%"><html-el:select property="is_sell" styleId="is_sell">
            <html-el:option value="">请选择...</html-el:option>
            <html-el:option value="0">否</html-el:option>
            <html-el:option value="1">是</html-el:option>
          </html-el:select></td>
      </tr>
      <tr id=up_date_tr>
        <td nowrap="nowrap" class="title_item"><span style="color: #F00;">*</span>上架时间：</td>
        <td  colspan="2" height="24"><fmt:formatDate value="${af.map.up_date}" pattern="yyyy-MM-dd" var="_up_date" />
          <html-el:text property="up_date" styleId="up_date" size="10" maxlength="20" styleClass="webinput" readonly="true" onclick="WdatePicker();" style="cursor:pointer;" value="${_up_date}" /></td>
      </tr>
      <tr id="down_date_tr">
        <td nowrap="nowrap" class="title_item"><span style="color: #F00;">*</span>下架时间：</td>
        <td  colspan="2" height="24"><fmt:formatDate value="${af.map.down_date}" pattern="yyyy-MM-dd" var="_down_date" />
          <html-el:text property="down_date" styleId="down_date" size="10" maxlength="20" styleClass="webinput" readonly="true" onclick="WdatePicker();" style="cursor:pointer;" value="${_down_date}" /></td>
      </tr><!--
      <tr>
        <td width="12%" nowrap="nowrap" class="title_item"><span style="color: #F00;">*</span>最小起订：</td>
        <td colspan="2" width="88%"><html-el:text property="order_min_num" styleId="order_min_num" maxlength="16" style="width:100px" styleClass="webinput" />
          &nbsp;&nbsp;<span style="color:#FF0000;">单位：只、个</span></td>
      </tr>
      <tr>
        <td width="12%" nowrap="nowrap" class="title_item"><span style="color: #F00;">*</span>最大供货：</td>
        <td colspan="2" width="88%"><html-el:text property="order_max_supply" styleId="order_max_supply" maxlength="12" style="width:100px" styleClass="webinput" />
          &nbsp;&nbsp;<span style="color:#FF0000;">单位：千克</span></td>
      </tr>
      --><tr>
        <td width="12%" nowrap="nowrap" class="title_item"><span style="color: #F00;">*</span>是否推荐：</td>
        <td colspan="2" width="88%"><html-el:select property="is_commend" styleId="is_commend">
            <html-el:option value="">请选择...</html-el:option>
            <html-el:option value="0">否</html-el:option>
            <html-el:option value="1">是</html-el:option>
          </html-el:select></td>
      </tr>
      <tr>
        <td width="12%" nowrap="nowrap" class="title_item"><span style="color: #F00;">*</span>是否特价：</td>
        <td colspan="2" width="88%"><html-el:select property="is_spec_price" styleId="is_spec_price">
            <html-el:option value="">请选择...</html-el:option>
            <html-el:option value="0">否</html-el:option>
            <html-el:option value="1">是</html-el:option>
          </html-el:select></td>
      </tr>
      <tr style="display:none;" id="spec_price_tr">
        <td width="12%" nowrap="nowrap" class="title_item">特价价格：</td>
        <td colspan="2" width="88%"><html-el:text property="spec_price" styleId="spec_price" maxlength="10" style="width:100px" styleClass="webinput" />
          &nbsp;&nbsp;<span style="color:#FF0000;"></span></td>
      </tr>
      <tr>
          <td width="12%" nowrap="nowrap" class="title_item" ><span style="color: #F00;">*</span>是否促销：</td>
          <td colspan="2" width="88%"><html-el:select property="is_promotion" styleId="is_promotion" >
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="1">是</html-el:option>
              <html-el:option value="0">否</html-el:option>
            </html-el:select></td>
        </tr>
      <tr>
        <td width="12%" nowrap="nowrap" class="title_item">排序值：</td>
        <td colspan="2" width="88%"><html-el:text property="order_value" styleId="order_value" maxlength="4" style="width:50px" styleClass="webinput" /></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item">产品详细信息：</td>
        <td colspan="2"><html-el:textarea property="pd_content" styleId="pd_content" style="width:650px;height:200px;visibility:hidden;" styleClass="webinput"></html-el:textarea>
          <div>点击【第一排】顺数【最后一个】按钮可实现全屏编辑</div></td>
      </tr>
      <tr>
        <td colspan="3" align="center"><html-el:submit property="" value="保 存" styleClass="bgButton" styleId="btn_submit" />
          &nbsp;
          <html-el:button property="" value="重 填" styleClass="bgButton" styleId="btn_reset" onclick="this.form.reset();" />
          &nbsp;
          <html-el:button property="" value="返 回" styleClass="bgButton" styleId="btn_back" onclick="history.back();" /></td>
      </tr>
    </table>
  </html-el:form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/kindeditor/kindeditor.min.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script>
<script type="text/javascript" src="${ctx}/scripts/asyncbox/asyncbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	var editor = KindEditor.create("textarea[id='pd_content']");
	
	$("#province").attr({"subElement": "city", "defaultText": "请选择...", "defaultValue": "", "selectedValue": "${af.map.province}","datatype": "Require", "msg": "请选择省份"});
	$("#city").attr({"subElement": "country", "defaultText": "请选择...", "defaultValue": "", "selectedValue": "${af.map.city}","datatype": "Require", "msg": "请选择市"});
	$("#country" ).attr({"defaultText": "请选择...", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#province").cs("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);
	$("#province").change();
	
	var f = document.forms[0];

	if("1" == $("#is_spec_price").val()){
		$("#spec_price_tr").show();
		}
	$("#is_spec_price").change(function(){
		if("1" == $("#is_spec_price").val()){
			$("#spec_price_tr").show();
			}else{
			  $("#spec_price_tr").hide();
			  $("#spec_price").val("");
			}
		});

	// 通过判断是否上架控制上架时间和下架时间的显示
	if("1" != $("#is_sell").val()){
		$("#up_date_tr").hide();
        $("#down_date_tr").hide();
	}
	$("#is_sell").change(function(){
         if("1" == $(this).val()){
             $("#up_date_tr").show();
             $("#down_date_tr").show();
             }else{
               $("#up_date").val("");
                $("#down_date").val("");  
                $("#up_date_tr").hide();
                $("#down_date_tr").hide();
              }
		});
	
	$("#order_value").focus(setOnlyNum2);
	$("#price_ref").focus(setOnlyNum);
	//$("#order_min_num").focus(setOnlyNum2);
	//$("#order_max_supply").focus(setOnlyNum);
	$("#spec_price").focus(setOnlyNum);
	//$("#pd_num").focus(setOnlyNum2);

	$("#pd_name").attr("dataType", "Require").attr("msg", "请输入产品名称");
	$("#cls_name").attr("dataType", "Require").attr("msg", "请输入产品类别");
	//$("#is_nx_pd").attr("dataType", "Require").attr("msg", "请选择是否农畜产品");
	<c:if test="${empty (af.map.main_pic)}">
	$("#main_pic").attr("dataType", "Filter" ).attr("msg", "请上传格式为（bmp, gif, jpeg, jpg, png）的主图地址").attr("require", "true").attr("accept", "bmp, gif, jpeg, jpg, png");
	</c:if>
	//$("#own_entp_id").attr("dataType", "Require").attr("msg", "请选择所属企业");
	$("#price_ref").attr("dataType", "Require").attr("msg", "请输入参考价格");
	$("#is_sell").attr("dataType", "Require").attr("msg", "请选择是否上架");
	//$("#up_date").attr("dataType", "Require").attr("msg", "请输入上架时间");
	//$("#down_date").attr("dataType", "Require").attr("msg", "请输入下架时间");
	//$("#order_max_supply").attr("dataType", "Require").attr("msg", "请输入最大供货");
	$("#is_commend").attr("dataType", "Require").attr("msg", "请选择是否推荐");
	$("#is_spec_price").attr("dataType", "Require").attr("msg", "请选择是否特价");
	$("#is_promotion").attr("dataType", "Require").attr("msg", "请选择是否促销");

    //$("#order_min_num").attr("dataType","Integer").attr("msg","请输入整数");
    //$("#pd_num").attr("dataType", "Integer").attr("msg", "请输入整数");
    $("#pd_desc").attr("dataType","Limit").attr("max","128").attr("msg","输入内容不能超过128个汉字");
	

    <c:if test="${not empty pdInfoCustomFieldContentList }">
	 <c:forEach var="cur" items="${pdInfoCustomFieldContentList}">
	 	<c:if test="${cur.is_required eq 1}">
		 	<c:if test="${cur.type eq 3 or cur.type eq 1}">
		 		$("input[type='radio'][name='zdy_content_${cur.custom_field_id}']").eq(0).attr("datatype", "Group").attr("msg", "${fn:escapeXml(cur.custom_field_name)}是必选项！");
		 	</c:if>
		 	<c:if test="${cur.type eq 1 and cur.is_show eq 1}">
	 			$("input[type='text'][name='zdy_content2_${cur.custom_field_id}']").eq(0).attr("datatype", "Require").attr("msg", "${fn:escapeXml(cur.custom_field_name)}必须输入值！");
	 		</c:if>
		 	<c:if test="${cur.type eq 4}">
		 	$("input[type='checkbox'][name='zdy_content_${cur.custom_field_id}']").eq(0).attr("datatype", "Group").attr("msg", "${fn:escapeXml(cur.custom_field_name)}是必选项！");
			</c:if>
		 	<c:if test="${cur.type eq 5}">
		 		$("#zdy_${cur.custom_field_id}").attr("dataType", "Require").attr("msg", "${fn:escapeXml(cur.custom_field_name)}必须选择");
		 	</c:if>
		</c:if>
	 </c:forEach>
	</c:if>
    
	// 主图
	$("#main_pic").change(function(){
		var img_div = $("#img_div");
		img_div.empty();
		if (this.value != '') {
			$("<img />").attr("src",this.value).appendTo(img_div).parent().show();
		}
	});
	<c:if test="${not empty af.map.main_pic}">
		$("<img />").attr("src",'${af.map.main_pic}').appendTo($("#img_div")).parent().show();
	</c:if>

	f.onsubmit = function(){
		$("#pd_content").val(editor.html());
		if(Validator.Validate(f, 1)){
			if("1" == $("#is_sell").val()){
			 if($("#up_date").val()>=$("#down_date").val()){
				alert("下架时间必须大于上架时间");
				return false;
				}
			}
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			f.submit();
		}
	return false;
	};

    $("#imgAddTr").click(function (){
		$("#divFileHidden").clone().find("#file_hidden").attr("name", "file_" + new Date().getTime()).end().appendTo($("#divFile")).show();
	    $("img[id='imgDelTr']").each(function(){
			$(this).click(function (){
				$(this).parent().remove();
			});
		});
	});
});

function getBaseClassList() {
	var url = "CsAjax.do?method=getBaseClassList&pd_class_type=-1&azaz=" + Math.random();
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
				$("#cls_id").val(returnValue.cls_id);
				$("#cls_name").val(returnValue.cls_name);
				if("" != returnValue.cls_id && null != returnValue.cls_id){
					getAttrToHtml("${ctx}/manager/admin/CsAjax.do?method=getPdAttrbute",returnValue.cls_id);
				}
			}
		}
	});
};
   
function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	})
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		if(this.value.length == 0) this.value = 0;
	})
}

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

function deleteFile(id, file_path){
	 var k = window.confirm("您确定要删除吗?");
	 if (k) {
			$.post("PdInfo.do?method=deleteFile" , {
				id : id , file_path : file_path
			}, function(d){
				if(d == "success"){
					$("#del_" + id).parent().remove();
				}
			});
	 } 
}

//]]></script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true"/>
</body>
</html>
