<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page import="gov.nih.nci.camod.webapp.form.ChemicalDrugForm" %>	
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.Constants.Dropdowns" %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>

<%
	String aTherapyID = request.getParameter( "aTherapyID" );
	
	//if aTherapyID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "ChemicalDrugAction.do?method=save";
	
	if ( aTherapyID != null )
		actionName = "ChemicalDrugAction.do?method=edit";
%>

<SCRIPT LANGUAGE="JavaScript">

	function chkOtherName( control ) {
		ideControl = document.forms[0].otherName;
			
		if( control.value == 'Other' )
			ideControl.disabled = false;
		else {
			ideControl.value = null;
			ideControl.disabled = true;
		}
	}
	
	function chkOtherAdminRoute( control ) {
		ideControl = document.forms[0].otherAdministrativeRoute;
		
		if( control.value == "Other" )
			ideControl.disabled = false;
		else {
			ideControl.value = null;
			ideControl.disabled = true;
		}
	}
	
</script>


<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
<!-- -->

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">
	
	<tr>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>
	
	<tr>
		<td class="formTitle" height="20" colspan="3">Chemical/Drug </td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Chemical/Drug:</label></td>
		<td class="formField">
			<br>
			<label for="field3">(if Chemical/Drug is not listed, then please<br>select "Other" from the list and specify it below)</label>
			<br>
			<br>
			<html:form action="<%= actionName %>" focus="name">			 
			
			<html:select styleClass="formFieldSized" size="1" property="name" name="formdata" onclick="chkOtherName(this);">										
				<html:options name="<%= Dropdowns.CHEMICALDRUGDROP %>"/>					
			</html:select>	
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Chemical/Drug:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="otherName"  size="30" name="formdata"/>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Dose:</label>
		<camod:cshelp key="TREATMENT.DOSAGE" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="dosage"  size="10" name="formdata"/>
			<label for="field1">&nbsp;Units&nbsp;</label>
			<html:select styleClass="formFieldUnSized" size="1" property="doseUnit" name="formdata">												
				<html:options name="<%= Dropdowns.CHEMTHERAPYDOSEUNITSDROP %>"/>					
			</html:select>	
		</td>
	</tr>
	
	<tr>
			<td class="formRequiredNotice" width="3">&nbsp;</td>
			<td class="formLabel"><label for="field1">NSC number:</label>
			<camod:cshelp key="ENV_FACTOR.NSC_NUMBER" image="images/iconHelp.gif" text="Tool Tip Test 1" />
			</td>
			<td class="formField">		
				<input type=button value="Find NSC #" onClick="myRef = window.open('http://dtp.nci.nih.gov/dtpstandard/chemname/index.jsp?field1=','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"></input>
				<html:text styleClass="formFieldUnSized" size="10" property="NSCNumber" name="formdata" />
			</td>
	</tr>
	<!-- changed linkd to CAS# but NSC link can get both CAS and NSC - ask Ulli?? http://dtp.nci.nih.gov/dtpstandard/chemname/index.jsp?field1=   -->
	<tr>
			<td class="formRequiredNotice" width="3">&nbsp;</td>
			<td class="formLabel"><label for="field1">CAS number:</label></td>
			<td class="formField">		
				<input type=button value="Find CAS #" onClick="myRef = window.open('http://chemfinder.cambridgesoft.com/','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"></input>
				<html:text styleClass="formFieldUnSized" size="10" property="CASNumber" name="formdata" />
			</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Administrative Routes:</label>
		<camod:cshelp key="TREATMENT.ADMINISTRATIVE_ROUTE" image="images/iconHelp.gif" text="Tool Tip Test 1" />		
		</td>
		<td class="formField">
		<br>
		<label for="field3">- if Administration Route is not listed, <br>then please select "Other" and then specify it below:</label>
		<br>
		<br>
			<html:select styleClass="formFieldSized" size="1" property="administrativeRoute" name="formdata" onclick="chkOtherAdminRoute(this);">												
				<html:options name="<%= Dropdowns.ADMINISTRATIVEROUTEDROP %>"/>					
			</html:select>	
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Administrative Route:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="otherAdministrativeRoute"  size="30" name="formdata" />			
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Treatment Regimen:</label>
		<camod:cshelp key="TREATMENT.REGIMEN" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="regimen" size="30" name="formdata"/>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Age:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="ageAtTreatment"  size="10" name="formdata"/>
			<label for="field1">&nbsp;Units&nbsp;</label>
			<html:select styleClass="formFieldUnSized" size="1" property="ageUnit" name="formdata">												
				<html:options name="<%= Dropdowns.AGEUNITSDROP %>"/>					
			</html:select>
		</td>
		
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Gender:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldUnSized" size="1" property="type" name="formdata">												
				<html:options name="<%= Dropdowns.SEXDISTRIBUTIONDROP %>"/>					
			</html:select>
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
				  <input type="hidden" name="aTherapyID" value="<%= aTherapyID %>">
				  	
			  </html:form>			
			</TABLE>
		</td>
	</tr>
</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<SCRIPT>
	function checkOthers()
	{
	    ideControl = document.forms[0].name;
	    ideOtherControl = document.forms[0].otherName;
			
		if( ideControl.value == 'Other' )
			ideOtherControl.disabled = false;
		else {
			ideOtherControl.disabled = true;
		}
		
	    ideControl = document.forms[0].administrativeRoute;
	    ideOtherControl = document.forms[0].otherAdministrativeRoute;
			
		if( ideControl.value == 'Other' )
			ideOtherControl.disabled = false;
		else {
			ideOtherControl.disabled = true;
		}
	}
	
	checkOthers();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>