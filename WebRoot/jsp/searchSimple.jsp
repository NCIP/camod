<%

/**
 * 
 * $Id: searchSimple.jsp,v 1.23 2006-05-03 19:05:39 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.22  2006/04/28 19:39:56  schroedn
 * Defect # 261
 * Made changes so the organ and diagnosis save differently and can be retained for SaveQuery
 *
 *
 */

%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.service.QueryStorageManager" %>
<%@ page import="gov.nih.nci.camod.domain.SavedQuery" %>	
<%@ page import="gov.nih.nci.camod.domain.SavedQueryAttribute" %>	

<%@ page import='gov.nih.nci.camod.Constants.*' %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>
<SCRIPT language="JavaScript" src="scripts/global.js"></script>
<SCRIPT language="JavaScript" src="scripts/EVSTreeScript.js"></script>

<SCRIPT>
    function blankKeyword() {
        document.searchForm.keyword.value = '';
    }
</SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
	
	function blankKeyword() {
        document.searchForm.keyword.value = '';
    }
	
	function checkFields() {
	
	    // Do the CI fields
		theCIFlag = document.searchForm.searchCarcinogenicInterventions[0];
		toggleField(theCIFlag, document.searchForm.chemicalDrug);
		toggleField(theCIFlag, document.searchForm.growthFactor);
		toggleField(theCIFlag, document.searchForm.hormone);
		toggleField(theCIFlag, document.searchForm.radiation);
		toggleField(theCIFlag, document.searchForm.viral);
		toggleField(theCIFlag, document.searchForm.surgery);
		
		theTargModFlag = document.searchForm.targetedModification[0];
		theEndTransGeneFlag = document.searchForm.engineeredTransgene[0];
		
		if (theTargModFlag.checked == true || theEndTransGeneFlag.checked == true) {
		    enableField(document.searchForm.geneName);
		}
		else {
		    disableField(document.searchForm.geneName);
		}
		
		toggleField(document.searchForm.searchTherapeuticApproaches[0], document.searchForm.therapeuticApproach);
	}
	
	function enableOrgan() {
		document.searchForm.organ.disabled = false;
	}
			
</SCRIPT>

<%
	//Display search criteria of executed search
	//SavedQuery inQuery = (SavedQuery) request.getSession().getAttribute( Constants.SEARCH_QUERY );		
	String aSavedQueryId = (String) request.getSession().getAttribute( Constants.ASAVEDQUERYID );		
	String aQueryName = (String) request.getSession().getAttribute( Constants.QUERY_NAME );		
%>

<html:form action="SearchSimpleAction.do" focus="keyword" onsubmit="transferFields()">

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">
        <tr>
            <td class="formTitleBlue" height="20" colspan="3">
                Keyword Search:&nbsp;&nbsp;
                <html:text styleClass="formFieldSized" property="keyword" size="45"/>
                &nbsp;&nbsp;
                <input class="actionButton" type="submit" value="Search" />
            </td>
        </tr>
        
        <tr>
        	<td colspan="3">        
		        <% if( aSavedQueryId != null ) { %>
			        <br>
					<TABLE border="0" class="contentPage" width="100%">
						<TR>
							<TD width="100%">
								<font color="red">* Saved query <b>"<%= aQueryName %>"</b> is being edited. You will be prompted to save the changes after pressing Search.</font>
				            </TD>
				        </TR>
			        </TABLE>				
		        <%}%>
        	</td>
        </tr>
        
                
		<tr>
			<td class="formTitleBlue" height="20" colspan="3">Simple Search</td>
			<!-- <td class="formMessage" align="left" FONT="9"><a href="advancedsearch.html">Advanced Search <a> </td> -->
		</tr>
		
		<tr>
		    <td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Model Name / Model Descriptor  
				<camod:cshelp mapId="simple_search_help" key="SEARCH.MODEL_DESCRIPTOR" image="images/iconHelp.gif" text="Tool Tip Test 1" />
				</label>
			</td>
			<td class="formField">			
					<html:text styleClass="formFieldSized" property="modelDescriptor" size="30"/>
			</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formLabel"><label for="field2">Principal Investigator's Name</label></td>
			
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="piName" >
					<html:options name="<%= Dropdowns.PRINCIPALINVESTIGATORQUERYDROP %>" />										
				</html:select>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formLabel">
				<html:hidden property="organTissueName"/>
		 		<html:hidden property="organTissueCode"/>
				<label for="field2">Site of Lesion/Tumor</label>
				&nbsp;
				<camod:cshelp mapId="simple_search_help" key="SEARCH.SITE_OF_TUMOR" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		  	    <a href="javascript:showMouseTissueTree('searchForm', 'organTissueCode', 'organTissueName', 'organ', false)">
				<IMG src="images\selectUP.gif" align=middle border=0>
				</a>
			</td>
			<td class="formField">
				<html:text styleClass="formFieldSizedDisabled" disabled="true" property="organ" size="25"/>	
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field3">Species</label></td>
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="species" >
					<html:optionsCollection name="<%= Dropdowns.NEWSPECIESDROP %>" />										
				</html:select>			
			</td>
		</tr>
		
		<tr>
			<td colspan="3" align="right">	
				<TABLE cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td align="right">
						  <html:submit styleClass="actionButton" onclick="blankKeyword()">
							  Search
						  </html:submit>
							  
						  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton">
						  	  <bean:message key="button.clear"/>
						  </html:submit>
						  
				  		</html:form>			
				  		</td>
			  		</tr>
				</TABLE>
			</td>
		</tr>
				
		</TABLE>		
		</td></tr>
				
</td></tr>
</TABLE>

<SCRIPT LANGUAGE="JavaScript">
    checkFields();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>