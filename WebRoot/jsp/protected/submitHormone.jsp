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
		<td class="formTitle" height="20" colspan="3">Enter Information for Hormone Treatment: </td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Hormone:</label></td>
		<td class="formField">
			<br>
			<label for="field3">(if Hormone is not listed, then please<br>select "Other" from the list and specify it below)</label>
			<br>
			<br>
			<select class="formFieldSized" name="field3" id="field3" multiple="true" size="4">
                                    <OPTION value="">
                                    <OPTION value="0" >Other
                                    <OPTION value="10" >19-nor-progesterone
                                    <OPTION value="11" >androsterone
                                    <OPTION value="12" >bovine pituitary extract
                                    <OPTION value="13" >cortisone
                                    <OPTION value="14" >dehydroepiandrosterone (DHEA)
                                    <OPTION value="146" >diethylstilbestrol (DES)
                                    <OPTION value="147" >dihydrotestosterone (DHT)
                                    <OPTION value="148" >estradiol (17beta-estradiol) (E2)
                                    <OPTION value="149" >estradiol benzoate
                                    <OPTION value="150" >estradiol dipropionate
                                    <OPTION value="151" >estrogen
                                    <OPTION value="152" >estrone
                                    <OPTION value="153" >horse anti-mouse anitlymphocyte serum (HALS)
                                    <OPTION value="154" >human chorionic gonadotropin (hCG)
                                    <OPTION value="155" >male gonadal ridge implantation
                                    <OPTION value="156" >normal horse serum (NHS)
                                    <OPTION value="157" >ovarian implantation
                                    <OPTION value="158" >ovariectomy
                                    <OPTION value="159" >ovariectomy - incomplete
                                    <OPTION value="160" >ovariectomy - unilateral
                                    <OPTION value="161" >pituitary isograft
                                    <OPTION value="162" >pregnant mare serum gonadotropin (PMSG)

                                    <OPTION value="163" >progesterone
                                    <OPTION value="164" >testosterone
			</select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Hormone:</label></td>
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

<%@ include file="/jsp/footer.jsp" %>