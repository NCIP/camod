<%
 /*
  *   $Id: viewModelCharacteristics.jsp,v 1.19 2005-11-16 15:31:35 georgeda Exp $
  *   
  *   $Log: not supported by cvs2svn $
  */
%>
<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.domain.Histopathology" %>	
<%@ page import="java.util.List" %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<bean:define id="mdl" name="animalmodel"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">

		<tr>
			<td class="formTitle" height="20" colspan="3">
				Model Characteristics - Model:
				<c:out value="${mdl.modelDescriptor}" escapeXml="false"/>
			</td>
		</tr>
		<tr>
			<td class="GreyBox" width="20%"><b>Model Descriptor</b></td>
			<td class="GreyBoxRightEnd" width="80%">
			    <c:out value="${mdl.modelDescriptor}" escapeXml="false"/>
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
			<td class="GreyBoxRightEnd" width="80%">
				<c:out value="${mdl.species.ethnicityStrain}"/>&nbsp;
			</td>
		</tr>		
		
		<tr>
			<td class="WhiteBox" width="20%"><b>Experimental Design</b></td>
			<td class="WhiteBoxRightEnd" width="80%">
				<P>
				    <c:out value="${mdl.experimentDesign}" escapeXml="false" />&nbsp;
				</P>			
			</td>
		</tr>		               

		<tr>
			<td class="GreyBox" width="20%"><b>Phenotype</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				<P>
				<c:out value="${mdl.phenotype.description}" escapeXml="false"/>
				</P>		
			</td>
		</tr>		
		<tr>
			<td class="WhiteBox" width="20%"><b>Website for add. info</b></td>
			<td class="WhiteBoxRightEnd" width="80%">
				<P>
				<a href="<c:out value="${mdl.url}"/>" ><c:out value="${mdl.url}"/></a>&nbsp;
				</P>
			</td>
		</tr>		
		<tr>
			<td class="GreyBox" width="20%"><b>Breeding Notes</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				<P>
				<c:out value="${mdl.phenotype.breedingNotes}"/>&nbsp;
				</P>		
			</td>
		</tr>		

		<tr>
			<td class="WhiteBox" width="20%"><b>Sex Distribution of the Phenotype</b></td>
			<td class="WhiteBoxRightEnd" width="80%">
				<c:out value="${mdl.phenotype.sexDistribution.type}"/>&nbsp;
			</td>
		</tr>		               
        
		<tr>
			<td class="GreyBox" width="20%"><b>Submitted by</b></td>
			<td class="GreyBoxRightEnd" width="80%">
			    <c:if test="${not empty mdl.submitter.emailAddress}">
				    <a href="mailto:<c:out value="${mdl.submitter.emailAddress}"/>">
				</c:if>
				<c:out value="${mdl.submitter.displayName}"/>
				<c:if test="${not empty mdl.submitter.emailAddress}">
				    </a>
				</c:if>
			</td>
		</tr>
                  
		<tr>
			<td class="WhiteBox" width="20%"><b>Principal Investigator / Lab</b></td>
			<td class="WhiteBoxRightEnd" width="80%">
				<c:if test="${not empty mdl.principalInvestigator.emailAddress}">
				    <a href="mailto:<c:out value="${mdl.principalInvestigator.emailAddress}"/>">
				</c:if>
				<c:out value="${mdl.principalInvestigator.displayName}"/>
				<c:if test="${mdl.principalInvestigator.emailAddress}">
				    </a>
				</c:if>
			</td>
		</tr>		               

		<c:if test="${not empty mdl.animalAvailabilityCollection}">
		<tr><td>&nbsp;</td></tr>
        <tr>
			<td class="formTitle" height="20" colspan="2">Model Availability: This model is available from</td>
		</tr>
		<tr><td colspan="2"><table cellpadding="5" cellspacing="0" border="0" width="100%">
		<tr>
			<td class="formTitleBlue" width="30%">Strain</td>				
			<td class="formTitleBlue" width="45%">Distributor</td>
			<td class="formTitleBlue" width="25%">Stock number</td>
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
				<td class="<c:out value="${tdClass}"/>" width="30%">
				<c:out value="${av.name}"/>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="45%">
					<c:set var="dist" value="${av.animalDistributorCollection[0]}"/>
					<c:choose>
						<c:when test = "${dist.id == 1}">
							<c:out value="${dist.name}"/>
						</c:when>
						<c:when test = "${dist.id == 2}">
							<a target="_distributor" href="http://jaxmice.jax.org/jaxmice-cgi/jaxmicedb.cgi?objtype=pricedetail&stock=<c:out value="${av.stockNumber}"/>">
							<c:out value="${dist.name}"/>
							</a>
						</c:when>
						<c:when test = "${dist.id == 3}">
							<a target="_distributor" href="http://mouse.ncifcrf.gov/available_details.asp?ID=<c:out value="${av.stockNumber}"/>">
							<c:out value="${dist.name}"/>
							</a>
						</c:when>
						<c:when test = "${dist.id == 4}">
							<c:out value="${dist.name}"/>
						</c:when>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="25%">
					<c:choose>
						<c:when test = "${dist.id != 1}">
							<c:out value="${av.stockNumber}"/>
						</c:when>
					</c:choose>&nbsp;
				</td>
			</tr>
		</c:forEach>
		</table></td></tr>

		</c:if>
		
		<tr><td>&nbsp;</td></tr>
		<% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.MODEL_CHARACTERISTICS); %>
		<%@ include file="/jsp/includeComments.jsp" %>
	</TABLE>
	
</td></tr>
</TABLE>

<%@ include file="/jsp/footer.jsp" %>