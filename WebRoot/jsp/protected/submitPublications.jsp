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
		<td class="formTitle" height="20" colspan="3">Publications</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">First Author:</label></td>
		<td class="formField"><label>(e.g. Doe JR)<br><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Publication Status:</label></td>
		<td class="formField">
			<select class="formFieldUnSized" name="field3" id="field3" size="1">
				<option value="option1"></option>
				<option value="option2">Published</option>
				<option value="option3">Unpublished</option>
			</select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Is this the publication in which the model was reported for the first time?</label></td>
		<td class="formField">
		                <input type="radio" id="radio1" name="radio1" /> <label for="field1">Yes</label>			
                                <input type="radio" id="radio1" name="radio2" checked="checked" /> <label for="field1">No</label>
		</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel2">
		For publications with a PubMed record, <br>look up the Pubmed Identifier (PMID number),<br> enter it in the PMID field and <br>click the "Fill in Fields" button. <br>The program will retrieve the citation <br>data from PubMed and populate the fields<br> automatically. Click "Save Data"<br>to submit the publication to the database.
		</td>
		<td class="formField">
		<label valign="TOP" for="field1"><a href="">Click to look up PubMed Identifier</a></label><br>
		<br>
		<br>
		<label valign="TOP" for="field1">PMID &nbsp;</label><br><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" />
		&nbsp; <input class="actionButton" type="submit" value="Fill in Fields" />
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Title of Publication</label></td>
		<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Year of Publication:</label></td>
		<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Journal:</label></td>
		<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Volume:</label></td>
		<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Start Page:</label></td>
		<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">End Page:</label></td>
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