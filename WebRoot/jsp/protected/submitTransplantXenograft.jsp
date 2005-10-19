<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.domain.Strain" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.XenograftForm" %>	
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.Constants.Dropdowns" %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>	

<%
	String aXenograftID = request.getParameter( "aXenograftID" );

	//if aXenograftID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "XenograftAction.do?method=save";
	
	if ( aXenograftID != null )
		actionName = "XenograftAction.do?method=edit";
%>

<SCRIPT LANGUAGE="JavaScript">
		
	function chkOtherGraft( control ) {
		ideControl = document.forms[0].otherGraftType;
			
		if( control.value == 'Other' )
			ideControl.disabled = false;
		else {
			ideControl.value = null;
			ideControl.disabled = true;
		}
	}
	
	function chkOtherStrain( control ) {
		ideControl = document.forms[0].otherHostEthinicityStrain;
		
		if( control.value == "Other" )
			ideControl.disabled = false;
		else {
			ideControl.value = null;
			ideControl.disabled = true;
		}
	}
	
	function getOptions( control ) {
		form = control.form;
		form.action  = "XenograftPopulateAction.do?method=setStrainDropdown";
		form.submit();
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
		<camod:cshelp key="ABS_CANCER_MODEL.PARENTAL_CELL_LINE_NAME" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
				<html:form action="<%= actionName %>" focus="name">			 
				
				<html:text styleClass="formFieldSized" property="name"  size="30" name="formdata"/>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">ATTC number (if available):</label>
			<camod:cshelp key="ABS_CANCER_MODEL.ATCC_NUMBER" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="ATCCNumber"  size="10" name="formdata"/>	
		</td>         		
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Species / Strain:</label></td>
		<td class="formField">
				<c:out value="${modelspecies}"/> / <c:out value="${modelstrain}"/>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Parental Cell line:</label>
			<camod:cshelp key="ABS_CANCER_MODEL.PARENTAL_CELL_LINE_NAME" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="parentalCellLineName"  size="30" name="formdata"/>	
		</td>
	</tr>

    <tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Amount of Cells:</label>
			<camod:cshelp key="ABS_CANCER_MODEL.CELL_AMOUNT" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">		
			<html:text styleClass="formFieldUnSized" property="cellAmount"  size="10" name="formdata"/>
		</td>
	</tr>	
	
        <tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Tumor harvest date after transplant:</label>
			<camod:cshelp key="ABS_CANCER_MODEL.HARVEST_DATE" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">		
			<html:text styleClass="formFieldUnSized" property="harvestDate"  size="15" name="formdata"/>&nbsp;(Use date format: dd/MM/yyyy)		
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Method of Modification:</label>
		<camod:cshelp key="ABS_CANCER_MODEL.MODIFICATION_DESCRIPTION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="modificationDescription"  size="30" name="formdata"/>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Genetic Alteration:</label>
		<camod:cshelp key="ABS_CANCER_MODEL.GENETIC_MANIPULATION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="geneticManipulation"  size="30" name="formdata"/>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Host Species:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldSized" size="1" property="hostScientificName" onchange="getOptions(this);" >
				<html:options name="<%= Dropdowns.SPECIESDROP %>" />										
			</html:select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Host Strain:</label></td>
		<td class="formField">
		<br>
			<html:select styleClass="formFieldSized" size="1" property="hostEthinicityStrain" onchange="chkOtherStrain(this)">
				<html:options name="<%= Dropdowns.STRAINDROP %>" />
			</html:select>
		<br>
		<br>
		- if strain is not listed then please select "Other" from the above Strain<br>selection list and then specify it below
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">if other Strain:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" disabled="true" property="otherHostEthinicityStrain" size="30" name="formdata"/>	
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Site of Administration:</label>
		<camod:cshelp key="ABS_CANCER_MODEL.ADMINISTRATIVE_SITE" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="administrativeSite"  size="30" name="formdata"/>
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Graft Type:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldSized" size="1" property="graftType" onclick="chkOtherGraft(this);">
				<html:options name="<%= Dropdowns.GRAFTTYPEDROP %>" />	
			</html:select>
		<br>
		<br>
		- if graft type is not listed then please select "Other" from the above <br>selection list and then specify it below						
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">If other Graft Type:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" disabled="true" property="otherGraftType"  size="30" name="formdata"/>	
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
				  <input type="hidden" name="aXenograftID" value="<%= aXenograftID %>">
				  	
				</html:form>			
			</TABLE>
		</td>
	</tr>
	</TABLE>
	
	</td></tr></TABLE>
</tr></td></TABLE>

<SCRIPT>
	function checkOthers()
	{
	    ideControl = document.forms[0].graftType;
	    ideOtherControl = document.forms[0].otherGraftType;
			
		if( ideControl.value == 'Other' )
			ideOtherControl.disabled = false;
		else {
			ideOtherControl.disabled = true;
		}
		
	    ideControl = document.forms[0].hostEthinicityStrain;
	    ideOtherControl = document.forms[0].otherHostEthinicityStrain;
			
		if( ideControl.value == 'Other' )
			ideOtherControl.disabled = false;
		else {
			ideOtherControl.disabled = true;
		}
	}
	
	checkOthers();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>
