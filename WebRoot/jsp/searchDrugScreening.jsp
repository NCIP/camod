<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<script language="JavaScript" src="scripts/EvsTree.js"></script>

<html:form action="DrugScreenSearchAction.do" focus="NSCNumber">
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0">
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
			<td class="formTitleBlue" height="20" colspan="3">Drug Screening Data <camod:cshelp mapId="drug_screening_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>		
		</tr>
        <TR>
	        <TD class=formRequiredNotice width=5>*</TD>
	        <TD class=formRequiredLabel>
	        	<LABEL for=field1>NSC Number:</LABEL>
	        </TD>
	        <TD class=formField>
		        <html:text styleClass="formFieldSized" property="NSCNumber" size="30"/>
	        </TD>
        </TR>
        <TR>
	        <TD class=formRequiredNotice width=5>*</TD>
	        <TD colspan="2" class=formRequiredLabel>
	        	<LABEL for=field1>One or more of the following selections must be checked</LABEL>
	        </TD>
        </TR>
		<TR>
	        <TD class=formRequiredNotice width=5>&nbsp;</TD>
	        <TD class=formLabel>DTP yeast Screening Data</TD>
	        <TD class=formField>
		        <html:checkbox property="doYeast"/>
	        	<LABEL for=box1>Find Yeast screening data for this compound</LABEL> 
	        </TD>
	    </TR>
		<TR>
	        <TD class=formRequiredNotice width=5>&nbsp;</TD>
	        <TD class=formLabel>DTP in-vivo Data</TD>
	        <TD class=formField>
		        <html:checkbox property="doInvivo"/>
	        	<LABEL for=box1>Find in-vivo screening data for this compound</LABEL> 
	        </TD>
	    </TR>

        <TR>
        <TD class=formRequiredNotice width=5>&nbsp;</TD>
        <TD class=formLabel>Pre-Clinical Trials</TD>
        <TD class=formField>
	        <html:checkbox property="doPreClinical"/>
        	<LABEL for=box1>Find pre-clinical trials with this compound</LABEL> 
        </TD>
        </TR>

        <TR>
        <TD class=formRequiredNotice width=5>&nbsp;</TD>
        <TD class=formLabel>Clinical Trials</TD>
        <TD class=formField>
	        <html:checkbox property="doClinical"/>
        	<LABEL for=box1>Find clinical trials with this compound</LABEL> 
        </TD>
        </TR>

		<tr>
		<td align="right" colspan="3">

			<!-- action buttons begins -->
			<TABLE cellpadding="4" cellspacing="0" border="0">
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

</html:form>
<%@ include file="/jsp/footer.jsp" %>