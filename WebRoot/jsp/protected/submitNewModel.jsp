<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page buffer="32kb"%>

<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.Constants" %>
<%@ page import="java.util.List" %>

<SCRIPT LANGUAGE="JavaScript">
	var cal = new CalendarPopup();

	function chkOther( control ) {
		ideControl = document.ModelCharacteristicsForm.otherEthinicityStrain;
			
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
			<td class="formTitle" height="20" colspan="3">General Information</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field1">Model Descriptor</label></td>
			<td class="formField">			
					<html:form action="AnimalModelAction.do?method=save" focus="modelDescriptor">
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
			<td class="formLabel"><label for="field1">Is this model a toolmouse?</label></td>
			<td class="formField">
				<html:radio property="isToolMouse" value="yes" /> Yes 
				<html:radio property="isToolMouse" value="no" /> No  
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formLabel"><label for="field3"><b>Species</b></label></td>
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="scientificName">
					<html:option value="">&nbsp;</html:option> 
					<html:option value="Mouse(Mus musculus)">Mouse(Mus musculus)</html:option>
					<html:option value="Rat(Rattus norvegicus)">Rat(Rattus norvegicus)</html:option>
					<html:option value="Rat(Rattus rattus)">Rat(Rattus rattus)</html:option> 
					<html:option value="Mongolian gerbil (Meriones unguiculatus)">Mongolian gerbil (Meriones unguiculatus)</html:option>
					<html:option value="Syrian (golden) hamster (Mesocricetus auratus)">Syrian (golden) hamster (Mesocricetus auratus)</html:option>
					<html:option value="Guinea pig (Cavia porcellus)">Guinea pig (Cavia porcellus)</html:option> 
					<html:option value="Mouse(in vivo)">Mouse(in vivo)</html:option>										
				</html:select>				
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formLabel"><label for="field3"><b>Strain</b></label></td>
			<td class="formField">
				<html:select styleClass="formFieldSized" size="1" property="ethinicityStrain" onclick="chkOther(this);">
					<html:option value="1">1</html:option> 
					<html:option value="2">2</html:option>
					<html:option value="Other">Other</html:option>
				</html:select>
			</td>
		</tr>	

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">if other Strain</label></td>
			<td class="formField">					
					<html:text styleClass="formFieldSized" property="otherEthinicityStrain" disabled="true" size="30"/>			
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Experimental Design</label></td>
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
					<html:option value="male">Male</html:option> 
					<html:option value="female">Female</html:option>
					<html:option value="female">Both Sexes</html:option>
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
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Website for add. info</label></td>
			<td class="formField">
					<html:text styleClass="formFieldSized" property="url" size="30"/>
			</td>
		</tr>

		<tr>
			
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formLabel"><b>Record Release Date</b></td>
			<td class="formField">
				<html:radio property="releaseDate" value="immediately" /> Release record immediately <br> 
				<html:radio property="releaseDate" value="after" /> Release Record After:<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(Select date from pop up calender) 
				<html:text styleClass="formFieldSized2" disabled="true" property="calendarReleaseDate" size="10"/>	<br>
		
				<A HREF="#"
					   onClick="goCal();"
 					   NAME="anchor1" ID="anchor1">select</A>
		
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





