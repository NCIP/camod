<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page import="gov.nih.nci.camod.webapp.form.CellLineForm" %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<script language="JavaScript" src="scripts/EVSTreeScript.js"></script>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>

<%
	String aCellID = request.getParameter( "aCellID" );
	String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
	
	//if aCellID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one	
	String actionName = "CellLineAction.do?method=save";
	
    if ( aCellID != null && aCellID.length() > 0 && isDeleted == null) {
		actionName = "CellLineAction.do?method=edit";
	}
	else {
	    aCellID = "";
	}
%>

<!-- submitCellLines.jsp -->
<!-- Main Content Begins -->
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/submitMenu.jsp" %>
<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%">
	<tr><td valign="top">

	<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" align="left" width="60%">
	<html:form action="<%= actionName %>" focus="cellLineName">	
	<tr>
		<html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>
	
	<tr>
		<td class="formTitle" height="20" colspan="3">Cell Line&nbsp;
		<camod:cshelp topic="cell_lines_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="cellLineName">Name of Cell Line:</label>
		</td>
		<td class="formField">
			<html:text styleClass="formFieldSized" size="30" styleId="cellLineName" property="cellLineName" />			
		</td>
	</tr>

	
	<tr>
		<td class="formRequiredNotice" width="5">*</td>
 			<!-- Display anatomy tree based on animal model species or allow for text entry if no specific tree exists -->
 			<c:choose>
				<c:when test="${modelspeciescommonname == 'Mouse'}">
				<td class="formRequiredLabel"><label for="mouseOrgan">Organ / Tissue:</label>&nbsp;
				<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />							
					<a href="javascript:showMouseTissueTree('cellLineForm', 'organTissueCode', 'organTissueName', 'organ', true)">
						<IMG alt="Select from EVSTree" src="images/selectUP.gif" align=middle ></a>
				</td>				
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">					
						<html:text styleClass="formFieldSized" disabled="true" styleId="mouseOrgan" property="organ" size="30"  />
					</td>				
				</c:when>
				<c:when test="${modelspeciescommonname == 'Rat'}">	
				<td class="formRequiredLabel"><label for="ratOrgan">Organ / Tissue:</label>&nbsp;
				<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />				
					<a href="javascript:showRatTissueTree('cellLineForm', 'organTissueCode', 'organTissueName', 'organ', true)">
						<IMG alt="Select from EVSTree" src="images/selectUP.gif" align=middle  ></a>
				</td>
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">										
						<html:text styleClass="formFieldSized" disabled="true" styleId="ratOrgan" property="organ" size="30"  />
					</td>
				</c:when>	
				<c:when test="${modelspeciescommonname == 'Zebrafish'}">
				<td class="formRequiredLabel"><label for="zebOrgan">Organ / Tissue:</label>&nbsp;
				<camod:cshelp topic="data_tree_help" key="ORGAN.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />				
					<a href="javascript:showZebrafishTissueTree('cellLineForm', 'organTissueCode', 'organTissueName', 'organ', true)">
						<IMG alt="Select from EVSTree" src="images/selectUP.gif" align=middle ></a>
				</td>
					<html:hidden property="organTissueCode"/>
					<input type="hidden" name="organTissueName" />
					<td class="formField">										
						<html:text styleClass="formFieldSized" disabled="true" styleId="zebOrgan" property="organ" size="30"  />
					</td>
				</c:when>
				<c:otherwise>
				<td class="formRequiredLabel"><label for="otherOrgan">Organ / Tissue:</label>&nbsp;				
				</td>
					<html:hidden property="organTissueCode"/>		
					<html:hidden property="organTissueName"/>
					<td class="formField">
						<html:text styleClass="formFieldSized" disabled="false" styleId="otherOrgan" property="organ"   size="25" />
					</td>				
				</c:otherwise>				
    		</c:choose>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="experiment">Experiment:</label>
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized" styleId="experiment" property="experiment" cols="32" rows="4"/>			
			</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="results">Results:</label>
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized" styleId="results" property="results" cols="32" rows="4"/>			
			</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="comments">Comment:</label>
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized" styleId="comments" property="comments" cols="32" rows="4"/>			
			</td>
	</tr>	

	<tr>
		<td align="right" colspan="3">
			<!-- action buttons begins -->
				<TABLE summary="This table is used to format page content" cellpadding="4" cellspacing="0" border="0">
			
				  <html:submit styleClass="actionButton">
					  <bean:message key="button.submit"/>
				  </html:submit>
				  
				  <html:reset styleClass="actionButton">
				  	  <bean:message key="button.reset"/>
  				  </html:reset>
	  				  
  				  <c:if test="${not empty aCellID}">
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
						  <bean:message key="button.delete"/>
					  </html:submit>
			      </c:if>
					  
				  <!--  Done this way since html:hidden doesn't seem to work correctly -->
				  <input type="hidden" name="aCellID" value="<%= aCellID %>">				  			
				</TABLE>
			<!-- action buttons end -->
		</td>
	</tr>
</html:form>	
</TABLE>
	<!-- -->
	</td></tr></TABLE>
</td></tr></TABLE>

<%@ include file="/jsp/footer.jsp" %>