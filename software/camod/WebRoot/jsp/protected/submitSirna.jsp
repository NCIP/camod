<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%
/*
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2006/11/17 17:45:53  pandyas
 * Minor format change - siRNA (lower case ‘s’)
 *
 * Revision 1.4  2006/11/10 22:01:34  pandyas
 * TestTrack #465 - delete tooltops, keep tooltips for vocabulary trees and link them to vocab tree help pages
 *
 * Revision 1.3  2006/10/27 13:01:26  pandyas
 * topic="skip" allows us to remove the onclick for ToolTips while preserving it for the title of each page
 *
 * Revision 1.2  2006/10/18 18:11:47  pandyas
 * modified label for SiRNA screen
 *
 * Revision 1.1  2006/10/17 16:08:12  pandyas
 * modified during development of caMOD 2.2 - various
 *
 *
 * $Id: submitSirna.jsp,v 1.6 2007-03-22 13:51:29 pandyas Exp $
 *
 */   
%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.webapp.form.TransientInterferenceForm" %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<script language="JavaScript" src="scripts/global.js"></script>

<%
	String aConceptCode = (String) request.getParameter("aConceptCode");

	String aTransIntID = request.getParameter( "aTransIntID" );
	String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
	
	//if aTransIntID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	String actionName = "SirnaAction.do?method=save";
	
    if ( aTransIntID != null && aTransIntID.length() > 0 && isDeleted == null) {
		actionName = "SirnaAction.do?method=edit";
	}
	else {
	    aTransIntID = "";
	}
%>

<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/submitMenu.jsp" %>
<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%">
	<tr><td valign="top">
<!-- -->

	<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" align="left">
	<html:form action="<%= actionName %>" focus="source">
	<tr>
		<html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>

	</tr>
	
	<tr>
		<td class="formTitle" height="20" colspan="3">siRNA:
			<camod:cshelp topic="sirna_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
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
				<html:options name="<%= Dropdowns.SIRNASOURCEDROP %>"/>					
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
			<td class="formLabel"><label for="sirType">Type:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldUnSized" size="1" styleId="sirType" property="type" >												
				<html:options name="<%= Dropdowns.SIRNATYPEDROP %>"/>					
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
		<td class="formLabel"><label for="deliveryMethod">Transfection Method:</label></td>
		<td class="formField">		
			<br>
			(if transfection method is not listed, then please<br>select "Other" from the list and specify it below)
			<br>
			<br>			
			<html:select styleClass="formFieldSized" size="1" styleId="deliveryMethod" property="deliveryMethod"  onclick="chkOtherMethod();">												
				<html:options name="<%= Dropdowns.SIRNADELIVMETHODDROP %>"/>					
			</html:select>			
		</td>
	</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="otherDeliveryMethod">Other Transfection Method:</label></td>
			<td class="formField">					
					<html:text styleClass="formFieldSized" size="30" styleId="otherDeliveryMethod" property="otherDeliveryMethod" />			
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
				<html:options name="<%= Dropdowns.SIRNAVISUALLIGANDSDROP %>"/>					
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
				  
				
			</TABLE>
			<!-- action buttons end -->
		</td>
	</tr>
	  </html:form>
</TABLE>

<!-- -->
	</td></tr></TABLE>
</td></tr></TABLE>

<SCRIPT>
chkOtherSource();
chkOtherMethod();
chkOtherLigand();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>