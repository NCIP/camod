<%@ include file="/common/taglibs.jsp"%>

<c:if test="${READDISCLAIMER != 'true'}">
    <c:redirect url="/jsp/viewLicense.jsp"/>
</c:if>


