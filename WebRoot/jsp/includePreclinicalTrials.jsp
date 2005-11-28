<%
 /*
 * $Log: not supported by cvs2svn $
 * Revision 1.8  2005/11/28 13:52:48  georgeda
 * Defect #207, handle nulls for pages w/ uncontrolled vocab
 *
 * Revision 1.7  2005/11/17 21:15:10  georgeda
 * Defect #86.  Removed unneeded field.
 *
 *
 * $Id: includePreclinicalTrials.jsp,v 1.9 2005-11-28 16:31:48 pandyas Exp $
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
	<c:out value="${t.experiment}"/>
	</td>
</tr>
<tr>
	<td class="resultsBoxGrey" width="25%"><b>Dose</b></td>
	<td class="resultsBoxGreyEnd" width="75%">&nbsp;
	<c:out value="${t.treatment.dosage}"/>
	</td>
</tr>
<tr>
	<td class="resultsBoxWhite" width="25%"><b>Administration Route</b></td>
	<td class="resultsBoxWhiteEnd" width="75%">
		<c:choose>
			<c:when test="${empty t.treatment.administrativeRoute}">
				<c:out value="${t.treatment.adminRouteUnctrlVocab}"/>
			</c:when>
			<c:otherwise>
				<c:out value="${t.treatment.administrativeRoute}"/>
			</c:otherwise>
		</c:choose>&nbsp;
	</td>
</tr>
<tr>
	<td class="resultsBoxGrey" width="25%"><b>Gender</b></td>
	<td class="resultsBoxGreyEnd" width="75%">&nbsp;
	<c:out value="${t.treatment.sexDistribution.type}"/>
	</td>
</tr>
<tr>
	<td class="resultsBoxWhite" width="25%"><b>Age at Treatment</b></td>
	<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
	<c:out value="${t.treatment.ageAtTreatment}"/>
	</td>
</tr>
<tr>
	<td class="resultsBoxGrey" width="25%"><b>Results</b></td>
	<td class="resultsBoxGreyEnd" width="75%">&nbsp;
	<c:out value="${t.results}"/>
	</td>
</tr>
<tr>
	<td class="resultsBoxWhite" width="25%"><b>Toxicity Grade</b></td>
	<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
	<c:out value="${t.toxicityGrade}"/>
	</td>
</tr>			
<tr>
	<td class="resultsBoxGrey" width="25%"><b>Biomarker</b></td>
	<td class="resultsBoxGreyEnd" width="75%"><c:out value="${t.biomarker}"/>&nbsp;</td>
</tr>
<tr>
	<td class="resultsBoxWhite" width="25%"><b>Comment </b></td>
	<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
	<c:out value="${t.comments}"/>
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
			<td class="greySubTitleLeft" width="15%">First Author</td>
			<td class="greySubTitleLeft" width="30%">Title</td>
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
		</table>
		</tr></td>
	</c:otherwise>
</c:choose >
<tr><td>&nbsp;</td></tr>
