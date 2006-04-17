<%

/**
 * 
 * $Id: includeAgentDetails.jsp,v 1.7 2006-04-17 19:08:19 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2005/11/29 21:47:41  pandyas
 * Defect #100: Modified formatting of clinical trials and agent tables
 *
 * Revision 1.5  2005/11/29 20:26:40  pandyas
 * Defect #138: Added hyperlink to CAS field on Therapy search screen.  The two others mentioned in this defect were already linked.
 *
 * Revision 1.4  2005/11/10 16:09:11  schroedn
 *  Defect #17 fix schroedln (11/9/05)
 * Added link for chemical structure
 *
 *
 */

%>

<tr>
	<td class="formTitleBlue" height="20" colspan="2">
		Compound / Drug - <c:out value="${agt.name}"/></td>				
</tr>
<tr>
	<td class="resultsBoxWhite" width="25%"><b>NSC Number</b></td>
	<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
	<c:out value="${agt.nscNumber}"/>
	<c:if test="${not empty agt.nscNumber}">
	(<a href="#" onClick="myRef = window.open('http://dtp.nci.nih.gov/dtpstandard/servlet/ChemData?queryHOLD=&searchtype=NSC&chemnameboolean=and&outputformat=html&searchlist=<c:out value='${agt.nscNumber}'/>&Submit=Submit','mywin','left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()">Chemical Structure</a>)
	</c:if>
	</td>
<tr>
<tr>
	<td class="resultsBoxGrey" width="25%"><b>CAS Number</b></td>
	<td class="resultsBoxGreyEnd" width="75%">&nbsp;
	<c:out value="${agt.casNumber}"/>
	<c:if test="${not empty agt.casNumber}">
	(<a href="#" onClick="myRef = window.open('http://dtp.nci.nih.gov/dtpstandard/servlet/ChemData?queryHOLD=&searchtype=CAS&chemnameboolean=and&outputformat=html&searchlist=<c:out value='${agt.casNumber}'/>&Submit=Submit','mywin','left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()">Chemical Structure</a>)
	</c:if>		
	</td>
<tr>				
<tr>
	<td class="resultsBoxWhite" width="25%"><b>Chemical Class</b></td>
	<td class="resultsBoxWhiteEnd" width="75%">
	<ul>
		<c:forEach var="item" items="${agt.chemicalClassCollection}" varStatus="stat">
		<li> <c:out value="${item.name}"/> </li>
		</c:forEach>
	</ul>&nbsp;
	</td>
<tr>
<tr>
	<td class="resultsBoxGrey" width="25%"><b>Biological Process</b></td>
	<td class="resultsBoxGreyEnd" width="75%">
	<ul>
		<c:forEach var="item" items="${agt.biologicalProcessCollection}" varStatus="stat">
		<li> <c:out value="${item.name}"/> </li>
		</c:forEach>
	</ul>&nbsp;
	</td>
<tr>			
<tr>
	<td class="resultsBoxWhite" width="25%"><b>Target</b></td>
	<td class="resultsBoxWhiteEnd" width="75%">
	<ul>
		<c:forEach var="item" items="${agt.agentTargetCollection}" varStatus="stat">
		<li> <c:out value="${item.name}"/> </li>
		</c:forEach>
	</ul>&nbsp;
	</td>
<tr>
<tr><td>&nbsp;</td></tr>
