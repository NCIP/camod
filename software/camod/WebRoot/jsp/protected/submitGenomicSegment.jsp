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
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/submitMenu.jsp" %>
<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%">
	<tr><td valign="top">

	<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" align="left">

	<tr>
		<html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>
		
		<tr>
			<td class="formTitle" height="20" colspan="6">Genomic Segment&nbsp;
				<camod:cshelp topic="genomic_segment_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>				
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel">Integration:
			</td>
			<td class="formField">
				<html:radio styleId="isRandom1" property="isRandom" value="yes" onclick="chkIntegration(this);" /> <label for="isRandom1">Random</label> 
				<html:radio styleId="isRandom2" property="isRandom" value="no" onclick="chkIntegration(this);" /> <label for="isRandom2">Targeted</label>  
			</td>
		</tr>		
		
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="locationOfIntegration">Location of Integration: *</label><br>(Required field when "Targeted" is selected<br>for the "Transgene Integration" field)
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" disabled="true" styleId="locationOfIntegration" property="locationOfIntegration"  size="10" />
		</td>
	</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>

			<td class="formLabel"><label for="segmentName"><b>Segment Type:</b></label></td>
			<td class="formField">
			<html:select styleClass="formFieldSized" size="1" styleId="segmentName" property="segmentName" onchange="chkSegmentName();" >
				<html:options name="<%= Dropdowns.GENOMICSEGMENTDROP %>" />										
			</html:select>
			<br>
			-if category you are looking for is not listed, <br>select "Other" and enter the category in the text field below:
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="otherSegmentName">Other Segment Type:</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" styleId="otherSegmentName" property="otherSegmentName"  size="10" />
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="segmentSize">Segment Size:</label></td>
			<td class="formField">
				<html:text styleClass="formFieldUnSized" styleId="segmentSize" property="segmentSize" size="20" />
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="cloneDesignator">Designator:</label>
			</td>
			<td class="formField">
				<html:text styleClass="formFieldUnSized" styleId="cloneDesignator" property="cloneDesignator" size="20" />
			</td>
		</tr>
		
	<tr>
		<c:if test="${modelspeciescommonname == 'Mouse'}">			
				<td class="formRequiredNotice" width="5">&nbsp;</td>
				<td class="formLabel"><label for="mgiId">MGI Identifier:</label>
				</td>
				<td class="formField">
					<input type=button value="Find MGI ID" onClick="myRef = window.open('http://www.informatics.jax.org/','mywin',
								'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
					&nbsp;&nbsp;
					<html:text styleClass="formFieldUnSized" size="25" styleId="mgiId" property="mgiId"  />&nbsp;&nbsp;<a class="sideMenuLink" href="#" onClick="myRef = window.open('html/disclaimer.html#external','mywin',
										'left=20,top=20,width=800,height=200,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()">Disclaimer</a>
				</td>
		</c:if>				
	</tr>	
	<tr>
		<c:if test="${modelspeciescommonname == 'Zebrafish'}">	
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="zfinId">ZFIN Identifier:</label>
			</td>
			<td class="formField">
				<input type=button value="Find ZFIN ID" onClick="myRef = window.open('http://zfin.org/','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
				&nbsp;&nbsp;
				<html:text styleClass="formFieldUnSized" size="25" styleId="zfinId" property="zfinId"  />
			</td>
		</c:if>
	</tr>	
	
	<tr>
		<c:if test="${modelspeciescommonname == 'Rat'}">	
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="rgdId">RGD Identifier:</label>
			</td>
			<td class="formField">
				<input type=button value="Find RGD ID" onClick="myRef = window.open('http://rgd.mcw.edu/strains/','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
				&nbsp;&nbsp;
				<html:text styleClass="formFieldUnSized" size="25" styleId="rgdId" property="rgdId"  />
			</td>
		</c:if>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="constructSequence">Construct Sequence:<br></label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" styleId="constructSequence" property="constructSequence" rows="4" cols="32"  />	
		</td>
	</tr>
		        
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="comments">Comment:</label>
			</td>
			<td class="formField">
				<html:textarea styleClass="formFieldSized" styleId="comments" property="comments" rows="4" cols="32" />
			</td>
		</tr>				
		
		<tr>
			<td class="formTitle" height="20" colspan="6">&nbsp;</td>				
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="fileLocation">Upload Construct Map<br>(Image of type .jpg, .jpeg, .gif or .png):</label></td>
			<td class="formField">
			
			<c:if test="${not empty genomicSegmentForm.url}">
				Current Image Thumbnail: <br>
					
				<a href='<c:out value="${genomicSegmentForm.imageUrl}"/>'>						
				<img src="<c:out value="${genomicSegmentForm.thumbUrl}"/>" height="40" width="40" alt="<c:out value="${genomicSegmentForm.url}"/>" target="_blank">				
				Click to View</a>
				<br><br>													
			</c:if>
						
			<html:file styleClass="formFieldSized" size="40" styleId="fileLocation" property="fileLocation" />	
				
			</td>			
		</tr>		

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="constructTitle">Title of Construct:<br>(Enter info only when uploading image)</label></td>
			<td class="formField">
				<html:textarea styleClass="formFieldSized" styleId="constructTitle" property="title" rows="4" cols="32" />
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="descriptionOfConstruct">Description of Construct:<br>(Enter info only when uploading image)</label></td>
			<td class="formField">
				<html:textarea styleClass="formFieldSized" styleId="descriptionOfConstruct" property="descriptionOfConstruct"  rows="4" cols="32"  />	
			</td>
		</tr>		

		<tr>
			<td align="right" colspan="3">
			<TABLE summary="This table is used to format page content" cellpadding="4" cellspacing="0" border="0">
			
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
				  	
			</TABLE>
		</td>
	</tr>
</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>
</html:form>

<SCRIPT>
chkSegmentName();
chkIntegration();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>