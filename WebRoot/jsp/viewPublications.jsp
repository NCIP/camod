<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	

<bean:define id="mdl" name="animalmodel"/>
<bean:define id="pubColl" name="publications"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	

			<tr>
				<td class="formTitle" height="20" colspan="9">
					Publications - Model:
					<c:out value="${mdl.modelDescriptor}" escapeXml="false"/>
				</td>				
			</tr>			
			
			<tr>
				<td class="greySubTitleLeft" width="10%">Publication Status</td>
				<td class="greySubTitleLeft" width="15%">First Author</td>
				<td class="greySubTitleLeft" width="30%">Title</td>
				<td class="greySubTitleLeft" width="10%">Journal</td>
				<td class="greySubTitleLeft" width="5%">Year</td>
				<td class="greySubTitleLeft" width="10%">Volume</td>
				<td class="greySubTitleLeft" width="10%">Pages</td>
				<td class="greySubTitle" width="10%">Abstract in PubMed</td>
			</tr>
			
			<c:forEach var="p" items="${pubColl}" varStatus="stat">
			<tr>
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
				<td class="<c:out value="${tdClass}"/>" width="10%">
					<c:out value="${p.publicationStatus.name}"/>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="15%">
					<c:out value="${p.authors}"/>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="30%">
					<c:out value="${p.title}"/>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="10%">
					<c:out value="${p.journal}"/>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="5%">
					<c:out value="${p.year}"/>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="10%">
					<c:out value="${p.volume}"/>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="10%">
					<c:out value="${p.startPage}"/> - 
					<c:out value="${p.endPage}"/> 
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="10%">
					<a target="_pubmed" href=" http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?cmd=retrieve&db=pubmed&dopt=abstract&list_uids=<c:out value="${p.pmid}"/>">
					<IMG src="images/pubmed_70.gif" align="middle">
					</a>
				</td>					
			</tr>
			</c:forEach>
			
			</TABLE>
		</td></tr></TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
    <% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.PUBLICATIONS); %>
    <%@ include file="/jsp/includeComments.jsp" %>
    </TABLE>
</TABLE>

<%@ include file="/jsp/footer.jsp" %>