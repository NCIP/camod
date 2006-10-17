<%

/**
 * 
 * $Id: sidebar.jsp,v 1.16 2006-10-17 16:08:28 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.15  2006/08/13 18:17:54  pandyas
 * Minor change - added file name (eventually to all Jsps)
 *
 * Revision 1.14  2006/07/31 20:08:12  pandyas
 * Modified page link for Help file:
 * Swapped the new online help generated from ePublisher with the current online help generated from RoboHelp.  From Jill Hadfield
 *
 * Revision 1.13  2006/04/28 19:40:44  schroedn
 * Defect #261
 * Added links to Saved Queries and Query History when user is logged in
 *
 *
 */

%>

<TR><TD height="100%" class=subMenu valign=top width="210">
<script language="JavaScript" src="scripts/global.js"></script>
  
<!-- standard submenu begins -->
<!-- sidebar.jsp -->	  
    <TABLE height="100%" cellSpacing=0 cellPadding=0 width="210" summary="" border=0>
      <TBODY>   
      
<!-- Place Code here to determine the sub-Menu Links -->
	<%@ page import="java.io.File" %>
	<%@ page import="gov.nih.nci.camod.webapp.util.SidebarUtil" %>	
	<%@ page import="gov.nih.nci.camod.Constants" %>
	
	<% 
	    //Get number of saved queries for current logged on user
	    String numSavedQueries = (String) request.getSession().getAttribute(Constants.NUMBEROFSAVEDQUERIES);
	    
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
		 href="javascript:openHelpWindow('WebHelp/!SSL!/index.html')" >HELP</A></TD></TR>
		 

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
		     <img src="/camod/images/subMenuCircleFilled.gif" border="0">&nbsp;<a href="/camod/AdminUserSettingsPopulateAction.do">User Settings</a><br>
		     <img src="/camod/images/subMenuCircleFilled.gif" border="0">&nbsp;<a href="/camod/CustomizeSearchResultsPopulateAction.do">Customize Results</a><br>
			 <img src="/camod/images/subMenuCircleFilled.gif" border="0">&nbsp;<a href="/camod/ReturnUserSavedQueries.do?method=returnSavedUserQueries">View Saved Queries</a>&nbsp;( <b><%=numSavedQueries%></b> saved )<br>
		     <img src="/camod/images/subMenuCircleFilled.gif" border="0">&nbsp;<a href="/camod/ReturnUserQueries.do?method=returnUserQueries">View Query History</a><br>
		     <img src="/camod/images/subMenuArrow.gif" border="0">&nbsp;<a href="/camod/LogoutAction.do">Log out</a>
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




