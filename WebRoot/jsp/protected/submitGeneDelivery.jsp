<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page import="gov.nih.nci.camod.webapp.form.GeneDeliveryForm" %>	
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<script language="JavaScript" src="scripts/EVSTreeScript.js"></script>
<script language="JavaScript" src="scripts/global.js"></script>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>

<%
	String aCarcinogenExposureID = request.getParameter( "aCarcinogenExposureID" );
	String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
	//if aCarcinogenExposureID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "GeneDeliveryAction.do?method=save";
	
	if ( aCarcinogenExposureID != null && aCarcinogenExposureID.length() > 0 && isDeleted == null) {
		actionName = "GeneDeliveryAction.do?method=edit";
	}
	else {
	    aCarcinogenExposureID = "";
	}
%>

<html:form action="<%= actionName %>" focus="name" >


<!-- using chkOtherName and name for viralVector would conflict with the organ name variable -->

<SCRIPT LANGUAGE="JavaScript">

	function chkOtherViralVector() {
	    chkOther(document.forms[0].viralVector, 
	             document.forms[0].otherViralVector);
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
            <td class="formRequiredNotice" width="5">*</td>
            <td class="formRequiredLabel"><label for="field2">Viral Vector:</label>
            <camod:cshelp mapId="gene_delivery_help" key="GENE_DELIVERY.VIRAL_VECTOR" image="images/iconHelp.gif" text="Tool Tip Test 1" />
            </td>
            <td class="formField">
            <label for="field3">(if Viral Vector is not listed, then please <br>select "Other" from the list and specify it below)</label>
            <br>		
			<br>		
			<html:select styleClass="formFieldSized" size="1" property="viralVector" onclick="chkOtherViralVector();">
				<html:options name="<%= Dropdowns.VIRALVECTORDROP %>"/>					
			</html:select>
			
			</td>
        </tr>
        
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Viral Vector:</label></td>
		<td class="formField">					
			<html:text styleClass="formFieldSized" size="30" property="otherViralVector"/>			
		</td>
	</tr>        
        
	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Gene:</label>
		<camod:cshelp mapId="gene_delivery_help" key="GENE_DELIVERY.GENE_IN_VIRUS" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">		
			<html:text styleClass="formFieldSized" size="30" property="geneInVirus" />
		</td>				
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Treatment Regimen:</label>
		<camod:cshelp mapId="gene_delivery_help" key="TREATMENT.REGIMEN" image="images/iconHelp.gif" text="Tool Tip Test 1" />		
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="regimen" size="30"/>
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="0">&nbsp;</td>
		<td class="formLabel"><label for="field2">Injection Site:</label>
		  	    <a href="javascript:showMouseTissueTree('geneDeliveryForm', 'organTissueCode', 'organTissueName', 'organ', true)">
				<IMG src="images\selectUP.gif" align=middle border=0>
				</a>
				<html:hidden property="organTissueCode" />
				<INPUT name="organTissueName" type="hidden"/>
			</td>
			<td class="formField">
				<html:text styleClass="formFieldSized"  property="organ" size="30"/>
				<a href="javascript: clearOrgan(document.forms[0].organ, document.forms[0].organTissueCode);"><img border="0" src="/camod/images/clear.gif"></a>
			</td>
	</tr>
		
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Age at Treatment:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="ageAtTreatment"  size="15"/>
			<label for="field1">&nbsp;Units&nbsp;</label>
			<html:select styleClass="formFieldUnSized" size="1" property="ageAtTreatmentUnit">												
				<html:options name="<%= Dropdowns.AGEUNITSDROP %>"/>					
			</html:select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Gender:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldUnSized" size="1" property="type">												
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
	  				  
	  				  <c:if test="${not empty aCarcinogenExposureID}">
		  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
							  <bean:message key="button.delete"/>
						  </html:submit>
				      </c:if>
			        				  
					  <!--  Done this way since html:hidden doesn't seem to work correctly -->
  				  	  <input type="hidden" name="aCarcinogenExposureID" value="<%= aCarcinogenExposureID %>">
				  
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
    chkOtherViralVector();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>