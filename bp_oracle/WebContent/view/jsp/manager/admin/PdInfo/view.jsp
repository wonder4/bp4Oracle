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
    <html-el:form action="/admin/PdInfo" enctype="multipart/form-data">
      <table width="100%" border="0" align="left" cellpadding="1" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="3">商铺产品基本信息</th>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">产品名称：</td>
          <td colspan="2" width="88%">${fn:escapeXml(af.map.pd_name)}</td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">产品类型：</td>
          <td colspan="2" width="88%"><c:if test="${af.map.pd_type eq 0}">
              <c:out value="普通产品"/>
            </c:if>
            <c:if test="${af.map.pd_type eq 1}">
              <c:out value="供应信息"/>
            </c:if>
            <c:if test="${af.map.pd_type eq 2}">
              <c:out value="求购信息"/>
            </c:if></td>
        </tr>
        <c:forEach var="cur" items="${pdInfoCustomFieldContentList}">
          <tr>
            <td nowrap="nowrap" class="title_item">${cur.custom_field_name}：</td>
            <td><c:if test="${cur.type eq 1 and cur.is_show eq 1}"> ${cur.custom_field_content} </c:if>
              <c:if test="${cur.type eq 1}">
                <c:forEach var="cur_son" items="${cur.baseAttributeSonList}">
                  <c:if test="${cur_son.id eq cur.map.attr_son_id}"> ${cur_son.attr_show_name} </c:if>
                </c:forEach>
              </c:if>
              <c:if test="${cur.type eq 3 or cur.type eq 5}">
                <c:forEach var="cur_son" items="${cur.baseAttributeSonList}">
                  <c:if test="${cur_son.id eq cur.custom_field_content}"> ${cur_son.attr_show_name} </c:if>
                </c:forEach>
              </c:if>
              <c:if test="${cur.type eq 4}">
                <c:set var="fieldContent" value=",${cur.custom_field_content}," />
                <c:forEach items="${cur.baseAttributeSonList}" var="cur_son">
                  <c:set var="fieldsSonId" value=",${cur_son.id}," />
                  <c:if test="${fn:contains(fieldContent, fieldsSonId)}" var="isContains"> ${cur_son.attr_show_name}&nbsp; </c:if>
                </c:forEach>
              </c:if></td>
          </tr>
        </c:forEach>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">产品类别：</td>
          <td colspan="2" width="88%">${fn:escapeXml(af.map.cls_name)}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">产品简介：</td>
          <td colspan="2"><html-el:textarea styleId="pd_desc" property="pd_desc" rows="7" style="width:597px" disabled="true"/></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">所属区域：</td>
          <td colspan="2" width="88%">${fn:escapeXml(af.map.map.full_name)}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">主图地址：</td>
          <td colspan="2"><c:if test="${not empty (af.map.main_pic)}"> <img src="${ctx}/${fn:substringBefore(af.map.main_pic, '.')}_240.${fn:substringAfter(af.map.main_pic, '.')}"}" /> </c:if>
            <c:if test="${empty (af.map.main_pic)}"> <img src="${ctx}/images/no_img.gif"}" /> </c:if></td>
        </tr>
        <tr id="trFile">
          <td width="12%" class="title_item">产品轮播图片：</td>
          <td colspan="2"><c:forEach var="cur" items="${af.map.pdImgsList}" varStatus="vs"> <span><img src="${ctx}/${cur.file_path}" style="width:100px;height:58px;"/>
              <c:if test="${vs.count%4 eq 0}"><br/>
              </c:if>
              <c:if test="${vs.count%4 ne 0}">&nbsp;&nbsp;</c:if>
              </span></c:forEach></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">参考价格：</td>
          <td colspan="2" width="88%">${fn:escapeXml(af.map.price_ref)}<span >单位：元</span></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">是否上架：</td>
          <td colspan="2" width="88%"><c:if test="${af.map.is_sell eq 0}">
              <c:out value="否"/>
            </c:if>
            <c:if test="${af.map.is_sell eq 1}">
              <c:out value="是"/>
            </c:if></td>
        </tr>
        <c:if test="${af.map.is_sell eq 1}">
          <tr>
            <td nowrap="nowrap" class="title_item">上架时间：</td>
            <td  colspan="2" height="24"><fmt:formatDate value="${af.map.up_date}" pattern="yyyy-MM-dd" var="_up_date" />
              <c:out value="${_up_date}"/></td>
          </tr>
          <tr>
            <td nowrap="nowrap" class="title_item">下架时间：</td>
            <td  colspan="2" height="24"><fmt:formatDate value="${af.map.down_date}" pattern="yyyy-MM-dd" var="_down_date" />
              <c:out value="${_down_date}"/></td>
          </tr>
        </c:if>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">最小起订：</td>
          <td colspan="2" width="88%">${fn:escapeXml(af.map.order_min_num)}&nbsp;&nbsp;<span >单位：只、个</span></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">最大供货：</td>
          <td colspan="2" width="88%">${fn:escapeXml(af.map.order_max_supply)}&nbsp;&nbsp;<span >单位：只、个</span></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">是否推荐：</td>
          <td colspan="2" width="88%"><c:if test="${af.map.is_commend eq 0}">
              <c:out value="否"/>
            </c:if>
            <c:if test="${af.map.is_commend eq 1}">
              <c:out value="是"/>
            </c:if></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">是否特价：</td>
          <td colspan="2" width="88%"><c:if test="${af.map.is_spec_price eq 0}">
              <c:out value="否"/>
            </c:if>
            <c:if test="${af.map.is_spec_price eq 1}">
              <c:out value="是"/>
            </c:if></td>
        </tr>
        <c:if test="${af.map.is_spec_price eq 1}">
          <tr>
            <td width="12%" nowrap="nowrap" class="title_item">特价价格：</td>
            <td colspan="2" width="88%">${fn:escapeXml(af.map.spec_price)}</td>
          </tr>
        </c:if>
       <tr>
        <td nowrap="nowrap" class="title_item">是否促销：</td>
        <td colspan="2"><c:if test="${af.map.is_promotion eq 1}">
            <c:out value="是"/>
          </c:if>
          <c:if test="${af.map.is_promotion eq 0}">
            <c:out value="否"/>
          </c:if></td>
       </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">排序值：</td>
          <td colspan="2" width="88%">${fn:escapeXml(af.map.order_value)}</td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item">内容：</td>
          <td colspan="2" width="88%">${af.map.pd_content}</td>
        </tr>
        <c:if test="${not empty af.map.audit_user_id}">
          <tr>
            <td width="12%" nowrap="nowrap" class="title_item">审核状态：</td>
            <td colspan="2" width="88%"><c:if test="${af.map.audit_state eq -1}"> <span style="color:#F00;">
                <c:out value="审核不通过"/>
                </span> </c:if>
              <c:if test="${(af.map.audit_state eq 0) or (af.map.audit_state eq 2)}">
                <c:out value="待审核"/>
              </c:if>
              <c:if test="${af.map.audit_state eq 1}"> <span style="color:#060;">
                <c:out value="审核通过"/>
                </span> </c:if></td>
          </tr>
          <tr>
            <td width="12%" nowrap="nowrap" class="title_item">审核说明：</td>
            <td colspan="2" width="88%">${af.map.audit_desc}</td>
          </tr>
          <tr>
            <td width="12%" nowrap="nowrap" class="title_item">审核时间：</td>
            <td colspan="2" width="88%"><fmt:formatDate value="${af.map.audit_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
          </tr>
        </c:if>
        <tr>
          <td colspan="3" align="center"><html-el:button property="" value="返 回" styleClass="bgButton" onclick="history.back();"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<jsp:include page="../_global_manager_page_bottom.jsp" flush="true"/>
</body>
</html>
