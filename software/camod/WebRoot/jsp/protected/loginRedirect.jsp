<%@ include file="/common/taglibs.jsp"%>
<%pageContext.getRequest().setAttribute("server", pageContext.getRequest().getServerName());%>
<base href="https://<%= request.getServerName()%>:<%= request.getServerPort()%>/camod/">
<meta http-equiv="Refresh" content="0;url=http://<c:out value="${server}"/>/camod/ReturnUserModels.do?method=returnUserModels">
<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<TABLE class=contentPage cellSpacing=0 cellPadding=0 width=100% summary="" border=0 >
	<TR>
		<TD valign="top">
			<B><FONT FACE="Arial" SIZE=2><P>Login Successful</P>
			</B>
			<P align="justify">
			Login successful.  If you are not redirected to the login in 5 seconds, please click on the following link:<br/><br/>
			</P>
			<P align="justify">
			<a href="http://<c:out value="${server}"/>/camod/ReturnUserModels.do?method=returnUserModels">click</a>
			</P>
		</TD>
	</TR>
</TABLE>
<%@ include file="/jsp/footer.jsp" %>