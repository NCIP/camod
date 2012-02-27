<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<script language="JavaScript" src="scripts/EvsTree.js"></script>

<script type="text/javascript" src="js/prototype-1.4.0.js"></script>
<script type="text/javascript" src="js/scriptaculous.js"></script>
<script type="text/javascript" src="js/ajaxtags-1.2-beta2.js"></script>


<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>

<html:form action="DrugScreenSearchAction.do" focus="NSCNumber">

<!-- searchDrugScreening.jsp -->
<!-- Main Content Begins -->
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<!-- Took this out of sidebar.jsp and has to go here to format correctly -->
	<%@ include file="/jsp/searchMenu.jsp" %>
	<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="60%" height="100%">	
	<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0">
	    <tr>          
		    <td colspan="3">          
	            <logic:messagesPresent>
		            <b><font color=red>
		                <ul>
		                    <html:messages id="error"> 
		                        <li><bean:write name="error"/></li>
		                    </html:messages>
		                </ul> 
		            </font></b> 
	            </logic:messagesPresent>
			</td>
		</tr>
		<tr>
		    <td class="formMessage" colspan="3">* indicates a required field</td>
	    </tr>
        <tr>
			<td class="formTitleBlue" height="20" colspan="3">Drug Screening Data 
			<camod:cshelp topic="drug_screening_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>		
		</tr>
        <TR>
	        <TD class=formRequiredNotice width=5>*</TD>
	        <TD class=formRequiredLabel>
	        	<LABEL for="NSCNumber">NSC Number:</LABEL>
	        </TD>
	        <TD class=formField>
	            <html:text styleClass="formFieldSized" styleId="NSCNumber" property="NSCNumber" size="30"/>
				<ajax:autocomplete baseUrl="/camod/autocomplete.view" source="NSCNumber" target="NSCNumber"
  				parameters="NSCNumber={NSCNumber}" className="autocomplete" minimumCharacters="1" />	
		        <br>
		        <a href="http://dtp.nci.nih.gov/dtpstandard/ChemData/index.jsp" target="blank">Search for NSC numbers</a>
	        </TD>
        </TR>
        <TR>
	        <TD class=formRequiredNotice width=5>*</TD>
	        <TD colspan="2" class=formRequiredLabel>
	        	One or more of the following selections must be checked
	        </TD>
        </TR>
		<TR>
	        <TD class=formRequiredNotice width=5>&nbsp;</TD>
	        <TD class=formLabel>DTP yeast Screening Data</TD>
	        <TD class=formField>
		        <html:checkbox styleId="doYeast" property="doYeast"/>
	        	<LABEL for="doYeast">Find Yeast screening data for this compound</LABEL> 
	        </TD>
	    </TR>
		<TR>
	        <TD class=formRequiredNotice width=5>&nbsp;</TD>
	        <TD class=formLabel>DTP in-vivo Data</TD>
	        <TD class=formField>
		        <html:checkbox styleId="doInvivo" property="doInvivo"/>
	        	<LABEL for="doInvivo">Find in-vivo screening data for this compound</LABEL> 
	        </TD>
	    </TR>

        <TR>
        <TD class=formRequiredNotice width=5>&nbsp;</TD>
        <TD class=formLabel>Pre-Clinical Trials</TD>
        <TD class=formField>
	        <html:checkbox styleId="doPreClinical" property="doPreClinical"/>
        	<LABEL for="doPreClinical">Find pre-clinical trials with this compound</LABEL> 
        </TD>
        </TR>

        <TR>
        <TD class=formRequiredNotice width=5>&nbsp;</TD>
        <TD class=formLabel>Clinical Trials</TD>
        <TD class=formField>
	        <html:checkbox styleId="doClinical" property="doClinical"/>
        	<LABEL for="doClinical">Find clinical trials with this compound</LABEL> 
        </TD>
        </TR>

		<tr>
		<td align="right" colspan="3">

			<!-- action buttons begins -->
			<TABLE summary="This table is used to format page content" cellpadding="4" cellspacing="0" border="0">
			<tr>
				<td>
					  <html:submit styleClass="actionButton">
						  <bean:message key="button.submit"/>
					  </html:submit>
				</td>
				<TD>
					  <html:reset styleClass="actionButton">
					  	  <bean:message key="button.reset"/>
	  				  </html:reset>
	  		 	</td>
			</tr>
			</TABLE>

		<!-- action buttons end -->
		</td></tr></TABLE>
	</td></tr></TABLE>
</td></tr></TABLE>

</html:form>
<%@ include file="/jsp/footer.jsp" %>