<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/commons/styles/blue/base.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="divContent">
  <div class="subtitle">
    <h3>${naviString}</h3>
  </div>
  <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
    <tr>
      <th colspan="2">问题</th>
    </tr>
    <tr>
      <td width="15%" nowrap="nowrap" class="title_item">标题：</td>
      <td><c:out value="${af.map.q_title}" /></td>
    </tr>
    <tr>
      <td width="15%" nowrap="nowrap" class="title_item">提问内容：</td>
      <td>${af.map.q_content}</td>
    </tr>
    <tr>
      <td nowrap="nowrap" class="title_item">提问时间：</td>
      <td><fmt:formatDate value="${af.map.q_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    </tr>
    <tr>
      <th colspan="2" nowrap="nowrap">提问人基本信息</th>
    </tr>
    <tr>
      <td nowrap="nowrap" class="title_item">提问人姓名：</td>
      <td><c:out value="${af.map.q_name}" /></td>
    </tr>
    <tr>
      <td nowrap="nowrap" class="title_item">提问人电话：</td>
      <td><c:out value="${af.map.q_tel}" /></td>
    </tr>
    <tr>
      <td nowrap="nowrap" class="title_item">提问人邮箱：</td>
      <td><c:out value="${af.map.q_email}" /></td>
    </tr>
    <tr>
      <td nowrap="nowrap" class="title_item">提问人联系地址：</td>
      <td><c:out value="${af.map.q_addr}" /></td>
    </tr>
    <tr>
      <td nowrap="nowrap" class="title_item">提问人IP：</td>
      <td><c:out value="${af.map.q_ip}" /></td>
    </tr>
    <tr>
      <th colspan="2" nowrap="nowrap">回答</th>
    </tr>
    <tr>
      <td nowrap="nowrap" class="title_item">回答内容：</td>
      <td>${af.map.a_content}</td>
    </tr>
    <tr>
      <td nowrap="nowrap" class="title_item">回答人：</td>
      <td><c:out value="${af.map.a_uname}" /></td>
    </tr>
    <tr>
      <td nowrap="nowrap" class="title_item">回答时间：</td>
      <td><fmt:formatDate value="${af.map.a_date}" /></td>
    </tr>
    <tr>
      <th colspan="2" nowrap="nowrap">状态</th>
    </tr>
    <tr>
      <td nowrap="nowrap" class="title_item">排序值：</td>
      <td><c:out value="${af.map.order_value}" /></td>
    </tr>
    <tr>
      <td nowrap="nowrap" class="title_item">状态：</td>
      <td><c:choose>
          <c:when test="${af.map.qa_state eq 1}"> <span style="color:#060;">已发布</span> </c:when>
          <c:when test="${af.map.qa_state eq 0}"> <span style="color:#f00;">未发布</span> </c:when>
        </c:choose></td>
    </tr>
    <tr>
      <td colspan="2" align="center"><html-el:button property="" value="返 回" styleClass="bgButton" styleId="btn_back" onclick="history.back();" /></td>
    </tr>
  </table>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true"/>
</body>
</html>
