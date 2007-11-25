<%

/**
 * 
 * $Id: includeCaelmirData.jsp,v 1.1 2007-11-25 23:31:01 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

%>

<!-- caELMIR study data-->
<tr>
	<td class="formTitleBlue" height="20" colspan="2">
		Study Summary</td>				
</tr>

<c:set var="ced" value="${caelmirStudyDataColl}"/>
<!-- c:if test="${not empty ced}" -->
	<c:forEach var="stg" items="${ced}" varStatus="stat2">
		<tr>
			<td class="resultsBoxWhite" width="25%"><b>Name</b></td>
			<td class="resultsBoxWhiteEnd" width="75%">&nbsp;
			<!--c:out value="${agt.nscNumber}"/>
			<c:if test="${not empty agt.nscNumber}">
			(<a href="#" onClick="myRef = window.open('http://dtp.nci.nih.gov/dtpstandard/servlet/ChemData?queryHOLD=&searchtype=NSC&chemnameboolean=and&outputformat=html&searchlist=<c:out value='${agt.nscNumber}'/>&Submit=Submit','mywin','left=20,top=20,width=700,height=700,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()">Chemical Structure</a>)
			</c:if-->
			</td>
		<tr>
		<tr>
			<td class="resultsBoxGrey" width="25%"><b>Hypothesis</b></td>
			<td class="resultsBoxGreyEnd" width="75%">&nbsp;
				<!--c:out value="${agt.casNumber}"/-->
			</td>
		<tr>				
		<tr>
			<td class="resultsBoxWhite" width="25%"><b>Description</b></td>
			<td class="resultsBoxWhiteEnd" width="75%">
				<!--c:out value="${item.name}"/-->
			</td>
		<tr>
		<tr>
			<td class="resultsBoxGrey" width="25%"><b>Principal Investigator</b></td>
			<td class="resultsBoxGreyEnd" width="75%">
				<!--c:out value="${cpitem.PIName}"/>&nbsp;<c:out value="${cpitem.phase}"/><br/>
				( <c:out value="${cpitem.currentStatus}"/>)&nbsp;<c:out value="${cpitem.PIName}"/--><br/>
			</td>
		<tr>			

		<tr><td>&nbsp;</td></tr>
	</c:forEach>
<!-- /c:if -->