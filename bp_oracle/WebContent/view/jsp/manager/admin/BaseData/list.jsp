<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/commons/styles/blue/base.css" rel="stylesheet" type="text/css" />
<jsp:include page="../_global_manager_page_head.jsp" flush="true" />
</head>
<body>
<div class="divContent">
  <div class="subtitle">
    <h3>${naviString}</h3>
  </div>
  <html-el:form action="/admin/BaseData">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" />
    <html-el:hidden property="type" />
    <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClassSearch">
      <tr>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="0" align="left">
            <tr>
              <td width="6%" nowrap="nowrap">用户类型：
                <html-el:text property="type_name_like" styleId="type_name_like" style="width:80px;" styleClass="webinput"/>
                &nbsp;&nbsp;是否锁定：
                <html-el:select property="is_lock">
                  <html-el:option value="">请选择...</html-el:option>
                  <html-el:option value="0">未锁定</html-el:option>
                  <html-el:option value="1">已锁定</html-el:option>
                </html-el:select>
                &nbsp;&nbsp;是否删除：
                <html-el:select property="is_del">
                  <html-el:option value="">请选择...</html-el:option>
                  <html-el:option value="0">未删除</html-el:option>
                  <html-el:option value="1">已删除</html-el:option>
                </html-el:select>
                &nbsp;&nbsp;
                <html-el:submit value="查 询" styleClass="bgButton" /></td>
            </tr>
          </table></td>
      </tr>
    </table>
  </html-el:form>
  <%@ include file="/commons/pages/messages.jsp" %>
  <form id="listForm" name="listForm" method="post" action="BaseData.do?method=delete">
    <div style="text-align: left; padding: 5px;">
      <logic-el:match name="popedom" value="+4+">
        <input type="button" name="delete" id="delete" class="bgButton" value="删除所选" onclick="this.form.action += '&' + $('#bottomPageForm').serialize();confirmDeleteAll(this.form);" />
      </logic-el:match>
      <logic-el:match name="popedom" value="+1+">
        <!-- <input type="button" name="add" id="add" class="bgButton" value="添加" onclick="location.href='BaseData.do?method=add&mod_id=${af.map.mod_id}&type=${af.map.type}';" /> -->
      </logic-el:match>
    </div>
    <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
      <tr>
        <th width="7%" nowrap="nowrap"> <c:if test="${fn:contains(popedom, '+4+')}" var="isDelete">
            <input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" />
          </c:if>
          <c:if test="${not isDelete}"> 序号 </c:if>
        </th>
        <th nowrap="nowrap">用户类型</th>
        <th width="15%">备注</th>
        <th width="10%">添加时间</th>
        <th width="6%">排序值</th>
        <th width="6%" nowrap="nowrap">是否锁定</th>
        <th width="6%" nowrap="nowrap">是否删除</th>
        <c:if test="${fn:contains(popedom, '+2+') or fn:contains(popedom, '+4+')}" var="isContains">
          <th width="15%" nowrap="nowrap">操作</th>
        </c:if>
      </tr>
      <c:forEach var="cur" items="${baseDataList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap"><c:if test="${fn:contains(popedom, '+4+')}" var="isDelete">
              <c:set var="isLock" value="${cur.is_lock eq 1}"></c:set>
              <c:set var="isDel" value="${cur.is_del eq 1}"></c:set>
              <c:if test="${isLock or isDel}">
                <input name="pks" type="checkbox" id="pks" value="${cur.id}" disabled="disabled"/>
              </c:if>
              <c:if test="${not isLock and not isDel}">
                <input name="pks" type="checkbox" id="pks" value="${cur.id}" />
              </c:if>
            </c:if>
            <c:if test="${not isDelete}"> ${vs.count } </c:if></td>
          <td align="left" nowrap="nowrap"><a title="查看" href="BaseData.do?method=view&amp;mod_id=${af.map.mod_id}&amp;type=${af.map.type }&amp;id=${cur.id}">${fn:escapeXml(cur.type_name )}</a></td>
          <td align="left"><c:out value="${cur.remark}"></c:out></td>
          <td align="center"><fmt:formatDate value="${cur.add_date }" pattern="yyyy-MM-dd"/></td>
          <td align="right"><c:out value="${cur.order_value }"></c:out></td>
          <td align="center"><c:choose>
              <c:when test="${cur.is_lock eq 0}"><span style=" color:#060;">未锁定</span></c:when>
              <c:when test="${cur.is_lock eq 1}"><span style=" color:#ccc;">已锁定</span></c:when>
            </c:choose></td>
          <td align="center"><c:choose>
              <c:when test="${cur.is_del eq 0}"><span style=" color:#060;">未删除</span></c:when>
              <c:when test="${cur.is_del eq 1}"><span style=" color:#F00;">已删除</span></c:when>
            </c:choose></td>
          <c:if test="${isContains}" >
            <td align="center" nowrap="nowrap"><logic-el:match name="popedom" value="+2+"> <a class="butbase" href="javascript:void(0);"><span class="icon-edit" onclick="confirmUpdate(null, 'BaseData.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span></a> </logic-el:match>
              <logic-el:match name="popedom" value="+4+">
                <c:if test="${isLock or isDel}"> <a class="butbase but-disabled" href="javascript:void(0);"><span title="已删除或已锁定，不能删除" class="icon-remove">删除</span></a> </c:if>
                <c:if test="${not isLock and not isDel}"> <a class="butbase" href="javascript:void(0);"><span class="icon-remove" onclick="confirmDelete(null, 'BaseData.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span></a> </c:if>
              </logic-el:match></td>
          </c:if>
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
            <c:if test="${isContains}"><td>&nbsp;</td></c:if>
          </tr>
        </c:forEach>      
    </table>
  </form>
  <div class="pageClass">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="BaseData.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" >
        <tr>
          <td height="10" ><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("type_name_like", "${fn:escapeXml(af.map.type_name_like)}");
			pager.addHiddenInputs("is_del", "${af.map.is_del}");
			pager.addHiddenInputs("is_lock", "${af.map.is_lock}");
			pager.addHiddenInputs("type", "${af.map.type}");
            document.write(pager.toString());
        </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/rowEffect.js"></script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true" />
</body>
</html>