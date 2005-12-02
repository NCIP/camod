<%

/**
 * 
 * $Id: searchAdvanced.jsp,v 1.25 2005-12-02 14:17:30 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
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
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/camod/scripts/RoboHelp_CSH.js" type=text/javascript></SCRIPT>
<script language="JavaScript" src="scripts/EVSTreeScript.js"></script>
<script language="JavaScript" src="scripts/global.js"></script>

<SCRIPT LANGUAGE="JavaScript">
	
	function blankKeyword() {
        document.searchForm.keyword.value = '';
    }
	
	function checkFields() {
	
	    // Do the CI fields
		theCIFlag = document.searchForm.searchCarcinogenicInterventions;
		toggleField(theCIFlag, document.searchForm.chemicalDrug);
		toggleField(theCIFlag, document.searchForm.growthFactor);
		toggleField(theCIFlag, document.searchForm.hormone);
		toggleField(theCIFlag, document.searchForm.radiation);
		toggleField(theCIFlag, document.searchForm.viral);
		toggleField(theCIFlag, document.searchForm.surgery);
		
		theTargModFlag = document.searchForm.targetedModification;
		theEndTransGeneFlag = document.searchForm.engineeredTransgene;
		
		if (theTargModFlag.checked == true || theEndTransGeneFlag.checked == true) {
		    enableField(document.searchForm.geneName);
		}
		else {
		    disableField(document.searchForm.geneName);
		}
		
		toggleField(document.searchForm.searchTherapeuticApproaches, document.searchForm.therapeuticApproach);
	}
		
</SCRIPT>



<html:form action="SearchAdvancedAction.do" focus="keyword" onsubmit="transferFields()">

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0">
                <tr>
                        <td class="formTitleBlue" height="20" colspan="3">Keyword Search:&nbsp;&nbsp;<html:text property="keyword" size="55"/>&nbsp;&nbsp;<input class="actionButton" type="submit" value="Search" /></td>
                </tr>
                
                <tr><td>&nbsp;</td></tr>
                
                <tr>
			<td class="formTitleBlue" height="20" colspan="3">Advanced Search</td>		
		</tr>
		
		<tr>
		    <td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Model Name /Model Descriptor:</label> 
				<camod:cshelp mapId="advanced_search_help" key="SEARCH.MODEL_DESCRIPTOR" image="images/iconHelp.gif" text="Tool Tip Test 1" />
			</td>
			<td class="formField">			
					<html:text styleClass="formFieldSized" property="modelDescriptor" size="30"/>
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
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formLabel">
				<label for="field2">Site of Lesion/Tumor</label>
				<camod:cshelp mapId="advanced_search_help" key="SEARCH.SITE_OF_TUMOR" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		  	    <a href="javascript:showTissueTree('searchForm', 'descendants=true;isaFlag=false;preferredName=true;depthLevel=6;roleType=Anatomic_Structure_is_Physical_Part_of')">
				<IMG src="images\selectUP.gif" align=middle border=0>
				</a>
				<INPUT name="organTissueName" type="hidden"/>
		 		<INPUT name="organTissueCode" type="hidden"/>
			</td>
			<td class="formField"><input class="formFieldSizedDisabled" type="text" disabled="true" name="organ" id="organFieldId" size="25" /></td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field2">Diagnosis</label>
				&nbsp;
		  	    <a href="javascript:showDiagnosisTree('searchForm', 'descendants=true;isaFlag=false;preferredName=true;depthLevel=6;roleType=Anatomic_Structure_is_Physical_Part_of')">
				<IMG src="images\selectUP.gif" align=middle  border=0>
				</a>
				<input type="hidden" name="diagnosisName"/>
			    <input type="hidden" name="diagnosisCode"/>
			</td>
			<td class="formField">
				<input class="formFieldSizedDisabled" type="text" disabled="true" name="tumorClassification" id="field3" size="25" />
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field3">Species:</label></td>
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="species" >
					<html:optionsCollection name="<%= Dropdowns.NEWSPECIESDROP %>" />										
				</html:select>				
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
					<html:text styleClass="formFieldSized" property="geneName" size="30"/>
			</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field1">&nbsp;</label>
			</td>
			<td class="formField">
                <html:checkbox property="engineeredTransgene" onclick="checkFields()" />	
			    <label for="box1">Engineered Transgene</label>			
			    </br>
                <html:checkbox property="targetedModification" onclick="checkFields()" />	
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
			<td class="formLabel">
				<label for="field1">Models with Carcinogenic Interventions:</label>
			</td>

			<td class="formField">
                <html:checkbox property="searchCarcinogenicInterventions" onclick="checkFields()" />	
			    <label for="box1">Check here to search for models with <br>Carcinogenic interventions data</label>			
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field3">Select Chemical/Drug:</label></td>
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="chemicalDrug" >
					<html:options name="<%= Dropdowns.CHEMICALDRUGQUERYDROP %>" />										
				</html:select>				
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field3">Select Growth Factor:</label></td>
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="growthFactor" >
					<html:options name="<%= Dropdowns.GROWTHFACTORQUERYDROP %>" />										
				</html:select>				
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field3">Select Hormone:</label></td>
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="hormone" >
					<html:options name="<%= Dropdowns.HORMONEQUERYDROP %>" />										
				</html:select>				
			</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field3">Select Radiation:</label></td>
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="radiation" >
					<html:options name="<%= Dropdowns.RADIATIONQUERYDROP %>" />										
				</html:select>				
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field3">Select Virus:</label></td>
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="viral" >
					<html:options name="<%= Dropdowns.VIRUSQUERYDROP %>" />										
				</html:select>				
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field3">Select Surgery:</label></td>
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="surgery" >
					<html:options name="<%= Dropdowns.SURGERYQUERYDROP %>" />										
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
				<label for="box1">Check here to search for models with Metastasis</label>
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
				<label for="box1">Check here to search for models with microarray data</label>
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
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>