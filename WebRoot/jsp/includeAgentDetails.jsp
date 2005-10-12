<tr>
	<td class="formTitleBlue" height="20" colspan="2">
		Compound / Drug - <c:out value="${agt.name}"/></td>				
</tr>
<tr>
	<td class="resultsBoxWhite" width="25%"><b>NSC Number</b></td>
	<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
	<c:out value="${agt.nscNumber}"/>
	<c:if test="${not empty agt.nscNumber}">
	(<a href="#">Chemical Structure</a>)
	</c:if>
	</td>
<tr>
<tr>
	<td class="resultsBoxGrey" width="25%"><b>CAS Number</b></td>
	<td class="resultsBoxGreyEnd" width="75%">&nbsp;
	<c:out value="${agt.casNumber}"/>
	</td>
<tr>				
<tr>
	<td class="resultsBoxWhite" width="25%"><b>Chemical Class</b></td>
	<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
	<ul>
		<c:forEach var="item" items="${agt.chemicalClassCollection}" varStatus="stat">
		<li> <c:out value="${item.chemicalClassName}"/> </li>
		</c:forEach>
	</ul>
	</td>
<tr>
<tr>
	<td class="resultsBoxGrey" width="25%"><b>Biological Process</b></td>
	<td class="resultsBoxGreyEnd" width="75%">&nbsp;
	<ul>
		<c:forEach var="item" items="${agt.biologicalProcessCollection}" varStatus="stat">
		<li> <c:out value="${item.processName}"/> </li>
		</c:forEach>
	</ul>
	</td>
<tr>			
<tr>
	<td class="resultsBoxWhite" width="25%"><b>Target</b></td>
	<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
	<ul>
		<c:forEach var="item" items="${agt.agentTargetCollection}" varStatus="stat">
		<li> <c:out value="${item.targetName}"/> </li>
		</c:forEach>
	</ul>
	</td>
<tr>
<tr><td>&nbsp;</td></tr>
