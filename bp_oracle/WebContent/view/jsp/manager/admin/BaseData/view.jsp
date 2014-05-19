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
  <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
    <tr>
      <th colspan="2"><c:if test="${af.map.type eq 10}">用户类型信息</c:if>
        <c:if test="${af.map.type eq 20}">仓库类型信息</c:if>
      </th>
    </tr>
       <tr>
      <td width="15%" class="title_item" nowrap="nowrap"><c:if test="${af.map.type eq 10}">用户类型</c:if>
        <c:if test="${af.map.type eq 20}">仓库类型</c:if>
        ：</td>
      <td width="85%">${fn:escapeXml(af.map.type_name)}</td>
    </tr>
    <tr>
      <td class="title_item">备注：</td>
      <td>${fn:escapeXml(af.map.remark)}</td>
    </tr>
    <tr>
      <td class="title_item">排序值：</td>
      <td>${af.map.order_value }</td>
    </tr>
    <tr>
      <td colspan="2" style="text-align:center"><input type="button" class="bgButton" value=" 返回 " onclick="history.back();" /></td>
    </tr>
  </table>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true" />
</body>
</html>
