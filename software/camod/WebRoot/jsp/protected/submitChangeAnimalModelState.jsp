<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page buffer="32kb"%>
<%@ page import="gov.nih.nci.camod.webapp.form.ModelCharacteristicsForm" %>	
<%@ page import="gov.nih.nci.camod.webapp.form.AnimalModelStateForm" %>
<%@ page import="gov.nih.nci.camod.Constants" %>

<!-- submitChangeAnimalModelState.jsp -->
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/adminMenu.jsp" %>
<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="60%" height="100%">
	<tr><td>
	<TABLE cellpadding="3" cellspacing="0" border="0">
	<html:form action="ChangeAnimalModelStateAction">
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
				* indicates a required field
			</td>
		</tr>
		<tr>
			<th scope="col" class="formTitle" height="20" colspan="3">
			    <bean:write name="action" /> AnimalModel <c:out value="${animalModelStateForm.modelDescriptor}" escapeXml="false" />&nbsp;
			    <c:if test="${wiki_cs_help eq 1}">
		    		<camod:cshelp topic="assigning_screener_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/>
		    	</c:if>
			    <c:if test="${wiki_cs_help eq 2}">
		    		<camod:cshelp topic="assigning_an_editor_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/>
		    	</c:if>
		    	<c:if test="${wiki_cs_help eq 3}">
		    		<camod:cshelp topic="rejecting_model_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/>
		    	</c:if>
		    	<c:if test="${wiki_cs_help eq 4}">
		    		<camod:cshelp topic="approving_model_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/>
		    	</c:if>
			</th>
		</tr>        
		<logic:notEmpty name="<%=Constants.Dropdowns.USERSFORROLEDROP%>" >
		    <tr>
			    <td class="formRequiredNotice" width="5">*</td>
			    <td class="formRequiredLabel"><label for="assignedTo">Assigned To</label></td>
			    <td class="formField">			
		            <html:select styleId="assignedTo" property="assignedTo">
			            <html:optionsCollection name="<%=Constants.Dropdowns.USERSFORROLEDROP%>"/>
		            </html:select>
			    </td>
		    </tr>
		</logic:notEmpty>
		<logic:empty name="<%=Constants.Dropdowns.USERSFORROLEDROP%>" >
	        <html:hidden property="assignedTo"/>
		</logic:empty>
		<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formRequiredLabel"><label for="remark">Remark</label></td>
			<td class="formField">
					<html:textarea styleId="remark" styleClass="formFieldSized" property="remark" cols="32" rows="4"/>			
			</td>
		</tr>
        <html:hidden property="modelId" />
        <html:hidden property="modelDescriptor" />
        <html:hidden property="event" />
		<tr>
			<td align="right" colspan="3">
				<TABLE summary="This table is used to format page content" cellpadding="4" cellspacing="0" border="0">				
					  <html:submit styleClass="actionButton">
						  <bean:message key="button.submit"/>
					  </html:submit>
					  
					  <html:cancel styleClass="actionButton">
					  	  <bean:message key="button.cancel"/>
	  				  </html:cancel>			  		
				</TABLE>
			</td>
		</tr>
	</html:form>	
	</TABLE>
	</td></tr>
	</TABLE>	
</td></tr>
</TABLE>

<%@ include file="/jsp/footer.jsp" %>