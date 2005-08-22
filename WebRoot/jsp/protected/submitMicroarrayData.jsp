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
		<td class="formTitle" height="20" colspan="3">Microarray Data </td>
	</tr>

	<tr>
		<td class="greyBox" height="20" colspan="3">
                        The application accepts the submission of Affymetrix and Spotted Array Experiments.<br><br>
                        * If you are submitting an Affymetrix experiment you can upload the various Affymetrix Files (*.dat, *.cel, *.chp, *.txt). The *.txt file is parsed and the data is then stored in the database. All other files are stored in the file system for retrieval at a later time.<br><br>
                        * Spotted Array Experiments must be accompanied by a Gene Array List (*.gal) file and a GenePix Results (*.gpr) file. Additional file formats will be added in response to community feedback. <br><br>
                        If you have all the required infomation, click on <input class="actionButton" type="submit" value="Proceed" />.<br><br>
                        <b>Microarray data is stored in the Gene Expression Data Portal(Director's Challenge).</b>
		</td>
	</tr>

	<tr>
		<td class="whiteBox" height="20" colspan="3">
                        If your data is stored in another location enter the URL below <br><br>
                       <input class="formFieldSized" type="text" name="field1" id="field1" size="30" />&nbsp;<input class="actionButton" type="submit" value="GO" /><br><br>
		</td>
	</tr>
</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>