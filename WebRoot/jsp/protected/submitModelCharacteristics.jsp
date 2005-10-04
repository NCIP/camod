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
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>	

<SCRIPT LANGUAGE="JavaScript">
	var cal = new CalendarPopup();

	function chkOther( control ) {
		ideControl = document.forms[0].ethnicityStrainUnctrlVocab;
			
		if( control.value == 'Other' )
			ideControl.disabled = false;
		else {
			ideControl.value = null;
			ideControl.disabled = true;
		}
	}
	
	function goCal() {
		cal.select(document.ModelCharacteristicsForm.calendarReleaseDate,'anchor1','MM/dd/yyyy'); 
		return false;
	}
	
	function getOptions( control ) {
		form = control.form;
		form.action = "AnimalModelPopulateAction.do?method=setStrainDropdown&page=modelChar";
		form.submit();
	}	
	
</SCRIPT>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0">
		<tr>
			<td class="formMessage" colspan="3">
			
		
				<logic:messagesPresent>
				  <ul>
				    <font color="red">
				      <html:messages id="error">
				        <li><%=error %></li>
				      </html:messages>
				    </font>
				  </ul>
				</logic:messagesPresent>
			 
				* indicates a required field
			</td>
		</tr>

		<tr>
			<td class="formTitle" height="20" colspan="3">General Information</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field1">Model Descriptor</label>
				<camod:cshelp key="ABS_CANCER_MODEL.MODEL_DESCRIPTOR" image="images/iconHelp.gif" text="Tool Tip Test 1" />
			</td>
			<td class="formField">			
					<html:form action="EditAnimalModel.do?method=edit" focus="modelDescriptor">
					<html:text styleClass="formFieldSized" property="modelDescriptor" name="formdata" size="30"/>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field1">PI's Email Address</label></td>
			<td class="formField">					
					<html:text styleClass="formFieldSized" property="email" name="formdata" size="30"/>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Is this model a toolmouse?</label>
				<camod:cshelp key="ABS_CANCER_MODEL.IS_TOOL_MOUSE" image="images/iconHelp.gif" text="Tool Tip Test 1" />			
			</td>
			<td class="formField">
				<html:radio property="isToolMouse" value="yes" name="formdata"/> Yes 
				<html:radio property="isToolMouse" value="no" name="formdata"/> No  
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formLabel"><label for="field3"><b>Species</b></label></td>
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="scientificName" name="formdata" onchange="getOptions(this);" >
					<html:options name="<%= Dropdowns.SPECIESDROP %>" />										
				</html:select>				
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formLabel"><label for="field3"><b>Strain</b></label></td>
			<td class="formField">
				<html:select styleClass="formFieldSized" size="1" property="ethinicityStrain" name="formdata" onclick="chkOther(this);">
					<html:options name="<%= Dropdowns.STRAINDROP %>" />	
				</html:select>
			</td>
		</tr>	

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">if other Strain</label></td>
			<td class="formField">					
					<html:text styleClass="formFieldSized" property="ethnicityStrainUnctrlVocab" name="formdata" size="30"/>			
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Experimental Design</label>
					<camod:cshelp key="ABS_CANCER_MODEL.EXPERIMENT_DESIGN" image="images/iconHelp.gif" text="Tool Tip Test 1" />
			</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized" property="experimentDesign" name="formdata" cols="32" rows="4"/>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formLabel"><label for="field2"><b>Phenotype</b></label></td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized" property="description" name="formdata" cols="32" rows="4"/>			
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formLabel"><label for="field3"><b>Gender</b></label></td>
			<td class="formField">
				<html:select styleClass="formFieldSized" size="1" property="type" name="formdata">												
					<html:options name="<%= Dropdowns.SEXDISTRIBUTIONDROP %>"/>					
				</html:select>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Breeding Notes</label></td>
			<td class="formField">
					<html:text styleClass="formFieldSized" property="breedingNotes" name="formdata" size="30"/>			
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Website for add. info</label>
					<camod:cshelp key="ABS_CANCER_MODEL.URL" image="images/iconHelp.gif" text="Tool Tip Test 1" />
			</td>
			<td class="formField">
					<html:text styleClass="formFieldSized" property="url" name="formdata" size="30"/>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formLabel"><b>Record Release Date</b></td>
			<td class="formField">
				<html:radio property="releaseDate" value="immediately" /> Release record immediately <br> 
				<html:radio property="releaseDate" onclick="openCalendar()" value="after" /> Release Record After:<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(Select date from pop up calender) 
				<html:text  styleClass="formFieldSized2" disabled="true" property="calendarReleaseDate" size="10"/>	
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





