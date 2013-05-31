<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%

/**
 * $Id: submitTherapy.jsp,v 1.41 2009-05-19 17:28:54 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.40  2009/05/19 17:10:04  pandyas
 * modified for gforge #19723  	Increase width of paragraph text boxes on various submission screens
 *
 * Revision 1.39  2008/11/10 18:48:04  pandyas
 * modified dose box for gforge #17673 Add values to Unit, Administrative Route drop-down menus
 *
 * Revision 1.38  2008/11/10 18:33:48  pandyas
 * modified dose box for gforge #17673 Add values to Unit, Administrative Route drop-down menus
 *
 * Revision 1.37  2008/01/23 22:26:25  pandyas
 * Fixed #11831  	maximize buttion in pop-up windows disabled which prevents user to see full page
 *
 * Revision 1.36  2007/10/23 17:29:51  pandyas
 * Fixed bug #9611:  	"Submit" "Cancel" buttons become invisible after submission error in "Enter Terapy" page
 * Moved </form> below last table so form displays in full
 *
 */

%> 
 
<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.webapp.form.TherapyForm" %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<script language="JavaScript" src="scripts/initIt.js"></script>
<script language="JavaScript" src="scripts/EVSTreeScript.js"></script>

<%@ page buffer="100kb"%>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<script language="JavaScript" src="scripts/global.js"></script>

<%
	String aTherapyID = request.getParameter( "aTherapyID" );
	String isDeleted = (String) request.getAttribute(Constants.Parameters.DELETED);
	
	//if aTherapyID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "TherapyAction.do?method=save";
	
    if ( aTherapyID != null && aTherapyID.length() > 0 && isDeleted == null) {
		actionName = "TherapyAction.do?method=edit";
	}
	else {
	    aTherapyID = "";
	}
%>

<SCRIPT LANGUAGE="JavaScript">
	function removeAllSelected()
	{
	    removeSelected(document.forms[0].selectedChemicalClasses, document.forms[0].chemicalClasses);
	    removeSelected(document.forms[0].selectedProcesses, document.forms[0].processes);
	    removeSelected(document.forms[0].selectedTargets, document.forms[0].targets);
	    unselectAll();
	}

	function selectAll()
	{
	    select(document.forms[0].selectedChemicalClasses);
	    select(document.forms[0].selectedProcesses);
	    select(document.forms[0].selectedTargets);
	}

	function unselectAll()
	{
	    unselect(document.forms[0].selectedChemicalClasses);
	    unselect(document.forms[0].selectedProcesses);
	    unselect(document.forms[0].selectedTargets);
	}
</SCRIPT>

