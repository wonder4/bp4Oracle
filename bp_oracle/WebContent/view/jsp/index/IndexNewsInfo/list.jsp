<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新闻列表 - 内蒙古商务厅</title>
<link href="${ctx}/styles/index/css/common.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/index/css/head.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/index/css/union_city.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="../_header.jsp" flush="true" />
<!--first start -->
<div class="mainbox">
  <jsp:include page="_left_nav.jsp" flush="true" />
  <div class="news_r">
    <div class="news_r_top">您当前的位置: ${naviString}</div>
    <div class="news_r_cont">
      <div class="news_r_cont_tit"><strong>通知公告</strong></div>
      <div class="news_cont_cont">
        <div style="margin:0 auto; text-align:center; width: 100%;">
          <div class="newsnr">
            <ul>
              <c:forEach var="cur" items="${newsInfoList}" >
                <c:if var="hasDirectUrl" test="${(cur.is_use_direct_uri eq 1) and (not empty (cur.title_direct_uri))}">
                  <c:url var="url" value="${cur.title_direct_uri}" />
                </c:if>
                <c:if test="${not hasDirectUrl}">
                  <c:url var="url" value="/IndexNewsInfo.do?uuid=${cur.uuid}" />
                </c:if>
                <li><span>
                  <fmt:formatDate value="${cur.add_time}" pattern="yyyy-MM-dd" />
                  </span>
                  <c:if var="hasColor" test="${not empty (cur.title_color)}"><a href="${url}" target="_blank" style="color:${cur.title_color}; <c:if test='${cur.title_is_strong eq 1}'>font-weight:bold;</c:if>" title="${fn:escapeXml(cur.title)}">${fn:escapeXml(fnx:abbreviate(cur.title, 2 * 28, "..."))}</a></c:if>
                  <c:if test="${not hasColor}"><a href="${url}" target="_blank" style="color:${cur.title_color}; <c:if test='${cur.title_is_strong eq 1}'>font-weight:bold;</c:if>" title="${fn:escapeXml(cur.title)}">${fn:escapeXml(fnx:abbreviate(cur.title, 2 * 28, '...'))}</a></c:if>
                </li>
              </c:forEach>
              <c:if test="${empty newsInfoList}">
                <li style="color: red;">暂无数据</li>
              </c:if>
            </ul>
          </div>
          <div class="spagecont">
            <form id="bottomPageForm" name="bottomPageForm" method="post" action="<c:url value="/IndexNewsInfo.do" />">
              <table width="98%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="10"><script type="text/javascript" src="${ctx}/commons/pager/pagination.min.js">;</script> 
                    <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		        pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_code", "${af.map.mod_code}");
				pager.addHiddenInputs("par_code", "${af.map.par_code}");
		        pager.addHiddenInputs("title_like", "${fn:escapeXml(af.map.title_like)}");
		        document.write(pager.toString());
            	</script></td>
                </tr>
              </table>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<!--first end -->
<jsp:include page="../_footer.jsp" flush="true" />
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

});
//]]>
</script>
<jsp:include page="../_public_page.jsp" flush="true" />
</body>
</html>
