<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/commons/styles/blue/base.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="divContent">
  <div class="subtitle">
    <h3>${naviString}</h3>
  </div>
  <html-el:form action="/admin/QaInfo.do?method=list">
    <html-el:hidden property="mod_id" />
    <html-el:hidden property="q_type" />
    <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClassSearch">
      <tr>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="0" align="left">
            <tr>
              <td>&nbsp;标题：
                <html-el:text property="q_title_like" styleClass="webinput" maxlength="120" style="width:80px;"/>
                &nbsp;状态：
                <html-el:select property="qa_state" style="width:80px;">
                  <html-el:option value="">请选择...</html-el:option>
                  <html-el:option value="1">已发布</html-el:option>
                  <html-el:option value="0">未发布</html-el:option>
                </html-el:select>
                &nbsp;提问时间：
                <input name="st_q_date" id="st_q_date" size="11" value="${af.map.st_q_date}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'2013-11-01',maxDate:'#F{$dp.$D(\'en_q_date\')}'})" />
                至：
                <input name="en_q_date" id="en_q_date" size="11"  value="${af.map.en_q_date}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'st_q_date\')||\'2013-11-01\'}'})" />
                &nbsp;回答时间：
                <input name="st_a_date" id="st_a_date" size="11" value="${af.map.st_a_date}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'2013-11-01',maxDate:'#F{$dp.$D(\'en_a_date\')}'})" />
                至：
                <input name="en_a_date" id="en_a_date" size="11"  value="${af.map.en_a_date}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'st_a_date\')||\'2013-11-01\'}'})" />
                &nbsp;
                <html-el:button property="" value="查 询" styleClass="bgButton" styleId="btn_submit" /></td>
            </tr>
          </table></td>
      </tr>
    </table>
  </html-el:form>
  <%@ include file="/commons/pages/messages.jsp" %>
  <form id="listForm" name="listForm" method="post" action="QaInfo.do?method=delete">
    <div style="padding-bottom:5px;">
      <logic-el:match name="popedom" value="+4+">
        <input type="button" name="delete" id="delete" class="bgButton" value="删除所选" onclick="this.form.action += '&' + $('#bottomPageForm').serialize();confirmDeleteAll(this.form);" />
      </logic-el:match>
      <logic-el:match name="popedom" value="+1+">
        <input type="button" name="add" id="add" class="bgButton" value="添加" onclick="location.href='QaInfo.do?method=add&mod_id=${af.map.mod_id}&q_type=${af.map.q_type}';"/>
      </logic-el:match>
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
    </div>
    <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
      <tr>
        <th width="5%" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></th>
        <th width="25%" nowrap="nowrap">问题标题</th>
        <th width="10%" nowrap="nowrap">提问时间</th>
        <th width="10%" nowrap="nowrap">回答时间</th>
        <th width="7%" nowrap="nowrap">回答人</th>
        <th width="7%" nowrap="nowrap">状态</th>
        <th width="5%" nowrap="nowrap">排序值</th>
        <c:if test="${fn:contains(popedom, '+2+') or fn:contains(popedom, '+4+') or fn:contains(popedom, '+8+')}" var="isContains">
          <th width="12%" nowrap="nowrap">操作</th>
        </c:if>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap"><c:if test="${fn:contains(popedom, '+4+')}" var="isDelete">
              <c:set var="isDel" value="${cur.qa_state eq 0}"></c:set>
              <c:if test="${isDel}">
                <input name="pks" type="checkbox" id="pks_${cur.id}" value="${cur.id}" />
              </c:if>
              <c:if test="${not isDel}">
                <input name="pks" type="checkbox" id="pks_${cur.id}" value="${cur.id}" disabled="disabled" />
              </c:if>
            </c:if>
            <c:if test="${not isDelete}"> ${vs.count } </c:if></td>
          <td align="left"><a style="text-decoration:none;" title="${fn:escapeXml(cur.q_title)} " href="QaInfo.do?method=view&amp;id=${cur.id}&amp;mod_id=${af.map.mod_id}">${fn:escapeXml(cur.q_title)}</a></td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.q_date}" pattern="yyyy-MM-dd" /></td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.a_date}" pattern="yyyy-MM-dd" /></td>
          <td align="left" nowrap="nowrap"><c:out value="${cur.a_uname}" /></td>
          <td align="center" nowrap="nowrap"><c:choose>
              <c:when test="${cur.qa_state eq 1}"> <span style="color:green">已发布</span> </c:when>
              <c:when test="${cur.qa_state eq 0}"> <span style="color:red">未发布</span> </c:when>
              <c:when test="${cur.qa_state eq -1}"> 已删除 </c:when>
              <c:otherwise> - </c:otherwise>
            </c:choose></td>
          <td align="right"><c:out value="${cur.order_value}" /></td>
          <td align="center" nowrap="nowrap"><logic-el:match name="popedom" value="+2+"> <a class="butbase" href="javascript:void(0);"><span class="icon-reply" onclick="confirmUpdate(null, 'QaInfo.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">回复</span></a> </logic-el:match>
            <logic-el:match name="popedom" value="+4+">
              <c:if test="${not isDel}"> <a class="butbase but-disabled" href="javascript:void(0);"><span title="已发布，不能删除" class="icon-remove">删除</span></a> </c:if>
              <c:if test="${isDel}"> <a class="butbase" href="javascript:void(0);"><span class="icon-remove" onclick="confirmDelete(null, 'QaInfo.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span></a> </c:if>
            </logic-el:match></td>
        </tr>
        <c:if test="${vs.last eq true}">
          <c:set var="i" value="${vs.count}" />
        </c:if>
      </c:forEach>
      <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
        <tr align="center">
          <c:forEach begin="1" end="${isContains ? 8 : 7}">
            <td>&nbsp;</td>
          </c:forEach>
        </tr>
      </c:forEach>
    </table>
  </form>
  <div class="pageClass">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="QaInfo.do">
      <table width="98%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="10"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		        pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		        pager.addHiddenInputs("q_title_like", "${fn:escapeXml(af.map.q_title_like)}");
		        pager.addHiddenInputs("q_type", "${af.map.q_type}");
		        pager.addHiddenInputs("qa_state", "${af.map.qa_state}");
				pager.addHiddenInputs("st_q_date", "${af.map.st_q_date}");
				pager.addHiddenInputs("en_q_date", "${af.map.en_q_date}");
				pager.addHiddenInputs("st_a_date", "${af.map.st_a_date}");
				pager.addHiddenInputs("en_a_date", "${af.map.en_a_date}");

		        document.write(pager.toString());
            	</script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/cs.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#btn_submit").click(function(){
		if (this.form.st_q_date.value != "" && this.form.en_q_date.value != "") {
			if (this.form.en_q_date.value < this.form.st_q_date.value) {
				alert("结束日期不得早于开始日期,请重新选择!");
				return false;
			}
		}
		if(this.form.st_a_date.value != "" && this.form.en_a_date.value != "") {
			if (this.form.en_a_date.value < this.form.st_a_date.value) {
				alert("结束日期不得早于开始日期,请重新选择!");
				return false;
			}
		}
		this.form.submit();
	});
});
//]]></script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true"/>
</body>
</html>