<%

/**
 * 
 * $Id: submitTransplantXenograft.jsp,v 1.34 2005-12-12 17:33:37 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.33  2005/12/05 21:29:32  pandyas
 * Follow up for defect#191:  moved text above drop down boxes on page to be consistent.
 *
 * Revision 1.32  2005/12/01 20:04:07  schroedn
 * Defect #243
 *
 * Changed the way the method of observation and observation fields interact. when observation is blank 'method of observation' is greyed out and when the user types in obsercation, the other is editable
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
	function getOptions( control ) {
		form = control.form;
		form.action  = "XenograftPopulateAction.do?method=setStrainDropdown";
		form.submit();
	}
	
	function chkOtherGraft() {		
		chkOther(document.forms[0].graftType, document.forms[0].otherGraftType);
	}
	
	function chkOtherStrain() {
		chkOther(document.forms[0].hostEthinicityStrain, document.forms[0].otherHostEthinicityStrain);
	}
	
	function chkOtherAdminSite() {

		var site = document.forms[0].administrativeSite;
		var otherSite = document.forms[0].otherAdministrativeSite;
	
    	chkOther(site, otherSite);  	
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
				<html:form action="<%= actionName %>" focus="name">			 
				
				<html:text styleClass="formFieldSized" property="name"  size="30" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Donor Species:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldSized" size="1" property="hostScientificName" onchange="getOptions(this);" >
				<html:optionsCollection name="<%= Dropdowns.HOSTSPECIESDROP %>" />										
			</html:select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Donor Strain:</label></td>
		<td class="formField">
		<br>
		<label for="field3">- if strain is not listed, <br>then please select "Other" and then specify it below:</label>
		<br>
		<br>
			<html:select styleClass="formFieldSized" size="1" property="hostEthinicityStrain" onchange="chkOtherStrain()">
				<html:options name="<%= Dropdowns.STRAINDROP %>" />
			</html:select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">if other Strain:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" disabled="true" property="otherHostEthinicityStrain" size="30" />	
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Organ/Tissue:</label>&nbsp;
			<camod:cshelp mapId="histopathology_help" key="ORGAN.CONCEPT_CODE" image="images/iconHelp.gif" text="Tool Tip Test 1" />

			<a href="javascript:showTissueTree('xenograftForm', 'descendants=true;isaFlag=false;onlyLeaf=true;preferredName=true;depthLevel=6;roleType=Anatomic_Structure_is_Physical_Part_of')">
				<IMG src="images\selectUP.gif" align=middle border=0>
			</a>
		</td>
		<td class="formField">
			<html:hidden property="organTissueCode"/>
			<input type="hidden" name="organTissueName" />				
			<html:text styleClass="formFieldSized" disabled="true" property="organ" size="30"  />
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
			<html:text styleClass="formFieldSized" disabled="true" property="otherGraftType"  size="30" />	
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
				<html:text styleClass="formFieldUnSized" size="15" property="ATCCNumber"  />
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
			<html:text styleClass="formFieldSized" size="30" property="otherAdministrativeSite" disabled="true"/>			
		</td>
	</tr>	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Host Species / Strain:</label></td>
		<td class="formField">
				<c:out value="${modelspecies}"/> / <c:out value="${modelstrain}"/>
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
				  	
				</html:form>			
			</TABLE>
		</td>
	</tr>
	</TABLE>
	
	</td></tr></TABLE>
</tr></td></TABLE>

<SCRIPT LANGUAGE="JavaScript">
	chkOtherGraft();
	chkOtherStrain();
	chkOtherAdminSite();
	chkObservation();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>
