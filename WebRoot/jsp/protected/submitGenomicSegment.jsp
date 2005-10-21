<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.GenomicSegment" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.GenomicSegmentForm" %>	
<%@ page import="gov.nih.nci.camod.Constants.*" %>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.Constants.Dropdowns" %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>

<%
	String aGenomicSegmentID = request.getParameter( "aGenomicSegmentID" );

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
			<html:errors/>
			<td class="formMessage" colspan="3">* indicates a required field</td>
		</tr>
		
		<tr>
			<td class="formTitle" height="20" colspan="6">Genomic Segment</td>				
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field3">Integration</label>
			</td>
			<td class="formField">
				<html:form action="<%= actionName %>" focus="locationOfIntegration" enctype="multipart/form-data">	
		
				<html:radio property="locationOfIntegration" value="Random" onchange="chkIntegration(this);" /> Random 
				<html:radio property="locationOfIntegration" value="Targeted" onchange="chkIntegration(this);" /> Targeted
			</td>
		</tr>		
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Location of Integration<br>
													  (Required field when "Targeted" is selected<br>
													  for the "Transgene Integration" field) </label>
			<camod:cshelp key="ENGINEERED_GENE.LOCATION_OF_INTEGRATION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
			</td>
			<td class="formField">
				<html:text styleClass="formFieldSized" property="otherLocationOfIntegration" disabled="true" size="10" name="formdata"/>
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
				<html:text styleClass="formFieldUnSized" property="segmentSize" size="20" name="formdata"/>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Designator</label>
			<camod:cshelp key="ENGINEERED_GENE.CLONE_DESIGNATOR" image="images/iconHelp.gif" text="Tool Tip Test 1" />
			</td>
			<td class="formField">
				<html:text styleClass="formFieldUnSized" property="cloneDesignator" size="20" name="formdata"/>
			</td>
		</tr>
		
		<tr>
           <td class="formRequiredNotice" width="5">&nbsp;</td>
           <td class="formLabel"><label for="field2"><a href="http://www.informatics.jax.org/">MGI Number</a></label>
           <camod:cshelp key="MUTATION_IDENTIFIER.NUMBER_MGI" image="images/iconHelp.gif" text="Tool Tip Test 1" />
           </td>
           <td class="formField">
				<html:text styleClass="formFieldUnSized" property="numberMGI" size="20" name="formdata"/>
			</td>
        </tr>	
        
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Comments</label>
			<camod:cshelp key="ENGINEERED_GENE.COMMENTS" image="images/iconHelp.gif" text="Tool Tip Test 1" />
			</td>
			<td class="formField">
				<html:textarea styleClass="formFieldSized" property="comments" rows="4" cols="32" name="formdata"/>
			</td>
		</tr>				
		
		<tr>
			<td class="formTitle" height="20" colspan="6">&nbsp;</td>				
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Upload Construct Map (Image)</label></td>
			<td class="formField">
						
			<% 
				 // Only display a thumbnail if Image exists
			     GenomicSegmentForm theGenomicSegmentForm = (GenomicSegmentForm) request.getSession().getAttribute("formdata");
					
				 if ( theGenomicSegmentForm.getFileServerLocation() != null ) {
				 	if ( ! theGenomicSegmentForm.getFileServerLocation().equals( "" ) ) {
				 	
				 		pageContext.setAttribute("fileServerLocationName", theGenomicSegmentForm.getFileServerLocation() );
			%>
						<c:set var="uri" value="javascript: rs('commentWin','viewLizardImage.do?aFileServerLocation=${fileServerLocationName}',600,600);"/>
					
						Current Image: <bean:write name="formdata" property="fileServerLocation"/><br>
						Current Image Thumbnail: <br>
							
						<a href='<c:out value="${uri}"/>'>			
						
						<img src="http://caimage.nci.nih.gov/lizardtech/iserv/getthumb?cat=Model&amp;img=<bean:write name='formdata' property='fileServerLocation'/>&amp;thumbspec=" main="" alt="<bean:write name='formdata' property='fileServerLocation'/>" target="_blank">				
						Click to View</a><br><br>									
			<% 
					} 
				} 			
			%>
						
			<html:file styleClass="formFieldSized" size="40" property="fileLocation" name="formdata"/>	
				
			</td>			
		</tr>		

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Title of Construct<br>(Enter info only when uploading image)</label></td>
			<td class="formField">
				<html:textarea styleClass="formFieldSized" property="title" rows="4" cols="32" name="formdata"/>
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Description of Construct<br>(Enter info only when uploading image)</label></td>
			<td class="formField">
				<html:textarea styleClass="formFieldSized" property="descriptionOfConstruct"  rows="4" cols="32"  name="formdata"/>	
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

<SCRIPT>
	function checkOthers()
	{
	    ideControl = document.forms[0].segmentName;
	    ideOtherControl = document.forms[0].otherSegmentName;
				
		if( ideControl.value == "Other" )
			ideOtherControl.disabled = false;
		else {
			ideOtherControl.disabled = true;
		}
		
	    ideControl = document.forms[0].locationOfIntegration;
	    ideOtherControl = document.forms[0].otherLocationOfIntegration;
			
		if( ideControl[0].checked == true )
			ideOtherControl.disabled = true;
		else {
			ideOtherControl.disabled = false;
		}
	}
	
	checkOthers();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>