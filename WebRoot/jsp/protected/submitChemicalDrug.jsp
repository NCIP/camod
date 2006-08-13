<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page import="gov.nih.nci.camod.webapp.form.ChemicalDrugForm" %>	
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<script language="JavaScript" src="scripts/global.js"></script>

<%
	String aCarcinogenExposureID = request.getParameter( "aCarcinogenExposureID" );
    String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
    
	//if aCarcinogenExposureID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	String actionName = "ChemicalDrugAction.do?method=save";
	
	if ( aCarcinogenExposureID != null && aCarcinogenExposureID.length() > 0 && isDeleted == null) {
		actionName = "ChemicalDrugAction.do?method=edit";
	}
	else {
	    aCarcinogenExposureID = "";
	}
%>

<html:form action="<%= actionName %>" focus="name" >


<!-- submitChemicalDrug.jsp -->
<!-- Main Content Begins -->
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">
	<tr>
		<html:errors/>		
		<td class="formMessage" colspan="3">
			* indicates a required field
		</td>
	</tr>
	
	<tr>
		<td class="formTitle" height="20" colspan="3">Chemical/Drug 
		<camod:cshelp topic="chemical_drug_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Chemical/Drug:</label></td>
		<td class="formField">
			<br>
			<label for="field3">(if Chemical/Drug is not listed, then please<br>select "Other" from the list and specify it below)</label>
			<br>
			<br>
			<html:select styleClass="formFieldSized" size="1" property="name"  onclick="chkOtherName();">										
			<html:options name="<%= Dropdowns.CHEMICALDRUGDROP %>"/>						
			</html:select>	
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Chemical/Drug:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="otherName"  size="40" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Dose:</label>
		<camod:cshelp topic="chemical_drug_help" key="TREATMENT.DOSAGE" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="dosage"  size="15" />
			<label for="field1">&nbsp;Units&nbsp;</label>
			<html:select styleClass="formFieldUnSized" size="1" property="dosageUnit" >												
				<html:options name="<%= Dropdowns.CHEMTHERAPYDOSEUNITSDROP %>"/>					
			</html:select>	
		</td>
	</tr>
	
	<tr>
			<td class="formRequiredNotice" width="3">&nbsp;</td>
			<td class="formLabel"><label for="field1">NSC number:</label>
			<camod:cshelp topic="chemical_drug_help" key="ENV_FACTOR.NSC_NUMBER" image="images/iconHelp.gif" text="Tool Tip Test 1" />
			</td>
			<td class="formField">		
				<input type=button value="Find NSC #" onClick="myRef = window.open('http://dtp.nci.nih.gov/dtpstandard/chemname/index.jsp?field1=','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"></input>
				<label for="field1">&nbsp;&nbsp;</label>
				<html:text styleClass="formFieldUnSized" size="15" property="nscNumber"  />
			</td>
	</tr>
	<!-- changed link to CAS# but NSC link can get both CAS and NSC - ask Ulli?? http://dtp.nci.nih.gov/dtpstandard/chemname/index.jsp?field1=   -->
	<tr>
			<td class="formRequiredNotice" width="3">&nbsp;</td>
			<td class="formLabel"><label for="field1">CAS number:</label>
			<camod:cshelp topic="chemical_drug_help" key="ENV_FACTOR.CAS_NUMBER" image="images/iconHelp.gif" text="Tool Tip Test 1" />
			</td>
			<td class="formField">		
				<input type=button value="Find CAS #" onClick="myRef = window.open('http://chemfinder.cambridgesoft.com/','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"></input>
				<label for="field1">&nbsp;&nbsp;</label>
				<html:text styleClass="formFieldUnSized" size="15" property="casNumber"  />
			</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Administrative Route:</label>
		<camod:cshelp topic="chemical_drug_help" key="TREATMENT.ADMINISTRATIVE_ROUTE" image="images/iconHelp.gif" text="Tool Tip Test 1" />		
		</td>
		<td class="formField">
		<br>
		<label for="field3">- if Administration Route is not listed, <br>then please select "Other" and then specify it below:</label>
		<br>
		<br>
			<html:select styleClass="formFieldSized" size="1" property="administrativeRoute"  onclick="chkOtherAdminRoute();">												
				<html:options name="<%= Dropdowns.ADMINISTRATIVEROUTEDROP %>"/>					
			</html:select>	
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Administrative Route:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="otherAdministrativeRoute"  size="40"  />			
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Treatment Regimen:</label>
		<camod:cshelp topic="chemical_drug_help" key="TREATMENT.REGIMEN" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="regimen" size="40" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Age at Treatment:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="ageAtTreatment"  size="15" />
			<label for="field1">&nbsp;Units&nbsp;</label>
			<html:select styleClass="formFieldUnSized" size="1" property="ageAtTreatmentUnit" >												
				<html:options name="<%= Dropdowns.AGEUNITSDROP %>"/>					
			</html:select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Gender:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldUnSized" size="1" property="type" >												
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
  				  
  				  <c:if test="${not empty aCarcinogenExposureID}">
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
			      </c:if>
			      
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <input type="hidden" name="aCarcinogenExposureID" value="<%= aCarcinogenExposureID %>">
			
			</TABLE>
		</td>
	</tr>
</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>
</html:form>

<SCRIPT>
chkOtherName();
chkOtherAdminRoute();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>