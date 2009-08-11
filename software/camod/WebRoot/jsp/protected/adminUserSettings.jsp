<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<SCRIPT LANGUAGE="JavaScript">
	function enableUsername() {
		ideControl = document.forms[0].username.disabled = false;
	}		
</SCRIPT>

<!-- Main Content Begins -->  
<TABLE summary="" cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td width="100%">		

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">

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
			<td class="formTitle" height="20" colspan="3">User Settings for <c:out value="${userSettingsForm.firstName}"/> <c:out value="${userSettingsForm.lastName}"/>
				&nbsp;<camod:cshelp topic="user_settings_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
		</tr>
        <tr>
			<td class="formRequiredNotice" width="5">&nbsp</td>
			<td class="formLabel"><label for="field2">Username</label></td>
			<html:form action="AdminUserSettingsAction.do" onsubmit="enableUsername()">
			<td class="formField">
				<html:text disabled="true" styleClass="formFieldSized" property="username" size="30"/>
			</td>
		</tr>		
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field2">Institute / Organization</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" property="affiliation" size="30"/>
			</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="0">*</td>
			<td class="formRequiredLabel"><label for="field1">First Name</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" property="firstName" size="30"/>
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="0">*</td>
			<td class="formRequiredLabel"><label for="field1">Last Name</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" property="lastName" size="30"/>
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="0">*</td>
			<td class="formRequiredLabel"><label for="field1">Phone</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" property="phone" size="30"/>
			</td>
		</tr>		

		<tr>
			<td class="formRequiredNotice" width="0">*</td>
			<td class="formRequiredLabel"><label for="field1">Email</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" property="email" size="30"/>
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
				  </html:form>			
				</TABLE>
			</td>
		</tr>		
	</TABLE>
	</td></tr>
	    
</TABLE>

<!-- Main Content Ends  -->

<%@ include file="/jsp/footer.jsp" %>