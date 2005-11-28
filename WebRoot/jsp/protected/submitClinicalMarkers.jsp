<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page import="gov.nih.nci.camod.webapp.form.ClinicalMarkerForm" %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<%
	String aHistopathologyID = request.getParameter( "aHistopathologyID" );
	String aClinicalMarkerID = request.getParameter( "aClinicalMarkerID" );
	String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);	
	
	//if aClinicalMarkerID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "ClinicalMarkerAction.do?method=save";
	
	if ( aClinicalMarkerID != null && aClinicalMarkerID.length() > 0 && isDeleted == null) {
		actionName = "ClinicalMarkerAction.do?method=edit";
	} else {
        aClinicalMarkerID = "";
    }
%>
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
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
                <td class="formRequiredNotice" width="5">*</td>        
                <TD class="formRequiredLabel">Select Clinical Marker:</TD>
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
	  				  
	  		      <c:if test="${not empty aClinicalMarkerID}">
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
			      </c:if>	  				  
	  				  
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <input type="hidden" name="aHistopathologyID" value="<%= aHistopathologyID %>">
				  <input type="hidden" name="aClinicalMarkerID" value="<%= aClinicalMarkerID %>">
				  
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