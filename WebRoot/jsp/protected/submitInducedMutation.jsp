<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.InducedMutation" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.InducedMutationForm" %>	
<%@ page import="gov.nih.nci.camod.Constants.*" %>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.Constants.Dropdowns" %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>

<%
	String aInducedMutationID = request.getParameter( "aInducedMutationID" );

	//if aInducedMutationID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "InducedMutationAction.do?method=save";
	
	if ( aInducedMutationID != null )
		actionName = "InducedMutationAction.do?method=edit";
%>

<SCRIPT LANGUAGE="JavaScript">
		
	function chkOther( control ) {
		ideControl = document.forms[0].otherType;
			
		if( control.value == 'Other' )
			ideControl.disabled = false;
		else {
			ideControl.value = null;
			ideControl.disabled = true;
		}
	}
	
</SCRIPT>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
<!-- -->

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">
	
	<tr>
		<html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>
	
	<tr>
		<td class="formTitle" height="20" colspan="3">Induced Mutation:</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Name of Inducing Agent:</label></td>
		<td class="formField">					
			<html:form action="<%= actionName %>" focus="name">	
				
			<html:text styleClass="formFieldSized" property="name" size="10" name="formdata"/>		
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel">Inducing Agent Category:</td>
		<td class="formField">
			<html:select styleClass="formFieldSized" size="1" property="type" onchange="chkOther( this );" >
				<html:options name="<%= Dropdowns.INDUCEDMUTATIONDROP %>" />										
			</html:select>
			<br>
			-if category you are looking for is not listed, <br>select "Other" and enter the category in the text field below:
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Category:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="otherType" disabled="true" size="30" name="formdata"/>		
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">CAS number:</label>
		<camod:cshelp key="ENV_FACTOR.CAS_NUMBER" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="CASNumber" size="20" name="formdata"/>		
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Gene ID ( Entrez ):</label>
		<camod:cshelp key="ENGINEERED_GENE.GENE_ID" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="geneId" size="20" name="formdata"/>		
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">MGI Number:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="numberMGI" size="20" name="formdata"/>		
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Observation:</label>
		<camod:cshelp key="GENETIC_ALTERATION.OBSERVATION_INDUCED_MUTATION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="observation" rows="4" cols="32"  name="formdata"/>		
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Method of Observation:</label>
		<camod:cshelp key="GENETIC_ALTERATION.METHOD_OF_OBSERVATION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="methodOfObservation" rows="4" cols="32"  name="formdata"/>		
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Description:</label></td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="description"  rows="4" cols="32"  name="formdata"/>		
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
				  <input type="hidden" name="aInducedMutationID" value="<%= aInducedMutationID %>">
				  	
				</html:form>			
			</TABLE>
		</td>
	</tr>
	</TABLE>

	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>