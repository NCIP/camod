<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.Constants.*" %>
<%@ page import=" gov.nih.nci.camod.webapp.form.AssociatedExpressionForm" %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>

<script language="JavaScript" src="scripts/EVSTreeScript.js"></script>

<%
	String aEngineeredTransgeneID = request.getParameter( "aEngineeredTransgeneID" );
	String aGenomicSegmentID = request.getParameter( "aGenomicSegmentID" );
	String aTargetedModificationID = request.getParameter( "aTargetedModificationID" );
	String aAssociatedExpressionID = request.getParameter( "aAssociatedExpressionID" );
    String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
	
	String actionName = "AssociatedExpressionAction.do?method=save";
	
	// Handle adding to eng. trans
	if ( aEngineeredTransgeneID != null && aEngineeredTransgeneID.length() > 0 && isDeleted == null) {
		actionName = "AssociatedExpressionAction.do?method=save&aEngineeredTransgeneID=" + aEngineeredTransgeneID;
	} else {
	    aEngineeredTransgeneID = "";
	}
	
	// Handle adding to genomic seg
	if ( aGenomicSegmentID != null && aGenomicSegmentID.length() > 0 && isDeleted == null) {
		actionName = "AssociatedExpressionAction.do?method=save&aGenomicSegmentID=" + aGenomicSegmentID;
	} else {
	    aGenomicSegmentID = "";
	}
	
	// Handle the add to targ mod.
	if ( aTargetedModificationID != null && aTargetedModificationID.length() > 0 && isDeleted == null) {
		actionName = "AssociatedExpressionAction.do?method=save&aTargetedModificationID=" + aTargetedModificationID;
	} else {
	    aTargetedModificationID = "";
	}
	
	// Handle editing of assoc expr.
	if ( aAssociatedExpressionID != null && aAssociatedExpressionID.length() > 0 && isDeleted == null) {
		actionName = "AssociatedExpressionAction.do?method=edit";
	} else {
	    aAssociatedExpressionID = "";
	}
%>
	
<html:form action="<%= actionName %>" focus="name">	


<!-- submitAssocExpression.jsp -->
<!-- Main Content Begins -->
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
		<td class="formTitle" height="20" colspan="3">Expression&nbsp;
			<camod:cshelp topic="associated_expression_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
	</tr>


	<tr>
		<td class="formRequiredNotice" width="5">*</td>
 			<!-- Display anatomy tree based on animal model species or allow for text entry if no specific tree exists -->
 			<c:choose>
				<c:when test="${modelspeciescommonname == 'Mouse'}">
				<td class="formRequiredLabel"><label for="field1">Organ / Tissue:</label>&nbsp;
				<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />							
					<a href="javascript:showMouseTissueTree('associatedExpressionForm', 'organTissueCode', 'organTissueName', 'organ', true)">
						<IMG src="images\selectUP.gif" align=middle border=0></a>
				</td>				
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">					
						<html:text styleClass="formFieldSized" disabled="true" property="organ" size="30"  />
					</td>				
				</c:when>
				
				<c:when test="${modelspeciescommonname == 'Rat'}">
				<td class="formRequiredLabel"><label for="field1">Organ / Tissue:</label>&nbsp;
				<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />					
					<a href="javascript:showRatTissueTree('associatedExpressionForm', 'organTissueCode', 'organTissueName', 'organ', true)">
						<IMG src="images\selectUP.gif" align=middle border=0></a>
				</td>
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">										
						<html:text styleClass="formFieldSized" disabled="true" property="organ" size="30"  />
					</td>
				</c:when>
					
				<c:when test="${modelspeciescommonname == 'Zebrafish'}">
				<td class="formRequiredLabel"><label for="field1">Organ / Tissue:</label>&nbsp;
				<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />					
					<a href="javascript:showZebrafishTissueTree('associatedExpressionForm', 'organTissueCode', 'organTissueName', 'organ', true)">
						<IMG src="images\selectUP.gif" align=middle border=0></a>
				</td>
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">										
						<html:text styleClass="formFieldSized" disabled="true" property="organ" size="30"  />
					</td>
				</c:when>
				<c:otherwise>
				<td class="formRequiredLabel"><label for="field1">Organ / Tissue:</label>&nbsp;				
				</td>
					<html:hidden property="organTissueCode"/>		
					<html:hidden property="organTissueName"/>
					<td class="formField">
						<html:text styleClass="formFieldSized" disabled="false" property="organ"   size="25" />
					</td>				
				</c:otherwise>				
    		</c:choose>
	</tr>
	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Expression Level:</label></td>
		<td class="formField">				
			<html:select styleClass="formFieldSized" size="1" property="expressionLevel" name="formdata" >
				<html:options name="<%= Dropdowns.EXPRESSIONLEVELDROP %>" />										
			</html:select>
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Comment:</label>
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized" property="comments" cols="32" rows="4"/>			
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
				
				  <c:if test="${not empty aAssociatedExpressionID}">
					  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
					      <bean:message key="button.delete" />
					  </html:submit>
				  </c:if>
				  
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->				 
				  <input type="hidden" name="aAssociatedExpressionID" value="<%= aAssociatedExpressionID %>">
				  <c:if test="${not empty aGenomicSegmentID}">
				      <input type="hidden" name="aGenomicSegmentID" value="<%= aGenomicSegmentID %>">
				  </c:if>
				  <c:if test="${not empty aTargetedModificationID}">
				      <input type="hidden" name="aTargetedModificationID" value="<%= aTargetedModificationID %>">
				  </c:if>
				  <c:if test="${not empty aEngineeredTransgeneID}">
				      <input type="hidden" name="aEngineeredTransgeneID" value="<%= aEngineeredTransgeneID %>">
				  </c:if>
				</html:form>			
			</TABLE>
		</td>
	</tr>
</TABLE>

	<!-- -->
</TABLE>
</tr></TABLE>

<%@ include file="/jsp/footer.jsp" %>