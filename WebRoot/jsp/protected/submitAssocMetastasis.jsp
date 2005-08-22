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
		<td class="formTitle" height="20" colspan="3">Enter Information for Associated Metastasis</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="0">*</td>
		<td class="formRequiredLabel"><label for="field2">Site of Lesion/Tumor</label>&nbsp;<IMG src="images\selectUP.gif" align=middle></td>
		<td class="formField"><input class="formFieldSized" type="text" disabled="true" name="field3" id="field3" size="25" /></td>
	</tr>
	<tr>
		<td class="formRequiredNotice" width="0">*</td>
		<td class="formRequiredLabel"><label for="field2">Diagnosis</label>&nbsp;<IMG src="images\selectUP.gif" align=middle></td>
		<td class="formField"><input class="formFieldSized" type="text" disabled="true" name="field3" id="field3" size="25" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Age of Metastasis Onset:</label></td>
		<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Average Weight of Metastasis (mg):</label></td>
		<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Average Volume of Metastasis (mm<sup>3</sup>) </label></td>
		<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" /></td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Metastasis Incidence over lifetime(%)<br>(Enter numbers only)</label></td>
		<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" /></td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Survival Information:</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Gross Description / Macroscopic Lesion:</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Microscopic Description (Field holds 2,000 characters)</label></td>
		<td class="formField"><textarea class="formFieldSized" name="field2" id="field2" cols="32" rows="4"></textarea></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Genetic Alterations found in the Tumor:</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>	
	
        <tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Human Data:</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Comments:</label></td>
		<td class="formField"><textarea class="formFieldSized" name="field2" id="field2" cols="32" rows="5"></textarea></td>
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