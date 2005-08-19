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
		<td class="formTitle" height="20" colspan="3">Enter Information for Growth Factor Treatment </td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Growth Factor:</label></td>
		<td class="formField">
			<br>
			<label for="field3">(if Growth Factor is not listed, then please<br>select "Other" from the list and specify it below)</label>
			<br>
			<br>
			<select class="formFieldSized" name="field3" id="field3" multiple="true" size="1">
                                <OPTION value="0" >Other
                                <OPTION value="9" >insulin-like growth factor 1 (IGF1) (IGF-1) (human recombinant)			
			</select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Growth Factor:</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Dose:</label></td>
		<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" />&nbsp;
                    <select class="formFieldUnSized" name="field3" id="field3" size="1">
                            <OPTION value="">mg
                            <OPTION value="0" >kg
                            <OPTION value="552" >cg
                    </select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Treatment Regimen:</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Age:</label></td>
		<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" />&nbsp;
                    <select class="formFieldUnSized" name="field3" id="field3" size="1">
                            <OPTION value="">Months
                            <OPTION value="0" >Days
                            <OPTION value="552" >Weeks
                            <OPTION value="553" >Years
                    </select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Gender:</label></td>
		<td class="formField">
			<select class="formFieldSized" name="field3" id="field3" size="1">
				<br>
				<option value="option1">male</option>
				<option value="option2">female</option>
			</select>
		</td>
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