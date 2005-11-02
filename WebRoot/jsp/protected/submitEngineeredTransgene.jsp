<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page buffer="32kb"%>
<%@ page import="gov.nih.nci.camod.domain.EngineeredGene" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.EngineeredTransgeneForm" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.EngineeredTransgeneData" %>	
<%@ page import="gov.nih.nci.camod.Constants.*" %>
<%@ page import="java.util.List" %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<script language="JavaScript" src="scripts/global.js"></script>

<%
	String aEngineeredTransgeneID = request.getParameter( "aEngineeredTransgeneID" );

	//if aEngineeredTransgeneID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "EngineeredTransgeneAction.do?method=save";
	
	if ( aEngineeredTransgeneID != null && aEngineeredTransgeneID.length()> 0) {
		actionName = "EngineeredTransgeneAction.do?method=edit";
	}
	else {
	    aEngineeredTransgeneID = "";
	}
%>

<SCRIPT LANGUAGE="JavaScript">

	function chkIntegration() {
			
		if( document.forms[0].locationOfIntegration[1].checked == true ) {
			enableField(document.forms[0].otherLocationOfIntegration);
		}
		else {
		    disableField(document.forms[0].otherLocationOfIntegration);
		}
	}
	
	function chkName() {
	     chkOtherUnsized(document.forms[0].scientificName,document.forms[0].otherScientificName);
	}
	
	function chkOther_t1() {
	     chkOtherUnsized(document.forms[0].transcriptional1_species, document.forms[0].transcriptional1_otherSpecies);
	}
		
	function chkOther_t2() {
	     chkOtherUnsized(document.forms[0].transcriptional2_species, document.forms[0].transcriptional2_otherSpecies);
	}

	function chkOther_t3() {
	     chkOtherUnsized(document.forms[0].transcriptional3_species, document.forms[0].transcriptional3_otherSpecies);
	}
	
	function chkOther_PS() {
	    chkOtherUnsized(document.forms[0].polyASignal_species, document.forms[0].polyASignal_otherSpecies);
	}
	
	function chkOther_SS() {
		chkOtherUnsized(document.forms[0].spliceSites_species, document.forms[0].spliceSites_otherSpecies);
	}
							
	function chkConditional() {
	
		if( document.forms[0].conditionedBy[0].checked == true ) {
			enableFieldUnsized(document.forms[0].description);
		}
		else {
		    disableFieldUnsized(document.forms[0].description);
		}
	}
	
