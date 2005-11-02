<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<FORM name="input" action="http://caarraydb.nci.nih.gov" method="get">

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
<!-- -->

	<TABLE summary="" cellpadding="6" cellspacing="0" border="0" align="left">
	
	<tr>
		<td class="formTitle" height="20" colspan="3">Microarray Data &nbsp;<camod:cshelp mapId="microarray_data_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
	</tr>

	<tr>
		<td class="greyBox" height="20" colspan="3">
    <b>Microarray data is stored in the caArray portal. Follow these simple steps to upload micro-array data to caArray.
    <ol >
    	<li style="padding-bottom:7px">Click <a href="http://caarraydb.nci.nih.gov/" target="_caArray">here</a> to login into caArray. A new browser window will be opened (please turn off any popup blockers for this site) <br/>
    		If you do not have a login userId (or have forgotten your id), please follow the instructions on the caArray page to obtain an account.
    	</li>
    	<li style="padding-bottom:7px">
    		Submit micro-array data by following the on-screen instructions. (refer to the caArray user guide if you need additional help)
    	</li>
    	<li style="padding-bottom:7px">
    		Set the visibility of the micro-array experiment to "public".
    	</li>
    	<li style="padding-bottom:7px">
    		When you are done completing the upload of micro-array data to caArray, please make a note of <br/>
    		(a) The experiment name (b) The system generated "Experiment Id" (looks like 10007638)
    	</li>
    	<li style="padding-bottom:7px">
    		Logout of the caArray application and close the caArray window.
    	</li>
    	<li style="padding-bottom:7px">
    		Email caMOD development team (mailto:mmhcc-dev@list.nih.gov) requesting that the micro-array data be associated
    		with your cancer model. Please include the micro-array experiment name(s) and the experiment Id(s) in your email.
    	</li>
    	<li style="padding-bottom:7px">
    		The micro array data will be linked to your cancer model within 24 hours (excluding holidays). You will be notified 
    		via email when this task is completed.
    	</li>
    </ol>
    </b>
        The application accepts the submission of Affymetrix and Spotted Array Experiments.<br><br>
    <ul style="list-style-type: square">
    <li style="padding-bottom:8px">If you are submitting an Affymetrix experiment you can upload the various Affymetrix Files (*.dat, *.cel, *.chp, *.txt). All the data is stored in the MIAME complaint caArray database and available for retrieval at a later time.</li>
    <li style="padding-bottom:8px">Spotted Array Experiments must be accompanied by a Gene Array List (*.gal) file and a GenePix Results (*.gpr) file. Additional file formats will be added in response to community feedback.</li>
    </ul>
		</td>
	</tr>

</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>