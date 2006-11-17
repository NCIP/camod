<%
/*
 * $Log: not supported by cvs2svn $
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
 * $Id: submitSirna.jsp,v 1.5 2006-11-17 17:45:53 pandyas Exp $
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

<html:form action="<%= actionName %>" focus="source">

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
		<td class="formTitle" height="20" colspan="3">siRNA:
			<camod:cshelp topic="transient_interference_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Source:</label></td>
		<td class="formField">		
			<br>
			<label for="field3">(if source is not listed, then please<br>select "Other" from the list and specify it below)</label>
			<br>
			<br>			
			<html:select styleClass="formFieldSized" size="1" property="source"  onclick="chkOtherSource();">												
				<html:options name="<%= Dropdowns.SIRNASOURCEDROP %>"/>					
			</html:select>
			
		</td>
	</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Other Source:</label></td>
			<td class="formField">					
					<html:text styleClass="formFieldSized" size="30" property="otherSource" />			
			</td>
		</tr>
		

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Type:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldUnSized" size="1" property="type" >												
				<html:options name="<%= Dropdowns.SIRNATYPEDROP %>"/>					
			</html:select>
		</td>
		</tr>
		
		

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Sequence Direction:</label></td>
			<td class="formField">					
				<html:select styleClass="formFieldUnSized" size="1" property="sequenceDirection" >												
					<html:options name="<%= Dropdowns.SEQUENCEDIRECTIONSDROP %>"/>					
				</html:select>					
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field1">Targeted Region:</label></td>
			<td class="formField">					
					<html:text styleClass="formFieldSized" size="30" property="targetedRegion" />			
			</td>
		</tr>

		
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Concentration:</label>
		</td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="concentration"  size="10" />
			<label for="field1">&nbsp;Units&nbsp;</label>
			<html:select styleClass="formFieldUnSized" size="1" property="concentrationUnit" >												
				<html:options name="<%= Dropdowns.CONCENTRATIONUNITSDROP %>"/>					
			</html:select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Transfection Method:</label></td>
		<td class="formField">		
			<br>
			<label for="field3">(if transfection method is not listed, then please<br>select "Other" from the list and specify it below)</label>
			<br>
			<br>			
			<html:select styleClass="formFieldSized" size="1" property="deliveryMethod"  onclick="chkOtherMethod();">												
				<html:options name="<%= Dropdowns.SIRNADELIVMETHODDROP %>"/>					
			</html:select>			
		</td>
	</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Other Delivery Method:</label></td>
			<td class="formField">					
					<html:text styleClass="formFieldSized" size="30" property="otherDeliveryMethod" />			
			</td>
		</tr>
		
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Visualization Ligands:</label></td>
		<td class="formField">		
			<br>
			<label for="field3">(if visual ligand is not listed, then please<br>select "Other" from the list and specify it below)</label>
			<br>
			<br>			
			<html:select styleClass="formFieldSized" size="1" property="visualLigand"  onclick="chkOtherLigand();">												
				<html:options name="<%= Dropdowns.SIRNAVISUALLIGANDSDROP %>"/>					
			</html:select>
			
		</td>
	</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Other Visualization Ligands:</label></td>
			<td class="formField">					
					<html:text styleClass="formFieldSized" size="30" property="otherVisualLigand" />			
			</td>
		</tr>
		
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Comment:</label>
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized"  property="comments" cols="32" rows="4"/>			
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