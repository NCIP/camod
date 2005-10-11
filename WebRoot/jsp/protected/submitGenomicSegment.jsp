<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.GenomicSegment" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.GenomicSegmentForm" %>	
<%@ page import="gov.nih.nci.camod.Constants.*" %>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.Constants.Dropdowns" %>

<%
	String aGenomicSegmentID = request.getParameter( "aGenomicSegmentID" );

	GenomicSegmentForm theGenomicSegmentForm = (GenomicSegmentForm) request.getSession().getAttribute(Constants.FORMDATA);
	
	boolean booleanLocationOfIntegration = true;
	if (theGenomicSegmentForm.getLocationOfIntegration() != null )
		if (theGenomicSegmentForm.getLocationOfIntegration().equals("Targeted") )
			booleanLocationOfIntegration = false;	
		
	//if aGenomicSegmentID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "GenomicSegmentAction.do?method=save";
	
	if ( aGenomicSegmentID != null )
		actionName = "GenomicSegmentAction.do?method=edit";
%>

<SCRIPT LANGUAGE="JavaScript">
		
	function chkOther( control ) {
		ideControl = document.forms[0].otherSegmentName;
			
		if( control.value == 'Other' )
			ideControl.disabled = false;
		else {
			ideControl.value = null;
			ideControl.disabled = true;
		}
	}
	
	function chkIntegration( control ) {
		ideControl = document.forms[0].otherLocationOfIntegration;
			
		if( control.value == 'Targeted' )
			ideControl.disabled = false;
		else {
			ideControl.value = null;
			ideControl.disabled = true;
		}
	}
	
</SCRIPT>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0">
		<tr>
			<td class="formMessage" colspan="3">* indicates a required field</td>
		</tr>
		
		<tr>
			<td class="formTitle" height="20" colspan="6">Genomic Segment</td>				
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field3">Integration</label></td>
			<td class="formField">
				<html:form action="<%= actionName %>" focus="locationOfIntegration">	
		
				<html:radio property="locationOfIntegration" value="Random" onchange="chkIntegration(this);" /> Random 
				<html:radio property="locationOfIntegration" value="Targeted" onchange="chkIntegration(this);" /> Targeted
			</td>
		</tr>		
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Location of Integration<br>
													  (Required field when "Targeted" is selected<br>
													  for the "Transgene Integration" field)
								  </label>
			</td>
			<td class="formField">
				<html:text styleClass="formFieldSized" property="otherLocationOfIntegration" disabled="<%= booleanLocationOfIntegration %>" size="10" name="formdata"/>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>

			<td class="formLabel"><label for="field3"><b>Segment Type</b></label></td>
			<td class="formField">
			<html:select styleClass="formFieldSized" size="1" property="segmentName" onchange="chkOther( this );" >
				<html:options name="<%= Dropdowns.GENOMICSEGMENTDROP %>" />										
			</html:select>
			<br>
			-if category you are looking for is not listed, <br>select "Other" and enter the category in the text field below:
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Other Segment Type</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" property="otherSegmentName" disabled="true" size="10" name="formdata"/>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Segment Size</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" property="segmentSize" size="10" name="formdata"/>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Designator</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" property="cloneDesignator" size="10" name="formdata"/>
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Comments</label></td>
			<td class="formField">
				<html:textarea styleClass="formFieldSized" property="comments" rows="4" cols="32" name="formdata"/>
			</td>
		</tr>				

        <tr>
           <td class="formRequiredNotice" width="5">&nbsp;</td>
           <td class="formLabel"><label for="field2"><a href="http://www.informatics.jax.org/">MGI Number</a></label></td>
           <td class="formField">
				<html:text styleClass="formFieldSized" property="numberMGI" size="10" name="formdata"/>
			</td>
        </tr>	
		
		<tr>
			<td class="formTitle" height="20" colspan="6">&nbsp;</td>				
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Upload Construct Map (Image)</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" property="fileServerLocation" size="10" name="formdata"/>
			</td>			
		</tr>		

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Title of Construct<br>(Enter info only when uploading image)</label></td>
			<td class="formField">
				<html:textarea styleClass="formUnFieldSized" property="title" rows="4" cols="32" name="formdata"/>
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Description of Construct<br>(Enter info only when uploading image)</label></td>
			<td class="formField">
				<html:textarea styleClass="formFieldUnSized" property="descriptionOfConstruct"  rows="4" cols="32"  name="formdata"/>	
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
				
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <input type="hidden" name="aGenomicSegmentID" value="<%= aGenomicSegmentID %>">
				  	
				</html:form>			
			</TABLE>
			</td>
		</tr>		

	</TABLE>	
</td></tr></TABLE>

<%@ include file="/jsp/footer.jsp" %>