<!-- submitTherapy.jsp -->
<!-- Main Content Begins -->
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly  -->
<%@ include file="/jsp/submitMenu.jsp" %>
<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%">
	<tr><td valign="top">

	<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" align="left">
	<html:form action="<%= actionName %>" focus="name" onsubmit="selectAll()">
	<tr>
		<html:errors/>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>

	<tr>
		<td class="formTitle" height="20" colspan="3">Therapy
			<camod:cshelp topic="therapy_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel" ><label for="drugName">Drug / Compound Name:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldSized" size="30" styleId="drugName" property="name" />	
		</td>
	</tr>
	
	<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="nscNumber">NSC number:</label>
			</td>
			<td class="formField">		
				<input type=button value="Find NSC #" onClick="myRef = window.open('http://dtp.nci.nih.gov/dtpstandard/chemname/index.jsp?field1=','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
				&nbsp;&nbsp;
				<html:text styleClass="formFieldUnSized" size="10" styleId="nscNumber" property="nscNumber" />
			</td>
	</tr>

	<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="casNumber">CAS number:</label>
			</td>
			<td class="formField">		
				<input type=button value="Find CAS #" onClick="myRef = window.open('http://chemfinder.cambridgesoft.com/','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"></input>
				&nbsp;&nbsp;
				<html:text styleClass="formFieldUnSized" size="10" styleId="casNumber" property="casNumber" />&nbsp;&nbsp;<a class="sideMenuLink" href="#" onClick="myRef = window.open('html/disclaimer.html#external','mywin',
										'left=20,top=20,width=800,height=200,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()">Disclaimer</a>
			</td>
	</tr>	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>        
		<td class="formLabel"><label for="chemicalClasses">Chemical Class:</label><label for="selectedChemicalClasses">&#160;</label></td>
		<td class="formField">
		    <center>
		    <table summary="This table is used to format page content">
		    <tr>
			    <html:select styleClass="formFieldUnSized" size="5"  multiple="true" style="width: 500px" styleId="chemicalClasses" property="chemicalClasses">												
			    <html:options name="<%= Dropdowns.CHEMICALCLASSESDROP %>"/>					
			    </html:select>
			</tr>
			<br>
			<tr>
			    <a href="javascript: transferItem(document.forms[0].chemicalClasses, document.forms[0].selectedChemicalClasses, 'YES');"><img alt="Move down" border="0" src="/camod/images/downarrow.gif"></a>
			    <a href="javascript: transferItem(document.forms[0].selectedChemicalClasses, document.forms[0].chemicalClasses, 'YES');"><img alt="Move up" border="0" src="/camod/images/uparrow.gif"></a>
            </tr>
            <br>
            <tr>
			    <html:select styleClass="formFieldUnSized" size="5" multiple="true" style="width: 500px" styleId="selectedChemicalClasses" property="selectedChemicalClasses" >												
			        <html:options property="selectedChemicalClasses" />
			    </html:select>
			</tr>
			</table>
			</center>
		</td>
 	</tr>   
    
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>        
		<td class="formLabel"><label for="processes">Biological Process:</label><label for="selectedProcesses">&#160;</label></td>
		<td class="formField">
		    <center>
		    <table summary="This table is used to format page content">
		    <tr>
			    <html:select styleClass="formFieldUnSized" size="5"  multiple="true" style="width: 500px" styleId="processes" property="processes">												
			    <html:options name="<%= Dropdowns.BIOLOGICALPROCESSDROP %>"/>					
			    </html:select>
			</tr>
			<br>
			<tr>
			    <a href="javascript: transferItem(document.forms[0].processes, document.forms[0].selectedProcesses, 'YES');"><img alt="Move down" border="0" src="/camod/images/downarrow.gif"></a>
			    <a href="javascript: transferItem(document.forms[0].selectedProcesses, document.forms[0].processes, 'YES');"><img alt="Move up" border="0" src="/camod/images/uparrow.gif"></a>
            </tr>
            <br>
            <tr>
			    <html:select styleClass="formFieldUnSized" size="5" multiple="true" style="width: 500px" styleId="selectedProcesses" property="selectedProcesses" >												
			        <html:options property="selectedProcesses" />
			    </html:select>
			</tr>
			</table>
			</center>
		</td>
 	</tr>    					
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>        
		<td class="formLabel"><label for="targets">Target:</label><label for="selectedTargets">&#160;</label></td>
		<td class="formField">
		    <center>
		    <table summary="This table is used to format page content">
		    <tr>
			    <html:select styleClass="formFieldUnSized" size="5"  multiple="true" style="width: 500px" styleId="targets" property="targets" >												
			    <html:options name="<%= Dropdowns.THERAPEUTICTARGETSDROP %>"/>					
			    </html:select>
			</tr>
			<br>
			<tr>
			    <a href="javascript: transferItem(document.forms[0].targets, document.forms[0].selectedTargets, 'YES');"><img alt="Move down" border="0" src="/camod/images/downarrow.gif"></a>
			    <a href="javascript: transferItem(document.forms[0].selectedTargets, document.forms[0].targets, 'YES');"><img alt="Move up" border="0" src="/camod/images/uparrow.gif"></a>
            </tr>
            <br>
            <tr>
			    <html:select styleClass="formFieldUnSized" size="5" multiple="true" style="width: 500px" styleId="selectedTargets" property="selectedTargets" >
			    <html:options property="selectedTargets" />
			    </html:select>
			</tr>
			</table>
			</center>
		</td>
 	</tr>				
				 	
 	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="experiment">Experiment:</label>
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldUnSized" styleId="experiment" property="experiment" cols="60" rows="3"/>			
			</td>
	</tr>
	<tr>
		<td class="formRequiredNotice" width="5"><label for="dosageUnit">&nbsp;</label></td>
		<td class="formLabel"><label for="dosage">Dose:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized"  styleId="dosage" property="dosage" size="15"  />
			&nbsp;Units&nbsp;			
			<html:select styleClass="formFieldUnSized" size="1" styleId="dosageUnit" property="dosageUnit" >												
				<html:options name="<%= Dropdowns.CHEMTHERAPYDOSEUNITSDROP %>"/>				
			</html:select>
		</td>
	</tr> 
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="administrativeRoute">Administrative Route:</label>
		</td>
		<td class="formField">
		<br>
		- if Administration Route is not listed, <br>then please select "Other" and then specify it below:
		<br>
		<br>
			<html:select styleClass="formFieldSized" size="1" styleId="administrativeRoute" property="administrativeRoute"  onclick="chkOtherAdminRoute();">												
				<html:options name="<%= Dropdowns.ADMINISTRATIVEROUTEDROP %>"/>					
			</html:select>			
		</td>
	</tr>	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="otherAdministrativeRoute">Other Administrative Route:</label></td>
		<td class="formField">					
			<html:text styleClass="formFieldSized" size="30" styleId="otherAdministrativeRoute" property="otherAdministrativeRoute" disabled="true"/>			
		</td>
	</tr>
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="gender">Gender:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldUnSized" size="1" styleId="gender" property="type" >												
				<html:options name="<%= Dropdowns.SEXDISTRIBUTIONDROP %>"/>					
			</html:select>
		</td>
	</tr>
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="ageAtTreatment">Age at Treatment:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" styleId="ageAtTreatment" property="ageAtTreatment"  size="15"/>
			<label for="ageAtTreatmentUnit">&nbsp;Units&nbsp;</label>
			<html:select styleClass="formFieldUnSized" size="1" styleId="ageAtTreatmentUnit" property="ageAtTreatmentUnit" >												
				<html:options name="<%= Dropdowns.AGEUNITSDROP %>"/>					
			</html:select>
		</td>
	</tr>

 	<!-- Display anatomy tree if animal model species is Zebrafish or hide if not Zebrafish -->
	<c:if test="${modelspeciescommonname == 'Zebrafish'}">	
		<tr>
			<td class="formRequiredNotice" width="10">&nbsp;</td>
	
					<td class="formLabel"><label for="developmentalStage">Developmental Stage:</label>&nbsp;
						<camod:cshelp topic="data_tree_help" key="DEVELOPMENTALSTAGE.CONCEPT_CODE" image="images/helpTooltip.gif" text="Tool Tip Test 1" />
					<br>							
					<a href="javascript:showZebrafishStageTree('therapyForm', 'developmentalStageCode', 'developmentalStageName', 'developmentalStage', true)">
						<IMG alt="Select from EVSTree" src="images\selectUP.gif" align=middle border=0></a>
					</td>				
					<html:hidden property="developmentalStageCode"/>
					<input type="hidden" name="developmentalStageName" />
					<td class="formField">					
						<html:text styleClass="formFieldSized" disabled="true" styleId="developmentalStage" property="developmentalStage" size="30"  />
						<a href="javascript: clearField(document.forms[0].developmentalStage, document.forms[0].developmentalStageCode);">
						<img alt="Clear the Selection" border="0" align=middle src="/camod/images/clear.gif"></a>						
						
					</td>
		</tr>
	</c:if>	
		
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="results">Results:</label>
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldUnSized" styleId="results" property="results" cols="60" rows="3"/>			
			</td>
	</tr>
    <tr align="LEFT" valign="TOP">
        <td class="formRequiredNotice" width="5">&nbsp;</td>        
        <TD class="formLabel"><label for="toxicityGrade">Toxicity Grade:</label>
        </TD>
		<td class="formField" >
			<html:select styleClass="formFieldUnSized" size="1" styleId="toxicityGrade" property="toxicityGrade" >												
				<html:options name="<%= Dropdowns.TOXICITYGRADESDROP %>" />					
			</html:select>
		</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="biomarker">Biomarker:</label>
		</td>
			<td class="formField">
				<html:text styleClass="formFieldSized" size="30" styleId="biomarker" property="biomarker" />			
			</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="tumorResponse">Tumor Response:</label>
		</td>
			<td class="formField">
				<html:text styleClass="formFieldSized" size="30" styleId="tumorResponse" property="tumorResponse" />			
			</td>
	</tr>		
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="comments">Comment:</label>
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldUnSized" styleId="comments" property="comments" cols="60" rows="3"/>			
			</td>
	</tr>		
	

	<tr>
		<td align="right" colspan="3">
			<TABLE summary="This table is used to format page content" cellpadding="4" cellspacing="0" border="0">
				
					  <html:submit styleClass="actionButton">
						  <bean:message key="button.submit"/>
					  </html:submit>
					  
					  <html:reset styleClass="actionButton">
					  	  <bean:message key="button.reset"/>
	  				  </html:reset>
	  				  
	  				  <c:if test="${not empty aTherapyID}">
		  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton" onclick="return confirm('Are you sure you want to delete?');">
							  <bean:message key="button.delete"/>
						  </html:submit>
				      </c:if>
				      
					  <!--  Done this way since html:hidden doesn't seem to work correctly -->
  				  	  <input type="hidden" name="aTherapyID" value="<%= aTherapyID %>">	 			
			</TABLE>
		</td>
	</tr>
	</html:form>
</TABLE>

<!-- -->
	</td></tr></TABLE>
</td></tr></TABLE>


<SCRIPT LANGUAGE="JavaScript">
    removeAllSelected();
    chkOtherAdminRoute();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>