</SCRIPT>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">

	<tr>
		<html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>
	
	<tr>
		<td class="formTitle" height="20" colspan="3">Engineered Transgene </td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Transgene Integration</label></td>
		<td class="formField">
			<html:form action="<%= actionName %>" focus="locationOfIntegration" enctype="multipart/form-data">	

			<html:radio property="locationOfIntegration" value="Random" onchange="chkIntegration(this);" /> Random 
			<html:radio property="locationOfIntegration" value="Targeted" onchange="chkIntegration(this);" /> Targeted
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Location of Integration *</label><br>(Required field when "Targeted" is selected<br>for the "Transgene Integration" field)
		<camod:cshelp mapId="engineered_transgene_help" key="ENGINEERED_GENE.LOCATION_OF_INTEGRATION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="otherLocationOfIntegration" disabled="true" size="10" />
		</td>
	</tr>

	<tr>
		<td class="formTitle" colspan="3">Transgene (coding sequence only)</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Transgene</label>
		<camod:cshelp mapId="engineered_transgene_help" key="ENGINEERED_GENE.NAME_TRANSGENE" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">Transgene<br>
						<html:text styleClass="formFieldUnSized" property="name" size="20" />
					</td>
					<td class="standardText" width="33%">Species of Origin<br>
						<html:select styleClass="formFieldUnSized" size="1" property="scientificName" onchange="chkName();" >
							<html:optionsCollection name="<%= Dropdowns.HOSTSPECIESDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%">Other Species<br>
						<html:text styleClass="formFieldUnSized" property="otherScientificName" disabled="true" size="20" />
					</td>
				</tr>
			</TABLE>	
		</td>
	</tr>
	
	<tr>
		<td class="formTitle" colspan="3">Transcriptional (Promoter)</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Transcriptional 1</label>
		<camod:cshelp mapId="engineered_transgene_help" key="REGULATORY_ELEMENT.NAME_Transcriptional1" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">Transgene<br>
						<html:text styleClass="formFieldUnSized" property="transcriptional1_name" size="20" />
					</td>
					<td class="standardText" width="33%">Species of Origin<br>
						<html:select styleClass="formFieldUnSized" size="1" property="transcriptional1_species" onchange="chkOther_t1();" >
							<html:optionsCollection name="<%= Dropdowns.HOSTSPECIESDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%">Other Species<br>
						<html:text styleClass="formFieldUnSized" property="transcriptional1_otherSpecies" disabled="true" size="20" />
					</td>
				</tr>
			</TABLE>		
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Transcriptional 2</label></td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">Transgene<br>
						<html:text styleClass="formFieldUnSized" property="transcriptional2_name" size="20" />
					</td>
					<td class="standardText" width="33%">Species of Origin<br>
						<html:select styleClass="formFieldUnSized" size="1" property="transcriptional2_species" onchange="chkOther_t2();" >
							<html:optionsCollection name="<%= Dropdowns.HOSTSPECIESDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%">Other Species<br>
						<html:text styleClass="formFieldUnSized" property="transcriptional2_otherSpecies" disabled="true" size="20" />
					</td>
				</tr>
			</TABLE>	
		</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Transcriptional 3</label></td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">Transgene<br>
						<html:text styleClass="formFieldUnSized" property="transcriptional3_name" size="20" />
					</td>
					<td class="standardText" width="33%">Species of Origin<br>
						<html:select styleClass="formFieldUnSized" size="1" property="transcriptional3_species" onchange="chkOther_t3();" >
							<html:optionsCollection name="<%= Dropdowns.HOSTSPECIESDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%">Other Species<br>
						<html:text styleClass="formFieldUnSized" property="transcriptional3_otherSpecies" disabled="true" size="20" />
					</td>
				</tr>
			</TABLE>	
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Poly A Signal</label>
		<camod:cshelp key="REGULATORY_ELEMENT.NAME_PolyA" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">Transgene<br>
						<html:text styleClass="formFieldUnSized" property="polyASignal_name" size="20" />
					</td>
					<td class="standardText" width="33%">Species of Origin<br>
						<html:select styleClass="formFieldUnSized" size="1" property="polyASignal_species" onchange="chkOther_PS();" >
							<html:optionsCollection name="<%= Dropdowns.HOSTSPECIESDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%">Other Species<br>
						<html:text styleClass="formFieldUnSized" property="polyASignal_otherSpecies" disabled="true" size="20" />
					</td>
				</tr>
			</TABLE>	
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Splice Sites / Intron</label></td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">Transgene<br>
						<html:text styleClass="formFieldUnSized" property="spliceSites_name" size="20" />
					</td>
					<td class="standardText" width="33%">Species of Origin<br>
						<html:select styleClass="formFieldUnSized" size="1" property="spliceSites_species" onchange="chkOther_SS();" >
							<html:optionsCollection name="<%= Dropdowns.HOSTSPECIESDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%">Other Species<br>
						<html:text styleClass="formFieldUnSized" property="spliceSites_otherSpecies" disabled="true" size="20" />
					</td>
				</tr>
			</TABLE>	
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">MGI Number</label>
		<camod:cshelp mapId="engineered_transgene_help" key="MUTATION_IDENTIFIER.NUMBER_MGI" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<input type=button value="Find MGI #" onClick="myRef = window.open('http://www.informatics.jax.org/','mywin',
			'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"></input>
			<html:text styleClass="formFieldUnSized" property="numberMGI" size="20" />
		</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Gene Functions<br>(seperate each entry by a comma)</label>
			<camod:cshelp mapId="engineered_transgene_help" key="GENE_FUNCTION.FUNCTION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="geneFunctions"  rows="4" cols="32"  />		
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Conditional?</label></td>
		<td class="formField">
			<html:radio property="conditionedBy" value="Conditional" onchange="chkConditional(this);" /> Conditional 
			<html:radio property="conditionedBy" value="Not Conditional" onchange="chkConditional(this);" /> Not Conditional
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Conditional Description</label>
			<camod:cshelp mapId="engineered_transgene_help" key="CONDITIONALITY.DESCRIPTION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="description" disabled="true" rows="4" cols="32" />		
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Additional Features</label>
			<camod:cshelp mapId="engineered_transgene_help" key="ENGINEERED_GENE.COMMENTS" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="comments"  rows="4" cols="32"  />	
		</td>
	</tr>
	
	<tr>
		<td class="formTitle" colspan="3">&nbsp;</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Upload Construct Map (Image)</label></td>
		<td class="formField">
		
			<c:if test="${not empty engineeredTransgeneForm.fileServerLocation}">
				<c:set var="uri" value="javascript: rs('commentWin','viewLizardImage.do?aFileServerLocation=${engineeredTransgeneForm.fileServerLocation}',600,600);"/>
			
					Current Image: <c:out value="${engineeredTransgeneForm.fileServerLocation}"/><br>
					Current Image Thumbnail: <br>
						
					<a href='<c:out value="${uri}"/>'>			
					
					<img src="http://caimage.nci.nih.gov/lizardtech/iserv/getthumb?cat=Model&amp;img=<c:out value="${engineeredTransgeneForm.fileServerLocation}"/>&amp;thumbspec=" main="" alt="<bean:write name='formdata' property='fileServerLocation'/>" target="_blank">				
					Click to View</a><br><br>									
		    </c:if>
						
			<html:file styleClass="formFieldSized" size="40" property="fileLocation" />			
		</td>			
	</tr>		

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Title of Construct<br>(Enter info only when uploading image)</label></td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="title" rows="4" cols="32" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Description of Construct<br>(Enter info only when uploading image)</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="descriptionOfConstruct" rows="4" cols="32"  />	
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
				
		          <c:if test="${not empty aEngineeredTransgeneID}">
	  				  <html:submit property="action" styleClass="actionButton" onclick="confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
			      </c:if>
			      
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <input type="hidden" name="aEngineeredTransgeneID" value="<%= aEngineeredTransgeneID %>">
				  	
				</html:form>			
			</TABLE>
		</td>
	</tr>
</TABLE>

<SCRIPT>
chkConditional();
chkIntegration();
chkName();
chkOther_t1();
chkOther_t2();
chkOther_t3();
chkOther_PS();
chkOther_SS();
</SCRIPT>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>



<%@ include file="/jsp/footer.jsp" %>




