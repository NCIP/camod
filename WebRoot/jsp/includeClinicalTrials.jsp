<!-- clinical trials protocols -->
<c:set var="cp" value="${clinProtocols[nscnum]}"/>
<tr>
	<td class="formTitleBlue" height="20" colspan="2">
	<b>Current Clincal Trials in  <c:out value="${agt.name}"/></b>
	</td>
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
