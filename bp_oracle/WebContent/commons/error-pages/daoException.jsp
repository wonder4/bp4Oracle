<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="false" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>内部服务器异常错误</title>
<link href="${ctx}/commons/error-pages/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div align="center">
  <table width="50%" border="0" cellspacing="0" cellpadding="0" style="margin-top:30px">
    <tr>
      <td width="17%" align="left" valign="top"><img src="${ctx}/commons/error-pages/images/1.gif" width="100" height="120"/></td>
      <td width="83%" align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:30px">
          <tr>
            <td height="40"><h1>内部服务器异常</h1></td>
          </tr>
          <tr>
            <td height="28px"><h2> 内部服务器异常错误 </h2></td>
          </tr>
          <tr>
            <td height="28px">在操作数据库时，发生异常。 </td>
          </tr>
          <tr>
            <td height="140px" align="left">请尝试下列操作：<br />
              - 打开 <a href="${ctx}/">${ctx}/ </a>主页，然后查找与所需信息相关的链接。<br />
              - 单击 <a href="javascript:location.reload(true);">刷新</a> 按钮，或稍后重试。</td>
          </tr>
          <tr>
            <td height="28px" align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="26%"><a href="${ctx}/"><img src="${ctx}/commons/error-pages/images/2.gif" width="154" height="39" onmousemove="this.src='${ctx}/commons/error-pages/images/2_1.gif'" onmouseout="this.src='${ctx}/commons/error-pages/images/2.gif';" /></a></td>
                  <td width="74%" align="left" style="padding-top:6px"><h3>本页面3秒后自动转到首页</h3></td>
                </tr>
              </table></td>
          </tr>
        </table></td>
    </tr>
  </table>
</div>
</body>
</html>
