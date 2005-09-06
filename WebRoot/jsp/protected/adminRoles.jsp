<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

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
				</td> 
			</tr>

		</TABLE>
		</td></tr>
		<tr><td>
		<br>
				
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
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
				            <html:link action="/viewModelCharacteristics"  paramId="ModelId" paramName="model"  paramProperty="id" >
				                <IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5 border=0><bean:write name="model" property="modelDescriptor" />
	                        </html:link>
			                <td class="resultsBoxWhiteNoSides" width="25" >
				                <html:link action="/ChangeAnimalModelStatePopulateAction?event=approved" paramId="ModelId" paramName="model"  paramProperty="id">
		                            Approve
				                </html:link>
				            </td>
				            <td class="resultsBoxWhiteNoStart" width="25">
				                <html:link action="/ChangeAnimalModelStatePopulateAction?event=need_more_info" paramId="ModelId" paramName="model"  paramProperty="id">
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
				            <html:link action="/viewModelCharacteristics"  paramId="ModelId" paramName="model"  paramProperty="id" >
				                <IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5 border=0><bean:write name="model" property="modelDescriptor" />
				            </html:link>
				            <td class="resultsBoxWhiteNoSides" width="25" >
				                <html:link action="/ChangeAnimalModelStatePopulateAction?event=approved" paramId="ModelId" paramName="model"  paramProperty="id">
				                    Approve
				                </html:link>
				            </td>
				            <td class="resultsBoxWhiteNoStart" width="25">
				                <html:link action="/ChangeAnimalModelStatePopulateAction?event=need_more_info" paramId="ModelId" paramName="model"  paramProperty="id">
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
				            <html:link action="/viewModelCharacteristics"  paramId="ModelId" paramName="model"  paramProperty="id" >
				                <IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5 border=0><bean:write name="model" property="modelDescriptor" />
				            </html:link>
		                    <td class="resultsBoxWhiteNoStart" width="25" >
				                <html:link action="/ChangeAnimalModelStatePopulateAction?event=assign_editor" paramId="ModelId" paramName="model"  paramProperty="id">
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
				            <html:link action="/viewModelCharacteristics"  paramId="ModelId" paramName="model"  paramProperty="id" >
				                <IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5 border=0><bean:write name="model" property="modelDescriptor" />
				            </html:link>
				            <td class="resultsBoxWhiteNoSides" width="25" >
				                <html:link action="/ChangeAnimalModelStatePopulateAction?event=approved" paramId="ModelId" paramName="model"  paramProperty="id">
				                    Approve
				                </html:link>
				            </td>
				            <td class="resultsBoxWhiteNoStart" width="25">
				                <html:link action="/ChangeAnimalModelStatePopulateAction?event=rejected" paramId="ModelId" paramName="model"  paramProperty="id">
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
				            <html:link action="/viewModelCharacteristics"  paramId="ModelId" paramName="model"  paramProperty="id" >
				                <IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5 border=0><bean:write name="model" property="modelDescriptor" />
				            </html:link>
				            <td class="resultsBoxWhiteNoStart" width="25" >
				                <html:link action="/ChangeAnimalModelStatePopulateAction?event=assign_screener" paramId="ModelId" paramName="model" paramProperty="id">
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
		<logic:present name="<%= Admin.COMMENTS_NEEDING_REVIEW %>">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left" width="100%">	
			    <tr>
				    <td class="formTitleBlue" height="20" colspan="3">Comments to Review</td>				
			    </tr>			
		        <tr>
				    <td class="resultsBoxWhiteEnd" colspan="3">You have been assigned the following Comments to Review
			    </tr>
			    <logic:iterate name="<%= Admin.COMMENTS_NEEDING_REVIEW %>" id="model" type="gov.nih.nci.camod.domain.AnimalModel">
			        <tr>
				        <td class="resultsBoxWhiteNoEnd">
				            <html:link action="/viewModelCharacteristics"  paramId="ModelId" paramName="model"  paramProperty="id" >
				                <IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5 border=0><bean:write name="model" property="modelDescriptor" />
				            </html:link>
				            <td class="resultsBoxWhiteNoSides" width="25" >
				                <html:link action="/ChangeAnimalModelStatePopulateAction?event=approved" paramId="ModelId" paramName="model"  paramProperty="id">
				                    Approve
				                </html:link>
				            </td>
				            <td class="resultsBoxWhiteNoStart" width="25">
				                <html:link action="/ChangeAnimalModelStatePopulateAction?event=rejected" paramId="ModelId" paramName="model"  paramProperty="id">
				                    Reject
				                </html:link>
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