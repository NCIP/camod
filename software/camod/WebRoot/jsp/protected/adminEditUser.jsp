<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page buffer="32kb"%>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>

<!-- adminEditUser.jsp -->
<!-- Main Content Begins -->
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0">

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
			<td class="formTitle" height="20" colspan="3">
			<c:if test="${empty editUserForm.id}">
			    Add User &nbsp;<camod:cshelp topic="add_user_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/>
			</c:if>
			<c:if test="${not empty editUserForm.id}">
			    Edit User&nbsp;<camod:cshelp topic="edit_user_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/>
			</c:if>
			</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field2">Institute / Organization</label></td>
			<html:form method="get" action="AdminEditUserAction.do">
			<td class="formField">
				<html:text styleClass="formFieldSized" property="affiliation" size="30"/>
			</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field2">First Name</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" property="firstName" size="30"/>
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field2">Last Name</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" property="lastName" size="30"/>
			</td>
		</tr>
	
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field2">Username</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" property="username" size="30"/>
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
			<td class="formRequiredNotice" width="5">&nbsp</td>
			<td class="formLabel"><label for="field1">Principal Investigator</label></td>
			<td class="formField">
			    <html:checkbox styleClass="formFieldSized" property="principalInvestigator"> </html:checkbox>
			</td>
		</tr>
	
	    <html:hidden property="id" />
	    
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
</td></tr></TABLE>

<%@ include file="/jsp/footer.jsp" %>