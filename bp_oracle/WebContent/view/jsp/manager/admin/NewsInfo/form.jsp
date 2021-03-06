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
  <html-el:form action="/admin/NewsInfo" enctype="multipart/form-data">
    <html-el:hidden property="queryString" styleId="queryString" />
    <html-el:hidden property="method" styleId="method" value="save" />
    <html-el:hidden property="mod_id" styleId="mod_id" />
    <html-el:hidden property="id" styleId="id" />
    <html-el:hidden property="upload_image_files" styleId="upload_image_files"/>
    <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
      <tr>
        <th colspan="2">新闻基本信息</th>
      </tr>
      <tr>
        <td width="15%" class="title_item" nowrap="nowrap"><span style="color: #F00;">*</span>标题：</td>
        <td width="85%"><html-el:text property="title" styleId="title" maxlength="125" style="width:480px" styleClass="webinput" />
          <html-el:hidden property="title_color" styleId="title_color" style="display:none;"/>
          <span title="字体加粗">
          <html-el:checkbox style="vertical-align:bottom;" property="title_is_strong" styleId="title_is_strong" onclick="checkweight();" value="1"  />
          <label for="title_is_strong" style="cursor:pointer;vertical-align:bottom">加粗</label>
          </span></td>
      </tr>
      <tr>
        <td class="title_item" nowrap="nowrap">是否启用直接URI：</td>
		<td><label for="is_use_direct_uri1" style="width:80px;">
          <html-el:radio property="is_use_direct_uri" styleClass="is_use_direct_uri" styleId="is_use_direct_uri1" value="0">否</html-el:radio>
          </label>
          <label for="is_use_direct_uri2" style="width:80px;">
          <html-el:radio property="is_use_direct_uri" styleClass="is_use_direct_uri" styleId="is_use_direct_uri2" value="1">是</html-el:radio>
          </label>
          <div id="urlddiv" style="display:${af.map.is_use_direct_uri eq 1 ? 'block' : 'none'};">
          <html-el:text property="direct_uri" styleId="direct_uri" style="width:480px;" styleClass="webinput" maxlength="128" />
          </div>启用直接URI后，点击新闻标题后直接转向到此URI。
		</td>
      </tr>      
      <tr>
        <td class="title_item" nowrap="nowrap">所属语言：</td>
        <td><html-el:select property="locale_name" styleId="locale_name">
            <html-el:option value="zh_CN">中文简体</html-el:option>
            <html-el:option value="zh_TW">中文繁体</html-el:option>
            <html-el:option value="en">英文</html-el:option>
          </html-el:select></td>
      </tr>
      <tr>
        <td class="title_item" nowrap="nowrap">短标题：</td>
        <td><html-el:text property="title_short" styleId="title_short" maxlength="125" style="width:597px" styleClass="webinput" /></td>
      </tr>
      <tr>
        <td class="title_item" nowrap="nowrap">副标题：</td>
        <td><html-el:text property="title_sub" styleId="title_sub" maxlength="125" style="width:597px" styleClass="webinput" /></td>
      </tr>
      <tr>
        <td class="title_item" nowrap="nowrap">作者：</td>
        <td><html-el:text property="author" styleId="author" maxlength="30" style="width:120px" styleClass="webinput" /></td>
      </tr>
      <tr>
        <td class="title_item" nowrap="nowrap">信息来源：</td>
        <td><html-el:text property="info_source" styleId="info_source" maxlength="30" style="width:240px" styleClass="webinput" /></td>
      </tr>
      <tr>
        <td class="title_item" nowrap="nowrap">摘要：</td>
        <td><html-el:textarea styleId="summary" property="summary" rows="7" style="width:597px" /></td>
      </tr>
      <tr>
        <td class="title_item" nowrap="nowrap"><span style="color: #F00;">*</span>新闻内容：</td>
        <td><html-el:textarea property="content" styleId="content" style="width:650px;height:200px;visibility:hidden;"></html-el:textarea>
          <jsp:include page="../_global_editor_tip.jsp" flush="true"/></td>
      </tr>
      <tr>
        <td class="title_item" nowrap="nowrap">主图地址：</td>
        <td><c:if test="${not empty (af.map.image_path)}" var="hasImage" >
            <div id="image_path_div"> <img src="${ctx}/${fn:substringBefore(af.map.image_path, '.')}_240.${fn:substringAfter(af.map.image_path, '.')}" title="${af.map.image_desc}" /> (<a href="javascript:void(0);" onclick= "deleteImageOrVideo('${af.map.id}', 'image_path', '${af.map.image_path}') ">删除主图</a>)</div>
            <input type="checkbox" name="chkReUploadImage" id="chkReUploadImage" value="1" onclick="$('#image_path').toggle();" />
            <label for="chkReUploadImage">重新上传主图</label>
            <br/>
            <html-el:file property="image_path" style="display:none;width:500px;" styleId="image_path" />
          </c:if>
          <c:if test="${empty (af.map.image_path)}">
            <html-el:file property="image_path" style="width:500px;" styleId="image_path" />
          </c:if>
          <div>只支持 <span id="image_tip"></span> 格式的图片。</div>
          <div>上传的主图会自动缩放成合适的尺寸，主图宽高比例最好是4:3，否则在幻灯片显示中会变形。</div></td>
      </tr>
      <tr>
        <td class="title_item" nowrap="nowrap">主图说明：</td>
        <td><html-el:text property="image_desc" styleId="image_desc" maxlength="120" style="width:450px" styleClass="webinput" /></td>
      </tr>
      <tr>
        <td class="title_item"><strong>视频地址：</strong></td>
        <td colspan="2"><c:if test="${not empty (af.map.video_path)}" >
            <div align="left" style="margin-top:10px; margin-bottom:10px;" id="video_path_div"> 
              <script type="text/javascript" src="${ctx}/commons/scripts/mediaPlayer.js"></script> 
              <script type="text/javascript" src="${ctx}/scripts/flash/AC_RunActiveContent.js"></script> 
              <script type="text/javascript">//<![CDATA[
				  <c:if test="${fn:substringAfter(af.map.video_path, '.') eq 'flv'}" var="is_flv">
				  playFlv("${ctx}/${af.map.video_path}", "${ctx}/${af.map.image_path}", "400", "300", "${ctx}/scripts/flash/jcplayer");
				  </c:if>
				  <c:if test="${not is_flv}">
				  var mediaPlayerFactory = new MediaPlayerFactory(null, 400, 300, "${ctx}/${af.map.video_path}");
				  mediaPlayerFactory.init();
				  document.write(mediaPlayerFactory.getMediaPlayer(2)); 
				  </c:if>
				 //]]></script> 
              (<a href="javascript:void(0);" onclick= "deleteImageOrVideo('${af.map.id}', 'video_path', '${af.map.video_path}') ">删除视频</a>) </div>
            <br />
            <input type="checkbox" name="chkReUploadVideo" id="chkReUploadVideo" value="1" onclick="$('#video_path').toggle();" />
            <label for="chkReUploadVideo">重新上传视频</label>
            <br/>
            <html-el:file property="video_path" style="display:none;width:500px;" styleId="video_path" />
          </c:if>
          <c:if test="${empty (af.map.video_path)}">
            <html-el:file property="video_path" style="width:500px;" styleId="video_path"/>
          </c:if>
          <div>只支持 <span id="video_tip"></span> 格式的视频。</div></td>
      </tr>
      <tr>
        <td class="title_item"><strong>视频说明：</strong></td>
        <td colspan="2"><html-el:text property="video_desc" maxlength="120" style="width:450px" /></td>
      </tr>
      <tr id="trFile">
        <td class="title_item" nowrap="nowrap">上传附件：</td>
        <td><div id="divFileHidden" style="display: none;">
            <input name="file_hidden" type="file" id="file_hidden" style="width: 500px;" />
            <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelTr" title="删除"/></div>
          <div id="divFile">
            <input name="file_show" type="file" id="file_show" style="width: 500px;" />
            <img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" title="再添加一个" /><br/>
          </div>
          <c:forEach var="cur" items="${attachmentList}" varStatus="vs"> <div>${vs.count}、<a href="${ctx}/${cur.save_path}" target="_blank">${cur.file_name}</a> &nbsp;
            (<a href="javascript:void(0);" id="del_${cur.id}" onclick= "deleteFile('${cur.id}','${cur.save_path}');" title="删除此附件">删</a>) <br />
            </div></c:forEach>
          <div>只支持 <span id="attachment_tip"></span> 格式的附件。</div></td>
      </tr>
      <tr>
        <td class="title_item" nowrap="nowrap"><span style="color: #F00;">*</span>发布时间：</td>
        <td  height="24" ><fmt:formatDate value="${af.map.pub_time}" pattern="yyyy-MM-dd" var="_pub_time" />
          <html-el:text property="pub_time" styleId="pub_time" size="10" maxlength="20" styleClass="webinput" readonly="true" onclick="WdatePicker();" style="cursor:pointer;" value="${_pub_time}" /></td>
      </tr>
      <tr>
        <td class="title_item" nowrap="nowrap">是否使用失效时间：</td>
        <td height="24" ><html-el:select property="is_use_invalid_date" styleId="is_use_invalid_date" >
            <html-el:option value="0">不使用</html-el:option>
            <html-el:option value="1">使用</html-el:option>
          </html-el:select></td>
      </tr>
      <tr id="tr_invalid_date" style="display: none;">
        <td class="title_item" nowrap="nowrap"><span style="color: #F00;">*</span>失效时间：</td>
        <td height="24" ><fmt:formatDate value="${af.map.invalid_date}" pattern="yyyy-MM-dd" var="_invalid_date" />
          <html-el:text property="invalid_date" styleId="invalid_date" size="10" maxlength="20" styleClass="webinput" readonly="true" onclick="WdatePicker();" value="${_invalid_date}" /></td>
      </tr>
      <tr>
        <td class="title_item" nowrap="nowrap">排序值：</td>
        <td><html-el:text property="order_value" styleId="order_value" maxlength="4" size="4" styleClass="webinput" />
          值越大，显示越靠前，范围：0-9999</td>
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/commons/kindeditor/kindeditor.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/colorPicker/mColorPicker.min.js"></script> 
<script type="text/javascript"><!--//<![CDATA[
$("#title_color").attr({"hex":"true"}).mColorPicker({imageFolder: '${ctx}/scripts/colorPicker/images/'});

