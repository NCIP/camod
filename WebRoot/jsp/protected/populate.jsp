<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%@ include file="/common/taglibs.jsp"%>

<%
   Object theErrors = pageContext.getRequest().getAttribute(org.apache.struts.Globals.ERROR_KEY);
   
   if (theErrors != null)
   {
       pageContext.getSession().setAttribute(org.apache.struts.Globals.ERROR_KEY, theErrors);
   }
%>
<c:redirect url="/AnimalModelTreePopulateRedirectAction.do"/>


