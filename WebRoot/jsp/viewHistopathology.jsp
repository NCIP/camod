<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%
/*
 * $Log: not supported by cvs2svn $
 * Revision 1.25  2005/11/30 17:36:11  pandyas
 * Defect #233: Modified field labels according to instructions
 *
 * Revision 1.24  2005/11/29 13:27:47  georgeda
 * Defect #76, move top link to right
 *
 * Revision 1.23  2005/11/28 16:22:17  pandyas
 * Defect #187:  Changed Comments to Comment
 *
 * Revision 1.22  2005/11/28 13:54:10  georgeda
 * Defect #207, handle nulls for pages w/ uncontrolled vocab
 *
 * Revision 1.21  2005/11/22 18:58:52  georgeda
 * Defect #171, move location of Disease to match submission page
 *
 * Revision 1.20  2005/11/21 22:04:26  georgeda
 * Defects #168,169,179.  Changed wording on submit and view pages
 *
 *
 * $Id: viewHistopathology.jsp,v 1.26 2005-12-05 17:48:31 schroedn Exp $
 *
 */   
%>
<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	

<bean:define id="mdl" name="animalmodel"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<bean:define id="hpColl" name="mdl" property="histopathologyCollectionSorted"/>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	

			<tr>
				<td class="formTitle" height="20" >
				Histopathology - Model:
				<c:out value="${mdl.modelDescriptor}" escapeXml="false"/>&nbsp;
				</td>				
			</tr>
<%      
	final List histopathColl = ((AnimalModel)mdl).getHistopathologyCollectionSorted();
	final int cc = (histopathColl!=null)?histopathColl.size():0;
	System.out.println("Histopathology rowCount==>" + cc);
