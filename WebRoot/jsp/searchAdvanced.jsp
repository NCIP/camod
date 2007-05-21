<%

/**
 * 
 * $Id: searchAdvanced.jsp,v 1.60 2007-05-21 17:36:13 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.59  2007/05/18 15:35:59  pandyas
 * Modifed label for Agent Type and Agent Name as per Ulli
 *
 * Revision 1.58  2007/05/18 14:41:13  pandyas
 * Modified species and organ tree to default to no organ button and to work when user goes from species to empty selection
 *
 * Revision 1.57  2007/05/16 12:30:36  pandyas
 * Modified adv and simple search vocab tree section to populate depending on species selected
 *
 * Revision 1.56  2007/04/25 15:04:02  pandyas
 * Agreed on one help icon for all title bars and one icon for light grey tool tip - removed all others
 *
 * Revision 1.55  2007/04/09 12:35:16  pandyas
 * modified after caMOD 2.3 unit testing
 *
 * Revision 1.54  2007/03/28 18:11:35  pandyas
 * Modified for the following Test Track items:
 * #462 - Customized search for carcinogens for Jackson Lab data
 * #494 - Advanced search for Carcinogens for Jackson Lab data
 *
 * Revision 1.53  2006/12/28 16:05:26  pandyas
 * Reverted to previous version - changed CE on adv search page
 *
 * Revision 1.50  2006/11/21 18:24:49  pandyas
 * changed “External Source Data From Jackson Labs” (blue bar) to “Data from External Sources”
 *
 * Revision 1.49  2006/11/14 21:47:32  pandyas
 * #465	delete tooltops, keep tooltips for vocabulary trees and link them to vocab tree help pages
 *
 * Revision 1.48  2006/11/10 22:01:45  pandyas
 * TestTrack #465 - delete tooltops, keep tooltips for vocabulary trees and link them to vocab tree help pages
 *
 * Revision 1.47  2006/11/10 20:18:11  pandyas
 * Took out redundant ToolTip
 *
 * Revision 1.46  2006/11/06 16:15:34  pandyas
 * removed onclick from model description
 *
 * Revision 1.45  2006/10/17 16:08:28  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.44  2006/08/16 13:55:45  pandyas
 * updated on-line help from Robohelp to ePublisher - added new link for advanced search title
 *
 * Revision 1.43  2006/08/15 15:30:29  pandyas
 * updated on-line help from Robohelp to ePublisher - modified link - new data tree link added
 *
 * Revision 1.42  2006/08/13 17:45:53  pandyas
 * Updated online help - redefined camod tag by substituting mapId for topic (ePublisher changes)
 *
 * Revision 1.41  2006/05/25 12:02:51  georgeda
 * Slight tweaks
 *
 * Revision 1.40  2006/05/19 17:12:11  guptaa
 * added advance search
 *
 * Revision 1.39  2006/05/18 14:26:40  guptaa
 * fix style id
 *
 * Revision 1.38  2006/05/18 13:05:48  guptaa
 * added disease
 *
 * Revision 1.37  2006/05/17 21:24:49  guptaa
 * organ work with the autocomplete
 *
 * Revision 1.36  2006/05/15 19:52:23  georgeda
 * Fixed bugs introduced putting in Ajax
 *
 * Revision 1.35  2006/05/12 20:42:41  guptaa
 * deleted css
 *
 * Revision 1.34  2006/05/12 20:30:28  guptaa
 * indicator out
 *
 * Revision 1.33  2006/05/12 19:41:42  guptaa
 * uses tag
 *
 * Revision 1.32  2006/05/12 17:11:38  guptaa
 * ajax additions
 *
 * Revision 1.31  2006/05/10 14:21:51  schroedn
 * New Features - Changes from code review
 *
 * Revision 1.30  2006/05/10 12:02:47  georgeda
 * Changes for searching on transient interfaces
 *
 * Revision 1.29  2006/05/03 19:05:29  georgeda
 * Move to new EVSTree
 *
 * Revision 1.28  2006/04/28 19:38:15  schroedn
 * Defect # 261
 * Made changes so the organ and diagnosis save differently and can be retained for SaveQuery
 *
 * Revision 1.27  2006/03/31 13:47:36  georgeda
 * Changed the EVSTree call to work w/ new servers
 *
 * Revision 1.26  2005/12/19 14:05:33  georgeda
 * Defect #271 - Search issues
 *
 * Revision 1.25  2005/12/02 14:17:30  georgeda
 * Defect #241, handle truncated HTML tags
 *
 * Revision 1.24  2005/11/16 21:33:24  georgeda
 * Defect #40.  Changed reset button to clear.
 *
 * Revision 1.23  2005/11/16 19:43:30  georgeda
 * Added clear to search forms
 *
 * Revision 1.22  2005/11/16 16:32:43  georgeda
 * Defect #46.  Made disabling/enabling fields consistent between IE/Firefox
 *
 * Revision 1.21  2005/11/16 14:57:39  schroedn
 * Defect #50
 *
 * Changed the text on Submit button to 'Search'
 *
 *
 */

