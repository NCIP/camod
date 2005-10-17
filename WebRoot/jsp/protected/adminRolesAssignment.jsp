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
		    <td class="formTitle" height="20" colspan="4">Roles Assignment</td>		
		</tr>
		<html:form action="AdminRolesAssignmentPopulateAction">
		    
			    <td class=resultsBoxGreyNoEnd>			
			        <html:select property="currentRole">
			            <html:options name="<%=Constants.FORMDATA%>" property="roles"/>
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

    <c:if test="${not empty adminRolesSearchResults}">
	<display:table id="row" name="${sessionScope.adminRolesSearchResults}"
      pagesize = "15"
	  cellpadding="5" 
	  cellspacing="0" 
	  border="1"
 	  width = "100%"> 	 

 	    <display:column title="No." >
 	        <c:out value="${row_rowNum}"/>
 	    </display:column>      
		<display:column href="/camod/AdminEditUserRolesPopulateAction.do?" paramId="<%=Constants.Parameters.PERSONID%>" paramProperty="id" title="User" >
			<c:out escapeXml="false" value="${row.displayName}"/>
		</display:column>
		<display:column title="Member Status">
		    <c:forEach var="role" items="${row.roles}">
		        <c:out value="${role}"/><br>
		    </c:forEach>&nbsp
		</display:column>     
		<display:column title="Contact Information">
			<c:out value="${row.contactInfo.institute}"/><br>
			<c:out value="${row.contactInfo.email}"/><br>
			<c:out value="${row.contactInfo.phone}"/><br>
		</display:column>
	</display:table>
	</c:if>

    </td></tr>
   
</TABLE>	

<%@ include file="/jsp/footer.jsp" %>





