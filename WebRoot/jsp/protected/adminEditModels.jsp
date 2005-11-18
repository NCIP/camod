<%
/*
 * $Id: adminEditModels.jsp,v 1.1 2005-11-18 21:07:44 georgeda Exp $
 *
 * $Log: not supported by cvs2svn $
 *
 */
%>
<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" width="100%">
		
		<tr>
			<td class="formMessage" colspan="3">
				<logic:messagesPresent>
				  <ul>
				    <font color="red">
				      <html:messages id="error">
				        <li><%=error %></li>
				      </html:messages>
				    </font>
				  </ul>
				</logic:messagesPresent>
			</td>
		</tr>
		<tr>
		    <td class="formTitle" height="20" colspan="4">Edit Models (SuperUser)</td>		
		</tr>
		<html:form action="AdminEditModelsPopulateAction">
		    
			    <td class=resultsBoxGreyNoEnd>			
			        <html:select property="currentState">
			            <html:options name="<%=Constants.Dropdowns.CURATIONSTATESDROP%>"/>
		            </html:select>
			    </td>
		    
		
	        <td  class="resultsBoxGreyNoStart" align="right" colspan="3">
				<TABLE cellpadding="4" cellspacing="0" border="0">	
			        <html:submit styleClass="actionButton">
					    <bean:message key="button.submit"/>
				    </html:submit>	
				</TABLE>
			</td>
		</html:form>
	 </TABLE>
	<br>	

    <c:if test="${not empty adminModelSearchResults}">
	<display:table id="row" name="${sessionScope.adminModelSearchResults}"
      pagesize = "15"
	  cellpadding="5" 
	  cellspacing="0" 
	  border="1"
 	  width = "100%"> 	 

 	    <display:column title="No." >
 	        <c:out value="${row_rowNum}"/>
 	    </display:column>      
		<display:column href="/camod/SubmitAction.do?method=setModelConstants&" paramId="aModelID" paramProperty="id" title="Model Descriptor" >
			<c:out escapeXml="false" value="${row.modelDescriptor}"/>
		</display:column>
		<display:column title="Submitter's Name">
			<c:out escapeXml="false" value="${row.submitterName}"/>
		</display:column>     
		<display:column title="Entered on">
			<c:out value="${row.submittedDate}"/>
		</display:column>	                   
	</display:table>
	</c:if>

    </td></tr>
   
</TABLE>	

<%@ include file="/jsp/footer.jsp" %>





