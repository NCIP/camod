<%
/*
 * $Log: not supported by cvs2svn $
 * Revision 1.40  2007/06/25 16:35:09  pandyas
 * Fixed typo that displayed histopathology units instead of metastasis units for age of onset and age of detection
 *
 * Revision 1.39  2007/06/19 20:41:06  pandyas
 * The EVSPreferredDescription does not return results for Zebrafish vocabulary so the code was changed (This impacts organ.EVSPreferredDescription,  disease.EVSPreferredDescription, and developmentalStage) for all screens with trees
 *
 * Revision 1.38  2007/06/19 20:15:08  pandyas
 * The EVSPreferredDescription does not return results for Zebrafish vocabulary so the code was changed (This impacts organ.EVSPreferredDescription and disease.EVSPreferredDescription) for all screens with trees
 *
 * Revision 1.37  2006/11/13 20:20:29  pandyas
 * Modified IMG SRC location to include complete location (added /camod/...)
 *
 * Revision 1.36  2006/11/13 17:14:11  pandyas
 * #468 - remove width and height variable for mtb image icon on header of serach results and view pages with Jax data
 *
 * Revision 1.35  2006/11/08 19:11:13  pandyas
 * added MTB logo onto view screens for Jackson Lab models
 *
 * Revision 1.34  2006/11/01 17:34:10  pandyas
 * Added red color to tumor incidence rates of 0 (from JAX data)
 *
 * Revision 1.33  2006/10/27 18:31:16  pandyas
 * Fixed fields in display page to allow for html markup
 *
 * Revision 1.32  2006/10/23 16:50:04  pandyas
 * Added Age at Detection to view screen
 *
 * Revision 1.31  2006/10/17 16:08:28  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.30  2006/05/19 17:35:40  georgeda
 * Fixed bug introduced in 2.1 OM change
 *
 * Revision 1.29  2006/04/28 19:50:13  schroedn
 * Defect #55
 * Added Keyword Highlighting to this jsp
 *
 * Revision 1.28  2006/04/17 19:08:19  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.27  2006/01/04 21:30:54  georgeda
 * Enhancement #272 - Added diagnosis to summary of tumors at top of page
 *
 * Revision 1.26  2005/12/05 17:48:31  schroedn
 * Defect #251 - Changed 'Comments' to 'Comment' throughout entire page
 *
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
 * $Id: viewHistopathology.jsp,v 1.41 2007-08-15 16:16:39 pandyas Exp $
 *
 */   
%>
<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	

<bean:define id="mdl" name="animalmodel"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<bean:define id="hpColl" name="mdl" property="histopathologyCollection"/>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="0" cellspacing="0" border="0" align="center" width="100%">	

			<tr>
				<td class="formTitle" height="20" width="100%">
				Histopathology - Model:
				<camod:highlight>
					<c:out value="${mdl.modelDescriptor}" escapeXml="false"/>&nbsp;
					<c:if test="${mdl.externalSource == 'Jax MTB'}">
						<IMG src="/camod/images/mtb_logo.jpg">
					</c:if>						
				</camod:highlight>
				</td>				
			</tr>
<%      
	final List histopathColl = new ArrayList(((AnimalModel)mdl).getHistopathologyCollection());
	final int cc = (histopathColl!=null)?histopathColl.size():0;
	System.out.println("Histopathology rowCount==>" + cc);
