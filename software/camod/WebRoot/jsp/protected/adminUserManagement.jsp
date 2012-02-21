<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>
<%@ page import='gov.nih.nci.camod.Constants' %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>

<!-- adminUserManagement.jsp -->
<!-- Main Content Begins -->
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<!-- Took this out of sidebar.jsp and has to go here to format correctly -->
	<%@ include file="/jsp/adminMenu.jsp" %>
	<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="60%" height="100%">
	<tr><td>
	<TABLE cellpadding="3" cellspacing="0" border="0" width="100%">		
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
		    <th scope="col" class="formTitle" height="20" colspan="3"><label for="userId">User Management</label> &nbsp;
		    	<camod:cshelp topic="edit_user_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></th>		
		</tr>
		<html:form action="AdminEditUserPopulateAction">
		    
			<td class="resultsBoxGreyNoEnd" colspan="1">
				<html:select styleId="userId" styleClass="formFieldSized" size="1" property="id">
					<html:optionsCollection name="<%= Dropdowns.USERSDROP %>" />	
				</html:select>
			</td>
		
	        <td  class="resultsBoxGreyNoSides" align="center" colspan="1">
				<TABLE summary="This table is used to format page content" cellpadding="4" cellspacing="0" border="0">	
			        <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton">
					    <bean:message key="button.edit"/>
				    </html:submit>	
				</TABLE>
			</td>
		    <td  class="resultsBoxGreyNoStart" align="center" colspan="1">
				<TABLE summary="This table is used to format page content" cellpadding="4" cellspacing="0" border="0">	
			        <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton">
					    <bean:message key="button.add"/>
				    </html:submit>	
				</TABLE>
			</td>
		</html:form>
	 </TABLE> 
	 </td></tr></TABLE>  
</td></tr></TABLE>	

<%@ include file="/jsp/footer.jsp" %>





