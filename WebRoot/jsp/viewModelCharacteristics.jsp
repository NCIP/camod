<%
 /*
  *   $Id: viewModelCharacteristics.jsp,v 1.22 2005-11-21 20:25:22 georgeda Exp $
  *   
  *   $Log: not supported by cvs2svn $
  *   Revision 1.21  2005/11/18 20:12:52  pandyas
  *   Defect #56:
  *   The stock number is linked if available.  If not available, the distributor is linked to the main web page for that distributor.  The PI (stored in stock number) for Investigator will be displayed in the column for distributor with the stock number left blank.  IMSR doesn't link to stock number at this timel, but the main page is linked to the distributor.
  *
  *   Revision 1.20  2005/11/17 18:36:47  georgeda
  *   Defect #57, add a mailto link for Investigator availabilty
  *
  *   Revision 1.19  2005/11/16 15:31:35  georgeda
  *   Defect #41. Clean up of email functionality
  *
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
				<a target="_blank" href="<c:out value="${mdl.url}"/>" ><c:out value="${mdl.url}"/></a>&nbsp;
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
						    <!-- Investigator: Populate the PI name in distributor column -->
							<c:if test="${not empty av.principalInvestigator.emailAddress}">
							    <a href="mailto:<c:out value="${av.principalInvestigator.emailAddress}"/>">
							</c:if>
							<c:out value="${av.principalInvestigator.displayName}"/>
							<c:if test="${av.principalInvestigator.emailAddress}">
							    </a>
							</c:if>	
						</c:when>
						<c:when test = "${dist.id == 2}">
						<!-- Jackson Lab: If stock# not empty link to stock#, else link to main distributor page -->
							<c:choose>
								<c:when test="${not empty av.stockNumber}">						    
									<a target="_distributor" href="http://jaxmice.jax.org/jaxmice-cgi/jaxmicedb.cgi?objtype=pricedetail&stock=<c:out value="${av.stockNumber}"/>">
									<c:out value="${dist.name}"/>
								</c:when>							
								<c:otherwise>
									<a target="_distributor" href="http://jaxmice.jax.org/index.html"/>
									<c:out value="${dist.name}"/></a>
								</c:otherwise>							
							</c:choose>
						</c:when>
						<c:when test = "${dist.id == 3}">
						<!-- MMHC Repo -->
						<c:choose>
							<c:when test="${not empty av.stockNumber}">												
								<a target="_distributor" href="http://mouse.ncifcrf.gov/available_details.asp?ID=<c:out value="${av.stockNumber}"/>">
								<c:out value="${dist.name}"/></a>
							</c:when>							
							<c:otherwise>								
								<a target="_distributor" href="http://mouse.ncifcrf.gov">
								<c:out value="${dist.name}"/></a>								
							</c:otherwise>							
						</c:choose>
						</c:when>
						<c:when test = "${dist.id == 4}">
						<!-- IMSR -->
							<a target="_distributor" href="http://www.informatics.jax.org/imsr/index.jsp">						
							<c:out value="${dist.name}"/>
						</c:when>
					</c:choose>
				</td>
				<!-- Add stock number if not Investigator (already displayed under distributor)  -->
				<td class="<c:out value="${tdClass}"/>End" width="25%">
						<c:if test = "${dist.id != 1}">
							<c:out value="${av.stockNumber}"/>
						</c:if>&nbsp;
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