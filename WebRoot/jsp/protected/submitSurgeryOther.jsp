<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page import="gov.nih.nci.camod.webapp.form.SurgeryForm" %>	
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<script language="JavaScript" src="scripts/global.js"></script>

<%
	String aCarcinogenExposureID = request.getParameter( "aCarcinogenExposureID" );
	String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
	
	//if aCarcinogenExposureID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "SurgeryAction.do?method=save";
	
	if ( aCarcinogenExposureID != null && aCarcinogenExposureID.length() > 0 && isDeleted == null) {
		actionName = "SurgeryAction.do?method=edit";
	}
	else {
	    aCarcinogenExposureID = "";
	}
%>

<html:form action="<%= actionName %>" focus="name">

<!-- submitSurgeryOther.jsp -->
<!-- Main Content Begins -->
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
<!-- -->

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">
	
	<tr>
	    <html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>
	
	<tr>
		<td class="formTitle" height="20" colspan="3">Surgery
			<camod:cshelp topic="surgery_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Surgery:</label></td>
		<td class="formField">
			<br>
			<label for="field3">(if Surgery is not listed, then please<br>select "Other" from the list and specify it below)</label>
			<br>
			<br>
			<html:select styleClass="formFieldSized" size="1" property="name"  onclick="chkOtherName( this );">												
				<html:options name="<%= Dropdowns.SURGERYDROP %>"/>					
			</html:select>			
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Surgery:</label></td>
		<td class="formField">					
			<html:text styleClass="formFieldSized" size="30" property="otherName" />			
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Treatment Regimen:</label>
		<camod:cshelp topic="surgery_help" key="TREATMENT.REGIMEN" image="images/iconHelp.gif" text="Tool Tip Test 1" />		
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="regimen" size="30" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Age at Treatment:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="ageAtTreatment"  size="15" />
			<label for="field1">&nbsp;Units&nbsp;</label>
			<html:select styleClass="formFieldUnSized" size="1" property="ageAtTreatmentUnit" >												
				<html:options name="<%= Dropdowns.AGEUNITSDROP %>"/>					
			</html:select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Gender:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldUnSized" size="1" property="type" >												
				<html:options name="<%= Dropdowns.SEXDISTRIBUTIONDROP %>"/>					
			</html:select>
		</td>
	</tr>

	<tr>
		<td align="right" colspan="3">
				<TABLE cellpadding="4" cellspacing="0" border="0">
				
					  <html:submit styleClass="actionButton">
						  <bean:message key="button.submit"/>
					  </html:submit>
					  
					  <html:reset styleClass="actionButton">
					  	  <bean:message key="button.reset"/>
	  				  </html:reset>
	  				  
	  				  <c:if test="${not empty aCarcinogenExposureID}">
		  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
							  <bean:message key="button.delete"/>
						  </html:submit>
				      </c:if>							
				      
					  <!--  Done this way since html:hidden doesn't seem to work correctly -->
					  <input type="hidden" name="aCarcinogenExposureID" value="<%= aCarcinogenExposureID %>">
					  	
				  </html:form>			
				</TABLE>
		</td>
	</tr>
</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<SCRIPT>
chkOtherName();
</SCRIPT>
<%@ include file="/jsp/footer.jsp" %>