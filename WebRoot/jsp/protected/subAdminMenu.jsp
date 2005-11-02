<%@ include file="/common/taglibs.jsp"%>
<%@ page import='gov.nih.nci.camod.Constants.*' %>
<%@ page import='gov.nih.nci.camod.Constants' %>
<%@ page import='java.util.List' %>

<SCRIPT src="/camod/scripts/RoboHelp_CSH.js" type=text/javascript></SCRIPT>

<TR><TD class=subMenuPrimaryTitle height=22>ADMINISTRATION</TD></TR>
<TR><TD class=subMenuPrimaryItems>

<DIV>
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5><html:link styleClass="subMenuPrimary" action="AdminRolesPopulateAction">&nbsp;&nbsp;ADMIN ROLES</html:link>
	<BR>
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5><html:link styleClass="subMenuPrimary" action="AdminCommentsAssignmentPopulateAction">&nbsp;&nbsp;VIEW COMMENT ASSIGNMENT</html:link>
	<BR>
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5><html:link styleClass="subMenuPrimary" action="AdminModelsAssignmentPopulateAction">&nbsp;&nbsp;VIEW MODEL ASSIGNMENT</html:link>
	<BR>
	<% 
	   List theRoles = (List) pageContext.getSession().getAttribute(Constants.CURRENTUSERROLES);
	   if (theRoles.contains(Admin.Roles.COORDINATOR))
	   {
	%>  
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5><html:link styleClass="subMenuPrimary" action="AdminRolesAssignmentPopulateAction">&nbsp;&nbsp;ROLE MANAGEMENT</html:link>
	<BR>
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5><html:link styleClass="subMenuPrimary" action="AdminUserManagementPopulateAction">&nbsp;&nbsp;USER MANAGEMENT</html:link>
	<BR>
	<%
	   }
	%>	
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5><html:link styleClass="subMenuPrimary" href="javascript:RH_ShowHelp(0,'WebHelp/!SSL!/WebHelp/caMOD_2.htm>WithNavPane=true', HH_HELP_CONTEXT,0)">&nbsp;&nbsp;HELP</html:link>	
</DIV>

<BR>
</TD></TR>




