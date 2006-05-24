<%

/**
 * 
 * $Id: viewCarcinogenicInterventions.jsp,v 1.24 2006-05-24 16:58:40 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.23  2006/04/28 19:48:14  schroedn
 * Defect #55
 * Added HighlightTextTag
 *
 *
 */

%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	

<bean:define id="mdl" name="animalmodel"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">
			<tr>
				<td class="formTitle" height="20" colspan="6">
					Carcinogenic Agents - Model:
					<camod:highlight>
						<c:out value="${mdl.modelDescriptor}" escapeXml="false"/>
					</camod:highlight>
				</td>				
			</tr>			
			</TABLE>			
			<br>
			
			<!--   Start Chemical / Drug Section -->
			<c:set var="environmentalFactorType" value="Chemical / Drug"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">

			<tr>
				<td class="formTitleBlue" height="20" colspan="6">Chemical / Drug</td>				
			</tr>
			
			<tr>
				<td class="greySubTitleLeft" width="17%">Chemical / Drug</td>
				<td class="greySubTitleLeft" width="17%">Dose</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="17%">Administrative Route</td>
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>				
			</tr>
			
			<c:forEach var="cd" items="${cdList}" varStatus="stat">
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
			<tr>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.environmentalFactor.name}">
							<camod:highlight><c:out value="${cd.environmentalFactor.nameUnctrlVocab}"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.environmentalFactor.name}"/></camod:highlight>
						</c:otherwise>
					</c:choose>
					<c:if test="${not empty cd.environmentalFactor.nscNumber}">
						<br>NSC: 
						<a target="_blank" href="http://dtp.nci.nih.gov/dtpstandard/servlet/ChemData?queryHOLD=&searchtype=NSC&chemnameboolean=and&outputformat=html&Submit=Submit&searchlist=<c:out value="${cd.environmentalFactor.nscNumber}"/>">
						    <c:out value="${cd.environmentalFactor.nscNumber}"/>
						</a>
					</c:if>
					<c:if test="${not empty cd.environmentalFactor.casNumber}">
						<br>CAS: 
						<a target="_blank" href="http://dtp.nci.nih.gov/dtpstandard/servlet/ChemData?queryHOLD=&searchtype=CAS&chemnameboolean=and&outputformat=html&Submit=Submit&searchlist=<c:out value="${cd.environmentalFactor.casNumber}"/>">
						    <c:out value="${cd.environmentalFactor.casNumber}"/>
						</a>    
					</c:if>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.dosage}"/>&nbsp;<c:out value="${cd.treatment.dosageUnit}"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.regimen}"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.treatment.administrativeRoute}">
							<camod:highlight><c:out value="${cd.treatment.adminRouteUnctrlVocab}"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.treatment.administrativeRoute}"/></camod:highlight>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			</c:forEach>
			</TABLE>
			<br>
			</c:if>

			<!--   Start Environmental Factor Section -->
			<c:set var="environmentalFactorType" value="Environment"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="6">Environment</td>				
			</tr>
			
			<tr>
				<td class="greySubTitleLeft" width="17%">Environmental Factor</td>
				<td class="greySubTitleLeft" width="17%">Dose</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="17%">Administrative Route</td>
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>				
			</tr>
			
			<c:forEach var="cd" items="${cdList}" varStatus="stat">
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
			<tr>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<c:choose>
						<c:when test="${empty cd.environmentalFactor.name}">
							<camod:highlight><c:out value="${cd.environmentalFactor.nameUnctrlVocab}"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.environmentalFactor.name}"/></camod:highlight>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.dosage}"/>&nbsp;<c:out value="${cd.treatment.dosageUnit}"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.regimen}"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.treatment.administrativeRoute}">
							<camod:highlight><c:out value="${cd.treatment.adminRouteUnctrlVocab}"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.treatment.administrativeRoute}"/></camod:highlight>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			</c:forEach>
			</TABLE>		
	        <br>
	        </c:if>

			<!--   Start Gene Delivery Section -->
			<c:set var="environmentalFactorType" value="GeneDelivery"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="6">Gene Delivery</td>				
			</tr>
			<tr>
				<td class="greySubTitleLeft" width="17%">Viral Vector</td>
				<td class="greySubTitleLeft" width="17%">Gene</td>
				<td class="greySubTitleLeft" width="17%">Organ</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>	
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>
			</tr>
			<c:forEach var="cd" items="${cdList}" varStatus="stat">
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
			<tr>
				<td class="<c:out value="${tdClass}"/>" width="30%">
					<c:choose>
						<c:when test="${empty cd.viralVector}">
							<camod:highlight><c:out value="${cd.viralVectorUnctrlVocab}"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.viralVector}"/></camod:highlight>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.geneInVirus}"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="30%">
					<camod:highlight><c:out value="${cd.organ.EVSPreferredDescription}"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.treatment.regimen}"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			</c:forEach>
			</TABLE>
			<br>
			</c:if>

			<!--   Start Growth Factor Section -->
			<c:set var="environmentalFactorType" value="Growth Factor"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="6">Growth Factor</td>				
			</tr>
			
			<tr>
				<td class="greySubTitleLeft" width="17%">Growth Factor</td>
				<td class="greySubTitleLeft" width="17%">Dose</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>	
				<td class="greySubTitleLeft" width="17%">Administrative Route</td>
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>					
			</tr>
			<c:forEach var="cd" items="${cdList}" varStatus="stat">
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
			<tr>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.environmentalFactor.name}">
							<camod:highlight><c:out value="${cd.environmentalFactor.nameUnctrlVocab}"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.environmentalFactor.name}"/></camod:highlight>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.dosage}"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.regimen}"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.treatment.administrativeRoute}">
							<camod:highlight><c:out value="${cd.treatment.adminRouteUnctrlVocab}"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.treatment.administrativeRoute}"/></camod:highlight>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			</c:forEach>
			</TABLE>		
			<br>
			</c:if>


			<!--   Start Hormone Section -->
			<c:set var="environmentalFactorType" value="Hormone"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="6">Hormone</td>				
			</tr>
			<tr>
				<td class="greySubTitleLeft" width="17%">Hormone</td>
				<td class="greySubTitleLeft" width="17%">Dose</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="17%">Administrative Route</td>
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>
			</tr>
			<c:forEach var="cd" items="${cdList}" varStatus="stat">
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
			<tr>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.environmentalFactor.name}">
							<camod:highlight><c:out value="${cd.environmentalFactor.nameUnctrlVocab}"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.environmentalFactor.name}"/></camod:highlight>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.dosage}"/>&nbsp;<c:out value="${cd.treatment.dosageUnit}"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.regimen}"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.treatment.administrativeRoute}">
							<camod:highlight><c:out value="${cd.treatment.adminRouteUnctrlVocab}"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.treatment.administrativeRoute}"/></camod:highlight>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			</c:forEach>
			</TABLE>
			<br>
			</c:if>

			<!--   Start Nutritional Factor Section -->
			<c:set var="environmentalFactorType" value="Nutrition"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="5">Nutrition</td>				
			</tr>
			<tr>
				<td class="greySubTitleLeft" width="20%">Nutritional Component / Diet</td>
				<td class="greySubTitleLeft" width="20%">Dose</td>
				<td class="greySubTitleLeft" width="20%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="20%">Age at Treatment</td>
				<td class="greySubTitle" width="20%">Gender</td>				
			</tr>
			
			<c:forEach var="cd" items="${cdList}" varStatus="stat">
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>&nbsp;
			<tr>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<c:choose>
						<c:when test="${empty cd.environmentalFactor.name}">
							<camod:highlight><c:out value="${cd.environmentalFactor.nameUnctrlVocab}"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.environmentalFactor.name}"/></camod:highlight>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.treatment.dosage}"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.treatment.regimen}"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="20%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			</c:forEach>
			</TABLE>
			<br>
			</c:if>


			<!--   Start Radiation Section -->
			<c:set var="environmentalFactorType" value="Radiation"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="6">Radiation</td>				
			</tr>
			<tr>
				<td class="greySubTitleLeft" width="17%">Radiation</td>
				<td class="greySubTitleLeft" width="17%">Dose</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>	
				<td class="greySubTitleLeft" width="17%">Administrative Route</td>
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>	
			</tr>
			<c:forEach var="cd" items="${cdList}" varStatus="stat">
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
			<tr>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.environmentalFactor.name}">
							<camod:highlight><c:out value="${cd.environmentalFactor.nameUnctrlVocab}"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.environmentalFactor.name}"/></camod:highlight>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.dosage}"/>&nbsp;<c:out value="${cd.treatment.dosageUnit}"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.regimen}"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.treatment.administrativeRoute}">
							<camod:highlight><c:out value="${cd.treatment.adminRouteUnctrlVocab}"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.treatment.administrativeRoute}"/></camod:highlight>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			</c:forEach>
			</TABLE>
			<br>
			</c:if>
			
			<!--   Start Surgery / Other Section -->
			<c:set var="environmentalFactorType" value="Other"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="4">Surgery</td>
			</tr>
			
			<tr>
				<td class="greySubTitleLeft" width="30%">Surgery</td>
				<td class="greySubTitleLeft" width="30%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="20%">Age at Treatment</td>
				<td class="greySubTitle" width="20%">Gender</td>				
			</tr>
			
			<c:forEach var="cd" items="${cdList}" varStatus="stat">
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>&nbsp;
			<tr>
				<td class="<c:out value="${tdClass}"/>" width="30%">
					<c:choose>
						<c:when test="${empty cd.environmentalFactor.name}">
							<camod:highlight><c:out value="${cd.environmentalFactor.nameUnctrlVocab}"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.environmentalFactor.name}"/></camod:highlight>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="30%">
					<camod:highlight><c:out value="${cd.treatment.regimen}"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="20%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			</c:forEach>
			</TABLE>
			<br>
			</c:if>

     		<!--   Start Viral Treatment Section -->
			<c:set var="environmentalFactorType" value="Viral"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="6">Viral Treatment</td>
			</tr>
			
			<tr>
				<td class="greySubTitleLeft" width="17%">Virus</td>
				<td class="greySubTitleLeft" width="17%">Dose</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="17%">Administrative Route</td>
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>				
			</tr>
			
			<c:forEach var="cd" items="${cdList}" varStatus="stat">
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
			<tr>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<c:choose>
						<c:when test="${empty cd.environmentalFactor.name}">
							<camod:highlight><c:out value="${cd.environmentalFactor.nameUnctrlVocab}"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.environmentalFactor.name}"/></camod:highlight>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.dosage}"/>&nbsp;<c:out value="${cd.treatment.dosageUnit}"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.regimen}"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.treatment.administrativeRoute}">
							<camod:highlight><c:out value="${cd.treatment.adminRouteUnctrlVocab}"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.treatment.administrativeRoute}"/></camod:highlight>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			</c:forEach>
			</TABLE>
			<br>
			</c:if>


		</td></tr></TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
    <% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.CARCINOGENIC_INTERVENTION); %>
    <%@ include file="/jsp/includeComments.jsp" %>
    </TABLE>
</TABLE>


<%@ include file="/jsp/footer.jsp" %>