<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page buffer="32kb"%>
<%@ page import="gov.nih.nci.camod.webapp.form.ModelCharacteristicsForm" %>	
<%@ page import='gov.nih.nci.camod.Constants.*' %>
<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<script language="JavaScript" src="scripts/global.js"></script>
<SCRIPT src="/camod/scripts/CalendarPopup.js" type=text/javascript></SCRIPT>
<SCRIPT src="/camod/scripts/RoboHelp_CSH.js" type=text/javascript></SCRIPT>

<SCRIPT LANGUAGE="JavaScript" ID="js1">
var cal1 = new CalendarPopup();
</SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
	
	function chkOtherStrain() {
		chkOther(document.forms[0].ethinicityStrain, document.forms[0].otherEthnicityStrain);
	}

	function getOptions( control ) {
		form = control.form;
		form.action = "AnimalModelPopulateAction.do?method=setStrainDropdown&page=modelChar";
		form.submit();
	}
	
	function immediateRelease()
	{
	    document.forms[0].calendarReleaseDateDisp.value = '';
	    document.forms[0].calendarReleaseDate.value = '';
	    
	    return true;
	}
	
	function selectFromCalendar()
	{
		cal1.select(document.forms[0].calendarReleaseDateDisp,
		            'calendarReleaseDateDisp',
		            'MM/dd/yyyy'); 
	    return true;
	}
	
	function transferFields() {
		document.forms[0].calendarReleaseDate.value = document.forms[0].calendarReleaseDateDisp.value;
		document.forms[0].calendarReleaseDate.disabled = false;
	}
	
	function setFields() {
		document.forms[0].calendarReleaseDateDisp.value = document.forms[0].calendarReleaseDate.value;
	}
	
</SCRIPT>

<!-- submitModelCharacteristics.jsp -->
<!-- Main Content Begins -->
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0">
		
		<tr>
			<html:errors/>
			<td class="formMessage" colspan="3">* indicates a required field</td>
	
		</tr>

		<tr>
			<td class="formTitle" height="20" colspan="3"><a>Model Characteristics</a>&nbsp;
				<camod:cshelp topic="model_characteristics_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field1">Model Descriptor:</label>
			</td>
			<td class="formField">			
					<html:form action="EditAnimalModel.do?method=edit" focus="modelDescriptor" onsubmit="transferFields()">
					<html:text styleClass="formFieldSized" property="modelDescriptor" size="30"/>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field1">Principal Investigator:</label></td>
			<td class="formField">
				<html:select styleClass="formFieldSized" size="1" property="principalInvestigator">
					<html:optionsCollection name="<%= Dropdowns.PRINCIPALINVESTIGATORDROP %>" />	
				</html:select>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Is this model a tool strain?</label>			
			</td>
			<td class="formField">
				<html:radio property="isToolStrain" value="yes" /> Yes 
				<html:radio property="isToolStrain" value="no" /> No  
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formLabel"><label for="field3"><b>Species:</b></label></td>
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="scientificName" onchange="getOptions(this);">
					<html:optionsCollection name="<%= Dropdowns.NONHUMANSPECIESDROP %>" filter="false"/>												
				</html:select>				
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formLabel"><label for="field3"><b>Strain:</b></label></td>
			<td class="formField">
				<html:select styleClass="formFieldSized" size="1" property="ethinicityStrain" onclick="chkOtherStrain();">
					<html:options name="<%= Dropdowns.STRAINDROP %>"/>		
				</html:select>
			</td>
		</tr>	

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">if other Strain:</label></td>
			<td class="formField">					
					<html:text styleClass="formFieldSized" property="otherEthnicityStrain" size="30"/>			
			</td>
		</tr>

		<c:if test="${modelspeciescommonname == 'Zebrafish'}">
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Developmental Stage:</label>				
			</td>
			<td class="formField">
				<html:select styleClass="formFieldSized" size="1" property="developmentalStage" >
					<html:options name="<%= Dropdowns.DEVELOPMENTALSTAGES %>"/>		
				</html:select>
			</td>
		</tr>
		</c:if>
				
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Genotype:</label>				
			</td>
			<td class="formField">			
					<html:text styleClass="formFieldSized" property="genotype" size="30"/>
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Nomenclature:</label>				
			</td>
			<td class="formField">			
					<html:text styleClass="formFieldSized" property="nomenclature" size="30"/>
			</td>
		</tr>				

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Experimental Design:</label>
			</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized" property="experimentDesign" cols="32" rows="4"/>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field2">Phenotype:</label>
			</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized" property="description" cols="32" rows="4"/>			
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field3">Gender:</label></td>
			<td class="formField">
				<html:select styleClass="formFieldSized" size="1" property="type" >												
					<html:options name="<%= Dropdowns.SEXDISTRIBUTIONDROP %>"/>					
				</html:select>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Breeding Notes:</label>
			</td>
			<td class="formField">
					<html:text styleClass="formFieldSized" property="breedingNotes" size="30"/>			
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Website for add. info:</label>
			</td>
			<td class="formField">
					<html:text styleClass="formFieldSized" property="url" size="30"/>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formLabel"><b>Record Release Date:</b>
			</td>
			<td class="formField">
				<html:radio property="releaseDate" value="immediately" onclick="return immediateRelease();" /> Release record immediately <br> 
				<html:radio property="releaseDate" value="after" onclick="return selectFromCalendar();" /> Release Record After:<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(Select date from pop up calender) 
				<html:hidden disabled="false" property="calendarReleaseDate" />
				<INPUT styleClass="formFieldSized2" disabled="true" property="calendarReleaseDateDisp" id="calendarReleaseDateDisp" size="10"/>	<br>
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
			<td align="right" colspan="3">
				<TABLE cellpadding="4" cellspacing="0" border="0">
				
					  <html:submit styleClass="actionButton">
						  <bean:message key="button.submit"/>
					  </html:submit>
					  
					  <html:reset styleClass="actionButton">
					  	  <bean:message key="button.reset"/>
	  				  </html:reset>
				  
				  </html:form>			
				</TABLE>
			</td>
		</tr>
		
	</TABLE>	
</td></tr></TABLE>

<SCRIPT LANGUAGE="JavaScript">
	chkOtherStrain();
	setFields();
</SCRIPT>	

<%@ include file="/jsp/footer.jsp" %>





