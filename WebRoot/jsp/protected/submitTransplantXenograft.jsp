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
		<td class="formTitle" height="20" colspan="3">Xenograft/Transplantation</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Cell Line/Transplant:</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>

	<tr>
                <FORM name="input" action="http://www.atcc.org" method="get">
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">ATTC number (if available):</label></td>
		<td class="formField"><input class="actionButton" type="submit" value="Find ATTC #" />&nbsp;<input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" /></td>
                </FORM>      		
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Species</label></td>
		<td class="formField">
			<select class="formFieldSized" name="field3" id="field3" size="1">
                                    <OPTION value="">
                                    <OPTION value="0" >Other
                                    <OPTION value="1" >(Human)Homo Sapiens
                                    <OPTION value="2" >(Mouse)Mus Musculus
                                    <OPTION value="3" >(Cattle)Bos Taurus
                                    <OPTION value="4" >(Dog)Canis Familiaris
                                    <OPTION value="5" >(Goat)Capra Hircus
                                    <OPTION value="6" >(Horse)Equus Caballus
                                    <OPTION value="7" >(Sheep)Ovis Aries
                                    <OPTION value="8" >(Rat)Rattus Norvegicus
                                    <OPTION value="9" >(Rat)Rattus Rattus
                                    <OPTION value="10" >(Pig)Sus Scrofa
                                    <OPTION value="11" >(Cat)Felis Catus
			</select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Parental Cell line:</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Mice Age:</label></td>
		<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" /></td>
	</tr>

        <tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Amount of Cells</label></td>
		<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" /></td>
	</tr>	
	
        <tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Tumor harvest date after transplant:</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Transplant route</label></td>
		<td class="formField">
			<select class="formFieldSized" name="field3" id="field3" size="1">
				<option value="option1"></option>
				<option value="option2">Option 1</option>
				<option value="option2">Option 2</option>
				<option value="option2">Option 3</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Method of Modification:</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Genetic Alteration:</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Host Species</label></td>
		<td class="formField">
			<select class="formFieldSized" name="field3" id="field3" size="1">
                                    <OPTION value="">
                                    <OPTION value="0" >Other
                                    <OPTION value="1" >(Human)Homo Sapiens
                                    <OPTION value="2" >(Mouse)Mus Musculus
                                    <OPTION value="3" >(Cattle)Bos Taurus
                                    <OPTION value="4" >(Dog)Canis Familiaris
                                    <OPTION value="5" >(Goat)Capra Hircus
                                    <OPTION value="6" >(Horse)Equus Caballus
                                    <OPTION value="7" >(Sheep)Ovis Aries
                                    <OPTION value="8" >(Rat)Rattus Norvegicus
                                    <OPTION value="9" >(Rat)Rattus Rattus
                                    <OPTION value="10" >(Pig)Sus Scrofa
                                    <OPTION value="11" >(Cat)Felis Catus
			</select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Host Strain</label></td>
		<td class="formField">
		<br>
			<select class="formFieldSized" name="field3" id="field3" size="1">
                                <OPTION value="">
                                <OPTION value="0" >Other
                                <OPTION value="1" >(Human)Homo Sapiens
                                <OPTION value="2" >(Mouse)Mus Musculus
                                <OPTION value="3" >(Cattle)Bos Taurus
                                <OPTION value="4" >(Dog)Canis Familiaris
                                <OPTION value="5" >(Goat)Capra Hircus
                                <OPTION value="6" >(Horse)Equus Caballus
                                <OPTION value="7" >(Sheep)Ovis Aries
                                <OPTION value="8" >(Rat)Rattus Norvegicus
                                <OPTION value="9" >(Rat)Rattus Rattus
                                <OPTION value="10" >(Pig)Sus Scrofa
                                <OPTION value="11" >(Cat)Felis Catus
			</select>
		<br>
		<br>
		- if strain is not listed then please select "Other" from the above Strain<br>selection list and then specify it below
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">if other Strain:</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Site of Administration:</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Graft Type</label></td>
		<td class="formField">
			<select class="formFieldSized" name="field3" id="field3" size="1">
				<option value="option1"></option>
				<option value="option2">Type 1</option>
				<option value="option2">Type 2</option>
				<option value="option2">Other</option>
			</select>
		<br>
		<br>
		- if graft type is not listed then please select "Other" from the above <br>selection list and then specify it below						
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">If other Graft Type:</label></td>
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