%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.Constants.*" %>
<%@ page import="gov.nih.nci.camod.service.SavedQueryManager" %>
<%@ page import="gov.nih.nci.camod.domain.SavedQuery" %>	
<%@ page import="gov.nih.nci.camod.domain.SavedQueryAttribute" %>	

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/camod/scripts/RoboHelp_CSH.js" type=text/javascript></SCRIPT>
<script language="JavaScript" src="scripts/EVSTreeScript.js"></script>
<script language="JavaScript" src="scripts/global.js"></script>
<script type="text/javascript" src="js/prototype-1.4.0.js"></script>
<script type="text/javascript" src="js/scriptaculous.js"></script>
<script type="text/javascript" src="js/ajaxtags-1.2-beta2.js"></script>


<SCRIPT LANGUAGE="JavaScript">
	
	function blankKeyword() {
        document.searchForm.keyword.value = '';
    }
	
	function checkFields() {
		
		theTargModFlag = document.searchForm.targetedModification[0];
		theEndTransGeneFlag = document.searchForm.engineeredTransgene[0];
		
		if (theTargModFlag.checked == true || theEndTransGeneFlag.checked == true) {
		    enableField(document.searchForm.geneName);
		}
		else {
		    disableField(document.searchForm.geneName);
		}
		
		toggleField(document.searchForm.searchTherapeuticApproaches[0], document.searchForm.therapeuticApproach);
	}
	
	function enableFields() {
		document.searchForm.organ.disabled = false;
		document.searchForm.tumorClassification.disabled = false;
	}
	
	function getOptions( control ) {
		form = control.form;
		form.action = "AdvancedSearchPopulateAction.do?unprotected_method=setAgentNameDropdown";
		form.submit();
	}
	
	function setAgentName() {
		document.forms[0].agentName.value = document.searchForm.agentName;
	}
	
	function getOrganTree( control ) {
		form = control.form;
		form.action = "AdvancedSearchPopulateAction.do?unprotected_method=setSpeciesForOrganTree";
		form.submit();
	}			
	
</SCRIPT>

<%
	//Display search criteria of executed search
	String aSavedQueryId = (String) request.getSession().getAttribute( Constants.ASAVEDQUERYID );
	String aQueryName = (String) request.getSession().getAttribute( Constants.QUERY_NAME );		
%>

<html:form action="SearchAdvancedAction.do" focus="keyword" onsubmit="enableFields()">

