<!-- yeast data -->
<c:set var="yst" value="${yeastData[nscnum]}"/>
<c:forEach var="stg" items="${yst}" varStatus="stat2">
		<c:choose>
		<c:when test="${stg.strainCount <= 0}">
		<tr>
			<td class="greySubTitleLeft" colspan=2>
			<b>Data unavailable for NCI Yeast Anticancer Drug Screen </b>
			<br>Stage <c:out value="${stat2.count - 1}"/>
			</td>
		</tr>
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
						<td class="resultsBoxWhite"><c:out value="${100*dosage.aveInh}"/></td>
						<td class="resultsBoxWhite"><c:out value="${100*dosage.diffInh}"/></td>
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

