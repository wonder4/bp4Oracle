<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>

<div class="mainbox">
  <div class="friendbox" id="div_friendlink">
    <div class="freindtit">
      <h3><img src="${ctx}/styles/index/images/friendtit.gif" /></h3>
      <ul class="linkul">
        <li id="friendlink1" class="linkcur">中央部委</li>
        <li id="friendlink2">各省政府</li>
        <li id="friendlink3">省直厅局</li>
        <li id="friendlink4">各市政府</li>
        <li id="friendlink5" title="驻外经济商务参赞处">驻外参赞处</li>
        <li id="friendlink6">各省商务厅</li>
      </ul>
    </div>
    <div class="friendlinkclass" id="friendlinkcontent1">
      <ul class="freindul">
        <c:set var="i" value="1" />
        <c:forEach var="cur" items="${friendLink1007101000List}" varStatus="vs">
          <c:set var="friendlinkurl" value="javascript:void(0);"></c:set>
          <c:if test="${not empty cur.direct_uri}">
            <c:set var="friendlinkurl" value="${cur.direct_uri}"></c:set>
          </c:if>
          <c:set var="titleColor" value="" />
          <c:set var="titleStrong" value="" />
          <c:if test="${not empty (cur.title_color)}">
            <c:set var="titleColor" value="color:${cur.title_color};" />
          </c:if>
          <c:if test="${cur.title_is_strong eq 1}">
            <c:set var="titleStrong" value="font-weight:bold;" />
          </c:if>
          <li><a href="${friendlinkurl}" target="_blank" style="${titleColor};${titleStrong}" title="${fn:escapeXml(cur.title)}">${fn:escapeXml(fnx:abbreviate(cur.title, 2 * 5, ''))}</a></li>
          <c:set var="i" value="${i + 1}" />
        </c:forEach>
        <c:forEach begin="${i}" end="70">
          <li>&nbsp;</li>
        </c:forEach>
      </ul>
    </div>
    <div class="friendlinkclass" id="friendlinkcontent2" style="display:none;">
      <ul class="freindul">
		<c:set var="i" value="1" />
        <c:forEach var="cur" items="${friendLink1007102000List}" varStatus="vs">
          <c:set var="friendlinkurl" value="javascript:void(0);"></c:set>
          <c:if test="${not empty cur.direct_uri}">
            <c:set var="friendlinkurl" value="${cur.direct_uri}"></c:set>
          </c:if>
          <c:set var="titleColor" value="" />
          <c:set var="titleStrong" value="" />
          <c:if test="${not empty (cur.title_color)}">
            <c:set var="titleColor" value="color:${cur.title_color};" />
          </c:if>
          <c:if test="${cur.title_is_strong eq 1}">
            <c:set var="titleStrong" value="font-weight:bold;" />
          </c:if>
          <li><a href="${friendlinkurl}" target="_blank" style="${titleColor};${titleStrong}" title="${fn:escapeXml(cur.title)}">${fn:escapeXml(fnx:abbreviate(cur.title, 2 * 5, ''))}</a></li>
          <c:set var="i" value="${i + 1}" />
        </c:forEach>
        <c:forEach begin="${i}" end="70">
          <li>&nbsp;</li>
        </c:forEach>
      </ul>
    </div>
    <div class="friendlinkclass" id="friendlinkcontent3" style="display:none;">
      <ul class="freindul">
		<c:set var="i" value="1" />
        <c:forEach var="cur" items="${friendLink1007103000List}" varStatus="vs">
          <c:set var="friendlinkurl" value="javascript:void(0);"></c:set>
          <c:if test="${not empty cur.direct_uri}">
            <c:set var="friendlinkurl" value="${cur.direct_uri}"></c:set>
          </c:if>
          <c:set var="titleColor" value="" />
          <c:set var="titleStrong" value="" />
          <c:if test="${not empty (cur.title_color)}">
            <c:set var="titleColor" value="color:${cur.title_color};" />
          </c:if>
          <c:if test="${cur.title_is_strong eq 1}">
            <c:set var="titleStrong" value="font-weight:bold;" />
          </c:if>
          <li><a href="${friendlinkurl}" target="_blank" style="${titleColor};${titleStrong}" title="${fn:escapeXml(cur.title)}">${fn:escapeXml(fnx:abbreviate(cur.title, 2 * 5, ''))}</a></li>
          <c:set var="i" value="${i + 1}" />
        </c:forEach>
        <c:forEach begin="${i}" end="70">
          <li>&nbsp;</li>
        </c:forEach>
      </ul>
    </div>
    <div class="friendlinkclass" id="friendlinkcontent4" style="display:none;">
      <ul class="freindul">
		<c:set var="i" value="1" />
        <c:forEach var="cur" items="${friendLink1007104000List}" varStatus="vs">
          <c:set var="friendlinkurl" value="javascript:void(0);"></c:set>
          <c:if test="${not empty cur.direct_uri}">
            <c:set var="friendlinkurl" value="${cur.direct_uri}"></c:set>
          </c:if>
          <c:set var="titleColor" value="" />
          <c:set var="titleStrong" value="" />
          <c:if test="${not empty (cur.title_color)}">
            <c:set var="titleColor" value="color:${cur.title_color};" />
          </c:if>
          <c:if test="${cur.title_is_strong eq 1}">
            <c:set var="titleStrong" value="font-weight:bold;" />
          </c:if>
          <li><a href="${friendlinkurl}" target="_blank" style="${titleColor};${titleStrong}" title="${fn:escapeXml(cur.title)}">${fn:escapeXml(fnx:abbreviate(cur.title, 2 * 5, ''))}</a></li>
          <c:set var="i" value="${i + 1}" />
        </c:forEach>
        <c:forEach begin="${i}" end="70">
          <li>&nbsp;</li>
        </c:forEach>
      </ul>
    </div>
    <div class="friendlinkclass" id="friendlinkcontent5" style="display:none;">
      <ul class="freindul">
		<c:set var="i" value="1" />
        <c:forEach var="cur" items="${friendLink1007105000List}" varStatus="vs">
          <c:set var="friendlinkurl" value="javascript:void(0);"></c:set>
          <c:if test="${not empty cur.direct_uri}">
            <c:set var="friendlinkurl" value="${cur.direct_uri}"></c:set>
          </c:if>
          <c:set var="titleColor" value="" />
          <c:set var="titleStrong" value="" />
          <c:if test="${not empty (cur.title_color)}">
            <c:set var="titleColor" value="color:${cur.title_color};" />
          </c:if>
          <c:if test="${cur.title_is_strong eq 1}">
            <c:set var="titleStrong" value="font-weight:bold;" />
          </c:if>
          <li><a href="${friendlinkurl}" target="_blank" style="${titleColor};${titleStrong}" title="${fn:escapeXml(cur.title)}">${fn:escapeXml(fnx:abbreviate(cur.title, 2 * 5, ''))}</a></li>
          <c:set var="i" value="${i + 1}" />
        </c:forEach>
        <c:forEach begin="${i}" end="70">
          <li>&nbsp;</li>
        </c:forEach>
      </ul>
    </div>
    <div class="friendlinkclass" id="friendlinkcontent6" style="display:none;">
      <ul class="freindul">
		<c:set var="i" value="1" />
        <c:forEach var="cur" items="${friendLink1007106000List}" varStatus="vs">
          <c:set var="friendlinkurl" value="javascript:void(0);"></c:set>
          <c:if test="${not empty cur.direct_uri}">
            <c:set var="friendlinkurl" value="${cur.direct_uri}"></c:set>
          </c:if>
          <c:set var="titleColor" value="" />
          <c:set var="titleStrong" value="" />
          <c:if test="${not empty (cur.title_color)}">
            <c:set var="titleColor" value="color:${cur.title_color};" />
          </c:if>
          <c:if test="${cur.title_is_strong eq 1}">
            <c:set var="titleStrong" value="font-weight:bold;" />
          </c:if>
          <li><a href="${friendlinkurl}" target="_blank" style="${titleColor};${titleStrong}" title="${fn:escapeXml(cur.title)}">${fn:escapeXml(fnx:abbreviate(cur.title, 2 * 5, ''))}</a></li>
          <c:set var="i" value="${i + 1}" />
        </c:forEach>
        <c:forEach begin="${i}" end="70">
          <li>&nbsp;</li>
        </c:forEach>
      </ul>
    </div>
  </div>
  <div class="clear"></div>
</div>