var editor = KindEditor.create("textarea[id='content']");
//var editor = KindEditor.create("textarea[name='content']",{items:KindEditor.simpleItems});
$(document).ready(function(){
	$("#is_use_invalid_date").change(function(){
		var value = $(this).val();
		//alert(value);
		if("1" == value){
			$("#tr_invalid_date").show();
			$("#invalid_date").attr("dataType", "Require").attr("msg", "失效时间必须填写");
		}else{
			$("#tr_invalid_date").hide();
			$("#invalid_date").removeAttr("dataType");
		}
	});

	if($("#is_use_invalid_date").val() == "1"){
		$("#tr_invalid_date").show();
		$("#invalid_date").attr("dataType", "Require").attr("msg", "失效时间必须填写");
	}
	
	var $t = $("#title");
	<c:if test="${not empty af.map.title_color}">
		$t.css("color", '${af.map.title_color}');
	</c:if>
	<c:if test="${1 eq (af.map.title_is_strong)}">
		$t.css("font-weight", "bold");
	</c:if>
	
    $("#imgAddTr").click(function (){
		$("#divFileHidden").clone().find("#file_hidden").attr("name", "file_" + new Date().getTime()).end().appendTo($("#divFile")).show();
	    $("img[id='imgDelTr']").each(function(){
			$(this).click(function (){
				$(this).parent().remove();
			});
		});
	});

	//var acceptUploadFileExts = "7z, aiff, asf, avi, bmp, csv, doc, docx, fla, flv, gif, gz, gzip, jpeg, jpg, mid, mov, mp3, mp4, mpc, mpeg, mpg, ods, odt, pdf, png, ppt, pxd, qt, ram, rar, rm, rmi, rmvb, rtf, sdc, sitd, swf, sxc, sxw, tar, tgz, tif, tiff, txt, vsd, wav, wma, wmv, xls, xml, zip";
	var acceptUploadAttachmentFileExts = "doc, docx, xls, xlsx, ppt, pptx, txt, bmp, gif, jpeg, jpg, png, rar, zip, flv, avi";
	$("#attachment_tip").text(acceptUploadAttachmentFileExts);
	
	var acceptUploadImageFileExts = "bmp, gif, jpeg, jpg, png";
	$("#image_tip").text(acceptUploadImageFileExts);
	
	var acceptUploadVideoFileExts = "flv, avi, mp4, wmv";
	$("#video_tip").text(acceptUploadVideoFileExts);
	
	$("#title"		 ).attr("dataType", "Require").attr("msg", "标题必须填写");
	$("#order_value" ).attr("dataType", "Number" ).attr("msg", "排序值必须为正整数");
	$("#direct_uri"  ).attr("dataType", "Url"    ).attr("msg", "直接URI格式不合法").attr("require", "false");
	//$("#invalid_date").attr("dataType", "Require").attr("msg", "失效时间必须填写").attr("require", "false");
	$("#image_path"  ).attr("dataType", "Filter" ).attr("msg", "您上传的主图文件格式不受支持。支持格式：" + acceptUploadImageFileExts).attr("require", "false").attr("accept", acceptUploadImageFileExts);
	$("#video_path"  ).attr("dataType", "Filter" ).attr("msg", "您上传的视频文件格式不受支持。支持格式：" + acceptUploadVideoFileExts).attr("require", "false").attr("accept", acceptUploadVideoFileExts);
	$("#file_show"   ).attr("dataType", "Filter" ).attr("msg", "您上传的附件文件格式不受支持。支持格式：" + acceptUploadAttachmentFileExts).attr("require", "false").attr("accept", acceptUploadAttachmentFileExts);
	$("#file_hidden" ).attr("dataType", "Filter" ).attr("msg", "您上传的附件文件格式不受支持。支持格式：" + acceptUploadAttachmentFileExts).attr("require", "false").attr("accept", acceptUploadAttachmentFileExts);
	$("#pub_time"    ).attr("datatype", "Require").attr("msg", "发布时间必须选择");
	$("#summary"     ).attr("datatype", "Limit"  ).attr("max", "500").attr("msg", "摘要内容必须在500个字之内");

<c:if test="${af.map.is_use_direct_uri eq '1'}">
	$("#direct_uri").attr("dataType", "Url").attr("msg", "请按有效格式填写URI，如http://www.baidu.com").attr("require", "true");
</c:if>
	
	//是否启用URI，默认值为否
	$(".is_use_direct_uri").click(function(){
		var is_use_direct_uri = $(this).val();
		switch(is_use_direct_uri){
		case '1' :
			$("#urlddiv").show();
			$('#direct_uri').val("http://");
		    $("#direct_uri").attr("dataType", "Url").attr("msg", "请按有效格式填写URI，如http://www.baidu.com").attr("require", "true");
			break;
		case '0' :
			$("#urlddiv").hide();
			$('#direct_uri').val("");
			$("#direct_uri").removeAttr("dataType", "Url");
			break;
		}
	});

	String.prototype.trim = function(){
        return this.replace(/(^\s*)|(\s*$)/g, "");
    }
	$t.blur(function() {
        $(this).val(this.value.trim());                           
    });
	
	$("#show_win").click(function() {
		$("#win").dialog( {
			open : function() {
				$("body > div[role=dialog]").appendTo($(document.forms[0]));
			},
			buttons : {
				"确定" : function() {
							$(this).dialog("close");
							var c = $("input[name='title_color']:checked").val();
							$("#title").css("color", c);
						},
				"取消" : function() {$(this).dialog("close");}
			},
			modal : true,
			title : '选择标题颜色'
		}).dialog("open");
	});
	
	var f = document.forms[0];
	f.onsubmit = function () {
		if (editor.isEmpty()) {
			alert("新闻内容必须填写");
			editor.focus();
			return false;
		}
		$("#content").val(editor.html());
		
		if(Validator.Validate(this, 1)){
            $("#b_s").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			f.submit();
		}
		return false;
	}
});

function checkweight() {
	var c = document.getElementById('title_is_strong');
	var t = document.getElementById('title');
	if (c.checked) {
		$(t).css("font-weight", "bold");
	} else {
		$(t).css("font-weight", "normal");
	}
}

function deleteFile(id, file_path){
	 var k = window.confirm("该操作直接删除文件并且无法恢复，您确定要删除吗?");
	 if (k) {
			$.post("NewsInfo.do?method=deleteFile" , {
				id : id , file_path : file_path
			}, function(d){
				if(d == "success"){
					$("#del_" + id).parent().remove();
				}
			});
	 } 
}


function deleteImageOrVideo(id, type, file_path){
	 var k = window.confirm("该操作直接删除文件并且无法恢复，您确定要删除吗?");
	 if (k) {
			$.post("NewsInfo.do?method=deleteImageOrVideo" , {
				id : id ,type: type, file_path : file_path
			}, function(d){
				if(d == "success"){
					$("#" + type + "_div").remove();
				}
			});
	 } 
}
//]]></script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true"/>
</body>
</html>