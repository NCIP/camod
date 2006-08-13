<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page buffer="32kb"%>
<%@ page import="gov.nih.nci.camod.domain.EngineeredGene" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.EngineeredTransgeneForm" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.EngineeredTransgeneData" %>	
<%@ page import="gov.nih.nci.camod.Constants.*" %>
<%@ page import="java.util.List" %>

<%@ page import="gov.nih.nci.camod.Constants.CaImage" %>
<%@ page import="java.util.*" %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<script language="JavaScript" src="scripts/global.js"></script>

<%
	EngineeredTransgeneForm form = (EngineeredTransgeneForm) request.getAttribute( "engineeredTransgeneForm" );
	
	String aEngineeredTransgeneID = form.getTransgeneId();
	String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
	
	//if aEngineeredTransgeneID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "EngineeredTransgeneAction.do?method=save";
	
	if ( aEngineeredTransgeneID != null && aEngineeredTransgeneID.length()> 0 && isDeleted == null) {
		actionName = "EngineeredTransgeneAction.do?method=edit";
	}
	else {
	    aEngineeredTransgeneID = "";
	}
	
%>

<SCRIPT LANGUAGE="JavaScript">

	function chkConditional() {
	
		if( document.forms[0].conditionedBy[0].checked == true ) {
			enableFieldUnsized(document.forms[0].description);
		}
		else {
		    disableFieldUnsized(document.forms[0].description);
		}
	}	
</SCRIPT>

<html:form action="<%= actionName %>" focus="isRandom" enctype="multipart/form-data">

