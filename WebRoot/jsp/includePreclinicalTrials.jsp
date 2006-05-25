<%
 /*
 * $Log: not supported by cvs2svn $
 * Revision 1.16  2006/05/25 15:57:43  pandyas
 * updated hyperlink for jax number in publications with MTB and MGI links
 *
 * Revision 1.15  2006/05/25 15:26:50  pandyas
 * adding hyperlink for jax number in therapy
 *
 * Revision 1.14  2006/05/25 15:16:54  pandyas
 * added Jax column
 *
 * Revision 1.13  2006/05/22 17:38:09  pandyas
 * Added tumorResponse to view screen - before comment
 *
 * Revision 1.12  2006/04/28 19:36:19  schroedn
 * Defect #55
 * Added Keyword Highlighting to this jsp
 *
 * Revision 1.11  2006/04/27 15:09:01  pandyas
 * Modified while testing caMod 2.1
 *
 * Revision 1.10  2006/04/17 19:08:19  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.9  2005/11/28 16:31:48  pandyas
 * Defect #187:  Reordered fields on therapy search page to match submission page
 *
 * Revision 1.8  2005/11/28 13:52:48  georgeda
 * Defect #207, handle nulls for pages w/ uncontrolled vocab
 *
 * Revision 1.7  2005/11/17 21:15:10  georgeda
 * Defect #86.  Removed unneeded field.
 *
 *
 * $Id: includePreclinicalTrials.jsp,v 1.17 2006-05-25 16:00:25 pandyas Exp $
 */
%>
<tr>
	<td class="formTitleBlue" height="20" colspan="2">
		Summary of the pre-clinical study in <c:out value="${agt.name}"/>
	</td>
</tr>	
<tr>
	<td class="resultsBoxWhite" width="25%"><b>Experiment</b></td>
	<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
		<camod:highlight><c:out value="${t.experiment}"/></camod:highlight>
	</td>
</tr>
<tr>
	<td class="resultsBoxGrey" width="25%"><b>Dose</b></td>
	<td class="resultsBoxGreyEnd" width="75%">&nbsp;
		<camod:highlight><c:out value="${t.treatment.dosage}"/>&nbsp;<c:out value="${cd.treatment.dosageUnit}"/></camod:highlight>
	</td>
</tr>
<tr>
	<td class="resultsBoxWhite" width="25%"><b>Administration Route</b></td>
	<td class="resultsBoxWhiteEnd" width="75%">
		<c:choose>
			<c:when test="${empty t.treatment.administrativeRoute}">
				<camod:highlight><c:out value="${t.treatment.adminRouteUnctrlVocab}"/></camod:highlight>
			</c:when>
			<c:otherwise>
				<camod:highlight><c:out value="${t.treatment.administrativeRoute}"/></camod:highlight>
			</c:otherwise>
		</c:choose>&nbsp;
	</td>
</tr>
<tr>
	<td class="resultsBoxGrey" width="25%"><b>Gender</b></td>
	<td class="resultsBoxGreyEnd" width="75%">&nbsp;
		<camod:highlight><c:out value="${t.treatment.sexDistribution.type}"/></camod:highlight>
	</td>
</tr>
<tr>
	<td class="resultsBoxWhite" width="25%"><b>Age at Treatment</b></td>
	<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
		<camod:highlight><c:out value="${t.treatment.ageAtTreatment}"/>&nbsp;<c:out value="${t.treatment.ageAtTreatmentUnit}"/></camod:highlight>
	</td>
</tr>
<tr>
	<td class="resultsBoxGrey" width="25%"><b>Results</b></td>
	<td class="resultsBoxGreyEnd" width="75%">&nbsp;
		<camod:highlight><c:out value="${t.results}"/></camod:highlight>
	</td>
</tr>
<tr>
	<td class="resultsBoxWhite" width="25%"><b>Toxicity Grade</b></td>
	<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
		<camod:highlight><c:out value="${t.toxicityGrade}"/></camod:highlight>
	</td>
</tr>			
<tr>
	<td class="resultsBoxGrey" width="25%"><b>Biomarker</b></td>
	<td class="resultsBoxGreyEnd" width="75%">
		<camod:highlight><c:out value="${t.biomarker}"/>&nbsp;</camod:highlight>
	</td>
</tr>
<tr>
	<td class="resultsBoxWhite" width="25%"><b>Tumor Response</b></td>
	<td class="resultsBoxWhiteEnd" width="75%">
		<camod:highlight><c:out value="${t.tumorResponse}"/>&nbsp;</camod:highlight>
	</td>
</tr>
<tr>
	<td class="resultsBoxGrey" width="25%"><b>Comment </b></td>
	<td class="resultsBoxGreyEnd" width="75%">&nbsp;
		<camod:highlight><c:out value="${t.comments}"/></camod:highlight>
	</td>
</tr>
<tr><td></td></tr>
<tr>
	<td class="greySubTitleLeftEnd" colspan=2><b>Publications:</b></td>
</tr>
<c:choose>
	<c:when test="${empty t.publicationCollection}">
		<tr>
			<td class="resultsBoxWhiteEnd" colspan=2><b>No Publications Found</b></td>
		</tr>
	</c:when>
	<c:otherwise>
	  <tr><td colspan="2">
		<table summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
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
		<c:forEach var="p" items="${t.publicationCollection}" varStatus="stat2">
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
				<camod:highlight><c:out value="${p.publicationStatus.name}"/></camod:highlight>
			</td>
			<td class="<c:out value="${tdClass}"/>" width="15%">
				<camod:highlight><c:out value="${p.authors}"/></camod:highlight>
			</td>
			<td class="<c:out value="${tdClass}"/>" width="15%">
			<c:out value="${pub.jaxJNumber}"/>&nbsp;
				<c:if test="${not empty p.jaxJNumber}">												
					(<a target="_blank" href="http://www.informatics.jax.org/searches/accession_report.cgi?id=<c:out value="${p.jaxJNumber}"/>&Submit=Submit','mywin','left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()">MGI</a>)
				</c:if>
				<c:if test="${not empty p.jaxJNumber}">
					(<a target="_blank" href="http://tumor.informatics.jax.org/mtbwi/referenceDetails.do?accId=<c:out value="${p.jaxJNumber}"/>&Submit=Submit','mywin','left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()">MTB</a>)
				</c:if>
			</td>			
			<td class="<c:out value="${tdClass}"/>" width="30%">
				<camod:highlight><c:out value="${p.title}"/></camod:highlight>
			</td>
			<td class="<c:out value="${tdClass}"/>" width="10%">
				<camod:highlight><c:out value="${p.journal}"/></camod:highlight>
			</td>
			<td class="<c:out value="${tdClass}"/>" width="5%">
				<camod:highlight><c:out value="${p.year}"/></camod:highlight>
			</td>
			<td class="<c:out value="${tdClass}"/>" width="10%">
				<camod:highlight><c:out value="${p.volume}"/></camod:highlight>
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
		</table>
		</tr></td>
	</c:otherwise>
</c:choose >
<tr><td>&nbsp;</td></tr>
