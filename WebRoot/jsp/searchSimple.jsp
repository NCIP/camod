<%

/**
 * 
 * $Id: searchSimple.jsp,v 1.35 2006-11-06 16:15:16 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.34  2006/08/16 13:54:35  pandyas
 * updated on-line help from Robohelp to ePublisher - added new link for simple search title
 *
 * Revision 1.33  2006/08/15 15:28:54  pandyas
 * updated on-line help from Robohelp to ePublisher - modified link - new data tree link added
 *
 * Revision 1.32  2006/08/13 17:43:20  pandyas
 * Updated online help - redefined camod tag by substituting mapId for topic (ePublisher changes)
 *
 * Revision 1.31  2006/05/18 14:26:55  guptaa
 * fix style id
 *
 * Revision 1.30  2006/05/17 21:15:29  guptaa
 * organ tree changes
 *
 * Revision 1.29  2006/05/15 19:52:23  georgeda
 * Fixed bugs introduced putting in Ajax
 *
 * Revision 1.28  2006/05/12 20:42:06  guptaa
 * deleted css
 *
 * Revision 1.27  2006/05/12 20:29:00  guptaa
 * indicator out
 *
 * Revision 1.26  2006/05/12 19:25:37  guptaa
 * uses tag library
 *
 * Revision 1.25  2006/05/12 17:11:22  guptaa
 * ajax additions
 *
 * Revision 1.24  2006/05/10 14:22:59  schroedn
 * New Features - Changes from code review
 *
 * Revision 1.23  2006/05/03 19:05:39  georgeda
 * Move to new EVSTree
 *
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

<%@ page import="gov.nih.nci.camod.service.SavedQueryManager" %>
<%@ page import="gov.nih.nci.camod.domain.SavedQuery" %>	
<%@ page import="gov.nih.nci.camod.domain.SavedQueryAttribute" %>	

<%@ page import='gov.nih.nci.camod.Constants.*' %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>
<SCRIPT language="JavaScript" src="scripts/global.js"></script>
<SCRIPT language="JavaScript" src="scripts/EVSTreeScript.js"></script>
<script type="text/javascript" src="js/prototype-1.4.0.js"></script>
<script type="text/javascript" src="js/scriptaculous.js"></script>
<script type="text/javascript" src="js/ajaxtags-1.2-beta2.js"></script>


<SCRIPT LANGUAGE="JavaScript">	
	function blankKeyword() {
        document.searchForm.keyword.value = '';
    }
	
	function enableOrgan() {
		document.searchForm.organ.disabled = false;
	}			
</SCRIPT>

<%
	//Display search criteria of executed search		
	String aSavedQueryId = (String) request.getSession().getAttribute( Constants.ASAVEDQUERYID );		
	String aQueryName = (String) request.getSession().getAttribute( Constants.QUERY_NAME );		
%>

<html:form action="SearchSimpleAction.do" focus="keyword" onsubmit="transferFields()">

<!-- searchSimple.jsp -->
<!-- Main Content Begins -->
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
			<td class="formTitleBlue" height="20" colspan="3">Simple Search
				<camod:cshelp topic="simple_search_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
			<!-- <td class="formMessage" align="left" FONT="9"><a href="advancedsearch.html">Advanced Search <a> </td> -->
		</tr>
		
		<tr>
		    <td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Model Name / Model Descriptor  
				<camod:cshelp topic="skip" key="SEARCH.MODEL_DESCRIPTOR" image="images/iconHelp.gif" text="Tool Tip Test 1" />
				</label>
			</td>
			<td class="formField">			
				<html:text styleClass="formFieldSized" styleId="modelDescriptor" property="modelDescriptor" size="30"/>
				<ajax:autocomplete baseUrl="/camod/autocomplete.view" source="modelDescriptor" target="modelDescriptor"
  				parameters="modelDescriptor={modelDescriptor}" className="autocomplete" minimumCharacters="1" />	
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
				<html:hidden styleId="organTissueName" property="organTissueName"/>
		 		<html:hidden styleId="organTissueCode" property="organTissueCode"/>
				<label for="field2">Site of Lesion/Tumor</label>
				&nbsp;
				<camod:cshelp topic="data_tree_help" key="SEARCH.SITE_OF_TUMOR" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		  	    <a href="javascript:showMouseTissueTree('searchForm', 'organTissueCode', 'organTissueName', 'organ', false)">
				<IMG src="images\selectUP.gif" align=middle border=0>
				</a>
			</td>
			<td class="formField">
				<html:text styleClass="formFieldSized" styleId="organ" property="organ" size="25"/>
				<ajax:autocomplete baseUrl="/camod/autocomplete.view" source="organ" target="organTissueCode"
  				parameters="organTissueCode={organTissueCode}" className="autocomplete" minimumCharacters="1" />	
			
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

<%@ include file="/jsp/footer.jsp" %>