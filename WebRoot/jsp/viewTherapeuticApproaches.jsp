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
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	

			<tr>
				<td class="formTitle" height="20" colspan="9">
				Therapeutic Approaches - Model:
				<c:out value="${mdl.modelDescriptor}"/></td>				
			</tr>
			<tr>
				<td class="resultsBoxWhiteEnd" height="20" colspan="9">
					<c:forEach var="t" items="${mdl.therapyCollection}" 
					       varStatus="stat">
					       <c:out value="${t.agent.name}"/><br/>
					</c:forEach>
				</td>
			</tr>	
			</TABLE>
			<br>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	

			<c:forEach var="t" items="${mdl.therapyCollection}" 
					       varStatus="stat">
			<tr>
				<td class="formTitleBlue" height="20" colspan="2">
					Compound / Drug - <c:out value="${t.agent.name}"/></td>				
			</tr>	
				
            <tr>
				<td class="resultsBoxWhite" width="25%"><b>NSC Number</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
				<c:out value="${t.agent.nscNumber}"/>
				(<a href="#">Chemical Structure</a>)
				</td>
			<tr>
            <tr>
				<td class="resultsBoxGrey" width="25%"><b>CAS Number</b></td>
				<td class="resultsBoxGreyEnd" width="75%">&nbsp;
				<c:out value="${t.agent.casNumber}"/>
				</td>
			<tr>				
            <tr>
				<td class="resultsBoxWhite" width="25%"><b>Chemical Class</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
				<ul>
					<c:forEach var="item" items="${t.agent.chemicalClassCollection}" varStatus="stat">
					<li> <c:out value="${item.chemicalClassName}"/> </li>
					</c:forEach>
				</ul>
				</td>
			<tr>
			
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Biological Process</b></td>
				<td class="resultsBoxGreyEnd" width="75%">&nbsp;
				<ul>
					<c:forEach var="item" items="${t.agent.biologicalProcessCollection}" varStatus="stat">
					<li> <c:out value="${item.processName}"/> </li>
					</c:forEach>
				</ul>
				</td>
			<tr>			

			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Target</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
				<ul>
					<c:forEach var="item" items="${t.agent.agentTargetCollection}" varStatus="stat">
					<li> <c:out value="${item.targetName}"/> </li>
					</c:forEach>
				</ul>
				</td>
			<tr>
	
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Toxicity Grade</b></td>
				<td class="resultsBoxGreyEnd" width="75%">&nbsp;
				<c:out value="${t.toxicityGrade}"/>
				</td>
			<tr>			

			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Dose</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
				<c:out value="${t.treatment.dosage}"/>
				</td>
			<tr>
			<tr>
				<td class="resultsBoxGrey" width="25%"><i>Units of Measure</i></td>
				<td class="resultsBoxGreyEnd" width="75%">TBD</td>
			<tr>			

			<tr>
				<td class="resultsBoxWhite" width="25%"><i>BioMarker</i></td>
				<td class="resultsBoxWhiteEnd" width="75%">TBD</td>
			<tr>
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Mouse Age</b></td>
				<td class="resultsBoxGreyEnd" width="75%">&nbsp;
				<c:out value="${t.treatment.ageAtTreatment}"/>
				</td>
			<tr>			
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Gender</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
				<c:out value="${t.treatment.sexDistribution.type}"/>
				</td>
			<tr>
            <tr>
				<td class="resultsBoxGrey" width="25%"><b>Administration Route</b></td>
				<td class="resultsBoxGreyEnd" width="75%">&nbsp;
				<c:out value="${t.treatment.administrativeRoute}"/>
				</td>
			<tr>			

			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Experiment \ Treatment Regiment</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
				<c:out value="${t.treatment.regimen}"/>
				</td>
			<tr>
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Experiment</b></td>
				<td class="resultsBoxGreyEnd" width="75%">&nbsp;
				<c:out value="${t.experiment}"/>
				</td>
			<tr>			
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Results</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
				<c:out value="${t.results}"/>
				</td>
			<tr>
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Additional Information</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
				<c:out value="${t.comments}"/>
				</td>
			<tr>

			<tr>
				<td class="greySubTitleLeft" colspan=2><b>Publications:</b></td>
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

			<!-- clinical trials protocols -->
			<c:set var="nscnum" value="${t.agent.nscNumber}"/>
			<c:set var="cp" value="${clinProtocols[nscnum]}"/>
			<tr>
				<td class="greySubTitleLeft" colspan=2><b>Current Clincal Trials</b></td>
			</tr>
			<c:choose>
				<c:when test="${empty cp}">
					<tr>
						<td class="resultsBoxWhiteEnd" colspan=2><b>No Current clinical Trials</b></td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="cpitem" items="${cp}" varStatus="stat2">
					<tr>
						<td class="resultsBoxWhite" width="25%">
						<c:out value="${cpitem.leadOrganizationName}"/>
						</td>
						<td class="resultsBoxWhiteEnd" width="75%">
							<!-- TODO PU Name -->
							PI:<c:out value="${cpitem.PIName}"/><br/>
							Phase: <c:out value="${cpitem.phase}"/><br/>
							Status of Trial: <c:out value="${cpitem.currentStatus}"/><br/>
						</td>
					</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<tr><td>&nbsp;</td></tr>
			
			<!-- yeast data -->
			<c:set var="yst" value="${yeastData[nscnum]}"/>
			<c:forEach var="stg" items="${yst}" varStatus="stat2">
	 			<c:choose>
					<c:when test="${stg.strainCount <= 0}">
					</c:when>
					<c:otherwise>
					  	<tr><td colspan="2">
						<table summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
							<tr>
								<td class="formTitleBlue" colspan="11" align="center">
									Publicly available data from the NCI Yeast Anticancer Drug Screen
									<br>Stage <c:out value="${stat2.count - 1}"/> - Dose Response
								</td>
							</tr>
							<tr>
								<td class="greySubTitle" colspan="11">
								<a href="javascript:expdesign2()"><br>Experimental Design - Stage <c:out value="${stat2.count}"/> Testing</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:strains0()">
									Yeast Strains used for Stage <c:out value="${stat2.count - 1}"/> Experiments
								</a>
								<br>
							 	</td>
							</tr>
							<tr>
								<td rowspan=2 class="greySubTitleLeft">Strain  /  Dosage <br>A= AveIng, D=DiffInh</td>
								<c:forEach var="dosage" items="${stg.dosages}" varStatus="stat3">
									<td colspan=2 class="greySubTitleLeft"><c:out value="${dosage}"/><font face="symbol">m</font>M</td>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach var="dosage" items="${stg.dosages}" varStatus="stat3">
									<td class="greySubTitleLeft">A</td>
									<td class="greySubTitleLeft">D</td>
								</c:forEach>
							</tr>
							<c:forEach var="strain" items="${stg.strains}" varStatus="stat3">
							<tr>
								<td class="greySubTitleLeft"><c:out value="${strain}"/></td>
								<c:forEach var="dosage" items="${stg.results[strain]}" varStatus="stat4">
									<td class="resultsBoxWhite"><c:out value="${dosage.aveInh}"/></td>
									<td class="resultsBoxWhite"><c:out value="${dosage.diffInh}"/></td>
								</c:forEach>
							</tr>
							</c:forEach>
						</table>
						</td></tr>
						<tr><td>&nbsp;</td></tr>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<!-- end yeast data -->

			<!-- invivo / Xenograft data-->
			<c:set var="invivoColl" value="${invivoData[nscnum]}"/>
 			<c:choose>
				<c:when test="${not empty invivoColl}">
				  	<tr><td colspan="2">
					<table summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
						<tr>
							<td class="formTitleBlue" colspan="3" align="center">
								In Vivo Screening Data Summary: <br/>
								<b>NSC: &nbsp;&nbsp;<c:out value="${t.agent.nscNumber}"/><br/>
								CAS: &nbsp;&nbsp; <c:out value="${t.agent.casNumber}"/><br/>
								Hydrazinecarbothioamide, 2-[[5-(1-oxopropoxy)-2-pyridinyl]methylene]
								</b>
							</td>
						</tr>
						<tr>
							<td class="greySubTitle" colspan="3">For approximately thirty years, the NCI used <em>in vivo </em>animal tumor models to screen compounds for potential antitumor activity.
							</td>
						</tr>
						<c:forEach var="ivd" items="${invivoColl}" varStatus="stat2">
							<c:choose>
								<c:when test = "${stat2.count % 2 == 0}">
									<c:set var="tdClass" value="resultsBoxWhite"/>
								</c:when>
								<c:otherwise>
									<c:set var="tdClass" value="resultsBoxGrey"/>
								</c:otherwise>
							</c:choose>
							<tr>
								<td align="right" class="<c:out value="${tdClass}"/>"><c:out value="${stat2.count}"/></td>
								<td class="<c:out value="${tdClass}"/>">
								<c:out value="${ivd[1]}"/> in <c:out value="${ivd[2]}"/>
								</td>
								<td align="right" class="<c:out value="${tdClass}End"/>"> &nbsp;&nbsp;
								<A href="detail3.htm?<c:out value="${ivd[0]}"/>" target="_blank">
								<c:out value="${ivd[3]}"/></a>
								</td>
						  	</tr>
					  	</c:forEach>
					</table></td></tr>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			<!-- end invivo /xenograft data-->

            </c:forEach>
			<tr>
				<td class="WhiteBox" width="100%" colspan="2"> <a href='javascript: rs("commentWin","submitComment.jsp",415,250);'><IMG src="images/comment.gif" border=0 align=middle> <b>Place your comment here</b></a></td>
			</tr>
			
			</TABLE>
		</td></tr></TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>