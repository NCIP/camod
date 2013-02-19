<%
/*
 * $Log: not supported by cvs2svn $
 * Revision 1.36  2007/06/13 19:39:27  pandyas
 * Modified code for EVS trees after formal testing
 *
 * Revision 1.35  2007/06/13 12:12:30  pandyas
 * Modified for save of organ/diagnosis for each tree options
 * Removed constants so validation would work for all cases
 *
 * Revision 1.34  2007/06/05 19:26:44  pandyas
 * Added final method name for Zebrafish tissue tree
 *
 * Revision 1.33  2007/06/04 11:59:53  pandyas
 * Updated validation message
 *
 * Revision 1.32  2007/05/23 16:58:02  pandyas
 * Fixed ToolTip image to match correct one
 *
 * Revision 1.31  2007/05/17 12:24:17  pandyas
 * Modified screen to display EVSTree vacabulary
 *
 * Revision 1.30  2007/04/30 20:22:05  pandyas
 * Moved title bar inside c:when to eliminate tree help icon for text entry only situation
 *
 * Revision 1.29  2007/04/30 20:16:52  pandyas
 * Implemented species specific vocabulary trees from EVSTree
 *
 * Revision 1.28  2007/04/25 15:05:51  pandyas
 * Agreed on one help icon for all title bars and one icon for light grey tool tip - removed all others
 *
 * Revision 1.27  2006/11/14 21:47:41  pandyas
 * #465	delete tooltops, keep tooltips for vocabulary trees and link them to vocab tree help pages
 *
 * Revision 1.26  2006/11/10 22:01:34  pandyas
 * TestTrack #465 - delete tooltops, keep tooltips for vocabulary trees and link them to vocab tree help pages
 *
 * Revision 1.25  2006/10/27 13:01:26  pandyas
 * topic="skip" allows us to remove the onclick for ToolTips while preserving it for the title of each page
 *
 * Revision 1.24  2006/10/17 16:08:12  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.23  2006/08/13 18:25:45  pandyas
 * updated on-line help from Robohelp to ePublisher - modified links
 *
 * Revision 1.22  2006/05/03 19:05:50  georgeda
 * Move to new EVSTree
 *
 * Revision 1.21  2006/04/17 19:17:23  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.20  2006/03/31 13:47:17  georgeda
 * Changed the EVSTree call to work w/ new servers
 *
 * Revision 1.19  2005/12/29 22:20:36  pandyas
 * removed disabled="true" on methodOfObservation field
 *
 * Revision 1.18  2005/12/20 18:46:31  pandyas
 * Moved <html:form action..> above first table - caused errors when identifying fields on page
 *
 * Revision 1.17  2005/12/12 17:54:56  georgeda
 * Defect #268, added a return in front of all the confirms.
 *
 * Revision 1.16  2005/12/01 20:03:42  schroedn
 * Defect #239
 *
 * Changed the way the method of observation and observation fields interact. when observation is blank 'method of observation' is greyed out and when the user types in obsercation, the other is editable
 *
 * Revision 1.15  2005/11/30 17:33:22  pandyas
 * Defect #233: Modified field labels according to instructions
 *
 * Revision 1.14  2005/11/22 19:44:44  georgeda
 * Defect #172.  Changed to 'Gross Description / Macroscopic Description'
 *
 * Revision 1.13  2005/11/22 16:09:03  georgeda
 * One change fixes defects # 91, 107, 173.  Changed the EVS tree to correctly use the onlyLeaf setting.
 *
 * Revision 1.12  2005/11/21 22:04:26  georgeda
 * Defects #168,169,179.  Changed wording on submit and view pages
 *
 *
 * $Id: submitHistopathology.jsp,v 1.37 2009-06-01 14:44:56 pandyas Exp $
 *
 */   
%>
<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page import="gov.nih.nci.camod.webapp.form.HistopathologyForm" %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<%
	String aHistopathologyID = request.getParameter( "aHistopathologyID" );
	String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
	
	//if aHistopathID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "HistopathologyAction.do?method=saveHistopathology";
	
	if ( aHistopathologyID != null && aHistopathologyID.length() > 0 && isDeleted == null) {
		actionName = "HistopathologyAction.do?method=editHistopathology";
	} 
	else {
	    aHistopathologyID = "";
	}
%>

<SCRIPT LANGUAGE="JavaScript">
		
	function chkObservation() {
	
	    observation = document.forms[0].observation;
	
		if( observation.value != null && observation.value != "" ) {
			enableField(document.forms[0].methodOfObservation);
		}
		else {
			disableField(document.forms[0].methodOfObservation);
		}	
	}
	 
</SCRIPT>

<script language="JavaScript" src="scripts/EVSTreeScript.js"></script>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<script language="JavaScript" src="scripts/global.js"></script>


<!-- submitHistopathology.jsp -->
<!-- Main Content Begins -->
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/submitMenu.jsp" %>
<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%">
	<tr><td valign="top">
