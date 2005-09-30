<!-- invivo / Xenograft data-->
<c:set var="invivoColl" value="${invivoData[nscnum]}"/>
	<c:choose>
	<c:when test="${not empty invivoColl}">
	  	<tr><td colspan="2">
		<table summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" colspan="3" align="center">
					In Vivo Screening Data Summary: <br/>
					<b>NSC: &nbsp;&nbsp;<c:out value="${agt.nscNumber}"/><br/>
					CAS: &nbsp;&nbsp; <c:out value="${agt.casNumber}"/><br/>
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
		<tr>
			<td class="greySubTitleLeft" colspan=2>
			<b>No Invivo Drug Screening Data found </b>
			</td>
		</tr>
	</c:otherwise>
</c:choose>
<!-- end invivo /xenograft data-->
