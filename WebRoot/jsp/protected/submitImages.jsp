<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.Image" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.ImageForm" %>	
<%@ page import="gov.nih.nci.camod.Constants.*" %>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.Constants.Dropdowns" %>
<%@ page import="gov.nih.nci.camod.Constants.CaImage" %>
<%@ page import="java.util.*" %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>

<%
	// Retrieve ftp data from a resource bundle
	ResourceBundle theBundle = ResourceBundle.getBundle("camod");
	
	// Retrieve the server location
	String caImageServer = theBundle.getString(CaImage.CAIMAGESERVERTHUMBVIEW);

	System.out.println( "caImageServer=" + caImageServer );

	String aImageID = (String) request.getAttribute( "aImageID" );

	// if aImageID is passed in, then we are dealing with 
	// a previously entered model and are editing it
	// otherwise, create a new one
	
	String actionName = "ImageAction.do?method=save";
	
	if ( aImageID != null && aImageID.length() > 0) {
		actionName = "ImageAction.do?method=edit";
	}
	else {
	    aImageID = "";
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
			<td class="formTitle" height="20" colspan="3">Images &nbsp;<camod:cshelp mapId="images_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field1">Upload Construct Map (Image)</label></td>
			<td class="formField">
			
			<html:form action="<%= actionName %>" focus="fileLocation" enctype="multipart/form-data">	
			
			<c:if test="${not empty imageForm.fileServerLocation}">
				<c:set var="uri" value="javascript: rs('commentWin','viewLizardImage.do?aFileServerLocation=${imageForm.fileServerLocation}',600,600);"/>
			
				Current Image: <c:out value="${imageForm.fileServerLocation}"/><br>
				Current Image Thumbnail: <br>
					
				<a href='<c:out value="${uri}"/>'>							
				<img src="<%= caImageServer %><c:out value="${imageForm.fileServerLocation}"/>" height="40" width="40" alt="<c:out value="${imageForm.fileServerLocation}"/>" target="_blank">				
				Click to View</a>
				<br><br>									
				
			</c:if>
						
			<html:file styleClass="formFieldSized" size="40" property="fileLocation"/>	
			
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field1">Title of Construct<br>(Enter info only when uploading image)</label></td>
			<td class="formField">
				<html:textarea styleClass="formFieldSized" property="title" rows="4" cols="40"/>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Description of Construct<br>(Enter info only when uploading image)</label></td>
			<td class="formField">
				<html:textarea styleClass="formFieldSized" property="descriptionOfConstruct"  rows="4" cols="40" />	
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

	  				  <c:if test="${not empty aImageID}">
		  				  <html:submit property="action" styleClass="actionButton" onclick="confirm('Are you sure you want to delete?');">
							  <bean:message key="button.delete"/>
						  </html:submit>
				      </c:if>
				      					
					  <!--  Done this way since html:hidden doesn't seem to work correctly -->
					  <input type="hidden" name="aImageID" value="<%= aImageID %>">
					  	
					</html:form>			
				</TABLE>
			</td>
		</tr>
	</TABLE>

	<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>
