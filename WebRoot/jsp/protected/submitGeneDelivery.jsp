<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page import="gov.nih.nci.camod.webapp.form.GeneDeliveryForm" %>	
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<%
	String aTherapyID = request.getParameter( "aTherapyID" );
	
	//if aTherapyID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "GeneDeliveryAction.do?method=save";
	
	if ( aTherapyID != null  ) {
		actionName = "GeneDeliveryAction.do?method=edit";
	}
%>


<script language="JavaScript" src="scripts/EvsTree.js"></script>

<SCRIPT LANGUAGE="JavaScript">
	
	function chkOther( control ) {
		ideControl = document.GeneDeliveryForm.otherName;
			
		if( control.value == 'Other' )
			ideControl.disabled = false;
		else {
			ideControl.value = null;
			ideControl.disabled = true;
		}
	}
		
</SCRIPT>

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
		<td class="formTitle" height="20" colspan="3">Enter Gene Delivery Information </td>
	</tr>

        <tr>
            <td class="formRequiredNotice" width="5">&nbsp;</td>
            <td class="formLabel"><label for="field2">Viral Vector</label></td>
            <td class="formField">
            <label for="field3">(if Viral Vector is not listed, then please <br>select "Other" from the list and specify it below)</label>
            <br>		
			<br>
			<html:form action="<%= actionName %>" focus="name">	
			
			<html:select styleClass="formFieldSized" size="1" property="viralVector" name="formdata" onclick="chkOther( this );">
				<html:options name="<%= Dropdowns.VIRALVECTORDROP %>"/>					
			</html:select>
			
			</td>
        </tr>
        
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Viral Vector</label></td>
		<td class="formField">		
			<html:text styleClass="formFieldSized" size="30" property="otherViralVector" name="formdata"  disabled="true"/>		
		</td>
	</tr>        
        
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Gene</label></td>
		<td class="formField">		
			<html:text styleClass="formFieldSized" size="30" property="geneInVirus" name="formdata"  />
		</td>				
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Treatment Regimen:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" property="regimen" size="30" name="formdata"/>
		</td>
	</tr>
		<!-- TO DO: convert this to a button to retrieve the evs# - taken from submitHistopatholoy.jsp  -->
		<!-- The evs# is inserted into Organ.conceptCode, the the OrganID is inserted   -->
		<!-- into as the GeneDelivery.geneDeliveryID value  -->
	<tr>
		<td class="formRequiredNotice" width="0">*</td>
		<td class="formRequiredLabel">Location of Delivery&nbsp;
		  	    <a href="javascript:showTissueTree('geneDeliveryForm', 'descendants=true;isaFlag=false;depthLevel=6;roleType=Anatomic_Structure_is_Physical_Part_of')">
				<IMG src="images\selectUP.gif" align=middle border=0>
				</a>
			 		<html:hidden property="organTissueName" name="formdata" />
			 		<html:hidden property="organTissueCode" name="formdata" />
				</a>
		</td>
		
		<td class="formField">		
			<html:text styleClass="formFieldSized" property="organ" disabled="true" size="25" name="formdata"/>
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

<%@ include file="/jsp/footer.jsp" %>