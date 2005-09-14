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
					<c:forEach var="p" items="${t.publicationCollection}" varStatus="stat2">
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
            </c:forEach>

		  	<tr><td colspan="2">
				<table summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
					<tr>
						<td class="formTitleBlue" colspan="11" align="center">
							Publicly available data from the NCI Yeast Anticancer Drug Screen
							<br>Stage 2 - Dose Response
						</td>
					</tr>
					<tr>
						<td class="greySubTitle" colspan="11">
						<a href="javascript:expdesign2()"><br>Experimental Design - Stage 2 Testing</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:strains0()">
							Yeast Strains used for Stage 2 Experiments
						</a>
						<br>
					 	</td>
					</tr>
					<tr>
						<td rowspan=2 class="greySubTitleLeft">Strain  /  Dosage <br>A= AveIng, D=DiffInh</td>
						<td colspan=2 class="greySubTitleLeft">1.2<font face="symbol">m</font>M</td>
						<td colspan=2 class="greySubTitleLeft">3.7 <font face="symbol">m</font>M</td>
						<td colspan=2 class="greySubTitleLeft">11 <font face="symbol">m</font>M</td>
						<td colspan=2 class="greySubTitleLeft">33 <font face="symbol">m</font>M</td>
						<td colspan=2 class="greySubTitle">100 <font face="symbol">m</font>M</td>
					</tr>
					<tr>
						<td class="greySubTitleLeft">A</td>
						<td class="greySubTitleLeft">D</td>
						<td class="greySubTitleLeft">A</td>
						<td class="greySubTitleLeft">D</td>
						<td class="greySubTitleLeft">A</td>
						<td class="greySubTitleLeft">D</td>
						<td class="greySubTitleLeft">A</td>
						<td class="greySubTitleLeft">D</td>
						<td class="greySubTitleLeft">A</td>
						<td class="greySubTitle">D</td>
					</tr>
					<tr>
						<td class="greySubTitleLeft">wt1</td>
						<td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td>
						<td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td>
						<td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td>
						<td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td>
						<td class="resultsBoxWhite">1</td> <td class="resultsBoxWhiteEnd">1</td>
					</tr>
					<tr><td class="greySubTitleLeft">rad18</td>
						<td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhiteEnd">1</td></tr>
					<tr><td class="greySubTitleLeft">rad14</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhiteEnd">1</td></tr>
				
					<tr><td class="greySubTitleLeft">rad50</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhiteEnd">1</td></tr>
					<tr><td class="greySubTitleLeft">rad52</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhiteEnd">1</td></tr>
				
					<tr><td class="greySubTitleLeft">bub3</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhiteEnd">1</td></tr>
					<tr><td class="greySubTitleLeft">CLN2oe</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhiteEnd">1</td></tr>
					<tr><td class="greySubTitleLeft">mec2</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhiteEnd">1</td></tr>
				
					<tr><td class="greySubTitleLeft">wt2</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhiteEnd">1</td></tr>
					<tr><td class="greySubTitleLeft">mgt1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhiteEnd">1</td></tr>
					<tr><td class="greySubTitleLeft">mlh1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhiteEnd">1</td></tr>
				
					<tr><td class="greySubTitleLeft">sgs1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhiteEnd">1</td></tr>
					<tr><td class="greySubTitleLeft">rad50 EPP+</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhite">1</td><td class="resultsBoxWhite">1</td> <td class="resultsBoxWhiteEnd">1</td></tr>
				</table>
				</td></tr>
			<tr><td>&nbsp;</td></tr>

			<tr>
				<td class="WhiteBox" width="100%" colspan="2"> <a href='javascript: rs("commentWin","submitComment.jsp",415,250);'><IMG src="images/comment.gif" border=0 align=middle> <b>Place your comment here</b></a></td>
			</tr>
			
			</TABLE>
		</td></tr></TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>