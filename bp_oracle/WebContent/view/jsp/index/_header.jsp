<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<!-- head start -->

<div class="ucheadbox">
  <div class="toplogo">${applicationScope._global_website_name}</div>
  <ul class="topnavul">
  <c:url var="url" value="/index.do" />
    <li><a href="${url}">首页</a></li>
    <c:url var="url" value="/IndexNewsInfo.do?method=list&mod_code=1003001000&par_code=1003000000" />
    <li><a href="${url}">公告</a></li>
    <c:url var="url" value="/IndexNewsInfo.do?method=list&mod_code=1003002000&par_code=1003000000" />
    <li><a href="${url}">最新活动</a></li>
    <c:url var="url" value="/IndexNewsInfo.do?method=list&mod_code=1003003000&par_code=1003000000" />
    <li><a href="${url}">专题销售区</a></li>
  </ul>
  <div class="clear"></div>
</div>
<!--head end --> 

