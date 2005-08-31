<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<logic:equal parameter="page" value="ModelCharacteristics">
<%@ include file="/jsp/viewModelCharacteristics.jsp"%>
</logic:equal>

<logic:equal parameter="page" value="GeneticDescription">
<%@ include file="/jsp/viewGeneticDescription.jsp" %>
</logic:equal>

<logic:equal parameter="page" value="CarcinogenicInterventions">
<%@ include file="/jsp/viewCarcinogenicInterventions.jsp" %>
</logic:equal>

<logic:equal parameter="page" value="CellLines">
<%@ include file="/jsp/viewCellLines.jsp"%>
</logic:equal>

<logic:equal parameter="page" value="Publications">
<%@ include file="/jsp/viewPublications.jsp"%>
</logic:equal>

<logic:equal parameter="page" value="Histopathology">
<%@ include file="/jsp/viewHistopathology.jsp"%>
</logic:equal>

<logic:equal parameter="page" value="TherapeuticApproaches">
<%@ include file="/jsp/viewTherapeuticApproaches.jsp"%>
</logic:equal>

<logic:equal parameter="page" value="Images">
<%@ include file="/jsp/viewImages.jsp"%>
</logic:equal>

<logic:equal parameter="page" value="Microarrays">
<%@ include file="/jsp/viewMicroarrays.jsp"%>
</logic:equal>

<logic:equal parameter="page" value="TransplantXenograft">
<%@ include file="/jsp/viewTransplantXenograft.jsp"%>
</logic:equal>

<%@ include file="/jsp/footer.jsp" %>