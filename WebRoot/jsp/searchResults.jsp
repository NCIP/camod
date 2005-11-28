<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.domain.Histopathology" %>	
<%@ page import="gov.nih.nci.camod.Constants" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%      
	List results = (List) request.getSession().getAttribute( Constants.SEARCH_RESULTS );
	if (results == null) {
	    results = new ArrayList();
	    request.getSession().setAttribute(Constants.SEARCH_RESULTS, results );
	}
	int size = results.size();
	System.out.println( "SIZE: " + size );
%>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" width="100%">	

				<tr>
					<td class="formTitle" height="20" colspan="4">Search Results</td>
				</tr>
			</TABLE>
			
            <display:table id="row" name="${sessionScope.searchResults}"
              		
	          		 pagesize = "15"
	          		 cellpadding="5" cellspacing="0" border="1"
 	                      width = "100%"
 	                      
 	                 > 	 

 	                 <display:column title="No." >
 	                     <c:out value="${row_rowNum}"/>
				     </display:column>      
		             <display:column href="/camod/ViewModelAction.do?unprotected_method=populateModelCharacteristics&" paramId="aModelID" paramProperty="id" title="Model Descriptor" >
		                 <c:out escapeXml="false" value="${row.modelDescriptor}"/>
				     </display:column>
				     <display:column title="Species">
				         <c:out value="${row.species}"/>
				     </display:column>     
				     <display:column title="Tumor Sites">
				         <c:out escapeXml="false" value="${row.tumorSites}"/>&nbsp;
				     </display:column>	                   
	          </display:table>	
	          
		</td></tr></TABLE>
	</td></tr></TABLE>
</td></tr></TABLE>

<%@ include file="/jsp/footer.jsp" %>