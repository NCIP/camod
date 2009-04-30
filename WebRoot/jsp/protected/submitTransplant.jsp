<%

/**
 * 
 * $Id: submitTransplant.jsp,v 1.2 2008-01-23 22:26:25 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2008/01/16 18:34:35  pandyas
 * Renamed value to Transplant for #8290
 *
 * Revision 1.1  2007/10/31 19:25:33  pandyas
 * Fixed #8290 	Rename graft object into transplant object
 *
 * Revision 1.2  2007/08/22 13:59:50  pandyas
 * bug #8363: Problems with "other" filed on updated graft page on dev - filed writable when user selects a value for various dropdowns
 *
 * Revision 1.1  2007/08/07 19:36:47  pandyas
 * Removed reference to Transplant as per VCDE comments and after modification to object definition for CDE
 *
 * Revision 1.1  2007/07/31 12:00:10  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.55  2007/06/18 16:12:14  pandyas
 * Fixedmethod name for Zebrafish tree and made text entry enable
 *
 * Revision 1.54  2007/05/18 15:35:31  pandyas
 * Modified species and organ tree to default to no organ button and to work when user goes from species to empty selection
 *
 * Revision 1.53  2007/05/17 19:10:45  pandyas
 * Modified for organ tree code - needs to populate select button correctly still
 *
 * Revision 1.52  2007/05/17 17:58:50  pandyas
 * Consolidated all the clear field scripts to reuse generically on all vocab tree screens where tree value in not a required field
 *
 * Revision 1.51  2007/05/17 12:24:17  pandyas
 * Modified screen to display EVSTree vacabulary
 *
 * Revision 1.50  2007/05/10 02:19:32  pandyas
 * Implemented species specific vocabulary trees from EVSTree
 *
 * Revision 1.49  2007/04/25 15:05:51  pandyas
 * Agreed on one help icon for all title bars and one icon for light grey tool tip - removed all others
 *
 * Revision 1.48  2007/04/04 13:25:19  pandyas
 * Modified name for conditioning regimen and target site
 *
 * Revision 1.47  2007/03/26 12:08:01  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.46  2006/11/10 22:01:34  pandyas
 * TestTrack #465 - delete tooltops, keep tooltips for vocabulary trees and link them to vocab tree help pages
 *
 * Revision 1.45  2006/10/27 13:01:26  pandyas
 * topic="skip" allows us to remove the onclick for ToolTips while preserving it for the title of each page
 *
 * Revision 1.44  2006/08/13 18:29:32  pandyas
 * updated on-line help from Robohelp to ePublisher - modified links
 *
 * Revision 1.43  2006/05/24 16:37:50  georgeda
 * Slight cleanup of clear button
 *
 * Revision 1.42  2006/05/19 18:50:28  pandyas
 * defect #225 - Add clearOrgan functionality to Xenograft screen
 *
 * Revision 1.41  2006/05/19 16:45:00  pandyas
 * Defect #249 - add other to species on the Xenograft screen, needed to add javascript code and field
 *
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
<%@ page import="gov.nih.nci.camod.webapp.form.TransplantForm" %>	
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.Constants.Dropdowns" %>

<script language="JavaScript" src="scripts/EVSTreeScript.js"></script>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<script language="JavaScript" src="scripts/global.js"></script>

<%
	String aTransplantID = request.getParameter( "aTransplantID" );
    String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
    
	//if aTransplantID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "TransplantAction.do?method=save";
	
	if ( aTransplantID != null && aTransplantID.length() > 0 && isDeleted == null) {
		actionName = "TransplantAction.do?method=edit";
	}
	else {
	    aTransplantID = "";
	}
%>

<SCRIPT LANGUAGE="JavaScript">
	function getResults(control) {
		getOptions( control );
		chkOtherSpecies();
	}
	function getOptions( control ) {
		form = control.form;
		form.action  = "TransplantPopulateAction.do?method=setStrainOrganValues";
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

<html:form action="<%= actionName %>" focus="name">

<!-- submitTransplant.jsp -->
<!-- Main Content Begins -->
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
		<td class="formTitle" height="20" colspan="3">Transplant
			<camod:cshelp topic="xenograft_transplant_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Name of Transplant:</label>
		</td>
		<td class="formField">
				<html:text styleClass="formFieldSized" property="name"  size="30" />
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
			<html:select styleClass="formFieldSized" size="1" property="donorScientificName" onchange="getResults(this);" >
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
 			<!-- Display anatomy tree based on animal model species or allow for text entry if no specific tree exists -->
 			<c:choose>
				<c:when test="${donorspeciescommonname == 'Mouse'}">
				<td class="formLabel"><label for="field1">Organ/Tissue:</label>&nbsp;
				<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />							
					<a href="javascript:showMouseTissueTree('transplantForm', 'organTissueCode', 'organTissueName', 'organ', true)">
						<IMG src="images\selectUP.gif" align=middle border=0></a>
				</td>				
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">					
						<html:text styleClass="formFieldSized" disabled="true" property="organ" size="20" />
						<a href="javascript: clearField(document.forms[0].organ, document.forms[0].organTissueCode);"><img border="0" align=middle src="/camod/images/clear.gif"></a>						
					</td>
				</c:when>
				<c:when test="${donorspeciescommonname == 'Rat'}">	
				<td class="formLabel"><label for="field1">Organ/Tissue:</label>&nbsp;
				<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />				
					<a href="javascript:showRatTissueTree('transplantForm', 'organTissueCode', 'organTissueName', 'organ', true)">
						<IMG src="images\selectUP.gif" align=middle border=0></a>
				</td>
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">										
						<html:text styleClass="formFieldSized" disabled="true" property="organ" size="20"/>
						<a href="javascript: clearField(document.forms[0].organ, document.forms[0].organTissueCode);"><img border="0" align=middle src="/camod/images/clear.gif"></a>						
					</td>
				</c:when>	
				<c:when test="${donorspeciescommonname == 'Zebrafish'}">
				<td class="formLabel"><label for="field1">Organ/Tissue:</label>&nbsp;
				<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />				
					<a href="javascript:showZebrafishTissueTree('transplantForm', 'organTissueCode', 'organTissueName', 'organ', true)">
						<IMG src="images\selectUP.gif" align=middle border=0></a>
				</td>
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">										
						<html:text styleClass="formFieldSized" disabled="true" property="organ" size="20" />
						<a href="javascript: clearField(document.forms[0].organ, document.forms[0].organTissueCode);"><img border="0" align=middle src="/camod/images/clear.gif"></a>						
					</td>
				</c:when>
				<c:when test="${donorspeciescommonname == 'Human'}">
				<td class="formLabel"><label for="field1">Organ/Tissue:</label>&nbsp;
				<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />				
					<a href="javascript:showHumanTissueTree('transplantForm', 'organTissueCode', 'organTissueName', 'organ', true)">
						<IMG src="images\selectUP.gif" align=middle border=0></a>
				</td>
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">										
						<html:text styleClass="formFieldSized" disabled="true" property="organ" size="20"  />
						<a href="javascript: clearField(document.forms[0].organ, document.forms[0].organTissueCode);"><img border="0" align=middle src="/camod/images/clear.gif"></a>						
					</td>
				</c:when>				
				<c:otherwise>
				<td class="formLabel"><label for="field1">Organ/Tissue:</label>&nbsp;
				</td>
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">										
						<html:text styleClass="formFieldSized" disabled="false" property="organ" size="20"  />
					</td>				
				</c:otherwise>				
    		</c:choose>
	</tr>	


	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Source Type:</label></td>
		<td class="formField">
		<br>				
		<label for="field3">- if transplant type is not listed, <br>then please select "Other" and then specify it below:</label>
		<br>
		<br>		
			<html:select styleClass="formFieldSized" size="1" property="sourceType" onclick="chkOtherSourceType(this);">
				<html:options name="<%= Dropdowns.SOURCETYPEDROP %>" />	
			</html:select>
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">If other Source Type:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="otherSourceType"  size="30" />	
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Parental Cell line:</label>
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="parentalCellLineName"  size="30" />	
		</td>
	</tr>			
	<tr>
			<td class="formRequiredNotice" width="3">&nbsp;</td>
			<td class="formLabel"><label for="field1">ATTC number (if available):</label>
			</td>
			<td class="formField">		
				<input type=button value="Find ATTC#" onClick="myRef = window.open('http://www.atcc.org/','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
				<html:text styleClass="formFieldUnSized" size="15" property="atccNumber"  />
			</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Conditioning Regimen:</label></td>
		<td class="formField">
		<br>
		<label for="field1">- if conditioning regime is not listed, <br>then please select "Other" and then specify it below:</label>
		<br>
		<br>
			<html:select styleClass="formFieldSized" size="1" property="conditioningRegimen" onchange="chkOtherCondRegimen()">
				<html:options name="<%= Dropdowns.CONDITIONINGREGIMEN %>" />
			</html:select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">if other Conditioning Regimen:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized"  property="otherConditioningRegimen" size="30" />	
		</td>
	</tr>		

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Genetic Alteration:</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="geneticManipulation" cols="32" rows="4" onkeypress="chkObservation();" />
		</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Method of Modification:</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="modificationDescription" cols="32" rows="4" disabled="true" />
		</td>
	</tr>


    <tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Amount of Cells:</label>
		</td>
		<td class="formField">		
			<html:text styleClass="formFieldUnSized" property="cellAmount"  size="15" />
		</td>
	</tr>
	
    <tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Growth Period:</label>
		</td>
		<td class="formField">		
			<html:text styleClass="formFieldUnSized" property="growthPeriod"  size="15" />
		</td>
	</tr>
		
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Site of Administration:</label>
		</td>
		<td class="formField">
		<br>
		<label for="field3">- if administration site is not listed, <br>then please select "Other" and then specify it below:</label>
		<br>
		<br>
			<html:select styleClass="formFieldSized" size="1" property="administrativeSite"  onclick="chkOtherAdminSite();">												
				<html:options name="<%= Dropdowns.TRANSPLANTADMINSITESDROP %>"/>					
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
		<td class="formLabel"><label for="field1">Comment:</label>
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized" property="comments" cols="32" rows="4"/>			
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

			      <c:if test="${not empty aTransplantID}">
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
				  </c:if>
				  				
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <input type="hidden" name="aTransplantID" value="<%= aTransplantID %>">
							
			</TABLE>
		</td>
	</tr>
	</TABLE>
	
	</td></tr></TABLE>
</tr></td></TABLE>
</html:form>

<SCRIPT LANGUAGE="JavaScript">
	chkOtherSourceType();
	chkOtherStrain();
	chkOtherAdminSite();
	chkObservation();
	chkOtherSpecies();
	chkOtherCondRegimen();
	getOptions();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>
