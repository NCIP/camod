<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page import="gov.nih.nci.camod.webapp.form.MicroArrayDataForm" %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<script language="JavaScript" src="scripts/EVSTreeScript.js"></script>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>

<%
	String aMicroArrayDataID = request.getParameter( "aMicroArrayDataID" );
	String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
	
	//if aCellID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "MicroArrayDataAction.do?method=save";
	
    if ( aMicroArrayDataID != null && aMicroArrayDataID.length() > 0 && isDeleted == null) {
		actionName = "MicroArrayDataAction.do?method=edit";
	}
	else {
	    aMicroArrayDataID = "";
	}
%>

<html:form action="<%= actionName %>" focus="experimentName">


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
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Experiment Name:</label>
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" size="30" property="experimentName" />			
		</td>
	</tr>
		
	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">URL for Microarray data not stored in caArray:</label>
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" size="30" property="url" />			
		</td>
	</tr>

	<tr>
		<td align="right" colspan="3">
			<!-- action buttons begins -->
				<TABLE cellpadding="4" cellspacing="0" border="0">
			
				  <html:submit styleClass="actionButton">
					  <bean:message key="button.submit"/>
				  </html:submit>
				  
				  <html:reset styleClass="actionButton">
				  	  <bean:message key="button.reset"/>
  				  </html:reset>
	  				  
  				  <c:if test="${not empty aMicroArrayDataID}">
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
			      </c:if>
					  
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <input type="hidden" name="aMicroArrayDataID" value="<%= aMicroArrayDataID %>">
				  
				  </html:form>			
				</TABLE>
			<!-- action buttons end -->
		</td>
	</tr>	
	
	<!-- Misc -->
	<tr>
		<td class="GreyBoxTop" height="20" colspan="3">
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