<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>

<ul class="ucitylul1">
  <li>
    <c:set var="i" value="1" />
    <c:forEach var="cur" items="${newsInfo1004002000List}" varStatus="vs">
      <c:url var="url" value="/IndexNewsInfo.do?uuid=${cur.uuid}" />
      <c:set var="titleColor" value="" />
      <c:set var="titleStrong" value="" />
      <c:if test="${not empty (cur.title_color)}">
        <c:set var="titleColor" value="color:${cur.title_color};" />
      </c:if>
      <c:if test="${cur.title_is_strong eq 1}">
        <c:set var="titleStrong" value="font-weight:bold;" />
      </c:if>
      <a href="${url}" target="_blank" style="${titleColor};${titleStrong}" title="${fn:escapeXml(cur.title)}">${fn:escapeXml(fnx:abbreviate(cur.title, 2 * 4, ''))}</a>
      <c:if test="${vs.count mod 3 eq 0}">
        <c:out value="</li>" escapeXml="false" />
        <c:out value="<li>" escapeXml="false" />
        <c:set var="i" value="${i + 1}" />
      </c:if>
    </c:forEach>
  </li>
  <c:forEach begin="${i + 1}" end="10">
    <li>&nbsp;</li>
  </c:forEach>
</ul>
