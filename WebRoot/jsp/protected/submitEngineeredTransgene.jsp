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
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>	

<%
	String aEngineeredTransgeneID = request.getParameter( "aEngineeredTransgeneID" );

	//if aEngineeredTransgeneID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "EngineeredTransgeneAction.do?method=save";
	
	if ( aEngineeredTransgeneID != null )
		actionName = "EngineeredTransgeneAction.do?method=edit";
%>

<SCRIPT LANGUAGE="JavaScript">
	function chkIntegration( control ) {
		ideControl = document.forms[0].otherLocationOfIntegration;
			
		if( control.value == 'Targeted' )
			ideControl.disabled = false;
		else {
			ideControl.value = null;
			ideControl.disabled = true;
		}
	}
	
	function chkOther( control ) {
		ideControl = document.forms[0].otherScientificName;
			
		if( control.value == 'Other' )
			ideControl.disabled = false;
		else {
			ideControl.value = null;
			ideControl.disabled = true;
		}
	}
	
	function chkOther_t1( control ) {
		ideControl = document.forms[0].transcriptional1_otherSpecies;
			
		if( control.value == 'Other' )
			ideControl.disabled = false;
		else {
			ideControl.value = null;
			ideControl.disabled = true;
		}
	}
	
	function chkOther_t2( control ) {
		ideControl = document.forms[0].transcriptional2_otherSpecies;
			
		if( control.value == 'Other' )
			ideControl.disabled = false;
		else {
			ideControl.value = null;
			ideControl.disabled = true;
		}
	}
	
	function chkOther_t3( control ) {
		ideControl = document.forms[0].transcriptional3_otherSpecies;
			
		if( control.value == 'Other' )
			ideControl.disabled = false;
		else {
			ideControl.value = null;
			ideControl.disabled = true;
		}
	}
	
	function chkOther_PS( control ) {
		ideControl = document.forms[0].polyASignal_otherSpecies;
			
		if( control.value == 'Other' )
			ideControl.disabled = false;
		else {
			ideControl.value = null;
			ideControl.disabled = true;
		}
	}
	
	function chkOther_SS( control ) {
		ideControl = document.forms[0].spliceSites_otherSpecies;
			
		if( control.value == 'Other' )
			ideControl.disabled = false;
		else {
			ideControl.value = null;
			ideControl.disabled = true;
		}
	}
							
	function chkConditional( control ) {
		ideControl = document.forms[0].description;
			
		if( control.value == 'Conditional' )
			ideControl.disabled = false;
		else {
			ideControl.value = null;
			ideControl.disabled = true;
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
		<camod:cshelp key="ENGINEERED_GENE.LOCATION_OF_INTEGRATION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="otherLocationOfIntegration" disabled="true" size="10" name="formdata"/>
		</td>
	</tr>

	<tr>
		<td class="formTitle" colspan="3">Transgene (coding sequence only)</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Transgene</label>
		<camod:cshelp key="ENGINEERED_GENE.NAME_TRANSGENE" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">Transgene<br>
						<html:text styleClass="formFieldUnSized" property="name" size="20" name="formdata"/>
					</td>
					<td class="standardText" width="33%">Species of Origin<br>
						<html:select styleClass="formFieldUnSized" size="1" property="scientificName" onchange="chkOther( this );" >
							<html:options name="<%= Dropdowns.HOSTSPECIESDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%">Other Species<br>
						<html:text styleClass="formFieldUnSized" property="otherScientificName" disabled="true" size="20" name="formdata"/>
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
		<camod:cshelp key="REGULATORY_ELEMENT.NAME_Transcriptional1" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">Transgene<br>
						<html:text styleClass="formFieldUnSized" property="transcriptional1_name" size="20" name="formdata"/>
					</td>
					<td class="standardText" width="33%">Species of Origin<br>
						<html:select styleClass="formFieldUnSized" size="1" property="transcriptional1_species" onchange="chkOther_t1( this );" >
							<html:options name="<%= Dropdowns.HOSTSPECIESDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%">Other Species<br>
						<html:text styleClass="formFieldUnSized" property="transcriptional1_otherSpecies" disabled="true" size="20" name="formdata"/>
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
						<html:text styleClass="formFieldUnSized" property="transcriptional2_name" size="20" name="formdata"/>
					</td>
					<td class="standardText" width="33%">Species of Origin<br>
						<html:select styleClass="formFieldUnSized" size="1" property="transcriptional2_species" onchange="chkOther_t2( this );" >
							<html:options name="<%= Dropdowns.HOSTSPECIESDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%">Other Species<br>
						<html:text styleClass="formFieldUnSized" property="transcriptional2_otherSpecies" disabled="true" size="20" name="formdata"/>
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
						<html:text styleClass="formFieldUnSized" property="transcriptional3_name" size="20" name="formdata"/>
					</td>
					<td class="standardText" width="33%">Species of Origin<br>
						<html:select styleClass="formFieldUnSized" size="1" property="transcriptional3_species" onchange="chkOther_t3( this );" >
							<html:options name="<%= Dropdowns.HOSTSPECIESDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%">Other Species<br>
						<html:text styleClass="formFieldUnSized" property="transcriptional3_otherSpecies" disabled="true" size="20" name="formdata"/>
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
						<html:text styleClass="formFieldUnSized" property="polyASignal_name" size="20" name="formdata"/>
					</td>
					<td class="standardText" width="33%">Species of Origin<br>
						<html:select styleClass="formFieldUnSized" size="1" property="polyASignal_species" onchange="chkOther_PS( this );" >
							<html:options name="<%= Dropdowns.HOSTSPECIESDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%">Other Species<br>
						<html:text styleClass="formFieldUnSized" property="polyASignal_otherSpecies" disabled="true" size="20" name="formdata"/>
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
						<html:text styleClass="formFieldUnSized" property="spliceSites_name" size="20" name="formdata"/>
					</td>
					<td class="standardText" width="33%">Species of Origin<br>
						<html:select styleClass="formFieldUnSized" size="1" property="spliceSites_species" onchange="chkOther_SS( this );" >
							<html:options name="<%= Dropdowns.HOSTSPECIESDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%">Other Species<br>
						<html:text styleClass="formFieldUnSized" property="spliceSites_otherSpecies" disabled="true" size="20" name="formdata"/>
					</td>
				</tr>
			</TABLE>	
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">MGI Number</label>
		<camod:cshelp key="MUTATION_IDENTIFIER.NUMBER_MGI" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<input type=button value="Find MGI #" onClick="myRef = window.open('http://www.informatics.jax.org/','mywin',
			'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"></input>
			<html:text styleClass="formFieldUnSized" property="numberMGI" size="20" name="formdata"/>
		</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Gene Functions<br>(seperate each entry by a comma)</label>
			<camod:cshelp key="GENE_FUNCTION.FUNCTION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="geneFunctions"  rows="4" cols="32"  name="formdata"/>		
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
			<camod:cshelp key="CONDITIONALITY.DESCRIPTION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="description" disabled="true" rows="4" cols="32" name="formdata"/>		
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Additional Features</label>
			<camod:cshelp key="ENGINEERED_GENE.COMMENTS" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="comments"  rows="4" cols="32"  name="formdata"/>	
		</td>
	</tr>
	
	<tr>
		<td class="formTitle" colspan="3">&nbsp;</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Upload Construct Map (Image)</label></td>
		<td class="formField">
		
		<% 
			 // Only display a thumbnail if Image exists
		     EngineeredTransgeneForm theEngineeredTransgeneForm = (EngineeredTransgeneForm) request.getSession().getAttribute("formdata");
				
			 if ( theEngineeredTransgeneForm.getFileServerLocation() != null ) {
			 	if ( ! theEngineeredTransgeneForm.getFileServerLocation().equals( "" ) ) {
			 	
			 		pageContext.setAttribute("fileServerLocationName", theEngineeredTransgeneForm.getFileServerLocation() );
		%>
					<c:set var="uri" value="javascript: rs('commentWin','viewLizardImage.do?aFileServerLocation=${fileServerLocationName}',600,600);"/>
				
					Current Image: <bean:write name="formdata" property="fileServerLocation"/><br>
					Current Image Thumbnail: <br>
						
					<a href='<c:out value="${uri}"/>'>			
					
					<img src="http://caimage.nci.nih.gov/lizardtech/iserv/getthumb?cat=Model&amp;img=<bean:write name='formdata' property='fileServerLocation'/>&amp;thumbspec=" main="" alt="<bean:write name='formdata' property='fileServerLocation'/>" target="_blank">				
					Click to View</a><br><br>									
		<% 
				} 
			} 			
		%>
						
			<html:file styleClass="formFieldSized" size="40" property="fileLocation" name="formdata"/>			
		</td>			
	</tr>		

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Title of Construct<br>(Enter info only when uploading image)</label></td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="title" rows="4" cols="32" name="formdata"/>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Description of Construct<br>(Enter info only when uploading image)</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="descriptionOfConstruct" rows="4" cols="32"  name="formdata"/>	
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
				
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <input type="hidden" name="aEngineeredTransgeneID" value="<%= aEngineeredTransgeneID %>">
				  	
				</html:form>			
			</TABLE>
		</td>
	</tr>
</TABLE>

<SCRIPT>
	function checkOthers()
	{
	    ideControl = document.forms[0].conditionedBy;
	    ideOtherControl = document.forms[0].description;
			
		if( ideControl[0].checked == true )
			ideOtherControl.disabled = false;
		else {
			ideOtherControl.disabled = true;
		}
		
	    ideControl = document.forms[0].locationOfIntegration;
	    ideOtherControl = document.forms[0].otherLocationOfIntegration;
			
		if( ideControl[0].checked == true )
			ideOtherControl.disabled = true;
		else {
			ideOtherControl.disabled = false;
		}
		
		ideControl = document.forms[0].scientificName;		
		ideOtherControl = document.forms[0].otherScientificName;
		
		if( ideControl.value == 'Other' )
			ideOtherControl.disabled = false;
		else {
			ideOtherControl.disabled = true;
		}				
		
		ideControl = document.forms[0].transcriptional1_species;
	    ideOtherControl = document.forms[0].transcriptional1_otherSpecies;
			
		if( ideControl.value == 'Other' )
			ideOtherControl.disabled = false;
		else {
			ideOtherControl.disabled = true;
		}						
		
		ideControl = document.forms[0].transcriptional2_species;
	    ideOtherControl = document.forms[0].transcriptional2_otherSpecies;
			
		if( ideControl.value == 'Other' )
			ideOtherControl.disabled = false;
		else {
			ideOtherControl.disabled = true;
		}					
						
		ideControl = document.forms[0].transcriptional3_species;
	    ideOtherControl = document.forms[0].transcriptional3_otherSpecies;
			
		if( ideControl.value == 'Other' )
			ideOtherControl.disabled = false;
		else {
			ideOtherControl.disabled = true;
		}					
		
		ideControl = document.forms[0].polyASignal_species;
	    ideOtherControl = document.forms[0].polyASignal_otherSpecies;
			
		if( ideControl.value == 'Other' )
			ideOtherControl.disabled = false;
		else {
			ideOtherControl.disabled = true;
		}					
		
		ideControl = document.forms[0].spliceSites_species;
	    ideOtherControl = document.forms[0].spliceSites_otherSpecies;
			
		if( ideControl.value == 'Other' )
			ideOtherControl.disabled = false;
		else {
			ideOtherControl.disabled = true;
		}					
	}
	
	checkOthers();
</SCRIPT>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>



<%@ include file="/jsp/footer.jsp" %>




