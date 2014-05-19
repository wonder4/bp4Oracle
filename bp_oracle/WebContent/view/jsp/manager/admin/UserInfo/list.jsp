<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/commons/styles/blue/base.css" rel="stylesheet" type="text/css" />
<jsp:include page="../_global_manager_page_head.jsp" flush="true" />
<link href="${ctx}/scripts/autocomplete/autocomplete.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="divContent">
  <div class="subtitle">
    <h3>${naviString}</h3>
  </div>
  <html-el:form action="/admin/UserInfo">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" />
    <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClassSearch">
      <tr>
        <td>登录名：
          <html-el:text property="user_name_like" maxlength="20" styleId="user_name_like" style="width:80px;" styleClass="webinput" />
          &nbsp;&nbsp;用户类型：
          <html-el:select property="user_type" style="width:100px;">
            <html-el:option value="">请选择...</html-el:option>
            <c:forEach items="${baseData10List}" var="cur">
              <html-el:option value="${cur.type_value}">${fn:escapeXml(cur.type_name)}</html-el:option>
            </c:forEach>
          </html-el:select>
          &nbsp;&nbsp; 性别：
          <html-el:select property="sex">
            <html-el:option value="">请选择...</html-el:option>
            <html-el:option value="0">男</html-el:option>
            <html-el:option value="1">女</html-el:option>
          </html-el:select>
          &nbsp;&nbsp;是否锁定：
          <html-el:select property="is_lock">
            <html-el:option value="">请选择...</html-el:option>
            <html-el:option value="0">未锁定</html-el:option>
            <html-el:option value="1">已锁定</html-el:option>
          </html-el:select>
          &nbsp;&nbsp; 是否删除：
          <html-el:select property="is_del">
            <html-el:option value="">请选择...</html-el:option>
            <html-el:option value="0">未删除</html-el:option>
            <html-el:option value="1">已删除</html-el:option>
          </html-el:select>
          &nbsp;&nbsp;
          <html-el:submit value="查 询" styleClass="bgButton" /></td>
      </tr>
    </table>
  </html-el:form>
  <%@ include file="/commons/pages/messages.jsp" %>
  <form id="listForm" name="listForm" method="post" action="UserInfo.do?method=delete">
    <div style="padding-bottom:5px;">
      <input type="button" name="delete" id="delete" class="bgButton" value="删除所选" onclick="this.form.action += '&' + $('#bottomPageForm').serialize();confirmDeleteAll(this.form);" />
      <input type="button" name="add" id="add" class="bgButton" value="添加" onclick="location.href='UserInfo.do?method=add&mod_id=${af.map.mod_id}';" />
    </div>
    <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
      <tr>
        <th width="5%" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></th>
        <th nowrap="nowrap">登录名</th>
        <th nowrap="nowrap" width="10%">用户类型</th>
        <th nowrap="nowrap" width="5%">性别</th>
        <th nowrap="nowrap" width="8%">添加时间</th>
        <th nowrap="nowrap" width="6%">排序值</th>
        <th nowrap="nowrap" width="6%">是否锁定</th>
        <th nowrap="nowrap" width="6%">是否删除</th>
        <th width="6%" nowrap="nowrap">授权</th>
        <th width="10%" nowrap="nowrap">初始化密码</th>
        <th width="12%" nowrap="nowrap">操作</th>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap"><c:set var="isLock" value="${cur.is_lock eq 1}"></c:set>
            <c:set var="isDel" value="${cur.is_del eq 1}"></c:set>
            <c:if test="${isLock or isDel}">
              <input name="pks" type="checkbox" id="pks" value="${cur.id}" disabled="disabled"/>
            </c:if>
            <c:if test="${not isLock and not isDel}">
              <input name="pks" type="checkbox" id="pks" value="${cur.id}" />
            </c:if></td>
          <td align="left" nowrap="nowrap"><a title="查看" href="UserInfo.do?method=view&amp;mod_id=${af.map.mod_id}&amp;id=${cur.id}">${fn:escapeXml(cur.user_name)}</a></td>
          <td align="left"><c:set var="type_name" value="超级管理员" />
            <c:forEach items="${baseData10List}" var="curBaseDate">
              <c:if test="${curBaseDate.type_value eq cur.user_type}">
                <c:set var="type_name" value="${fn:escapeXml(curBaseDate.type_name)}" />
              </c:if>
            </c:forEach>
            ${type_name} </td>
          <td align="center"><c:choose>
              <c:when test="${cur.sex eq 0}">男</c:when>
              <c:when test="${cur.sex eq 1}">女</c:when>
            </c:choose></td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
          <td align="right">${cur.order_value}</td>
          <td align="center"><c:choose>
              <c:when test="${cur.is_lock eq 0}"><span style=" color:#060;">未锁定</span></c:when>
              <c:when test="${cur.is_lock eq 1}"><span style=" color:#ccc;">已锁定</span></c:when>
            </c:choose></td>
          <td align="center"><c:choose>
              <c:when test="${cur.is_del eq 0}"><span style=" color:#060;">未删除</span></c:when>
              <c:when test="${cur.is_del eq 1}"><span style=" color:#F00;">已删除</span></c:when>
            </c:choose></td>
          <td nowrap="nowrap"><a class="butbase" href="javascript:void(0);"><span class="icon-configure" onclick="doNeedMethod(null, 'ModPopedom.do', 'edit', 'mod_id=${af.map.mod_id}&amp;user_id=${cur.id}&amp;url=UserInfo');">授权</span></a></td>
          <td nowrap="nowrap" align="center"><a class="butbase" href="javascript:void(0);"><span class="icon-lock" onclick="initPassword(${cur.id});">初始化密码</span></a></td>
          <td align="center" nowrap="nowrap"><a class="butbase" href="javascript:void(0);"><span class="icon-edit" onclick="confirmUpdate(null, 'UserInfo.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span></a>
            <c:if test="${isLock or isDel}"> <a class="butbase but-disabled" href="javascript:void(0);"><span title="已删除或已锁定，不能删除" class="icon-remove">删除</span></a> </c:if>
            <c:if test="${not isLock and not isDel}"> <a class="butbase" href="javascript:void(0);"><span class="icon-remove" onclick="confirmDelete(null, 'UserInfo.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span></a> </c:if></td>
        </tr>
         <c:if test="${vs.last eq true}">
          <c:set var="i" value="${vs.count}" />
        </c:if>        
      </c:forEach>
      <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
          <tr align="center">
            <td>&nbsp;</td>       	
            <td>&nbsp;</td>       	
            <td>&nbsp;</td>       	
            <td>&nbsp;</td>       	
            <td>&nbsp;</td>       	
            <td>&nbsp;</td>       	
            <td>&nbsp;</td>       	
            <td>&nbsp;</td>       	
            <td>&nbsp;</td>       	
            <td>&nbsp;</td>       	
            <td>&nbsp;</td>       	
          </tr>
        </c:forEach>      
    </table>
  </form>
  <div class="pageClass">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="UserInfo.do">
      <table width="98%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("user_name_like", "${fn:escapeXml(af.map.user_name_like)}");
			pager.addHiddenInputs("is_del", "${af.map.is_del}");
			pager.addHiddenInputs("is_lock", "${af.map.is_lock}");
			pager.addHiddenInputs("user_type", "${af.map.user_type}");
			pager.addHiddenInputs("sex", "${af.map.sex}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/autocomplete/autocomplete.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/rowEffect.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
    var options = {
      serviceUrl : "UserInfo.do?method=getQueryUserNames",
      maxHeight : 240,
      width: 80,
      delimiter : /(,|;)\s*/,
      pageCount : 20,
      animate : true,
      onSelect : function(value, data) {},
      deferRequestBy : 0, //miliseconds
      params : {country : "Yes"}
    };
    $("#user_name_like").autocomplete(options);
    
});

function initPassword(id) {
	if (confirm("确认要初始化密码吗？")) {
		var password = prompt("请输入您的新密码,如果不输入,默认初始密码为“0”。","");
		if (null != password) {
			if (password.length == 0) {
				password = "0";
			}
			$.post("CsAjax.do?method=initPassword",{uid : id, password : password},function(data){
				if(null != data.result){alert(data.msg);} else {alert("初始化密码失败");}
			});
		}
	}
	return false;
}
//]]></script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true" />
</body>
</html>
