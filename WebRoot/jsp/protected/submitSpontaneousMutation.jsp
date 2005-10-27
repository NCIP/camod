<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.SpontaneousMutation" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.SpontaneousMutationForm" %>	
<%@ page import="gov.nih.nci.camod.Constants.*" %>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.Constants.Dropdowns" %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>	

<%
	String aSpontaneousMutationID = request.getParameter( "aSpontaneousMutationID" );

	//if aXenograftID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "SpontaneousMutationAction.do?method=save";
	
	if ( aSpontaneousMutationID != null )
		actionName = "SpontaneousMutationAction.do?method=edit";
%>


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
		<td class="formTitle" height="20" colspan="3">Spontaneous Mutation:</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Gene Name:</label></td>
		<td class="formField">
			<html:form action="<%= actionName %>" focus="name">	
			
			<html:text styleClass="formFieldSized" property="name"  size="30" name="formdata"/>
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Observation:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="observation"  size="30" name="formdata"/>
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Method of Observation:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="methodOfObservation"  size="30" name="formdata"/>
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel">
			<label for="field2"><a href="http://www.informatics.jax.org/">MGI Number:</a></label>
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="numberMGI"  size="30" name="formdata"/>
		</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Comments Field:</label>
		<camod:cshelp key="SPONTANEOUS_MUTATION.COMMENTS" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">			
			<html:textarea styleClass="formFieldSized" property="comments"  rows="3" cols="30" name="formdata"/>
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
				  <input type="hidden" name="aSpontaneousMutationID" value="<%= aSpontaneousMutationID %>">
				  	
				</html:form>			
			</TABLE>
		</td>
	</tr>
	</TABLE>

	<!-- -->

	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>