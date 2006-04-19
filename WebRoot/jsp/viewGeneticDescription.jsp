<%

/**
 * 
 * $Id: viewGeneticDescription.jsp,v 1.40 2006-04-19 18:48:03 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.39  2006/04/17 19:08:19  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.38  2005/12/05 22:17:01  pandyas
 * Defect #61 and #62:  Removed Libraries and Tissues and Protein Similarities   until we must decide if we are going to display this data in the next release.
 *
 * Revision 1.37  2005/11/29 13:27:47  georgeda
 * Defect #76, move top link to right
 *
 * Revision 1.36  2005/11/28 22:59:17  georgeda
 * Defect #82, print the common name if the scientific name is not available
 *
 * Revision 1.35  2005/11/28 19:06:08  georgeda
 * Defect #76, added a "Top" link to bring them to the top of the page
 *
 * Revision 1.34  2005/11/28 18:31:57  georgeda
 * Defect #64, fix for newly submitted models
 *
 * Revision 1.33  2005/11/28 16:21:40  pandyas
 * Defect #187:  Changed Comments to Comment
 *
 * Revision 1.32  2005/11/28 13:53:42  georgeda
 * Defect #207, handle nulls for pages w/ uncontrolled vocab
 *
 * Revision 1.31  2005/11/22 18:14:01  georgeda
 * Defect #133, Cleaned up Gene ID
 *
 * Revision 1.30  2005/11/21 19:42:52  georgeda
 * Defect #139, do not add the segment type everytime; only when null.
 *
 * Revision 1.29  2005/11/21 18:59:26  georgeda
 * Defect #106, check for Random instead of null location of integration
 *
 * Revision 1.28  2005/11/21 18:08:41  schroedn
 * Defects #137, 136, 135
 *
 * Resorted Induced Mutation
 * Added CAS number
 * Added Gene ID
 *
 * Revision 1.27  2005/11/21 17:49:54  georgeda
 * Defect #134, added gene name to search results
 *
 * Revision 1.26  2005/11/21 16:54:36  georgeda
 * Defect #105, added MGI number for targeted modification and added hyperlink to search pages
 *
 * Revision 1.25  2005/11/21 14:43:38  georgeda
 * Defect #64, replace boolean number with text
 *
 * Revision 1.24  2005/11/17 22:39:42  pandyas
 * Follow up to Defect #103: formatting &nbsp
 *
 * Revision 1.23  2005/11/17 21:28:44  schroedn
 * Defect #141
 *
 * Capitized word
 *
 * Revision 1.22  2005/11/17 21:26:39  schroedn
 * Bug #140
 *
 * Capitalized the word construct in "Title of the construct"
 *
 * Revision 1.21  2005/11/17 17:46:56  georgeda
 * Defect #58, removed functions and conditional type.
 *
 * Revision 1.20  2005/11/16 15:17:27  schroedn
 * Defect #59
 *
 * Rename the "Gene Function" field to "Function(s) of Targeted Gene" field
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
<bean:define id="tgc" name="<%=Constants.TRANSGENE_COLL%>"/>
<bean:define id="tmc" name="<%=Constants.TARGETED_MOD_COLL%>"/>
<bean:define id="imc" name="<%=Constants.INDUCED_MUT_COLL%>"/>
<bean:define id="gsc" name="<%=Constants.GENOMIC_SEG_COLL%>"/>
<bean:define id="smc" name="<%=Constants.SPONTANEOUS_MUT_COLL%>"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">

		<tr>
			<td class="formTitle" height="20" colspan="3">
			Genetic Description - Model:
			<c:out value="${mdl.modelDescriptor}" escapeXml="false"/>&nbsp;
			</td>
		</tr>

		<tr>
			<td class="GreyBox" width="20%"><b>Transgene</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				<ul>
					<logic:iterate id="eg" name="tgc" indexId="idx">
					<li>
					    <a href="<c:out value="#eng_trans_${idx}"/>">
							<bean:write name="eg" property="name"/>
						</a>
					</logic:iterate>
				</ul>&nbsp;
			</td>			
		</tr>

		<tr>
			<td class="WhiteBox" width="20%"><b>Genomic Segment</b></td>
			<td class="WhiteBoxRightEnd" width="80%">
				<ul>
					<logic:iterate id="eg" name="gsc" indexId="idx">
					<li>
						<a href="<c:out value="#gen_seg_${idx}"/>">
							<bean:write name="eg" property="cloneDesignator"/>
						</a>
					</logic:iterate>
				</ul>&nbsp;
			</td>			
		</tr>

		<tr>
			<td class="GreyBox" width="20%"><b>Targeted Modification</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				<ul>
					<logic:iterate id="eg" name="tmc" indexId="idx">
					<li>
						<a href="<c:out value="#targ_mod_${idx}"/>">
							<bean:write name="eg" property="name"/>
						</a>
					</logic:iterate>
				</ul>&nbsp;
			</td>			
		</tr>
		
		<tr>
			<td class="WhiteBox" width="20%"><b>Induced Mutation</b></td>
			<td class="WhiteBoxRightEnd" width="80%">
				<ul>
				    <c:set var="count" value="0"/>
					<logic:iterate id="eg" name="mdl" property="engineeredGeneCollection" indexId="idx">
					<% if ( eg instanceof gov.nih.nci.camod.domain.InducedMutation ) { %>
					
					<li>
						<a href="<c:out value="#ind_mut_${count}"/>">
						    <c:set var="count" value="${count + 1}"/>
							<c:choose>
								<c:when test="${empty eg.environmentalFactor.name}">
									<c:out value="${eg.environmentalFactor.nameUnctrlVocab}"/>&nbsp;
								</c:when>
								<c:otherwise>
						            <c:out value="${eg.environmentalFactor.name}"/>
								</c:otherwise>
							</c:choose>
						</a>
					<%}%>
					</logic:iterate>
				</ul>&nbsp;
			</td>			
		</tr>
		<tr>
			<td class="GreyBox" width="20%"><b>Spontaneous Mutation</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				<ul>
				    <logic:iterate id="sm" name="smc" indexId="idx">
						<li>
						<a href="<c:out value="#spon_mut_${idx}"/>">
								<bean:write name="sm" property="name"/>
						</a>
					</logic:iterate>
				</ul>&nbsp;
			</td>			
		</tr>
	</TABLE>
	
</td></tr>
</TABLE>

<!-- Transgene -->
<% int cc = ((List)tgc).size(); %>
<% if ( cc > 0 ) { %>
<c:set var="count" value="0"/>
<c:forEach var="tg" items="${tgc}" >
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<a name="<c:out value="eng_trans_${count}"/>"/>&nbsp;
	<c:set var="count" value="${count + 1}"/>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
		<tr>
			<td class="formTitle" height="20" colspan="2">
				Engineered Transgene - Model:
				<c:out value="${mdl.modelDescriptor}" escapeXml="false"/>&nbsp;
			</td>
		</tr>

		<c:choose>
			<c:when test="${tg.locationOfIntegration != 'Random'}">
				<tr>
					<td class="GreyBox" width="35%">
					<b>Transgene Integration</b></td>
					<td class="GreyBoxRightEnd" width="65%">
					Targeted
					</td>
				</tr>
				<tr>
					<td class="WhiteBox" width="35%"><b>Location of Integration</b></td>
					<td class="WhiteBoxRightEnd" width="65%">
					<c:out value="${tg.locationOfIntegration}"/>&nbsp;
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td class="GreyBox" width="35%">
					<b>Transgene Integration</b></td>
					<td class="GreyBoxRightEnd" width="65%">
					Random
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
		
		<tr>
			<td class="GreyBox" width="35%"><b>Transgene</b></td>
			<td class="GreyBoxRightEnd" width="65%">
			<c:out value="${tg.name}"/>&nbsp;
			</td>
		</tr>
		<tr>
			<td class="WhiteBox" width="35%"><b>Transgene Species of Origin</b></td>
			<td class="WhiteBoxRightEnd" width="65%">
			<c:out value="${tg.species.scientificName}"/>&nbsp;
			</td>
		</tr>

		<bean:define id="remColl" name="tg" property="regulatoryElementCollection"/>
		<tr>
			<td class="GreyBox" width="35%"><b>Transcriptional (Promoter)</b></td>
			<td class="GreyBoxRightEnd" width="65%">
			<c:if test="${not empty remColl}">
			<table summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
				<tr>
					<td class="formTitle" width="65%"><b>Transcriptional (Promoter)</b></td>
					<td class="formTitle" width="35%"><b>Species</b></td>
				</tr>
				<c:forEach var="rem" items="${remColl}">
					<tr>
						<td class="WhiteBox"><c:out value="${rem.name}"/>&nbsp;</td>
						<td class="WhiteBoxRightEnd" width="65%">
							<c:choose>
								<c:when test="${not empty rem.species.scientificName}">
									<c:out value="${rem.species.scientificName}"/>&nbsp;
								</c:when>
								<c:otherwise>
						            <c:out value="${rem.species.commonName}"/>&nbsp;
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
			</c:if>
		</td>
		</tr>

		<tr>
			<td class="WhiteBox" width="35%"><b>Gene Function</b></td>
			<td class="WhiteBoxRightEnd" width="65%">
			<ul>
			<c:forEach var="gf" items="${tg.geneFunctionCollection}">
				<li><c:out value="${gf.function}"/></li>
			</c:forEach>
			</ul>&nbsp;
			</td>
		</tr>

		<tr>
			<td class="GreyBox" width="35%"><b>Conditional Type</b></td>
			<td class="GreyBoxRightEnd" width="65%">
				<c:if test="${tg.conditionality.conditionedBy == '1'}">
				    Conditional
				</c:if>
				<c:if test="${tg.conditionality.conditionedBy == '0'}">
				    Not Conditional
				</c:if>&nbsp;
			</td>
			
		</tr>
		<tr>
			<td class="WhiteBox" width="35%"><b>Conditional Description</b></td>
			<td class="WhiteBoxRightEnd" width="65%"><c:out value="${tg.conditionality.description}"/>&nbsp;</td>
		</tr>

		<c:if test="${not empty tg.image.id}">
		<tr>
			<td class="GreyBox" width="35%"><b>Construct Map ( Image )</b></td>
			<td class="GreyBoxRightEnd" width="65%">
			<a href='<c:out value="${tg.image.imageUrl}"/>'>
			<img src="<c:out value="${tg.image.thumbUrl}"/>" 
				height="40" width="40" border=0
				alt="Click on the image to open in a new Browser window"></a>
			<br/>( Click to View )
			</td>
		</tr>

		<tr>
			<td class="WhiteBox" width="35%"><b>Title of the Construct</b></td>
			<td class="WhiteBoxRightEnd" width="65%"><c:out value="${tg.image.title}"/>&nbsp;</td>
		</tr>
		<tr>
			<td class="GreyBox" width="35%"><b>Description of the Construct</b></td>
			<td class="GreyBoxRightEnd" width="65%"><c:out value="${tg.image.description}"/>&nbsp;</td>
		</tr>
		</c:if>


		<tr>
			<td class="WhiteBox" width="35%"><b>Organ / Tissue Gene is Expressed in and Expression Level</b></td>
			<td class="WhiteBoxRightEnd" width="65%">
				<c:if test="${not empty tg.expressionFeatureCollection}">
					<table summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
						<tr>
							<td class="formTitle" width="65%"><b>Organ</b></td>
							<td class="formTitle" width="35%"><b>Expression Level</b></td>
						</tr>
						<c:forEach var="el" items="${tg.expressionFeatureCollection}">
							<tr>
								<td class="WhiteBox"><c:out value="${el.organ.EVSPreferredDescription}"/>&nbsp;</td>
								<td class="WhiteBoxRightEnd">
								<c:out value="${el.expressionLevelDesc.expressionLevel}"/>&nbsp;</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
				<c:if test="${empty tg.expressionFeatureCollection}">
				    &nbsp;
				</c:if>
			</td>
		</tr>	
		
        <tr>
			<td class="GreyBox" width="35%"><b>MGI Number</b></td>
			<td class="GreyBoxRightEnd" width="65%">
			<c:if test="${not empty tg.mutationIdentifier.mgiNumber}">
				<a target="_blank" href="http://www.informatics.jax.org/javawi2/servlet/WIFetch?page=searchTool&query=MGI:<c:out value="${tg.mutationIdentifier.mgiNumber}"/>&selectedQuery=Genes+and+Markers">
				    <c:out value="${tg.mutationIdentifier.mgiNumber}"/>
				</a>
			</c:if>&nbsp;
			</td>			
		</tr>
		<tr>
			<td class="WhiteBox" width="35%"><b>Comment</b></td>
			<td class="WhiteBoxRightEnd" width="65%"><c:out value="${tg.comments}"/>&nbsp;</td>
		</tr>
	</TABLE>
	
</td></tr>
<tr>
    <td colspan="2" align="right"><a href="#">Top</a></td>
</tr>
</TABLE>
</c:forEach>
<%}%>
<!-- End Transgene -->


<!-- Genomic Segment -->
<c:set var="count" value="0"/>
<c:if test="${not empty genomicSegColl}">
<c:forEach var="gs" items="${genomicSegColl}">
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
    <a name="<c:out value="gen_seg_${count}"/>"/>
	<c:set var="count" value="${count + 1}"/>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
		<tr>
			<td class="formTitle" height="20" colspan="2">
				Genomic Segment - Model:<c:out value="${mdl.modelDescriptor}" escapeXml="false"/>&nbsp;
			</td>
		</tr>
		<c:choose>
			<c:when test="${gs.locationOfIntegration != 'Random'}">
				<tr>
					<td class="GreyBox" width="35%">
					<b>Transgene Integration</b></td>
					<td class="GreyBoxRightEnd" width="65%">
					Targeted&nbsp;
					</td>
				</tr>
				<tr>
					<td class="WhiteBox" width="35%"><b>Location of Integration</b></td>
					<td class="WhiteBoxRightEnd" width="65%">
					<c:out value="${gs.locationOfIntegration}"/>&nbsp;
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td class="GreyBox" width="35%">
					<b>Transgene Integration</b></td>
					<td class="GreyBoxRightEnd" width="65%">
					Random&nbsp;
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
		<tr>
			<td class="GreyBox" width="35%"><b>Segment Type</b></td>
			<td class="GreyBoxRightEnd" width="65%">
				<ul>
					<c:forEach var="segtype" items="${gs.segmentTypeCollection}">
					<li>
						<c:choose>
							<c:when test="${empty segtype.name}">
								<c:out value="${segtype.nameUnctrlVocab}"/>&nbsp;
							</c:when>
							<c:otherwise>
								<c:out value="${segtype.name}"/>&nbsp;
							</c:otherwise>
						</c:choose>
					</li>
					</c:forEach>
				</ul>
			</td>			
		</tr>

		<tr>
			<td class="WhiteBox" width="35%"><b>Designator</b></td>
			<td class="WhiteBoxRightEnd" width="65%"><c:out value="${gs.cloneDesignator}"/>&nbsp;</td>			
		</tr>		
		
		<tr>
			<td class="GreyBox" width="35%"><b>Segment Size</b></td>
			<td class="GreyBoxRightEnd" width="65%"><c:out value="${gs.segmentSize}"/>&nbsp;</td>
		</tr>

		<tr>
			<td class="WhiteBox" width="35%"><b>Organ / Tissue Gene is Expressed in and Expression Level</b></td>
			<td class="WhiteBoxRightEnd" width="65%">
				<c:if test="${not empty gs.expressionFeatureCollection}">
					<table summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
						<tr>
							<td class="formTitle" width="65%"><b>Organ</b></td>
							<td class="formTitle" width="35%"><b>Expression Level</b></td>
						</tr>
						<c:forEach var="el" items="${gs.expressionFeatureCollection}">
							<tr>
								<td class="WhiteBox"><c:out value="${el.organ.EVSPreferredDescription}"/>&nbsp;</td>
								<td class="WhiteBoxRightEnd">
								<c:out value="${el.expressionLevelDesc.expressionLevel}"/>&nbsp;</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
				<c:if test="${empty gs.expressionFeatureCollection}">
				    &nbsp;
				</c:if>
			</td>
		</tr>
	
		<c:if test="${not empty gs.image.id}">
		<tr>
			<td class="GreyBox" width="35%"><b>Construct Map ( Image )</b></td>
			<td class="GreyBoxRightEnd" width="65%">
			<a href='<c:out value="${gs.image.imageUrl}"/>'>&nbsp;
			<img src="<c:out value="${gs.image.thumbUrl}"/>" 
				height="40" width="40" border=0
				alt="Click on the image to open in a new Browser window"></a>
			<br/>( Click to View )
			</td>
		</tr>

		<tr>
			<td class="WhiteBox" width="35%"><b>Title of the Construct</b></td>
			<td class="WhiteBoxRightEnd" width="65%"><c:out value="${gs.image.title}"/>&nbsp;</td>
		</tr>
		<tr>
			<td class="GreyBox" width="35%"><b>Description of the Construct</b></td>
			<td class="GreyBoxRightEnd" width="65%"><c:out value="${gs.image.description}"/>&nbsp;</td>
		</tr>
		</c:if>

        <tr>
			<td class="WhiteBox" width="35%"><b>MGI Number</b></td>
			<td class="WhiteBoxRightEnd" width="65%">
			    <c:if test="${not empty gs.mutationIdentifier.mgiNumber}">
					<a target="_blank" href="http://www.informatics.jax.org/javawi2/servlet/WIFetch?page=searchTool&query=MGI:<c:out value="${gs.mutationIdentifier.mgiNumber}"/>&selectedQuery=Genes+and+Markers">
					    <c:out value="${gs.mutationIdentifier.mgiNumber}"/>
					</a>
			    </c:if>&nbsp;
			</td>
		</tr>

		<tr>
			<td class="GreyBox" width="35%"><b>Comments</b></td>
			<td class="GreyBoxRightEnd" width="65%"><c:out value="${gs.comments}"/>&nbsp;</td>
		</tr>
	</TABLE>
</td></tr>
<tr>
    <td colspan="2" align="right"><a href="#">Top</a></td>
</tr>
</TABLE>
</c:forEach>
</c:if>
<!-- End Genomic Segment -->

<!-- Targeted modification-->
<c:set var="count" value="0"/>
<c:if test="${not empty targetedModColl}">
<c:forEach var="tm" items="${targetedModColl}">
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<a name="<c:out value="targ_mod_${count}"/>"/>
	<c:set var="count" value="${count + 1}"/>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
		<tr>
			<td class="formTitle" height="20" colspan="2">
				Targeted Modification - Model:<c:out value="${mdl.modelDescriptor}" escapeXml="false"/>&nbsp;
			</td>
		</tr>
        <tr>
            <td class="GreyBox" width="35%"><b>Gene</b></td>
            <td class="GreyBoxRightEnd" width="65%"><c:out value="${tm.name}"/>&nbsp;</td>
        </tr>
                 
        <tr>
            <td class="WhiteBox" width="35%"><b>Modification Types</b></td>
            <td class="WhiteBoxRightEnd" width="65%">
				<c:forEach var="modType" items="${tm.modificationTypeCollection}">
				    <li>
						<c:out value="${modType.name}"/>&nbsp;
					</li>	
				</c:forEach>
				<c:if test="${not empty tm.modTypeUnctrlVocab}">&nbsp;
				    <li>
						<c:out value="${tm.modTypeUnctrlVocab}"/>&nbsp;
					</li>	
				</c:if>
            </td>
        </tr>
       <tr>
            <td class="GreyBox"><b>Gene ID</b></td>
            <td class="GreyBoxRightEnd">
            	<c:choose>
					<c:when test="${empty tm.geneId}">
						&nbsp;
					</c:when>
					<c:otherwise>
			            <a href="http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?cmd=search&db=gene&term=<c:out value="${tm.geneId}"/>" target="blank"><c:out value="${tm.geneId}"/></a>
					</c:otherwise>
				</c:choose>            	
            </td>
        </tr>
        <tr>
            <td class="WhiteBox" width="35%"><b>Genetic Background - Donor</b></td>
            <td class="WhiteBoxRightEnd" width="65%">
            	<c:out value="${tm.esCellLineName}"/>&nbsp;
            </td>
        </tr>
        <tr>
            <td class="GreyBox" width="35%"><b>Genetic Background - Recipient</b></td>
            <td class="GreyBoxRightEnd" width="65%"><c:out value="${tm.blastocystName}"/>&nbsp;</td>
        </tr>

		<tr>
			<td class="WhiteBox" width="35%"><b>Conditional Type</b></td>
			<td class="WhiteBoxRightEnd" width="65%">
			    <c:if test="${tm.conditionality.conditionedBy == '1'}">
				    Conditional
				</c:if>
				<c:if test="${tm.conditionality.conditionedBy == '0'}">
				    Not Conditional
				</c:if>&nbsp;
			</td>
		</tr>
		<tr>
			<td class="GreyBox" width="35%"><b>Conditional Description</b></td>
			<td class="GreyBoxRightEnd" width="65%"><c:out value="${tm.conditionality.description}"/>&nbsp;</td>
		</tr>

		<tr>
			<td class="WhiteBox" width="35%"><b>Function(s) of Targeted Gene</b></td>
			<td class="WhiteBoxRightEnd" width="65%">
			<ul>
			<c:forEach var="gf" items="${tm.geneFunctionCollection}">
				<li><c:out value="${gf.function}"/></li>&nbsp;
			</c:forEach>
			</ul>&nbsp;
			</td>
		</tr>

		<tr>
			<td class="WhiteBox" width="35%"><b>Organ / Tissue Gene is Expressed in and Expression Level</b></td>
			<td class="WhiteBoxRightEnd" width="65%">
				<c:if test="${not empty tm.expressionFeatureCollection}">
					<table summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
						<tr>
							<td class="formTitle" width="65%"><b>Organ</b></td>
							<td class="formTitle" width="35%"><b>Expression Level</b></td>
						</tr>
						<c:forEach var="el" items="${tm.expressionFeatureCollection}">
							<tr>
								<td class="WhiteBox"><c:out value="${el.organ.EVSPreferredDescription}"/>&nbsp;</td>
								<td class="WhiteBoxRightEnd">
								<c:out value="${el.expressionLevelDesc.expressionLevel}"/>&nbsp;</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
				<c:if test="${empty tm.expressionFeatureCollection}">
				    &nbsp;
				</c:if>
			</td>
		</tr>

		<c:if test="${not empty tm.image.id}">
		<tr>
			<td class="WhiteBox" width="35%"><b>Construct Map ( Image )</b></td>
			<td class="WhiteBoxRightEnd" width="65%">
			<a href='<c:out value="${tm.image.imageUrl}"/>'>
			<img src="<c:out value="${tm.image.thumbUrl}"/>" 
				height="40" width="40" border=0
				alt="Click on the image to open in a new Browser window"></a>
			<br/>( Click to View )
			</td>
		</tr>
		<tr>
			<td class="WhiteBox" width="35%"><b>Title of the Construct</b></td>
			<td class="WhiteBoxRightEnd" width="65%"><c:out value="${tm.image.title}"/>&nbsp;</td>
		</tr>
		<tr>
			<td class="WhiteBox" width="35%"><b>Description of the Construct</b></td>
			<td class="WhiteBoxRightEnd" width="65%"><c:out value="${tm.image.description}"/>&nbsp;</td>
		</tr>
		</c:if>
        <tr>
			<td class="GreyBox" width="35%"><b>MGI Number</b></td>
			<td class="GreyBoxRightEnd" width="65%">
				<c:if test="${not empty tm.mutationIdentifier.mgiNumber}">
					<a target="_blank" href="http://www.informatics.jax.org/javawi2/servlet/WIFetch?page=searchTool&query=MGI:<c:out value="${tm.mutationIdentifier.mgiNumber}"/>&selectedQuery=Genes+and+Markers">
					    <c:out value="${tm.mutationIdentifier.mgiNumber}"/>
					</a>
			    </c:if>&nbsp;
			</td>
		</tr>
		<tr>
			<td class="WhiteBox" width="35%"><b>Comments</b></td>
			<td class="WhiteBoxRightEnd" width="65%"><c:out value="${tm.comments}"/>&nbsp;</td>
		</tr>

		<c:set var="tmId" value="${tm.id}"/>
		<c:set var="gene" value="${targetedModGeneMap[tmId]}"/>
		<c:if test="${not empty gene}">
	        <tr>
	            <td class="GreyBox" width="35%"><b>Gene Info</b></td>
	            <td class="GreyBoxRightEnd" width="65%">
	            <c:out value="${gene.taxon.abbreviation}"/>.&nbsp; 
	            <c:out value="${gene.symbol}"/>.&nbsp; 
	            <c:out value="${gene.fullName}"/>&nbsp;
	            </td>
	        </tr>
	        <tr>
	            <td valign="top" class="WhiteBox" width="35%"><b>Sequence ID</b></td>
	            <td class="WhiteBoxRightEnd" width="65%">
						&nbsp;
	            </td>
	        </tr>
	        <tr>
	            <td class="GreyBox" width="35%"><b>Database Links</b></td>
	            <td class="GreyBoxRightEnd" width="65%">&nbsp;
					<A target="_blank" href="http://www.ncbi.nlm.nih.gov/UniGene/clust.cgi?ORG=<c:out value="${gene.taxon.abbreviation}"/>&amp;CID=<c:out value="${gene.clusterId}"/>">UniGene</A>
						&nbsp;|&nbsp;
			        <A target="_blank" href="http://cgap.nci.nih.gov/Genes/GeneInfo?ORG=Mm&CID=<c:out value="${gene.clusterId}"/>">CGAP</A>
	           	</td>
	        </tr>
	        <tr>
	            <td valign="top" class="WhiteBox" width="35%"><b>Gene Ontology</b></td>
	            <td valign="top" class="WhiteBoxRightEnd" width="65%"><font size="-2" color="#666699">Gene classification by the European Bioinformatics Institute, as recorded in GOA (GO Annotation@EBI)</font>
					<ul>
						<c:forEach var="ont" items="${gene.geneOntologyCollection}">
							<li>
							<c:set var="paddedNode"><fmt:formatNumber value="${ont.id}" minIntegerDigits="7" groupingUsed="false" /></c:set>
							<a target="_blank" 
				 				href="http://cgap.nci.nih.gov/Genes/GOBrowser?CMD=open&NODE=<c:out value="${paddedNode}"/>">
				 				<c:out value="${ont.name}"/>
					 		</a></li>
						</c:forEach>
					</ul>
	           	</td>
	        </tr>
	        <tr>
	            <td valign="top" class="GreyBox" width="35%"><b>BioCarta Pathways</b></td>
	            <td valign="top" class="GreyBoxRightEnd" width="65%">
		        <font size="-2" color="#666699">Pathway information courtesy of <A target="_blank" href="http://www.biocarta.com">BioCarta</a></font>
		        <br>
					<ul>
						<c:forEach var="item" items="${gene.pathwayCollection}">
							<li>
							<a target="_blank" 
				 				href="http://cmap.nci.nih.gov/Pathways/BioCarta/<c:out value="${item.name}"/>">
				 				<c:out value="${item.displayValue}"/>
					 		</a></li>
						</c:forEach>
					</ul>
	            </td>
	        </tr>
		</c:if>
	</TABLE>
</td></tr>
<tr>
    <td colspan="2" align="right"><a href="#">Top</a></td>
</tr>
</TABLE>
</c:forEach>
</c:if>
<!-- End Targeted modification-->

<!-- Induced Mutation-->
<c:set var="count" value="0"/>
<c:if test="${not empty inducedMutColl}">
<c:forEach var="im" items="${inducedMutColl}">
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<a name="<c:out value="ind_mut_${count}"/>"/>
	<c:set var="count" value="${count + 1}"/>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
		<tr>
			<td class="formTitle" height="20" colspan="2">
				Induced Mutation - Model:<c:out value="${mdl.modelDescriptor}" escapeXml="false"/>
			</td>
		</tr>
		
        <tr>
            <td class="GreyBox"><b>Inducing Agent Name</b></td>
            <td class="GreyBoxRightEnd">
				<c:choose>
					<c:when test="${empty im.environmentalFactor.name}">
						<c:out value="${im.environmentalFactor.nameUnctrlVocab}"/>&nbsp;
					</c:when>
					<c:otherwise>
			            <c:out value="${im.environmentalFactor.name}"/>&nbsp;
					</c:otherwise>
				</c:choose>
            </td>
        </tr>     
              
        <tr>
            <td class="WhiteBox"><b>Inducing Agent Category</b></td>
            <td class="WhiteBoxRightEnd">
				<c:choose>
					<c:when test="${empty im.environmentalFactor.type}">
						<c:out value="${im.environmentalFactor.typeUnctrlVocab}"/>&nbsp;
					</c:when>
					<c:otherwise>
			            <c:out value="${im.environmentalFactor.type}"/>&nbsp;
					</c:otherwise>
				</c:choose>
            </td>
        </tr>
        
        <tr>
            <td class="GreyBox"><b>CAS Number</b></td>
            <td class="GreyBoxRightEnd">            	     
				<c:if test="${not empty im.environmentalFactor.type}">
			            <a href="http://dtp.nci.nih.gov/dtpstandard/servlet/ChemData?queryHOLD=&searchtype=CAS&chemnameboolean=and&outputformat=html&searchlist=<c:out value="${im.environmentalFactor.casNumber}"/>&Submit=Submit" target="blank"><c:out value="${im.environmentalFactor.casNumber}"/></a>
				</c:if>&nbsp;
            </td>
        </tr>
				
        <tr>
            <td class="WhiteBox"><b>Gene ID</b></td>
            <td class="WhiteBoxRightEnd">
            	<c:choose>
					<c:when test="${empty im.geneId}">
						&nbsp;
					</c:when>
					<c:otherwise>
			            <a href="http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?cmd=search&db=gene&term=<c:out value="${im.geneId}"/>" target="blank"><c:out value="${im.geneId}"/></a>
					</c:otherwise>
				</c:choose>            	
            </td>
        </tr>
				         
        <tr>
            <td class="GreyBox"><b>Description of the induced mutation</b></td>
            <td class="GreyBoxRightEnd"><c:out value="${im.description}"/>&nbsp;</td>
        </tr>
		
		<tr>
			<td class="WhiteBox" width="35%"><b>Mutated Locus/Gene (Observation)</b></td>
			<td class="WhiteBoxRightEnd" width="65%">
				<c:if test="${not empty im.geneticAlterationCollection}">
					<table summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
						<tr>
							<td class="formTitle" width="65%"><b>Mutated Locus/Gene (Observation)</b></td>
							<td class="formTitle" width="35%"><b>Method of Observation</b></td>
						</tr>
						<c:forEach var="gene" items="${im.geneticAlterationCollection}">
							<tr>
					            <td class="WhiteBox"><c:out value="${gene.observation}"/>&nbsp;</td>
					            <td class="WhiteBoxRightEnd"><c:out value="${gene.methodOfObservation}"/>&nbsp;</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>&nbsp;
			</td>
		</tr>
		
        <tr>
			<td class="GreyBox" width="35%"><b>MGI Number</b></td>
			<td class="GreyBoxRightEnd" width="65%">
				<c:if test="${not empty im.mutationIdentifier.mgiNumber}">
					<a target="_blank" href="http://www.informatics.jax.org/javawi2/servlet/WIFetch?page=searchTool&query=MGI:<c:out value="${im.mutationIdentifier.mgiNumber}"/>&selectedQuery=Genes+and+Markers">
					    <c:out value="${im.mutationIdentifier.mgiNumber}"/>
					</a>
			    </c:if>&nbsp;
			</td>
		</tr>
		
		<tr>
			<td class="WhiteBox" width="35%"><b>Comments</b></td>
			<td class="WhiteBoxRightEnd" width="65%"><c:out value="${im.comments}"/>&nbsp;</td>
		</tr>
		
	</TABLE>
</td></tr>
<tr>
    <td colspan="2" align="right"><a href="#">Top</a></td>
</tr>
</TABLE>
</c:forEach>
</c:if>

<!-- Spontaneous Mutation-->
<c:set var="count" value="0"/>
<c:if test="${not empty smc}">
<c:forEach var="sm" items="${smc}">
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<a name="<c:out value="spon_mut_${count}"/>"/>
	<c:set var="count" value="${count + 1}"/>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
		<tr>
			<td class="formTitle" height="20" colspan="2">
				Spontaneous Mutation - Model:<c:out value="${mdl.modelDescriptor}" escapeXml="false"/>
			</td>
		</tr>
        <tr>
            <td class="GreyBox" width="35%"><b>Gene</b></td>
            <td class="GreyBoxRightEnd" width="65%"><c:out value="${sm.name}"/>&nbsp;</td>
        </tr>
		<tr>
			<td class="WhiteBox" width="35%"><b>Mutated Locus/Gene (Observation)</b></td>
			<td class="WhiteBoxRightEnd" width="65%">
				<c:if test="${not empty sm.geneticAlterationCollection}">
					<table summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
						<tr>
							<td class="formTitle" width="65%"><b>Mutated Locus/Gene (Observation)</b></td>
							<td class="formTitle" width="35%"><b>Method of Observation</b></td>
						</tr>
						<c:forEach var="gene" items="${sm.geneticAlterationCollection}">
							<tr>
					            <td class="WhiteBox"><c:out value="${gene.observation}"/>&nbsp;</td>
					            <td class="WhiteBoxRightEnd"><c:out value="${gene.methodOfObservation}"/>&nbsp;</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>&nbsp;
			</td>
		</tr>
        <tr>
			<td class="GreyBox" width="35%"><b>MGI Number</b></td>
			<td class="GreyBoxRightEnd" width="65%">
				<c:if test="${not empty sm.mutationIdentifier.mgiNumber}">
					<a href="http://www.informatics.jax.org/javawi2/servlet/WIFetch?page=searchTool&query=MGI:<c:out value="${sm.mutationIdentifier.mgiNumber}"/>&selectedQuery=Genes+and+Markers">
					    <c:out value="${sm.mutationIdentifier.mgiNumber}"/>
					</a>
			    </c:if>&nbsp;
			</td>
		</tr>
		<tr>
			<td class="WhiteBox" width="35%"><b>Comments</b></td>
			<td class="WhiteBoxRightEnd" width="65%"><c:out value="${sm.comments}"/>&nbsp;</td>
		</tr>
	</TABLE>
</td></tr>
<tr>
    <td colspan="2" align="right"><a href="#">Top</a></td>
</tr>
</TABLE>
</c:forEach>
</c:if>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
    <% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.GENETIC_DESCRIPTION); %>
    <%@ include file="/jsp/includeComments.jsp" %>
    </TABLE>
</TABLE>
<!-- End Induced Mutation-->

<%@ include file="/jsp/footer.jsp" %>