<!-- submitEngineeredTransgene.jsp -->
<!-- Main Content Begins -->
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
		<td class="formTitle" height="20" colspan="3">Transgene 
			<camod:cshelp topic="transgene_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Integration:</label></td>
			<td class="formField">
				<html:radio property="isRandom" value="yes" onclick="chkIntegration(this);" /> Random 
				<html:radio property="isRandom" value="no" onclick="chkIntegration(this);" /> Targeted  
			</td>		
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Location of Integration: *</label><br>(Required field when "Targeted" is selected<br>for the "Transgene Integration" field)
		<camod:cshelp topic="transgene_help" key="ENGINEERED_GENE.LOCATION_OF_INTEGRATION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" disabled="true" property="locationOfIntegration"  size="10" />
		</td>
	</tr>

	<tr>
		<td class="formTitle" colspan="3">Transgene (coding sequence only):</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Transgene:</label>
		<camod:cshelp topic="transgene_help" key="ENGINEERED_GENE.NAME_TRANSGENE" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">Transgene:<br>
						<html:text styleClass="formFieldUnSized" property="name" size="20" />
					</td>
					<td class="standardText" width="33%">Species of Origin:<br>
						<html:select styleClass="formFieldSized" size="1" property="scientificName" onchange="chkName();" >
							<html:optionsCollection name="<%= Dropdowns.SPECIESQUERYDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%">Other Species<br>
						<html:text styleClass="formFieldSized" size="20" property="otherScientificName"   />
					</td>
				</tr>
			</TABLE>	
		</td>
	</tr>
	
	<tr>
		<td class="formTitle" colspan="3">Transcriptional (Promoter):</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Transcriptional 1:</label>
		<camod:cshelp topic="transgene_help" key="REGULATORY_ELEMENT.NAME_Transcriptional1" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">Regulatory Element:<br>
						<html:text styleClass="formFieldUnSized" property="transcriptional1_name" size="20" />
					</td>
					<td class="standardText" width="33%">Species of Origin:<br>
						<html:select styleClass="formFieldSized" size="1" property="transcriptional1_species" onchange="chkOther_t1();" >
							<html:optionsCollection name="<%= Dropdowns.SPECIESQUERYDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%">Other Species:<br>
						<html:text styleClass="formFieldSized" property="transcriptional1_otherSpecies"  size="20" />
					</td>
				</tr>
			</TABLE>		
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Transcriptional 2:</label></td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">Regulatory Element:<br>
						<html:text styleClass="formFieldUnSized" property="transcriptional2_name" size="20" />
					</td>
					<td class="standardText" width="33%">Species of Origin:<br>
						<html:select styleClass="formFieldSized" size="1" property="transcriptional2_species" onchange="chkOther_t2();" >
							<html:optionsCollection name="<%= Dropdowns.SPECIESQUERYDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%">Other Species:<br>
						<html:text styleClass="formFieldSized" property="transcriptional2_otherSpecies"  size="20" />
					</td>
				</tr>
			</TABLE>	
		</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Transcriptional 3:</label></td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">Regulatory Element:<br>
						<html:text styleClass="formFieldUnSized" property="transcriptional3_name" size="20" />
					</td>
					<td class="standardText" width="33%">Species of Origin:<br>
						<html:select styleClass="formFieldSized" size="1" property="transcriptional3_species" onchange="chkOther_t3();" >
							<html:optionsCollection name="<%= Dropdowns.SPECIESQUERYDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%">Other Species<br>
						<html:text styleClass="formFieldSized" property="transcriptional3_otherSpecies"  size="20" />
					</td>
				</tr>
			</TABLE>	
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Poly A Signal:</label>
		<camod:cshelp topic="transgene_help" key="REGULATORY_ELEMENT.NAME_PolyA" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">Regulatory Element:<br>
						<html:text styleClass="formFieldUnSized" property="polyASignal_name" size="20" />
					</td>
					<td class="standardText" width="33%">Species of Origin:<br>
						<html:select styleClass="formFieldSized" size="1" property="polyASignal_species" onchange="chkOther_PS();" >
							<html:optionsCollection name="<%= Dropdowns.SPECIESQUERYDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%">Other Species:<br>
						<html:text styleClass="formFieldSized" property="polyASignal_otherSpecies"  size="20" />
					</td>
				</tr>
			</TABLE>	
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Splice Sites / Intron:</label></td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">Regulatory Element:<br>
						<html:text styleClass="formFieldUnSized" property="spliceSites_name" size="20" />
					</td>
					<td class="standardText" width="33%">Species of Origin:<br>
						<html:select styleClass="formFieldSized" size="1" property="spliceSites_species" onchange="chkOther_SS();" >
							<html:optionsCollection name="<%= Dropdowns.SPECIESQUERYDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%">Other Species:<br>
						<html:text styleClass="formFieldSized" property="spliceSites_otherSpecies"  size="20" />
					</td>
				</tr>
			</TABLE>	
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Gene Functions:<br>(seperate each entry by a comma)</label>
			<camod:cshelp topic="transgene_help" key="GENE_FUNCTION.FUNCTION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
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
		<td class="formLabel"><label for="field2">Conditional Description:</label>
			<camod:cshelp topic="transgene_help" key="CONDITIONALITY.DESCRIPTION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="description" rows="4" cols="32" />		
		</td>
	</tr>
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">MGI number:</label>
		<camod:cshelp topic="transgene_help" key="MUTATION_IDENTIFIER.NUMBER_MGI" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<input type=button value="Find MGI #" onClick="myRef = window.open('http://www.informatics.jax.org/','mywin',
			'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"></input>
			<label for="field1">&nbsp;&nbsp;</label>
			<html:text styleClass="formFieldUnSized" size="15" property="mgiNumber"  />
		</td>
	</tr>	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Comment:</label>
			<camod:cshelp topic="transgene_help" key="ENGINEERED_GENE.COMMENTS" image="images/iconHelp.gif" text="Tool Tip Test 1" />
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
		<td class="formLabel"><label for="field1">Upload Construct Map<br>(Image of type .jpg, .jpeg, .gif or .png):</label></td>
		<td class="formField">
		
			<c:if test="${not empty engineeredTransgeneForm.fileServerLocation}">
				Current Image Thumbnail: <br>
					
				<a href='<c:out value="${engineeredTransgeneForm.imageUrl}"/>'>						
				<img src="<c:out value="${engineeredTransgeneForm.thumbUrl}"/>" height="40" width="40" alt="<c:out value="${engineeredTransgeneForm.fileServerLocation}"/>" target="_blank">				
				Click to View</a>
				<br><br>													
			</c:if>

			<html:file styleClass="formFieldSized" size="40" property="fileLocation" />			
		</td>			
	</tr>		

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Title of Construct:<br>(Enter info only when uploading image)</label></td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="title" rows="4" cols="32" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Description of Construct:<br>(Enter info only when uploading image)</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="descriptionOfConstruct" rows="4" cols="32"  />	
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Construct Sequence:<br>(Enter info only when uploading image)</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="constructSequence" rows="4" cols="32"  />	
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
				
		          <c:if test="${not empty engineeredTransgeneForm.transgeneId}">
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
			      </c:if>
			      
				  <html:hidden property="transgeneId"/>
				  	
				</html:form>			
			</TABLE>
		</td>
	</tr>
</TABLE>

<SCRIPT>
chkIntegration();
chkConditional();
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




