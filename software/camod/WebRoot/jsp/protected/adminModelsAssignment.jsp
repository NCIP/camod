<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<SCRIPT LANGUAGE="JavaScript">	
	function blankCurrentState() {
        document.curationAssignmentForm.currentState.value = '';
    }    			
</SCRIPT>

<!-- adminModelsAssignment.jsp -->
<!-- Main Content Begins -->

<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/adminMenu.jsp" %>
<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="50%" height="100%">
	<tr><td>	
		<TABLE cellpadding="3" cellspacing="0" border="0" width="100%">	
		<html:form action="SearchAdminAssignmentAction.do" >	
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
			    <th scope="col" class="formTitle" height="20" colspan="4"><label for="currentState">Models Assignment</label> &nbsp;<camod:cshelp topic="models_assignment_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></th>		
			</tr>		    
				<td class=resultsBoxGreyNoEnd>			
				    <html:select styleId="currentState" property="currentState">
				        <html:options name="<%=Constants.Dropdowns.CURATIONSTATESDROP%>"/>
			        </html:select>
				</td>
		        <td  class="resultsBoxGreyNoStart" align="right" colspan="3">
					<TABLE summary="This table is used to format page content" cellpadding="4" cellspacing="0" border="0">	
				        <html:submit styleClass="actionButton">
						    <bean:message key="button.submit"/>
					    </html:submit>	
					</TABLE>
				</td>
			</html:form>
		 </TABLE><br>	

    <c:if test="${not empty adminModelAssignSearchResults}">
	<display:table id="row" name="${sessionScope.adminModelAssignSearchResults}"
      pagesize = "15"
	  cellpadding="5" 
	  cellspacing="0" 
	  border="1"
 	  width = "100%"> 	 

 	    <display:column title="No." >
 	        <c:out value="${row_rowNum}"/>
 	    </display:column>      
		<display:column href="/camod/ViewModelAction.do?unprotected_method=populateModelCharacteristics&" paramId="aModelID" paramProperty="id" title="Model Descriptor" >
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
</td></tr>   
</TABLE>

<SCRIPT LANGUAGE="JavaScript">
	blankCurrentState();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>





