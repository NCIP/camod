<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="java.io.*, java.util.*"%>  

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	

			<tr>
				<td class="formTitle" height="20">You have SUCCESSFULLY logged on</td>				
			</tr>			
			
			<tr>
				<td class="resultsBoxWhiteEnd">Welcome, <%=  session.getAttribute("camod.loggedon.username") %><br>
						<a href="submitModels.jsp">Submit Models</a>				
				</td> 
			</tr>
                        
			</TABLE>
		</td></tr></TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="footer.jsp" %>