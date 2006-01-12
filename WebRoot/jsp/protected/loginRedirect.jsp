<%@ include file="/common/taglibs.jsp"%>
<%pageContext.getRequest().setAttribute("server", pageContext.getRequest().getServerName());%>
<meta http-equiv="Refresh" content="0;url=http://<c:out value="${server}"/>/camod/ReturnUserModels.do?method=returnUserModels">
<a href="http://<c:out value="${server}"/>/camod/ReturnUserModels.do?method=returnUserModels">click</a>