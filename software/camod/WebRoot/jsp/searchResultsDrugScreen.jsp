<%
/*
 *  $Log: not supported by cvs2svn $
 *  Revision 1.5  2005/11/11 21:27:38  georgeda
 *  Defect #29.  Separate drug screening and animal model search results.
 *
 *
 *  $Id: searchResultsDrugScreen.jsp,v 1.6 2005-11-15 22:13:46 georgeda Exp $
 */
%>
<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import='gov.nih.nci.camod.Constants.*' %>

<bean:define id="agentList" name="<%=Constants.DRUG_SCREEN_SEARCH_RESULTS%>"/>
<bean:define id="searchOption" name="drugScreenSearchOptions"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
		    <c:if test="${empty agentList}">
				<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
					<tr>
						<td class="formTitle" height="20" colspan="4" align="center">Search Results</td>
					</tr>
					<tr>
						<td class="resultsBoxWhiteEnd" height="20" colspan="9">
							
								<B><I>No compounds with the specified NSC number: 
								<c:out value="${nscNumber}"/><br/>
								</I></B>
							
						</td>
					</tr>
				</TABLE>
				<br>
			</c:if>
			<c:if test="${not empty agentList}">
			<c:set var="foundYstData" value="0"/>
			<c:set var="foundInvivoData" value="0"/>
			<c:set var="foundPreClinicalData" value="0"/>
			<c:set var="foundClinicalData" value="0"/>
			<TABLE summary="" cellpadding="0" cellspacing="0" border="0" align="center" width="100%">
				<tr>			
				    <td class="formTitle" height="20" colspan="2" align="center">
		                <b>Search Results for <c:out value="${agentName}"/></b>
		                <br/>
		                <b>
							NSC: &nbsp;&nbsp;<c:out value="${nscNumber}"/><br/>
							CAS: &nbsp;&nbsp; <c:out value="${casNumber}"/><br/>
						</b>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<c:forEach var="agt" items="${agentList}" varStatus="stat">
				    <c:set var="agentId" value="${agt.id}"/>
					<c:if test="${searchOption.doPreClinical}">
					    <c:set var="modelList" value="${preClinicalModels[agt.id]}"/>
					    <c:if test="${not empty modelList && foundPreClinicalData == 0}">
						    <c:set var="foundPreClinicalData" value="1"/>
							<!-- preclinical models data-->
							
						  	<tr>
								<td class="formTitleBlue" colspan="2" align="center">
									Summary of Preclinical Trials
								</td>
							</tr>
						  	<tr>
							  	<td colspan="2">
									<table summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
										<c:forEach var="ivd" items="${modelList}" varStatus="stat2">
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
												<a href="ViewModelAction.do?unprotected_method=populateModelCharacteristics&<%=Constants.Parameters.MODELID+"="%><c:out value="${ivd[0]}"/>">
													<c:out value="${ivd[1]}"/>
												</a>
												</td>
												<td align="left" class="<c:out value="${tdClass}End"/>"> &nbsp;&nbsp;<c:out value="${ivd[2]}"/>
												</td>
										  	</tr>
									  	</c:forEach>
									</table>
								</td>
							</tr>
						</c:if>
						<tr><td colspan="2"> &nbsp; </td></tr>
						<!-- end preclinical models data -->
					</c:if>
					<c:set var="nscnum" value="${agt.nscNumber}"/>
					<c:if test="${searchOption.doClinical}">
						<%@ include file="/jsp/includeClinicalTrials.jsp" %>
					</c:if>
					<c:if test="${searchOption.doYeast}">
						<%@ include file="/jsp/includeYeastData.jsp" %>
					</c:if>
					<c:if test="${searchOption.doInvivo}">
						<%@ include file="/jsp/includeInvivoData.jsp" %>
					</c:if>
	            </c:forEach>
	            <c:if test="${foundPreClinicalData == 0 && searchOption.doPreClinical}">
            		<tr>
						<td class="formTitleBlue" colspan="2" align="center">
							Summary of Preclinical Trials
						</td>
					</tr>
					<tr>
					    <td class="resultsBoxWhiteEnd" colspan=2><b>No data found.</b></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
				</c:if>
				<c:if test="${foundClinicalData == 0 && searchOption.doClinical}">
					<tr>
						<td class="formTitleBlue" colspan="2" align="center">
							Current Clinical Trials
						</td>
					</tr>
					<tr>
						<td class="resultsBoxWhiteEnd" colspan=2><b>No data found.</b></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
				</c:if>
				<c:if test="${foundYstData == 0 && searchOption.doYeast}">
					<tr>
						<td class="formTitleBlue" colspan="2" align="center">
							Publicly available data from the NCI Yeast Anticancer Drug Screen
						</td>
					</tr>
					<tr>
					    <td class="resultsBoxWhiteEnd" colspan=2><b>No data found.</b></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
				</c:if>
				<c:if test="${foundInvivoData == 0 && searchOption.doInvivo}">
				    <tr>
						<td class="formTitleBlue" colspan="2" align="center">
							In Vivo Screening Data
						</td>
					</tr>
					<tr>
						<td class="resultsBoxWhiteEnd" colspan=2><b>No data found.</b></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
				</c:if>
			</TABLE>
		</c:if>
		</td></tr></TABLE>
	</td></tr></TABLE>
</td></tr></TABLE>

<%@ include file="/jsp/footer.jsp" %>