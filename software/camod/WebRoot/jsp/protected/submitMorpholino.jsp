<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%
/*
 * $Log: not supported by cvs2svn $
 * Revision 1.8  2007/04/04 13:25:19  pandyas
 * Modified name for conditioning regimen and target site
 *
 * Revision 1.7  2007/03/26 12:07:56  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.6  2006/11/10 22:01:34  pandyas
 * TestTrack #465 - delete tooltops, keep tooltips for vocabulary trees and link them to vocab tree help pages
 *
 * Revision 1.5  2006/10/27 13:01:26  pandyas
 * topic="skip" allows us to remove the onclick for ToolTips while preserving it for the title of each page
 *
 * Revision 1.4  2006/10/17 16:07:54  pandyas
 * modified during development of caMOD 2.2 - transientInterference changes
 *
 * Revision 1.2  2006/08/13 18:37:02  pandyas
 * updated on-line help from Robohelp to ePublisher - modified links
 *
 * Revision 1.1  2006/05/03 20:07:44  pandyas
 * Modified/Added to support Morpholino object data in the application
 *
 *
 *
 * $Id: submitMorpholino.jsp,v 1.9 2007-04-23 16:18:17 pandyas Exp $
 *
 */   
%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.webapp.form.TransientInterferenceForm" %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<!-- Needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>

<%
	String aConceptCode = (String) request.getParameter("aConceptCode");
	
	String aTransIntID = request.getParameter( "aTransIntID" );
	String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
	
	//if aTransIntID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	String actionName = "MorpholinoAction.do?method=save";
	
    if ( aTransIntID != null && aTransIntID.length() > 0 && isDeleted == null) {
		actionName = "MorpholinoAction.do?method=edit";
	}
	else {
	    aTransIntID = "";
	}
%>

<html:form action="<%= actionName %>" focus="source">

<!-- submitMorpholino.jsp -->
<!-- Main Content Begins -->
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/submitMenu.jsp" %>
<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%">
	<tr><td valign="top">
<!-- -->

	<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" align="left">
	<tr>
		<html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>

	</tr>
	
	<tr>
		<td class="formTitle" height="20" colspan="3">Morpholino:
			<camod:cshelp topic="morpholino_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="source">Source:</label></td>
		<td class="formField">		
			<br>
			(if source is not listed, then please<br>select "Other" from the list and specify it below)
			<br>
			<br>			
			<html:select styleClass="formFieldSized" size="1" styleId="source" property="source"  onclick="chkOtherSource();">												
				<html:options name="<%= Dropdowns.MORPHOSOURCEDROP %>"/>					
			</html:select>
			
		</td>
	</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="otherSource">Other Source:</label></td>
			<td class="formField">					
					<html:text styleClass="formFieldSized" size="30" styleId="otherSource" property="otherSource" />			
			</td>
		</tr>
		

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="morType">Type:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldUnSized" size="1" styleId="morType" property="type" >												
				<html:options name="<%= Dropdowns.MORPHOTYPEDROP %>"/>					
			</html:select>
		</td>
		</tr>
		
		

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="sequenceDirection">Sequence Direction:</label></td>
			<td class="formField">					
				<html:select styleClass="formFieldUnSized" size="1" styleId="sequenceDirection" property="sequenceDirection" >												
					<html:options name="<%= Dropdowns.SEQUENCEDIRECTIONSDROP %>"/>					
				</html:select>					
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="targetedRegion">Targeted Region:</label></td>
			<td class="formField">					
					<html:text styleClass="formFieldSized" size="30" styleId="targetedRegion" property="targetedRegion" />			
			</td>
		</tr>
		
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="concentration">Concentration:</label>
		</td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" styleId="concentration" property="concentration"  size="10" />
			<label for="concentrationUnit">&nbsp;Units&nbsp;</label>
			<html:select styleClass="formFieldUnSized" size="1" styleId="concentrationUnit" property="concentrationUnit" >												
				<html:options name="<%= Dropdowns.CONCENTRATIONUNITSDROP %>"/>					
			</html:select>
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="deliveryMethod">Delivery Method:</label></td>
		<td class="formField">		
			<br>
			(if delivery method is not listed, then please<br>select "Other" from the list and specify it below)
			<br>
			<br>			
			<html:select styleClass="formFieldSized" size="1" styleId="deliveryMethod" property="deliveryMethod"  onclick="chkOtherMethod();">												
				<html:options name="<%= Dropdowns.DELIVERYMETHODDROP %>"/>					
			</html:select>			
		</td>
	</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="otherDeliveryMethod">Other Delivery Method:</label></td>
			<td class="formField">					
					<html:text styleClass="formFieldSized" size="30" styleId="otherDeliveryMethod" property="otherDeliveryMethod" />			
			</td>
	</tr>
	
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="targetSite">Target Site:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldUnSized" size="1" styleId="targetSite" property="targetSite" >												
				<html:options name="<%= Dropdowns.MORPHOLINOTARGETSITE %>"/>					
			</html:select>
		</td>
		</tr>	
		
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="visualLigand">Visualization Ligands:</label></td>
		<td class="formField">		
			<br>
			(if visual ligand is not listed, then please<br>select "Other" from the list and specify it below)
			<br>
			<br>			
			<html:select styleClass="formFieldSized" size="1" styleId="visualLigand" property="visualLigand"  onclick="chkOtherLigand();">												
				<html:options name="<%= Dropdowns.VISUALLIGANDSDROP %>"/>					
			</html:select>
			
		</td>
	</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="otherVisualLigand">Other Visualization Ligands:</label></td>
			<td class="formField">					
					<html:text styleClass="formFieldSized" size="30" styleId="otherVisualLigand" property="otherVisualLigand" />			
			</td>
		</tr>
		
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="comments">Comment:</label>
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized"  styleId="comments" property="comments" cols="32" rows="4"/>			
			</td>
	</tr>		
	
	<tr>
		<td align="right" colspan="3">
			<!-- action buttons begins -->
			<TABLE summary="This table is used to format page content" cellpadding="4" cellspacing="0" border="0">
			
				  <html:submit styleClass="actionButton">
					  <bean:message key="button.submit"/>
				  </html:submit>
				  
				  <html:reset styleClass="actionButton">
				  	  <bean:message key="button.reset"/>
  				  </html:reset>
	  				  
	  			  <c:if test="${not empty aTransIntID}">
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
			      </c:if>
			      
				   <!--  Done this way since html:hidden doesn't seem to work correctly -->
					<input type="hidden" name="aTransIntID" value="<%= aTransIntID %>">
				    <input type="hidden" name="aConceptCode" value="<%= aConceptCode %>">
		  
				  </html:form>
			</TABLE>
			<!-- action buttons end -->
		</td>
	</tr>
</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<SCRIPT>
chkOtherSource();
chkOtherMethod();
chkOtherLigand();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>