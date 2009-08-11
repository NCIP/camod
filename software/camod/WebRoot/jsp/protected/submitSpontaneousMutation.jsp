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
<script language="JavaScript" src="scripts/global.js"></script>

<%
	String aSpontaneousMutationID = request.getParameter( "aSpontaneousMutationID" );
    String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
    
	String actionName = "SpontaneousMutationAction.do?method=save";
	
	if ( aSpontaneousMutationID != null && aSpontaneousMutationID.length() > 0 && isDeleted == null) {
		actionName = "SpontaneousMutationAction.do?method=edit";
	}
	else {
	    aSpontaneousMutationID = "";
	}
%>

<html:form action="<%= actionName %>" focus="name">	


<!-- submitSpontaneousMutation.jsp -->
<!-- Main Content Begins -->
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
		<td class="formTitle" height="20" colspan="3">Spontaneous Mutation&nbsp;
			<camod:cshelp topic="spontaneous_mutation_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Gene Name:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="name"  size="30" />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Observation:</label></td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="observation" rows="4" cols="32" onkeypress="chkObservation();" />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Method of Observation:</label></td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="methodOfObservation" rows="4" cols="32"   />
		</td>
	</tr>
	<tr>
               
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Entrez Gene ID:</label></td>
		<td class="formField">
			<input type=button value="Find Gene ID" onClick="myRef = window.open('http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?db=gene','mywin',
			'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>		
			<label for="field1">&nbsp;&nbsp;</label>
			<html:text styleClass="formFieldUnSized" property="geneIdentifier" size="20" />	
	</tr>	
	
	<tr>
		<c:if test="${modelspeciescommonname == 'Mouse'}">			
				<td class="formRequiredNotice" width="5">&nbsp;</td>
				<td class="formLabel"><label for="field2">MGI Identifier:</label>
				</td>
				<td class="formField">
					<input type=button value="Find MGI ID" onClick="myRef = window.open('http://www.informatics.jax.org/','mywin',
								'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
					<label for="field1">&nbsp;&nbsp;</label>
					<html:text styleClass="formFieldUnSized" size="25" property="mgiId"  />
				</td>
		</c:if>				
	</tr>	
	<tr>
		<c:if test="${modelspeciescommonname == 'Zebrafish'}">	
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">ZFIN Identifier:</label>
			</td>
			<td class="formField">
				<input type=button value="Find ZFIN ID" onClick="myRef = window.open('http://zfin.org/','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
				<label for="field1">&nbsp;&nbsp;</label>
				<html:text styleClass="formFieldUnSized" size="25" property="zfinId"  />
			</td>
		</c:if>
	</tr>	
	
	<tr>
		<c:if test="${modelspeciescommonname == 'Rat'}">	
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">RGD Identifier:</label>
			</td>
			<td class="formField">
				<input type=button value="Find RGD ID" onClick="myRef = window.open('http://rgd.mcw.edu/strains/','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
				<label for="field1">&nbsp;&nbsp;</label>
				<html:text styleClass="formFieldUnSized" size="25" property="rgdId"  />
			</td>
		</c:if>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Comment:</label></td>
		<td class="formField">			
			<html:textarea styleClass="formFieldSized" property="comments"  rows="4" cols="32" />
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
  				  
				  <c:if test="${not empty aSpontaneousMutationID}">
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
			      </c:if>
			      
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

<SCRIPT>
chkObservation();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>