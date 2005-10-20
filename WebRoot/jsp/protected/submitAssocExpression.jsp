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
		<td class="formTitle" height="20" colspan="3">Organ / Tissue / Cell type (Associated Expression):</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="0">*</td>
		<td class="formRequiredLabel"><label for="field2">Organ / Tissue:</label>&nbsp;
		<camod:cshelp key="ORGAN.CONCEPT_CODE" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		<IMG src="images\selectUP.gif" align=middle></td>
		<td class="formField"><input class="formFieldSized" type="text" disabled="true" name="field3" id="field3" size="25" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Expression Level:</label></td>
		<td class="formField">				
                                <select class="formFieldSized" name="field3" id="field3" size="1">
                                        <OPTION value=""></OPTION>
                                        <OPTION value="1">Other</OPTION>
                                        <OPTION value="8">1</OPTION>
                                        <OPTION value="9">2</OPTION>
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

<%@ include file="/jsp/footer.jsp" %>