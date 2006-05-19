<%

/**
 * 
 * $Id: submitTransplantXenograft.jsp,v 1.41 2006-05-19 16:45:00 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.40  2006/05/03 19:05:50  georgeda
 * Move to new EVSTree
 *
 * Revision 1.39  2006/04/20 19:46:23  pandyas
 * Modified host species/  host strain / otherHostStrain text on Xenograft screen
 *
 * Revision 1.38  2006/04/17 19:17:03  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.37  2006/03/31 13:47:17  georgeda
 * Changed the EVSTree call to work w/ new servers
 *
 * Revision 1.36  2006/01/17 19:12:57  pandyas
 * Defect# 378: ToolTip to Organ/Tissue links to histopathology_help instead of xenograft_transplant_help
 *
 * Revision 1.35  2005/12/21 18:00:02  pandyas
 * Modified jsp to add test for other fields - and cleaned up the javascript
 *
 * Revision 1.34  2005/12/12 17:33:37  georgeda
 * Defect #265, store host/origin species in correct places
 *
 * Revision 1.33  2005/12/05 21:29:32  pandyas
 * Follow up for defect#191:  moved text above drop down boxes on page to be consistent.
 *
 * Revision 1.32  2005/12/01 20:04:07  schroedn
 * Defect #243
 *
 * Changed the way the method of observation and observation fields interact. when observation is blank 'method of observation' is greyed out and when the user types in obsevation, the other is editable
 *
 *
 */

%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.domain.Strain" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.XenograftForm" %>	
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.Constants.Dropdowns" %>

<script language="JavaScript" src="scripts/EVSTreeScript.js"></script>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<script language="JavaScript" src="scripts/global.js"></script>

<%
	String aXenograftID = request.getParameter( "aXenograftID" );
    String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
    
	//if aXenograftID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "XenograftAction.do?method=save";
	
	if ( aXenograftID != null && aXenograftID.length() > 0 && isDeleted == null) {
		actionName = "XenograftAction.do?method=edit";
	}
	else {
	    aXenograftID = "";
	}
%>

<SCRIPT LANGUAGE="JavaScript">

	function getBothResults(control) {
	getOptions( control );	
	chkOtherSpecies();
	}
	function getOptions( control ) {
		form = control.form;
		form.action  = "XenograftPopulateAction.do?method=setStrainDropdown";
		form.submit();		
	}
	function chkOtherSpecies() {	
			chkOther(document.forms[0].donorScientificName, document.forms[0].otherDonorScientificName);
	}
	function chkOtherStrain() {
		chkOther(document.forms[0].donorEthinicityStrain, document.forms[0].otherDonorEthinicityStrain);
	}
	function chkObservation() {
	
	    geneticManipulation = document.forms[0].geneticManipulation;
	
		if( geneticManipulation.value != null && geneticManipulation.value != "" ) {
			enableField(document.forms[0].modificationDescription);
		}
		else {
			disableField(document.forms[0].modificationDescription);
		}	
	}
</SCRIPT>

