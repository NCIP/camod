<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>

<SCRIPT LANGUAGE="JavaScript">
		
	function checkFields() {
	
		ideControl = document.forms[0].principalInvestigator;
	
		if( ideControl.checked == true ) {
		    document.forms[0].piFirstName.disabled = true;
		    document.forms[0].piFirstName.value= '';
		    document.forms[0].piFirstName.className = "formFieldSizedDisabled";
			document.forms[0].piLastName.disabled = true;
			document.forms[0].piLastName.value= '';
			document.forms[0].piLastName.className = "formFieldSizedDisabled";
			document.forms[0].piUsername.disabled = true;
			document.forms[0].piUsername.value= '';
			document.forms[0].piUsername.className = "formFieldDisabled";
			document.forms[0].piEmail.disabled = true;
			document.forms[0].piEmail.value= '';
			document.forms[0].piEmail.className = "formFieldSizedDisabled";
		}
		else {
		    document.forms[0].piUsername.disabled = false;
		    document.forms[0].piUsername.className = "formFieldEnabled";
		    
		    ideControl = document.forms[0].piUsername;
			
		    if( ideControl.value == '' ) {
		        document.forms[0].piFirstName.disabled = false;
		        document.forms[0].piFirstName.className = "formFieldSizedEnabled";
			    document.forms[0].piLastName.disabled = false;
			    document.forms[0].piLastName.className = "formFieldSizedEnabled";
			    document.forms[0].piEmail.disabled = false;
			    document.forms[0].piEmail.className = "formFieldSizedEnabled";
		    }
		    else {
		        document.forms[0].piFirstName.disabled = true;
		        document.forms[0].piFirstName.value= '';
		        document.forms[0].piFirstName.className = "formFieldSizedDisabled";
			    document.forms[0].piLastName.disabled = true;
			    document.forms[0].piLastName.value= '';
			    document.forms[0].piLastName.className = "formFieldSizedDisabled";
			    document.forms[0].piEmail.disabled = true;
			    document.forms[0].piEmail.value= '';
			    document.forms[0].piEmail.className = "formFieldSizedDisabled";
		    }
		}
	}
</SCRIPT>

<!-- register.jsp -->
<!-- Main Content Begins --> 
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/searchMenu.jsp" %>
<tr><td>		
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="60%" height="100%">
	<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" align="left">
	<html:form action="RegisterUserAction.do">
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
			<td class="formTitle" height="20" colspan="3">Register for an account &nbsp 
			<camod:cshelp topic="register_user_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="affiliation">Institute / Organization</label></td>			
			<td class="formField">
				<html:text styleId="affiliation" styleClass="formFieldSized" property="affiliation" size="30"/>
			</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="0">*</td>
			<td class="formRequiredLabel"><label for="firstName">First Name</label></td>
			<td class="formField">
				<html:text styleId="firstName" styleClass="formFieldSized" property="firstName" size="30"/>
			</td>
		</tr>		
		<tr>
			<td class="formRequiredNotice" width="0">*</td>
			<td class="formRequiredLabel"><label for="lastName">Last Name</label></td>
			<td class="formField">
				<html:text styleId="lastName" styleClass="formFieldSized" property="lastName" size="30"/>
			</td>
		</tr>		
		<tr>
			<td class="formRequiredNotice" width="0">*</td>
			<td class="formRequiredLabel"><label for="phone">Phone</label></td>
			<td class="formField">
				<html:text styleId="phone" styleClass="formFieldSized" property="phone" size="30"/>
			</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="0">*</td>
			<td class="formRequiredLabel"><label for="email">Email</label></td>
			<td class="formField">
				<html:text styleId="email" styleClass="formFieldSized" property="email" size="30"/>
			</td>
		</tr>						
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="principalInvestigator">Check if you are a principal investigator / lab chief</label></td>
			<td class="formField" align="left" >
			    <html:checkbox styleId="principalInvestigator" styleClass="formFieldSized" property="principalInvestigator" onclick="checkFields()" > </html:checkbox>
			</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="piUsername">If you are not a principal investigator / lab chief, select your principal investigator from the list.  If your principal investigator is not in the list, please fill in information below.</label></td>
			<td class="formField">
				<html:select styleId="piUsername" styleClass="formFieldSizedEnabled" size="1" property="piUsername"  onchange="checkFields()" >
					<html:optionsCollection name="<%= Dropdowns.PRINCIPALINVESTIGATORDROP %>" />	
				</html:select>
			</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="0">*</td>
			<td class="formRequiredLabel"><label for="piFirstName">Principal Investigator First Name</label></td>
			<td class="formField">
				<html:text styleId="piFirstName" styleClass="formFieldSizedEnabled" property="piFirstName" size="30"/>
			</td>
		</tr>		
		<tr>
			<td class="formRequiredNotice" width="0">*</td>
			<td class="formRequiredLabel"><label for="piLastName">Principal Investigator Last Name</label></td>
			<td class="formField">
				<html:text styleId="piLastName" styleClass="formFieldSizedEnabled" property="piLastName" size="30"/>
			</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="0">*</td>
			<td class="formRequiredLabel"><label for="piEmail">Principal Investigator E-mail</label></td>
			<td class="formField">
				<html:text styleId="piEmail" styleClass="formFieldSizedEnabled" property="piEmail" size="30"/>
			</td>
		</tr>		
		<tr>
			<td align="right" colspan="3">
				<TABLE summary="This table is used to format page content" cellpadding="4" cellspacing="0" border="0">				
					  <html:submit styleClass="actionButton">
						  <bean:message key="button.submit"/>
					  </html:submit>					  
					  <html:cancel styleClass="actionButton">
					  	  <bean:message key="button.cancel"/>
	  				  </html:cancel>				  			
				</TABLE>
			</td>
		</tr>
	</html:form>		
	</TABLE>	
	</td></tr>
	</TABLE>
</td></tr>	    
</TABLE>

<SCRIPT LANGUAGE="JavaScript">
    checkFields();
</SCRIPT>

<!-- Main Content Ends  -->

<%@ include file="/jsp/footer.jsp" %>