<!-- -->

	<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" align="left">
	<html:form action="<%= actionName %>" focus="organ">
	<tr>
		<html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>

	</tr>
	
	<tr>
		<td class="formTitle" height="20" colspan="3">Histopathology:&nbsp;
			<camod:cshelp topic="histopathology_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>			
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
 			<!-- Display anatomy tree based on animal model species or allow for text entry if no specific tree exists -->
 			<c:choose>
				<c:when test="${modelspeciescommonname == 'Mouse'}">
					<td class="formRequiredLabel"><label for="mouseOrgan">Site of Lesion/Tumor:</label>&nbsp;
						<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />							
					<a href="javascript:showMouseTissueTree('histopathologyForm', 'organTissueCode', 'organTissueName', 'organ', true)">
						<IMG alt="Select from EVSTree" src="images/selectUP.gif" align=middle border=0></a>
					</td>
								
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">					
						<html:text styleClass="formFieldSized" disabled="true" styleId="mouseOrgan" property="organ" size="30"  />
					</td>				
				</c:when>
				<c:when test="${modelspeciescommonname == 'Rat'}">	
					<td class="formRequiredLabel"><label for="ratOrgan">Site of Lesion/Tumor:</label>&nbsp;
						<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />				
					<a href="javascript:showRatTissueTree('histopathologyForm', 'organTissueCode', 'organTissueName', 'organ', true)">
						<IMG alt="Select from EVSTree" src="images/selectUP.gif" align=middle border=0></a>
					</td>
		
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">										
						<html:text styleClass="formFieldSized" disabled="true" styleId="ratOrgan" property="organ" size="30"  />
					</td>
				</c:when>	
				<c:when test="${modelspeciescommonname == 'Zebrafish'}">
					<td class="formRequiredLabel"><label for="zebOrgan">Site of Lesion/Tumor:</label>&nbsp;
						<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />				
					<a href="javascript:showZebrafishTissueTree('histopathologyForm', 'organTissueCode', 'organTissueName', 'organ', true)">
						<IMG alt="Select from EVSTree" src="images/selectUP.gif" align=middle border=0></a>
					</td>
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">										
						<html:text styleClass="formFieldSized" disabled="true" styleId="zebOrgan" property="organ" size="30"  />
					</td>
				</c:when>
				<c:otherwise>
					<td class="formRequiredLabel"><label for="lesOrgan">Site of Lesion/Tumor:</label>&nbsp;
					</td>
					<html:hidden property="organTissueCode"/>		
					<html:hidden property="organTissueName"/>
					<td class="formField">
						<html:text styleClass="formFieldSized" disabled="false" styleId="lesOrgan" property="organ"   size="25" />
					</td>				
				</c:otherwise>				
    		</c:choose>
	</tr>
	
	
	<tr>
		<td class="formRequiredNotice" width="5">*</td>
 			<!-- Display disease tree based on animal model species or allow for text entry if no specific tree exists -->
	 		<c:choose>			
				<c:when test="${modelspeciescommonname == 'Mouse'}">
					<td class="formRequiredLabel"><label for="mouseTumorClassification">Diagnosis:</label>&nbsp;				
							<camod:cshelp topic="data_tree_help" key="DIAGNOSIS.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />					
						<a href="javascript:showMouseDiagnosisTree('histopathologyForm', 'diagnosisCode', 'diagnosisName', 'tumorClassification', true)">
						<IMG src="images/selectUP.gif" align=middle border=0></a>	
					</td>
						<html:hidden property="diagnosisCode"/>		
						<html:hidden property="diagnosisName"/>
						<td class="formField">
							<html:text styleClass="formFieldSized" disabled="true" styleId="mouseTumorClassification" property="tumorClassification"   size="30" />
						</td>									
				</c:when>
				<c:when test="${modelspeciescommonname == 'Rat'}">
					<td class="formRequiredLabel"><label for="ratTumorClassification">Diagnosis:</label>&nbsp;				
						<camod:cshelp topic="data_tree_help" key="DIAGNOSIS.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />					
						<a href="javascript:showRatDiagnosisTree('histopathologyForm', 'diagnosisCode', 'diagnosisName', 'tumorClassification', true)">
						<IMG src="images/selectUP.gif" align=middle border=0></a>	
					</td>			
						<html:hidden property="diagnosisCode"/>		
						<html:hidden property="diagnosisName"/>
						<td class="formField">
							<html:text styleClass="formFieldSized" disabled="true" styleId="ratTumorClassification" property="tumorClassification"   size="30" />
						</td>												
				</c:when>
				<c:when test="${modelspeciescommonname == 'Zebrafish'}">
					<td class="formRequiredLabel"><label for="zebTumorClassification">Diagnosis:</label>&nbsp;				
					</td>	
						<td class="formField">
								<html:hidden property="diagnosisCode"/>	
								<html:hidden property="diagnosisName"/>													
							<html:select styleClass="formFieldSized" size="1" styleId="zebTumorClassification" property="tumorClassification" onchange="chkOtherDiagnosis();" >
								<html:optionsCollection name="<%= Constants.Dropdowns.ZEBRAFISHDIAGNOSISDROP %>" />										
							</html:select>					
							<br>
							-if Diagnosis is not listed, then please<br>select "Other" from the list and specify it below:
						</td>
					</tr>			
						<tr>
							<td class="formRequiredNotice" width="5">&nbsp;</td>
							<td class="formLabel"><label for="otherTumorClassification">Other Diagnosis:</label></td>
							<td class="formField">
								<html:text styleClass="formFieldSized" styleId="otherTumorClassification" property="otherTumorClassification"  size="30" />
							</td>
						</tr>						
				</c:when>							
				<c:otherwise>
					<td class="formRequiredLabel"><label for="otherTumorClassification1">Diagnosis:</label>&nbsp;				
					</td>
						<html:hidden property="diagnosisCode"/>								
						<html:hidden property="diagnosisName"/>
						<td class="formField">
							<html:text styleClass="formFieldSized" disabled="false" styleId="otherTumorClassification1" property="tumorClassification"   size="25" />
						</td>				
				</c:otherwise>
	    	</c:choose>
	</tr>

		
	<tr>
		<td class="formRequiredNotice" width="5"><label for="ageOfOnsetUnit">&nbsp;</label></td>
		<td class="formLabel"><label for="ageOfOnset">Age of Tumor Onset:</label>
		</td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" styleId="ageOfOnset" property="ageOfOnset"  size="10" />
			
			<html:select styleClass="formFieldUnSized" size="1" styleId="ageOfOnsetUnit" property="ageOfOnsetUnit" >												
				<html:options name="<%= Dropdowns.AGEUNITSDROP %>"/>					
			</html:select>
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5"><label for="ageOfDetectionUnit">&nbsp;</label></td>
		<td class="formLabel"><label for="ageOfDetection">Age of Tumor Detection:</label>		
		</td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" styleId="ageOfDetection" property="ageOfDetection"  size="10" />
			
			<html:select styleClass="formFieldUnSized" size="1" styleId="ageOfDetectionUnit" property="ageOfDetectionUnit" >												
				<html:options name="<%= Dropdowns.AGEUNITSDROP %>"/>					
			</html:select>
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="weightOfTumor">Average Weight of Tumor (mg):</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" styleId="weightOfTumor" property="weightOfTumor"  size="10" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="volumeOfTumor">Average Volume of Tumor (mm<sup>3</sup>): </label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" styleId="volumeOfTumor" property="volumeOfTumor"  size="10" />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="tumorIncidenceRate">Tumor Incidence over Lifetime (%)</label>
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" styleId="tumorIncidenceRate" property="tumorIncidenceRate" size="30" />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="survivalInfo">Survival Information:</label>
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" styleId="survivalInfo" property="survivalInfo" size="30" />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="grossDescription">Gross Description / Macroscopic Description<br> (Field holds 2,000 characters):</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" styleId="grossDescription" property="grossDescription" cols="32" rows="4"/>
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="microscopicDescription">Microscopic Description<br> (Field holds 2,000 characters):</label>
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized"  styleId="microscopicDescription" property="microscopicDescription" cols="32" rows="4"/>			
			</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="observation">Genetic Alterations found in the Tumor:</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" styleId="observation" property="observation" cols="32" rows="4" onkeypress="chkObservation();"/>
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="methodOfObservation">Method of Observation:</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" styleId="methodOfObservation" property="methodOfObservation" cols="32" rows="4"  />
		</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="comparativeData">Comparative Data from other Species:</label>
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" styleId="comparativeData" property="comparativeData" size="30" />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="comments">Comment:</label>
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized"  styleId="comments" property="comments" cols="32" rows="4"/>			
			</td>
	</tr>
	
	<tr>
		<td align="right" colspan="3">
			<!-- action buttons begins -->
			<TABLE summary="This table is used to format page content" cellpadding="4" cellspacing="0" border="0">
			
				  <html:submit styleClass="actionButton">
					  <bean:message key="button.submit"/>
				  </html:submit>
				  
				  <html:reset styleClass="actionButton">
				  	  <bean:message key="button.reset"/>
  				  </html:reset>
	  				  
	  			  <c:if test="${not empty aHistopathologyID}">
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
			      </c:if>
			      
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <input type="hidden" name="aHistopathologyID" value="<%= aHistopathologyID %>">				  

			</TABLE>
			<!-- action buttons end -->
		</td>
	</tr>
</html:form>	
</TABLE>

<!-- -->
	</td></tr></TABLE>
</td></tr></TABLE>

<SCRIPT>
	chkObservation();
	chkOtherDiagnosis();	
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>