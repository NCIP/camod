<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.webapp.form.AvailabilityForm" %>
<%@ page import='gov.nih.nci.camod.Constants.Dropdowns.*' %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>

<%
	String aAvailabilityID = request.getParameter( "aAvailabilityID" );
	String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
    
	//if aAvailabilityID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	String actionName = "InvestigatorAction.do?method=save";
	
	if ( aAvailabilityID != null && aAvailabilityID.length() > 0 && isDeleted == null) {
		actionName = "InvestigatorAction.do?method=edit";
	}
	else {
		aAvailabilityID = "";
	}
%>

<html:form action="<%= actionName %>" focus="name">

<!-- submitInvestigator.jsp -->
<!-- Main Content Begins -->
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE  cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
<!-- -->

	<TABLE  cellpadding="3" cellspacing="0" border="0" align="left">
	
	<tr>
	    <html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>
	
	<tr>
		<td class="formTitle" height="20" colspan="3">Available from Investigator
			<camod:cshelp topic="investigator_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
	</tr>
	
        <tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Strain Name:</label>
		</td>
		<td class="formField">			
				<html:hidden property="source" />
				<html:text styleClass="formFieldSized" size="40" property="name"  />			
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Principal Investigator:</label></td>
			<td class="formField">
				<html:select styleClass="formFieldSized" size="1" property="principalInvestigator">
					<html:optionsCollection name="<%= Dropdowns.PRINCIPALINVESTIGATORDROP %>" />	
				</html:select>
			</td>
		</tr>
			
        
	<tr>
		<td align="right" colspan="3">
			<!-- action buttons begins -->
			<TABLE cellpadding="4" cellspacing="0" border="0">
				
					  <html:submit styleClass="actionButton">
						  <bean:message key="button.submit"/>
					  </html:submit>
					  
					  <html:reset styleClass="actionButton">
					  	  <bean:message key="button.reset"/>
	  				  </html:reset>

				      <c:if test="${not empty aAvailabilityID}">
						  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
						      <bean:message key="button.delete" />
						  </html:submit>
					  </c:if>
	  				  
					  <!--  Done this way since html:hidden doesn't seem to work correctly -->
  				  	  <input type="hidden" name="aAvailabilityID" value="<%= aAvailabilityID %>">	  				  
				  
				  </html:form>			
			</TABLE>
			<!-- action buttons end -->
		</td>
	</tr>
</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>