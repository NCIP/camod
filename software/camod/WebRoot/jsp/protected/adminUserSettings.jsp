<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<SCRIPT LANGUAGE="JavaScript">
	function enableUsername() {
		ideControl = document.forms[0].username.disabled = false;
	}		
</SCRIPT>

<!-- Main Content Begins -->  
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly -->
<%@ include file="/jsp/adminMenu.jsp" %>
<tr><td>		
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="60%" height="100%">
	<html:form action="AdminUserSettingsAction.do" onsubmit="enableUsername()">
	<tr><td>
		<TABLE cellpadding="3" cellspacing="0" border="0" align="left">
		<tr>
		    <logic:messagesPresent>
				<ul>
				    <font color="red">
				        <html:messages id="error">
				            <li><%=error %></li>
				        </html:messages>
				    </font>
			    </ul>
			</logic:messagesPresent>
			<td class="formMessage" colspan="3">
				* indicates a required field
			</td>
		</tr>
		<tr>
			<th scope="col" class="formTitle" height="20" colspan="3">User Settings for <c:out value="${userSettingsForm.firstName}"/> <c:out value="${userSettingsForm.lastName}"/>
				&nbsp;<camod:cshelp topic="user_settings_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></th>
		</tr>
        <tr>
			<td class="formRequiredNotice" width="5">&nbsp</td>
			<td class="formLabel"><label for="username">Username</label></td>

			<td class="formField">
				<html:text disabled="true" styleClass="formFieldSized" styleId="username" property="username" size="30"/>
			</td>
			
		</tr>
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="affiliation">Institute / Organization</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" styleId="affiliation" property="affiliation" size="30"/>
			</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="0">*</td>
			<td class="formRequiredLabel"><label for="firstName">First Name</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" styleId="firstName" property="firstName" size="30"/>
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="0">*</td>
			<td class="formRequiredLabel"><label for="lastName">Last Name</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" styleId="lastName" property="lastName" size="30"/>
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="0">*</td>
			<td class="formRequiredLabel"><label for="phone">Phone</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" styleId="phone" property="phone" size="30"/>
			</td>
		</tr>		

		<tr>
			<td class="formRequiredNotice" width="0">*</td>
			<td class="formRequiredLabel"><label for="email">Email</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" styleId="email" property="email" size="30"/>
			</td>
		</tr>				
        <html:hidden property="principalInvestigator" />
        
		<tr>
			<td align="right" colspan="3">
				<TABLE cellpadding="4" cellspacing="0" border="0">
				
					  <html:submit styleClass="actionButton">
						  <bean:message key="button.submit"/>
					  </html:submit>
					  
					  <html:cancel styleClass="actionButton">
					  	  <bean:message key="button.cancel"/>
	  				  </html:cancel>
				  			
				</TABLE>
			</td>
		</tr>
				
		</TABLE>
	</td></tr>
	</html:form>	    
	</TABLE>
</td></tr>	    
</TABLE>	

<!-- Main Content Ends  -->

<%@ include file="/jsp/footer.jsp" %>