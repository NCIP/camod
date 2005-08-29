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
		<td class="formTitle" height="20" colspan="3">Enter Information for Induced Mutation:</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel">Inducing Agent Category</td>
		<td class="formField">
		<select class="formFieldSized" name="field3" id="field3" size="1">
			<option value="option2"></option>	
			<option value="option1">Other</option>
			<option value="option2">Radiation</option>
			<option value="option2">Chemical</option>
			<option value="option2">Transgene</option>
		</select>
		<br>
		-if category you are looking for is not listed, <br>select "Other" and enter the category in the text field below:
		</td>

	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Category</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">CAS number <br>(if inducing agent is "Chemical")</label></td>
		<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" /></td>
	</tr>
	
	<tr>
                <FORM name="input" action="http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?db=gene" method="get">
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Gene ID ( Entrez )</label></td>
		<td class="formField"><input class="actionButton" type="reset" value="Find ID" />&nbsp;<input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" /></td>
                </FORM>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Name of Inducing Agent</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Description:</label></td>
		<td class="formField"><textarea class="formFieldSized" name="field2" id="field2" cols="32" rows="4"></textarea></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Mutated Locus/Gene(if known)<br></label></td>
		<td class="formField"><textarea class="formFieldSized" name="field2" id="field2" cols="32" rows="4"></textarea></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2"><a href="http://www.informatics.jax.org/">MGI Number</a></label></td>
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

<%@ include file="/jsp/footer.jsp" %>