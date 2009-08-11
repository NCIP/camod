
<!-- yeast data -->
<c:set var="yst" value="${yeastData[agentId]}"/>
<c:if test="${not empty yst}">
	<c:forEach var="stg" items="${yst}" varStatus="stat2">
		<c:if test="${stg.strainCount <= 0}">
		    <table summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="greySubTitleLeftEnd" colspan=4>
				<b>Data unavailable for NCI Yeast Anticancer Drug Screen </b>
				<br>Stage <c:out value="${stat2.count - 1}"/>
				</td>
			</tr>
		</c:if>
	    <c:if test="${stg.strainCount > 0}">
		    <c:set var="foundYstData" value="1"/>
		  	<tr><td colspan="2">
			<table summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
				<tr>
					<td class="formTitleBlue" colspan="11" align="center">
						Publicly available data from the NCI Yeast Anticancer Drug Screen
						<br>
						Stage
						<c:if test="${stat2.count-1=='0'}">
							<c:out value="${stat2.count - 1}"/> - Crude Sensitivity Screen
						</c:if>
						<c:if test="${stat2.count-1=='1'}">
							<c:out value="${stat2.count - 1}"/> - Crude Selectivity Screen
						</c:if>
						<c:if test="${stat2.count-1=='2'}">
							<c:out value="${stat2.count - 1}"/> - Dose Response
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="greySubTitle" colspan="11">
						<br>
							<c:if test="${stat2.count-1=='0'}">
								<html:link action="expDesignStage0">Experimental Design - Stage <c:out value="${stat2.count - 1}"/> Testing
								</html:link>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:link action="yeastStrainsStage01">Yeast Strains used for Stage <c:out value="${stat2.count - 1}"/> Experiments
								</html:link>								
							</c:if>
							<c:if test="${stat2.count-1=='1'}">
								<html:link action="expDesignStage1">Experimental Design - Stage <c:out value="${stat2.count - 1}"/> Testing
								</html:link>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:link action="yeastStrainsStage01">Yeast Strains used for Stage <c:out value="${stat2.count - 1}"/> Experiments
								</html:link>								
							</c:if>
							<c:if test="${stat2.count-1=='2'}">
								<html:link action="expDesignStage2">Experimental Design - Stage <c:out value="${stat2.count - 1}"/> Testing
								</html:link>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:link action="yeastStrainsStage2">Yeast Strains used for Stage <c:out value="${stat2.count - 1}"/> Experiments
								</html:link>								
							</c:if>						
						<br>
				 	</td>
				</tr>
				<tr>
					<td rowspan=2 class="greySubTitleLeft">Strain  /  Dosage <br>A= AveInh, D=DiffInh</td>
					<c:forEach var="dosage" items="${stg.dosages}" varStatus="stat3">
					    <c:set var="roundedDosage"><fmt:formatNumber value="${dosage}" minFractionDigits="2" maxFractionDigits="2"/></c:set>
						<td colspan=2 class="greySubTitleLeftEnd"><p align="right"><c:out value="${roundedDosage}"/><font face="symbol">m</font>M</p></td>
					</c:forEach>
				</tr>
				<tr>
					<c:forEach var="dosage" items="${stg.dosages}" varStatus="stat3">
						<td class="greySubTitleAlignRightNoTop">A</td>
						<td class="greySubTitleAlignRightEndNoTop">D</td>
					</c:forEach>
				</tr>
				<c:forEach var="strain" items="${stg.strains}" varStatus="stat3">
				<tr>
					<td class="greySubTitleLeftNoTop"><c:out value="${strain}"/></td>
					<c:forEach var="dosage" items="${stg.results[strain]}" varStatus="stat4">
					    <c:set var="roundedAveDosage"><fmt:formatNumber value="${100*dosage.aveInh}" minFractionDigits="2" maxFractionDigits="2"/></c:set>
					    <c:set var="roundedDiffDosage"><fmt:formatNumber value="${100*dosage.diffInh}" minFractionDigits="2" maxFractionDigits="2"/></c:set>
					    
						<td class="resultsBoxWhiteAlignRight"><c:out value="${roundedAveDosage}"/></td>
						<td class="resultsBoxWhiteEndAlignRight"><c:out value="${roundedDiffDosage}"/></td>
					</c:forEach>
				</tr>
				</c:forEach>
			</table>
			</td></tr>
		</c:if>
		<tr><td>&nbsp;</td></tr>
	</c:forEach>
</c:if>
<!-- end yeast data -->

