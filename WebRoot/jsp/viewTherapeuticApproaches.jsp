<%

/**
 * 
 * $Id: viewTherapeuticApproaches.jsp,v 1.26 2009-05-04 20:01:12 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.25  2007/12/27 01:15:53  pandyas
 * Modified version for feature #8816  	Connection to caELMIR - retrieve data for therapy search page
 *
 * Revision 1.24  2007/12/18 13:30:36  pandyas
 * Added caELMIR jsp include for integration of study data
 *
 * Revision 1.23  2007/12/04 16:04:40  pandyas
 * modified for #816  	Connection to caELMIR - retrieve data for therapy search page
 *
 * Revision 1.22  2007/12/04 13:47:24  pandyas
 * Modified code for #8816  	Connection to caELMIR - retrieve data for therapy search page
 *
 * Revision 1.21  2007/11/25 23:30:49  pandyas
 * Initial version for feature #8816  	Connection to caELMIR - retrieve data for therapy search page
 *
 * Revision 1.20  2006/10/27 18:31:16  pandyas
 * Fixed fields in display page to allow for html markup
 *
 * Revision 1.19  2006/04/28 19:52:30  schroedn
 * Defect #55
 * Added Keyword Highlighting to this jsp
 *
 *
 */

%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	

<bean:define id="mdl" name="animalmodel"/>
<bean:define id="ta" name="therapeuticApproachesColl"/>
<bean:define id="csd" name="caelmirStudyData"/>
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitle" height="20" colspan="9">
					Therapeutic Approaches - Model:
					<camod:highlight>
						<c:out value="${mdl.modelDescriptor}" escapeXml="false"/>
					</camod:highlight>
				</td>				
			</tr>
			<tr>
				<td class="resultsBoxWhiteEnd" height="20" colspan="9">
					<c:forEach var="t" items="${ta}" varStatus="stat">
					    <a href="<c:out value="#therap_${stat.count}"/>">
							 <c:out value="${t.agent.name}" escapeXml="false"/><br/>
						</a>  
					</c:forEach>
				</td>
			</tr>
			</TABLE>
			<br>	
			<c:forEach var="t" items="${ta}" varStatus="stat">
			
			    <c:set var="foundYstData" value="0"/>
			    <c:set var="foundInvivoData" value="0"/>
			    <c:set var="foundPreClinicalData" value="0"/>
			    <c:set var="foundClinicalData" value="0"/>
			    
				<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">
				    <tr><td><a name="<c:out value="therap_${stat.count}"/>"/>&nbsp;</td></tr>
					<c:set var="agt" value="${t.agent}"/>
					<c:set var="agentId" value="${agt.id}"/>
					<c:set var="nscnum" value="${agt.nscNumber}"/>
					<%@ include file="/jsp/includeAgentDetails.jsp" %>
					<%@ include file="/jsp/includePreclinicalTrials.jsp" %>
					<%@ include file="/jsp/includeClinicalTrials.jsp" %>
					<%@ include file="/jsp/includeYeastData.jsp" %>
					<%@ include file="/jsp/includeInvivoData.jsp" %>
				</TABLE>
			</c:forEach>
				<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">			
					<%@ include file="/jsp/includeCaelmirData.jsp" %>					
				</TABLE>		
			</BR>	
			
			
		</td></tr></TABLE>
	</td></tr></TABLE>
</td></tr></TABLE>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
    <% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.THERAPEUTIC_APPROACHES); %>
    <%@ include file="/jsp/includeComments.jsp" %>
    </TABLE>
    </td></tr>
</TABLE>

<%@ include file="/jsp/footer.jsp" %>