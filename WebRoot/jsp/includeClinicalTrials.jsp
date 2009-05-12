<!-- clinical trials protocols -->
<c:set var="cp" value="${clinProtocols[nscnum]}"/>

<c:if test="${not empty cp && foundClinicalData == 0}">
	<c:set var="foundClinicalData" value="1"/>
	<tr>
		<td class="formTitleBlue" height="20" colspan="4">
			Current Clinical Trials for <c:out value="${agt.name}"/>
		</td>
	</tr>	
	
	<c:forEach var="cpitem" items="${cp}" varStatus="stat2">
		<tr>
			<c:choose>
				<c:when test = "${stat2.count % 2 == 0}">
					<c:set var="tdClass" value="resultsBoxWhite"/>
				</c:when>
				<c:otherwise>
					<c:set var="tdClass" value="resultsBoxGrey"/>
				</c:otherwise>
			</c:choose>
			<td class="<c:out value="${tdClass}"/>" width="25%">
				Title:<c:out value="${cpitem.title}"/><br/>
				<c:choose>
					<c:when test="${empty cpitem.PDQIdentifier}">
						PDQId:<c:out value="${cpitem.PDQIdentifier}"/><br/>
						<a target="_blank" href="http://www.cancer.gov/clinicltrials/<c:out value="${cpitem.PDQIdentifier}"/>"></a>
						<br/>
					</c:when>
					<c:otherwise>
					PDQId: blank
					</c:otherwise>
				</c:choose>			
			</td>
			<td class="<c:out value="${tdClass}"/>End" width="75%">
				<!-- TODO PU Name -->
				Lead Organization:<camod:highlight><c:out value="${cpitem.leadOrganizationName}"/></camod:highlight><br/>
				PI:<c:out value="${cpitem.PIName}"/><br/>
				Phase: <c:out value="${cpitem.phase}"/><br/>
				Status of Trial: <c:out value="${cpitem.currentStatus}"/><br/>
				PDQId:<c:out value="${cpitem.PDQIdentifier}"/><br/>

			</td>
		</tr>
	</c:forEach>
	    <td colspan="2" align="right"><a href="#">Top</a></td>	
	<tr><td>&nbsp;</td></tr>
</c:if>