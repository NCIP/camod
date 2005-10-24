<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.Image" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.ImageForm" %>	
<%@ page import="gov.nih.nci.camod.Constants.*" %>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.Constants.Dropdowns" %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>

<%
	String aImageID = request.getParameter( "aImageID" );

	// if aImageID is passed in, then we are dealing with 
	// a previously entered model and are editing it
	// otherwise, create a new one
	
	String actionName = "ImageAction.do?method=save";
	
	if ( aImageID != null )
		actionName = "ImageAction.do?method=edit";
%>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
	<!-- -->

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">
		<tr>
			<td class="formMessage" colspan="3">* indicates a required field</td>
		</tr>

		<tr>
			<td class="formTitle" height="20" colspan="3">Images</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field1">Upload Construct Map (Image)</label></td>
			<td class="formField">
			
			<html:form action="<%= actionName %>" focus="fileLocation" enctype="multipart/form-data">	
			
			<% 
				 // Only display a thumbnail if Image exists
			     ImageForm theImageForm = (ImageForm) request.getSession().getAttribute("formdata");
					
				 if ( theImageForm.getFileServerLocation() != null ) {
				 	if ( ! theImageForm.getFileServerLocation().equals( "" ) ) {
				 	
				 		pageContext.setAttribute("fileServerLocationName", theImageForm.getFileServerLocation() );
			%>
						<c:set var="uri" value="javascript: rs('commentWin','viewLizardImage.do?aFileServerLocation=${fileServerLocationName}',600,600);"/>
					
						Current Image: <bean:write name="formdata" property="fileServerLocation"/><br>
						Current Image Thumbnail: <br>
							
						<a href='<c:out value="${uri}"/>'>			
						
						<img src="http://caimage-dev.nci.nih.gov/lizardtech/iserv/getthumb?cat=Model&amp;img=<bean:write name='formdata' property='fileServerLocation'/>&amp;thumbspec=" main="" alt="<bean:write name='formdata' property='fileServerLocation'/>" target="_blank">				
						Click to View</a><br><br>									
			<% 
					} 
				} 			
			%>
						
			<html:file styleClass="formFieldSized" size="40" property="fileLocation" name="formdata"/>	
			
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field1">Title of Construct<br>(Enter info only when uploading image)</label></td>
			<td class="formField">
				<html:textarea styleClass="formFieldSized" property="title" rows="4" cols="32" name="formdata"/>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Description of Construct<br>(Enter info only when uploading image)</label></td>
			<td class="formField">
				<html:textarea styleClass="formFieldSized" property="descriptionOfConstruct"  rows="4" cols="32"  name="formdata"/>	
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
