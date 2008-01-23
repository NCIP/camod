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

<html:form action="<%= (String) pageContext.getAttribute("actionName") %>" focus="name">

<!-- submitPublications.jsp -->
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
		<td class="formTitle" height="20" colspan="3">Publications &nbsp;
			<camod:cshelp topic="publication_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel">
			<label for="field1">First Author:</label>						
		</td>
		
		<td class="formField">
			<label>(e.g. Doe JR)</label>
			<br>
			<html:text styleClass="formFieldSized" size="30" property="authors" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Publication Status:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldUnSized" size="1" property="name" >												
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
			<html:radio property="firstTimeReported" value="yes" /> Yes 
			<html:radio property="firstTimeReported" value="no" /> No  
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
			<label valign="TOP" for="field1"><a href="#" onClick="myRef = window.open('http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?db=PubMed','mywin',
			'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()">Click to look up PubMed Identifier</a></label>
			<br><br><br>
			<label valign="TOP" for="field1">PMID: &nbsp;</label>
			<br>
			<html:text styleClass="formFieldUnSized" size="20" property="pmid" />
			<html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" >Fill in Fields</html:submit>		
		</td>
	</tr>

	<c:choose>
		<c:when test="${modelspeciescommonname == 'Zebrafish'}">	
			<tr>
					<td class="formRequiredNotice" width="3">&nbsp;</td>
					<td class="formLabel"><label for="field1">ZFIN number:</label>
					</td>
					<td class="formField">		
						<input type=button value="Find ZFIN ID" onClick="myRef = window.open('http://zfin.org/cgi-bin/webdriver?MIval=aa-pubselect2.apg&select_from=PUBLICATION','mywin',
						'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
						<label for="field1">&nbsp;&nbsp;</label>
						<html:text styleClass="formFieldUnSized" size="20" property="zfinPubId" />
					</td>
			</tr>
		</c:when>
		<c:otherwise>
			<tr>
					<td class="formRequiredNotice" width="3">&nbsp;</td>
					<td class="formLabel"><label for="field1">J number:</label>
					</td>
					<td class="formField">		
						<input type=button value="Find J #" onClick="myRef = window.open('http://www.informatics.jax.org/searches/reference_form.shtml','mywin',
						'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
						<label for="field1">&nbsp;&nbsp;</label>
						<html:text styleClass="formFieldUnSized" size="20" property="jaxJNumber" />
					</td>
			</tr>		
		</c:otherwise>
	</c:choose>		

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Title of Publication:<br/><br/>
			Note: Either the PMID or the Title of Publication must be entered.</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" size="50" property="title" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Year of Publication:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" size="10" property="year" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Journal:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" size="30" property="journal" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Volume:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" size="10" property="volume" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Start Page:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" size="10" property="startPage" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">End Page:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" size="10" property="endPage" />
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Comment:</label>
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized" property="comments" cols="32" rows="4"/>			
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
  				  
  				  <c:if test="${not empty publicationForm.APubID}">
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
			      </c:if>
			      
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <html:hidden property="APubID" />
  				  <html:hidden property="ACellID" />
  				  <html:hidden property="ATherapyID" />
 
 			  </html:form>			
			</TABLE>
			
		</td></tr></TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>