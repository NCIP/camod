<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page buffer="32kb"%>
<%@ page import="gov.nih.nci.camod.webapp.form.PublicationForm" %>
<%@ page import="gov.nih.nci.camod.webapp.form.PublicationData" %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>
<%@ page import='gov.nih.nci.camod.Constants.Dropdowns.*' %>

<!-- Needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>
<SCRIPT src="/camod/scripts/PublicationManager.js" type=text/javascript></SCRIPT>
<script type='text/javascript'
	src='/camod/dwr/interface/PublicationManager.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>	

<script type="text/javascript">
<!--

function trim(str)
{
    // str. remove whitespaces from left. remove whitespaces from right
    return str.replace(/^\s+/g, "").replace(/\s+$/g, "");
}

function trimRGDId(str)
{
	str = str.replace(/RGD+/g, "").replace(/rgd+/g, "");
	str = str.replace(/Rgd+/g, "").replace(/:+/g, "");
	return str.replace(/^\s+/g, "").replace(/\s+$/g, "");
}

//-->
</script>

<c:set var="actionName" value="PublicationAction.do?method=save" />

<c:if test="${not empty publicationForm.APubID}">
	<c:set var="actionName" value="PublicationAction.do?method=edit" />
</c:if>

<c:if test="${empty publicationForm.APubID && not empty publicationForm.ACellID}">
	<c:set var="actionName" value="PublicationAction.do?method=addCellLinePublication&aCellID=${publicationForm.ACellID}"/>
</c:if>

<c:if test="${empty publicationForm.APubID && not empty publicationForm.ATherapyID}">
	<c:set var="actionName" value="PublicationAction.do?method=addTherapyPublication&aTherapyID=${publicationForm.ATherapyID}" />
</c:if>



