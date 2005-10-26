<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.webapp.form.TherapyForm" %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<script language="JavaScript" src="scripts/global.js"></script>
<SCRIPT>

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
<%
	String aTherapyID = request.getParameter( "aTherapyID" );
	
	//if aTherapyID is passed in, then we are dealing with a previously entered model and are editing it
	//otherwise, create a new one
	
	String actionName = "TherapyAction.do?method=save";
	
	if ( aTherapyID != null )
		actionName = "TherapyAction.do?method=edit";
%>

<script language="JavaScript" src="scripts/initIt.js"></script>


<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left" width="100%" height="100%">
	
    <tr>
	    <td colspan="3">
	        <html:errors/>
	    </td>
	</tr>
	<tr>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>

	<tr>
		<td class="formTitle" height="20" colspan="3">Therapy</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Drug / Compound Name:</label>
		<camod:cshelp key="ENV_FACTOR.NSC_NUMBER" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">
			<html:form action="<%= actionName %>" focus="name" onsubmit="selectAll()">			 
			
			<html:text styleClass="formFieldSized" size="30" property="name" />	
		</td>
	</tr>
	
	<tr>
			<td class="formRequiredNotice" width="3">&nbsp;</td>
			<td class="formLabel"><label for="field1">NSC number:</label>
			<camod:cshelp key="ENV_FACTOR.NSC_NUMBER" image="images/iconHelp.gif" text="Tool Tip Test 1" />
			</td>
			<td class="formField">		
				<input type=button value="Find NSC #" onClick="myRef = window.open('http://dtp.nci.nih.gov/dtpstandard/chemname/index.jsp?field1=','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"></input>
				<html:text styleClass="formFieldUnSized" size="10" property="NSCNumber" />
			</td>
	</tr>
	<!-- changed linkd to CAS# but NSC link can get both CAS and NSC - ask Ulli?? http://dtp.nci.nih.gov/dtpstandard/chemname/index.jsp?field1=   -->
	<tr>
			<td class="formRequiredNotice" width="3">&nbsp;</td>
			<td class="formLabel"><label for="field1">CAS number:</label>
			<camod:cshelp key="ENV_FACTOR.CAS_NUMBER" image="images/iconHelp.gif" text="Tool Tip Test 1" />
			</td>
			<td class="formField">		
				<input type=button value="Find CAS #" onClick="myRef = window.open('http://chemfinder.cambridgesoft.com/','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"></input>
				<html:text styleClass="formFieldUnSized" size="10" property="CASNumber" />
			</td>
	</tr>	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>        
		<td class="formLabel"><label for="field3">Chemical Class:</label></td>
		<td class="formField">
		    <center>
		    <table>
		    <tr>
			    <html:select styleClass="formFieldUnSized" size="5"  multiple="true" style="width: 500px" property="chemicalClasses">												
			    <html:options name="<%= Dropdowns.CHEMICALCLASSESDROP %>"/>					
			    </html:select>
			</tr>
			<br>
			<tr>
			    <a href="javascript: transferItem(document.forms[0].chemicalClasses, document.forms[0].selectedChemicalClasses, 'YES');"><img border="0" src="/camod/images/downarrow.gif"></a>
			    <a href="javascript: transferItem(document.forms[0].selectedChemicalClasses, document.forms[0].chemicalClasses, 'YES');"><img border="0" src="/camod/images/uparrow.gif"></a>
            </tr>
            <br>
            <tr>
			    <html:select styleClass="formFieldUnSized" size="5" multiple="true" style="width: 500px" property="selectedChemicalClasses" >												
			        <html:options property="selectedChemicalClasses" />
			    </html:select>
			</tr>
			</table>
			</center>
		</td>
 	</tr>   
    
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>        
		<td class="formLabel"><label for="field3">Biological Process:</label></td>
		<td class="formField">
		    <center>
		    <table>
		    <tr>
			    <html:select styleClass="formFieldUnSized" size="5"  multiple="true" style="width: 500px" property="processes">												
			    <html:options name="<%= Dropdowns.BIOLOGICALPROCESSDROP %>"/>					
			    </html:select>
			</tr>
			<br>
			<tr>
			    <a href="javascript: transferItem(document.forms[0].processes, document.forms[0].selectedProcesses, 'YES');"><img border="0" src="/camod/images/downarrow.gif"></a>
			    <a href="javascript: transferItem(document.forms[0].selectedProcesses, document.forms[0].processes, 'YES');"><img border="0" src="/camod/images/uparrow.gif"></a>
            </tr>
            <br>
            <tr>
			    <html:select styleClass="formFieldUnSized" size="5" multiple="true" style="width: 500px" property="selectedProcesses" >												
			        <html:options property="selectedProcesses" />
			    </html:select>
			</tr>
			</table>
			</center>
		</td>
 	</tr>    					
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>        
		<td class="formLabel"><label for="field3">Target:</label></td>
		<td class="formField">
		    <center>
		    <table>
		    <tr>
			    <html:select styleClass="formFieldUnSized" size="5"  multiple="true" style="width: 500px" property="targets" >												
			    <html:options name="<%= Dropdowns.THERAPEUTICTARGETSDROP %>"/>					
			    </html:select>
			</tr>
			<br>
			<tr>
			    <a href="javascript: transferItem(document.forms[0].targets, document.forms[0].selectedTargets, 'YES');"><img border="0" src="/camod/images/downarrow.gif"></a>
			    <a href="javascript: transferItem(document.forms[0].selectedTargets, document.forms[0].targets, 'YES');"><img border="0" src="/camod/images/uparrow.gif"></a>
            </tr>
            <br>
            <tr>
			    <html:select styleClass="formFieldUnSized" size="5" multiple="true" style="width: 500px" property="selectedTargets" >
			    <html:options property="selectedTargets" />
			    </html:select>
			</tr>
			</table>
			</center>
		</td>
 	</tr>			
    <TR align="LEFT" valign="TOP">
        <td class="formRequiredNotice" width="5">&nbsp;</td>        
        <TD class="formLabel"><label for="field1">Toxicity Grade:</label>
        <camod:cshelp key="THERAPY.TOXICITY_GRADE" image="images/iconHelp.gif" text="Tool Tip Test 1" />
        </TD>
		<td class="formField">
			<html:select styleClass="formFieldUnSized" size="1" property="toxicityGrade" >												
				<html:options name="<%= Dropdowns.TOXICITYGRADESDROP %>"/>					
			</html:select>
		</td>
	</tr>	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Dose:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized"  property="dosage" size="10"  />
			<label for="field1">&nbsp;Units&nbsp;</label>			
			<html:select styleClass="formFieldUnSized" size="1" property="doseUnit" >												
				<html:options name="<%= Dropdowns.CHEMTHERAPYDOSEUNITSDROP %>"/>				
			</html:select>
		</td>
	</tr>
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Gender:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldUnSized" size="1" property="type" >												
				<html:options name="<%= Dropdowns.SEXDISTRIBUTIONDROP %>"/>					
			</html:select>
		</td>
	</tr>
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Mouse Age:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized" property="ageAtTreatment"  size="10"/>
			<label for="field1">&nbsp;Units&nbsp;</label>
			<html:select styleClass="formFieldUnSized" size="1" property="ageUnit" >												
				<html:options name="<%= Dropdowns.AGEUNITSDROP %>"/>					
			</html:select>
		</td>
	</tr>
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Adminstration Route:</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" size="30" property="administrativeRoute" />			
			</td>
	</tr>
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Biomarker:</label>
		<camod:cshelp key="THERAPY.BIOMARKER" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
			<td class="formField">
				<html:text styleClass="formFieldSized" size="30" property="biomarker" />			
			</td>
	</tr>			
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Tumor Progression:</label>
		<camod:cshelp key="THERAPY.TUMOR_RESPONSE" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
		<td class="formField">		
			<label for="field3">Time to preneoplastic lesion malignancy metastais</label>
			<br>
			<html:text styleClass="formFieldUnSized" property="tumorResponse"  size="10" />
			<label for="field1">&nbsp;Units&nbsp;</label>
			<html:select styleClass="formFieldUnSized" size="1" property="tumorAgeUnit" >												
				<html:options name="<%= Dropdowns.AGEUNITSDROP %>"/>					
			</html:select>
		</td>			
	</tr>	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Experiment:</label>
		<camod:cshelp key="THERAPY.EXPERIMENT" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized" property="experiment" cols="60" rows="3"/>			
			</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Results:</label>
		<camod:cshelp key="THERAPY.RESULTS" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized" property="results" cols="60" rows="3"/>			
			</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Comments:</label>
		<camod:cshelp key="THERAPY.COMMENTS" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized" property="comments" cols="60" rows="3"/>			
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
  				  	  <input type="hidden" name="aTherapyID" value="<%= aTherapyID %>">	  				  
				  
				  </html:form>			
			</TABLE>
		</td>
	</tr>
	
	
</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<SCRIPT>
    removeAllSelected();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>