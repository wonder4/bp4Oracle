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
  <html-el:form action="/admin/PdInfo">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" />
    <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClassSearch">
      <tr>
        <td> 产品名称：
          <html-el:text property="pd_name_like" styleClass="webinput" maxlength="50"/>
          &nbsp;产品类别：
          <html-el:select property="cls_id" styleId="cls_id" style="width:258px;">
            <html-el:option value="">请选择...</html-el:option>
            <c:forEach var="cur" items="${basePdClazzTreeList}" varStatus="vs">
              <html-el:option value="${cur.cls_id}">${fn:escapeXml(cur.tree_name)}</html-el:option>
            </c:forEach>
          </html-el:select>
          &nbsp; 审核状态：
          <html-el:select property="audit_state" styleId="audit_state">
            <html-el:option value="">请选择...</html-el:option>
            <html-el:option value="-1">审核不通过</html-el:option>
            <html-el:option value="0">待审核</html-el:option>
            <html-el:option value="1">审核通过</html-el:option>
          </html-el:select>
          &nbsp;
          <html-el:submit value="查 询" styleClass="bgButton" styleId="btn_submit" /></td>
      </tr>
      <tr>
        <td>所属区域：
          <select name="province" id="province" style="width:120px;">
            <option value="">请选择...</option>
          </select>
          <select name="city" id="city" style="width:120px;">
            <option value="">请选择...</option>
          </select>
          <select name="country" id="country" style="width:120px;">
            <option value="">请选择...</option>
          </select>
          &nbsp;是否删除：
          <html-el:select property="is_del" styleId="is_del">
            <html-el:option value="-1">请选择...</html-el:option>
            <html-el:option value="0">否</html-el:option>
            <html-el:option value="1">是</html-el:option>
          </html-el:select></td>
      </tr>
    </table>
  </html-el:form>
  <%@ include file="/commons/pages/messages.jsp" %>
  <form id="listForm" name="listForm" method="post" action="PdInfo.do?method=delete">
    <div style="padding-bottom:5px;">
      <input type="button" name="delete" id="delete" class="bgButton" value="删除所选" onclick="this.form.action += '&' + $('#bottomPageForm').serialize();confirmDeleteAll(this.form);" />
      <input type="button" name="add" id="add" class="bgButton" value="添加" onclick="location.href='PdInfo.do?method=add&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize();" />
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
    </div>
    <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tableClass">
      <tr>
        <th width="3%" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></th>
        <th nowrap="nowrap">产品名称</th>
        <th width="8%">产品类别</th>
        <th width="6%">参考价格</th>
        <th width="6%">是否上架</th>
        <th width="18%">所属地区</th>
        <th width="8%">审核状态</th>
        <th width="6%">是否删除</th>
        <th width="8%" nowrap="nowrap">操作</th>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr align="center">
          <td><c:if test="${cur.is_del eq 1}">
              <input name="pks" type="checkbox" id="pks_${cur.pd_id}" value="${cur.pd_id}_${cur.cls_id}_${cur.pd_type}" disabled="disabled" />
            </c:if>
            <c:if test="${cur.is_del eq 0}">
              <c:if test="${cur.audit_state ne 1}">
                <input name="pks" type="checkbox" id="pks_${cur.pd_id}" value="${cur.pd_id}_${cur.cls_id}_${cur.pd_type}" />
              </c:if>
              <c:if test="${cur.audit_state eq 1}">
                <input name="pks" type="checkbox" id="pks_${cur.pd_id}" value="${cur.pd_id}_${cur.cls_id}_${cur.pd_type}" disabled="disabled" />
              </c:if>
            </c:if></td>
          <td align="left"><c:url var="url" value="/manager/admin/PdInfo.do?method=view&amp;pd_id=${cur.pd_id}&amp;mod_id=${af.map.mod_id}" />
            <a style="text-decoration:none;" href="${url}" title="${fn:escapeXml(cur.pd_name)}"> ${fn:escapeXml(cur.pd_name)} </a></td>
          <td>${fn:escapeXml(cur.cls_name)}</td>
          <td><fmt:formatNumber pattern="#,##0.00" value="${cur.price_ref}"/></td>
          <td><c:if test="${cur.is_sell eq 1}">
              <c:out value="是"/>
            </c:if>
            <c:if test="${cur.is_sell eq 0}">
              <c:out value="否"/>
            </c:if></td>
          <td>${fn:escapeXml(cur.map.full_name)}</td>
          <td><c:if test="${cur.audit_state eq -1}"> <span style="color:#F00;">
              <c:out value="审核不通过"/>
              </span> </c:if>
            <c:if test="${cur.audit_state eq 0}">
              <c:out value="待审核"/>
            </c:if>
            <c:if test="${cur.audit_state eq 1}"> <span style="color:#060;">
              <c:out value="审核通过"/>
              </span> </c:if>
            <c:if test="${cur.audit_state eq 2}">
              <c:out value="待审核"/>
            </c:if></td>
          <td><c:if test="${cur.is_del eq 1}">
              <c:out value="是"/>
            </c:if>
            <c:if test="${cur.is_del eq 0}">
              <c:out value="否"/>
            </c:if></td>
          <td nowrap="nowrap"><c:if test="${cur.is_del eq 0}">
              <c:if test="${cur.audit_state ne 1}"> <a class="butbase" href="javascript:void(0);"><span class="icon-ok" onclick="doNeedMethod(null, 'PdInfo.do', 'audit', 'pd_id=${cur.pd_id}&' + $('#bottomPageForm').serialize())">审核</span></a> <a class="butbase" href="javascript:void(0);"><span class="icon-edit" onclick="confirmUpdate(null, 'PdInfo.do', 'pd_id=${cur.pd_id}&' + $('#bottomPageForm').serialize())">修改</span></a> <a class="butbase" href="javascript:void(0);"><span class="icon-remove" onclick="confirmDelete(null, 'PdInfo.do', 'pd_id=${cur.pd_id}&' + $('#bottomPageForm').serialize())">删除</span></a> </c:if>
              <c:if test="${cur.audit_state eq 1}"> <a class="butbase" href="javascript:void(0);"><span class="icon-ok" onclick="doNeedMethod(null, 'PdInfo.do', 'audit', 'pd_id=${cur.pd_id}&' + $('#bottomPageForm').serialize())">审核</span></a> <a class="butbase but-disabled" href="javascript:void(0);"><span title="已审核，不能删除" class="icon-edit">修改</span></a> <a class="butbase but-disabled" href="javascript:void(0);"><span title="已审核，不能删除" class="icon-remove">删除</span></a> </c:if>
            </c:if>
            <c:if test="${cur.is_del eq 1}"><span style="color:#999;"><img src="${ctx}/styles/images/shenhe_2.gif" width="55" height="18" />&nbsp;</span><span style="color:#999;"><img src="${ctx}/styles/images/xiugai_2.gif" width="55" height="18" />&nbsp;</span><span style="color:#999;"><img src="${ctx}/styles/images/shanchu_2.gif" width="55" height="18" /></span> </c:if></td>
        </tr>
        <c:if test="${vs.last eq true}">
          <c:set var="i" value="${vs.count}" />
        </c:if>
      </c:forEach>
      <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
        <tr align="center">
          <td>&nbsp;</td>
          <td nowrap="nowrap">&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td nowrap="nowrap">&nbsp;</td>
        </tr>
      </c:forEach>
    </table>
  </form>
  <div class="pageClass">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="PdInfo.do">
      <table width="98%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="10"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
					var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
					pager.addHiddenInputs("method", "list");
					pager.addHiddenInputs("pd_name_like", "${fn:escapeXml(af.map.pd_name_like)}");
					pager.addHiddenInputs("is_del", "${af.map.is_del}");
					pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
					pager.addHiddenInputs("province", "${af.map.province}");
					pager.addHiddenInputs("city", "${af.map.city}");
					pager.addHiddenInputs("country", "${af.map.country}");
					//pager.addHiddenInputs("pd_type", "${af.map.pd_type}");
					pager.addHiddenInputs("audit_state", "${af.map.audit_state}");
					pager.addHiddenInputs("cls_id", "${af.map.cls_id}");
					document.write(pager.toString());
	            	</script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#province").attr({"subElement": "city", "defaultText": "请选择...", "defaultValue": "", "selectedValue": "${af.map.province}"});
	$("#city"    ).attr({"subElement": "country", "defaultText": "请选择...", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country" ).attr({"defaultText": "请选择...", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#province").cs("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);
	$("#province").change();
	
	var f = document.forms[0];

	$("#btn_submit").click(function(){
		this.form.submit();
	});
});
//]]></script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true"/>
</body>
</html>
