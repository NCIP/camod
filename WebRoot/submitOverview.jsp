<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="gov.nih.nci.camod.Constants" %>

<%@ page buffer="32kb"%>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	

			<tr>
				<td class="formTitle" height="20" colspan=2>Model Status</td>				
			</tr>			

			<tr>
				<td class="resultsBoxWhiteEnd" colspan=2>
	                <logic:messagesPresent>
					      <html:messages id="overview">
					        <%=overview %>
					      </html:messages>
					</logic:messagesPresent>
					&nbsp;								
				</td> 
			</tr>			
					
            <tr>
                <td class="resultsBoxGrey">
                		This model's status is currently set to <b><%= request.getSession().getAttribute( Constants.MODELSTATUS ) %></b>                		
                </td>
                <td align="right" class="resultsBoxGreyEnd2">
                	<input class="actionButton" type="submit" value="Set model status to Complete" />
                </td>
                		               		
            </tr>				

            <tr>
                <td align="right" colspan="2">
                        <!-- action buttons begins -->
                        <table cellpadding="4" cellspacing="0" border="0">
                                <tr>
                                    <td></td>                                                      
                                </tr>
                        </table>
                        <!-- action buttons end -->
                </td>
            </tr>
			</TABLE>
		</td></tr></TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>
<%@ include file="footer.jsp" %>