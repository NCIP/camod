<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page buffer="32kb"%>
<%@ page import="gov.nih.nci.camod.domain.TargetedModification" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.TargetedModificationForm" %>	
<%@ page import="gov.nih.nci.camod.Constants.*" %>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.Constants.Dropdowns" %>

<%@ page import="gov.nih.nci.camod.Constants.CaImage" %>
<%@ page import="java.util.*" %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<script language="JavaScript" src="scripts/global.js"></script>

<%	
	TargetedModificationForm form = (TargetedModificationForm) request.getAttribute( "targetedModificationForm" );
	
	String aTargetedModificationID = form.getModificationId();
	String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
			
	//if aInducedMutationID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "TargetedModificationAction.do?method=save";
	
	if ( aTargetedModificationID != null && aTargetedModificationID.length() > 0 && isDeleted == null) {
		actionName = "TargetedModificationAction.do?method=edit";
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

<!-- submitTargetedModification.jsp -->
<!-- Main Content Begins -->
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/submitMenu.jsp" %>
<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%">
	<tr><td valign="top">	

	<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" align="left">
	<html:form action="<%= actionName %>" focus="name" enctype="multipart/form-data">
	<tr>
		<html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>

	<tr>
		<td class="formTitle" height="20" colspan="3">Targeted Modification
			<camod:cshelp topic="targeted_modification_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="tgName">Targeted Gene/Locus:</label>
		</td>
		<td class="formField">			
			<html:text styleClass="formFieldSized" styleId="tgName" property="name" size="10" />		
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="modificationType">Type of Modification:</label>
		<td class="formField">
			<html:select styleClass="formFieldSized" multiple="true" size="5" styleId="modificationType" property="modificationType" onchange="chkTypeMod( this );" >
				<html:options name="<%= Dropdowns.TARGETEDMODIFICATIONDROP %>" />										
			</html:select>
			<br><br>
			-If the category you are looking for is not listed, <br>select "Other" and enter the category in the text field below.
			<br>
			-To select multiple modification types using a PC, <br>hold down the 'Control Key' while clicking the option with <br>the mouse.  Use the 'Command Key' if you are using a Macintosh.
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="otherModificationType">Other modification type:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" styleId="otherModificationType" property="otherModificationType"  size="10" />	
		</td>
	</tr>

	<tr>
               
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="geneIdentifier">Entrez Gene ID:</label>
		</td>
		<td class="formField">
			<input type=button value="Find Gene ID" onClick="myRef = window.open('http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?db=gene','mywin',
			'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>		
			&nbsp;&nbsp;
			<html:text styleClass="formFieldUnSized" styleId="geneIdentifier" property="geneIdentifier" size="20" />	
	</tr>


	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel">Genetic Background:</td>
		<td class="formField">
			<label valign="TOP" for="esCellLineName">ES Cell Line: &nbsp;</label><br>
				<html:text styleClass="formFieldSized" styleId="esCellLineName" property="esCellLineName" size="10" />
			<br>
			<br>
			<label valign="TOP" for="blastocystName">Blastocyst:&nbsp;</label><br>
				<html:text styleClass="formFieldSized" styleId="blastocystName" property="blastocystName" size="10" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel">Conditional?</td>
		<td class="formField">
			<html:radio styleId="conditionedBy1" property="conditionedBy" value="Conditional" onclick="chkConditional(this);" /> <label for="conditionedBy1">Conditional</label> 
			<html:radio styleId="conditionedBy2" property="conditionedBy" value="Not Conditional" onclick="chkConditional(this);" /> <label for="conditionedBy2">Not Conditional</label>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="condDesc">Conditional Description:</label>
		</td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" styleId="condDesc" property="description"  rows="4" cols="32" />		
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
		    <html:textarea styleClass="formFieldSized" styleId="comments" property="comments" rows="4" cols="32" />
		</td>
	</tr>
	<tr>
		<td class="formTitle" height="20" colspan="6">&nbsp;</td>				
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="fileLocation">Upload Construct Map<br>(Image of type .jpg, .jpeg, .gif or .png):</label></td>
		<td class="formField">
				
			<c:if test="${not empty targetedModificationForm.url}">
				Current Image Thumbnail: <br>
					
				<a href='<c:out value="${targetedModificationForm.imageUrl}"/>'>						
				<img src="<c:out value="${targetedModificationForm.thumbUrl}"/>" height="40" width="40" alt="<c:out value="${targetedModificationForm.url}"/>" target="_blank">				
				Click to View</a>
				<br><br>													
			</c:if>
					
			<html:file styleClass="formFieldSized" size="40" styleId="fileLocation" property="fileLocation" />	
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="constructTitle">Title of Construct: <br>(enter info only when uploading image)</label></td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" styleId="constructTitle" property="title" rows="4" cols="30" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="descriptionOfConstruct">Description of Construct:<br>(enter info only when uploading image)</label></td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" styleId="descriptionOfConstruct" property="descriptionOfConstruct"  rows="4" cols="30"  />
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

  				  <c:if test="${not empty targetedModificationForm.modificationId}">
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
			      </c:if>
			      
				  <html:hidden property="modificationId"/>
				  
							
			</TABLE>
		</td>
	</tr>
	</html:form>
</TABLE>

<!-- -->
	</td></tr></TABLE>
</td></tr></TABLE>

<SCRIPT>
chkTypeMod();
chkConditional();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>