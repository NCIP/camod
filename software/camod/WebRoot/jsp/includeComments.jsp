<%
 /*
  *   $Id: includeComments.jsp,v 1.5 2005-11-16 17:03:06 georgeda Exp $
  *   
  *   $Log: not supported by cvs2svn $
  *   Revision 1.4  2005/11/16 15:31:35  georgeda
  *   Defect #41. Clean up of email functionality
  *
  */
%>
<%@ page import='gov.nih.nci.camod.Constants.*' %>
<%@ page import='gov.nih.nci.camod.Constants' %>
<%@ page import='gov.nih.nci.camod.domain.Comments' %>

<!-- display comments -->
		
<!--  set some attributes to build the URL -->
<% pageContext.setAttribute("modelIdTag", Parameters.MODELID); %>
<% pageContext.setAttribute("modelSectionTag", Parameters.MODELSECTIONNAME); %>
		
<% if (pageContext.getSession().getAttribute(Constants.CURRENTUSER) != null) { %>

<c:set var="uri" value="javascript: rs('commentWin','AddCommentsPopulateAction.do?${modelIdTag}=${mdl.id}&${modelSectionTag}=${modelSectionValue}',360,305);"/>
<tr>
    <td class="WhiteBoxFull" width="100%" colspan="2"><a href='<c:out value="${uri}"/>'><IMG src="images/comment.gif" border=0 align=middle> <b>Place your comment here</b></a></td>
</tr>
    
<% } else { %>

<tr>
    <td class="WhiteBoxFull" width="100%" colspan="2"><IMG src="images/comment.gif" border=0 align=middle> <b>Comments (Please login to submit new comments)</b></td>
</tr>

<% } %>

<tr>
    <c:forEach var="comments" items="${aCommentsList}" >
        <tr>
            <td class="WhiteBoxNoEnd" width="30%" colspan="1">
                <c:if test="${not empty comments.submitter.emailAddress}">
				    <a href="mailto:<c:out value="${comments.submitter.emailAddress}"/>">
				</c:if>
				<c:out value="${comments.submitter.displayName}"/>
				<c:if test="${not empty comments.submitter.emailAddress}">
				    </a>
				</c:if>
            </td>
            <td class="WhiteBox" width="70%" colspan="1"><c:out value="${comments.remark}"/></td>
        </tr>
    </c:forEach>
</tr>