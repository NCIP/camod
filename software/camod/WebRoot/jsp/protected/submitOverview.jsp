<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>

<%@ page import='gov.nih.nci.camod.Constants.*' %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>
<%@ page import='java.util.List' %>
<%@ page buffer="32kb"%>

<!-- submitOverview.jsp -->
<!-- Main Content Begins -->
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/submitMenu.jsp" %>
<tr><td>	

	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%">
	<tr><td valign="top">
		<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			
			<TABLE cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	

			<tr>
				<th scope="col" class="formTitle" height="20" colspan=2>Model Status &nbsp;
				<camod:cshelp topic="model_status_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></th>				
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
                            <html:hidden property="remark" name="<%= Constants.FORMDATA %>" />
                            <html:submit>Set model status to Complete</html:submit> 
                        </html:form> 
                    </td>
                </logic:equal>                		               		
            </tr>
			<tr>
				<td class="resultsBoxWhiteEnd" colspan=2>
					<td> </td>			
				</td> 
			</tr>
			</TABLE>
			<br>			
			<!-- Do not show admin functions if user is not superuser role -->
			<logic:present name="<%= Constants.CURRENTUSERROLES %>">
			<logic:iterate name="<%= Constants.CURRENTUSERROLES %>" id="role" type="String">
				<% 
						if ( role.contains(Admin.Roles.SUPER_USER)) { 
				%> 
			
				
			<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" align="left" width="98%"> 
			
				<!-- Do not show any of the admin functions if the model is not set to complete state --> 	
            	<logic:notEqual name="<%= Constants.MODELSTATUS %>" value="Complete-not screened">
            	<logic:notEqual name="<%= Constants.MODELSTATUS %>" value="Incomplete">	
            			           
					<tr>
						<td class="formTitle" height="1" colspan="5">&nbsp;</td>				
					</tr> 

         			<td class="resultsBoxGreyNoEnd" align="left" colspan="1">
            				Change the State To:
         			</td>

	    			<!-- Do not show Complete-not screened functions if the model is Complete-not screened or Incomplete -->
	                <td align="left" colspan="1" class="resultsBoxGreyNoEnd">
	                	<html:form action="ChangeAnimalModelToCompleteNotScreenedAction">
	                    	<input type="hidden" name="aModelId" value="<%= Constants.Parameters.MODELID %>"/>
	                    	<input type="hidden" name="aEvent" value="<%= Constants.Admin.Actions.BACK_TO_COMPLETE%>"/>      
	                    	<html:submit>Complete-not screened</html:submit>						        
	                	</html:form>				                                
	                </td>
                    
                    <!-- Show Screened - Approved function if the model is Editor-assigned -->
                    <logic:notEqual name="<%= Constants.MODELSTATUS %>" value="Screener-assigned">
                    <logic:notEqual name="<%= Constants.MODELSTATUS %>" value="Screened-approved">
                    <logic:notEqual name="<%= Constants.MODELSTATUS %>" value="Screened-rejected">
                    <logic:notEqual name="<%= Constants.MODELSTATUS %>" value="Edited-need more info">
                    <logic:notEqual name="<%= Constants.MODELSTATUS %>" value="Edited-approved">
                    <logic:notEqual name="<%= Constants.MODELSTATUS %>" value="Inactive">
                    <td align="left" colspan="1" class="resultsBoxGreyEnd">
                    	<html:form action="ChangeAnimalModelToScreenedApprovedAction">
                    		<input type="hidden" name="modelId" value="<%= Constants.MODELID%>"/>
                    		<input type="hidden" name="aEvent" value="<%= Constants.Admin.Actions.BACK_TO_SCREENER_APPROVE%>"/>                    	
                            <html:submit>Screened - Approved</html:submit> 
                       	</html:form>                    
               		</td>    &nbsp;
               		</logic:notEqual>
               		</logic:notEqual>
               		</logic:notEqual>
               		</logic:notEqual>
               		</logic:notEqual>
               		</logic:notEqual>                     
                 </logic:notEqual>
                 </logic:notEqual>
			</TABLE>
			
			<%
	 	  		}
			%>
		</logic:iterate>
		</logic:present> 			
				  
		</td></tr></TABLE>
	</td></tr></TABLE>	
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>