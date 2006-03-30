<base href="http://<%= request.getServerName()%>:<%= request.getServerPort()%>/caarray/">
<link rel="stylesheet" type="text/css" href="css/caArray.css" />
<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<TABLE class=contentPage cellSpacing=0 cellPadding=0 width=100% summary="" border=0 >
	<TR>
		<TD valign="top">
			<B><FONT FACE="Arial" SIZE=2><P>SYSTEM ERROR</P>
			</B>
			<P align="justify">
			An error has unfortunately occurred.   We apologize for the inconvenience.  Please click <html:link action="login.do"><b>Here</b></html:link>
			
			to return to the caMOD application. <br/><br/>
			</P>
			<P align="justify">
			
			If you need more assistance please contact NCICB Application Support as outlined below.
			
			<br/><br/><b>URL:&nbsp</b> <a href="http://ncicbsupport.nci.nih.gov/sw/">http://ncicbsupport.nci.nih.gov/sw/</a><br/>
			<b>Email:&nbsp</b> <a href="mailto:ncicb@pop.nci.nih.gov?subject=caArray System Error">ncicb@pop.nci.nih.gov</a><br/>
			<b>Phone:&nbsp</b> 301-451-4384 or toll free: 888-478-4423
			
			</P>
		</TD>
	</TR>
</TABLE>
<%@ include file="/jsp/footer.jsp" %>