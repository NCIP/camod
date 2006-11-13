<%

/**
 * 
 * $Id: viewPublications.jsp,v 1.23 2006-11-13 20:20:03 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.22  2006/11/13 17:13:58  pandyas
 * #468 - remove width and height variable for mtb image icon on header of serach results and view pages with Jax data
 *
 * Revision 1.21  2006/11/08 19:11:17  pandyas
 * added MTB logo onto view screens for Jackson Lab models
 *
 * Revision 1.20  2006/10/27 18:31:16  pandyas
 * Fixed fields in display page to allow for html markup
 *
 * Revision 1.19  2006/05/25 18:33:57  pandyas
 * added break after MGI number
 *
 * Revision 1.18  2006/05/25 17:34:04  pandyas
 * added break after jax number
 *
 * Revision 1.17  2006/05/25 16:10:58  pandyas
 * updated hyperlink for jax number in publications with MTB and MGI links
 *
 * Revision 1.16  2006/05/25 16:04:17  pandyas
 * updated hyperlink for jax number in publications with MTB and MGI links
 *
 * Revision 1.15  2006/05/25 15:57:33  pandyas
 * updated hyperlink for jax number in publications with MTB and MGI links
 *
 * Revision 1.14  2006/05/25 15:45:11  pandyas
 * adding hyperlink for jax number in publications with MTB and MGI links
 *
 * Revision 1.13  2006/04/28 19:52:10  schroedn
 * Defect #55
 * Added Keyword Highlighting to this jsp
 *
 *
 */

%>

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
					<camod:highlight>
						<c:out value="${mdl.modelDescriptor}" escapeXml="false"/>&nbsp;
						<c:if test="${mdl.externalSource == 'Jax MTB'}">
							<IMG src="/camod/images/mtb_logo.jpg" >
						</c:if>							
					</camod:highlight>
				</td>				
			</tr>			
			
			<tr>
				<td class="greySubTitleLeft" width="10%">Publication Status</td>
				<td class="greySubTitleLeft" width="20%">First Author</td>
				<td class="greySubTitleLeft" width="15%">JAX Number</td>
				<td class="greySubTitleLeft" width="25%">Title</td>
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
					<camod:highlight><c:out value="${p.publicationStatus.name}"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="15%">
					<camod:highlight><c:out value="${p.authors}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="15%">				
					<c:out value="${p.jaxJNumber}"/>&nbsp;<br/>
						<c:if test="${not empty p.jaxJNumber}">												
							(<a target="_blank" href="http://www.informatics.jax.org/searches/accession_report.cgi?id=<c:out value="${p.jaxJNumber}"/>">MGI</a>)
						</c:if><br/>
							<c:if test="${not empty p.jaxJNumber}">
							(<a target="_blank" href="http://tumor.informatics.jax.org/mtbwi/referenceDetails.do?accId=<c:out value="${p.jaxJNumber}"/>">MTB</a>)
						</c:if>											
				</td>				
				<td class="<c:out value="${tdClass}"/>" width="30%">
					<camod:highlight><c:out value="${p.title}" escapeXml="false" />&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="10%">
					<camod:highlight><c:out value="${p.journal}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="5%">
					<camod:highlight><c:out value="${p.year}"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="10%">
					<camod:highlight><c:out value="${p.volume}"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="10%">
					<camod:highlight><c:out value="${p.startPage}"/> - </camod:highlight>
					<camod:highlight><c:out value="${p.endPage}"/> </camod:highlight>
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