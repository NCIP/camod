<%@ include file="/jsp/header.jsp"%>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.webapp.form.AvailabilityForm"%>

<!-- needed for tooltips -->
<DIV id="TipLayer"
	style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>

<%
		String aAvailabilityID = request.getParameter("aAvailabilityID");
        String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
        
		//if aAvailabilityID is passed in, then we are dealing with a previously entered model and are editing it
		//otherwise, create a new one

		String actionName = "JacksonLabAction.do?method=save";

		if (aAvailabilityID != null && aAvailabilityID.length() > 0 && isDeleted == null) {
			actionName = "JacksonLabAction.do?method=edit";
		}
		else {
		    aAvailabilityID = "";
		}
%>

<html:form action="<%= actionName %>" focus="name">

<!-- submitJacksonLab.jsp -->
<!-- Main Content Begins -->
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins"
	width="100%" height="100%">
	<tr>
		<td>
		<TABLE summary="" cellpadding="0" cellspacing="0" border="0"
			class="contentPage" width="100%" height="100%">
			<tr>
				<td valign="top">

				<TABLE summary="" cellpadding="3" cellspacing="0" border="0"
					align="left">
					<tr>
						<html:errors />
						<td class="formMessage" colspan="3">* indicates a required field</td>
					</tr>

					<tr>
						<td class="formTitle" height="20" colspan="3">Available from Jackson Laboratory
							<camod:cshelp topic="model_availability_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
					</tr>

					<tr>
						<td class="formRequiredNotice" width="5">*</td>
						<td class="formRequiredLabel"><label for="field1">Strain Name:</label>
						<camod:cshelp topic="model_availability_help" key="ANIMAL_AVAILABILITY.NAME" image="images/iconHelp.gif" text="Tool Tip Test 1" /></td>
						<td class="formField">
							<html:hidden property="source" />
							<html:text styleClass="formFieldUnSized" size="40" property="name" />
						</td>
					</tr>


					<tr>
						<td class="formRequiredNotice" width="5">&nbsp;</td>
						<td class="formLabel">Stock Number: 
						     <camod:cshelp topic="model_availability_help" key="ANIMAL_AVAILABILITY.STOCK_NUMBER" image="images/iconHelp.gif" text="Tool Tip Test 1" /></td>
						<td class="formField">
						    <input type=button value="Find Info" onClick="myRef = window.open('http://jaxmice.jax.org/index.html','mywin','left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"></input>
						    <html:text styleClass="formFieldSized" size="30" property="stockNumber" />
						</td>
					</tr>

					<tr>
						<td align="right" colspan="3">
						<TABLE cellpadding="4" cellspacing="0" border="0">

							<html:submit styleClass="actionButton">
								<bean:message key="button.submit" />
							</html:submit>

							<html:reset styleClass="actionButton">
								<bean:message key="button.reset" />
							</html:reset>

							<c:if test="${not empty aAvailabilityID}">
								<html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton"
									onclick="return confirm('Are you sure you want to delete?');">
									<bean:message key="button.delete" />
								</html:submit>
							</c:if>

							<!--  Done this way since html:hidden doesn't seem to work correctly -->
							<input type="hidden" name="aAvailabilityID" value="<%= aAvailabilityID %>">

							</html:form>
						</TABLE>
						</td>
					</tr>
				</TABLE>

				<!-- --></td>
			</tr>
		</TABLE>
	</tr>
	</td>
</TABLE>

<%@ include file="/jsp/footer.jsp"%>