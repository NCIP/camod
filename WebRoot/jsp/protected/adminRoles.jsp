<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<!-- adminRoles.jsp -->

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
	
		<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left" width="100%">	
			<tr>
				<td class="formTitle" height="20">Administration Roles</td>				
			</tr>			
			<tr>
				<td class="resultsBoxGreyEnd">
				<TABLE width="100%">
					<tr>
						<td>News and notes...</td>
						<td align="right"><img src="/camod/images/admin.gif"></td>
					</tr>
				</TABLE>
				</tr>
				<tr>
	                <logic:messagesPresent>
	                    <td>
	                        <TABLE width="100%">
	                            <tr>
						        <br>
								<b><font color=red>
								    <html:messages id="errors">
									    <%=errors %>
								    </html:messages>
								</font></b>
				               </tr>
				           </TABLE>
				       </td>
				    </logic:messagesPresent>
				</tr>
		</TABLE>
		</td></tr>
		<tr><td>
		<br>

       <!-- setup some useful variables -->
       <% pageContext.setAttribute("modelIdTag", Parameters.MODELID); %>
       <% pageContext.setAttribute("modelSectionTag", Parameters.MODELSECTIONNAME); %>
	   <% pageContext.setAttribute("commentsIdTag", Parameters.COMMENTSID); %>
	   <% pageContext.setAttribute("reject", Admin.Actions.REJECT); %>
	   <% pageContext.setAttribute("approve", Admin.Actions.APPROVE); %>
	   <% pageContext.setAttribute("assign_screener", Admin.Actions.ASSIGN_SCREENER); %>
			    		
		<!-- Start the various sections per role -->
		 		
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
		
		<!-- Models assigned to this user that need to be edited -->
		<logic:present name="<%= Admin.MODELS_NEEDING_EDITING %>">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left" width="100%">	
			    <tr>
				    <td class="formTitleBlue" height="20" colspan="3">Models to Edit</td>				
			    </tr>			
			    <tr>
				    <td class="resultsBoxWhiteEnd" colspan="3" >You have been assigned the following models to Edit</td> 
			    </tr>
			    <logic:iterate name="<%= Admin.MODELS_NEEDING_EDITING %>" id="model" type="gov.nih.nci.camod.domain.AnimalModel">
			        <tr>
				        <td class="resultsBoxWhiteNoEnd">
				            <html:link action="ViewModelAction.do?unprotected_method=populateModelCharacteristics" paramId="<%=Constants.Parameters.MODELID%>" paramName="model" paramProperty="id" >
				                <IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5 border=0><bean:write name="model" property="modelDescriptor" />
	                        </html:link>
			                <td class="resultsBoxWhiteNoSides" width="25" >
				                <html:link action="<%="ChangeAnimalModelStatePopulateAction?event=" + Admin.Actions.APPROVE %>" paramId="<%=Constants.Parameters.MODELID%>" paramName="model"  paramProperty="id">
		                            Approve
				                </html:link>
				            </td>
				            <td class="resultsBoxWhiteNoStart" width="25">
				                <html:link action="<%="ChangeAnimalModelStatePopulateAction?event=" + Admin.Actions.NEED_MORE_INFO %>" paramId="<%=Constants.Parameters.MODELID%>" paramName="model"  paramProperty="id">
				                    Need More Info
				                </html:link>
				            </td>
				        </td>
			        </tr>
			    </logic:iterate>
            <tr><td>&nbsp;</td></tr>
			</TABLE>	
		</logic:present>			
		</td></tr>
				<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
		
		<!-- Models assigned to this user that need more information -->
		
		<logic:present name="<%= Admin.MODELS_NEEDING_MORE_INFO %>">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left" width="100%">	
			    <tr>
				    <td class="formTitleBlue" height="20" colspan="3">Models Needing more information</td>				
			    </tr>			
			    <tr>
				    <td class="resultsBoxWhiteEnd" colspan="3" >You are awaiting more information for the following models assigned to you</td> 
			    </tr>
			    <logic:iterate name="<%= Admin.MODELS_NEEDING_MORE_INFO %>" id="model" type="gov.nih.nci.camod.domain.AnimalModel">
			        <tr>
				        <td class="resultsBoxWhiteNoEnd">
				            <html:link action="ViewModelAction.do?unprotected_method=populateModelCharacteristics" paramId="<%=Constants.Parameters.MODELID%>" paramName="model" paramProperty="id" >
				                <IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5 border=0><bean:write name="model" property="modelDescriptor" />
				            </html:link>
				            <td class="resultsBoxWhiteNoSides" width="25" >
				                <html:link action="<%="ChangeAnimalModelStatePopulateAction?event=" + Admin.Actions.APPROVE %>" paramId="<%=Constants.Parameters.MODELID%>" paramName="model"  paramProperty="id">
				                    Approve
				                </html:link>
				            </td>
				            <td class="resultsBoxWhiteNoStart" width="25">
				                <html:link action="<%="ChangeAnimalModelStatePopulateAction?event=" + Admin.Actions.NEED_MORE_INFO %>" paramId="<%=Constants.Parameters.MODELID%>" paramName="model"  paramProperty="id">
				                    Need More Info
				                </html:link>
				            </td>
				        </td>
			        </tr>
			    </logic:iterate>		
            <tr><td>&nbsp;</td></tr>                
			</TABLE>			
		</logic:present>	
		</td></tr>
		<tr><td>
		
		<!-- Models assigned to this user that need to have an editor assigned -->
		
		<logic:present name="<%= Admin.MODELS_NEEDING_EDITOR_ASSIGNMENT %>">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left" width="100%">	
			    <tr>
				    <td class="formTitleBlue" height="20" colspan="3">Models Needing Editor Assignment</td>				
			    </tr>			
			    <tr>
				    <td class="resultsBoxWhiteEnd" colspan="3" >You need to assign Editors for the following models</td> 
			    </tr>    
			    <logic:iterate name="<%= Admin.MODELS_NEEDING_EDITOR_ASSIGNMENT %>" id="model" type="gov.nih.nci.camod.domain.AnimalModel">
			        <tr>
				        <td class="resultsBoxWhiteNoEnd">
				            <html:link action="ViewModelAction.do?unprotected_method=populateModelCharacteristics" paramId="<%=Constants.Parameters.MODELID%>" paramName="model" paramProperty="id" >
				                <IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5 border=0><bean:write name="model" property="modelDescriptor" />
				            </html:link>
		                    <td class="resultsBoxWhiteNoStart" width="25" >
				                <html:link action="<%="ChangeAnimalModelStatePopulateAction?event=" + Admin.Actions.ASSIGN_EDITOR %>" paramId="<%=Constants.Parameters.MODELID%>" paramName="model"  paramProperty="id">
				                    Assign
				                </html:link>
	                        </td>
				        </td>
			        </tr>
			    </logic:iterate>		
            <tr><td>&nbsp;</td></tr>
			</TABLE>
		</logic:present>	
		</td></tr>
		<tr><td>
		
		<!-- Models assigned to this user that need to be screened -->
		
		<logic:present name="<%= Admin.MODELS_NEEDING_SCREENING %>">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left" width="100%">	
			    <tr>
				    <td class="formTitleBlue" height="20" colspan="3">Models to Screen</td>				
			    </tr>			
			    <tr>
				    <td class="resultsBoxWhiteEnd" colspan="3">You have been assigned the following models to Screen</td> 
			    </tr>
			    <logic:iterate name="<%= Admin.MODELS_NEEDING_SCREENING %>" id="model" type="gov.nih.nci.camod.domain.AnimalModel">
			        <tr>
				        <td class="resultsBoxWhiteNoEnd">
				            <html:link action="ViewModelAction.do?unprotected_method=populateModelCharacteristics" paramId="<%=Constants.Parameters.MODELID%>" paramName="model" paramProperty="id" >
				                <IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5 border=0><bean:write name="model" property="modelDescriptor" />
				            </html:link>
				            <td class="resultsBoxWhiteNoSides" width="25" >
				                <html:link action="<%="ChangeAnimalModelStatePopulateAction?event=" + Admin.Actions.APPROVE %>" paramId="<%=Constants.Parameters.MODELID%>" paramName="model"  paramProperty="id">
				                    Approve
				                </html:link>
				            </td>
				            <td class="resultsBoxWhiteNoStart" width="25">
				                <html:link action="<%="ChangeAnimalModelStatePopulateAction?event=" + Admin.Actions.REJECT %>" paramId="<%=Constants.Parameters.MODELID%>" paramName="model"  paramProperty="id">
				                    Reject
				                </html:link>
				            </td>
				        </td>
			        </tr>
			    </logic:iterate>			
			    <tr>
			        <td>&nbsp;</td>
			    </tr>
			</TABLE>			
		</logic:present>
		</td></tr>
		<tr><td>
		
		<!-- Models assigned to this user that need a screener assigned -->
		
		<logic:present name="<%= Admin.MODELS_NEEDING_SCREENER_ASSIGNMENT %>">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left" width="100%">	
			    <tr>
				    <td class="formTitleBlue" height="20" colspan="3">Models Needing Screener Assignment</td>				
			    </tr>			
			    <tr>
				    <td class="resultsBoxWhiteEnd" colspan="3" >You need to assign Sceeners for the following models</td> 
			    </tr>			    
			    <logic:iterate name="<%= Admin.MODELS_NEEDING_SCREENER_ASSIGNMENT %>" id="model" type="gov.nih.nci.camod.domain.AnimalModel">
			        <tr>
				        <td class="resultsBoxWhiteNoEnd">
				            <html:link action="ViewModelAction.do?unprotected_method=populateModelCharacteristics" paramId="<%=Constants.Parameters.MODELID%>" paramName="model" paramProperty="id" >
				                <IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5 border=0><bean:write name="model" property="modelDescriptor" />
				            </html:link>
				            <td class="resultsBoxWhiteNoStart" width="25" >
				                <html:link action="<%="ChangeAnimalModelStatePopulateAction?event=" + Admin.Actions.ASSIGN_SCREENER %>" 
				                           paramId="<%=Constants.Parameters.MODELID%>" paramName="model" paramProperty="id">
				                    Assign
				                </html:link>
				            </td>
				        </td>
			        </tr>
			    </logic:iterate>
            <tr><td>&nbsp;</td></tr>
			</TABLE>	
		</logic:present>				
		</td></tr>
		<tr><td>
		
		<!-- Comments assigned to this user that need review -->
		
		<logic:present name="<%= Admin.COMMENTS_NEEDING_REVIEW %>">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left" width="100%">	
			    <tr>
				    <td class="formTitleBlue" height="20" colspan="3">Comments to Review</td>				
			    </tr>			
		        <tr>
				    <td class="resultsBoxWhiteEnd" colspan="3">You have been assigned the following Comments to Review
			    </tr>

			    <logic:iterate name="<%= Admin.COMMENTS_NEEDING_REVIEW %>" id="comments" type="gov.nih.nci.camod.domain.Comments">
			        <tr>
				        <td class="resultsBoxWhiteNoEnd">
				            
				            <c:set var="uri" value="ViewModelSectionAction.do?${modelIdTag}=${comments.cancerModel.id}&${modelSectionTag}=${comments.modelSection.name}&${commentsIdTag}=${comments.id}"/>
				            <a href="<c:out value="${uri}"/>">
				            
				                <IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5 border=0><c:out value="${comments.modelSection.name}"/> - <c:out value="${comments.cancerModel.modelDescriptor}"/>
				            </a>
				            <td class="resultsBoxWhiteNoSides" width="25" >
				                <c:set var="uri" value="ChangeCommentsStatePopulateAction.do?event=${approve}&${modelIdTag}=${comments.cancerModel.id}&${commentsIdTag}=${comments.id}"/>
				                <a href="<c:out value="${uri}"/>">
				                    Approve
				                </a>
				            </td>
				            <td class="resultsBoxWhiteNoStart" width="25" >
				                <c:set var="uri" value="ChangeCommentsStatePopulateAction.do?event=${reject}&${modelIdTag}=${comments.cancerModel.id}&${commentsIdTag}=${comments.id}"/>
				                <a href="<c:out value="${uri}"/>">
				                    Reject
				                </a>
				            </td>				            
				        </td>
			        </tr>
			    </logic:iterate>		
			</TABLE>
		</logic:present>
		<logic:present name="<%= Admin.COMMENTS_NEEDING_ASSIGNMENT %>">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left" width="100%">	
			    <tr>
				    <td class="formTitleBlue" height="20" colspan="3">Comments to Review</td>				
			    </tr>			
		        <tr>
				    <td class="resultsBoxWhiteEnd" colspan="3">You have been assigned the following Comments to Review
			    </tr>
			    <logic:iterate name="<%= Admin.COMMENTS_NEEDING_ASSIGNMENT %>" id="comments" type="gov.nih.nci.camod.domain.Comments">
			        <tr>
				        <td class="resultsBoxWhiteNoEnd">
				            <c:set var="uri" value="ViewModelSectionAction.do?${modelIdTag}=${comments.cancerModel.id}&${modelSectionTag}=${comments.modelSection.name}&${commentsIdTag}=${comments.id}"/>
				            <a href="<c:out value="${uri}"/>">
				                <IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5 border=0><c:out value="${comments.modelSection.name}"/> - <c:out value="${comments.cancerModel.modelDescriptor}"/>
				            </a>
				            <td class="resultsBoxWhiteNoStart" width="25" >
				                <c:set var="uri" value="ChangeCommentsStatePopulateAction.do?event=${assign_screener}&${modelIdTag}=${comments.cancerModel.id}&${commentsIdTag}=${comments.id}"/>
				                <a href="<c:out value="${uri}"/>">
				                    Assign
				                </a>
				            </td>
				        </td>
			        </tr>
			    </logic:iterate>		
			</TABLE>
		</logic:present>	
		</td></tr>
	</TABLE>
	</td></tr>
	</TABLE>
</tr></td>
</TABLE>

<%@ include file="/jsp/footer.jsp" %>