<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="description" content="${fn:escapeXml(fnx:abbreviate(af.map.content, 2 * 50, ''))}" />
<meta name="keywords" content="${fn:escapeXml(af.map.title)}" />
<title>${fn:escapeXml(af.map.title)} - 新闻详细 - 内蒙古商务厅</title>
<link href="${ctx}/styles/index/css/common.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/index/css/head.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/index/css/union_city.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="../_header.jsp" flush="true" />
<!--first start -->
<div class="mainbox">
  <c:set var="info_source_min" value="${fn:escapeXml(fnx:abbreviate(af.map.info_source, 2 * 6, ''))}"></c:set>
  <div style="margin:0 auto; text-align:center">
    <table width="90%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px; margin:0 auto">
      <tr>
        <td align="center" class="jqjd_cont_tit"><c:if var="colorIsEmpty" test="${empty (af.map.title_color)}"><span style=" <c:if test='${af.map.title_is_strong eq 1}'>font-weight:bold;</c:if>">${fn:escapeXml(af.map.title)} </span></c:if>
          <c:if test="${not colorIsEmpty}"> <span style="color:${af.map.title_color}; <c:if test='${af.map.title_is_strong eq 1}'>font-weight:bold;</c:if>">${fn:escapeXml(af.map.title)}</span> </c:if></td>
      </tr>
      <tr>
        <td align="center" class="jqjd_cont_tit1">字体大小：[<a href="javascript:doZoom('16px');">大</a> <a href="javascript:doZoom('14px');">中</a> <a href="javascript:doZoom('12px');">小</a>]　|　
          <fmt:formatDate value="${af.map.update_time}" pattern="yyyy-MM-dd HH:mm"/>
          | 来源：<span title="${fn:escapeXml(af.map.info_source)}">${info_source_min}</span> | 浏览次数：${af.map.view_count}　&nbsp;<a href="javascript:closeWindow();">【关闭】</a></td>
      </tr>
      <c:if test="${not empty (af.map.image_path)}">
        <tr>
          <td align="center"><img src="${ctx}/${fn:substringBefore(af.map.image_path, '.')}_600.${fn:substringAfter(af.map.image_path, '.')}" alt="" title="${fn:escapeXml(af.map.image_desc)}" /></td>
        </tr>
      </c:if>
      <c:if test="${not empty (af.map.video_path)}">
        <tr>
          <td><div align="center" style="margin-top:10px; margin-bottom:10px;"> 
              <script type="text/javascript" src="${ctx}/commons/scripts/mediaPlayer.js"></script> 
              <script type="text/javascript" src="${ctx}/scripts/flash/AC_RunActiveContent.js"></script> 
              <script type="text/javascript">//<![CDATA[
				 <c:if test="${fn:substringAfter(af.map.video_path, '.') eq 'flv'}" var="is_flv">
				 playFlv("${ctx}/${af.map.video_path}", "${ctx}/${af.map.image_path}", "500", "400", "${ctx}/scripts/flash/jcplayer");
				 </c:if>
				 <c:if test="${not is_flv}">
				 var mediaPlayerFactory = new MediaPlayerFactory(null, 500, 400, "${ctx}/${af.map.video_path}");
				 mediaPlayerFactory.init();
				 document.write(mediaPlayerFactory.getMediaPlayer(2)); 
				 </c:if>
			  //]]></script> 
            </div></td>
        </tr>
      </c:if>
      <tr>
        <td align="center" class="gran14px" style="text-align: justify; padding-left:14px; padding-right:14px; text-justify:inter-ideograph; padding-top: 30px; line-height: 25px;" id="newscontent"> ${af.map.content}</td>
      </tr>
      <tr valign="top">
        <td align="left"><c:if test="${not empty (attachmentList)}">
            <fieldset style="margin:5px; padding:10px; border:1px solid #ccc;">
              <legend style="font-size:12px;">附件下载</legend>
              <c:forEach var="cur" items="${attachmentList}" varStatus="vs"><a href="${ctx}/${cur.save_path}">${cur.file_name}</a><br />
              </c:forEach>
            </fieldset>
          </c:if></td>
      </tr>
    </table>
  </div>
  <div class="clear"></div>
</div>
<!--first end -->
<jsp:include page="../_footer.jsp" flush="true" />
<script type="text/javascript" src="${ctx}/scripts/news.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

});
//]]>
</script>
<jsp:include page="../_public_page.jsp" flush="true" />
</body>
</html>
