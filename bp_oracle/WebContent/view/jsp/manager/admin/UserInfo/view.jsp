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
      <th colspan="2">用户基本信息</th>
    </tr>
    <tr>
      <td width="15%" class="title_item">登录名：</td>
      <td width="85%">${fn:escapeXml(af.map.user_name)}</td>
    </tr>
    <tr>
      <td class="title_item" nowrap="nowrap">真实姓名：</td>
      <td>${fn:escapeXml(af.map.real_name)}</td>
    </tr>
    <tr>
      <td class="title_item">用户类型：</td>
      <td><c:forEach items="${baseData10List}" var="cur">
          <c:if test="${af.map.user_type eq cur.type_value}">${fn:escapeXml(cur.type_name)}</c:if>
        </c:forEach></td>
    </tr>
    <tr>
      <td class="title_item">所属部门：</td>
      <td><c:out value="${af.map.dept_name}" /></td>
    </tr>
    <tr>
      <td class="title_item">性别：</td>
      <td><c:out value="${af.map.sex eq 0 ? '男' : '女'}" /></td>
    </tr>
    <tr>
      <td class="title_item">生日：</td>
      <td><fmt:formatDate value="${af.map.birthday}" pattern="yyyy-M-d" /></td>
    </tr>
    <tr>
      <td class="title_item">手机：</td>
      <td>${fn:escapeXml(af.map.mobile)}</td>
    </tr>
    <tr>
      <td class="title_item">办公室电话：</td>
      <td>${fn:escapeXml(af.map.office_tel)}</td>
    </tr>
    <tr>
      <td class="title_item">电子信箱：</td>
      <td>${fn:escapeXml(af.map.email)}</td>
    </tr>
    <tr>
      <td class="title_item">最后登录IP：</td>
      <td>${fn:escapeXml(af.map.last_login_ip)}</td>
    </tr>
    <tr>
      <td class="title_item">最后登录时间：</td>
      <td><fmt:formatDate value="${af.map.last_login_time}" pattern="yyyy-M-d" /></td>
    </tr>
    <tr>
      <td class="title_item">登录次数：</td>
      <td>${af.map.login_count}</td>
    </tr>
    <tr>
      <td class="title_item">排序值：</td>
      <td>${af.map.order_value}</td>
    </tr>
    <c:if test="${af.map.is_del eq 1}"> </c:if>
    <tr>
      <td colspan="2" style="text-align:center"><input type="button" class="bgButton" value=" 返回 " onclick="history.back();" /></td>
    </tr>
  </table>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true" />
</body>
</html>
