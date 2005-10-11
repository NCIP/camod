<%@ page import='gov.nih.nci.camod.Constants.*' %>
<%@ page import='gov.nih.nci.camod.Constants' %>

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
        <td class="WhiteBoxFull" width="100%" colspan="2"><IMG src="images/comment.gif" border=0 align=middle> <b>Comments</b></td>
    </tr>
<% } %>
<c:forEach var="comments" items="${aCommentsList}" >
    <tr>
        <td class="WhiteBox" width="100%" colspan="2"><c:out value="${comments.remark}"/></td>
    </tr>
</c:forEach>