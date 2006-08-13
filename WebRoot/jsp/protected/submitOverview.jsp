<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>

<%@ page import="gov.nih.nci.camod.Constants" %>
<%@ page buffer="32kb"%>

<!-- submitOverview.jsp -->
<!-- Main Content Begins -->
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	

			<tr>
				<td class="formTitle" height="20" colspan=2>Model Status &nbsp;
				<camod:cshelp topic="model_status_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>				
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
                <logic:notEqual name="<%= Constants.MODELSTATUS %>" value="Incomplete">
                    <td class="resultsBoxGreyBothEnd">
                		    This model's status is currently set to <b><%= request.getSession().getAttribute( Constants.MODELSTATUS ) %></b>                		
                    </td>
                </logic:notEqual>
                <logic:equal name="<%= Constants.MODELSTATUS %>" value="Incomplete">
                    <td class="resultsBoxGrey">
                		    This model's status is currently set to <b><%= request.getSession().getAttribute( Constants.MODELSTATUS ) %></b>                		
                    </td>
                    <td align="right" class="resultsBoxGreyEnd2">
                        <html:form action="ChangeAnimalModelToCompleteAction">
                            <html:hidden property="modelId" name="<%= Constants.FORMDATA %>" />
                            <html:hidden property="modelDescriptor" name="<%= Constants.FORMDATA %>" />
                            <html:hidden property="assignedTo" name="<%= Constants.FORMDATA %>" />
                            <html:hidden property="event" name="<%= Constants.FORMDATA %>" />
                            <html:hidden property="note" name="<%= Constants.FORMDATA %>" />
                            <html:submit>Set model status to Complete</html:submit>  
                        </html:form> 
                    </td>
                </logic:equal>
                		               		
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

<%@ include file="/jsp/footer.jsp" %>