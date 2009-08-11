<%@ include file="/common/taglibs.jsp"%>

<%
   Object theErrors = pageContext.getRequest().getAttribute(org.apache.struts.Globals.ERROR_KEY);
   
   if (theErrors != null)
   {
       pageContext.getSession().setAttribute(org.apache.struts.Globals.ERROR_KEY, theErrors);
   }
%>
<c:redirect url="/AnimalModelTreePopulateRedirectAction.do"/>


