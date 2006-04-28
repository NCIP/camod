<%

/**
 * 
 * $Id: savedSearchQueries.jsp,v 1.1 2006-04-28 19:41:32 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<%@ page import="gov.nih.nci.camod.service.QueryStorageManager" %>
<%@ page import="gov.nih.nci.camod.domain.SavedQuery" %>	
<%@ page import="gov.nih.nci.camod.domain.SavedQueryAttribute" %>	

<%  
	List userQueryList = (List) request.getSession().getAttribute( Constants.USERSAVEDQUERYLIST );
	int size = userQueryList.size();
	System.out.println( "SIZE: " + size );	
	
	int menuNumber = 1;
%>

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
			<td class="formTitleBlue" height="20" colspan="6">Saved Queries</td>				
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
											    <bean:write name="aCriteria" property="attributeValue" filter="true"/>
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