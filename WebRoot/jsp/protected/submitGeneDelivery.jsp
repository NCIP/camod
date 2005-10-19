<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page import="gov.nih.nci.camod.webapp.form.GeneDeliveryForm" %>	
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<script language="JavaScript" src="scripts/EVSTreeScript.js"></script>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>

<%
	String aTherapyID = request.getParameter( "aTherapyID" );
	
	//if aTherapyID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "GeneDeliveryAction.do?method=save";
	
	if ( aTherapyID != null  ) {
		actionName = "GeneDeliveryAction.do?method=edit";
	}
%>


<!-- using chkOtherName and name for viralVector would conflict with the organ name variable -->

<SCRIPT LANGUAGE="JavaScript">

	function chkOtherViralVector( control ) {
		ideControl = document.forms[0].otherViralVector;
			
		if( control.value == 'Other' )
			ideControl.disabled = false;
		else {
			ideControl.value = null;
			ideControl.disabled = true;
		}
	}
	
	
	
</script>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
<!-- -->

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">
	
	<tr>
		<html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>
	
	<tr>
		<td class="formTitle" height="20" colspan="3">Gene Delivery</td>
	</tr>

        <tr>
            <td class="formRequiredNotice" width="5">&nbsp;</td>
            <td class="formLabel"><label for="field2">Viral Vector:</label>
            <camod:cshelp key="GENE_DELIVERY.VIRAL_VECTOR" image="images/iconHelp.gif" text="Tool Tip Test 1" />
            </td>
            <td class="formField">
            <label for="field3">(if Viral Vector is not listed, then please <br>select "Other" from the list and specify it below)</label>
            <br>		
			<br>
			<html:form action="<%= actionName %>" focus="name" >	
			
			<html:select styleClass="formFieldSized" size="1" property="viralVector" name="formdata" onclick="chkOtherViralVector( this );">
				<html:options name="<%= Dropdowns.VIRALVECTORDROP %>"/>					
			</html:select>
			
			</td>
        </tr>
        
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Viral Vector:</label></td>
		<td class="formField">					
			<html:text styleClass="formFieldSized" size="30" property="otherViralVector" onclick="checkOthers( this );"/>			
		</td>
	</tr>        
        
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Gene:</label>
		<camod:cshelp key="GENE_DELIVERY.GENE_IN_VIRUS" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">		
			<html:text styleClass="formFieldSized" size="30" property="geneInVirus" name="formdata"  />
		</td>				
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Treatment Regimen:</label>
		<camod:cshelp key="TREATMENT.REGIMEN" image="images/iconHelp.gif" text="Tool Tip Test 1" />		
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="regimen" size="30" name="formdata"/>
		</td>
	</tr>
	
		<!-- The evs# is inserted into Organ.conceptCode, the the OrganID is inserted   -->
	<tr>
		<td class="formRequiredNotice" width="0">*</td>
		<td class="formRequiredLabel"><label for="field2">Location of Delivery:</label>
		  	    <a href="javascript:showTissueTree('geneDeliveryForm', 'descendants=true;isaFlag=false;depthLevel=6;roleType=Anatomic_Structure_is_Physical_Part_of', 3)">
				<IMG src="images\selectUP.gif" align=middle border=0>
				</a>
				
				<INPUT name="organTissueName" type="hidden"/>
		 		<INPUT name="organTissueCode" type="hidden"/>
			</td>
			<td class="formField"><input class="formFieldSized" type="text"  name="organ" id="organFieldId" size="40"  /></td>
		</tr>
		
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Age:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="ageAtTreatment"  size="10" name="formdata"/>
			<label for="field1">&nbsp;Units&nbsp;</label>
			<html:select styleClass="formFieldUnSized" size="1" property="ageUnit" name="formdata">												
				<html:options name="<%= Dropdowns.AGEUNITSDROP %>"/>					
			</html:select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Gender:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldUnSized" size="1" property="type" name="formdata">												
				<html:options name="<%= Dropdowns.SEXDISTRIBUTIONDROP %>"/>					
			</html:select>
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
	  				  
					  <!--  Done this way since html:hidden doesn't seem to work correctly -->
  				  	  <input type="hidden" name="aTherapyID" value="<%= aTherapyID %>">
				  
				  </html:form>			
				</TABLE>
			<!-- action buttons end -->
		</td>
	</tr>
</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<SCRIPT>
	function checkOthers()
	{
	    ideControl = document.forms[0].viralVector;
	    ideOtherControl = document.forms[0].otherViralVector;
			
		if( ideControl.value == 'Other' )
			ideOtherControl.disabled = false;
		else {
			ideOtherControl.disabled = true;
		}
		
	
	checkOthers();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>