<!-- searchAdvanced.jsp -->
<!-- Main Content Begins -->
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0">
	    <tr>
	        <td class="formTitleBlue" height="20" colspan="3">Keyword Search:&nbsp;&nbsp;<html:text property="keyword" size="55"/>&nbsp;&nbsp;<input class="actionButton" type="submit" value="Search" /></td>
	    </tr>
        
        <tr>
        	<td colspan="3">        
		        <% if( aSavedQueryId != null ) { %>
			        <br>
					<TABLE border="0" class="contentPage" width="100%">
						<TR>
							<TD width="100%">
								<font color="red">* Saved query <b>"<%= aQueryName %>"</b> is being edited. You will be prompted to save the changes after pressing Search.</font>
				            </TD>
				        </TR>
			        </TABLE>				
		        <%}%>
        	</td>
        </tr>
        
        <tr>
			<td class="formTitleBlue" height="20" colspan="3">Advanced Search
				<camod:cshelp topic="advanced_search_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>		
		</tr>
		
		<tr>
		    <td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Model Name /Model Descriptor:</label> </td>
			<td class="formField">
				<html:text styleClass="formFieldSized" styleId="modelDescriptor" property="modelDescriptor" size="30"/>
				<ajax:autocomplete baseUrl="/camod/autocomplete.view" source="modelDescriptor" target="modelDescriptor"
  				parameters="modelDescriptor={modelDescriptor}" className="autocomplete" minimumCharacters="1" />	
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formLabel"><label for="field2">PI's Name:</label></td>
			
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="piName" >
					<html:options name="<%= Dropdowns.PRINCIPALINVESTIGATORQUERYDROP %>" />										
				</html:select>			
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field3">Species:</label></td>
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="species" onchange="getOrganTree(this);">
					<html:optionsCollection name="<%= Dropdowns.APPROVEDSPECIESDROP %>" />										
				</html:select>				
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
 			<!-- Display anatomy tree based on species selected or default to mouse tree if no species selected (new screen) -->			
			<c:choose>					
				<c:when test="${searchspeciescommonname == 'Mouse'}">
					<td class="formLabel"><label for="field1">Site of Lesion/Tumor:</label>&nbsp;
						<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />							
					<a href="javascript:showMouseTissueTree('searchForm', 'organTissueCode', 'organTissueName', 'organ', false)">
						<IMG src="images\selectUP.gif" align=middle border=0></a>
					</td>				
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">					
						<html:text styleClass="formFieldSized" disabled="true" property="organ" size="30"  />
						<ajax:autocomplete baseUrl="/camod/autocomplete.view" source="organ" target="organTissueCode"
	  					parameters="organTissueCode={organTissueCode}" className="autocomplete" minimumCharacters="1" />					
					</td>				
				</c:when>
				<c:when test="${searchspeciescommonname == 'Rat'}">	
					<td class="formLabel"><label for="field1">Site of Lesion/Tumor:</label>&nbsp;
						<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />				
					<a href="javascript:showRatTissueTree('searchForm', 'organTissueCode', 'organTissueName', 'organ', false)">
						<IMG src="images\selectUP.gif" align=middle border=0></a>
					</td>
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">										
						<html:text styleClass="formFieldSized" disabled="true" property="organ" size="30"  />
						<ajax:autocomplete baseUrl="/camod/autocomplete.view" source="organ" target="organTissueCode"
	  					parameters="organTissueCode={organTissueCode}" className="autocomplete" minimumCharacters="1" />						
					</td>
				</c:when>
				<c:when test="${searchspeciescommonname == 'Zebrafish'}">
					<td class="formLabel"><label for="field1">Site of Lesion/Tumor:</label>&nbsp;
						<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />				
					<a href="javascript:showRatTissueTree('searchForm', 'organTissueCode', 'organTissueName', 'organ', false)">
						<IMG src="images\selectUP.gif" align=middle border=0></a>
					</td>
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">										
						<html:text styleClass="formFieldSized" disabled="true" property="organ" size="30"  />
						<ajax:autocomplete baseUrl="/camod/autocomplete.view" source="organ" target="organTissueCode"
	  					parameters="organTissueCode={organTissueCode}" className="autocomplete" minimumCharacters="1" />						
					</td>
				</c:when>	
				<c:otherwise>
					<td class="formLabel"><label for="field1">Site of Lesion/Tumor:</label>&nbsp;
					</td>				
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">					
						<html:text styleClass="formFieldSized" disabled="true" property="organ" size="30"  />
						<ajax:autocomplete baseUrl="/camod/autocomplete.view" source="organ" target="organTissueCode"
	  					parameters="organTissueCode={organTissueCode}" className="autocomplete" minimumCharacters="1"/>					
					</td>				
				</c:otherwise>					
    		</c:choose>							
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
		 		<html:hidden styleId="diagnosisCode" property="diagnosisCode"/>
		 		<html:hidden styleId="diagnosisName" property="diagnosisName"/>
				<label for="field2">Diagnosis</label>
				&nbsp;
				<camod:cshelp topic="data_tree_help" key="DIAGNOSIS.CONCEPT_CODE" image="/camod/images/helpTooltip.gif" text="Tool Tip Test 1" />
		  	    <a href="javascript:showMouseDiagnosisTree('searchForm', 'diagnosisCode', 'diagnosisName', 'tumorClassification', false)">
				<IMG src="images\selectUP.gif" align=middle  border=0>
				</a>			    
			</td>
			<td class="formField">
				<html:text styleClass="formFieldSized" styleId="tumorClassification" property="tumorClassification" size="25"/>
				<ajax:autocomplete baseUrl="/camod/autocomplete.view" source="tumorClassification" target="diagnosisCode"
  				parameters="diagnosisCode={diagnosisCode}" className="autocomplete" minimumCharacters="1" />				
			</td>
		</tr>
		

		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Phenotype:</td>
		</tr>

		<tr>
		    <td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field3">Phenotype:</label></td>
			<td class="formField">			
					<html:text styleClass="formFieldSized" property="phenotype" size="30"/>
			</td>
		</tr>

		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Genetic Description:</td>
		</tr>

		<tr>
		    <td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field3">Gene Name:</label></td>
			<td class="formField">
			    <html:text styleClass="formFieldSized" styleId="geneName" property="geneName" size="30"/>
				<ajax:autocomplete baseUrl="/camod/autocomplete.view" source="geneName" target="geneName"
  				parameters="geneName={geneName}" className="autocomplete" minimumCharacters="1" />	
			</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field1">&nbsp;</label>
			</td>
			<td class="formField">
                <html:checkbox property="engineeredTransgene" onclick="checkFields()" />
                <!-- NOTE: Needed to workaround struts bug -->
                <input type="hidden" name="engineeredTransgene" value="false">
			    <label for="box1">Transgene</label>			
			    </br>
                <html:checkbox property="targetedModification" onclick="checkFields()" />
                <input type="hidden" name="targetedModification" value="false">
			    <label for="box1">Targeted Modification</label>			
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field1">Genomic Segment Designator:</label>
			</td>
			<td class="formField">			
					<html:text styleClass="formFieldSized" property="genomicSegDesignator" size="30"/>
			</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field3">Select Inducing Agent for Induced Mutation</label>
			</td>
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="inducedMutationAgent" >
					<html:options name="<%= Dropdowns.INDUCEDMUTATIONAGENTQUERYDROP %>" />										
				</html:select>				
			</td>
		</tr>

		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Carcinogenic Interventions:</td>
		</tr>
			
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>		
			<td class="formLabel"><label for="field3">Select Carcinogenic Agent Type:</label></td>
			<td class="formField">
				<html:select styleClass="formFieldSized" size="1" property="carcinogenicIntervention" onchange="getOptions(this);">
					<html:options name="<%= Dropdowns.CARCINOGENICAGENTSQUERYDROP %>"/>												
				</html:select>			
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field3">Select Carcinogenic Agent Name:</label></td>
			<td class="formField">
				<html:select styleClass="formFieldSized" size="1" property="agentName" >
					<html:options name="<%= Dropdowns.ENVIRONMENTALFACTORNAMESDROP %>" />												
				</html:select>
			</td>
		</tr>

		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Cell Lines</td>
		</tr>

		<tr>
		    <td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field3">Cell Line:</label></td>
			<td class="formField">			
					<html:text styleClass="formFieldSized" property="cellLine" size="30"/>
			</td>
		</tr>

		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Therapeutic Approaches</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">Models with Therapeutic Approaches</td>
			<td class="formField">
			    <html:checkbox property="searchTherapeuticApproaches" onclick="checkFields()" />
			    <!-- NOTE: Needed to work around struts bug -->
			    <input type="hidden" name="searchTherapeuticApproaches" value="false">	
				<label for="box1">Check here to search for models with <br>therapeutic approaches data</label>
			</td>
		</tr>
		<tr>
		    <td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field3">Compound/Drug:</label></td>
			<td class="formField">			
					<html:text styleClass="formFieldSized" property="therapeuticApproach" size="30"/>
			</td>
		</tr>

		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Histopathology</td>
		</tr>		
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">Models with Metastasis</td>
			<td class="formField">
			    <html:checkbox property="searchHistoMetastasis" />
			    <!-- NOTE: Needed to work around struts bug -->
			    <input type="hidden" name="searchHistoMetastasis" value="false">
				<label for="box1">Check here to search for models with Metastasis</label>
			</td>
		</tr>
		
		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Transient Interference</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">Transient Interference</td>
			<td class="formField">
			    <html:checkbox property="searchTransientInterference" />
			    <!-- NOTE: Needed to work around struts bug -->
			    <input type="hidden" name="searchTransientInterference" value="false">
				<label for="box1">Check here to search for models with transient interference data</label>
			</td>
		</tr>
		
		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Microarray Data</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">Microarray Data</td>
			<td class="formField">
			    <html:checkbox property="searchMicroArrayData" />
			    <!-- NOTE: Needed to work around struts bug -->
			    <input type="hidden" name="searchMicroArrayData" value="false">
				<label for="box1">Check here to search for models with microarray data</label>
			</td>
		</tr>
		
		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Image Data</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">Image Data</td>
			<td class="formField">
			    <html:checkbox property="searchImageData" />
			    <!-- NOTE: Needed to work around struts bug -->
			    <input type="hidden" name="searchImageData" value="false">
				<label for="box1">Check here to search for models with images</label>
			</td>
		</tr>		
		
		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Tool Strain</td>
		</tr>		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">Tool Strain</td>
			<td class="formField">
			    <html:checkbox property="searchToolStrain" />
			    <!-- NOTE: Needed to work around struts bug -->
			    <input type="hidden" name="searchToolStrain" value="false">
				<label for="box1">Check here to search for tool strains <BR><BR>(A tool mouse strain is a strain that does not develop cancer, 
				<BR>but can be used to create cancer-bearing models. Example: WAP-Cre strain)</label>
			</td>
		</tr>
		
		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Data from External Sources</td>
		</tr>		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field3">External Data Source:</label></td>
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="externalSource" >
					<html:options name="<%= Dropdowns.EXTERNALSOURCEQUERYDROP %>" />										
				</html:select>				
			</td>
		</tr>
			
		<tr>			
			<td align="right" colspan="3">
				<!-- action buttons begins -->
				<TABLE cellpadding="4" cellspacing="0" border="0">
					  
					  <html:submit styleClass="actionButton" onclick="blankKeyword()">
						  Search
					  </html:submit>
	  				  
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton">
					  	  <bean:message key="button.clear"/>
	  				  </html:submit>
	  				  
				  </html:form>		
				</TABLE>
			</td>
		</tr>
		<!-- action buttons end -->
	</TABLE>
</td></tr></TABLE>	

<SCRIPT LANGUAGE="JavaScript">
    checkFields();
	setAgentName();    
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>