<%

/**
 * 
 * $Id: viewCarcinogenicInterventions.jsp,v 1.36 2008-11-14 17:14:13 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.35  2007/10/31 19:33:58  pandyas
 * Fixed #8188 	Rename UnctrlVocab items to text entries
 *
 * Revision 1.34  2007/08/27 15:34:57  pandyas
 * Reverted back to EVSPreferredDescription since this was fixed
 *
 * Revision 1.33  2007/06/19 20:41:06  pandyas
 * The EVSPreferredDescription does not return results for Zebrafish vocabulary so the code was changed (This impacts organ.EVSPreferredDescription,  disease.EVSPreferredDescription, and developmentalStage) for all screens with trees
 *
 * Revision 1.32  2007/05/16 16:42:18  pandyas
 * Removed two <br> after Radiation and after Gene Delivery to look better
 *
 * Revision 1.31  2007/03/28 18:04:32  pandyas
 * Modified for the following Test Track items:
 * #462 - Customized search for carcinogens for Jackson Lab data
 * #494 - Advanced search for Carcinogens for Jackson Lab data
 *
 * Revision 1.30  2006/11/13 20:19:50  pandyas
 * Modified IMG SRC location to include complete location (added /camod/...)
 *
 * Revision 1.29  2006/11/01 19:50:08  pandyas
 * Changed the values for the TREATMENT_TYPES inserted in the environmental factor table as follows:
 *
 * Instead of inserting “Growth Factor” insert “Growth Factor Type”
 * Instead of inserting “Hormone” insert “Hormone Type”
 * Instead of inserting “Other” insert “Other Type”
 * Instead of inserting “Radiation” insert “Radiation Type”
 *
 * Revision 1.28  2006/11/01 19:25:21  pandyas
 * modified to view CI for Jackson Lab data - Environmental_Factor.Type for JAX data needs to be unique from caMOD types already used.
 *
 * Revision 1.27  2006/10/31 19:36:21  pandyas
 * added more code to allow for html markup in fields
 *
 * Revision 1.26  2006/10/27 18:39:24  pandyas
 * Fixed fields in display page to allow for html markup
 *
 * Revision 1.25  2006/10/04 14:48:26  pandyas
 * added dosage unit and age of treatment unit to view - missed
 *
 * Revision 1.24  2006/05/24 16:58:40  georgeda
 * Fixed gene delivery display prob.
 *
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

<%@ page buffer="64kb"%>

<bean:define id="mdl" name="animalmodel"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="1" align="center" width="100%">
			<tr>
				<td class="formTitle" height="20" colspan="7">
					Carcinogenic Agents - Model:
					<camod:highlight>
						<c:out value="${mdl.modelDescriptor}" escapeXml="false"/>
					<c:if test="${mdl.externalSource == 'Jax MTB'}">
						<IMG src="/camod/images/mtb_logo.jpg">
					</c:if>							
					</camod:highlight>
				</td>				
			</tr>			
			</TABLE>			
			<br>

					
			<!--   Start Chemical / Drug Section (caMOD and Jackson Lab data) ------------------------------------------------>
			<c:set var="environmentalFactorType" value="Chemical / Drug"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
				<TABLE summary="" cellpadding="3" cellspacing="0" border="1" align="center" width="100%">
	
				<tr>
					<td class="formTitleBlue" height="20" colspan="7">Chemical / Drug</td>				
				</tr>
				<!--   Start of if externalSource is Jax MTB -->
				<c:if test="${mdl.externalSource == 'Jax MTB'}">
				<tr>
					<td class="greySubTitleLeft" width="17%">Agent Type</td>
					<td class="greySubTitleLeft" width="17%">Agent Name</td>				
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
						<camod:highlight><c:out value="${cd.environmentalFactor.type}" escapeXml="false"/></camod:highlight>
					</td>
	
					<td class="<c:out value="${tdClass}"/>" width="20%">
						<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
					</td>
				</tr>
				</c:forEach>			
				</c:if>		
				<!--   End of if externalSource is Jax MTB -->
			
				<!--   Start of if externalSource is empty (caMOD data) -->	
				<c:if test="${empty mdl.externalSource}">
				<tr>
					<td class="greySubTitleLeft" width="17%">Chemical / Drug</td>
					<td class="greySubTitleLeft" width="17%">Dose</td>
					<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>
					<td class="greySubTitleLeft" width="17%">Administrative Route</td>
					<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
					<td class="greySubTitle" width="17%">Gender</td>
					<td class="greySubTitle" width="17%">Comment</td>									
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
								<camod:highlight><c:out value="${cd.environmentalFactor.nameAlternEntry}" escapeXml="false"/></camod:highlight>
							</c:when>
							<c:otherwise>
								<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
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
						<camod:highlight><c:out value="${cd.treatment.dosage}"  escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.dosageUnit}" escapeXml="false"/></camod:highlight>
					</td>
					<td class="<c:out value="${tdClass}"/>" width="17%">
						<camod:highlight><c:out value="${cd.treatment.regimen}" escapeXml="false"/>&nbsp;</camod:highlight>
					</td>
					<td class="<c:out value="${tdClass}"/>" width="17%">
						<c:choose>
							<c:when test="${empty cd.treatment.administrativeRoute}">
								<camod:highlight><c:out value="${cd.treatment.adminRouteAlternEntry}" escapeXml="false"/></camod:highlight>
							</c:when>
							<c:otherwise>
								<camod:highlight><c:out value="${cd.treatment.administrativeRoute}" escapeXml="false"/></camod:highlight>
							</c:otherwise>
						</c:choose>&nbsp;
					</td>
					<td class="<c:out value="${tdClass}"/>" width="17%">
						<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}"/></camod:highlight>
					</td>
					<td class="<c:out value="${tdClass}"/>" width="17%">
						<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}" escapeXml="false"/>&nbsp;</camod:highlight>
					</td>

					<td class="<c:out value="${tdClass}"/>End" width="17%">
						<camod:highlight><c:out value="${cd.environmentalFactor.comments}"escapeXml="false"/>&nbsp;</camod:highlight>
					</td>
					
				</tr>
				</c:forEach>
				<!--   End of if externalSource is empty (caMOD data) -->
				</c:if>
				</TABLE>
				<br>								
				</c:if>

			
			
			<!--   Start Growth Factor Section (caMOD and Jackson Lab data) ------------------------------------------------>
			<c:set var="environmentalFactorType" value="Growth Factor"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="1" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="7">Growth Factor</td>				
			</tr>
			
			<!--   Start of if externalSource is Jax MTB -->
			<c:if test="${mdl.externalSource == 'Jax MTB'}">
			<tr>
				<td class="greySubTitleLeft" width="17%">Agent Type</td>
				<td class="greySubTitleLeft" width="17%">Agent Name</td>				
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
					<camod:highlight><c:out value="${cd.environmentalFactor.type}" escapeXml="false"/></camod:highlight>
				</td>

				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
				</td>
			</tr>
			</c:forEach>			
			</c:if>		
			<!--   End of if externalSource is Jax MTB -->

			<!--   Start of if externalSource is empty (caMOD data) -->	
			<c:if test="${empty mdl.externalSource}">												
			<tr>
				<td class="greySubTitleLeft" width="17%">Growth Factor</td>
				<td class="greySubTitleLeft" width="17%">Dose</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>	
				<td class="greySubTitleLeft" width="17%">Administrative Route</td>
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>
				<td class="greySubTitle" width="17%">Comment</td>					
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
							<camod:highlight><c:out value="${cd.environmentalFactor.nameAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.dosage}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.dosageUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.regimen}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.treatment.administrativeRoute}">
							<camod:highlight><c:out value="${cd.treatment.adminRouteAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.treatment.administrativeRoute}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
						<camod:highlight><c:out value="${cd.environmentalFactor.comments}"escapeXml="false"/>&nbsp;</camod:highlight>
				</td>				
			</tr>
			</c:forEach>
			<!--   End of if externalSource is empty (caMOD data) -->
			</c:if>			
			</TABLE>		
			<br>								
			</c:if>


			<!--   Start Hormone Section  (caMOD and Jackson Lab data) ------------------------------------------------>
			<c:set var="environmentalFactorType" value="Hormone"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="1" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="7">Hormone</td>				
			</tr>
			
			<!--   Start of if externalSource is Jax MTB -->
			<c:if test="${mdl.externalSource == 'Jax MTB'}">
			<tr>
				<td class="greySubTitleLeft" width="17%">Agent Type</td>
				<td class="greySubTitleLeft" width="17%">Agent Name</td>				
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
					<camod:highlight><c:out value="${cd.environmentalFactor.type}" escapeXml="false"/></camod:highlight>
				</td>

				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
				</td>
			</tr>
			</c:forEach>			
			</c:if>		
			<!--   End of if externalSource is Jax MTB -->

			<!--   Start of if externalSource is empty (caMOD data) -->	
			<c:if test="${empty mdl.externalSource}">									
			<tr>
				<td class="greySubTitleLeft" width="17%">Hormone</td>
				<td class="greySubTitleLeft" width="17%">Dose</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="17%">Administrative Route</td>
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>
				<td class="greySubTitle" width="17%">Comment</td>
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
							<camod:highlight><c:out value="${cd.environmentalFactor.nameAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.dosage}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.dosageUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.regimen}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.treatment.administrativeRoute}">
							<camod:highlight><c:out value="${cd.treatment.adminRouteAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.treatment.administrativeRoute}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
						<camod:highlight><c:out value="${cd.environmentalFactor.comments}"escapeXml="false"/>&nbsp;</camod:highlight>
				</td>				
			</tr>
			</c:forEach>
			<!--   End of if externalSource is empty (caMOD data) -->
			</c:if>			
			</TABLE>
			<br>								
			</c:if>			
			
			
			<!--   Start Radiation Section (caMOD and Jackson Lab data) ------------------------------------------------>
			<c:set var="environmentalFactorType" value="Radiation"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="1" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="7">Radiation</td>				
			</tr>
			
			<!--   Start of if externalSource is Jax MTB -->
			<c:if test="${mdl.externalSource == 'Jax MTB'}">
			<tr>
				<td class="greySubTitleLeft" width="17%">Agent Type</td>
				<td class="greySubTitleLeft" width="17%">Agent Name</td>				
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
					<camod:highlight><c:out value="${cd.environmentalFactor.type}" escapeXml="false"/></camod:highlight>
				</td>

				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
				</td>
			</tr>
			</c:forEach>			
			</c:if>		
			<!--   End of if externalSource is Jax MTB -->

			<!--   Start of if externalSource is empty (caMOD data) -->	
			<c:if test="${empty mdl.externalSource}">									
			<tr>
				<td class="greySubTitleLeft" width="17%">Radiation</td>
				<td class="greySubTitleLeft" width="17%">Dose</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>	
				<td class="greySubTitleLeft" width="17%">Administrative Route</td>
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>
				<td class="greySubTitle" width="17%">Comment</td>	
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
							<camod:highlight><c:out value="${cd.environmentalFactor.nameAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.dosage}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.dosageUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.regimen}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.treatment.administrativeRoute}">
							<camod:highlight><c:out value="${cd.treatment.adminRouteAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.treatment.administrativeRoute}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
						<camod:highlight><c:out value="${cd.environmentalFactor.comments}"escapeXml="false"/>&nbsp;</camod:highlight>
				</td>				
			</tr>
			</c:forEach>
			<!--   End of if externalSource is empty (caMOD data) -->
			</c:if>			
			</TABLE>
			<br>								
			</c:if>
			
			<!--   Start Surgery / Other Section ------------------------------------------------>
			<c:set var="environmentalFactorType" value="Other"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="1" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="7">Other Agents</td>
			</tr>
			<!--   Start of if externalSource is Jax MTB -->
			<c:if test="${mdl.externalSource == 'Jax MTB'}">
			<tr>
				<td class="greySubTitleLeft" width="17%">Agent Type</td>
				<td class="greySubTitleLeft" width="17%">Agent Name</td>				
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
					<camod:highlight><c:out value="${cd.environmentalFactor.type}" escapeXml="false"/></camod:highlight>
				</td>

				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
				</td>
			</tr>			
			</c:forEach>
			</c:if>		
			<!--   End of if externalSource is Jax MTB -->

			<!--   Start of if externalSource is empty (caMOD data) -->	
			<c:if test="${empty mdl.externalSource}">									
			<tr>
				<td class="greySubTitleLeft" width="30%">Surgery</td>
				<td class="greySubTitleLeft" width="30%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="20%">Age at Treatment</td>
				<td class="greySubTitle" width="20%">Gender</td>
				<td class="greySubTitle" width="17%">Comment</td>				
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
						<c:when test="${empty cd.environmentalFactor.name}">
							<camod:highlight><c:out value="${cd.environmentalFactor.nameAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="30%">
					<camod:highlight><c:out value="${cd.treatment.regimen}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
						<camod:highlight><c:out value="${cd.environmentalFactor.comments}"escapeXml="false"/>&nbsp;</camod:highlight>
				</td>				
			</tr>
			</c:forEach>
			<!--   End of if externalSource is empty (caMOD data) -->
			</c:if>			
			</TABLE>
			<br>								
			</c:if>			
			


     		<!--   Start Viral Treatment Section (caMOD and Jackson Lab data) ------------------------------------------------>
			<c:set var="environmentalFactorType" value="Viral"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="1" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="7">Viral Treatment</td>
			</tr>
			
			<!--   Start of if externalSource is Jax MTB -->
			<c:if test="${mdl.externalSource == 'Jax MTB'}">			
			<tr>
				<td class="greySubTitleLeft" width="17%">Agent Type</td>
				<td class="greySubTitleLeft" width="17%">Agent Name</td>				
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
					<camod:highlight><c:out value="${cd.environmentalFactor.type}" escapeXml="false"/></camod:highlight>
				</td>

				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
				</td>
			</tr>
			</c:forEach>			
			
			</c:if>		
			<!--   End of if externalSource is Jax MTB -->

			<!--   Start of if externalSource is empty (caMOD data) -->	
			<c:if test="${empty mdl.externalSource}">						
			<tr>
				<td class="greySubTitleLeft" width="17%">Virus</td>
				<td class="greySubTitleLeft" width="17%">Dose</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="17%">Administrative Route</td>
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>
				<td class="greySubTitle" width="17%">Comment</td>				
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
							<camod:highlight><c:out value="${cd.environmentalFactor.nameAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.dosage}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.dosageUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.regimen}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.treatment.administrativeRoute}">
							<camod:highlight><c:out value="${cd.treatment.adminRouteAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.treatment.administrativeRoute}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
						<camod:highlight><c:out value="${cd.environmentalFactor.comments}"escapeXml="false"/>&nbsp;</camod:highlight>
				</td>				
			</tr>
			</c:forEach>
			<!--   End of if externalSource is empty (caMOD data) -->
			</c:if>			
			</TABLE>
			<br>								
			</c:if>	
					

			<!--   Start Environmental Factor Section (caMOD data) ------------------------------------------------>
			<c:set var="environmentalFactorType" value="Environment"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="1" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="7">Environment</td>				
			</tr>
			
			<tr>
				<td class="greySubTitleLeft" width="17%">Environmental Factor</td>
				<td class="greySubTitleLeft" width="17%">Dose</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="17%">Administrative Route</td>
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>
				<td class="greySubTitle" width="17%">Comment</td>				
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
							<camod:highlight><c:out value="${cd.environmentalFactor.nameAlternEntry}"  escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.dosage}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.dosageUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.regimen}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.treatment.administrativeRoute}">
							<camod:highlight><c:out value="${cd.treatment.adminRouteAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.treatment.administrativeRoute}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
						<camod:highlight><c:out value="${cd.environmentalFactor.comments}"escapeXml="false"/>&nbsp;</camod:highlight>
				</td>				
			</tr>
			</c:forEach>
			</TABLE>		
			<br>								
			</c:if>

			<!--   Start Gene Delivery Section (caMOD data) ------------------------------------------------>
			<c:set var="environmentalFactorType" value="GeneDelivery"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="1" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="7">Gene Delivery</td>				
			</tr>
			<tr>
				<td class="greySubTitleLeft" width="17%">Viral Vector</td>
				<td class="greySubTitleLeft" width="17%">Gene</td>
				<td class="greySubTitleLeft" width="17%">Organ</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>	
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>
				<td class="greySubTitle" width="17%">Comment</td>
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
							<camod:highlight><c:out value="${cd.viralVectorAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.viralVector}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.geneInVirus}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="30%">
					<camod:highlight><c:out value="${cd.organ.EVSPreferredDescription}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.treatment.regimen}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
						<camod:highlight><c:out value="${cd.comments}"escapeXml="false"/>&nbsp;</camod:highlight>
				</td>				
			</tr>
			</c:forEach>
			</TABLE>
			<br>								
			</c:if>
				
			
			
			<!--   Start Nutritional Factor Section  (caMOD data) ------------------------------------------------>
			<c:set var="environmentalFactorType" value="Nutrition"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="1" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="6">Nutrition</td>				
			</tr>
			<tr>
				<td class="greySubTitleLeft" width="20%">Nutritional Component / Diet</td>
				<td class="greySubTitleLeft" width="20%">Dose</td>
				<td class="greySubTitleLeft" width="20%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="20%">Age at Treatment</td>
				<td class="greySubTitle" width="20%">Gender</td>
				<td class="greySubTitle" width="17%">Comment</td>				
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
							<camod:highlight><c:out value="${cd.environmentalFactor.nameAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.treatment.dosage}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.dosageUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.treatment.regimen}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
						<camod:highlight><c:out value="${cd.environmentalFactor.comments}"escapeXml="false"/>&nbsp;</camod:highlight>
				</td>				
			</tr>
			</c:forEach>
			</TABLE>
			<br>								
			</c:if>

			
     		<!--   Start Antibody Section (caMOD and Jackson Lab data) ------------------------------------------------>
			<c:set var="environmentalFactorType" value="Antibody"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="1" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="7">Antibody</td>
			</tr>
			<!--   Start of if externalSource is Jax MTB -->
			<c:if test="${mdl.externalSource == 'Jax MTB'}">
			<tr>
				<td class="greySubTitleLeft" width="17%">Agent Type</td>
				<td class="greySubTitleLeft" width="17%">Agent Name</td>				
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
					<camod:highlight><c:out value="${cd.environmentalFactor.type}" escapeXml="false"/></camod:highlight>
				</td>

				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
				</td>
			</tr>			
			</c:forEach>
			</c:if>		
			<!--   End of if externalSource is Jax MTB -->

			<!--   Start of if externalSource is empty (caMOD data) -->	
			<c:if test="${empty mdl.externalSource}">									
			<tr>
				<td class="greySubTitleLeft" width="17%">Antibody</td>
				<td class="greySubTitleLeft" width="17%">Dose</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="17%">Administrative Route</td>
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>
				<td class="greySubTitle" width="17%">Comment</td>
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
							<camod:highlight><c:out value="${cd.environmentalFactor.nameAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.dosage}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.dosageUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.regimen}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.treatment.administrativeRoute}">
							<camod:highlight><c:out value="${cd.treatment.adminRouteAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.treatment.administrativeRoute}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
						<camod:highlight><c:out value="${cd.environmentalFactor.comments}"escapeXml="false"/>&nbsp;</camod:highlight>
				</td>				
			</tr>
			</c:forEach>
			<!--   End of if externalSource is empty (caMOD data) -->
			</c:if>			
			</TABLE>
			<br>								
			</c:if>
			
     		<!--   Start Bacteria Section (caMOD and Jackson Lab data) ------------------------------------------------>
			<c:set var="environmentalFactorType" value="Bacteria"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="1" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="7">Bacteria</td>
			</tr>
			<!--   Start of if externalSource is Jax MTB -->
			<c:if test="${mdl.externalSource == 'Jax MTB'}">			
			<tr>
				<td class="greySubTitleLeft" width="17%">Agent Type</td>
				<td class="greySubTitleLeft" width="17%">Agent Name</td>				
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
					<camod:highlight><c:out value="${cd.environmentalFactor.type}" escapeXml="false"/></camod:highlight>
				</td>

				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
				</td>
			</tr>
			</c:forEach>
			</c:if>		
			<!--   End of if externalSource is Jax MTB -->

			<!--   Start of if externalSource is empty (caMOD data) -->	
			<c:if test="${empty mdl.externalSource}">									
			<tr>
				<td class="greySubTitleLeft" width="17%">Bacteria</td>
				<td class="greySubTitleLeft" width="17%">Dose</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="17%">Administrative Route</td>
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>
				<td class="greySubTitle" width="17%">Comment</td>
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
							<camod:highlight><c:out value="${cd.environmentalFactor.nameAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.dosage}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.dosageUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.regimen}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.treatment.administrativeRoute}">
							<camod:highlight><c:out value="${cd.treatment.adminRouteAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.treatment.administrativeRoute}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
						<camod:highlight><c:out value="${cd.environmentalFactor.comments}"escapeXml="false"/>&nbsp;</camod:highlight>
				</td>				
			</tr>
			</c:forEach>
			<!--   End of if externalSource is empty (caMOD data) -->
			</c:if>			
			</TABLE>
			<br>								
			</c:if>


     		<!--   Start Plasmid Section (caMOD and Jackson Lab data) ------------------------------------------------>
			<c:set var="environmentalFactorType" value="Plasmid"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="1" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="7">Plasmid</td>
			</tr>
			<!--   Start of if externalSource is Jax MTB -->
			<c:if test="${mdl.externalSource == 'Jax MTB'}">			
			<tr>
				<td class="greySubTitleLeft" width="17%">Agent Type</td>
				<td class="greySubTitleLeft" width="17%">Agent Name</td>				
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
					<camod:highlight><c:out value="${cd.environmentalFactor.type}" escapeXml="false"/></camod:highlight>
				</td>

				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
				</td>
			</tr>
			</c:forEach>
			</c:if>		
			<!--   End of if externalSource is Jax MTB -->

			<!--   Start of if externalSource is empty (caMOD data) -->	
			<c:if test="${empty mdl.externalSource}">									
			<tr>
				<td class="greySubTitleLeft" width="17%">Plasmid</td>
				<td class="greySubTitleLeft" width="17%">Dose</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="17%">Administrative Route</td>
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>
				<td class="greySubTitle" width="17%">Comment</td>
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
							<camod:highlight><c:out value="${cd.environmentalFactor.nameAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.dosage}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.dosageUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.regimen}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.treatment.administrativeRoute}">
							<camod:highlight><c:out value="${cd.treatment.adminRouteAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.treatment.administrativeRoute}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
						<camod:highlight><c:out value="${cd.environmentalFactor.comments}"escapeXml="false"/>&nbsp;</camod:highlight>
				</td>				
			</tr>
			</c:forEach>
			<!--   End of if externalSource is empty (caMOD data) -->
			</c:if>			
			</TABLE>
			<br>								
			</c:if>	
			
     		<!--   Start Transposon Section (caMOD and Jackson Lab data) ------------------------------------------------>
			<c:set var="environmentalFactorType" value="Transposon"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="1" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="7">Transposon</td>
			</tr>
			<!--   Start of if externalSource is Jax MTB -->
			<c:if test="${mdl.externalSource == 'Jax MTB'}">			
			<tr>
				<td class="greySubTitleLeft" width="17%">Agent Type</td>
				<td class="greySubTitleLeft" width="17%">Agent Name</td>				
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
					<camod:highlight><c:out value="${cd.environmentalFactor.type}" escapeXml="false"/></camod:highlight>
				</td>

				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
				</td>
			</tr>
			</c:forEach>
			</c:if>		
			<!--   End of if externalSource is Jax MTB -->

			<!--   Start of if externalSource is empty (caMOD data) -->	
			<c:if test="${empty mdl.externalSource}">									
			<tr>
				<td class="greySubTitleLeft" width="17%">Transposon</td>
				<td class="greySubTitleLeft" width="17%">Dose</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="17%">Administrative Route</td>
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>
				<td class="greySubTitle" width="17%">Comment</td>
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
							<camod:highlight><c:out value="${cd.environmentalFactor.nameAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.dosage}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.dosageUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.regimen}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.treatment.administrativeRoute}">
							<camod:highlight><c:out value="${cd.treatment.adminRouteAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.treatment.administrativeRoute}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
						<camod:highlight><c:out value="${cd.environmentalFactor.comments}"escapeXml="false"/>&nbsp;</camod:highlight>
				</td>				
			</tr>
			</c:forEach>
			<!--   End of if externalSource is empty (caMOD data) -->
			</c:if>			
			</TABLE>
			<br>								
			</c:if>		

     		<!--   Start Signaling Molecule Section (caMOD and Jackson Lab data) ------------------------------------------------>
			<c:set var="environmentalFactorType" value="Signaling Molecule"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[environmentalFactorType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="1" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="7">Signaling Molecule</td>
			</tr>
			<!--   Start of if externalSource is Jax MTB -->
			<c:if test="${mdl.externalSource == 'Jax MTB'}">			
			<tr>
				<td class="greySubTitleLeft" width="17%">Agent Type</td>
				<td class="greySubTitleLeft" width="17%">Agent Name</td>				
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
					<camod:highlight><c:out value="${cd.environmentalFactor.type}" escapeXml="false"/></camod:highlight>
				</td>

				<td class="<c:out value="${tdClass}"/>" width="20%">
					<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
				</td>
			</tr>
			</c:forEach>
			</c:if>		
			<!--   End of if externalSource is Jax MTB -->

			<!--   Start of if externalSource is empty (caMOD data) -->	
			<c:if test="${empty mdl.externalSource}">									
			<tr>
				<td class="greySubTitleLeft" width="17%">Signaling Molecule</td>
				<td class="greySubTitleLeft" width="17%">Dose</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="17%">Administrative Route</td>
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>
				<td class="greySubTitle" width="17%">Comment</td>
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
							<camod:highlight><c:out value="${cd.environmentalFactor.nameAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.environmentalFactor.name}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.dosage}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.dosageUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.regimen}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.treatment.administrativeRoute}">
							<camod:highlight><c:out value="${cd.treatment.adminRouteAlternEntry}" escapeXml="false"/></camod:highlight>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${cd.treatment.administrativeRoute}" escapeXml="false"/></camod:highlight>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.ageAtTreatment}" escapeXml="false"/>&nbsp;<c:out value="${cd.treatment.ageAtTreatmentUnit}" escapeXml="false"/></camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<camod:highlight><c:out value="${cd.treatment.sexDistribution.type}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
						<camod:highlight><c:out value="${cd.environmentalFactor.comments}"escapeXml="false"/>&nbsp;</camod:highlight>
				</td>				
			</tr>
			</c:forEach>
			<!--   End of if externalSource is empty (caMOD data) -->
			</c:if>			
			</TABLE>
			<br>								
			</c:if>	


		</td></tr></TABLE>
	</td></tr></TABLE>
</td></tr></TABLE>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
		<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
	    <% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.CARCINOGENIC_INTERVENTION); %>
	    <%@ include file="/jsp/includeComments.jsp" %>
	    </TABLE>
    </td></tr>
</TABLE>


<%@ include file="/jsp/footer.jsp" %>