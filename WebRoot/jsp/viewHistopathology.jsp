<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	

<bean:define id="mdl" name="animalmodel"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<bean:define id="hpColl" name="mdl" property="histopathologyCollection"/>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	

			<tr>
				<td class="formTitle" height="20" >
				Histopathology - Model:
				<c:out value="${mdl.modelDescriptor}"/>
				</td>				
			</tr>
<%      
	final List histopathColl = ((AnimalModel)mdl).getHistopathologyCollection();
	final int cc = (histopathColl!=null)?histopathColl.size():0;
	System.out.println("Histopathology rowCount==>" + cc);
%>
		<% if ( cc > 0 ) { %>
			<tr>
				<td class="resultsBoxWhiteEnd">
					<c:forEach var="h" items="${hpColl}">
						<a href="">
							C<c:out value="${h.organ.conceptCode}"/>
						</a>
						<bean:define id="mtsColl" name="h" property="metastatisCollection"/>
						<c:forEach var="m" items="${mtsColl}">
							<br>&nbsp;&nbsp;-&nbsp;
							<a href="">
								C<c:out value="${m.organ.conceptCode}"/>
							</a>&nbsp;(Metastasis)
						</c:forEach>
						<br/>
					</c:forEach>
					<br><br>
					<b>Clincal Markers</b> TBD<br/>
					<br>&nbsp;&nbsp;Marker XYZ ( Value: High )
					<br>&nbsp;&nbsp;Marker XYZ ( Value: High )
				</td>				
			</tr>	
	    <%} else { %>
		     <TR>
		  		<TD class="resultsBoxGreyEnd" colspan=4>
		  		<B><I>No information is available. </I></B> 
		   		</TD>
		     </TR>
		<%}%>		
			
			</TABLE>
			
			<br>

			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	

			<c:forEach var="h" items="${hpColl}">
			<tr>
				<td class="formTitleBlue" height="20" colspan="2">
				Lesion / Tumor in C<c:out value="${h.organ.conceptCode}"/>
				</td>
			</tr>				
			
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Organ / Tissue</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">
				C<c:out value="${h.organ.conceptCode}"/>
				</td>
			<tr>

			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Age of Tumor Onset</b></td>
				<td class="resultsBoxGreyEnd" width="75%">
					<c:out value="${h.ageOfOnset}"/>
				</td>
			<tr>			

			<tr>
				<td class="resultsBoxWhite" width="25%">
				<b>Average Weight of Tumor (mg)</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">
				<c:out value="${h.weightOfTumor}"/>
				</td>
			<tr>

			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Average Volume of Tumor (mm<sup>3</sup>)</b></td>
				<td class="resultsBoxGreyEnd" width="75%">
					 <c:out value="${h.volumeOfTumor}"/>
				mg<sup>3</sup></td>
			<tr>			

			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Tumor Incidence (%)</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">
					<c:out value="${h.relationalOperation}"/>&nbsp;
					<c:out value="${h.tumorIncidenceRate}"/>
				</td>
			<tr>

			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Survival Information</b></td>
				<td class="resultsBoxGreyEnd" width="75%">
				<c:out value="${h.survivalInfo}"/>
				</td>
			<tr>			

			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Macroscopic Lesion</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">
					<c:out value="${h.grossDescription}"/>
				</td>
			<tr>

			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Microscopic Description</b></td>
				<td class="resultsBoxGreyEnd" width="75%">
				<c:out value="${h.microscopicDescription}"/>
				</td>
			<tr>
			
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Diagnosis</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">
				<bean:define id="dc" name="h" property="diseaseCollection"/>
				<c:forEach var="d" items="${dc}">
					<c:out value="${d.conceptCode}"/><br>
				</c:forEach>
				</td>
			<tr>

			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Genetic Alterations found in the Tumor</b></td>
				<td class="resultsBoxGreyEnd" width="75%">
					<c:out value="${h.geneticAlteration.observation}"/>
					&nbsp; Method - &nbsp;
					<c:out value="${h.geneticAlteration.methodOfObservation}"/>
				</td>
			<tr>

			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Comments</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">
				<c:out value="${h.comments}"/>
				</td>
			<tr>

			<bean:define id="mtsColl" name="h" property="metastatisCollection"/>
			<c:forEach var="m" items="${mtsColl}">
				<tr>
					<td class="formTitleBlue" height="20" colspan="2">
						Metastasis in C<c:out value="${m.organ.conceptCode}"/>
					</td>
				</tr>

				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Organ / Tissue</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
					C<c:out value="${m.organ.conceptCode}"/>
					</td>
				<tr>
	
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Age of Tumor Onset</b></td>
					<td class="resultsBoxGreyEnd" width="75%">
						<c:out value="${m.ageOfOnset}"/>
					</td>
				<tr>			
	
				<tr>
					<td class="resultsBoxWhite" width="25%">
					<b>Average Weight of Tumor (mg)</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
					<c:out value="${m.weightOfTumor}"/>
					</td>
				<tr>
	
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Average Volume of Tumor (mm<sup>3</sup>)</b></td>
					<td class="resultsBoxGreyEnd" width="75%">
						 <c:out value="${m.volumeOfTumor}"/>
					mg<sup>3</sup></td>
				<tr>			
	
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Tumor Incidence (%)</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
						<c:out value="${m.relationalOperation}"/>&nbsp;
						<c:out value="${m.tumorIncidenceRate}"/>
					</td>
				<tr>
	
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Survival Information</b></td>
					<td class="resultsBoxGreyEnd" width="75%">
					<c:out value="${m.survivalInfo}"/>
					</td>
				<tr>			
	
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Macroscopic Lesion</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
						<c:out value="${m.grossDescription}"/>
					</td>
				<tr>
	
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Microscopic Description</b></td>
					<td class="resultsBoxGreyEnd" width="75%">
					<c:out value="${m.microscopicDescription}"/>
					</td>
				<tr>
				
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Diagnosis</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
					<bean:define id="dc" name="m" property="diseaseCollection"/>
					<c:forEach var="d" items="${dc}">
						<c:out value="${d.conceptCode}"/><br>
					</c:forEach>
					</td>
				<tr>
	
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Genetic Alterations found in the Tumor</b></td>
					<td class="resultsBoxGreyEnd" width="75%">
						<c:out value="${m.geneticAlteration.observation}"/>
						&nbsp; Method - &nbsp;
						<c:out value="${m.geneticAlteration.methodOfObservation}"/>
					</td>
				<tr>
	
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Comments</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
					<c:out value="${m.comments}"/>
					</td>
				<tr>
			</c:forEach>

			</c:forEach>
			
			</TABLE>
		</td>
		</tr>
		</TABLE>
<%@ include file="/jsp/footer.jsp" %>