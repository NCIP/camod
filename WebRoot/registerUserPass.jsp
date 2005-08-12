<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>

 <FORM name="input" action="registerSuccess.jsp" method="get">
<!-- Main Content Begins -->  
<TABLE summary="" cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td width="100%">		

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">

		<tr>			
			<td colspan="3" class="formMessage" align="left">* - Indicates a required field</td>
		</tr>

		<tr>
			<td class="formTitle" height="20" colspan="3">Choose a Username and Password</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formRequiredLabel" ><label for="field1">* Username</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formRequiredLabel"><label for="field1">* Password</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formRequiredLabel"><label for="field1">* Confirm Password</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>				

		<tr>
			<td align="right" colspan="3">
				<!-- action buttons begins -->
				<TABLE cellpadding="4" cellspacing="0" border="0">
					<tr>					
						<td>                                                   
                                                        <input class="actionButton" type="submit" value="Submit" />
						</td>
						<td>
                                                        <input class="actionButton" type="reset" value="Reset" />
						</td>
					</tr>
				</TABLE>
			</td>
		</tr>
	</TABLE>
	</td></tr>
	    
</TABLE>

<!-- Main Content Ends  -->

<%@ include file="footer.jsp" %>