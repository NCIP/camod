<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page buffer="32kb"%>
<%@ page import="gov.nih.nci.camod.domain.TargetedModification" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.TargetedModificationForm" %>	
<%@ page import="gov.nih.nci.camod.Constants.*" %>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.Constants.Dropdowns" %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<script language="JavaScript" src="scripts/global.js"></script>

<%
	String aTargetedModificationID = (String) request.getAttribute( "aTargetedModificationID" );
			
	//if aInducedMutationID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "TargetedModificationAction.do?method=save";
	
	if ( aTargetedModificationID != null && aTargetedModificationID.length() > 0) {
		actionName = "TargetedModificationAction.do?method=edit";
	}
	else {
	    aTargetedModificationID = "";
	}
%>

<SCRIPT LANGUAGE="JavaScript">
	
	function chkTypeMod() {
	    chkOther(document.forms[0].modificationType, document.forms[0].otherModificationType);
	}
	
	function chkConditional() {
			
	    if (document.forms[0].conditionedBy[0].checked == true) {
	        enableField(document.forms[0].description);
	    }
        else {
            disableField(document.forms[0].description);
        }
	}
	
</SCRIPT>

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
		<td class="formTitle" height="20" colspan="3">Targeted Modification</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Targeted Gene/Locus:</label>
		<camod:cshelp mapId="targeted_modification_help" key="ENGINEERED_GENE.NAME_TARGETEDMODIFICATION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:form action="<%= actionName %>" focus="name" enctype="multipart/form-data">	
			
			<html:text styleClass="formFieldSized" property="name" size="10" />		
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Type of Modification:</label>
		<camod:cshelp mapId="targeted_modification_help" key="MODIFICATION_TYPE.NAME" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:select styleClass="formFieldSized" size="1" property="modificationType" onchange="chkTypeMod( this );" >
				<html:options name="<%= Dropdowns.TARGETEDMODIFICATIONDROP %>" />										
			</html:select>
			<br>
			-if category you are looking for is not listed, <br>select "Other" and enter the category in the text field below:
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other modification type:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="otherModificationType" disabled="true" size="10" />	
		</td>
	</tr>

	<tr>
               
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Gene ID ( Entrez ):</label>
		<camod:cshelp mapId="targeted_modification_help" key="ENGINEERED_GENE.GENE_ID" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<input type=button value="Find Gene ID" onClick="myRef = window.open('http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?db=gene','mywin',
			'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"></input>		
			<html:text styleClass="formFieldUnSized" property="geneId" size="20" />	
	</tr>


	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel">Genetic Background:</td>
		<td class="formField">
			<label valign="TOP" for="field1">ES Cell Line: &nbsp;</label><br>
				<html:text styleClass="formFieldSized" property="esCellLineName" size="10" />
			<br>
			<br>
			<label valign="TOP" for="field1">Blastocyst:&nbsp;</label><br>
				<html:text styleClass="formFieldSized" property="blastocystName" size="10" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel">Conditional?</td>
		<td class="formField">
			<html:radio property="conditionedBy" value="Conditional" onchange="chkConditional(this);" /> Conditional 
			<html:radio property="conditionedBy" value="Not Conditional" onchange="chkConditional(this);" /> Not Conditional
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Conditional Description</label>
			<camod:cshelp mapId="targeted_modification_help" key="CONDITIONALITY.DESCRIPTION" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="description" disabled="true" rows="4" cols="32" />		
		</td>
	</tr>



	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">MGI Number</label>
		<camod:cshelp mapId="targeted_modification_help" key="MUTATION_IDENTIFIER.NUMBER_MGI" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<input type=button value="Find MGI #" onClick="myRef = window.open('http://www.informatics.jax.org/','mywin',
			'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"></input>		
			<html:text styleClass="formFieldUnSized" property="numberMGI" size="20" />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Comment:</label>
		<camod:cshelp mapId="targeted_modification_help" key="ENGINEERED_GENE.COMMENTS" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
		    <html:textarea styleClass="formFieldSized" property="comments" rows="4" cols="32" />
		</td>
	</tr>
	<tr>
		<td class="formTitle" height="20" colspan="6">&nbsp;</td>				
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Upload Construct Map (Image):</label></td>
		<td class="formField">
					
			<c:if test="${not empty targetedModificationForm.fileServerLocation}">
			
				<c:set var="uri" value="javascript: rs('commentWin','viewLizardImage.do?aFileServerLocation=${targetedModificationForm.fileServerLocation}',600,600);"/>
			
				Current Image: <c:out value="${targetedModificationForm.fileServerLocation}"/><br>
				Current Image Thumbnail: <br>
					
				<a href='<c:out value="${uri}"/>'>			
				
				<img src="http://caimage.nci.nih.gov/lizardtech/iserv/getthumb?cat=Model&amp;img=<c:out value="${targetedModificationForm.fileServerLocation}"/>&amp;thumbspec=" main="" alt="<c:out value="${targetedModificationForm.fileServerLocation}"/>" target="_blank">				
				Click to View</a><br><br>									
		    </c:if>			
			<html:file styleClass="formFieldSized" size="40" property="fileLocation" />	
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Title of Construct <br>(enter info only when uploading image)</label></td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="title" rows="4" cols="30" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Description of Construct<br>(enter info only when uploading image)</label></td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" property="descriptionOfConstruct"  rows="4" cols="30"  />
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

  				  <c:if test="${not empty aTargetedModificationID}">
	  				  <html:submit property="action" styleClass="actionButton" onclick="confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
			      </c:if>
			      
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <input type="hidden" name="aTargetedModificationID" value="<%= aTargetedModificationID %>">
				  	
				</html:form>			
			</TABLE>
		</td>
	</tr>
</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<SCRIPT>
chkTypeMod();
chkConditional();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>