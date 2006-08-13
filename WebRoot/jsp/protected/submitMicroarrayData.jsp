<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>

<FORM name="input" action="http://caarraydb.nci.nih.gov" method="get">


<!-- submitMicroarrayData.jsp -->
<!-- Main Content Begins -->
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
<!-- -->

	<TABLE summary="" cellpadding="6" cellspacing="0" border="0" align="left">
	
	<tr>
		<td class="formTitle" height="20" colspan="3">Microarray Data &nbsp;
		<camod:cshelp topic="microarray_data_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
	</tr>

	<tr>
		<td class="greyBox" height="20" colspan="3">
    <b>Microarray data is stored in the caArray portal. Follow these simple steps to upload microarray data to caArray.
    <ol >
    	<li style="padding-bottom:7px">Click <a href="http://caarraydb.nci.nih.gov/" target="_caArray">here</a> to login into caArray. A new browser window opens. (Be sure and turn off any pop-up blockers for this site.) <br/>
    		If you do not have a caArray login userID (or if you have forgotten your ID), follow the instructions of the caArray page to obtain an account.
    	</li>
    	<li style="padding-bottom:7px">
    		Submit microarray data by following the on-screen instructions. (For more information, refer to the <i>caArray User's Guide</i>, downloadable from <a href="http://ncicb.nci.nih.gov/download/downloadcaarray.jsp" target="_caArray">here</a>
    	</li>
    	<li style="padding-bottom:7px">
    		Set the visibility of the micro-array experiment to <b>Public</b>.
    	</li>
    	<li style="padding-bottom:7px">
    		When you have finished uploading the microarray data to caArray, record the following: <br/>
    		&nbsp;&nbsp;&nbsp;&nbsp;a. The Experiment name<br/>&nbsp;&nbsp;&nbsp;&nbsp;b. The system-generated Experiment ID (looks like 10007638).
    	</li>
    	<li style="padding-bottom:7px">
    		Log out of the caArray application and close caArray.
    	</li>
    	<li style="padding-bottom:7px">
    		<a href="mailto:mmhcc-dev@list.nih.gov">Email</a> the caMOD development team requesting that the microarray data be associated with your cancer model. Include the microarray experiment name(s) and the experiment ID(s) in your email.
    	</li>
    	<li style="padding-bottom:7px">
    		The microarray data will be linked to your cancer model within 24 hours (excluding Government holidays and weekends). You will be notified by email when this task is completed.
    	</li>
    </ol>
    </b>
        The application accepts the submission of Affymetrix and Spotted Array experiments.<br><br>
    <ul style="list-style-type: square">
    <li style="padding-bottom:8px">If you are submitting an Affymetrix experiment, you can upload the various Affymetrix files (*.dat, *.cel, *.chp, *.txt.). All the data is stored in the MIAME-compliant caArray database, available for retrieval at a later time.</li>
    <li style="padding-bottom:8px">Spotted Array experiments must be accompanied by a Gene Array List (*.gal) file and a GenPix results (*.gpr) file. Additional file formats will be added in response to community feedback.</li>
    </ul>
		</td>
	</tr>

</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>