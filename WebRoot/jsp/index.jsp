<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%@ include file="/common/taglibs.jsp"%>

<c:if test="${READDISCLAIMER == 'true'}">
    <c:redirect url="/login.do"/>
</c:if>
<c:if test="${READDISCLAIMER != 'true'}">
    <c:redirect url="/jsp/viewLicense.jsp"/>
</c:if>


