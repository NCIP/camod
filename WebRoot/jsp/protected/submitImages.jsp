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
			<td class="formTitle" height="20" colspan="3">Images</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field1">Upload Image</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" />
			<input class="actionButton" type="submit" value="Browse..." />
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field1">Image Title</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" />
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Description of Image</label></td>
			<td class="formField"><textarea class="formFieldSized" name="field2" id="field2" cols="32" rows="4"></textarea></td>
		</tr>
		
                <tr>
                        <td class="formRequiredNotice" width="5">&nbsp;</td>
                        <td class="formLabel"><label for="field2">Staining</label></td>
                        <td class="formField">
                        <select class="formFieldSized"  name="field2" id="field2" size="1">
                                <option value="option1"></option>
                                <option value="option2">adenovirus</option>
                                <option value="option3">lentivirus</option>
                                <option value="option4">Retrovirus</option>
                                <option value="option5">Other</option>
                        </select>			
                        </td>
                </tr>
                
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Other Stainings:</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
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

<%@ include file="/jsp/footer.jsp" %>