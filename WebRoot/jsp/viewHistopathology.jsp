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
				<c:out value="${mdl.modelDescriptor}" escapeXml="false"/>
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
						</a>
						<bean:define id="mtsColl" name="h" property="metastatisCollectionSorted"/>
						<c:forEach var="m" items="${mtsColl}" varStatus="metastat">
							<br>&nbsp;&nbsp;-&nbsp;
							<a href="<c:out value="#metas_${histstat.count}_${metastat.count}"/>">
								<c:out value="${m.organ.EVSPreferredDescription}"/>
							</a>&nbsp;(Metastasis)
						</c:forEach>
	                    <br>
						<bean:define id="cmColl" name="h" property="clinicalMarkerCollectionSorted"/>
						<c:if test="${not empty cmColl}">
						    <br>
							<table summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="50%">
							    <tr>
								    <td class="formTitle" width="65%"><b>Clinical Marker Name</b></td>
								    <td class="formTitle" width="35%"><b>Value</b></td>
							    </tr>
							    <c:forEach var="c" items="${cmColl}">
								    <tr>
						                <td class="WhiteBox"><c:out value="${c.name}"/></td>
						                <td class="WhiteBoxRightEnd"><c:out value="${c.value}"/>&nbsp;</td>
								    </tr>
							    </c:forEach>
						    </table>
						</c:if>
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
			    <a name="<c:out value="histo_${histstat.count}"/>"/>
				<td class="formTitleBlue" height="20" colspan="2">
				Lesion / Tumor in <c:out value="${h.organ.EVSPreferredDescription}"/>&nbsp;
				</td>
			</tr>				
			
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Organ / Tissue</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">
				<c:out value="${h.organ.EVSPreferredDescription}"/>&nbsp;
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
					<b>Average Weight of Tumor (mg)</b></td>
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
					<td class="resultsBoxWhite" width="25%"><b>Tumor Incidence (%)</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
						<c:out value="${h.relationalOperation}"/>
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
				<td class="resultsBoxWhite" width="25%"><b>Diagnosis</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">
				<bean:define id="dc" name="h" property="diseaseCollection"/>
				<c:forEach var="d" items="${dc}">
					<c:out value="${d.EVSPreferredDescription}"/>&nbsp;<br>
					</c:forEach>
					</td>
				</tr>
	
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Genetic Alterations found in the Tumor</b></td>
					<td class="resultsBoxGreyEnd" width="75%">
						<c:out value="${h.geneticAlteration.observation}" escapeXml="false"/>
					<c:if test="${not empty m.geneticAlteration.methodOfObservation}"><br/>
					Method - &nbsp;
					<c:out value="${m.geneticAlteration.methodOfObservation}" escapeXml="false"/>
						</c:if>
					</td>
				</tr>
	
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Comments</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
					<c:out value="${h.comments}" escapeXml="false" />&nbsp;
				</td>
			</tr>
	        <tr>
	            <td></td>
	        </tr>
        </TABLE>
	    <bean:define id="mtsColl" name="h" property="metastatisCollectionSorted"/>
	    <c:forEach var="m" items="${mtsColl}" varStatus="metastat">
            <TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
				<tr>
				    <a name="<c:out value="metas_${histstat.count}_${metastat.count}"/>"/>
					<td class="greySubTitleLeftEnd" height="20" colspan="2">
						Metastasis in <c:out value="${m.organ.EVSPreferredDescription}"/>&nbsp;
						</td>
					</tr>
	
					<tr>
						<td class="resultsBoxWhite" width="25%"><b>Organ / Tissue</b></td>
						<td class="resultsBoxWhiteEnd" width="75%">
						<c:out value="${m.organ.EVSPreferredDescription}"/>&nbsp;
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
					<td class="resultsBoxWhite" width="25%"><b>Incidence of Metastasis(%)</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
						<c:out value="${m.relationalOperation}"/>
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
					<td class="resultsBoxWhite" width="25%"><b>Diagnosis</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
					<bean:define id="dc" name="m" property="diseaseCollection"/>
					<c:forEach var="d" items="${dc}">
						<c:out value="${d.EVSPreferredDescription}"/><br>
						</c:forEach>&nbsp;
						</td>
					</tr>
	
					<tr>
						<td class="resultsBoxGrey" width="25%"><b>Genetic Alterations found in the Metastasis</b></td>
						<td class="resultsBoxGreyEnd" width="75%">
							<c:out value="${m.geneticAlteration.observation}"/>
						&nbsp; 
						<c:if test="${not empty m.geneticAlteration.methodOfObservation}"><br/>
						Method - &nbsp;
						<c:out value="${m.geneticAlteration.methodOfObservation}"/>
						</c:if>
					</td>
				</tr>
	
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Comments</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
					<c:out value="${m.comments}"/>&nbsp;
					</td>
				</tr>
				<tr>
				    <td></td>
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