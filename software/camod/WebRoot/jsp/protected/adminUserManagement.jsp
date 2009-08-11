<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>
<%@ page import='gov.nih.nci.camod.Constants' %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>

<!-- adminUserManagement.jsp -->
<!-- Main Content Begins -->
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
		    <td class="formTitle" height="20" colspan="3">User Management &nbsp;
		    	<camod:cshelp topic="edit_user_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>		
		</tr>
		<html:form action="AdminEditUserPopulateAction">
		    
			<td class="resultsBoxGreyNoEnd" colspan="1">
				<html:select styleClass="formFieldSized" size="1" property="id">
					<html:optionsCollection name="<%= Dropdowns.USERSDROP %>" />	
				</html:select>
			</td>
		
	        <td  class="resultsBoxGreyNoSides" align="center" colspan="1">
				<TABLE cellpadding="4" cellspacing="0" border="0">	
			        <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton">
					    <bean:message key="button.edit"/>
				    </html:submit>	
				</TABLE>
			</td>
		    <td  class="resultsBoxGreyNoStart" align="center" colspan="1">
				<TABLE cellpadding="4" cellspacing="0" border="0">	
			        <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton">
					    <bean:message key="button.add"/>
				    </html:submit>	
				</TABLE>
			</td>
		</html:form>
	 </TABLE>   
</TABLE>	

<%@ include file="/jsp/footer.jsp" %>





