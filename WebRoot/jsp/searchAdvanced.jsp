<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<script language="JavaScript" src="scripts/EVSTreeScript.js"></script>

<html:form action="SearchAction.do" focus="keyword">

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0">
                <tr>
                        <td class="formTitleBlue" height="20" colspan="3">Keyword Search:&nbsp;&nbsp;<input type="text" name="field3" id="field3" size="55" />&nbsp;&nbsp;<input class="actionButton" type="submit" value="Search" /></td>
                </tr>
                
                <tr><td>&nbsp;</td></tr>
                
                <tr>
			<td class="formTitleBlue" height="20" colspan="3">Advanced Search</td>		
		</tr>
		
		<tr>
		    <td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formRequiredLabel"><label for="field1">Model Name /Model Descriptor:</label> 
				<camod:cshelp key="ABS_CANCER_MODEL.MODEL_DESCRIPTOR" image="images/iconHelp.gif" text="Tool Tip Test 1" />
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
				<label for="field2">Site of Lesion/Tumor:</label>
				&nbsp;
		  	    <a href="javascript:showTissueTree('input', 'descendants=true;isaFlag=false;depthLevel=6;roleType=Anatomic_Structure_is_Physical_Part_of')">
				<IMG src="images\selectUP.gif" align=middle border=0>
				</a>
				<INPUT name="organTissueName" type="hidden"/>
		 		<INPUT name="organTissueCode" type="hidden"/>
			</td>
			<td class="formField"><input class="formFieldSized" type="text" disabled="true" name="organ" id="organFieldId" size="25" /></td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field2">Diagnosis</label>
				&nbsp;
		  	    <a href="javascript:showDiagnosisTree('input', 'descendants=true;isaFlag=false;depthLevel=6;roleType=Anatomic_Structure_is_Physical_Part_of')">
				<IMG src="images\selectUP.gif" align=middle  border=0>
				</a>
				<input type="hidden" name="DiagnosisName"/>
			    <input type="hidden" name="DiagnosisCode"/>
			</td>
			<td class="formField">
				<input class="formFieldSized" type="text" disabled="true" name="TumorClassification" id="field3" size="25" />
			</td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field3">Species:</label></td>
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="species" >
					<html:options name="<%= Dropdowns.SPECIESQUERYDROP %>" />										
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

			<td class="formLabel">
				<label for="field1">Gene Name:</label>
			</td>
			<td class="formField">
				<input class="formFieldSized" type="text" name="field1" id="field1" size="30" />
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field1">&nbsp;</label>
			</td>
			<td class="formField">
				<input type="checkbox" name="box1" id="box1" checked="checked" /> <label for="box1">Engineered Transgene</label>
				<br />
				<input type="checkbox" name="box2" id="box2" /> <label for="box2">Targeted Modification</label>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field1">Genomic Segment Designator:</label>
			</td>

			<td class="formField">
				<input class="formFieldSized" type="text" name="field1" id="field1" size="30" />
			</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field3">Select Inducing Agent for Induced Mutation</label>
			</td>

			<td class="formField">
				<select class="formFieldSized" name="field3" id="field3" size="1">
                                    <OPTION value="">
                                    <OPTION value="1">Chemical
                                    <OPTION value="2">Radiation
                                    <OPTION value="3">Transgene
				</select>
			</td>
		</tr>

		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Carcinogenic Agents:</td>
		</tr>

		<tr>	
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field1">Models with Carcinogenic Interventions:</label>
			</td>

			<td class="formField">
				<input type="checkbox" name="box1" id="box1" checked="checked" /> 
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
			<td class="formLabel">
				<label for="field1">Compound/Drug:</label>
			</td>
			<td class="formField">
				<input class="formFieldSized" type="text" name="field1" id="field1" size="30" />
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">Models with Therapeutic Approaches</td>
			<td class="formField">
				<input type="checkbox" name="box1" id="box1" checked="checked" /> 
				<label for="box1">Check here to search for models with <br>therapeutic approaches data</label>
			</td>
		</tr>

		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Histopathology</td>
		</tr>		
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">Models with Metastasis</td>
			<td class="formField">
				<input type="checkbox" name="box1" id="box1" checked="checked" /> 
				<label for="box1">Check here to search for models with Metastasis</label>
			</td>
		</tr>		

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Search for Models with Metastasis in Organ</label>&nbsp;<IMG src="images\selectUP.gif" align=middle></td>
			<td class="formField"><input class="formFieldSized" type="text" disabled="true" name="field3" id="field3" size="25" /></td>
		</tr>		
		
		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Microarray Data</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">Microarray Data</td>
			<td class="formField">
				<input type="checkbox" name="box1" id="box1" checked="checked" /> 
				<label for="box1">Check here to search for models with microarray data</label>
			</td>
		</tr>
		<tr>


			<td align="right" colspan="3">
				<!-- action buttons begins -->
				<TABLE cellpadding="4" cellspacing="0" border="0">
					<tr>
					  <html:submit styleClass="actionButton">
						  <bean:message key="button.submit"/>
					  </html:submit>
					  
					  <html:reset styleClass="actionButton">
					  	  <bean:message key="button.reset"/>
	  				  </html:reset>
				  </html:form>			
				  	</tr>
				</TABLE>
			</td>
		</tr>
		<!-- action buttons end -->
	</td></tr></TABLE>
</td></tr></TABLE>	

<%@ include file="/jsp/footer.jsp" %>