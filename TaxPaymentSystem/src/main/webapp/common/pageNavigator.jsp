<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/18
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<s:if test="pageHelper.totalCount>0">
<table width="100%" class="pageDown" border="0" cellspacing="0"
       cellpadding="0">
    <tr>
        <td align="right">
            总共<s:property value="pageHelper.totalCount"/>条记录，当前第<s:property value="pageHelper.currentPageNo"/>页，共<s:property value="pageHelper.totalPagesCount"/>页 &nbsp;&nbsp;
            <s:if test="pageHelper.currentPageNo>1">
                <a href="javaScript:doGoPage(<s:property value='pageHelper.currentPageNo-1'/> )">上一页</a>&nbsp;
            </s:if>
            <s:if test="pageHelper.currentPageNo<pageHelper.totalPagesCount">
                &nbsp;<a href="javaScript:doGoPage(<s:property value='pageHelper.currentPageNo+1'/> )">下一页</a>
            </s:if>
            到&nbsp;<input type="text" style="width: 30px;" id="pageNo" name="currentPageNo" onkeypress="if(event.keyCode == 13){doGoPage(this.value);}" min="1"
                          max="" value="<s:property value="pageHelper.currentPageNo"/>" /> &nbsp;&nbsp;
        </td>
    </tr>
</table>
</s:if>
<s:else>暂无查询到数据！</s:else>
<script type="text/javascript">
    function doGoPage(currentPageNo) {
        document.getElementById("pageNo").value=currentPageNo;
        document.forms[0].action=url;
        document.forms[0].submit();
    }
    function doSearch(){
        $("#pageNo").val(1);
        document.forms[0].action=url;
        document.forms[0].submit();
    }
</script>