%>
		<% if ( cc > 0 ) { %>
		    <c:forEach var="h" items="${hpColl}" varStatus="histstat">
			    <tr>
			  	    <td class="resultsBoxWhiteNoBottom" align="left">
						<a href="<c:out value="#histo_${histstat.count}"/>">
							<c:out value="${h.organ.EVSPreferredDescription}"/>
						</a>&nbsp;
						<bean:define id="mtsColl" name="h" property="metastatisCollectionSorted"/>
						<c:forEach var="m" items="${mtsColl}" varStatus="metastat">
							<br>&nbsp;&nbsp;-&nbsp;
							<a href="<c:out value="#metas_${histstat.count}_${metastat.count}"/>">
								<c:out value="${m.organ.EVSPreferredDescription}"/>
							</a>(Metastasis)
						</c:forEach>
	                    <br>
				    </td>			
			    </tr>
			</c:forEach>
			<tr><td class="resultsBoxWhiteOnlyTop">&nbsp;</td></tr>
			<br>
		</TABLE>	
	    <%} else { %>
		     <TR>
		  		<TD class="resultsBoxGreyEnd" colspan=4>
		  		<B><I>No information is available. </I></B> 
		   		</TD>
		     </TR>
		<%}%>		
			
	</TABLE>
	
	<c:forEach var="h" items="${hpColl}" varStatus="histstat">
		<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">
			<tr>
			    <a name="<c:out value="histo_${histstat.count}"/>"/>&nbsp;
				<td class="formTitleBlue" height="20" colspan="2">
					Lesion / Tumor in <c:out value="${h.organ.EVSPreferredDescription}"/>&nbsp;
				</td>
			</tr>				
			
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Organ / Tissue</b></td>
				<td class="resultsBoxGreyEnd" width="75%">
					<c:out value="${h.organ.EVSPreferredDescription}"/>&nbsp;
				</td>
			</tr>

			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Diagnosis</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">
				    <bean:define id="dc" name="h" property="diseaseCollection"/>
				    <c:forEach var="d" items="${dc}">
					    <c:out value="${d.EVSPreferredDescription}"/>&nbsp;<br>
					</c:forEach>
				</td>
			</tr>
				
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Age of Tumor Onset</b></td>
				<td class="resultsBoxGreyEnd" width="75%">
					<c:out value="${h.ageOfOnset}"/>&nbsp;
				</td>
			</tr>			
	
			<tr>
				<td class="resultsBoxWhite" width="25%">
					<b>Average Weight of Tumor (mg)</b>
				</td>
				<td class="resultsBoxWhiteEnd" width="75%">
					<c:out value="${h.weightOfTumor}"/>&nbsp;
				</td>
			</tr>

			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Average Volume of Tumor (mm<sup>3</sup>)</b></td>
				<td class="resultsBoxGreyEnd" width="75%">
					 <c:out value="${h.volumeOfTumor}"/>&nbsp;
				</td>
			</tr>			

			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Tumor Incidence over Lifetime (%)</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">
					<c:out value="${h.relationalOperation}"/>&nbsp;
					<c:out value="${h.tumorIncidenceRate}"/>&nbsp;
				</td>
			</tr>

			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Survival Information</b></td>
				<td class="resultsBoxGreyEnd" width="75%">
					<c:out value="${h.survivalInfo}" escapeXml="false"/>&nbsp;
				</td>
			</tr>			

			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Macroscopic Description</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">
					<c:out value="${h.grossDescription}" escapeXml="false"/>&nbsp;
				</td>
			</tr>
	
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Microscopic Description</b></td>
				<td class="resultsBoxGreyEnd" width="75%">
					<c:out value="${h.microscopicDescription}" escapeXml="false"/>&nbsp;
				</td>
			</tr>
	
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Genetic Alterations found in the Tumor</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">
					<c:out value="${h.geneticAlteration.observation}" escapeXml="false"/>&nbsp;
					<c:if test="${not empty h.geneticAlteration.methodOfObservation}">&nbsp;<br/>
						Method - &nbsp;
						<c:out value="${h.geneticAlteration.methodOfObservation}" escapeXml="false"/>&nbsp;
					</c:if>
				</td>
			</tr>
				
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Comparative Data from other Species</b></td>
				<td class="resultsBoxGreyEnd" width="75%">
					<c:out value="${h.comparativeData}" escapeXml="false" />&nbsp;
				</td>
			</tr>	
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Comment</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">
					<c:out value="${h.comments}" escapeXml="false" />&nbsp;
				</td>
			</tr>
			
			<bean:define id="cmColl" name="h" property="clinicalMarkerCollectionSorted"/>
			<c:if test="${not empty cmColl}">
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Clinical Markers</b></td>
					<td class="resultsBoxGreyEnd" width="75%">
						<table summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="90%">
						    <tr>
							    <td class="formTitle" width="65%"><b>Clinical Marker Name</b></td>
							    <td class="formTitle" width="35%"><b>Value</b></td>
						    </tr>
						    <c:forEach var="c" items="${cmColl}">
							    <tr>
					                <td class="WhiteBox"><c:out value="${c.name}"/>&nbsp;</td>
					                <td class="WhiteBoxRightEnd"><c:out value="${c.value}"/>&nbsp;</td>
							    </tr>
						    </c:forEach>
					    </table>
					</td>
				</tr>
			</c:if>	
	        <tr>
	            <td colspan="2" align="right"><a href="#">Top</a></td>
	        </tr>
        </TABLE>
	    <bean:define id="mtsColl" name="h" property="metastatisCollectionSorted"/>
	    <c:forEach var="m" items="${mtsColl}" varStatus="metastat">
            <TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
				<tr>
				    <a name="<c:out value="metas_${histstat.count}_${metastat.count}"/>"/>&nbsp;
					<td class="greySubTitleLeftEnd" height="20" colspan="2">
						Metastasis in <c:out value="${m.organ.EVSPreferredDescription}"/>&nbsp;
					</td>
				</tr>
	
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Organ / Tissue</b></td>
					<td class="resultsBoxGreyEnd" width="75%">
						<c:out value="${m.organ.EVSPreferredDescription}"/>&nbsp;
					</td>
				</tr>
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Diagnosis</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
						<bean:define id="dc" name="m" property="diseaseCollection"/>
						<c:forEach var="d" items="${dc}">
							<c:out value="${d.EVSPreferredDescription}"/>&nbsp;<br>
						</c:forEach>&nbsp;
					</td>
				</tr>	
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Age at Onset of Metastasis</b></td>
					<td class="resultsBoxGreyEnd" width="75%">
						<c:out value="${m.ageOfOnset}"/>&nbsp;
				    </td>
				</tr>			
	
				<tr>
					<td class="resultsBoxWhite" width="25%">
					<b>Average Weight of Tumor (mg)</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
					    <c:out value="${m.weightOfTumor}"/>&nbsp;
					</td>
				</tr>
	
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Average Volume of Tumor (mm<sup>3</sup>)</b></td>
					<td class="resultsBoxGreyEnd" width="75%">
						 <c:out value="${m.volumeOfTumor}"/>&nbsp;
				    </td>
			    </tr>			
	
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Metastasis Incidence over Lifetime (%)</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
						<c:out value="${m.relationalOperation}"/>&nbsp;
						<c:out value="${m.tumorIncidenceRate}"/>&nbsp;
					</td>
				</tr>
	
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Survival Information</b></td>
					<td class="resultsBoxGreyEnd" width="75%">
						<c:out value="${m.survivalInfo}"/>&nbsp;
					</td>
				</tr>			
	
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Macroscopic Description</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
						<c:out value="${m.grossDescription}"/>&nbsp;
					</td>
				</tr>
	
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Microscopic Description</b></td>
					<td class="resultsBoxGreyEnd" width="75%">
						<c:out value="${m.microscopicDescription}"/>&nbsp;
					</td>
				</tr>
	
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Genetic Alterations found in the Metastasis</b></td>
					<td class="resultsBoxGreyEnd" width="75%">
						<c:out value="${m.geneticAlteration.observation}"/>	&nbsp; 
						<c:if test="${not empty m.geneticAlteration.methodOfObservation}"><br/>
							Method - &nbsp;
							<c:out value="${m.geneticAlteration.methodOfObservation}"/>&nbsp;
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Comparative Data from other Species</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
					<c:out value="${m.comparativeData}" escapeXml="false" />&nbsp;
				</td>
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Comment</b></td>
					<td class="resultsBoxGreyEnd" width="75%">
					<c:out value="${m.comments}"/>&nbsp;
					</td>
				</tr>
				<tr>
				    <td colspan="2" align="right"><a href="#">Top</a></td>
				</tr>
			</TABLE>
		</c:forEach>
	</c:forEach>
	</TABLE>
</TABLE>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
    <% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.HISTOPATHOLOGY); %>
    <%@ include file="/jsp/includeComments.jsp" %>
    </TABLE>
</TABLE>

<%@ include file="/jsp/footer.jsp" %>