<%@ page import='java.util.List' %>
<%@ page import='gov.nih.nci.camod.Constants' %>

<TR><TD class=mainMenu width="100%" height=20>

<!-- Main menu begins -->
	<TABLE height=20 cellSpacing=0 cellPadding=0 summary=""	border=0>
	<TBODY>
		<TR>
		
		<!-- link 1 begins -->
		<TD height="20" class="footerMenuItem" onmouseover="changeMenuStyle(this,'footerMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'footerMenuItem'),hideCursor()" onclick="document.location.href='#'">
		  &nbsp;&nbsp;<html:link styleClass="footerMenuLink" action="login">HOME</html:link>&nbsp;&nbsp;
		</TD>
		<!-- link 1 ends -->

		<TD>
			<IMG height=16 alt="" src="/camod/images/mainMenuSeparator.gif" width=1>
		</TD>

		<TD height="20" class="footerMenuItem" onmouseover="changeMenuStyle(this,'footerMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'footerMenuItem'),hideCursor()" onclick="document.location.href='#'">
		  &nbsp;&nbsp;<html:link styleClass="footerMenuLink" action="RegisterUserPopulateAction">REGISTER</html:link>&nbsp;&nbsp;
		</TD>
		
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

		<TD height="20" class="footerMenuItem" onmouseover="changeMenuStyle(this,'footerMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'footerMenuItem'),hideCursor()" onclick="document.location.href='#'">
		  &nbsp;&nbsp;<html:link styleClass="footerMenuLink" href="javascript:openHelpWindow('WebHelp/caMOD/index.html')">HELP</html:link>&nbsp;&nbsp;
		</TD>
		
		<TD>
			<IMG height=16 alt="" src="/camod/images/mainMenuSeparator.gif" width=1>
		</TD>
		
		</TR>
	</TBODY>
	</TABLE>

<!-- Main menu ends -->

</TD></TR>



