<SCRIPT src="/camod/scripts/RoboHelp_CSH.js" type=text/javascript></SCRIPT>
<TR><TD height="100%" class=subMenu valign=top width="210">
	  
<!-- standard submenu begins -->
	  
    <TABLE height="100%" cellSpacing=0 cellPadding=0 width="210" summary="" border=0>
      <TBODY>   
      
<!-- Place Code here to determine the sub-Menu Links -->
	<%@ page import="java.io.File" %>
	<%@ page import="gov.nih.nci.camod.webapp.util.SidebarUtil" %>	
	<%@ page import="gov.nih.nci.camod.Constants" %>
	
	<% 
		//Get the filename of the current jsp page and display the correct submenu
		String jspName = new File(request.getRealPath(request.getServletPath())).getName();
		
		SidebarUtil sidebar = new SidebarUtil();
		String pageName = sidebar.findSubMenu( request, jspName );
		
		if ( ! pageName.equals("redirect") ) { 
			%><jsp:include page="<%=pageName%>" /><%
		} else
			response.sendRedirect( "login.jsp" );
	%>

    <TR>
	<TD class=subMenuPrimaryTitle height=22>QUICK LINKS <!-- anchor to skip sub menu --><A
	  	href="login.jsp"><IMG
	  	height=1 alt="Skip Menu" src="submit_files/shim.gif" width=1
	  	border=0></A> </TD></TR>
    <TR>
	<TD class=subMenuSecondaryTitle
		onmouseover="changeMenuStyle(this,'subMenuSecondaryTitleOver'),showCursor()"
		onclick="document.location.href='#'"
		onmouseout="changeMenuStyle(this,'subMenuSecondaryTitle'),hideCursor()"
		height=20><A class=subMenuSecondary
	  	href="http://www.cancer.gov" target="_blank">NCI HOME</A></TD></TR>
    <TR>
	<TD class=subMenuSecondaryTitle
		onmouseover="changeMenuStyle(this,'subMenuSecondaryTitleOver'),showCursor()"
		onclick="document.location.href='#'"
		onmouseout="changeMenuStyle(this,'subMenuSecondaryTitle'),hideCursor()"
		height=20><A class=subMenuSecondary
	  	href="http://ncicb.nci.nih.gov" target="_blank">NCICB HOME</A></TD></TR>
  	<TR>
	<TD class=subMenuSecondaryTitle
		onmouseover="changeMenuStyle(this,'subMenuSecondaryTitleOver'),showCursor()"
		onclick="document.location.href='#'"
		onmouseout="changeMenuStyle(this,'subMenuSecondaryTitle'),hideCursor()"
		height=20><A class=subMenuSecondary
		href="http://emice.nci.nih.gov" target="_blank">EMICE WEBSITE</A></TD></TR>
    <TR>
	<TD class=subMenuSecondaryTitle
		  onmouseover="changeMenuStyle(this,'subMenuSecondaryTitleOver'),showCursor()"
		  onclick="document.location.href='#'"
		  onmouseout="changeMenuStyle(this,'subMenuSecondaryTitle'),hideCursor()"
		  height=20><A class=subMenuSecondary
		  href="http://cancerimages.nci.nih.gov/caIMAGE/index.jsp" target="_blank">CANCER IMAGES DATABASE</A></TD></TR>	    
    <TR>
	<TD class=subMenuSecondaryTitle
		onmouseover="changeMenuStyle(this,'subMenuSecondaryTitleOver'),showCursor()"
		onclick="document.location.href='#'"
		onmouseout="changeMenuStyle(this,'subMenuSecondaryTitle'),hideCursor()"
		height=20><A class=subMenuSecondary
		href="http://ncicb.nci.nih.gov/NCICB/support" target="_blank">NCICB SUPPORT</A></TD></TR>
      <TR>
	<TD class=subMenuSecondaryTitle
		onmouseover="changeMenuStyle(this,'subMenuSecondaryTitleOver'),showCursor()"
		onclick="document.location.href='#'"
		onmouseout="changeMenuStyle(this,'subMenuSecondaryTitle'),hideCursor()"
		height=20><A class=subMenuSecondary
		 href="javascript:RH_ShowHelp(0,'WebHelp/!SSL!/WebHelp/caMOD_2.htm>WithNavPane=true', HH_HELP_CONTEXT,0)" >HELP</A></TD></TR>

	<%
	if( session.getAttribute("camod.loggedon.username") != null ) {
		%>
		<TR><TD class="loggedInBox" width="100%">Currently logged in as:<br>
		<TABLE><TR><TD class="loggedInBoxBorderless">
		     <b><%= session.getAttribute("camod.loggedon.username") %></b><br>
		     <logic:present name="<%= Constants.CURRENTUSERROLES %>">
			    <logic:iterate name="<%= Constants.CURRENTUSERROLES %>" id="role" type="String">
			        &nbsp;&nbsp;&nbsp;&nbsp;<font color="#475b82"> - <c:out value="${role}" /><br>
			    </logic:iterate>
			 </logic:present>
		     <a href="/camod/AdminUserSettingsPopulateAction.do">User Settings</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/camod/LogoutAction.do">Log out</a>
		     <br>
		</TD></TR></TABLE>
		</TD></TR>
	<%
	} 
	%>

      <TR>
	<TD class=subMenuFill height="100%">&nbsp;</TD></TR>
      <TR>
	<TD class=subMenuFooter height=23>&nbsp;</TD></TR>
	</TBODY>
   </TABLE>				
<!-- standard submenu ends -->

</TD>

<!-- Following cell is for main content -->
<TD valign=top width="100%"> 

<!-- Following table is ended at the end of the Application footer -->
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" summary="" border=0>
	<TBODY>	

<!-- Can place code here to determine what Main Menu is needed (this is placed on top of the main content in center of page)-->	
<%@ include file="loginMenu.jsp" %>

<TR><TD valign=top width="100%">

<!-- Main Content Begins -->
<TABLE class=contentPage cellSpacing=0 cellPadding=0 width=700 summary="" border=0>
<TBODY>
<TR>
<TD valign="top">




