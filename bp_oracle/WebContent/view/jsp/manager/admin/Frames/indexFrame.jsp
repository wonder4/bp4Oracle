<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${applicationScope._global_website_name}</title>
<script type="text/javascript">//<![CDATA[
if(self != top){
	top.location = self.location;
}
//]]></script>
</head>
<frameset rows="50,*" cols="*" border="0" frameborder="0" framespacing="0">
  <frame src="Frames.do?method=top" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset cols="173,6,*" id="mainFrameset" framespacing="0" frameborder="no" border="0">
    <frame src="Frames.do?method=left" name="leftFrame" id="leftFrame" title="leftFrame" scrolling="auto" />
    <frame src="Frames.do?method=lr" name="lrFrame" id="lrFrame" title="lrFrame" scrolling="no" noresize="noresize" />
    <frame src="Frames.do?method=main" name="mainFrame" id="mainFrame" title="mainFrame" />
    <noframes>
    <body>
    对不起，您的浏览器不支持框架
    </body>
    </noframes>
  </frameset>
</frameset>
</html>