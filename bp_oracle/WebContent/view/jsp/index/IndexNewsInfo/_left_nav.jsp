<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>

<div class="new_l">
  <div class="new_bt1"><strong>${par_mode_name}</strong></div>
  <c:forEach var="cur" items="${sysModuleSonList}">
    <c:set var="selectedClass" value="" />
    <c:if test="${af.map.mod_code eq cur.mod_id}">
      <c:set var="selectedClass" value="f_black12px" />
    </c:if>
    <c:url var="url" value="/IndexNewsInfo.do?method=list&mod_code=${cur.mod_id}&par_code=${cur.par_id}" />
    <div class="new_bt2">&gt;&gt; <a href="${url}" class="${selectedClass}">${cur.mod_name}</a></div>
  </c:forEach>
</div>
