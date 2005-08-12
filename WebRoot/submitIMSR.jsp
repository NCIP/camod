<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
<!-- -->

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">
	
	<tr>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>
	
	<tr>
		<td class="formTitle" height="20" colspan="3">Enter IMSR (International Mouse Strain Resource ) </td>
	</tr>
	
        <tr>
                <FORM name="input" action="http://www.informatics.jax.org/imsr/index.jsp" method="get">
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Strain Name:</label></td>
		<td class="formField"><input class="actionButton" type="submit" value="Find Info" />&nbsp;<input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
                </FORM>
	</tr>
	
        <tr>
                <td class="formRequiredNotice" width="5">*</td>
                <td class="formRequiredLabel">Stock#</td>
                <td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" /></td>
        </tr>
        
	<tr>
		<td align="right" colspan="3">
			<!-- action buttons begins -->
			<TABLE cellpadding="4" cellspacing="0" border="0">

				<tr>
					<td><input class="actionButton" type="submit" value="Submit" /></td>
					<td><input class="actionButton" type="reset" value="Reset" /></td>
				</tr>
			</TABLE>
			<!-- action buttons end -->
		</td>
	</tr>
</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="footer.jsp" %>













