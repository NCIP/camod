<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>

<!-- setup some useful variables -->
<% pageContext.setAttribute("modelIdTag", Parameters.MODELID); %>
<% pageContext.setAttribute("modelSectionTag", Parameters.MODELSECTIONNAME); %>
<% pageContext.setAttribute("commentsIdTag", Parameters.COMMENTSID); %>

	   
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
		    <td class="formTitle" height="20" colspan="4">Comments Assignment &nbsp;
		    	<camod:cshelp topic="comments_assignment_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/> 
		    </td>		
		</tr>
		<html:form action="AdminCommentsAssignmentPopulateAction">
		    
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

    <c:if test="${not empty adminCommentsSearchResults}">
	<display:table id="row" name="${sessionScope.adminCommentsSearchResults}"
      pagesize = "15"
	  cellpadding="5" 
	  cellspacing="0" 
	  border="1"
 	  width = "100%"> 	 

 	    <display:column title="No." >
 	        <c:out value="${row_rowNum}"/>
 	    </display:column>      
 	    				          
 	    <c:set var="uri" value="/camod/ViewModelSectionAction.do?${modelIdTag}=${row.modelId}&${modelSectionTag}=${row.modelSection}&${commentsIdTag}=${row.id}"/>
 
		<display:column href="<%= (String) pageContext.getAttribute("uri") %>" title="Section/Model Descriptor" >
			<c:out escapeXml="false" value="${row.modelSection}"/> - <c:out value="${row.modelDescriptor}" escapeXml="false"/>
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





