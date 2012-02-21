<%@ page import='java.util.List' %>
<%@ page import='gov.nih.nci.camod.Constants' %>

<TR><TD class=mainMenu width="100%" height=20>

<!-- Main menu begins -->
	<TABLE height=20 cellSpacing=0 cellPadding=0 summary="This table is used to format page content"	border=0>
	<TBODY>
		<TR>
			<!-- link 1 begins -->
			<TD height="20" class="footerMenuItem" onmouseover="changeMenuStyle(this,'footerMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'footerMenuItem'),hideCursor()" onclick="document.location.href='#'">
			  &nbsp;&nbsp;<a class="footerMenuLink" href="jsp/viewLicense.jsp">HOME</a>&nbsp;&nbsp;
			</TD>
			<!-- link 1 ends -->		
					
			<TD>
				<IMG height=16 alt="" src="/camod/images/mainMenuSeparator.gif" width=1>
			</TD>			
					
		
			<TD height="20" class="footerMenuItem" onmouseover="changeMenuStyle(this,'footerMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'footerMenuItem'),hideCursor()" onclick="document.location.href='#'">
			  &nbsp;&nbsp;<html:link styleClass="footerMenuLink" action="SimpleSearchPopulateAction?unprotected_method=populate">SEARCH MODELS</html:link>&nbsp;&nbsp;
			</TD>
			
			<TD>
				<IMG height=16 alt="" src="/camod/images/mainMenuSeparator.gif" width=1>
			</TD>
	
			<TD height="20" class="footerMenuItem" onmouseover="changeMenuStyle(this,'footerMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'footerMenuItem'),hideCursor()" onclick="document.location.href='#'">
			  &nbsp;&nbsp;<html:link styleClass="footerMenuLink" action="/ReturnUserModels.do?method=returnUserModels">SUBMIT MODELS</html:link>&nbsp;&nbsp;
			</TD>
		
			<TD>
				<IMG height=16 alt="" src="/camod/images/mainMenuSeparator.gif" width=1>
			</TD>		
			
			<% 
		        List theRoles = (List) pageContext.getSession().getAttribute(Constants.CURRENTUSERROLES);
		        if (theRoles != null && theRoles.size() > 0)
		        {
		    %>  
			<TD height="20" class="footerMenuItem" onmouseover="changeMenuStyle(this,'footerMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'footerMenuItem'),hideCursor()" onclick="document.location.href='#'">
			  &nbsp;&nbsp;<html:link styleClass="footerMenuLink" action="/AdminRolesPopulateAction">ADMIN</html:link>&nbsp;&nbsp;
			</TD>
			<TD>
				<IMG height=16 alt="" src="/camod/images/mainMenuSeparator.gif" width=1>
			</TD>		
			<% } %>
	
			<!--TD class=footerMenuItem onmouseover="changeMenuStyle(this,'footerMenuItemOver'),showCursor()" onclick="document.location.href='#'" onmouseout="changeMenuStyle(this,'footerMenuItem'),hideCursor()" height=20>
				<A class=footerMenuItem
				 onClick="myRef = window.open('../WebHelp/caMOD_Online_Help/index.html','mywin',
										'left=20,top=20,width=800,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()" >HELP</A></TD>
		
			
			<TD>
			
			<TD>
				<IMG height=16 alt="" src="/camod/images/mainMenuSeparator.gif" width=1>
			</TD-->
		</TR>
	</TBODY>
	</TABLE>

<!-- Main menu ends -->

</TD></TR>



