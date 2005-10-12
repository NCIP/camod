<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page buffer="32kb"%>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.ModelCharacteristicsForm" %>	
<%@ page import='gov.nih.nci.camod.Constants.*' %>
<%@ page import="java.util.List" %>

<!-- needed for tooltips -->
<SCRIPT src="/camod/scripts/CalendarPopup.js" type=text/javascript></SCRIPT>
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>


<SCRIPT LANGUAGE="JavaScript" ID="js1">
var cal1 = new CalendarPopup();
</SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
	
	function chkOther( control ) {
		ideControl = document.forms[0].ethnicityStrainUnctrlVocab;
		
		if( control.value == "Other" )
			ideControl.disabled = false;
		else {
			ideControl.value = null;
			ideControl.disabled = true;
		}
	}
	
	function getOptions( control ) {
		form = control.form;
		form.action = "AnimalModelPopulateAction.do?method=setStrainDropdown&page=newModel";
		form.submit();
	}	
	
	function immediateRelease()
	{
	    document.forms[0].calendarReleaseDateDisp.value = null;
	    document.forms[0].calendarReleaseDate.value = null;
	    
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
	}
	
</SCRIPT>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0">
		<tr>
		
			<html:errors/>		
			
			<td class="formMessage" colspan="3">
				* indicates a required field
			</td>
		</tr>

		<tr>
			<td class="formTitle" height="20" colspan="3">Model Characteristics</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field1">Model Descriptor </label> 
				<camod:cshelp key="ABS_CANCER_MODEL.MODEL_DESCRIPTOR" image="images/iconHelp.gif" text="Tool Tip Test 1" />
			</td>
			<td class="formField">			
				<html:form action="AnimalModelAction.do?method=save" focus="modelDescriptor" onsubmit="transferFields()">
				<html:text styleClass="formFieldSized" property="modelDescriptor" size="30"/>
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field1">PI's Email Address</label></td>
			<td class="formField">					
				<html:text styleClass="formFieldSized" property="email" size="30"/>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Is this model a toolmouse?</label>			
				<camod:cshelp key="ABS_CANCER_MODEL.IS_TOOL_MOUSE" image="images/iconHelp.gif" text="Tool Tip Test 1" />
			</td>
			<td class="formField">
				<html:radio property="isToolMouse" value="yes" /> Yes 
				<html:radio property="isToolMouse" value="no" /> No  
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formLabel"><label for="field3"><b>Species</b></label></td>
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="scientificName" onchange="getOptions(this);" >
					<html:options name="<%= Dropdowns.SPECIESDROP %>" />										
				</html:select>			
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formLabel"><label for="field3"><b>Strain</b></label></td>
			<td class="formField">
				<html:select styleClass="formFieldSized" size="1" property="ethinicityStrain" onclick="chkOther(this);">
					<html:options name="<%= Dropdowns.STRAINDROP %>" />	
				</html:select>
			</td>
		</tr>	

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">if other Strain</label></td>
			<td class="formField">					
				<html:text styleClass="formFieldSized" property="ethnicityStrainUnctrlVocab" disabled="true" size="30"/>			
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Experimental Design</label>
				<camod:cshelp key="ABS_CANCER_MODEL.EXPERIMENT_DESIGN" image="images/iconHelp.gif" text="Tool Tip Test 1" />
			</td>
			<td class="formField">
				<html:textarea styleClass="formFieldSized" property="experimentDesign" cols="32" rows="4"/>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formLabel"><label for="field2"><b>Phenotype</b></label></td>
			<td class="formField">
				<html:textarea styleClass="formFieldSized" property="description" cols="32" rows="4"/>			
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formLabel"><label for="field3"><b>Gender</b></label></td>
			<td class="formField">
				<html:select styleClass="formFieldSized" size="1" property="type">												
					<html:options name="<%= Dropdowns.SEXDISTRIBUTIONDROP %>"/>					
				</html:select>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Breeding Notes</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" property="breedingNotes" size="30"/>			
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" widh="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Website for add. info</label>
				<camod:cshelp key="ABS_CANCER_MODEL.URL" image="images/iconHelp.gif" text="Tool Tip Test 1" />
			</td>
			<td class="formField">
				<html:text styleClass="formFieldSized" property="url" size="30"/>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formLabel"><b>Record Release Date</b></td>
			<td class="formField">
				<html:radio property="releaseDate" value="immediately" onclick="return immediateRelease();" /> Release record immediately <br> 
				<html:radio property="releaseDate" value="after" onclick="return selectFromCalendar();" /> Release Record After:<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(Select date from pop up calender) 
				<html:hidden styleClass="formFieldSized2" disabled="false" property="calendarReleaseDate" />
				<INPUT styleClass="formFieldSized2" disabled="true" property="calendarReleaseDateDisp" id="calendarReleaseDateDisp" size="10"/>	<br>
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

<%@ include file="/jsp/footer.jsp" %>





