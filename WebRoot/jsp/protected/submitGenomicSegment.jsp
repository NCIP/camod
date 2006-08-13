<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.GenomicSegment" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.GenomicSegmentForm" %>	
<%@ page import="gov.nih.nci.camod.Constants.*" %>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.Constants.Dropdowns" %>
<%@ page import="gov.nih.nci.camod.Constants.CaImage" %>
<%@ page import="java.util.*" %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<script language="JavaScript" src="scripts/global.js"></script>

<%	
	GenomicSegmentForm form = (GenomicSegmentForm) request.getAttribute( "genomicSegmentForm" );

	//if aGenomicSegmentID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
	
	String actionName = "GenomicSegmentAction.do?method=save";
	
	if ( form.getSegmentId() != null && form.getSegmentId().length() > 0 && isDeleted == null) {
		actionName = "GenomicSegmentAction.do?method=edit";	
	}
%>

<html:form action="<%= actionName %>" focus="locationOfIntegration" enctype="multipart/form-data">

<!-- submitGenomicSegment.jsp -->
<!-- Main Content Begins -->
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0">
	
		<tr>
			<html:errors/>
			<td class="formMessage" colspan="3">* indicates a required field</td>
		</tr>
		
		<tr>
			<td class="formTitle" height="20" colspan="6">Genomic Segment
				<camod:cshelp topic="genomic_segment_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>				
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field3">Integration:</label>
			</td>
			<td class="formField">
				<html:radio property="isRandom" value="yes" onclick="chkIntegration(this);" /> Random 
				<html:radio property="isRandom" value="no" onclick="chkIntegration(this);" /> Targeted  
			</td>
		</tr>		
		
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Location of Integration: *</label><br>(Required field when "Targeted" is selected<br>for the "Transgene Integration" field)
		<camod:cshelp topic="genomic_segment_help" key="ENGINEERED_GENE.LOCATION_OF_INTEGRATION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" disabled="true" property="locationOfIntegration"  size="10" />
		</td>
	</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>

			<td class="formLabel"><label for="field3"><b>Segment Type:</b></label></td>
			<td class="formField">
			<html:select styleClass="formFieldSized" size="1" property="segmentName" onchange="chkSegmentName();" >
				<html:options name="<%= Dropdowns.GENOMICSEGMENTDROP %>" />										
			</html:select>
			<br>
			-if category you are looking for is not listed, <br>select "Other" and enter the category in the text field below:
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Other Segment Type:</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" property="otherSegmentName"  size="10" />
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Segment Size:</label></td>
			<td class="formField">
				<html:text styleClass="formFieldUnSized" property="segmentSize" size="20" />
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field1">Designator:</label>
			<camod:cshelp topic="genomic_segment_help" key="ENGINEERED_GENE.CLONE_DESIGNATOR" image="images/iconHelp.gif" text="Tool Tip Test 1" />
			</td>
			<td class="formField">
				<html:text styleClass="formFieldUnSized" property="cloneDesignator" size="20" />
			</td>
		</tr>
		
		<tr>
           <td class="formRequiredNotice" width="5">&nbsp;</td>
           <td class="formLabel"><label for="field2">MGI number:</label>
           <camod:cshelp topic="genomic_segment_help" key="MUTATION_IDENTIFIER.NUMBER_MGI" image="images/iconHelp.gif" text="Tool Tip Test 1" />
           </td>
           <td class="formField">
				<input type=button value="Find MGI #" onClick="myRef = window.open('http://www.informatics.jax.org/','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"></input>           
				<label for="field1">&nbsp;&nbsp;</label>
				<html:text styleClass="formFieldUnSized" property="mgiNumber" size="15" />
			</td>
        </tr>	
        
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Comment:</label>
			<camod:cshelp topic="genomic_segment_help" key="ENGINEERED_GENE.COMMENTS" image="images/iconHelp.gif" text="Tool Tip Test 1" />
			</td>
			<td class="formField">
				<html:textarea styleClass="formFieldSized" property="comments" rows="4" cols="32" />
			</td>
		</tr>				
		
		<tr>
			<td class="formTitle" height="20" colspan="6">&nbsp;</td>				
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Upload Construct Map<br>(Image of type .jpg, .jpeg, .gif or .png):</label></td>
			<td class="formField">
			
			<c:if test="${not empty genomicSegmentForm.fileServerLocation}">
				Current Image Thumbnail: <br>
					
				<a href='<c:out value="${genomicSegmentForm.imageUrl}"/>'>						
				<img src="<c:out value="${genomicSegmentForm.thumbUrl}"/>" height="40" width="40" alt="<c:out value="${genomicSegmentForm.fileServerLocation}"/>" target="_blank">				
				Click to View</a>
				<br><br>													
			</c:if>
						
			<html:file styleClass="formFieldSized" size="40" property="fileLocation" />	
				
			</td>			
		</tr>		

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Title of Construct:<br>(Enter info only when uploading image)</label></td>
			<td class="formField">
				<html:textarea styleClass="formFieldSized" property="title" rows="4" cols="32" />
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Description of Construct:<br>(Enter info only when uploading image)</label></td>
			<td class="formField">
				<html:textarea styleClass="formFieldSized" property="descriptionOfConstruct"  rows="4" cols="32"  />	
			</td>
		</tr>
		
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Construct Sequence:<br>(Enter info only when uploading image)</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="constructSequence" rows="4" cols="32"  />	
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

  				  <c:if test="${not empty genomicSegmentForm.segmentId}">
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
			      </c:if>
				      
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <html:hidden property="segmentId"/>
				  	
				</html:form>			
			</TABLE>
			</td>
		</tr>		
		
	</TABLE>	
</td></tr></TABLE>

<SCRIPT>
chkSegmentName();
chkIntegration();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>