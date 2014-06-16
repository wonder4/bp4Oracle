<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
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
  <!-- 
  <html-el:form action="/admin/MailMain">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" />
    <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClassSearch">
      <tr>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="0" align="left">
            <tr>
              <td width="5%" nowrap="nowrap">&nbsp;网站名称：
                <html-el:text property="title_like" style="width:100px;" styleClass="webinput" />
                <span style="display:none;">
                &nbsp;发布时间：
                <input name="st_pub_date" id="st_pub_date" size="12" value="${af.map.st_pub_date}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'2013-11-01',maxDate:'#F{$dp.$D(\'en_pub_date\')}'})" />
                至
                <input name="en_pub_date" id="en_pub_date" size="12"  value="${af.map.en_pub_date}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'st_pub_date\')||\'2013-11-01\'}'})" />
                </span>
                &nbsp; 发布状态：
                <select name="pub_state" id="pub_state" style="width:90px;">
                </select>
                &nbsp;信息状态：
                <select name="info_state" id="info_state" style="width:90px;">
                </select>
                &nbsp;
                <html-el:submit value="查 询" styleClass="bgButton"  /></td>
            </tr>
          </table></td>
      </tr>
    </table>
  </html-el:form>
   -->
  <%@ include file="/commons/pages/messages.jsp" %>
  <form id="listForm" name="listForm" method="post" action="MailMain.do?method=delete">
    <div style="padding-bottom:5px;">
      <input type="button" name="delete" id="delete" class="bgButton" value="删除所选" onclick="this.form.action += '&' + $('#bottomPageForm').serialize();confirmDeleteAll(this.form);" />
      <input type="button" name="add" id="add" class="bgButton" value="写信" onclick="location.href='MailMain.do?method=add&&mod_id=${af.map.mod_id}';" />
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
    </div>
    <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
      <tr>
	        <th width="5%" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></th>
	        <th width="1%"><img src="${ctx}/images/accessory_img01.gif" width="15" height="15" alt="附件标识" title="附件标识" /></th>
	        <th>主题</th>
	        <th width="10%">日期</th> 
	        <th width="12%" nowrap="nowrap">操作</th>       
      </tr>
	<c:forEach var="cur" items="${entityList}" varStatus="vs">
	    <tr>
	          <td align="center"><input name="pks" type="checkbox" id="pks" value="${cur.id}" /></td>
	          <td align="center"><c:if test="${cur.map.attacount ne 0}"><img src="${ctx}/images/accessory_img02.jpg" title="有附件"/></c:if></td>
	          <td align="left"><a href='MailMain.do?method=view&mod_id=${af.map.mod_id}&id=${cur.id}' style="cursor:pointer;" title="${cur.title}">
	           <c:if test="${cur.map.mail_state eq 0}"> 
                  <font color="red" > ${fnx:abbreviate_si(fn:escapeXml(cur.title), 30)}</font>
                </c:if>
                <c:if test="${cur.map.mail_state ne 0}">
                   ${fnx:abbreviate_si(fn:escapeXml(cur.title), 30)}
                </c:if>
	           </a></td>
	          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.send_date}" pattern="yyyy-MM-dd HH:mm"/></td>
              <td align="center" nowrap="nowrap"><a class="butbase" href="javascript:void(0);"><span class="icon-search" onclick="doNeedMethod(null, 'MailMain.do', 'view','id=${cur.id}&mod_id=${af.map.mod_id}')">查看</span></a></td>
	    </tr>
        <c:if test="${vs.last eq true}"><c:set var="i" value="${vs.count}" /></c:if>
	</c:forEach>
      <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
        <tr align="center">
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
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="MailMain.do">
      <table width="98%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="10"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				/* pager.addHiddenInputs("craf_code", "${fn:escapeXml(af.map.craf_code)}");	
				pager.addHiddenInputs("craf_name_like", "${fn:escapeXml(af.map.craf_name_like)}");	
				pager.addHiddenInputs("par_id", "${fn:escapeXml(af.map.par_id)}");		 */
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
});
//]]></script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true"/>
</body>
</html>
