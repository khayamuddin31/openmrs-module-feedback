<%@ include file="local_header.jsp"%>    


<h2><spring:message code="feedback.editSeverity"/></h2>

<b class="boxHeader"><spring:message code="feedback.severities"/></b>

<div class="box" >
<c:if test="${empty feedbackPageMessage}"> 

    <form method="post">
        <input type=hidden name=feedbackSeverityId value= <c:out value="${severity.feedbackSeverityId}"/> >
    <table>
        <tr>
            <td width="200"><spring:message code="feedback.severity"/></td>
            <td><input type="text" name="severity" value="${severity.severity}"  size="50" /></td>
        </tr>
	<tr class="oddRow">
            	<td width="200"><spring:message code="feedback.sortWeight"/></td>
                <td><input type="text" name="sortWeight"  size="50"  value="${severity.sortWeight}"/></td>
        </tr>
        <tr>    
            <td>
                <input type=hidden name=save value="1">
                <input type="submit" value="Save" />
            </td>
    </form>
            <td>
                <form method="post">
                    <input type=hidden name=feedbackSeverityId value= <c:out value="${severity.feedbackSeverityId}"/> >
                    <input type=hidden name=delete value="1">
                    <input type="submit" value="Delete" />
                </form>
            </td>        
        </tr>
    </table> 
</c:if>

</div>
        
        
<%@ include file="/WEB-INF/template/footer.jsp" %>