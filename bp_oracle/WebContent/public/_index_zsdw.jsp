<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>

<ul class="ucitylul2">
  <c:set var="i" value="1" />
  <c:forEach var="cur" items="${newsInfo1004003000List}" varStatus="vs">
    <c:url var="url" value="/IndexNewsInfo.do?uuid=${cur.uuid}" />
    <c:set var="titleColor" value="" />
    <c:set var="titleStrong" value="" />
    <c:if test="${not empty (cur.title_color)}">
      <c:set var="titleColor" value="color:${cur.title_color};" />
    </c:if>
    <c:if test="${cur.title_is_strong eq 1}">
      <c:set var="titleStrong" value="font-weight:bold;" />
    </c:if>
    <li><a href="${url}" target="_blank" style="${titleColor};${titleStrong}" title="${fn:escapeXml(cur.title)}">${fn:escapeXml(fnx:abbreviate(cur.title, 2 * 13, '...'))}</a></li>
    <c:if test="${vs.last eq true}">
      <c:set var="i" value="${vs.count + 1}" />
    </c:if>
  </c:forEach>
  <c:forEach begin="${i}" end="4">
    <li>&nbsp;</li>
  </c:forEach>
</ul>
