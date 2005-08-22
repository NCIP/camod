<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" width="100%">

		<tr>
			<td class="formTitle" height="20" colspan="5">Comment Screening</td>		
		</tr>

		<tr>
			<td class="resultsBoxGreyEnd" colspan="5" align="right">
				View by:&nbsp;
				<select class="formFieldSized"  name="field2" id="field2" size="1">
					<option value="option1">Comments: Screener-assigned</option>
					<option value="option2">Comments: Screened-approved</option>
					<option value="option3">Comments: Screened-rejected</option>
					<option value="option4">Comments: Screened-not screened</option>
				</select>			
				<input class="actionButton" type="submit" value="View Comments" />
			</td>
		</tr>
	<TABLE>
	
	<br>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" width="100%">
		
		<tr>
			<td class="formTitleBlue" height="20" colspan="5">Viewing - Comments: Screened-approved</td>		
		</tr>
		
		<tr>
			<td class="greySubTitleLeft" width="5%"><b>No</b></td>
			<td class="greySubTitleLeft" width="30%"><b>Model Descriptor</b></td>
			<td class="greySubTitleLeft" width="25%"><b>Submitter's Name</b></td>
			<td class="greySubTitleLeft" width="20%"><b>Entered on</b></td>
			<td class="greySubTitle" width="20%"><b>Commented Page(s)</b></td>
		</tr>
		
		<tr>
			<td class="resultsBoxWhite" width="5%">1</td>
			<td class="resultsBoxWhite" width="30%"><a href="">12T-10 (LPB-Tag)</a></td>
			<td class="resultsBoxWhite" width="25%"><input type="checkbox" name="box1" id="box1" />&nbsp;<a href="">Schroedl, Nicholas J</a></td>
			<td class="resultsBoxWhite" width="20%">01/31/2005 03:53:27 PM</td>
			<td class="resultsBoxWhiteEnd" width="20%">Engineered Transgene</td>
		</tr>
		
		<tr>
			<td class="resultsBoxGrey" width="5%">2</td>
			<td class="resultsBoxGrey" width="30%"><a href="">12T-10 (LPB-Tag)</a></td>
			<td class="resultsBoxGrey" width="25%"><input type="checkbox" name="box1" id="box1" />&nbsp;<a href="">Schroedl, Nicholas J</a></td>
			<td class="resultsBoxGrey" width="20%">01/31/2005 03:53:27 PM</td>
			<td class="resultsBoxGreyEnd" width="20%">Engineered Transgene</td>
		</tr>

		<tr>
			<td class="resultsBoxWhite" width="5%">3</td>
			<td class="resultsBoxWhite" width="30%"><a href="">12T-10 (LPB-Tag)</a></td>
			<td class="resultsBoxWhite" width="25%"><input type="checkbox" name="box1" id="box1" />&nbsp;<a href="">Schroedl, Nicholas J</a></td>
			<td class="resultsBoxWhite" width="20%">01/31/2005 03:53:27 PM</td>
			<td class="resultsBoxWhiteEnd" width="20%">Engineered Transgene</td>
		</tr>
		
		<tr>
			<td class="resultsBoxGrey" width="5%">4</td>
			<td class="resultsBoxGrey" width="30%"><a href="">12T-10 (LPB-Tag)</a></td>
			<td class="resultsBoxGrey" width="25%"><input type="checkbox" name="box1" id="box1" />&nbsp;<a href="">Schroedl, Nicholas J</a></td>
			<td class="resultsBoxGrey" width="20%">01/31/2005 03:53:27 PM</td>
			<td class="resultsBoxGreyEnd" width="20%">Engineered Transgene</td>
		</tr>
		
	</TABLE>
</td></tr></TABLE>	

<%@ include file="/jsp/footer.jsp" %>





