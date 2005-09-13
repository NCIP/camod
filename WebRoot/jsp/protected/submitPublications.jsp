<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page import="gov.nih.nci.camod.webapp.form.PublicationForm" %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>
<%@ page import='gov.nih.nci.camod.Constants.Dropdowns.*' %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>	

<%
	String aPubID = request.getParameter( "aPubID" );
	
	//if aTherapyID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "PublicationAction.do?method=edit";
	
	if ( aPubID == null || aPubID.equals( "null" ) ){
		actionName = "PublicationAction.do?method=save";
	}		
%>

<SCRIPT LANGUAGE="JavaScript">

	function getPubMed( control ) {
		form = control.form;
		form.action = "PubMedPopulateAction.do?pmid=";
		form.action += document.forms[0].pmid.value;
		form.submit();
	}	
	
</SCRIPT>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">
	
	<tr>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>

	<tr>
		<td class="formTitle" height="20" colspan="3">Publications</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel">
			<label for="field1">First Author:</label>
			<html:form action="<%= actionName %>" focus="name">	
		</td>
		
		<td class="formField">
			<label>(e.g. Doe JR)</label>
			<br>
			<html:text styleClass="formFieldSized" size="30" property="authors" name="formdata"/>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Publication Status:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldUnSized" size="1" property="name" name="formdata">												
				<html:options name="<%= Dropdowns.PUBDROP %>"/>					
			</html:select>				
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Is this the publication in which the model was reported for the first time?</label></td>
		<td class="formField">
			<html:radio property="firstTimeReported" value="yes" /> Yes 
			<html:radio property="firstTimeReported" value="no" /> No  
		</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel2">
			For publications with a PubMed record, <br>look up the Pubmed Identifier (PMID number),<br> enter it in the PMID field and <br>click the "Fill in Fields" button. <br>The program will retrieve the citation <br>data from PubMed and populate the fields<br> automatically. Click "Save Data"<br>to submit the publication to the database.
		</td>
		
		<td class="formField">
			<label valign="TOP" for="field1"><a href="#">Click to look up PubMed Identifier</a></label><br>
			<br>
			<br>
			<label valign="TOP" for="field1">PMID &nbsp;</label>
			<br>
			<html:text styleClass="formFieldUnSized" size="20" property="pmid" name="formdata" />
			<html:button property="none" onclick="getPubMed(this)">Fill in Fields</html:button>
			
			<br>			
	            <logic:messagesPresent>
				      <html:messages id="overview">
				        <%=overview %>
				      </html:messages>
				</logic:messagesPresent>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Title of Publication</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" size="30" property="title" name="formdata"/>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Year of Publication:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" size="10" property="year" name="formdata"/>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Journal:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" size="20" property="journal" name="formdata"/>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Volume:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" size="10" property="volume" name="formdata"/>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Start Page:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" size="10" property="startPage" name="formdata"/>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">End Page:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" size="10" property="endPage" name="formdata"/>
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
				  <input type="hidden" name="aPubID" value="<%= aPubID %>">
			  
			  </html:form>			
			</TABLE>
			
		</td></tr></TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>