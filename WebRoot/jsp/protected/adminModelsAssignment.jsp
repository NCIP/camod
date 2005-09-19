<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" width="100%">
		
		<tr>
			<td class="formMessage" colspan="3">
				<logic:messagesPresent>
				  <ul>
				    <font color="red">
				      <html:messages id="error">
				        <li><%=error %></li>
				      </html:messages>
				    </font>
				  </ul>
				</logic:messagesPresent>
			</td>
		</tr>
		<tr>
		    <td class="formTitle" height="20" colspan="4">Models Assignment</td>		
		</tr>
		<html:form action="AdminModelsAssignmentPopulateAction">
		    
			    <td class=resultsBoxGreyNoEnd>			
			        <html:select property="currentState">
			            <html:options name="<%=Constants.FORMDATA%>" property="states"/>
		            </html:select>
			    </td>
		    
		
	        <td  class="resultsBoxGreyNoStart" align="right" colspan="3">
				<TABLE cellpadding="4" cellspacing="0" border="0">	
			        <html:submit styleClass="actionButton">
					    <bean:message key="button.submit"/>
				    </html:submit>	
				</TABLE>
			</td>
		</html:form>
	</TABLE>
	    <br>	
		<logic:present name="models">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left" width="100%">	
				<tr>
			        <td class="formTitleBlue" height="20" colspan="5"><bean:write name="<%=Constants.FORMDATA%>" property="currentState"/> Records</td>				
		        </tr>
		        <tr>
			        <td class="greySubTitleLeft" width="10%">No</td>
			        <td class="greySubTitleLeft" width="30%">Model Descriptor</td>
			        <td class="greySubTitleLeft" width="30%">Submitter's Name</td>
			        <td class="greySubTitle" width="30%">Entered on</td>
		        </tr>
		        <c:set var="counter" value="1" />
			    <logic:iterate name="models" id="model" type="gov.nih.nci.camod.domain.AnimalModel">
			        <tr>
			            <td class="resultsBoxWhite" width="10%"><c:out value="${counter}"/></td>
				        <td class="resultsBoxWhiteNoEnd">
				            <html:link action="ViewModelAction.do?unprotected_method=populateModelCharacteristics" paramId="<%=Constants.Parameters.MODELID%>" paramName="model" paramProperty="id" >
				                <IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5 border=0><bean:write name="model" property="modelDescriptor" />
				            </html:link>
				        </td>
				        <td class="resultsBoxWhite" width="30%"><c:out value="${model.submitter.lastName}"/>, <c:out value="${model.submitter.firstName}"/></td>
				        <td class="resultsBoxWhiteEnd" width="40%"><c:out value="${model.availability.enteredDate}"/></td>
			        </tr>
			        <c:set var="counter" value="${counter + 1}" />
			    </logic:iterate>		
            <tr><td>&nbsp;</td></tr>
			</TABLE>
		</logic:present>	
    </td></tr>
</TABLE>	

<%@ include file="/jsp/footer.jsp" %>





