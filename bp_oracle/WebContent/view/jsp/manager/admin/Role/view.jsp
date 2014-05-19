<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/commons/styles/blue/base.css" rel="stylesheet" type="text/css" />
<jsp:include page="../_global_manager_page_head.jsp" flush="true" />
<style type="text/css">
<!--
body {
	font:normal 12px 宋体
}
a.MzTreeview /* TreeView 链接的基本样式 */ {
	cursor: hand;
	color: #000080;
	margin-top: 5px;
	padding: 2 1 0 2;
	text-decoration: none;
}
.MzTreeview a.select /* TreeView 链接被选中时的样式 */ {
	color: highlighttext;
	background-color: highlight;
}
#kkk input {
	vertical-align:middle;
}
.MzTreeViewRow {
	border:none;
	width:500px;
	padding:0px;
	margin:0px;
	border-collapse:collapse
}
.MzTreeViewCell0 {
	border-bottom:1px solid #CCCCCC;
	padding:0px;
	margin:0px;
}
.MzTreeViewCell1 {
	border-bottom:1px solid #CCCCCC;
	border-left:1px solid #CCCCCC;
	width:200px;
	padding:0px;
	margin:0px;
}
-->
</style>
</head>
<body>
<script type="text/javascript" src="${ctx}/commons/scripts/checkboxmztree/MzTreeView12.js"></script>
<div class="divContent">
  <div class="subtitle">
    <h3>${naviString}</h3>
  </div>
  <html-el:form action="/admin/Role.do">
    <html-el:hidden property="method" value="saveRoleUser" />
    <html-el:hidden property="mod_id" />
    <html-el:hidden property="id" />
    <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
      <tr>
        <th colspan="2">用户角色基本信息</th>
      </tr>
      <tr>
        <td width="15%" class="title_item" nowrap="nowrap">角色名称：</td>
        <td width="85%">${fn:escapeXml(af.map.role_name)}</td>
      </tr>
      <tr>
        <td class="title_item">排序值：</td>
        <td>${af.map.order_value }</td>
      </tr>
      <tr>
        <td class="title_item">用户列表：</td>
        <td><table width="80%" border="0">
            <c:forEach items="${userInfoList}" var="cur">
              <tr>
                <td width="5%" align="left" nowrap="nowrap"><div style="padding-left: 5px;">
                    <c:set var="isChecked" value="false"/>
                    <c:forEach var="cur_ru" items="${roleUserList}">
                      <c:if test="${cur_ru.user_id eq cur.id}">
                        <c:set var="isChecked" value="true"/>
                        <input type="checkbox" id="checkbox_${cur.id}" name="user_ids" value="${cur.id}" checked="checked" />
                      </c:if>
                    </c:forEach>
                    <c:if test="${not isChecked}">
                      <input type="checkbox" id="checkbox_${cur.id}" name="user_ids" value="${cur.id}" />
                    </c:if>
                  </div></td>
                <td width="20%" align="left" nowrap="nowrap"><label for="id_${cur.id}">&nbsp;${fn:escapeXml(cur.user_name)}&nbsp;</label></td>
                <td align="left" nowrap="nowrap">[用户类型]：
                  <c:forEach items="${baseData10List}" var="curBase">
                    <c:if test="${cur.user_type eq curBase.id}">${fn:escapeXml(curBase.type_name)}</c:if>
                  </c:forEach></td>
              </tr>
            </c:forEach>
            <c:if test="${empty userInfoList}">
              <tr>
                <td colspan="3"></td>
              </tr>
            </c:if>
          </table></td>
      </tr>
      <tr>
        <td colspan="2" style="text-align:center"><html-el:submit value=" 保 存 " styleClass="bgButton" />
          &nbsp;
          <input type="button" class="bgButton" value=" 返回 " onclick="history.back();" /></td>
      </tr>
    </table>
  </html-el:form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true" />
</body>
</html>
