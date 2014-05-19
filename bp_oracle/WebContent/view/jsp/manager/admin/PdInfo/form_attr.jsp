<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>

<tbody id="attr_tbody">
  <c:if test="${not empty pdInfoCustomFieldContentList}">
    <c:forEach var="cur" items="${pdInfoCustomFieldContentList}">
      <c:if test="${cur.type eq 1}">
        <c:if test="${cur.is_show eq 1}">
          <tr id="tr_${cur.custom_field_id}">
            <td width="12%" class="title_item" nowrap="nowrap" rowspan="2" align="right"><c:if test="${cur.is_required eq 1}"><span style="color: #f00;">*</span></c:if>
              ${fn:escapeXml(cur.custom_field_name)}：</td>
            <td width="88%" colspan="2"><c:forEach items="${cur.baseAttributeSonList}" var="cur_son">
                <label for="zdy_${cur_son.id}" style="width:80px;" >
                  <c:if test="${cur.map.attr_son_id eq cur_son.id}" var="isChecked">
                    <input type="radio" name="zdy_content_${cur.custom_field_id}" id="zdy_${cur_son.id}" value="${cur_son.id}" checked="checked" />
                    &nbsp; ${fn:escapeXml(cur_son.attr_show_name)} </c:if>
                  <c:if test="${not isChecked}">
                    <input type="radio" name="zdy_content_${cur.custom_field_id}" id="zdy_${cur_son.id}" value="${cur_son.id}" />
                    &nbsp; ${fn:escapeXml(cur_son.attr_show_name)} </c:if>
                </label>
                &nbsp;&nbsp; </c:forEach>
              <html-el:hidden property="zdy_column" value="${cur.custom_field_id},${cur.type},${cur.custom_field_name},${cur.is_required},${cur.is_show},${cur.order_value}"  styleClass="webinput" /></td>
          </tr>
          <tr>
            <td width="88%" colspan="2"><html-el:text property="zdy_content2_${cur.custom_field_id}" styleId="zdy_${cur.custom_field_id}"  styleClass="webinput" value="${cur.custom_field_content}"/></td>
          </tr>
        </c:if>
        <c:if test="${cur.is_show eq 0}">
          <tr id="tr_${cur.custom_field_id}">
            <td width="12%" class="title_item" nowrap="nowrap" align="right"><c:if test="${cur.is_required eq 1}"><span style="color: #F00;">*</span></c:if>
              ${fn:escapeXml(cur.custom_field_name)}：</td>
            <td width="88%" colspan="2"><c:forEach items="${cur.baseAttributeSonList}" var="cur_son">
                <label for="zdy_${cur_son.id}" style="width:80px;" >
                  <c:if test="${cur.map.attr_son_id eq cur_son.id}" var="isChecked">
                    <input type="radio" name="zdy_content_${cur.custom_field_id}" id="zdy_${cur_son.id}" value="${cur_son.id}" checked="checked" />
                    &nbsp; ${fn:escapeXml(cur_son.attr_show_name)} </c:if>
                  <c:if test="${not isChecked}">
                    <input type="radio" name="zdy_content_${cur.custom_field_id}" id="zdy_${cur_son.id}" value="${cur_son.id}" />
                    &nbsp; ${fn:escapeXml(cur_son.attr_show_name)} </c:if>
                </label>
                &nbsp;&nbsp; </c:forEach>
              <html-el:hidden property="zdy_column" value="${cur.custom_field_id},${cur.type},${cur.custom_field_name},${cur.is_required},${cur.is_show},${cur.order_value}"  styleClass="webinput" /></td>
          </tr>
        </c:if>
      </c:if>
      <c:if test="${cur.type eq 3}">
        <tr id="tr_${cur.custom_field_id}">
          <td width="12%" class="title_item" nowrap="nowrap" align="right"><c:if test="${cur.is_required eq 1}"><span style="color: #F00;">*</span></c:if>
            ${fn:escapeXml(cur.custom_field_name)}：</td>
          <td colspan="2" width="88%"><c:forEach items="${cur.baseAttributeSonList}" var="cur_son">
              <label for="zdy_${cur_son.id}" style="width:80px;" >
                <c:if test="${cur.custom_field_content eq cur_son.id}" var="isChecked">
                  <input type="radio" name="zdy_content_${cur.custom_field_id}" id="zdy_${cur_son.id}" value="${cur_son.id}" checked="checked" />
                  &nbsp; ${fn:escapeXml(cur_son.attr_show_name)} </c:if>
                <c:if test="${not isChecked}">
                  <input type="radio" name="zdy_content_${cur.custom_field_id}" id="zdy_${cur_son.id}" value="${cur_son.id}" />
                  &nbsp; ${fn:escapeXml(cur_son.attr_show_name)} </c:if>
              </label>
              &nbsp;&nbsp; </c:forEach>
            <html-el:hidden property="zdy_column" value="${cur.custom_field_id},${cur.type},${cur.custom_field_name},${cur.is_required},${cur.is_show},${cur.order_value}"  styleClass="webinput" /></td>
        </tr>
      </c:if>
      <c:if test="${cur.type eq 4}">
        <tr id="tr_${cur.custom_field_id}">
          <td width="12%" class="title_item" nowrap="nowrap" align="right"><c:if test="${cur.is_required eq 1}"><span style="color: #F00;">*</span></c:if>
            ${fn:escapeXml(cur.custom_field_name)}：</td>
          <td colspan="2" width="88%"><c:set var="fieldContent" value=",${cur.custom_field_content}," />
            <c:forEach items="${cur.baseAttributeSonList}" var="cur_son">
              <c:set var="fieldsSonId" value=",${cur_son.id}," />
              <label for="zdy_${cur_son.id}" style="width:80px;" >
                <c:if test="${fn:contains(fieldContent, fieldsSonId)}" var="isContains">
                  <input type="checkbox" name="zdy_content_${cur.custom_field_id}" id="zdy_${cur_son.id}" value="${cur_son.id}" checked="checked" />
                </c:if>
                <c:if test="${not isContains}" >
                  <input type="checkbox" name="zdy_content_${cur.custom_field_id}" id="zdy_${cur_son.id}" value="${cur_son.id}" />
                </c:if>
                &nbsp; ${fn:escapeXml(cur_son.attr_show_name)} </label>
            </c:forEach>
            <html-el:hidden property="zdy_column" value="${cur.custom_field_id},${cur.type},${cur.custom_field_name},${cur.is_required},${cur.is_show},${cur.order_value}"  styleClass="webinput" /></td>
        </tr>
      </c:if>
      <c:if test="${cur.type eq 5}">
        <tr id="tr_${cur.custom_field_id}">
          <td width="12%" class="title_item" nowrap="nowrap" align="right"><c:if test="${cur.is_required eq 1}"><span style="color: #F00;">*</span></c:if>
            ${fn:escapeXml(cur.custom_field_name)}：</td>
          <td colspan="2" width="88%"><select name="zdy_content_${cur.custom_field_id}" id="zdy_${cur.custom_field_id}" style="width:80px">
              <option value="">请选择...</option>
              <c:forEach items="${cur.baseAttributeSonList}" var="cur_son">
                <c:if test="${cur.custom_field_content eq cur_son.id}" var="isSelected">
                  <option value="${cur_son.id}" selected="selected">${fn:escapeXml(cur_son.attr_show_name)}</option>
                </c:if>
                <c:if test="${not isSelected}">
                  <option value="${cur_son.id}" >${fn:escapeXml(cur_son.attr_show_name)}</option>
                </c:if>
              </c:forEach>
            </select>
            <html-el:hidden property="zdy_column" value="${cur.custom_field_id},${cur.type},${cur.custom_field_name},${cur.is_required},${cur.is_show},${cur.order_value}"  styleClass="webinput" /></td>
        </tr>
      </c:if>
    </c:forEach>
  </c:if>
</tbody>
