<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>

<FORM name="input" action="registerUserPass.jsp" method="get">

<!-- Main Content Begins -->  
<TABLE summary="" cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td width="100%">		

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">

		<tr>			
			<td colspan="3" class="formMessage" align="left">* - Indicates a required field</td>
		</tr>

		<tr>
			<td class="formTitle" height="20" colspan="3">Register for an account</td>
			<!-- <td class="formMessage" align="left" FONT="9"><a href="advancedsearch.html">Advanced Search <a> </td> -->
		</tr>

		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formRequiredLabel"><label for="field1">* First Name</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formRequiredLabel"><label for="field1">* Last Name</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formLabel" ><label for="field1">Institute</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formRequiredLabel"><label for="field1">* PI's Name: <br>(lastname, firstname)</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>
				
		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formLabel"><label for="field1">Address 1</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formLabel"><label for="field1">Address 2</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formLabel"><label for="field1">City</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formLabel" ><label for="field1">State / Province </label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formLabel"><label for="field1">Zip</label></td>
			<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" /></td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formLabel"><label for="field1">Country</label></td>
			<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>		

		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formLabel"><label for="field1">Phone</label></td>
			<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>		
		
		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formLabel" ><label for="field1">Fax </label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formRequiredLabel"><label for="field1">* Email</label></td>
			<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>				

		<tr>
			<td align="right" colspan="3">
				<!-- action buttons begins -->
				<TABLE cellpadding="4" cellspacing="0" border="0">
					<tr>
						<td>                                                    
                                                        <input class="actionButton" type="submit" value="Submit" />                                                    
						</td>
						
						<td><input class="actionButton" type="reset" value="Reset" /></td>
					</tr>
				</TABLE>
			</td>
		</tr>
	</TABLE>
	</td></tr>
	    
</TABLE>

<!-- Main Content Ends  -->

<%@ include file="footer.jsp" %>