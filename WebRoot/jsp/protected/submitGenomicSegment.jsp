<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>

<form name="input" action="submitOverview.jsp" method="get">

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0">
		<tr>
			<td class="formMessage" colspan="3">* indicates a required field</td>
		</tr>
		
		<tr>
			<td class="formTitle" height="20" colspan="6">Genomic Segment</td>				
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel"><label for="field3">Integration</label></td>
			<td class="formField">
				<input id="radio1" name="radio5" checked="checked" type="radio"> <label for="field5">Random</label>
				<br>
				<input id="radio2" name="radio5" type="radio"> <label for="field5">Targeted</label>
			</td>
		</tr>		
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Location of Integration</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">*</td>

			<td class="formLabel"><label for="field3"><b>Segment Type</b></label></td>
			<td class="formField">
				<select class="formFieldSized" name="field3" id="field3" size="1">
					<option value="option1"></option>
					<option value="option2">Other</option>
					<option value="option3">Cosmid</option>
					<option value="option4">YAC</option>
					<option value="option3">BAC</option>
					<option value="option4">Plasmid</option>
				</select>
				<br>If Segment Type is not listed, then please select "Other"<br> from the above selection list and then specify it below: 
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Other Segment Type</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Segment Size</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Designator</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>


		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Gene(s)<br>(separate multiple entries by comma)</label></td>
			<td class="formField"><textarea class="formFieldSized" name="field2" id="field2" cols="32" rows="4"></textarea></td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Markers(s)<br>(separate multiple entries by comma)</label></td>
			<td class="formField"><textarea class="formFieldSized" name="field2" id="field2" cols="32" rows="4"></textarea></td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Comments</label></td>
			<td class="formField"><textarea class="formFieldSized" name="field2" id="field2" cols="32" rows="4"></textarea></td>
		</tr>				

                <tr>
                        <td class="formRequiredNotice" width="5">&nbsp;</td>
                        <td class="formLabel"><label for="field2"><a href="http://www.informatics.jax.org/">MGI Number</a></label></td>
                       <td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" /></td>
                </tr>	
		
		<tr>
			<td class="formTitle" height="20" colspan="6">&nbsp;</td>				
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Upload Construct Map (Image)</label></td>
			<td class="formField">
				<input class="formFieldSized" type="text" name="field1" id="field1" size="30" />
				<input class="actionButton" type="submit" value="Browse" />
			</td>			
		</tr>		

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Title of Construct<br>(Enter info only when uploading image)</label></td>
			<td class="formField"><textarea class="formFieldSized" name="field2" id="field2" cols="32" rows="4"></textarea></td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Description of Construct<br>(Enter info only when uploading image)</label></td>
			<td class="formField"><textarea class="formFieldSized" name="field2" id="field2" cols="32" rows="4"></textarea></td>
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

<%@ include file="footer.jsp" %>





