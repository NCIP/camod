<%

/**
 * 
 * $Id: savedSearchQueries.jsp,v 1.5 2006-08-15 14:52:37 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2006/08/13 18:21:23  pandyas
 * updated on-line help from Robohelp to ePublisher - modified links
 *
 * Revision 1.3  2006/05/25 15:19:17  georgeda
 * Added new context sensitive text topics
 *
 * Revision 1.2  2006/05/10 14:23:35  schroedn
 * New Features - Changes from code review
 *
 * Revision 1.1  2006/04/28 19:41:32  schroedn
 * Defect # 261, 238
 * Pages to edit user options, saved queries or query history
 *
 *
 */

%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<%@ page import="gov.nih.nci.camod.service.SavedQueryManager" %>
<%@ page import="gov.nih.nci.camod.domain.SavedQuery" %>	
<%@ page import="gov.nih.nci.camod.domain.SavedQueryAttribute" %>	


<%  
	List userQueryList = (List) request.getSession().getAttribute( Constants.USERSAVEDQUERYLIST );
	
	int size = 0;
	if( userQueryList != null)
	{
		size = userQueryList.size();	
	}
	
	int menuNumber = 1;
%>


<!-- savedSearchQueries.jsp -->
<!-- Main Content Begins -->
<DIV id="masterdiv">
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	
	<logic:messagesPresent>
	  <b>
	  <font color="red">
		  <html:messages id="errors">
			<%=errors %>
		  </html:messages>
	  </font>
	  </b>  
	</logic:messagesPresent>
		
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" width="100%">	

		<tr>
			<td class="formTitleBlue" height="20" colspan="6">Saved Queries&nbsp;
				<camod:cshelp topic="saving_search_query_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>				
		</tr>

		<tr>
			<td class="greySubTitleLeft" width="40%">Query Name</td>
			<td class="greySubTitleLeft" width="15%">Last Executed</td>
			<td class="greySubTitleLeft" width="5%">Results</td>
			<td class="greySubTitleLeft" width="20%">Resubmit Query</td>
			<td class="greySubTitleLeft" width="15%">Edit Query</td>
			<td class="greySubTitle" width="5%">Delete</td>
		</tr>
						
		<% if ( size > 0 ) { %>
		<logic:iterate id="aQuery" name="usersavedquerylist" type="SavedQuery">
	         <tr>
	             <td class="resultsBoxWhite" valign="top" width="40%">
			
					<div id="menu<%=menuNumber%>" class="criteriaTitle" onclick="SwitchMenu('subMenu<%=menuNumber%>')" onmouseover="ChangeClass('menu<%=menuNumber%>','criteriaTitleOver')" onmouseout="ChangeClass('menu<%=menuNumber%>','criteriaTitle')">
						<img src="images/arrow_closed.gif" border="0">&nbsp;<bean:write name="aQuery" property="queryName" filter="true"/>
					</div>	
					<span class="submasterdiv" id='subMenu<%=menuNumber%>'>
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>	
								<td colspan="2">							    
								    <b>Search Criteria</b><br>
							    </td>
								<bean:define id="criteriaList" name="aQuery" property="savedQueryAttributes" />
								<logic:iterate id="aCriteria" name="criteriaList" type="SavedQueryAttribute">							
									<tr>
										<td width="50%">
										    <font class="standardText">
													<bean:write name="aCriteria" property="attributeName" filter="true"/>
											</font>
									    </td>
									    
									    <td width="50%">
										    <font class="standardText">
											    <camod:shorten><bean:write name="aCriteria" property="attributeValue" filter="true"/></camod:shorten>
										    </font>
									    </td>							    
								    </tr>
						    	</logic:iterate>	
					    </table>    	
					</span>
					<% menuNumber++; %>		
							                 
	             </td>
	             
	             <td class="resultsBoxWhite" valign="top" width="15%">
					<bean:write name="aQuery" property="executeTime" filter="true"/>&nbsp;
	             </td>
	             <td class="resultsBoxWhite" valign="top" width="5%">
					<bean:write name="aQuery" property="numberResults" filter="true"/>&nbsp;
	             </td>    	             
	             <td class="resultsBoxWhite" valign="top" width="20%">
	                <html:link action="SearchAdvancedAction.do" paramId="aQueryId" paramName="aQuery" paramProperty="id"><img border="0" src="images/resubmitquery.gif"></html:link>
	             </td>                  
	             <td class="resultsBoxWhite" valign="top" width="15%">
	                <html:link action="AdvancedSearchPopulateAction.do?unprotected_method=populate" paramId="aQueryId" paramName="aQuery" paramProperty="id"><img border="0" src="images/editquery.gif"></html:link>
	             </td>                  	             
	             <td class="resultsBoxWhiteEnd" valign="top" width="5%">	           
	                <center>
	                    <c:set var="deleteLink" value="return confirm('Are you sure you want to delete this saved query (${aQuery.queryName})?');"/>   
	                    <c:set var="uri" value="/camod/SaveQueryAction.do?method=delete&aQueryId=${aQuery.id}"/>
	                    <a href='<c:out value="${uri}"/>' onclick='<c:out value="${deleteLink}"/>' ><IMG src="images/remove.gif" border=0/></a>               	
	                </center>	                	                
	             </td>                  	             	             
	         </tr>	         	         
	         
		</logic:iterate>
	    <%} else { %>
		     <TR>
		  		<TD class="resultsBoxGreyEnd" colspan="6"><B><I>No Saved Queries</I></B> 
		   		</TD>
		     </TR>
		<%}%>	
				
	</TABLE>
</tr></td></TABLE>
</DIV>	

<%@ include file="/jsp/footer.jsp" %>