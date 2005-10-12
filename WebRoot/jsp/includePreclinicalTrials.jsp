<tr>
	<td class="formTitleBlue" height="20" colspan="2">
		Summary of the pre-clinical study in <c:out value="${agt.name}"/>
	</td>
</tr>	
<tr>
	<td class="resultsBoxWhite" width="25%"><b>Experiment \ Treatment Regimen</b></td>
	<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
	<c:out value="${t.treatment.regimen}"/>
	</td>
</tr>
<tr>
	<td class="resultsBoxGrey" width="25%"><b>Experiment</b></td>
	<td class="resultsBoxGreyEnd" width="75%">&nbsp;
	<c:out value="${t.experiment}"/>
	</td>
</tr>			
<tr>
	<td class="resultsBoxWhite" width="25%"><b>Results</b></td>
	<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
	<c:out value="${t.results}"/>
	</td>
</tr>
<tr>
	<td class="resultsBoxGrey" width="25%"><b>Toxicity Grade</b></td>
	<td class="resultsBoxGreyEnd" width="75%">&nbsp;
	<c:out value="${t.toxicityGrade}"/>
	</td>
</tr>			
<tr>
	<td class="resultsBoxWhite" width="25%"><b>Dose</b></td>
	<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
	<c:out value="${t.treatment.dosage}"/>
	</td>
</tr>
<tr>
	<td class="resultsBoxGrey" width="25%"><b>Age at treatment</b></td>
	<td class="resultsBoxGreyEnd" width="75%">&nbsp;
	<c:out value="${t.treatment.ageAtTreatment}"/>
	</td>
</tr>			
<tr>
	<td class="resultsBoxWhite" width="25%"><b>Gender</b></td>
	<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
	<c:out value="${t.treatment.sexDistribution.type}"/>
	</td>
</tr>
<tr>
	<td class="resultsBoxGrey" width="25%"><b>Administration Route</b></td>
	<td class="resultsBoxGreyEnd" width="75%">&nbsp;
	<c:out value="${t.treatment.administrativeRoute}"/>
	</td>
</tr>			
<tr>
	<td class="resultsBoxGrey" width="25%"><b>Biomarker</b></td>
	<td class="resultsBoxGreyEnd" width="75%"><c:out value="${t.biomarker}"/>&nbsp;</td>
</tr>
<tr>
	<td class="resultsBoxWhite" width="25%"><b>Comments</b></td>
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
				<a href="">
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
