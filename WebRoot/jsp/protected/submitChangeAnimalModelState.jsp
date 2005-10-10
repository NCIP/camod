<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page buffer="32kb"%>
<%@ page import="gov.nih.nci.camod.webapp.form.ModelCharacteristicsForm" %>	
<%@ page import="gov.nih.nci.camod.Constants" %>

<!-- submitChangeAnimalModelState.jsp -->

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0">
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
			<td class="formTitle" height="20" colspan="3">
			    <bean:write name="action" /> AnimalModel <bean:write property="modelDescriptor" name="formdata" />
			</td>
		</tr>
        <html:form action="ChangeAnimalModelStateAction">
		<logic:notEmpty name="<%=Constants.FORMDATA%>" property="assignees" >
		    <tr>
			    <td class="formRequiredNotice" width="5">*</td>
			    <td class="formRequiredLabel"><label for="field1">Assigned To</label></td>
			    <td class="formField">			
		            <html:select property="assignedTo">
			            <html:options name="<%=Constants.FORMDATA%>" property="assignees"/>
		            </html:select>
			    </td>
		    </tr>
		</logic:notEmpty>
		<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formRequiredLabel"><label for="field2">Note</label></td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized" property="note" name="formdata" cols="32" rows="4"/>			
			</td>
		</tr>
        <html:hidden property="modelId" name="formdata" />
        <html:hidden property="modelDescriptor" name="formdata" />
        <html:hidden property="event" name="formdata" />
		<tr>
			<td align="right" colspan="3">
				<TABLE cellpadding="4" cellspacing="0" border="0">
				
					  <html:submit styleClass="actionButton">
						  <bean:message key="button.submit"/>
					  </html:submit>
					  
					  <html:cancel styleClass="actionButton">
					  	  <bean:message key="button.cancel"/>
	  				  </html:cancel>
				  
				  </html:form>			
				</TABLE>
			</td>
		</tr>
	</TABLE>	
</td></tr></TABLE>

<%@ include file="/jsp/footer.jsp" %>