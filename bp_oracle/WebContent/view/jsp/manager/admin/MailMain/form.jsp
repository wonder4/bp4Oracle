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
  <html-el:form action="/admin/MailMain" enctype="multipart/form-data">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="mail_state" styleId="mail_state" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
          <tr>
            <td width="10%" align="left" id="add_rec_user" class="title_item" nowrap="nowrap">收件人：</td>
            <td width="90%"><html-el:hidden property="rec_user_ids" styleId="rec_user_ids" />
              <html-el:text property="receive_names" styleId="receive_names" style="width:63%;cursor:pointer;" styleClass="selblue4" />
             &nbsp; <input type="button" class="bgButton" value="添加抄送" id="addCC_div"/>
              <input type="button" class="bgButton" value="删除抄送" id="deleteCC_div"  style="display:none;cursor:pointer;"/>
              </td>
          </tr>
          <tr id="cc_tr" style="display:none">
            <td width="10%" align="center" id="add_cc_user" class="title_item" nowrap="nowrap">抄送人：</td>
            <td width="90%"><html-el:hidden property="cc_user_ids" styleId="cc_user_ids" />
              <html-el:text property="cc_names" styleId="cc_names" style="width:63%;cursor:pointer;" styleClass="selblue4" /></td>
          </tr>
          <tr>
            <td width="10%" align="left" class="title_item" nowrap="nowrap">主题：</td>
            <td   width="90%"><html-el:text property="title" styleId="title" style="width:63%;" styleClass="selblue4" maxlength="25" /></td>
          </tr>
          <tr>
            <td width="10%" align="left" class="title_item" nowrap="nowrap">附件：</td>
            <td   width="90%"><div id="divFileHidden" style="display: none;">
                <input name="file_hidden" type="file" id="file_hidden" style="width:63%;height:24px;"  class="selblue4"/>
                <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;line-height:20px;" id="imgDelTr" title="删除"/></div>
              <div id="divFile">
                <input name="file_show" type="file" id="file_show" style="width:63%;height:24px;"   class="selblue4"/>
                <img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer; line-height:20px;" id="imgAddTr" title="再添加一个" /></div></td>
          </tr>
          <c:if test="${not empty attachmentList}">
            <tr>
              <td width="10%" align="left" class="title_item" nowrap="nowrap">已上传附件：</td>
              <td   width="90%"><ol>
                  <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
                    <li><a href="${ctx}/${cur.save_path}" target="_balnk">${cur.file_name}</a><a href="#" id="att_del_${cur.id}">
                      <html-el:hidden property="copy_file_id" value="${cur.id}" />
                      <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;line-height:20px;" id="imgDelTr" title="删除"/></a></li>
                  </c:forEach>
                </ol></td>
            </tr>
          </c:if>
          <tr>
            <td width="10%" align="left" class="title_item" nowrap="nowrap">正文：</td>
            <td   width="90%">
            	<html-el:textarea property="content" styleId="content" style="width:660px;height:200px;visibility:hidden;"></html-el:textarea>
            </td>
          </tr>
       	 <tr>
              <td>&nbsp;</td>
              <td><input type="button" class="bgButton" value=" 发 送 " id="btn_submit"  />
                 <input type="button" name="" value=" 返 回 " onclick='javascript:window.history.back(-1);' id="btn_back" class="bgButton" />
                </td>
          </tr>
             </table>
  </html-el:form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/commons/kindeditor/kindeditor.min.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var editor = KindEditor.create("textarea[id='content']");
	$("#title").attr("dataType", "Require").attr("msg", "请输入主题");
	$("#rec_user_ids").attr("dataType", "Require").attr("msg", "请选择收件人");
	
	    $("#imgAddTr").click(function (){
	        $("#divFileHidden").clone().find("#file_hidden").attr("name", "file_" + new Date().getTime()).end().appendTo($("#divFile")).show();
	        $("img[id='imgDelTr']").each(function(){
	            $(this).click(function (){
	                $(this).parent().remove();
	            });
	        });
	    });

	 var acceptUploadFileExts = "7z, bmp, csv, doc, gif, gz, gzip, jpeg, jpg, pdf, png, ppt, rar, tar, xls, zip";
	 $("#file_show"   ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持，系统支持的文件格式有： " +　acceptUploadFileExts).attr("require", "false").attr("accept", acceptUploadFileExts);
	 $("#file_hidden" ).attr("dataType", "Filter" ).attr("msg", "您上传的文件格式不受支持，系统支持的文件格式有： " +　acceptUploadFileExts).attr("require", "false").attr("accept", acceptUploadFileExts);
		 	    
	 $("#addCC_div").click(function(){
			$("#cc_tr").show();
			$("#addCC_div").hide();
			$("#deleteCC_div").show();
		 } );
	 $("#deleteCC_div").click(function(){
		 $("#cc_names").val("");
		 $("#cc_user_ids").val("");
			$("#cc_tr").hide();
			$("#addCC_div").show();
			$("#deleteCC_div").hide();
		 } );
	 
	 $("#btn_submit").click(function(){
		 $("#mail_state").val(1);  //发送
		 $("#content").val(editor.html());
		 if(Validator.Validate(this.form, 1)){
			this.form.submit();
		 }
        });
	 
});
//收件人
$("#receive_names").click(function(){
	var user_ids = $("#rec_user_ids").val();
	var returnValue = window.showModalDialog("SelectUserInfo.do?azaz=" + Math.random() + "&method=list&selectedUserIds=" + user_ids + "%20", window, "dialogWidth:500px;status:no;dialogHeight:415px;scroll:no");
    if(returnValue != null) {
        var names = returnValue.user_names;
        	names = names.substring(0, names.length - 1);
        $("#receive_names").val(names);
        $("#rec_user_ids").val(returnValue.user_ids.substring(0,returnValue.user_ids.length - 1));
	};
});

//抄送人
$("#cc_names").click(function(){
	var user_ids = $("#cc_user_ids").val();
	var returnValue = window.showModalDialog("SelectUserInfo.do?azaz=" + Math.random() + "&method=list&selectedUserIds=" + user_ids + "%20", window, "dialogWidth:500px;status:no;dialogHeight:415px;scroll:no");
    if(returnValue != null) {
        var names = returnValue.user_names;
        	names = names.substring(0, names.length - 1);
        $("#cc_names").val(names);
        $("#cc_user_ids").val(returnValue.user_ids.substring(0,returnValue.user_ids.length - 1));
	};
});
//]]>
</script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true"/>
</body>
</html>