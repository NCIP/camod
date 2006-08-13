<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

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
			<td class="formTitle" height="20" colspan="3">Publications
				<camod:cshelp topic="publication_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field1">Name of Cell Line:</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Organ/Tissue:</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1"><a href="http://www.atcc.org" target="_blank">ATCC </a>number of Cell Line:</label></td>
			<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" /></td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Experiment:</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Results:</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Comments:</label></td>
			<td class="formField"><textarea class="formFieldSized" name="field2" id="field2" cols="32" rows="4"></td>
		</tr>

		<tr>
			<td align="right" colspan="3">
				<!-- action buttons begins -->

				<table cellpadding="4" cellspacing="0" border="0">
				<tr>
					<td><input class="actionButton" type="submit" value="Submit" /></td>
					<td><input class="actionButton" type="reset" value="Reset" /></td>
				</tr>
				</table>
				<!-- action buttons end -->
			</td>
		</tr>
	</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>