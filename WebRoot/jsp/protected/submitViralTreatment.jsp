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
		<td class="formTitle" height="20" colspan="3">Viral Treatment</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Virus</label></td>

		<td class="formField">
		<br>
		<label for="field3">(if Virus is not listed, then please<br>select "Other" from the list and specify it below)</label>
		<br>
		<br>
			<select class="formFieldSized" name="field3" id="field3" size="1">
                                <OPTION value="">
                                <OPTION value="0" >Other
                                <OPTION value="15" >A-MuLV (Abelson murine leukemia virus)
                                <OPTION value="16" >Adenovirus (recombinant, expressing Cre recombinase)
                                <OPTION value="17" >Adenovirus (recombinant, expressing LacZ)
                                <OPTION value="18" >BALB/Tennant leukemia virus
                                <OPTION value="19" >Friend leukemia virus
                                <OPTION value="177" >Friend leukemia virus - "conventional" (CFV)
                                <OPTION value="176" >Friend leukemia virus - "regressing" (RFV)
                                <OPTION value="178" >Graffi MuLV (Graffi murine leukemia virus)
                                <OPTION value="179" >Gross leukemia virus
                                <OPTION value="180" >MLV (M-MuLV) (MoMuLV) (Moloney murine leukemia virus)
                                <OPTION value="181" >MMTV (mouse mammary tumor virus)
                                <OPTION value="182" >MoMuLV-TB (the TB strain of Moloney murine leukemia virus)
                                <OPTION value="184" >NIV (nodule-inducing virus)
                                <OPTION value="185" >R7 (a MoMuSV 124 variant)
                                <OPTION value="183" >murine retrovirus containing human PDGFB cDNA
                                <OPTION value="186" >vaccinia virus
                                <OPTION value="187" >vaccinia virus (inactivated)
			</select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Virus:</label></td>
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
		<td class="formLabel"><label for="field3">Administrative Routes:</label></td>
		<td class="formField">
			<br>
			<label for="field3">- if Administration Route is not listed, <br>then please select "Other" and then specify it below:</label>
			<br>
			<br>
			<select class="formFieldSized" name="field3" id="field3" size="1">
				<Br>
				<option value="option1">Mouse (mus muculus)</option>
				<option value="option2">Rat</option>
			</select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Administrative Route:</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
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