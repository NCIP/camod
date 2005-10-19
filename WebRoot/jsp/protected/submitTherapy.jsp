<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.webapp.form.TherapyForm" %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>

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
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
<!-- -->

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" align="left" width="100%" height="100%">
	
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
			<html:form action="<%= actionName %>" focus="name">			 
			
			<html:text styleClass="formFieldSized" size="30" property="name" name="formdata" />	
		</td>
	</tr>
	
	<tr>
			<td class="formRequiredNotice" width="3">&nbsp;</td>
			<td class="formLabel"><label for="field1">NSC number:</label></td>
			<td class="formField">		
				<input type=button value="Find NSC #" onClick="myRef = window.open('http://dtp.nci.nih.gov/dtpstandard/chemname/index.jsp?field1=','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"></input>
				<html:text styleClass="formFieldUnSized" size="10" property="NSCNumber" name="formdata" />
			</td>
	</tr>
	<!-- changed linkd to CAS# but NSC link can get both CAS and NSC - ask Ulli?? http://dtp.nci.nih.gov/dtpstandard/chemname/index.jsp?field1=   -->
	<tr>
			<td class="formRequiredNotice" width="3">&nbsp;</td>
			<td class="formLabel"><label for="field1">CAS number:</label></td>
			<td class="formField">		
				<input type=button value="Find CAS #" onClick="myRef = window.open('http://chemfinder.cambridgesoft.com/','mywin',
				'left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"></input>
				<html:text styleClass="formFieldUnSized" size="10" property="CASNumber" name="formdata" />
			</td>
	</tr>	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>        
		<td class="formRequiredLabel"><label for="field3">Chemical Class:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldUnSized" size="5"  property="chemicalClassName" name="formdata">												
			<html:options name="<%= Dropdowns.CHEMICALCLASSESDROP %>"/>					
			</html:select>
			
			<input TYPE="BUTTON" VALUE="->"><a href='javascript: addIt();'></a>
			<input TYPE="BUTTON" VALUE="<-" ONCLICK="delIt();"></input>

			<html:select styleClass="formFieldSized" size="5" style="width: 150px" property="selectedChemicalClassName" name="formdata">												
			</html:select>
		</td>
 	</tr>   
    
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>        
		<td class="formRequiredLabel"><label for="field3">Biological Process:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldUnSized" size="5"  property="processName" name="formdata">												
			<html:options name="<%= Dropdowns.BIOLOGICALPROCESSDROP %>"/>					
			</html:select>
			<input TYPE="BUTTON" VALUE="->" ONCLICK="addIt();"></input>
			<input TYPE="BUTTON" VALUE="<-" ONCLICK="delIt();"></input>

			<html:select styleClass="formFieldSized" size="5" style="width: 150px" property="selectedProcessName" name="formdata">												
			</html:select>
		</td>
 	</tr>    					
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>        
		<td class="formRequiredLabel"><label for="field3">Target:</label></td>
		<td class="formField">
			<html:select styleClass="formFieldUnSized" size="5"  property="targetName" name="formdata">												
			<html:options name="<%= Dropdowns.THERAPEUTICTARGETSDROP %>"/>					
			</html:select>
			<input TYPE="BUTTON" VALUE="->" ONCLICK="addIt();"></input>
			<input TYPE="BUTTON" VALUE="<-" ONCLICK="delIt();"></input>

			<html:select styleClass="formFieldSized" size="5" style="width: 150px" property="selectedTargetName" name="formdata">												
			</html:select>
		</td>
 	</tr>			
    <TR align="LEFT" valign="TOP">
        <td class="formRequiredNotice" width="5">&nbsp;</td>        
        <TD class="formLabel"><label for="field1">Toxicity Grade:</label>
        <camod:cshelp key="THERAPY.TOXICITY_GRADE" image="images/iconHelp.gif" text="Tool Tip Test 1" />
        </TD>
		<td class="formField">
			<html:select styleClass="formFieldUnSized" size="1" property="toxicityGrade" name="formdata">												
				<html:options name="<%= Dropdowns.TOXICITYGRADESDROP %>"/>					
			</html:select>
		</td>
	</tr>	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Dose:</label></td>
		<td class="formField">
			<html:text styleClass="formFieldUnSized"  property="dosage" size="10" name="formdata" />
			<label for="field1">&nbsp;Units&nbsp;</label>			
			<html:select styleClass="formFieldUnSized" size="1" property="doseUnit" name="formdata">												
				<html:options name="<%= Dropdowns.CHEMTHERAPYDOSEUNITSDROP %>"/>					
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
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Mouse Age:</label></td>
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
		<td class="formLabel"><label for="field1">Adminstration Route:</label></td>
			<td class="formField">
				<html:text styleClass="formFieldSized" size="30" property="administrativeRoute" name="formdata" />			
			</td>
	</tr>
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Biomarker:</label>
		<camod:cshelp key="THERAPY.BIOMARKER" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
			<td class="formField">
				<html:text styleClass="formFieldSized" size="30" property="biomarker" name="formdata" />			
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
			<html:text styleClass="formFieldUnSized" property="tumorResponse"  size="10" name="formdata"/>
			<label for="field1">&nbsp;Units&nbsp;</label>
			<html:select styleClass="formFieldUnSized" size="1" property="tumorAgeUnit" name="formdata">												
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
					<html:textarea styleClass="formFieldSized" property="experiment" cols="32" rows="4"/>			
			</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Results:</label>
		<camod:cshelp key="THERAPY.RESULTS" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		</td>
			<td class="formField">
					<html:textarea styleClass="formFieldSized" property="results" cols="32" rows="4"/>			
			</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Comments:</label>
		<camod:cshelp key="THERAPY.COMMENTS" image="images/iconHelp.gif" text="Tool Tip Test 1" />
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

<%@ include file="/jsp/footer.jsp" %>