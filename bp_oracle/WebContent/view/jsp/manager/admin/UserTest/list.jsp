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
      <html-el:form action="/admin/UserTest">
		<html-el:hidden property="method" value="list" />
		<html-el:hidden property="mod_id" />
	    <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClassSearch">
	      <tr>
	        <td><table width="100%" border="0" cellpadding="0" cellspacing="0" align="left">
	            <tr><td>
	 		&nbsp;用户名：<html-el:text property="user_name_like" style="width:90px;" maxlength="20" styleId="user_name_like" styleClass="webinput" />
	 		&nbsp;真实姓名：<html-el:text property="real_name_like" style="width:90px;" maxlength="20" styleId="real_name_like" styleClass="webinput" />
	                &nbsp;
	                <input type="button" value="查 询" class="bgButton" id="btn_submit" /></td>
	            </tr>
	          </table></td>
	      </tr>
	    </table>
	  </html-el:form>
	  <%@ include file="/commons/pages/messages.jsp" %>
	  <html-el:form action="/admin/UserTest">
	    <div style="padding-bottom:5px;">
	      <logic-el:match name="popedom" value="+4+">
	        <input type="button" name="delete" id="delete" class="bgButton" value="删除所选" onclick="this.form.action += '?method=delete&' + $('#bottomPageForm').serialize();confirmDeleteAll(this.form);" />
	      </logic-el:match>
	      <logic-el:match name="popedom" value="+1+">
	        <input type="button" name="add" id="add" class="bgButton" value="添加" onclick="location.href='UserTest.do?method=add&mod_id=${af.map.mod_id}';" />
	      </logic-el:match>
		  <input type="hidden" name="method" id="method" value="delete" />
	      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
	    </div>
		<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
	      <tr>
	        <th width="5%" nowrap="nowrap"> <c:if test="${fn:contains(popedom, '+4+')}" var="isDelete">
	            <input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" />
	          </c:if>
	          <c:if test="${not isDelete}"> 序号 </c:if>
	        </th>
	          <th nowrap="nowrap">用户名</th>	
	          <th nowrap="nowrap">真实姓名</th>	
	          <th nowrap="nowrap">性别</th>	
	          <th nowrap="nowrap">年龄</th>	
	          <th nowrap="nowrap">文化程度</th>	
	          <th nowrap="nowrap">出生年份</th>	
	          <th nowrap="nowrap">备注</th>	
	        <c:if test="${fn:contains(popedom, '+2+') or fn:contains(popedom, '+4+') or fn:contains(popedom, '+8+')}" var="isContains">
	          <th width="10%" nowrap="nowrap">操作</th>
	        </c:if>
	      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
	      <tr align="center">
          	<td nowrap="nowrap"><c:if test="${fn:contains(popedom, '+4+')}" var="isDelete">
              <input name="pks" type="checkbox" id="pks_${cur.id}" value="${cur.id}" />
            </c:if>
            <c:if test="${not isDelete}"> ${vs.count } </c:if></td>
			<td align="left">${fn:escapeXml(cur.user_name)}</td>
			<td align="left">${fn:escapeXml(cur.real_name)}</td>
			<td align="left"><c:choose>					
								<c:when test="${'1' eq cur.sex}">女</c:when>
								<c:when test="${'0' eq cur.sex}">男</c:when>
						</c:choose></td>
			<td align="left">${fn:escapeXml(cur.age)}</td>
			<td align="left"><c:choose>					
								<c:when test="${'小学' eq cur.education}">小学</c:when>
								<c:when test="${'中学' eq cur.education}">中学</c:when>
								<c:when test="${'大学' eq cur.education}">大学</c:when>
						</c:choose></td>
			<td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.birth_year}" pattern="yyyy-MM-dd"/></td>
			<td align="left">${fn:replace(fn:escapeXml(cur.user_desc), g_java_n, g_html_br)}</td>
          <c:if test="${isContains}" >
            <td nowrap="nowrap"><logic-el:match name="popedom" value="+0+">
				<a class="butbase" href="javascript:void(0);"><span class="icon-search" onclick="doNeedMethod(null, 'UserTest.do', 'view','id=${cur.id}&mod_id=${af.map.mod_id}')">查看</span></a>
			  </logic-el:match>
              <logic-el:match name="popedom" value="+8+">
				<a class="butbase" href="javascript:void(0);"><span class="icon-ok" onclick="doNeedMethod(null, 'UserTest.do', 'audit','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">审核</span></a>
			  </logic-el:match>
              <logic-el:match name="popedom" value="+2+">
                <a class="butbase" href="javascript:void(0);"><span class="icon-edit" onclick="confirmUpdate(null, 'UserTest.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span></a>
              </logic-el:match>
              <logic-el:match name="popedom" value="+4+">
                <a class="butbase" href="javascript:void(0);"><span class="icon-remove" onclick="confirmDelete(null, 'UserTest.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span></a>
              </logic-el:match></td>
          </c:if>
        <c:if test="${vs.last eq true}">
          <c:set var="i" value="${vs.count}" />
        </c:if>
	      </tr>
      </c:forEach>
        <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
          <tr align="center">
            <td>&nbsp;</td>
            <td>&nbsp;</td>       	
            <td>&nbsp;</td>       	
            <td>&nbsp;</td>       	
            <td>&nbsp;</td>       	
            <td>&nbsp;</td>       	
            <td>&nbsp;</td>       	
            <td>&nbsp;</td>       	
            <c:if test="${isContains}"><td>&nbsp;</td></c:if>
          </tr>
        </c:forEach>
    </table>
  </html-el:form>

  <div class="pageClass">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="UserTest.do">
      <table width="98%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="10"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("user_name_like", "${fn:escapeXml(af.map.user_name_like)}");	
				pager.addHiddenInputs("real_name_like", "${fn:escapeXml(af.map.real_name_like)}");	
	            document.write(pager.toString());
            	</script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#btn_submit").click(function(){
		this.form.submit();
	});
});
//]]></script>
</body>
</html>