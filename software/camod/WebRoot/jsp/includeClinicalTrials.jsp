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
			<td class="<c:out value="${tdClass}"/>" width="50%">
				<b><c:out value="${cpitem.title}"/></b><br/>
				<br/>
				<c:choose>			
					<c:when test="${not empty cpitem.PDQIdentifier}">
						Additional Info from PDQ: 
						<a target="_blank" href="http://www.cancer.gov/clinicaltrials/<c:out value="${cpitem.PDQIdentifier}"/>"><c:out value="${cpitem.PDQIdentifier}"/></a>
						<br/>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>			
			</td>
			<td valign="top" class="<c:out value="${tdClass}"/>End" width="50%">
				<!-- TODO PU Name -->
				<b>Lead Organization: </b><camod:highlight><c:out value="${cpitem.leadOrganizationName}"/></camod:highlight><br/>
				<b>PI: </b><c:out value="${cpitem.PIName}"/><br/>
				<b>Phase: </b><c:out value="${cpitem.phase}"/><br/>
				<b>Status of Trial: </b><c:out value="${cpitem.currentStatus}"/><br/>
			</td>
		</tr>
	</c:forEach>
	    <td colspan="2" align="right"><a href="#">Top</a></td>	
	<tr><td>&nbsp;</td></tr>
</c:if>