<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page import="gov.nih.nci.camod.webapp.form.RadiationForm" %>	
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<script language="JavaScript" src="scripts/global.js"></script>

<%
	String aTherapyID = request.getParameter( "aTherapyID" );
	
	//if aTherapyID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "RadiationAction.do?method=save";
	
	if ( aTherapyID != null && aTherapyID.length() > 0) {
		actionName = "RadiationAction.do?method=edit";
	}
	else {
        aTherapyID = "";
    }
%>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	
<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center">
		<tr>
			<html:errors/>
			<td class="formMessage" colspan="3">* indicates a required field</td>
		</tr>
		
		<tr>
			<td class="formTitle" height="20" colspan="3">Radiation</td>		
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>	
			<td class="formRequiredLabel"><label for="field3">Radiation:</label></td>
			<td class="formField">
			<br>
			<label for="field3">(if Radiation is not listed, then please<br>select "Other" from the list and specify it below)</label>
			<br>
			<br>
			<html:form action="<%= actionName %>" focus="name">	
			
			<html:select styleClass="formFieldSized" size="1" property="name" onclick="chkOtherName();">
				<html:options name="<%= Dropdowns.RADIATIONDROP %>"/>					
			</html:select>
			</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Other Radiation:</label></td>
			<td class="formField">					
					<html:text styleClass="formFieldSized" size="30" property="otherName"   />		
			</td>
		</tr>
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Dose:</label>
		<camod:cshelp key="TREATMENT.DOSAGE" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized"  property="dosage" size="10"  />
			<label for="field1">&nbsp;Units&nbsp;</label>
			<html:select styleClass="formFieldUnSized" size="1" property="doseUnit" >												
				<html:options name="<%= Dropdowns.RADIATIONUNITSDROP %>"/>					
			</html:select>
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
			<html:select styleClass="formFieldUnSized" size="1" property="administrativeRoute"  onclick="chkOtherAdminRoute(this);">												
				<html:options name="<%= Dropdowns.ADMINISTRATIVEROUTEDROP %>"/>					
			</html:select>			
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Administrative Route:</label></td>
		<td class="formField">					
			<html:text styleClass="formFieldSized" size="30" property="otherAdministrativeRoute" disabled="true"/>			
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Treatment Regimen:</label>
		<camod:cshelp key="TREATMENT.REGIMEN" image="images/iconHelp.gif" text="Tool Tip Test 1" />		
		</td>
		<td class="formField">
		<html:text styleClass="formFieldSized" property="regimen" size="30" />
		</td>
	</tr>

	<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Age:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="ageAtTreatment"  size="10" />
			<label for="field1">&nbsp;Units&nbsp;</label>
			<html:select styleClass="formFieldUnSized" size="1" property="ageUnit" >												
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
				<!-- action buttons begins -->
				<TABLE cellpadding="4" cellspacing="0" border="0">
				
					  <html:submit styleClass="actionButton">
						  <bean:message key="button.submit"/>
					  </html:submit>
					  
					  <html:reset styleClass="actionButton">
					  	  <bean:message key="button.reset"/>
	  				  </html:reset>
  				  
	  				  <c:if test="${not empty aTherapyID}">
		  				  <html:submit property="action" styleClass="actionButton" onclick="confirm('Are you sure you want to delete?');">
							  <bean:message key="button.delete"/>
						  </html:submit>
				      </c:if>				
			      
	  				  
					  <!--  Done this way since html:hidden doesn't seem to work correctly -->
  				  	  <input type="hidden" name="aTherapyID" value="<%= aTherapyID %>">
				  
				  </html:form>			
				</TABLE>
				<!-- action buttons end -->
			</td>
		</tr>
	</TABLE>
	</td></tr></TABLE>	
</td></tr></TABLE>

<SCRIPT>
chkOtherName();
chkOtherAdminRoute();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>
