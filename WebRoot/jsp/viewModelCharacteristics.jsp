<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.domain.Histopathology" %>	
<%@ page import="gov.nih.nci.camod.Constants" %>
<%@ page import="java.util.List" %>

<bean:define id="mdl" name="animalmodel"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">

		<tr>
			<td class="formTitle" height="20" colspan="3">
				Model Characteristics - Model:
				<bean:write name="mdl" property="modelDescriptor"/>
			</td>
		</tr>
		<tr>
			<td class="GreyBox" width="20%"><b>Model Descriptor</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				<bean:write name="mdl" property="modelDescriptor"/>
			</td>			
		</tr>
		
		<tr>
			<td class="WhiteBox" width="20%"><b>Official Nomenclature</b></td>
			<td class="WhiteBoxRightEnd" width="80%">
				<c:set var="items" value="${mdl.distinctNomenclatureFromEngineeredGeneCollection}"/>
				<logic:notEmpty name="items">
				<ul>    
					<c:forEach var="item" items="${items}" varStatus="stat">
					<li> <c:out value="${item}" escapeXml="false"/> </li>
					</c:forEach>
				</ul>
				</logic:notEmpty>
				<logic:empty name="items">
				    <br/>
				</logic:empty>
			</td>
		</tr>

		<tr>
			<td class="GreyBox" width="20%"><b>Genotype</b></td>
			<td class="GreyBoxRightEnd" width="80%">
			    <c:set var="items" value="${mdl.distinctGenotypeFromEngineeredGeneCollection}"/>
			    <logic:notEmpty name="items">
				<ul>    
					<c:forEach var="item" items="${items}" varStatus="stat">
					<li> <c:out value="${item}"/> </li>
					</c:forEach>
				</ul>
				</logic:notEmpty>
				<logic:empty name="items">
				    <br/>
				</logic:empty>
			</td>
		</tr>

		<tr>
			<td class="WhiteBox" width="20%"><b>Species</b></td>
			<td class="WhiteBoxRightEnd" width="80%">
				<c:out value="${mdl.species.scientificName}"/>
			</td>
		</tr>
		
		<tr>
			<td class="GreyBox" width="20%"><b>Strain</b></td>
			<td class="GreyBoxRightEnd" width="80%">&nbsp;
				<c:out value="${mdl.species.ethnicityStrain}"/>
			</td>
		</tr>		
		
		<tr>
			<td class="WhiteBox" width="20%"><b>Experimental Design</b></td>
			<td class="WhiteBoxRightEnd" width="80%">&nbsp;
				<P>
					<bean:write name="mdl" property="experimentDesign"/>
				</P>			
			</td>
		</tr>		               

		<tr>
			<td class="GreyBox" width="20%"><b>Phenotype</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				<P>
				<c:out value="${mdl.phenotype.description}"/>
				</P>		
			</td>
		</tr>		

		<tr>
			<td class="GreyBox" width="20%"><b>Breeding Notes</b></td>
			<td class="GreyBoxRightEnd" width="80%">&nbsp;
				<P>
				<c:out value="${mdl.phenotype.breedingNotes}"/>
				</P>		
			</td>
		</tr>		

		<tr>
			<td class="WhiteBox" width="20%"><b>Sex Distribution of the Phenotype</b></td>
			<td class="WhiteBoxRightEnd" width="80%">&nbsp;
				<c:out value="${mdl.phenotype.sexDistribution.type}"/>
			</td>
		</tr>		               
        
		<tr>
			<td class="GreyBox" width="20%"><b>Submitted by</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				<a href="#">
					TBD
				</a>
			</td>
		</tr>

		<tr>
			<td class="WhiteBox" width="20%"><b>Principal Investigator Lab</b></td>
			<td class="WhiteBoxRightEnd" width="80%">
				<a href=""> TBD
				</a>
			</td>
		</tr>		               

		<c:if test="${not empty mdl.animalAvailabilityCollection}">
		<tr><td>&nbsp;</td></tr>
        <tr>
			<td class="formTitle" height="20" colspan="2">Model Availability</td>		
		</tr>
		<c:forEach var="av" items="${mdl.animalAvailabilityCollection}" varStatus="stat2">
			<c:choose>
				<c:when test = "${stat2.count % 2 == 0}">
					<c:set var="tdClass" value="resultsBoxWhite"/>
				</c:when>
				<c:otherwise>
					<c:set var="tdClass" value="resultsBoxGrey"/>
				</c:otherwise>
			</c:choose>
			<tr>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<b><c:out value="${av.name}"/>- Stock #:<c:out value="${av.stockNumber}"/></b>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="80%">
					<b>Strain:</b> TBD
				</td>
			</tr>
		</c:forEach>
		<tr><td>&nbsp;</td></tr>
		</c:if>
		<tr>
			<td class="WhiteBox" width="100%" colspan="2"><a href='javascript: rs("commentWin","submitComment.jsp",415,250);'><IMG src="images/comment.gif" border=0 align=middle> <b>Place your comment here</b></a></td>
		</tr>

	</TABLE>
	
</td></tr>
</TABLE>

<%@ include file="/jsp/footer.jsp" %>