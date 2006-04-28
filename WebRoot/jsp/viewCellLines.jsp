<%

/**
 * 
 * $Id: viewCellLines.jsp,v 1.18 2006-04-28 19:49:11 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	

<bean:define id="mdl" name="animalmodel"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" 
			align="center" width="100%">	
			<tr>
				<td class="formTitle" height="20" colspan="2">
				    Cell Lines - Model: <c:out value="${mdl.modelDescriptor}" escapeXml="false" />
				</td>				
			</tr>
			<c:forEach var="p" items="${mdl.cellLineCollection}" 
				       varStatus="stat">
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
			<tr>
				<td class="formTitleBlue" height="20" colspan="2">
					<camod:highlight><c:out value="${p.name}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Name of Cell Line</b></td>
				<td class="resultsBoxWhiteEnd">
					<camod:highlight><c:out value="${p.name}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Organ / Tissue</b></td>
				<td class="resultsBoxGreyEnd">
					<camod:highlight><c:out value="${p.organ.EVSPreferredDescription}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Experiment</b></td>
				<td class="resultsBoxWhiteEnd">
					<camod:highlight><c:out value="${p.experiment}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Results</b></td>
				<td class="resultsBoxGreyEnd">
					<camod:highlight><c:out value="${p.results}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Comment</b></td>
				<td class="resultsBoxWhiteEnd">
					<camod:highlight><c:out value="${p.comments}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr><td></td></tr>
			<tr>
				<td class="greySubTitleLeftEnd" colspan=2><b>Publications:</b></td>
			</tr>
			<c:choose>
				<c:when test="${empty p.publicationCollection}">
					<tr>
						<td class="resultsBoxWhiteEnd" colspan=2><b>No Publications Found</b></td>
					</tr>
				</c:when>
				<c:otherwise>
				  <tr><td colspan="2">
					<table summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
					<tr>
						<td class="greySubTitleLeft" width="10%">Publication Status</td>
						<td class="greySubTitleLeft" width="15%">First Author</td>
						<td class="greySubTitleLeft" width="15%">JAX Number</td>						
						<td class="greySubTitleLeft" width="30%">Title</td>
						<td class="greySubTitleLeft" width="10%">Journal</td>
						<td class="greySubTitleLeft" width="5%">Year</td>
						<td class="greySubTitleLeft" width="10%">Volume</td>
						<td class="greySubTitleLeft" width="10%">Pages</td>
						<td class="greySubTitle" width="10%">Abstract in PubMed</td>
					</tr>
					<c:forEach var="pub" items="${p.publicationCollection}" varStatus="stat2">
					<tr>
						<c:choose>
							<c:when test = "${stat2.count % 2 == 0}">
								<c:set var="tdClass" value="resultsBoxWhite"/>
							</c:when>
							<c:otherwise>
								<c:set var="tdClass" value="resultsBoxGrey"/>
							</c:otherwise>
						</c:choose>
						<td class="<c:out value="${tdClass}"/>" width="10%">
							<camod:highlight><c:out value="${pub.publicationStatus.name}"/></camod:highlight>
						</td>
						<td class="<c:out value="${tdClass}"/>" width="15%">
							<camod:highlight><c:out value="${pub.authors}"/>&nbsp;</camod:highlight>
						</td>
						<td class="<c:out value="${tdClass}"/>" width="15%">
							<c:out value="${pub.jaxJNumber}"/>&nbsp;
						</td>							
						<td class="<c:out value="${tdClass}"/>" width="30%">
							<camod:highlight><c:out value="${pub.title}"/>&nbsp;</camod:highlight>
						</td>
						<td class="<c:out value="${tdClass}"/>" width="10%">
							<camod:highlight><c:out value="${pub.journal}"/>&nbsp;</camod:highlight>
						</td>
						<td class="<c:out value="${tdClass}"/>" width="5%">
							<camod:highlight><c:out value="${pub.year}"/>&nbsp;</camod:highlight>
						</td>
						<td class="<c:out value="${tdClass}"/>" width="10%">
							<camod:highlight><c:out value="${pub.volume}"/>&nbsp;</camod:highlight>
						</td>
						<td class="<c:out value="${tdClass}"/>" width="10%">
							<camod:highlight><c:out value="${pub.startPage}"/> - </camod:highlight>
							<camod:highlight><c:out value="${pub.endPage}"/> </camod:highlight>
						</td>
						<td class="<c:out value="${tdClass}"/>End" width="10%">
							<a target="_pubmed" href=" http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?cmd=retrieve&db=pubmed&dopt=abstract&list_uids=<c:out value="${pub.pmid}"/>">
							<IMG src="images/pubmed_70.gif" align="middle">
							</a>
						</td>
					</tr>
					</c:forEach>
					</table>
					</tr></td>
				</c:otherwise>
			</c:choose >
			<tr><td colspan="2">&nbsp;</td></tr>
			</c:forEach>
		
			</TABLE>
		</td></tr></TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
    <% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.CELL_LINES); %>
    <%@ include file="/jsp/includeComments.jsp" %>
    </TABLE>
</TABLE>

<%@ include file="/jsp/footer.jsp" %>