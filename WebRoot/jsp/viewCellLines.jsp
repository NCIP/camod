<%

/**
 * 
 * $Id: viewCellLines.jsp,v 1.29 2008-01-31 21:18:05 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.28  2007/12/04 13:46:57  pandyas
 * Rotate publication data and rename column heading
 *
 * Revision 1.27  2007/08/27 15:34:58  pandyas
 * Reverted back to EVSPreferredDescription since this was fixed
 *
 * Revision 1.26  2007/06/19 20:41:06  pandyas
 * The EVSPreferredDescription does not return results for Zebrafish vocabulary so the code was changed (This impacts organ.EVSPreferredDescription,  disease.EVSPreferredDescription, and developmentalStage) for all screens with trees
 *
 * Revision 1.25  2006/11/13 20:23:18  pandyas
 * Modified IMG SRC location to include complete location (added /camod/...)
 *
 * Revision 1.24  2006/10/27 18:31:16  pandyas
 * Fixed fields in display page to allow for html markup
 *
 * Revision 1.23  2006/05/25 18:35:16  pandyas
 * added break after MGI number
 *
 * Revision 1.22  2006/05/25 17:36:52  pandyas
 * added break after jax number
 *
 * Revision 1.21  2006/05/25 16:12:27  georgeda
 * Fixed publication link
 *
 * Revision 1.20  2006/05/25 16:03:40  pandyas
 * updated hyperlink for jax number in publications with MTB and MGI links
 *
 * Revision 1.19  2006/05/25 15:57:55  pandyas
 * updated hyperlink for jax number in publications with MTB and MGI links
 *
 * Revision 1.18  2006/04/28 19:49:11  schroedn
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
			<c:forEach var="cl" items="${mdl.cellLineCollection}" 
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
					<camod:highlight><c:out value="${cl.name}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Name of Cell Line</b></td>
				<td class="resultsBoxWhiteEnd">
					<camod:highlight><c:out value="${cl.name}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Organ / Tissue</b></td>
				<td class="resultsBoxGreyEnd">
					<camod:highlight><c:out value="${cl.organ.EVSPreferredDescription}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Experiment</b></td>
				<td class="resultsBoxWhiteEnd">
					<camod:highlight><c:out value="${cl.experiment}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Results</b></td>
				<td class="resultsBoxGreyEnd">
					<camod:highlight><c:out value="${cl.results}" escapeXml="false" />&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Comment</b></td>
				<td class="resultsBoxWhiteEnd">
					<camod:highlight><c:out value="${cl.comments}"escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr><td></td></tr>
			<tr>
				<td class="greySubTitleLeftEnd" colspan=2><b>Publications:</b></td>
			</tr>
			<c:choose>
				<c:when test="${empty cl.publicationCollection}">
					<tr>
						<td class="resultsBoxWhiteEnd" colspan=2><b>No Publications Found</b></td>
					</tr>
				</c:when>
				<c:otherwise>
				  <tr><td colspan="2">
					<table summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">
					<c:forEach var="p" items="${cl.publicationCollection}" varStatus="stat2">
					<tr>
						<c:choose>
							<c:when test = "${stat2.count % 2 == 0}">
								<c:set var="tdClass" value="resultsBoxWhite"/>
							</c:when>
							<c:otherwise>
								<c:set var="tdClass" value="resultsBoxGrey"/>
							</c:otherwise>
						</c:choose>
											
					<tr>
						<td class="GreyBoxTop" width="30%"><b>Publication Status:</b></td>
						<td class="GreyBoxTopRightEnd" width="65%"><c:out value="${p.publicationStatus.name}" escapeXml="false"/>&nbsp;</td>
					</tr>
						       
					<tr>
						<td class="WhiteBox" width="30%"><b>First Author:</b></td>
						<td class="WhiteBoxRightEnd" width="70%"><a name="authors"><c:out value="${p.authors}" escapeXml="false"/></a>&nbsp;</td>
					</tr>
					<tr>
						<td class="GreyBox" width="30%"><b>References:</b></td>
							<!-- Two choose required so we can check for emtpy ZFIN or J Numbers-->			
							<td class="GreyBoxRightEnd" width="70%">
								<c:choose>
									<c:when test="${not empty p.zfinPubId}">
											<a target="_blank" href="http://zfin.org/cgi-bin/webdriver?MIval=aa-pubview2.apg&OID=<c:out value="${p.zfinPubId}"/>">ZFIN</a>
											<br/>
									</c:when>				
									<c:otherwise>					
									</c:otherwise>
								</c:choose>
								<c:choose>
										<c:when test="${not empty p.jaxJNumber}">										
											<c:out value="${p.jaxJNumber}"/>&nbsp;<br/>												
												<a target="_blank" href="http://www.informatics.jax.org/searches/accession_report.cgi?id=<c:out value="${p.jaxJNumber}"/>">MGI</a>
												<br/>
												<a target="_blank" href="http://tumor.informatics.jax.org/mtbwi/referenceDetails.do?accId=<c:out value="${p.jaxJNumber}"/>">MTB</a>
										</c:when>				
									<c:otherwise>&nbsp;						
									</c:otherwise>
								</c:choose>
						</td>					
					</tr>
			       
					<tr>
						<td class="WhiteBox" width="30%"><b>Title:</b></td>
						<td class="WhiteBoxRightEnd" width="70%"><a name="authors"><c:out value="${p.title}" escapeXml="false"/></a>&nbsp;</td>
					</tr>
					
					<tr>
						<td class="GreyBox" width="30%"><b>Journal:</b></td>
						<td class="GreyBoxRightEnd" width="70%"><camod:highlight><c:out value="${p.journal}" escapeXml="false"/>&nbsp;</camod:highlight></td>
					</tr>
						       
					<tr>
						<td class="WhiteBox" width="30%"><b>Year:</b></td>
						<td class="WhiteBoxRightEnd" width="70%"><camod:highlight><c:out value="${p.year}" escapeXml="false"/>&nbsp;</camod:highlight></td>
					</tr>
					<tr>
						<td class="GreyBox" width="30%"><b>Volume:</b></td>
						<td class="GreyBoxRightEnd" width="70%"><camod:highlight><c:out value="${p.volume}" escapeXml="false"/>&nbsp;</camod:highlight></td>
					</tr>
			
					<tr>
						<td class="WhiteBox" width="30%"><b>Pages:</b></td>
						<td class="WhiteBoxRightEnd" width="70%"><camod:highlight><c:out value="${p.startPage}"/> - </camod:highlight>
								<camod:highlight><c:out value="${p.endPage}"/></camod:highlight></td>
					</tr>
						       
					<tr>
						<td class="GreyBox" width="30%"><b>Abstract in PubMed:</b></td>
						<td class="GreyBoxRightEnd" width="70%">
								<a target="_pubmed" href=" http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?cmd=retrieve&db=pubmed&dopt=abstract&list_uids=<c:out value="${p.pmid}"/>">
								<IMG src="/camod/images/pubmed_70.gif" align="middle">
								</a>
						</td>
					</tr>
							
					<tr>
						<td class="WhiteBox" width="30%"><b>Comment:</b></td>
						<td class="WhiteBoxRightEnd" width="70%"><camod:highlight><c:out value="${p.comments}" escapeXml="false"/>&nbsp;</camod:highlight></td>
					</tr>
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