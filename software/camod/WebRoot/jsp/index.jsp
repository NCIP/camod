<%@ include file="/common/taglibs.jsp"%>

<c:if test="${READDISCLAIMER == 'true'}">
    <c:redirect url="/login.do"/>
</c:if>
<c:if test="${READDISCLAIMER != 'true'}">
    <c:redirect url="/jsp/viewLicense.jsp"/>
</c:if>


