<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<script language="JavaScript" src="scripts/EvsTree.js"></script>

<FORM name="input" action="searchResults.do" method="get">

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0">
                <tr><td>&nbsp;</td></tr>
                
                <tr>
			<td class="formTitleBlue" height="20" colspan="3">Drug Screening Data</td>		
		</tr>
		
        <TR>
	        <TD class=formRequiredNotice width=5>&nbsp;</TD>
	        <TD class=formLabel>
	        	<LABEL for=field1>NSC Number:</LABEL>
	        </TD>
	        <TD class=formField>
	        	<INPUT class=formFieldSized id=nscNumber size=30 name=nscNumber>
	        </TD>
        </TR>
		<TR>
	        <TD class=formRequiredNotice width=5>&nbsp;</TD>
	        <TD class=formLabel>DTP yeast Screening Data</TD>
	        <TD class=formField>
	        	<INPUT id=yeastId type=checkbox name=yeastData>
	        	<LABEL for=box1>Find Yeast screening data for this compound</LABEL> 
	        </TD>
	    </TR>
		<TR>
	        <TD class=formRequiredNotice width=5>&nbsp;</TD>
	        <TD class=formLabel>DTP in-vivo Data</TD>
	        <TD class=formField>
	        	<INPUT id=invivoId type=checkbox name=invivoData>
	        	<LABEL for=box1>Find in-vivo screening data for this compound</LABEL> 
	        </TD>
	    </TR>
		<TR>
        <TD class=formRequiredNotice width=5>&nbsp;</TD>
        <TD class=formLabel>Clincal Trials</TD>
        <TD class=formField>
        	<INPUT id=clinTrialsId type=checkbox name=clinTrialsData>
        	<LABEL for=box1>Find clinical trials with this compound</LABEL> 
        </TD>
        </TR>
        <TR>
        <TD class=formRequiredNotice width=5>&nbsp;</TD>
        <TD class=formLabel>Pre-Clincial Trials</TD>
        <TD class=formField>
        	<INPUT id=preClinTrialsId type=checkbox name=preClinTrialsData>
        	<LABEL for=box1>Find pre-clincial trials with this compound</LABEL> 
        </TD>
        </TR>

		<tr>

		<td align="right" colspan="3">

			<!-- action buttons begins -->
			<TABLE cellpadding="4" cellspacing="0" border="0">
			<tr>
				<td>
					<input class="actionButton" type="submit" value="Search" />
				</td>
				<td>
					<input class="actionButton" type="reset" value="Reset" />
				</td>
			</tr>
			</TABLE>

		<!-- action buttons end -->
	</td></tr></TABLE>
</td></tr></TABLE>	

<%@ include file="/jsp/footer.jsp" %>