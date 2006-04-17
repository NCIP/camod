<%

/**
 * 
 * $Id: submitInducedMutation.jsp,v 1.27 2006-04-17 19:07:33 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.26  2005/12/27 20:12:00  pandyas
 * Moved html:form action, cleaned up script, removed disabled=true
 *
 * Revision 1.25  2005/12/12 17:54:56  georgeda
 * Defect #268, added a return in front of all the confirms.
 *
 * Revision 1.24  2005/12/01 20:05:13  schroedn
 * Added CVS log statement to top of file
 *
 *
 */

%>

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
<script language="JavaScript" src="scripts/global.js"></script>

<%
	String aInducedMutationID = request.getParameter("aInducedMutationID");
    String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
    
	//if aInducedMutationID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "InducedMutationAction.do?method=save";
	
	if ( aInducedMutationID != null && aInducedMutationID.length() > 0 && isDeleted == null) {
		actionName = "InducedMutationAction.do?method=edit";
	}
	else {
	    aInducedMutationID = "";
	}
%>

<html:form action="<%= actionName %>" focus="name">	

<SCRIPT LANGUAGE="JavaScript">
		
	function chkInducingAgent() {
	    chkOther(document.forms[0].type, document.forms[0].otherType);
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
		<td class="formTitle" height="20" colspan="3">Induced Mutation</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Name of Inducing Agent:</label></td>
		<td class="formField">					
			<html:text styleClass="formFieldSized" property="name" size="10" />		
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel">Inducing Agent Category:</td>
		<td class="formField">
			<html:select styleClass="formFieldSized" size="1" property="type" onchange="chkInducingAgent();" >
				<html:optionsCollection name="<%= Dropdowns.INDUCEDMUTATIONDROP %>" />										
			</html:select>
			<br>
			-if category you are looking for is not listed, <br>select "Other" and enter the category in the text field below:
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Category:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="otherType"  size="30" />		
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">CAS number:</label>
		<camod:cshelp mapId="induced_mutation_help" key="ENV_FACTOR.CAS_NUMBER" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<input type=button value="Find CAS #" onClick="myRef = window.open('http://chemfinder.cambridgesoft.com/','mywin',
			'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"></input>		
			<label for="field1">&nbsp;&nbsp;</label>
			<html:text styleClass="formFieldUnSized" property="casNumber" size="15" />		
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Gene ID ( Entrez ):</label>
		<camod:cshelp mapId="induced_mutation_help" key="ENGINEERED_GENE.GENE_ID" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
		<input type=button value="Find Gene ID" onClick="myRef = window.open('http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?db=gene','mywin',
		'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"></input>		
		<label for="field1">&nbsp;&nbsp;</label>
		<html:text styleClass="formFieldUnSized" property="geneId" size="15" />		
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Description:</label></td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="description"  rows="4" cols="32"  />		
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Observation:</label>
		<camod:cshelp mapId="induced_mutation_help" key="GENETIC_ALTERATION.OBSERVATION_INDUCED_MUTATION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="observation" rows="4" cols="32" onkeypress="chkObservation();"/>		
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Method of Observation:</label>
		<camod:cshelp mapId="induced_mutation_help" key="GENETIC_ALTERATION.METHOD_OF_OBSERVATION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="methodOfObservation" rows="4" cols="32"  />		
		</td>
	</tr>
		
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">MGI number:</label></td>
		<td class="formField">
			<input type=button value="Find MGI #" onClick="myRef = window.open('http://www.informatics.jax.org/','mywin',
			'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"></input>		
			<html:text styleClass="formFieldUnSized" property="mgiNumber" size="20" />		
		</td>
	</tr>
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Comment:</label>
			<camod:cshelp mapId="induced_mutation_help" key="ENGINEERED_GENE.COMMENTS" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="comments"  rows="4" cols="32"  />	
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
				
				  <c:if test="${not empty aInducedMutationID}">
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
			      </c:if>
			      
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <input type="hidden" name="aInducedMutationID" value="<%= aInducedMutationID %>">
				  	
				</html:form>			
			</TABLE>
		</td>
	</tr>
	</TABLE>

	</td></tr></TABLE>
</tr></td></TABLE>

<SCRIPT>
chkInducingAgent();
chkObservation();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>