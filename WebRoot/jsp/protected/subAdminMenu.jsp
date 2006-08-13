<%
/*
 * $Id: subAdminMenu.jsp,v 1.11 2006-08-13 18:19:34 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.10  2005/11/18 21:07:44  georgeda
 * Defect #130, added superuser
 *
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
	&nbsp;<html:link styleClass="subMenuPrimary" href="javascript:openHelpWindow('WebHelp/!SSL!/index.html?single=true&amp;context=caMOD_2.1_OH&amp;topic=login_page_help')" class="helpText">HELP</html:link>	
</DIV>

<BR>
</TD></TR>




