<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	
<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center">
		<tr>

			<td class="formMessage" colspan="3">* indicates a required field</td>
		</tr>
		
		<tr>
			<td class="formTitle" height="20" colspan="3">Radiation</td>		
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>	
			<td class="formRequiredLabel"><label for="field3">Radiation</label></td>
			<td class="formField">
			<br>
			<label for="field3">(if Radiation is not listed, then please<br>select "Other" from the list and specify it below)</label>
			<br>
			<br>
			<select class="formFieldSized" name="field3" id="field3" size="1">
                                    <OPTION value="">
                                    <OPTION value="0" >Other
                                    <OPTION value="22" >Californium-252 (Cf-252) fission neutron radiation
                                    <OPTION value="25" >Iodine-131 (I-131) (131I)

                                    <OPTION value="166" >Plutonium-239 (Pu-239) (239Pu)
                                    <OPTION value="168" >Radium-224 (Ra-224) (224Ra)
                                    <OPTION value="169" >Radium-226 (Ra-226) (226Ra)
                                    <OPTION value="171" >Strontium-90 (Sr-90) (90Sr)
                                    <OPTION value="175" >X-radiation
                                    <OPTION value="20" >alpha-radiation
                                    <OPTION value="21" >beta-radiation
                                    <OPTION value="23" >gamma-radiation
                                    <OPTION value="24" >heavy ion radiation
                                    <OPTION value="26" >ionizing radiation
                                    <OPTION value="165" >neutron-radiation
                                    <OPTION value="167" >radiation (unspecified type)
                                    <OPTION value="170" >radon
                                    <OPTION value="172" >ultraviolet radiation (UV)
                                    <OPTION value="173" >ultraviolet-B radiation (UVB)
                                    <OPTION value="174" >uranium ore dust
			</select>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Other Radiation:</label></td>
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
	</td></tr></TABLE>	
</td></tr></TABLE>

<%@ include file="/jsp/footer.jsp" %>
