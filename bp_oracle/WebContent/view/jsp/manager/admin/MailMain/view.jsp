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
	<form id="listForm" name="listForm" method="post" action="MailMain.do" enctype="multipart/form-data">
	  <input type="hidden" name="id" id="id"  value="${af.map.id}"/>
	  <input type="hidden" name="method" id="method" />
	  <input type="hidden" name="mod_id" id="mod_id"  value="${af.map.mod_id}"/>    
	  <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
		   <tr>
		     <td align="center" nowrap="nowrap" class="title_item"><span class="shenglan">主&nbsp;&nbsp;题：</span></td>
		     <td align="left">${af.map.title}</td>
		   </tr>
		   <tr>
		     <td align="center" nowrap="nowrap" class="title_item"><span class="shenglan">发件人：</span></td>
		     <td align="left"><c:forEach var="cur" items="${mainPepoList}" varStatus="vs">${cur.map.user_name}<span><font color="gray">&lt;${cur.map.real_name}&gt;</font></span></c:forEach></td>
		   </tr>
		   <tr>
		     <td width="10%" align="center" nowrap="nowrap" class="title_item">收件人：</td>
		     <td width="90%" ><c:forEach var="cur" items="${mailPepoRecList}" varStatus="vs">
		         <c:if test="${vs.last ne true }"> ${cur.map.user_name }
		           <c:if test="${not empty cur.map.real_name}"><font color="gray">&lt;${cur.map.real_name }&gt;&nbsp;;</font></c:if>
		         </c:if>
		         <c:if test="${vs.last eq true }"> ${cur.map.user_name }
		           <c:if test="${not empty cur.map.real_name}"><font color="gray">&lt;${cur.map.real_name }&gt;</font></c:if>
		         </c:if>
		       </c:forEach></td>
		   </tr>
		   <c:if test="${not empty mainPepoCcList }">
		     <tr>
		       <td align="center"nowrap="nowrap" class="title_item">抄&nbsp;&nbsp;送：</td>
		       <td><c:forEach var="cur" items="${mainPepoCcList}" varStatus="vs">
		           <c:if test="${vs.last ne true }">${cur.map.user_name }
		             <c:if test="${not empty cur.map.real_name}"><font color="gray">&lt;${cur.map.real_name }&gt;&nbsp;;</font></c:if>
		           </c:if>
		           <c:if test="${vs.last eq true }">${cur.map.user_name }
		             <c:if test="${not empty cur.map.real_name}"><font color="gray">&lt;${cur.map.real_name }&gt;&nbsp;</font></c:if>
		           </c:if>
		         </c:forEach></td>
		     </tr>
		   </c:if>
		   <tr>
		     <td align="center" nowrap="nowrap" class="title_item">日&nbsp;&nbsp;期：</td>
		     <td><c:if test="${not empty af.map.send_date }">
		         <fmt:formatDate value="${af.map.send_date}" pattern="yyyy-MM-dd (EEEE) HH:mm" dateStyle="full"/>
		       </c:if>
		       <c:if test="${empty af.map.send_date }">
		         <fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd hh:mm:ss"/>
		       </c:if></td>
		   </tr>
		   <c:if test="${not empty attachmentList }">
			<tr>
				<td align="center" nowrap="nowrap" class="title_item">附&nbsp;&nbsp;件：</td>
				<td>
					<c:forEach var="cur" items="${attachmentList}" varStatus="vs">
					${vs.count}、<a href="${ctx}/${cur.save_path}" target="_blank" title="点击查看或下载">${cur.file_name}</a><span style="color:gray">(${cur.map.filesizes} K)</span>
					</c:forEach>
				</td>
			</tr>
		   </c:if>
		   <tr>
				<td align="center" nowrap="nowrap" class="title_item">正&nbsp;&nbsp;文：</td>
				<td>${af.map.content}</td>
		   </tr>
		   <tr>
		      <td colspan="2" align="center"><c:if test="${af.map.can_re eq 1}">
			         <input type="button" class="bgButton" value=" 回 复 " id="replay"  />
			       </c:if>
			       <input type="button" name="" value=" 返 回 " onclick='javascript:window.history.back(-1);' id="btn_back" class="bgButton" /></td>
		   </tr>	   
	</table>
	</form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#replay").click(function(){
		$("#method").val("replay");
		this.form.submit();
	});
});

//]]>
</script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true"/>
</body>
</html>