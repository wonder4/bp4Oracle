<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
  <%@ include file="/commons/pages/messages.jsp" %>
  <fieldset style="padding: 10px;margin: 5px;">
  <legend style="color: #F00;font-weight: bold;"><span style="color: #0066FF;font-weight: bold;">【${fn:escapeXml(basePdClass.cls_name)}】</span>已授权的属性</legend>
  <c:if test="${not empty basePdAttributeLinkList}">
    <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
      <tr>
        <th width="20%" nowrap="nowrap">主属性名称 / 显示名称</th>
        <th nowrap="nowrap">子属性显示名称</th>
      </tr>
      <c:forEach var="curLink" items="${basePdAttributeLinkList}">
        <tr>
          <td style="text-align:left;">${fn:escapeXml(curLink.attr_name)} / ${fn:escapeXml(curLink.attr_show_name)}</td>
          <td><c:forEach var="curSonLink" items="${basePdAttributeSonList}">
              <c:if test="${curLink.id eq curSonLink.attr_id}"> ${fn:escapeXml(curSonLink.attr_show_name)}&nbsp; </c:if>
            </c:forEach>
          </td>
        </tr>
      </c:forEach>
    </table>
  </c:if>
  </fieldset>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[

//]]></script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true" />
</body>
</html>