<html:form action="<%= actionName %>" focus="xenograftName">

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">
	
	<tr>
		<html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>
	
	<tr>
		<td class="formTitle" height="20" colspan="3">Xenograft/Transplantation</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Cell Line/Transplant:</label>
		<camod:cshelp mapId="xenograft_transplant_help" key="ABS_CANCER_MODEL.NAME" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
				<html:text styleClass="formFieldSized" property="xenograftName"  size="30" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Donor Species:</label></td>
		<td class="formField">
		<br>
		<label for="field3">- if species is not listed, <br>then please select "Other" and then specify it below:</label>
		<br>
		<br>		
			<html:select styleClass="formFieldSized" size="1" property="donorScientificName" onchange="getBothResults(this);" >
				<html:optionsCollection name="<%= Dropdowns.SPECIESQUERYDROP %>" />										
			</html:select>
		</td>
	</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">if other Species:</label></td>
			<td class="formField">					
					<html:text styleClass="formFieldSized" property="otherDonorScientificName" size="30"/>			
			</td>
		</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Donor Strain:</label></td>
		<td class="formField">
		<br>
		<label for="field3">- if strain is not listed, <br>then please select "Other" and then specify it below:</label>
		<br>
		<br>
			<html:select styleClass="formFieldSized" size="1" property="donorEthinicityStrain" onchange="chkOtherStrain()">
				<html:options name="<%= Dropdowns.STRAINDROP %>" />
			</html:select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">if other Strain:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized"  property="otherDonorEthinicityStrain" size="30" />	
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Organ/Tissue:</label>&nbsp;
			<camod:cshelp mapId="xenograft_transplant_help" key="ORGAN.CONCEPT_CODE" image="images/iconHelp.gif" text="Tool Tip Test 1" />

			<a href="javascript:showMouseTissueTree('xenograftForm', 'organTissueCode', 'organTissueName', 'organ', true)">
				<IMG src="images\selectUP.gif" align=middle border=0>
			</a>
		</td>
		<td class="formField">
			<html:hidden property="organTissueCode"/>
			<input type="hidden" name="organTissueName" />				
			<html:text styleClass="formFieldSized" disabled="true" property="organ" size="20"  />

			 <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton">
				<bean:message key="button.clearOrgan"/>
	  		</html:submit>			
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Graft Type:</label></td>
		<td class="formField">
		<br>				
		<label for="field3">- if graft type is not listed, <br>then please select "Other" and then specify it below:</label>
		<br>
		<br>		
			<html:select styleClass="formFieldSized" size="1" property="graftType" onclick="chkOtherGraft();">
				<html:options name="<%= Dropdowns.GRAFTTYPEDROP %>" />	
			</html:select>
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">If other Graft Type:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="otherGraftType"  size="30" />	
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Parental Cell line:</label>
			<camod:cshelp mapId="xenograft_transplant_help" key="ABS_CANCER_MODEL.PARENTAL_CELL_LINE_NAME" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="parentalCellLineName"  size="30" />	
		</td>
	</tr>			
	<tr>
			<td class="formRequiredNotice" width="3">&nbsp;</td>
			<td class="formLabel"><label for="field1">ATTC number (if available):</label>
			<camod:cshelp mapId="xenograft_transplant_help" key="ABS_CANCER_MODEL.ATCC_NUMBER" image="images/iconHelp.gif" text="Tool Tip Test 1" />
			</td>
			<td class="formField">		
				<input type=button value="Find ATTC#" onClick="myRef = window.open('http://www.atcc.org/','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"></input>
				<html:text styleClass="formFieldUnSized" size="15" property="atccNumber"  />
			</td>
	</tr>		

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Genetic Alteration:</label>
		<camod:cshelp mapId="xenograft_transplant_help" key="ABS_CANCER_MODEL.GENETIC_MANIPULATION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="geneticManipulation" cols="32" rows="4" onkeypress="chkObservation();" />
		</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Method of Modification:</label>
		<camod:cshelp mapId="xenograft_transplant_help" key="ABS_CANCER_MODEL.MODIFICATION_DESCRIPTION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="modificationDescription" cols="32" rows="4" disabled="true" />
		</td>
	</tr>


    <tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Amount of Cells:</label>
			<camod:cshelp mapId="xenograft_transplant_help" key="ABS_CANCER_MODEL.CELL_AMOUNT" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">		
			<html:text styleClass="formFieldUnSized" property="cellAmount"  size="15" />
		</td>
	</tr>
	
    <tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Growth Period:</label>
			<camod:cshelp mapId="xenograft_transplant_help" key="ABS_CANCER_MODEL.GROWTH_PERIOD" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">		
			<html:text styleClass="formFieldUnSized" property="growthPeriod"  size="15" />
		</td>
	</tr>
		
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Site of Administration:</label>
		<camod:cshelp mapId="xenograft_transplant_help" key="ABS_CANCER_MODEL.ADMINISTRATIVE_SITE" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
		<br>
		<label for="field3">- if administration site is not listed, <br>then please select "Other" and then specify it below:</label>
		<br>
		<br>
			<html:select styleClass="formFieldSized" size="1" property="administrativeSite"  onclick="chkOtherAdminSite();">												
				<html:options name="<%= Dropdowns.XENOGRAFTADMINSITESDROP %>"/>					
			</html:select>			
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Site of Administration:</label></td>
		<td class="formField">					
			<html:text styleClass="formFieldSized" size="30" property="otherAdministrativeSite" />			
		</td>
	</tr>	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Host Species / Strain:</label></td>
		<td class="formField">
			<c:choose>
				<c:when test="${empty modelstrain}">
					<c:out value="${modelspecies}"/> / <c:out value="${othermodelstrain}"/>						
				</c:when>
				<c:otherwise>
					<c:out value="${modelspecies}"/> / <c:out value="${modelstrain}"/>
				</c:otherwise>
			</c:choose>		
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

			      <c:if test="${not empty aXenograftID}">
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
				  </c:if>
				  				
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <input type="hidden" name="aXenograftID" value="<%= aXenograftID %>">
							
			</TABLE>
		</td>
	</tr>
	</TABLE>
	
	</td></tr></TABLE>
</tr></td></TABLE>
</html:form>

<SCRIPT LANGUAGE="JavaScript">
	chkOtherGraft();
	chkOtherStrain();
	chkOtherAdminSite();
	chkObservation();
	chkOtherSpecies();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>
