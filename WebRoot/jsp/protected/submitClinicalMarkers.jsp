<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
<!-- -->

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">

	<tr>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>

	<tr>
		<td class="formTitle" height="20" colspan="3">Enter Clinical Markers</td>
	</tr>

        <TR align="LEFT" valign="TOP">
                <td class="formRequiredNotice" width="5">&nbsp;</td>        
                <TD class="formLabel">Select Clinical Marker:</TD>
                <TD class="formField">
                    <SELECT name="chemical_class" size="1">
                            <OPTION value="1"></OPTION>
                            <OPTION value="2">1</OPTION>
                            <OPTION value="3">2</OPTION>
                    </SELECT>
               </td>
	</tr>

        <TR align="LEFT" valign="TOP">
                <td class="formRequiredNotice" width="5">&nbsp;</td>        
                <TD class="formLabel">Select Value:</TD>
                <TD class="formField">
                    <SELECT name="chemical_class" size="1">
                            <OPTION value="1"></OPTION>
                            <OPTION value="2">1</OPTION>
                            <OPTION value="3">2</OPTION>
                    </SELECT>
               </td>
	</tr>

	<tr>
		<td align="right" colspan="3">
			<!-- action buttons begins -->
			<TABLE cellpadding="4" cellspacing="0" border="0">
				<tr>
					<td><input class="actionButton" type="submit" value="Submit" /></td>
					<td><input class="actionButton" type="reset" value="Reset" /></td>
				</tr>
			</TABLE>
			<!-- action buttons end -->
		</td>
	</tr>

	</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="footer.jsp" %>