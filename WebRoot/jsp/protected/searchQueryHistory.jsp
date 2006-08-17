<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<%@ page import="gov.nih.nci.camod.service.SavedQueryManager" %>
<%@ page import="gov.nih.nci.camod.domain.SavedQuery" %>	
<%@ page import="gov.nih.nci.camod.domain.SavedQueryAttribute" %>	

<%  
	List userQueryList = (List) request.getSession().getAttribute( Constants.USERSQUERYLIST );
	
	int size = 0;
	
	if ( userQueryList != null )
	{
		size = userQueryList.size();
	
		// Truncate the list to 25 items
		if ( size > 20 )
			userQueryList.subList( 20, size ).clear();		
	}
	
	int menuNumber = 1;	
%>

<!-- searchQueryHistory.jsp -->
<!-- Main Content Begins -->
<DIV id="masterdiv">
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" width="100%">	
		<tr>
			<td class="formTitleBlue" height="20" colspan="4">Query History ( Last 20 searches ) &nbsp;
				<camod:cshelp topic="search_query_history_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>				
		</tr>

		<tr>
			<td class="greySubTitleLeft" width="45%">Query Name</td>
			<td class="greySubTitleLeft" width="25%">Last Executed</td>
			<td class="greySubTitleLeft" width="5%">Results</td>
			<td class="greySubTitle" width="25%">Resubmit Query</td>
		</tr>
						
		<% if ( size > 0 ) { %>
		<logic:iterate id="aQuery" name="userquerylist" type="SavedQuery">
	         <TR>
	             <td class="resultsBoxWhite" valign="top" width="45%">
	             
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
	             <td class="resultsBoxWhite" valign="top" width="25%">
					<bean:write name="aQuery" property="executeTime" filter="true"/>&nbsp;
	             </td>
	             <td class="resultsBoxWhite" valign="top" width="5%">
	                <bean:write name="aQuery" property="numberResults" filter="true"/>&nbsp;
	             </td>  	             
	             <td class="resultsBoxWhiteEnd" valign="top" width="25%">
	                <html:link action="SearchAdvancedAction.do" paramId="aQueryId" paramName="aQuery" paramProperty="id"><img border="0" src="images/resubmitquery.gif"></html:link>
	             </td>                              
   	         </TR>
		</logic:iterate>
	    <%} else { %>
		     <TR>
		  		<TD class="resultsBoxGreyEnd" colspan="4"><B><I>No Query History</I></B> 
		   		</TD>
		     </TR>
		<%}%>		
				
	</TABLE>					
</tr></td></TABLE>
</DIV>

<%@ include file="/jsp/footer.jsp" %>