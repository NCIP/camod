<%

/**
 * 
 * $Id: searchTableOfContents.jsp,v 1.6 2006-05-10 14:23:14 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>
<%@ page import='gov.nih.nci.camod.Constants' %>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">

		<tr>
            <logic:messagesPresent>
                <td>
                    <TABLE width="100%">
                        <tr>
				        <br>
						<b><font color=red>
						    <html:messages id="errors">
							    <%=errors %>
						    </html:messages>
						</font></b>
		               </tr>
		           </TABLE>
		       </td>
		    </logic:messagesPresent>
		</tr>
				
		<tr>
			<td class="formTitle" height="20" colspan="3">Table of Contents</td>		
		</tr>	
			<c:set var="groupList" value="${TOC_QUERY_RESULTS}"/>
			<c:if test="${not empty groupList}">
			    <c:forEach var="group" items="${groupList}">
			        <tr><td></td></tr>
			  	    <tr>
				        <td class="formTitleBlue" height="20" colspan="3"><c:out value="${group.description}"/></td>				
			        </tr>		
			        <c:forEach var="query" items="${group.queries}">
			            <tr>
				            <td class="resultsBoxWhiteEnd">
				                <ul><li>
				                <html:link action="ViewTOCSearchResultsAction.do" paramId="<%=Constants.Parameters.TOCQUERYKEY%>" paramName="query" paramProperty="key" >
				                    <c:out escapeXml="false" value="${query.description}" /> ( <c:out value="${query.size}" /> )
	                            </html:link>
	                            </li></ul>
				            </td>
			            </tr>
			        </c:forEach>
			    </c:forEach>
		    </c:if>
	</TABLE>
	
</td></tr>
</TABLE>	

<%@ include file="/jsp/footer.jsp" %>