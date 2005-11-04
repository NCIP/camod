<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page import="gov.nih.nci.camod.webapp.form.AssociatedMetastasisForm" %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<script language="JavaScript" src="scripts/EVSTreeScript.js"></script>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>

<%
	String aHistopathologyID = request.getParameter( "aHistopathologyID" );
	String aAssociatedMetastasisID = request.getParameter( "aAssociatedMetastasisID" );
	String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
	
	//if aAssocMetastasisID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	String actionName = "AssociatedMetastasisAction.do?method=saveMetastasis";
	
	if ( aAssociatedMetastasisID != null && aAssociatedMetastasisID.length() > 0 && isDeleted == null) {
		actionName = "AssociatedMetastasisAction.do?method=editMetastasis";
	}
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
		<td class="formTitle" height="20" colspan="3">Associated Metastasis:</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Site of Lesion/Tumor:</label>&nbsp;
			<camod:cshelp mapId="associated_metastasis_help" key="ORGAN.CONCEPT_CODE" image="images/iconHelp.gif" text="Tool Tip Test 1" />

			<a href="javascript:showTissueTree('associatedMetastasisForm', 'descendants=true;isaFlag=false;onlyLeaf=true;preferredName=true;depthLevel=6;;roleType=Anatomic_Structure_is_Physical_Part_of')">
				<IMG src="images\selectUP.gif" align=middle border=0>
			</a>
		</td>
		<td class="formField">
			<html:form action="<%= actionName %>" focus="organ">

			<html:hidden property="organTissueCode"/>
			<input type="hidden" name="organTissueName" />				
			<html:text styleClass="formFieldSized" disabled="true" property="organ" size="30"  />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field2">Diagnosis:</label>&nbsp;				
			<camod:cshelp mapId="associated_metastasis_help" key="DISEASE.NAME" image="images/iconHelp.gif" text="Tool Tip Test 1" />

			<a href="javascript:showDiagnosisTree('associatedMetastasisForm', 'descendants=true;isaFlag=false;preferredName=true;depthLevel=6;roleType=Anatomic_Structure_is_Physical_Part_of')">
				<IMG src="images\selectUP.gif" align=middle border=0>
			</a>	
		</td>
		<html:hidden property="diagnosisName"/>
		<html:hidden property="diagnosisCode"/>
		<td class="formField">
			<html:text styleClass="formFieldSized" disabled="true" property="tumorClassification"  size="25" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Age of Metastasis Onset:</label>
		<camod:cshelp mapId="associated_metastasis_help" key="HISTOPATHOLOGY.AGE_OF_ONSET" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="ageOfOnset"  size="10" />
			
			<html:select styleClass="formFieldUnSized" size="1" property="ageUnit" >												
				<html:options name="<%= Dropdowns.AGEUNITSDROP %>"/>					
			</html:select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Average Weight of Metastasis (mg):</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="weightOfTumor"  size="10" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Average Volume of Metastasis (mm<sup>3</sup>): </label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="volumeOfTumor"  size="10" />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Metastasis Incidence over lifetime (%)<br>(Enter numbers only):</label>
		<camod:cshelp mapId="associated_metastasis_help" key="HISTOPATHOLOGY.TUMOR_INCIDENCE_RATE" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="tumorIncidenceRate" size="30" />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Survival Information:</label>
		<camod:cshelp mapId="associated_metastasis_help" key="HISTOPATHOLOGY.SURVIVAL_INFO" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="survivalInfo" size="30" />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Gross Description / Macroscopic Lesion:</label>
		<camod:cshelp mapId="associated_metastasis_help" key="HISTOPATHOLOGY.GROSS_DESCRIPTION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="grossDescription" size="30" />
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Microscopic Description (Field holds 2,000 characters):</label>
		<camod:cshelp mapId="associated_metastasis_help" key="HISTOPATHOLOGY.MICROSCOPIC_DESCRIPTION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized"  property="microscopicDescription" cols="32" rows="4"/>			
			</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Genetic Alterations found in the Tumor:</label>
		<camod:cshelp mapId="associated_metastasis_help" key="GENETIC_ALTERATION.OBSERVATION_HISTO" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="observation" size="30" />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Method of Observation:</label>
		<camod:cshelp mapId="associated_metastasis_help" key="GENETIC_ALTERATION.METHOD_OF_OBSERVATION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="methodOfObservation" size="30" />
		</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Human Data:</label>
		<camod:cshelp mapId="associated_metastasis_help" key="HISTOPATHOLOGY.COMPARATIVE_DATA" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="comparativeData" size="30" />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Comments:</label>
		<camod:cshelp mapId="associated_metastasis_help" key="HISTOPATHOLOGY.COMMENTS" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized"  property="comments" cols="32" rows="4"/>			
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
	  				  
	  		      <c:if test="${not empty aAssociatedMetastasisID}">
	  				  <html:submit property="action" styleClass="actionButton" onclick="confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
			      </c:if>
			      
	  				 
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <input type="hidden" name="histopathologyID" value="<%= aHistopathologyID %>">
				  <input type="hidden" name="aAssociatedMetastasisID" value="<%= aAssociatedMetastasisID %>">
				  
				  </html:form>
			</TABLE>
			<!-- action buttons end -->
		</td>
	</tr>
</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>