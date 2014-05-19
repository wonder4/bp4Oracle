<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Description" content="${applicationScope._global_website_name}" />
<meta name="Keywords" content="${applicationScope._global_website_name}" />
<title>首页 - ${applicationScope._global_website_name}</title>
<link href="${ctx}/styles/index/css/common.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/index/css/head.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/index/css/union_city.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="_header.jsp" flush="true" />
<script type="text/javascript" src="${ctx}/scripts/tabs/tabs.switch.min.js"></script> 
<jsp:include page="_public_page.jsp" flush="true" />
</body>
</html>