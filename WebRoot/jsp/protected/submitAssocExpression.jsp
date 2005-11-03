<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.Constants.*" %>
<%@ page import=" gov.nih.nci.camod.webapp.form.AssociatedExpressionForm" %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>

<%
	String aEngineeredTransgeneID = request.getParameter( "aEngineeredTransgeneID" );
	String aGenomicSegmentID = request.getParameter( "aGenomicSegmentID" );
	String aTargetedModificationID = request.getParameter( "aTargetedModificationID" );
	String aAssociatedExpressionID = request.getParameter( "aAssociatedExpressionID" );
	
	String actionName = "AssociatedExpressionAction.do?method=save";
	
	if ( aEngineeredTransgeneID != null )
		actionName = "AssociatedExpressionAction.do?method=save&aEngineeredTransgeneID=" + aEngineeredTransgeneID;
	if ( aGenomicSegmentID != null )
		actionName = "AssociatedExpressionAction.do?method=save&aGenomicSegmentID=" + aGenomicSegmentID;
	if ( aTargetedModificationID != null )
		actionName = "AssociatedExpressionAction.do?method=save&aTargetedModificationID=" + aTargetedModificationID;
	if ( aAssociatedExpressionID != null )
		actionName = "AssociatedExpressionAction.do?method=edit";
%>

<script language="JavaScript" src="scripts/EVSTreeScript.js"></script>

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
		<td class="formTitle" height="20" colspan="3">Expression</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="0">*</td>
		<td class="formRequiredLabel"><label for="field2">Organ / Tissue:</label>&nbsp;
		<camod:cshelp mapId="associated_expression_help" key="ORGAN.CONCEPT_CODE" image="images/iconHelp.gif" text="Tool Tip Test 1" />

		<a href="javascript:showTissueTree('associatedExpressionForm', 'descendants=true;isaFlag=false;preferredName=true;depthLevel=6;roleType=Anatomic_Structure_is_Physical_Part_of')">
				<IMG src="images\selectUP.gif" align=middle border=0>
			</a>

		</td>
		<td class="formField">
			
			<html:form action="<%= actionName %>" focus="name">	

			<html:hidden property="organTissueCode" name="formdata" />
			<html:hidden property="organTissueName" name="formdata" />
			
			<html:text styleClass="formFieldSized" disabled="true" property="organ" size="30" name="formdata"/>
			
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Expression Level:</label></td>
		<td class="formField">				
			<html:select styleClass="formFieldSized" size="1" property="expressionLevel" name="formdata" >
				<html:options name="<%= Dropdowns.EXPRESSIONLEVEL %>" />										
			</html:select>
		</td>
	</tr>

	<tr>
		<td align="right" colspan="3">
			<TABLE cellpadding="4" cellspacing="0" border="0">
			
				  <html:submit styleClass="actionButton">
					  <bean:message key="button.submit"/>
				  </html:submit>
				  
				  <html:reset styleClass="actionButton">
				  	  <bean:message key="button.reset"/>
  				  </html:reset>
				
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->				 
				  <input type="hidden" name="aAssociatedExpressionID" value="<%= aAssociatedExpressionID %>">
				  	
				</html:form>			
			</TABLE>
		</td>
	</tr>
</TABLE>

	<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>