<%
 /*
  *   $Id: viewModelCharacteristics.jsp,v 1.35 2006-11-13 17:14:25 pandyas Exp $
  *   
  *   $Log: not supported by cvs2svn $
  *   Revision 1.34  2006/11/10 21:20:36  pandyas
  *   COSMETIC – can you please remove the bullet points (<li>) in the genotype and nomenclature field on the model characteristics search page. The bullet points are obsolete since we are never going to have more than one entry for each of these fields.
  *
  *   Revision 1.33  2006/11/08 19:11:17  pandyas
  *   added MTB logo onto view screens for Jackson Lab models
  *
  *   Revision 1.32  2006/10/31 16:07:26  pandyas
  *   added more code to allow for html markup in fields
  *
  *   Revision 1.31  2006/10/27 18:19:54  pandyas
  *   Fixed fields in display page to allow for html markup
  *
  *   Revision 1.30  2006/10/17 16:08:28  pandyas
  *   modified during development of caMOD 2.2 - various
  *
  *   Revision 1.29  2006/05/23 18:16:55  georgeda
  *   Cleaned up species/strain display
  *
  *   Revision 1.28  2006/05/22 15:57:36  pandyas
  *   Added code to display isToolMouse only when species is Mus musculus
  *
  *   Revision 1.27  2006/05/03 20:07:14  pandyas
  *   Reversed diplay of isToolMouse - it was backwards
  *
  *   Revision 1.26  2006/04/28 19:51:50  schroedn
  *   Defect #55
  *   Added Keyword Highlighting to this jsp
  *
  *   Revision 1.25  2006/04/17 19:08:19  pandyas
  *   caMod 2.1 OM changes
  *
  *   Revision 1.24  2005/12/08 21:45:18  georgeda
  *   Defect #259; handle PI availability for 2-tier data
  *
  *   Revision 1.23  2005/11/28 13:54:23  georgeda
  *   Defect #207, handle nulls for pages w/ uncontrolled vocab
  *
  *   Revision 1.22  2005/11/21 20:25:22  georgeda
  *   Defect #155, open URL in another window
  *
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
				<camod:highlight>
					<c:out value="${mdl.modelDescriptor}" escapeXml="false"/>&nbsp;
					<c:if test="${mdl.externalSource == 'Jax MTB'}">
						<IMG src="images/mtb_logo.jpg">
					</c:if>					
				</camod:highlight>
			</td>
		</tr>
		<tr>
			<td class="GreyBox" width="20%"><b>Model Descriptor</b></td>
			<td class="GreyBoxRightEnd" width="70%">
				<camod:highlight>			
				    <c:out value="${mdl.modelDescriptor}" escapeXml="false"/>
				</camod:highlight>
			</td>			
		</tr>

		<tr>			
			<td class="WhiteBox" width="20%"><b>Official Nomenclature</b></td>
			<td class="WhiteBoxRightEnd" width="70%">
				<camod:highlight>
					<c:forEach var="gcol" items="${mdl.genotypeCollection}" varStatus="stat2">
					<c:set var="item" value="${gcol.nomenclature}"/>
						<logic:notEmpty name="item">
							<c:out value="${item.name}" escapeXml="false"/>
						</logic:notEmpty>
						<logic:empty name="item">
					    	<br/>
						</logic:empty>
					</c:forEach>											
				</camod:highlight>
			</td>
		</tr>


		<tr>
			<td class="GreyBox" width="20%"><b>Genotype</b></td>
			<td class="GreyBoxRightEnd" width="70%">
				<camod:highlight>
				    <c:set var="items" value="${mdl.genotypeCollection}"/>
				    <logic:notEmpty name="items">
						<c:forEach var="item" items="${items}" varStatus="stat">
						<c:out value="${item.name}" escapeXml="false"/> 
						</c:forEach>
					</logic:notEmpty>
					<logic:empty name="items">
					    <br/>
					</logic:empty>
				</camod:highlight>					
			</td>
		</tr>

		<tr>
			<td class="WhiteBox" width="20%"><b>Species</b></td>
			<td class="WhiteBoxRightEnd" width="70%">
				<camod:highlight>
					<c:out value="${mdl.strain.species.displayName}" escapeXml="false"/>
				</camod:highlight>					
			</td>
		</tr>
		
		<tr>
			<td class="GreyBox" width="20%"><b>Strain</b></td>
			<td class="GreyBoxRightEnd" width="70%">
				<camod:highlight>
				    <c:out value="${mdl.strain.displayName}" escapeXml="false"/>&nbsp;
				</camod:highlight>
			</td>
		</tr>
		
		<tr>
			<c:choose>
				<c:when test="${mdl.strain.species.scientificName == 'Mus musculus'}">		
					<td class="WhiteBox" width="30%"><b>Is This a Tool Strain?</b></td>
					<td class="WhiteBoxRightEnd" width="70%">
						<c:choose>						
							<c:when test = "${mdl.isToolStrain == true}">
								<c:out value="Yes"/>
							</c:when>
							<c:otherwise>
								<c:out value="No"/>
							</c:otherwise>
						</c:choose>
					</td>						
				</c:when>
			</c:choose>	
		</tr>				
		
		<tr>
			<td class="GreyBox" width="20%"><b>Experimental Design</b></td>
			<td class="GreyBoxRightEnd" width="70%">
				<P>
					<camod:highlight>
					    <c:out value="${mdl.experimentDesign}" escapeXml="false" />&nbsp;
					</camod:highlight>
				</P>			
			</td>
		</tr>		               

		<tr>
			<td class="WhiteBox" width="20%"><b>Phenotype</b></td>
			<td class="WhiteBoxRightEnd" width="70%">
				<P>
					<camod:highlight>
						<c:out value="${mdl.phenotype.description}" escapeXml="false"/>
					</camod:highlight>						
				</P>		
			</td>
		</tr>		
		<tr>
			<td class="GreyBox" width="20%"><b>Website for add. info</b></td>
			<td class="GreyBoxRightEnd" width="70%">
				<P>
					<a target="_blank" href="<c:out value="${mdl.url}"/>" ><c:out value="${mdl.url}" escapeXml="false"/></a>&nbsp;					
				</P>
			</td>
		</tr>		
		<tr>
			<td class="WhiteBox" width="20%"><b>Breeding Notes</b></td>
			<td class="WhiteBoxRightEnd" width="70%">
				<P>
					<camod:highlight>
						<c:out value="${mdl.phenotype.breedingNotes}" escapeXml="false"/>&nbsp;
					</camod:highlight>					
				</P>		
			</td>
		</tr>		               

		<tr>
			<td class="GreyBox" width="20%"><b>Sex Distribution of the Phenotype</b></td>
			<td class="GreyBoxRightEnd" width="70%">
				<camod:highlight>
					<c:out value="${mdl.phenotype.sexDistribution.type}"  escapeXml="false"/>&nbsp;
				</camod:highlight>
			</td>
		</tr>	
        
		<tr>
			<td class="WhiteBox" width="20%"><b>Submitted by</b></td>
			<td class="WhiteBoxRightEnd" width="70%">
			    <c:if test="${not empty mdl.submitter.emailAddress}">
				    <a href="mailto:<c:out value="${mdl.submitter.emailAddress}"/>"></a>
				</c:if>
				<c:out value="${mdl.submitter.displayName}"  escapeXml="false"/>
				<c:if test="${not empty mdl.submitter.emailAddress}">
				    
				</c:if>			
			</td>
		</tr>
                  
		<tr>
			<td class="GreyBox" width="20%"><b>Principal Investigator / Lab</b></td>
			<td class="GreyBoxRightEnd" width="70%">
				<c:if test="${not empty mdl.principalInvestigator.emailAddress}">
				    <a href="mailto:<c:out value="${mdl.principalInvestigator.emailAddress}"/>">
				</c:if>
				<c:out value="${mdl.principalInvestigator.displayName}" escapeXml="false"/>
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
		<tr><td colspan="2">
			<table cellpadding="5" cellspacing="0" border="0" width="100%">
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
				<c:out value="${av.name}" escapeXml="false"/>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="45%">
					<c:set var="dist" value="${av.animalDistributor}"/>
					<c:choose>
						<c:when test = "${dist.id == 1}">
						    <c:choose>
								<c:when test="${av.stockNumber == '-1'}">
						            <!-- Investigator from 2-tier. -->
									<c:if test="${not empty mdl.principalInvestigator.emailAddress}">
									    <a href="mailto:<c:out value="${mdl.principalInvestigator.emailAddress}"/>">
									</c:if>
									<c:out value="${mdl.principalInvestigator.displayName}" escapeXml="false"/>
									<c:if test="${mdl.principalInvestigator.emailAddress}">
									    </a>
									</c:if>	
								</c:when>
								<c:otherwise>
						    
								    <!-- Investigator: Populate the PI name in distributor column -->
									<c:if test="${not empty av.principalInvestigator.emailAddress}">
									    <a href="mailto:<c:out value="${av.principalInvestigator.emailAddress}"/>">
									</c:if>
									<c:out value="${av.principalInvestigator.displayName}" escapeXml="false"/>
									<c:if test="${av.principalInvestigator.emailAddress}">
									    </a>
									</c:if>	
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test = "${dist.id == 2}">
						<!-- Jackson Lab: If stock# not empty link to stock#, else link to main distributor page -->
							<c:choose>
								<c:when test="${not empty av.stockNumber}">						    
									<a target="_distributor" href="http://jaxmice.jax.org/jaxmice-cgi/jaxmicedb.cgi?objtype=pricedetail&stock=<c:out value="${av.stockNumber}"/>">
									<c:out value="${dist.name}" escapeXml="false"/>
								</c:when>							
								<c:otherwise>
									<a target="_distributor" href="http://jaxmice.jax.org/index.html"/>
									<c:out value="${dist.name}" escapeXml="false"/></a>
								</c:otherwise>							
							</c:choose>
						</c:when>
						<c:when test = "${dist.id == 3}">
						<!-- MMHC Repo -->
						<c:choose>
							<c:when test="${not empty av.stockNumber}">												
								<a target="_distributor" href="http://mouse.ncifcrf.gov/available_details.asp?ID=<c:out value="${av.stockNumber}" escapeXml="false"/>">
								<c:out value="${dist.name}" escapeXml="false"/></a>
							</c:when>							
							<c:otherwise>								
								<a target="_distributor" href="http://mouse.ncifcrf.gov">
								<c:out value="${dist.name}" escapeXml="false"/></a>								
							</c:otherwise>							
						</c:choose>
						</c:when>
						<c:when test = "${dist.id == 4}">
						<!-- IMSR -->
							<a target="_distributor" href="http://www.informatics.jax.org/imsr/index.jsp">						
							<c:out value="${dist.name}" escapeXml="false"/>
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
		</c:if>
		</table></td></tr>
		
		<tr><td>&nbsp;</td></tr>
		<% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.MODEL_CHARACTERISTICS); %>
		<%@ include file="/jsp/includeComments.jsp" %>
	</TABLE>
	
</td></tr>
</TABLE>

<%@ include file="/jsp/footer.jsp" %>