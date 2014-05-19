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
  <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
			<tr class="tt2c">
	          <td class="title_item" nowrap="nowrap" width="15%">用户名：</td>
	          <td class="tt2c" align="left" width="85%">${fn:escapeXml(af.map.user_name)}</td>
			</tr>	
			<tr class="tt2c">
	          <td class="title_item" nowrap="nowrap" width="15%">真实姓名：</td>
	          <td class="tt2c" align="left" width="85%">${fn:escapeXml(af.map.real_name)}</td>
			</tr>	
			<tr class="tt2c">
	          <td class="title_item" nowrap="nowrap" width="15%">性别：</td>
	          <td class="tt2c" align="left" width="85%"><c:choose>					
								<c:when test="${'1' eq af.map.sex}">女</c:when>
								<c:when test="${'0' eq af.map.sex}">男</c:when>
						</c:choose></td>
			</tr>	
			<tr class="tt2c">
	          <td class="title_item" nowrap="nowrap" width="15%">年龄：</td>
	          <td class="tt2c" align="left" width="85%">${fn:escapeXml(af.map.age)}</td>
			</tr>	
			<tr class="tt2c">
	          <td class="title_item" nowrap="nowrap" width="15%">文化程度：</td>
	          <td class="tt2c" align="left" width="85%"><c:choose>					
								<c:when test="${'小学' eq af.map.education}">小学</c:when>
								<c:when test="${'中学' eq af.map.education}">中学</c:when>
								<c:when test="${'大学' eq af.map.education}">大学</c:when>
						</c:choose></td>
			</tr>	
			<tr class="tt2c">
	          <td class="title_item" nowrap="nowrap" width="15%">出生年份：</td>
	          <td class="tt2c" align="left" width="85%"><fmt:formatDate value="${af.map.birth_year}" pattern="yyyy-MM-dd" /></td>
			</tr>	
			<tr class="tt2c">
	          <td class="title_item" nowrap="nowrap" width="15%">备注：</td>
	          <td class="tt2c" align="left" width="85%">${fn:replace(fn:escapeXml(af.map.user_desc), g_java_n, g_html_br)}</td>
			</tr>	
            <tr>
	          <td>&nbsp;</td>
	          <td><html-el:button property="" value="返 回" styleClass="bgButton" styleId="btn_back" onclick="history.back();" /></td>
	        </tr>		             
  </table>
</div>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true"/>
</body>
</html>