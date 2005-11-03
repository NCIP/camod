<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page import="gov.nih.nci.camod.webapp.form.ClinicalMarkerForm" %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<%
	String aHistopathologyID = request.getParameter( "aHistopathologyID" );
	String aClinicalMarkerID = request.getParameter( "aClinicalMarkerID" );
	
	//if aTherapyID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "ClinicalMarkerAction.do?method=saveClinicalMarker";
	
	if ( aClinicalMarkerID != null && aClinicalMarkerID.length() > 0 ) {
		actionName = "ClinicalMarkerAction.do?method=editClinicalMarker";
	} else {
        aClinicalMarkerID = "";
    }
%>

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
		<td class="formTitle" height="20" colspan="3">Clinical Marker</td>
	</tr>

        <TR align="LEFT" valign="TOP">
                <td class="formRequiredNotice" width="5">&nbsp;</td>        
                <TD class="formLabel">Select Clinical Marker:</TD>
			<td class="formField">			
					<html:form action="<%= actionName %>" focus="name">
					<html:select styleClass="formFieldSized" size="1" property="name" onchange="getOptions(this);" >
					<html:options name="<%= Dropdowns.CLINICALMARKERSDROP %>" />										
				</html:select>
			</td>
	</tr>

        <TR align="LEFT" valign="TOP">
            <td class="formRequiredNotice" width="5">&nbsp;</td>        
            <TD class="formLabel">Value:</TD>
			<td class="formField">
			<html:text styleClass="formFieldSized" size="30" property="value" />			
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
	  				  
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <input type="hidden" name="histopathologyID" value="<%= aHistopathologyID %>">
				  <input type="hidden" name="clinicalMarkerID" value="<%= aClinicalMarkerID %>">
				  
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