%>
		<% if ( cc > 0 ) { %>
		    <tr>
		        <td>
				    <table summary="" cellpadding="0" cellspacing="0" border="0" align="center" width="100%">
					    <tr>
						    <td class="formTitleBlue" height="20" width="50%">
								Organ / Tissue
							</td>	
							<td class="formTitleBlue" height="20" width="50%">
								Diagnosis
							</td>	
						</tr>
					</table>
				</td>
		    </tr>
		    <tr>
			  	<td class="resultsBoxWhiteNoBottom" width="100%" align="left">
                    <table summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">
			            <c:forEach var="h" items="${hpColl}" varStatus="histstat">
				  	        <tr>
					  	        <td width="50%">
									<a href="<c:out value="#histo_${histstat.count}"/>">
										<camod:highlight><c:out value="${h.organ.name}"/></camod:highlight>
									</a>
								</td>
								<td width="50%">
									<c:choose>
										<c:when test="${empty h.disease.name}">
											<camod:highlight><c:out value="${h.disease.nameUnctrlVocab}" escapeXml="false"/></camod:highlight>
										</c:when>
										<c:otherwise>
											<camod:highlight><c:out value="${h.disease.name}" escapeXml="false"/></camod:highlight>
										</c:otherwise>
									</c:choose>								
							    </td>
						    </tr>
						    
							<bean:define id="mtsColl" name="h" property="metastasisCollection"/>
							
							<c:forEach var="m" items="${mtsColl}" varStatus="metastat">
							    <tr>
								    <td width="50%">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-
										<a href="<c:out value="#metas_${histstat.count}_${metastat.count}"/>">
											<c:out value="${m.organ.name}"/>
										</a>(Metastasis)
									</td>
									<td width="50%">
									    <c:if test="${not empty m.disease}">
											    <camod:highlight><c:out value="${disease.name}"/></camod:highlight>
										</c:if>
									</td>
								</tr>
							</c:forEach>
				        </c:forEach>
			        </table>
				</td>
			</tr>
			
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
					Lesion / Tumor in <camod:highlight><c:out value="${h.organ.name}"/>&nbsp;</camod:highlight>
				</td>
			</tr>				
			
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Organ / Tissue</b></td>
				<td class="resultsBoxGreyEnd" width="75%">
					<camod:highlight><c:out value="${h.organ.name}"/>&nbsp;</camod:highlight>
				</td>
			</tr>

			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Diagnosis</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">
				    <bean:define id="d" name="h" property="disease"/>
					     <camod:highlight><c:out value="${d.name}"/>&nbsp;<br></camod:highlight>
				</td>
			</tr>
				
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Age of Tumor Onset</b></td>
				<td class="resultsBoxGreyEnd" width="75%">
					<camod:highlight><c:out value="${h.ageOfOnset}"/>&nbsp;<c:out value="${h.ageOfOnsetUnit}"/></camod:highlight>
				</td>
			</tr>
			
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Age of Tumor Detection</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">
					<camod:highlight><c:out value="${h.ageOfDetection}"/>&nbsp;<c:out value="${h.ageOfDetectionUnit}"/></camod:highlight>
				</td>
			</tr>						
	
			<tr>
				<td class="resultsBoxGrey" width="25%">
					<b>Average Weight of Tumor (mg)</b>
				</td>
				<td class="resultsBoxGreyEnd" width="75%">
					<camod:highlight><c:out value="${h.weightOfTumor}"/>&nbsp;</camod:highlight>
				</td>
			</tr>

			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Average Volume of Tumor (mm<sup>3</sup>)</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">
					 <camod:highlight><c:out value="${h.volumeOfTumor}"/>&nbsp;</camod:highlight>
				</td>
			</tr>			

			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Tumor Incidence over Lifetime (%)</b></td>
				<td class="resultsBoxGreyEnd" width="75%">
					<camod:highlight><c:out value="${h.relationalOperation}"/>&nbsp;</camod:highlight>
					<c:choose>
						<c:when test = "${h.tumorIncidenceRate == '0'}">
						<font color=red>
							<camod:highlight><c:out value="${h.tumorIncidenceRate}"/>&nbsp;</camod:highlight>
						</font>
						</c:when>
						<c:otherwise>
							<camod:highlight><c:out value="${h.tumorIncidenceRate}"/>&nbsp;</camod:highlight>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>

			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Survival Information</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">
					<camod:highlight><c:out value="${h.survivalInfo}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
			</tr>			

			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Macroscopic Description</b></td>
				<td class="resultsBoxGreyEnd" width="75%">
					<camod:highlight><c:out value="${h.grossDescription}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
			</tr>
	
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Microscopic Description</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">
					<camod:highlight><c:out value="${h.microscopicDescription}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
			</tr>
	
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Genetic Alterations found in the Tumor</b></td>
				<td class="resultsBoxGreyEnd" width="75%">
					<camod:highlight><c:out value="${h.geneticAlteration.observation}" escapeXml="false"/>&nbsp;</camod:highlight>
					<c:if test="${not empty h.geneticAlteration.methodOfObservation}">&nbsp;<br/>
						Method - &nbsp;
						<camod:highlight><c:out value="${h.geneticAlteration.methodOfObservation}" escapeXml="false"/>&nbsp;</camod:highlight>
					</c:if>
				</td>
			</tr>
				
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Comparative Data from other Species</b></td>
				<td class="resultsBoxWhiteEnd" width="75%">
					<camod:highlight><c:out value="${h.comparativeData}" escapeXml="false" />&nbsp;</camod:highlight>
				</td>
			</tr>	
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Comment</b></td>
				<td class="resultsBoxGreyEnd" width="75%">
					<camod:highlight><c:out value="${h.comments}" escapeXml="false" />&nbsp;</camod:highlight>
				</td>
			</tr>
			
			<bean:define id="cmColl" name="h" property="clinicalMarkerCollection"/>
			<c:if test="${not empty cmColl}">
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Clinical Markers</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
						<table summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="90%">
						    <tr>
							    <td class="formTitle" width="65%"><b>Clinical Marker Name</b></td>
							    <td class="formTitle" width="35%"><b>Value</b></td>
						    </tr>
						    <c:forEach var="c" items="${cmColl}">
							    	<tr>
					        		<td class="WhiteBox">
											<c:choose>
												<c:when test="${empty c.name}">
													<camod:highlight><c:out value="${c.nameUnctrlVocab}" escapeXml="false"/></camod:highlight>
												</c:when>
												<c:otherwise>
													<camod:highlight><c:out value="${c.name}" escapeXml="false"/></camod:highlight>
												</c:otherwise>
											</c:choose>					                
					                </td>
					                <td class="WhiteBoxRightEnd"><c:out value="${c.value}" escapeXml="false"/>&nbsp;</td>
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
	    <bean:define id="mtsColl" name="h" property="metastasisCollection"/>
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
						<camod:highlight><c:out value="${m.organ.name}"/>&nbsp;</camod:highlight>
					</td>
				</tr>
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Diagnosis</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
						<bean:define id="d" name="m" property="disease"/>
							<camod:highlight><c:out value="${d.name}"/>&nbsp;</camod:highlight><br>
						&nbsp;
					</td>
				</tr>	
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Age at Onset of Metastasis</b></td>
					<td class="resultsBoxGreyEnd" width="75%">
						<camod:highlight><c:out value="${m.ageOfOnset}"/>&nbsp;<c:out value="${m.ageOfOnsetUnit}"/></camod:highlight>
				    </td>
				</tr>	
				
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Age at Detection of Metastasis</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
						<camod:highlight><c:out value="${m.ageOfDetection}"/>&nbsp;<c:out value="${m.ageOfDetectionUnit}"/></camod:highlight>
				    </td>
				</tr>						
	
				<tr>
					<td class="resultsBoxGrey" width="25%">
					<b>Average Weight of Tumor (mg)</b></td>
					<td class="resultsBoxGreyEnd" width="75%">
					    <camod:highlight><c:out value="${m.weightOfTumor}"/>&nbsp;</camod:highlight>
					</td>
				</tr>
	
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Average Volume of Tumor (mm<sup>3</sup>)</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
						 <camod:highlight><c:out value="${m.volumeOfTumor}"/>&nbsp;</camod:highlight>
				    </td>
			    </tr>			
	
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Metastasis Incidence over Lifetime (%)</b></td>
					<td class="resultsBoxGreyEnd" width="75%">
						<camod:highlight><c:out value="${m.relationalOperation}"/>&nbsp;</camod:highlight>
						<camod:highlight><c:out value="${m.tumorIncidenceRate}"/>&nbsp;</camod:highlight>
					</td>
				</tr>
	
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Survival Information</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
						<camod:highlight><c:out value="${m.survivalInfo}" escapeXml="false"/>&nbsp;</camod:highlight>
					</td>
				</tr>			
	
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Macroscopic Description</b></td>
					<td class="resultsBoxGreyEnd" width="75%">
						<camod:highlight><c:out value="${m.grossDescription}" escapeXml="false"/>&nbsp;</camod:highlight>
					</td>
				</tr>
	
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Microscopic Description</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
						<camod:highlight><c:out value="${m.microscopicDescription}" escapeXml="false"/>&nbsp;</camod:highlight>
					</td>
				</tr>
	
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Genetic Alterations found in the Metastasis</b></td>
					<td class="resultsBoxGreyEnd" width="75%">
						<camod:highlight><c:out value="${m.geneticAlteration.observation}" escapeXml="false"/>&nbsp;</camod:highlight>
						<c:if test="${not empty m.geneticAlteration.methodOfObservation}"><br/>
							Method - &nbsp;
							<camod:highlight><c:out value="${m.geneticAlteration.methodOfObservation}" escapeXml="false"/>&nbsp;</camod:highlight>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="resultsBoxWhite" width="25%"><b>Comparative Data from other Species</b></td>
					<td class="resultsBoxWhiteEnd" width="75%">
						<camod:highlight><c:out value="${m.comparativeData}" escapeXml="false" />&nbsp;</camod:highlight>
				</td>
				<tr>
					<td class="resultsBoxGrey" width="25%"><b>Comment</b></td>
					<td class="resultsBoxGreyEnd" width="75%">
						<camod:highlight><c:out value="${m.comments}" escapeXml="false"/>&nbsp;</camod:highlight>
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