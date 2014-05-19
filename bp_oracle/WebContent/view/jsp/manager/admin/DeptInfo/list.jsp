<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>组织架构</title>
</head>
<frameset cols="240,*">
  <frame src="DeptInfo.do?method=showDeptInfoTree" name="leftFrame" marginwidth="0" marginheight="0" id="leftFrame" title="leftFrame" />
  <frame src="DeptInfo.do?method=edit&amp;par_id=-1&amp;dept_id=1" name="editFrame" scrolling="auto" marginwidth="0" marginheight="0" id="editFrame" title="editFrame" />
</frameset>
</html>
