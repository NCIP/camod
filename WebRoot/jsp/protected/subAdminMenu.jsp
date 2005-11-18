<%
/*
 * $Id: subAdminMenu.jsp,v 1.10 2005-11-18 21:07:44 georgeda Exp $
 *
 * $Log: not supported by cvs2svn $
 *
 */
%>

<%@ include file="/common/taglibs.jsp"%>
<%@ page import='gov.nih.nci.camod.Constants.*' %>
<%@ page import='gov.nih.nci.camod.Constants' %>
<%@ page import='java.util.List' %>

<SCRIPT src="/camod/scripts/RoboHelp_CSH.js" type=text/javascript></SCRIPT>

<TR><TD class=subMenuPrimaryTitle height=22>ADMINISTRATION</TD></TR>
<TR><TD class=subMenuPrimaryItems>

<DIV>
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5>
	&nbsp;<html:link styleClass="subMenuPrimary" action="AdminRolesPopulateAction">ADMIN ROLES</html:link>
	<BR>
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5>
	&nbsp;<html:link styleClass="subMenuPrimary" action="AdminCommentsAssignmentPopulateAction">VIEW COMMENT ASSIGNMENT</html:link>
	<BR>
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5>
	&nbsp;<html:link styleClass="subMenuPrimary" action="AdminModelsAssignmentPopulateAction">VIEW MODEL ASSIGNMENT</html:link>
	<BR>
	<% 
	   List theRoles = (List) pageContext.getSession().getAttribute(Constants.CURRENTUSERROLES);
	   if (theRoles.contains(Admin.Roles.COORDINATOR))
	   {
	%>  
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5>
	&nbsp;<html:link styleClass="subMenuPrimary" action="AdminRolesAssignmentPopulateAction">ROLE MANAGEMENT</html:link>
	<BR>
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5>
	&nbsp;<html:link styleClass="subMenuPrimary" action="AdminUserManagementPopulateAction">USER MANAGEMENT</html:link>
	<BR>
	<%
	   }
	%>	
	<% 
	   if (theRoles.contains(Admin.Roles.SUPER_USER))
	   {
	%>
    <IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5>
	&nbsp;<html:link styleClass="subMenuPrimary" action="AdminEditModelsPopulateAction">EDIT MODELS</html:link>
	<BR>
	<%
	   }
	%>	
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5>
	&nbsp;<html:link styleClass="subMenuPrimary" href="javascript:RH_ShowHelp(0,'WebHelp/!SSL!/WebHelp/caMOD_2.htm>WithNavPane=true', HH_HELP_CONTEXT,0)">HELP</html:link>	
</DIV>

<BR>
</TD></TR>




