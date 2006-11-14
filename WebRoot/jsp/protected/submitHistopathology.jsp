<%
/*
 * $Log: not supported by cvs2svn $
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
 * $Id: submitHistopathology.jsp,v 1.27 2006-11-14 21:47:41 pandyas Exp $
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

<html:form action="<%= actionName %>" focus="organ">

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
		<td class="formTitle" height="20" colspan="3">Histopathology:&nbsp;
			<camod:cshelp topic="histopathology_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>			
	</tr>

	<tr>

		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Site of Lesion/Tumor:</label>&nbsp;
			<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/iconHelp.gif" text="Tool Tip Test 1" />

			<a href="javascript:showMouseTissueTree('histopathologyForm', 'organTissueCode', 'organTissueName', 'organ', true)">
				<IMG src="images\selectUP.gif" align=middle border=0>
			</a>
		</td>						
		<td class="formField">
			<html:hidden property="organTissueCode"/>
			<input type="hidden" name="organTissueName" />				
			<html:text styleClass="formFieldSized" disabled="true" property="organ" size="30"  />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field2">Diagnosis:</label>&nbsp;				
			<camod:cshelp topic="data_tree_help" key="DIAGNOSIS.CONCEPT_CODE" image="images/iconHelp.gif" text="Tool Tip Test 1" />

			<a href="javascript:showMouseDiagnosisTree('histopathologyForm', 'diagnosisCode', 'diagnosisName', 'tumorClassification', true)">
				<IMG src="images\selectUP.gif" align=middle border=0>
			</a>	
		</td>
		<html:hidden property="diagnosisCode"/>		
		<html:hidden property="diagnosisName"/>
		<td class="formField">
			<html:text styleClass="formFieldSized" disabled="true" property="tumorClassification"   size="25" />
		</td>
	</tr>
		
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Age of Tumor Onset:</label>
		</td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="ageOfOnset"  size="10" />
			
			<html:select styleClass="formFieldUnSized" size="1" property="ageOfOnsetUnit" >												
				<html:options name="<%= Dropdowns.AGEUNITSDROP %>"/>					
			</html:select>
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Age of Tumor Detection:</label>		
		</td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="ageOfDetection"  size="10" />
			
			<html:select styleClass="formFieldUnSized" size="1" property="ageOfDetectionUnit" >												
				<html:options name="<%= Dropdowns.AGEUNITSDROP %>"/>					
			</html:select>
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Average Weight of Tumor (mg):</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="weightOfTumor"  size="10" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Average Volume of Tumor (mm<sup>3</sup>): </label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="volumeOfTumor"  size="10" />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Tumor Incidence over Lifetime (%)<br>(Enter numbers only):</label>
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="tumorIncidenceRate" size="30" />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Survival Information:</label>
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="survivalInfo" size="30" />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Gross Description / Macroscopic Description:</label>
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="grossDescription" size="30" />
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Microscopic Description (Field holds 2,000 characters):</label>
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized"  property="microscopicDescription" cols="32" rows="4"/>			
			</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Genetic Alterations found in the Tumor:</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="observation" cols="32" rows="4" onkeypress="chkObservation();"/>
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Method of Observation:</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="methodOfObservation" cols="32" rows="4"  />
		</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Comparative Data from other Species:</label>
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="comparativeData" size="30" />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Comment:</label>
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
	  				  
	  			  <c:if test="${not empty aHistopathologyID}">
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
			      </c:if>
			      
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <input type="hidden" name="aHistopathologyID" value="<%= aHistopathologyID %>">
				  
				  </html:form>
			</TABLE>
			<!-- action buttons end -->
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