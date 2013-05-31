<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page buffer="32kb"%>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>

<!-- adminEditUserRoles.jsp -->
<!-- Main Content Begins -->
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly -->
<%@ include file="/jsp/adminMenu.jsp" %>
<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="60%" height="100%">	
	<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0">
	<html:form method="get" action="AdminEditUserRolesAction.do">
		<tr>
			<td class="formTitle" height="20" colspan="3">Edit User Roles for <c:out value="${rolesAssignmentForm.displayName}"/>
				&nbsp;<camod:cshelp topic="edit_user_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp</td>
			<td class="formLabel"><label for="field1">Coordinator</label></td>
			<td class="formField">
			    
			    <html:checkbox styleClass="formFieldSized" property="coordinator"> </html:checkbox>
			</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp</td>
			<td class="formLabel"><label for="field1">Editor</label></td>
			<td class="formField">
			    <html:checkbox styleClass="formFieldSized" property="editor"> </html:checkbox>
			</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp</td>
			<td class="formLabel"><label for="field1">Screener</label></td>
			<td class="formField">
			    <html:checkbox styleClass="formFieldSized" property="screener"> </html:checkbox>
			</td>
		</tr>
		<html:hidden property="personId" />
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

<%@ include file="/jsp/footer.jsp" %>