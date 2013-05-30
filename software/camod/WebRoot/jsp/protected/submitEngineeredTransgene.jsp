<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

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



<!-- submitEngineeredTransgene.jsp -->
<!-- Main Content Begins -->
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/submitMenu.jsp" %>
<tr><td>
	
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%">
	<tr><td valign="top">
	<html:form action="<%= actionName %>" focus="isRandom" enctype="multipart/form-data">
	<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" align="left">

	<tr>
		<html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>
	
	<tr>
		<td class="formTitle" height="20" colspan="3">Transgene&nbsp; 
			<camod:cshelp topic="transgene_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel">Integration:</td>
			<td class="formField">
				<html:radio styleId="isRandom1" property="isRandom" value="yes" onclick="chkIntegration(this);" /> <label for="isRandom1">Random</label> 
				<html:radio styleId="isRandom2" property="isRandom" value="no" onclick="chkIntegration(this);" /> <label for="isRandom2">Targeted</label>  
			</td>		
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="locationOfIntegration">Location of Integration: *</label><br>(Required field when "Targeted" is selected<br>for the "Transgene Integration" field)
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" disabled="true" styleId="locationOfIntegration" property="locationOfIntegration"  size="10" />
		</td>
	</tr>

	<tr>
		<td class="formTitle" colspan="3">Transgene (coding sequence only):</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel">Transgene:
		</td>
		<td class="formField">
			<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%"><label for="transgeneName">Transgene:</label><br>
						<html:text styleClass="formFieldUnSized" styleId="transgeneName" property="name" size="20" />
					</td>
					<td class="standardText" width="33%"><label for="scientificName">Species of Origin:</label><br>
						<html:select styleClass="formFieldSized" size="1" styleId="scientificName" property="scientificName" onchange="chkName();" >
							<html:optionsCollection name="<%= Dropdowns.SPECIESQUERYDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%"><label for="otherScientificName">Other Species</label><br>
						<html:text styleClass="formFieldSized" size="20" styleId="otherScientificName" property="otherScientificName"   />
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
		<td class="formRequiredLabel">Transcriptional 1:
		</td>
		<td class="formField">
			<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%"><label for="transcriptional1_name">Regulatory Element:</label><br>
						<html:text styleClass="formFieldUnSized" styleId="transcriptional1_name" property="transcriptional1_name" size="20" />
					</td>
					<td class="standardText" width="33%"><label for="transcriptional1_species">Species of Origin:</label><br>
						<html:select styleClass="formFieldSized" size="1" styleId="transcriptional1_species" property="transcriptional1_species" onchange="chkOther_t1();" >
							<html:optionsCollection name="<%= Dropdowns.SPECIESQUERYDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%"><label for="transcriptional1_otherSpecies">Other Species:</label><br>
						<html:text styleClass="formFieldSized" styleId="transcriptional1_otherSpecies" property="transcriptional1_otherSpecies"  size="20" />
					</td>
				</tr>
			</TABLE>		
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel">Transcriptional 2:</td>
		<td class="formField">
			<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%"><label for="transcriptional2_name">Regulatory Element:</label><br>
						<html:text styleClass="formFieldUnSized" styleId="transcriptional2_name" property="transcriptional2_name" size="20" />
					</td>
					<td class="standardText" width="33%"><label for="transcriptional2_species">Species of Origin:</label><br>
						<html:select styleClass="formFieldSized" size="1" styleId="transcriptional2_species" property="transcriptional2_species" onchange="chkOther_t2();" >
							<html:optionsCollection name="<%= Dropdowns.SPECIESQUERYDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%"><label for="transcriptional2_otherSpecies">Other Species:</label><br>
						<html:text styleClass="formFieldSized" styleId="transcriptional2_otherSpecies" property="transcriptional2_otherSpecies"  size="20" />
					</td>
				</tr>
			</TABLE>	
		</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel">Transcriptional 3:</td>
		<td class="formField">
			<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%"><label for="transcriptional3_name">Regulatory Element:</label><br>
						<html:text styleClass="formFieldUnSized" styleId="transcriptional3_name" property="transcriptional3_name" size="20" />
					</td>
					<td class="standardText" width="33%"><label for="transcriptional3_species">Species of Origin:</label><br>
						<html:select styleClass="formFieldSized" size="1" styleId="transcriptional3_species" property="transcriptional3_species" onchange="chkOther_t3();" >
							<html:optionsCollection name="<%= Dropdowns.SPECIESQUERYDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%"><label for="transcriptional3_otherSpecies">Other Species</label><br>
						<html:text styleClass="formFieldSized" styleId="transcriptional3_otherSpecies" property="transcriptional3_otherSpecies"  size="20" />
					</td>
				</tr>
			</TABLE>	
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel">Poly A Signal:
		</td>
		<td class="formField">
			<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%"><label for="polyASignal_name">Regulatory Element:</label><br>
						<html:text styleClass="formFieldUnSized" styleId="polyASignal_name" property="polyASignal_name" size="20" />
					</td>
					<td class="standardText" width="33%"><label for="polyASignal_species">Species of Origin:</label><br>
						<html:select styleClass="formFieldSized" size="1" styleId="polyASignal_species" property="polyASignal_species" onchange="chkOther_PS();" >
							<html:optionsCollection name="<%= Dropdowns.SPECIESQUERYDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%"><label for="polyASignal_otherSpecies">Other Species:</label><br>
						<html:text styleClass="formFieldSized" styleId="polyASignal_otherSpecies" property="polyASignal_otherSpecies"  size="20" />
					</td>
				</tr>
			</TABLE>	
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel">Splice Sites / Intron:</td>
		<td class="formField">
			<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%"><label for="spliceSites_name">Regulatory Element:</label><br>
						<html:text styleClass="formFieldUnSized" styleId="spliceSites_name" property="spliceSites_name" size="20" />
					</td>
					<td class="standardText" width="33%"><label for="spliceSites_species">Species of Origin:</label><br>
						<html:select styleClass="formFieldSized" size="1" styleId="spliceSites_species" property="spliceSites_species" onchange="chkOther_SS();" >
							<html:optionsCollection name="<%= Dropdowns.SPECIESQUERYDROP %>" />										
						</html:select>					
					</td>
					<td class="standardText" width="33%"><label for="spliceSites_otherSpecies">Other Species:</label><br>
						<html:text styleClass="formFieldSized" styleId="spliceSites_otherSpecies" property="spliceSites_otherSpecies"  size="20" />
					</td>
				</tr>
			</TABLE>	
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="geneFunctions">Gene Functions:<br>(separate each entry by a comma)</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" styleId="geneFunctions" property="geneFunctions"  rows="4" cols="32"  />		
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel">Conditional?</td>
		<td class="formField">
			<html:radio styleId="conditionedBy1" property="conditionedBy" value="Conditional" onchange="chkConditional(this);" /> <label for="conditionedBy1">Conditional</label> 
			<html:radio styleId="conditionedBy2" property="conditionedBy" value="Not Conditional" onchange="chkConditional(this);" /> <label for="conditionedBy2">Not Conditional</label>
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="condDescription">Conditional Description:</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" styleId="condDescription" property="description" rows="4" cols="32" />		
		</td>
	</tr>

	<tr>
		<c:if test="${modelspeciescommonname == 'Mouse'}">			
				<td class="formRequiredNotice" width="5">&nbsp;</td>
				<td class="formLabel"><label for="mgiId">MGI Identifier:</label>
				</td>
				<td class="formField">
					<input type=button value="Find MGI ID" onClick="myRef = window.open('http://www.informatics.jax.org/','mywin',
								'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
					&nbsp;&nbsp;
					<html:text styleClass="formFieldUnSized" size="25" styleId="mgiId" property="mgiId"  />&nbsp;&nbsp;<a class="sideMenuLink" href="#" onClick="myRef = window.open('html/disclaimer.html#external','mywin',
										'left=20,top=20,width=800,height=200,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()">Disclaimer</a>
				</td>
		</c:if>				
	</tr>	
	<tr>
		<c:if test="${modelspeciescommonname == 'Zebrafish'}">	
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="zfinId">ZFIN Identifier:</label>
			</td>
			<td class="formField">
				<input type=button value="Find ZFIN ID" onClick="myRef = window.open('http://zfin.org/','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
				&nbsp;&nbsp;
				<html:text styleClass="formFieldUnSized" size="25" styleId="zfinId" property="zfinId"  />
			</td>
		</c:if>
	</tr>	
	
	<tr>
		<c:if test="${modelspeciescommonname == 'Rat'}">	
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="rgdId">RGD Identifier:</label>
			</td>
			<td class="formField">
				<input type=button value="Find RGD ID" onClick="myRef = window.open('http://rgd.mcw.edu/strains/','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
				&nbsp;&nbsp;
				<html:text styleClass="formFieldUnSized" size="25" styleId="rgdId" property="rgdId"  />
			</td>
		</c:if>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="constructSequence">Construct Sequence:</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" styleId="constructSequence" property="constructSequence" rows="4" cols="32"  />	
		</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="comments">Comment:</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" styleId="comments" property="comments"  rows="4" cols="32"  />	
		</td>
	</tr>
	
	<tr>
		<td class="formTitle" colspan="3">&nbsp;</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="fileLocation">Upload Construct Map<br>(Image of type .jpg, .jpeg, .gif or .png):</label></td>
		<td class="formField">
		
			<c:if test="${not empty engineeredTransgeneForm.url}">
				Current Image Thumbnail: <br>
					
				<a href='<c:out value="${engineeredTransgeneForm.imageUrl}"/>'>						
				<img src="<c:out value="${engineeredTransgeneForm.thumbUrl}"/>" height="40" width="40" alt="<c:out value="${engineeredTransgeneForm.url}"/>" target="_blank">				
				Click to View</a>
				<br><br>													
			</c:if>

			<html:file styleClass="formFieldSized" size="40" styleId="fileLocation" property="fileLocation" />			
		</td>			
	</tr>		

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="constructTitle">Title of Construct:<br>(Enter info only when uploading image)</label></td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" styleId="constructTitle" property="title" rows="4" cols="32" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="descriptionOfConstruct">Description of Construct:<br>(Enter info only when uploading image)</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" styleId="descriptionOfConstruct" property="descriptionOfConstruct" rows="4" cols="32"  />	
		</td>
	</tr>
	
	
	<tr>
		<td align="right" colspan="3">
			<TABLE summary="This table is used to format page content" cellpadding="4" cellspacing="0" border="0">
			
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
	</html:form>
	</td></tr></TABLE>
</td></tr></TABLE>



<%@ include file="/jsp/footer.jsp" %>




