<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page import="gov.nih.nci.camod.webapp.form.CellLineForm" %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<script language="JavaScript" src="scripts/EVSTreeScript.js"></script>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>

<%
	String aCellID = request.getParameter( "aCellID" );
	
	//if aCellID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "CellLineAction.do?method=save";
	
	if ( aCellID != null )
		actionName = "CellLineAction.do?method=edit";			
%>


<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">	
	<tr>
		<html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>
	
	<tr>
		<td class="formTitle" height="20" colspan="3">Enter Cell Line Generated from the Model </td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Name of Cell Line:</label>
		<camod:cshelp key="CELL_LINE.NAME" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:form action="<%= actionName %>" focus="cellLineName">
								
			<html:text styleClass="formFieldSized" size="30" name="formdata" property="cellLineName" />			
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="0">*</td>
		<td class="formRequiredLabel"><label for="field2">Organ / Tissue:</label>&nbsp;
		<camod:cshelp key="ORGAN.CONCEPT_CODE" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		  	    <a href="javascript:showTissueTree('cellLineForm', 'descendants=true;isaFlag=false;depthLevel=6;roleType=Anatomic_Structure_is_Physical_Part_of', 3)">
				<IMG src="images\selectUP.gif" align=middle border=0>
				</a>
				<INPUT name="organTissueName" type="hidden"/>
		 		<INPUT name="organTissueCode" type="hidden"/>
			</td>
			<td class="formField"><input class="formFieldSized" type="text"  name="organ" id="organFieldId" size="25" disabled="true" /></td>
		</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Experiment:</label>
		<camod:cshelp key="CELL_LINE.EXPERIMENT" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized" name="formdata" property="experiment" cols="32" rows="4"/>			
			</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Results:</label>
		<camod:cshelp key="CELL_LINE.RESULTS" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized" name="formdata" property="results" cols="32" rows="4"/>			
			</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Comments:</label>
		<camod:cshelp key="CELL_LINE.COMMENTS" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized" name="formdata" property="comments" cols="32" rows="4"/>			
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
				  <input type="hidden" name="aCellID" value="<%= aCellID %>">
				  
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