<!-- submitPublications.jsp -->
<!-- Main Content Begins -->
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/submitMenu.jsp" %>
<tr><td>
	
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%">
	<tr><td valign="top">

	<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" align="left">
	<html:form action="<%= (String) pageContext.getAttribute("actionName") %>" focus="name" enctype="multipart/form-data">
	<tr>
		<html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>

	<tr>
		<td class="formTitle" height="20" colspan="3">Publications &nbsp;
			<camod:cshelp topic="publication_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel">
			<label for="authors">First Author:</label>						
		</td>
		
		<td class="formField">
			(e.g. Doe JR)
			<br>
			<html:text styleClass="formFieldSized" size="30" styleId="authors" property="authors" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="pubName">Publication Status:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldUnSized" size="1" styleId="pubName" property="name" >												
				<html:options name="<%= Dropdowns.PUBDROP %>"/>					
			</html:select>				
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel">
				Is this the publication in which the model<br>
				was reported for the first time?
		</td>
		<td class="formField">
			<html:radio styleId="firstTimeReported1" property="firstTimeReported" value="yes" /> <label for="firstTimeReported1">Yes</label> 
			<html:radio styleId="firstTimeReported2" property="firstTimeReported" value="no" /> <label for="firstTimeReported2">No</label>  
		</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel">
			For publications with a PubMed record, <br>
			look up the Pubmed Identifier (PMID number),<br>
			enter it in the PMID field and <br>
			click the "Fill in Fields" button. <br>
			The program will retrieve the citation <br>
			data from PubMed and populate the fields<br>
			automatically. Click "Submit"<br>
			to submit the publication to the database.
			<br/>
			<br/>
			Note: Either the PMID or the Title of Publication must be entered.
		</td>		
		
		<td class="formField">
			<a href="#" onClick="myRef = window.open('http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?db=PubMed','mywin',
			'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()">Click to look up PubMed Identifier</a>
			<br><br><br>
			<label valign="TOP" for="pmid">PMID: &nbsp;</label>
			<br>
			<html:text styleClass="formFieldUnSized" size="20" styleId="pmid" property="pmid" />
			<html:button property="<%=Constants.Parameters.ACTION%>" onclick="javascript:fillPubMedInfo()" >Fill in Fields</html:button>		
		</td>
	</tr>

	<c:choose>
		<c:when test="${modelspeciescommonname == 'Zebrafish'}">	
			<tr>
					<td class="formRequiredNotice" width="3">&nbsp;</td>
					<td class="formLabel"><label for="zfinPubId">ZFIN number:</label>
					</td>
					<td class="formField">		
						<input type=button value="Find ZFIN ID" onClick="myRef = window.open('http://zfin.org/cgi-bin/webdriver?MIval=aa-pubselect2.apg&select_from=PUBLICATION','mywin',
						'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
						&nbsp;&nbsp;
						<html:text styleClass="formFieldUnSized" size="20" styleId="zfinPubId" property="zfinPubId" />
					</td>
			</tr>
		</c:when>
		<c:when test="${modelspeciescommonname == 'Rat'}">	
			<tr>
					<td class="formRequiredNotice" width="3">&nbsp;</td>
					<td class="formLabel"><label for="rgdPubId">RGD number:<br><br>Note: No special characters permitted in this field. Example RGD:1599131 should instead be 1599131</label>
					</td>
					<td class="formField">		
						<input type=button value="Find RGD #" onClick="myRef = window.open('http://rgd.mcw.edu/rgdweb/search/references.html','mywin',
						'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
						&nbsp;&nbsp;
						<html:text styleClass="formFieldUnSized" size="20" styleId="rgdPubId" property="rgdPubId" />
					</td>
			</tr>
		</c:when>
		<c:otherwise>
			<tr>
					<td class="formRequiredNotice" width="3">&nbsp;</td>
					<td class="formLabel"><label for="jaxJNumber">J number:</label>
					</td>
					<td class="formField">		
						<input type=button value="Find J #" onClick="myRef = window.open('http://www.informatics.jax.org/searches/reference_form.shtml','mywin',
						'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
						&nbsp;&nbsp;
						<html:text styleClass="formFieldUnSized" size="20" styleId="jaxJNumber" property="jaxJNumber" />&nbsp;&nbsp;<a class="sideMenuLink" href="html/disclaimer.html" target="new">Disclaimer</a>
					</td>
			</tr>		
		</c:otherwise>
	</c:choose>		

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="jTitle">Title of Publication:<br/><br/>
			Note: Either the PMID or the Title of Publication must be entered.</label></td>
		<td class="formField">
			<html:textarea styleClass="formFieldSized" styleId="jTitle" property="title" cols="32" rows="4"/>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="year">Year of Publication:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" size="10" styleId="year" property="year" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="journal">Journal:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" size="30" styleId="journal" property="journal" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="volume">Volume:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" size="10" styleId="volume" property="volume" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="startPage">Start Page:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" size="10" styleId="startPage" property="startPage" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="endPage">End Page:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" size="10" styleId="endPage" property="endPage" />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="comments">Comment:</label>
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized" styleId="comments" property="comments" cols="32" rows="4"/>			
			</td>
	</tr>		

	<tr>
		<td align="right" colspan="3">
			<TABLE summary="This table is used to format page content" cellpadding="4" cellspacing="0" border="0">
			
				  <html:submit styleClass="actionButton" onclick="javascript:pmid.value=trim(pmid.value)">
					  <bean:message key="button.submit"/>
				  </html:submit>
				  
				  <html:reset styleClass="actionButton">
				  	  <bean:message key="button.reset"/>
  				  </html:reset>
  				  
  				  <c:if test="${not empty publicationForm.APubID}">
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
			      </c:if>
			      
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <html:hidden property="APubID" />
  				  <html:hidden property="ACellID" />
  				  <html:hidden property="ATherapyID" />
 
 			  			
			</TABLE>
			</html:form>
		</td></tr></TABLE>
	</td></tr></TABLE>
</td></tr></TABLE>

<%@ include file="/jsp/footer.jsp" %>