<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	

<bean:define id="mdl" name="animalmodel"/>
<bean:define id="tgc" name="transgeneColl"/>
<bean:define id="tmc" name="targetedModColl"/>
<bean:define id="imc" name="inducedMutColl"/>
<bean:define id="gsc" name="genomicSegColl"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">

		<tr>
			<td class="formTitle" height="20" colspan="3">
			Genetic Description - Model:
			<bean:write name="mdl" property="modelDescriptor"/>
			</td>
		</tr>

		<tr>
			<td class="GreyBox" width="20%"><b>Engineered Transgene</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				<ul>
					<logic:iterate id="eg" name="tgc" indexId="idx">
					<li>
						<html:link action="viewEngineeredTransgene">
							<bean:write name="eg" property="name"/>
						</html:link>
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
						<html:link action="viewEngineeredTransgene">
							<bean:write name="eg" property="cloneDesignator"/>
						</html:link>
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
						<html:link action="viewEngineeredTransgene">
							<bean:write name="eg" property="name"/>
						</html:link>
					</logic:iterate>
				</ul>&nbsp;
			</td>			
		</tr>
		
		<tr>
			<td class="WhiteBox" width="20%"><b>Induced Mutation</b></td>
			<td class="WhiteBoxRightEnd" width="80%">
				<ul>
					<logic:iterate id="eg" name="mdl" property="engineeredGeneCollection" indexId="idx">
					<% if ( eg instanceof gov.nih.nci.camod.domain.InducedMutation ) { %>
					<li>
						<html:link action="viewEngineeredTransgene">
							<c:choose>
								<c:when test="${empty eg.environmentalFactor.name}">
									<c:out value="${eg.environmentalFactor.nameUnctrlVocab}"/>
								</c:when>
								<c:otherwise>
						            <c:out value="${eg.environmentalFactor.name}"/>
								</c:otherwise>
							</c:choose>
						</html:link>
					<%}%>
					</logic:iterate>
				</ul>&nbsp;
			</td>			
		</tr>
		
		<tr>
			<td class="GreyBox" width="20%"><b>Spontaneous Mutation</b></td>
			<td class="GreyBoxRightEnd" width="80%">
<%      
	final List spc = ((AnimalModel)mdl).getSpontaneousMutationCollection();
	final int cc2 = (spc != null)?spc.size():0;
	System.out.println("Spon. mut. coll size()=" + cc2);
%>
		<% if ( cc2 > 0 ) { %>
				<ul>
					<logic:iterate id="sm" name="mdl" property="spontaneousMutationCollection" indexId="idx">
					<li><html:link action="viewSpontaneousMutation">
							<bean:write name="sm" property="name"/>
					</html:link>
					</logic:iterate>
				</ul>&nbsp;
		<%}%>
			</td>			
		</tr>			
	</TABLE>
	
</td></tr>
</TABLE>

<!-- Transgene -->
<% int cc = ((List)tgc).size(); %>
<% if ( cc > 0 ) { %>
<c:forEach var="tg" items="${tgc}">
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
		<tr>
			<td class="formTitle" height="20" colspan="2">
				Engineered Transgene - Model:
				<c:out value="${mdl.modelDescriptor}"/>
			</td>
		</tr>

		<c:choose>
			<c:when test="${not empty tg.locationOfIntegration}">
				<tr>
					<td class="GreyBox" width="35%">
					<b>Transgene Integration</b></td>
					<td class="GreyBoxRightEnd" width="65%">&nbsp;
					Targeted
					</td>
				</tr>
				<tr>
					<td class="WhiteBox" width="35%"><b>Location of Integration</b></td>
					<td class="WhiteBoxRightEnd" width="65%">&nbsp;
					<c:out value="${tg.locationOfIntegration}"/>
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td class="GreyBox" width="35%">
					<b>Transgene Integration</b></td>
					<td class="GreyBoxRightEnd" width="65%">&nbsp;
					Random
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
		
		<tr>
			<td class="GreyBox" width="35%"><b>Transgene</b></td>
			<td class="GreyBoxRightEnd" width="65%">&nbsp;
			<c:out value="${tg.name}"/>
			</td>
		</tr>
		<tr>
			<td class="WhiteBox" width="35%"><b>Transgene Species of Origin</b></td>
			<td class="WhiteBoxRightEnd" width="65%">&nbsp;
			<c:out value="${tg.taxonCollection[0].scientificName}"/>
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
						<td class="WhiteBoxRightEnd" width="65%">&nbsp;
							<c:out value="${rem.taxon.scientificName}"/>&nbsp;
							<c:out value="${rem.taxon.ethnicityStrain}"/>
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
			</ul>
			</td>
		</tr>

		<tr>
			<td class="GreyBox" width="35%"><b>Conditional Type</b></td>
			<td class="GreyBoxRightEnd" width="65%"><c:out value="${tg.conditionality.conditionedBy}"/>&nbsp;</td>
		</tr>
		<tr>
			<td class="WhiteBox" width="35%"><b>Conditional Description</b></td>
			<td class="WhiteBoxRightEnd" width="65%"><c:out value="${tg.conditionality.description}"/>&nbsp;</td>
		</tr>

		<c:if test="${not empty tg.image.id}">
		<tr>
			<td class="GreyBox" width="35%"><b>Construct Map ( Image )</b></td>
			<td class="GreyBoxRightEnd" width="65%">
			<a href='javascript: rs("commentWin","zoomifyImage.jsp",1025,625);'>
			<Img src="http://caimage.nci.nih.gov/lizardtech/Model_Images/GeneticConstruct/<c:out value="${tg.image.id}"/>.jpg" 
				width=50 height=50 border=0
				alt="Click on the image to open in a new Browser window"></a>
			<br/>( Click to View )
			</td>
		</tr>

		<tr>
			<td class="WhiteBox" width="35%"><b>Title of the construct</b></td>
			<td class="WhiteBoxRightEnd" width="65%"><c:out value="${tg.image.title}"/>&nbsp;</td>
		</tr>
		<tr>
			<td class="GreyBox" width="35%"><b>Description of the construct</b></td>
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
			</td>
		</tr>	
		
        <tr>
			<td class="GreyBox" width="35%"><b>MGI Number</b></td>
			<td class="GreyBoxRightEnd" width="65%">&nbsp;
			<c:out value="${tg.mutationIdentifier.numberMGI}"/>
			</td>			
		</tr>
		<tr>
			<td class="WhiteBox" width="35%"><b>Comments</b></td>
			<td class="WhiteBoxRightEnd" width="65%"><c:out value="${tg.comments}"/>&nbsp;</td>
		</tr>
	</TABLE>
	
</td></tr>
</TABLE>
</c:forEach>
<%}%>
<!-- End Transgene -->


<!-- Genomic Segment -->
<c:if test="${not empty genomicSegColl}">
<c:forEach var="gs" items="${genomicSegColl}">
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
		<tr>
			<td class="formTitle" height="20" colspan="2">
				Genomic Segment - Model:<c:out value="${mdl.modelDescriptor}"/>
			</td>
		</tr>
		<c:choose>
			<c:when test="${not empty gs.locationOfIntegration}">
				<tr>
					<td class="GreyBox" width="35%">
					<b>Transgene Integration</b></td>
					<td class="GreyBoxRightEnd" width="65%">&nbsp;
					Targeted
					</td>
				</tr>
				<tr>
					<td class="WhiteBox" width="35%"><b>Location of Integration</b></td>
					<td class="WhiteBoxRightEnd" width="65%">&nbsp;
					<c:out value="${gs.locationOfIntegration}"/>
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td class="GreyBox" width="35%">
					<b>Transgene Integration</b></td>
					<td class="GreyBoxRightEnd" width="65%">&nbsp;
					Random
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
								<c:out value="${segtype.nameUnctrlVocab}"/>
							</c:when>
							<c:otherwise>
								<c:out value="${segtype.name}"/>
							</c:otherwise>
						</c:choose>
					</li>
					</c:forEach>
				</ul>
			</td>			
		</tr>

		<tr>
			<td class="WhiteBox" width="35%"><b>Designator</b></td>
			<td class="WhiteBoxRightEnd" width="65%">&nbsp;<c:out value="${gs.cloneDesignator}"/></td>			
		</tr>		
		
		<tr>
			<td class="GreyBox" width="35%"><b>Segment Size</b></td>
			<td class="GreyBoxRightEnd" width="65%">&nbsp;<c:out value="${gs.segmentSize}"/></td>
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
			</td>
		</tr>
	
		<c:if test="${not empty gs.image.id}">
		<tr>
			<td class="GreyBox" width="35%"><b>Construct Map ( Image )</b></td>
			<td class="GreyBoxRightEnd" width="65%">
			<a href='javascript: rs("commentWin","zoomifyImage.jsp",1025,625);'>
			<Img src="http://caimage.nci.nih.gov/lizardtech/Model_Images/GeneticConstruct/<c:out value="${gs.image.id}"/>.jpg" 
				width=50 height=50 border=0
				alt="Click on the image to open in a new Browser window"></a>
			<br/>( Click to View )
			</td>
		</tr>

		<tr>
			<td class="WhiteBox" width="35%"><b>Title of the construct</b></td>
			<td class="WhiteBoxRightEnd" width="65%"><c:out value="${gs.image.title}"/>&nbsp;</td>
		</tr>
		<tr>
			<td class="GreyBox" width="35%"><b>Description of the construct</b></td>
			<td class="GreyBoxRightEnd" width="65%"><c:out value="${gs.image.description}"/>&nbsp;</td>
		</tr>
		</c:if>

        <tr>
			<td class="WhiteBox" width="35%"><b>MGI Number</b></td>
			<td class="WhiteBoxRightEnd" width="65%">&nbsp;<c:out value="${gs.mutationIdentifier.numberMGI}"/></td>
		</tr>

		<tr>
			<td class="GreyBox" width="35%"><b>Comments</b></td>
			<td class="GreyBoxRightEnd" width="65%"><c:out value="${gs.comments}"/>&nbsp;</td>
		</tr>
	</TABLE>
</td></tr>
</TABLE>
</c:forEach>
</c:if>
<!-- End Genomic Segment -->

<!-- Targeted modification-->
<c:if test="${not empty targetedModColl}">
<c:forEach var="tm" items="${targetedModColl}">
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
		<tr>
			<td class="formTitle" height="20" colspan="2">
				Targeted Modification - Model:<c:out value="${mdl.modelDescriptor}"/>
			</td>
		</tr>
        <tr>
            <td class="GreyBox"><b>Gene</b></td>
            <td class="GreyBoxRightEnd">&nbsp;<c:out value="${tm.name}"/></td>
        </tr>
                 
        <tr>
            <td class="resultsBoxWhite"><b>Modification Type</b></td>
            <td class="resultsBoxWhiteEnd">&nbsp;
				<c:choose>
					<c:when test="${empty tm.modificationTypeCollection}">
						<c:out value="${tm.modTypeUnctrlVocab}"/>
					</c:when>
					<c:otherwise>
					<li>
						<c:forEach var="modType" items="${tm.modificationTypeCollection}">
							<li><c:out value="${modType.name}"/></li>
						</c:forEach>
					</li>
					</c:otherwise>
				</c:choose>
            </td>
        </tr>

        <tr>
            <td class="GreyBox"><b>Genetic Background - Donor</b></td>
            <td class="GreyBoxRightEnd">&nbsp;
            	<c:out value="${tm.esCellLineName}"/>
            </td>
        </tr>
        <tr>
            <td class="resultsBoxWhite"><b>Genetic Background - Recipient</b></td>
            <td class="resultsBoxWhiteEnd">&nbsp;<c:out value="${tm.blastocystName}"/></td>
        </tr>

		<tr>
			<td class="GreyBox" width="35%"><b>Conditional Type</b></td>
			<td class="GreyBoxRightEnd" width="65%"><c:out value="${tm.conditionality.conditionedBy}"/>&nbsp;</td>
		</tr>
		<tr>
			<td class="WhiteBox" width="35%"><b>Conditional Description</b></td>
			<td class="WhiteBoxRightEnd" width="65%"><c:out value="${tm.conditionality.description}"/>&nbsp;</td>
		</tr>

		<tr>
			<td class="GreyBox" width="35%"><b>Gene Function</b></td>
			<td class="GreyBoxRightEnd" width="65%">
			<ul>
			<c:forEach var="gf" items="${tm.geneFunctionCollection}">
				<li><c:out value="${gf.function}"/></li>
			</c:forEach>
			</ul>
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
			</td>
		</tr>

		<c:if test="${not empty tm.image.id}">
		<tr>
			<td class="GreyBox" width="35%"><b>Construct Map ( Image )</b></td>
			<td class="GreyBoxRightEnd" width="65%">
			<a href='javascript: rs("commentWin","zoomifyImage.jsp",1025,625);'>
			<Img src="http://caimage.nci.nih.gov/lizardtech/Model_Images/GeneticConstruct/<c:out value="${tm.image.id}"/>.jpg" 
				width=50 height=50 border=0
				alt="Click on the image to open in a new Browser window"></a>
			<br/>( Click to View )
			</td>
		</tr>
		<tr>
			<td class="WhiteBox" width="35%"><b>Title of the construct</b></td>
			<td class="WhiteBoxRightEnd" width="65%"><c:out value="${tm.image.title}"/>&nbsp;</td>
		</tr>
		<tr>
			<td class="GreyBox" width="35%"><b>Description of the construct</b></td>
			<td class="GreyBoxRightEnd" width="65%"><c:out value="${tm.image.description}"/>&nbsp;</td>
		</tr>
		</c:if>

		<tr>
			<td class="WhiteBox" width="35%"><b>Comments</b></td>
			<td class="WhiteBoxRightEnd" width="65%"><c:out value="${tm.comments}"/>&nbsp;</td>
		</tr>

		<c:set var="tmId" value="${tm.id}"/>
		<c:set var="gene" value="${targetedModGeneMap[tmId]}"/>
		<c:if test="${not empty gene}">
	        <tr>
	            <td class="resultsBoxGrey"><b>Gene Info</b></td>
	            <td class="resultsBoxGreyEnd">&nbsp;
	            <c:out value="${gene.taxon.abbreviation}"/>.&nbsp; 
	            <c:out value="${gene.symbol}"/>.&nbsp; 
	            <c:out value="${gene.fullName}"/>
	            </td>
	        </tr>
	        <tr>
	            <td valign="top" class="resultsBoxWhite"><b> Sequence ID</b></td>
	            <td class="resultsBoxWhiteEnd">
					<ul>
						<c:forEach var="seq" items="${gene.nucleicAcidSequenceCollection}">
							<li>
							<a target="_blank" 
				 				href="http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?db=Nucleotide&amp;CMD=Search&amp;term=<c:out value="${seq.accessionNumber}"/>">
				 				<c:out value="${seq.accessionNumber}"/>
				 		</A>&nbsp;</li>
						</c:forEach>
					</ul>
	            </td>
	        </tr>
	        <tr>
	            <td class="resultsBoxGrey"><b>Database Links</b></td>
	            <td class="resultsBoxGreyEnd">&nbsp;
					<A target="_blank" href="http://www.ncbi.nlm.nih.gov/UniGene/clust.cgi?ORG=<c:out value="${gene.taxon.abbreviation}"/>&amp;CID=<c:out value="${gene.clusterId}"/>">UniGene</A>
						&nbsp;|&nbsp;
			        <A target="_blank" href="http://cgap.nci.nih.gov/Genes/GeneInfo?ORG=Mm&CID=<c:out value="${gene.clusterId}"/>">CGAP</A>
	           	</td>
	        </tr>
	        <tr>
	            <td class="resultsBoxWhite"><b>Function(s) of Targeted Gene</b></td>
	            <td class="resultsBoxWhiteEnd">&nbsp;TBD
	            </td>
	        </tr>
	        <tr>
	            <td class="resultsBoxGrey"><b>Conditional Type</b></td>
	            <td class="resultsBoxGreyEnd">&nbsp;TBD
	            </td>
	        </tr>
	        <tr>
	            <td valign="top" class="resultsBoxWhite"><b>Gene Ontology</b></td>
	            <td valign="top" class="resultsBoxWhiteEnd"><font size="-2" color="#666699">Gene classification by the European Bioinformatics Institute, as recorded in GOA (GO Annotation@EBI)</font>
					<ul>
						<c:forEach var="ont" items="${gene.geneOntologyCollection}">
							<li>
							<a target="_blank" 
				 				href="http://cgap.nci.nih.gov/Genes/GOBrowser?CMD=open&NODE=">
				 				<c:out value="${ont.name}"/>
					 		</a></li>
						</c:forEach>
					</ul>
	           	</td>
	        </tr>
	        <tr>
	            <td valign="top" class="resultsBoxGrey"><b>BioCarta Pathways</b></td>
	            <td valign="top" class="resultsBoxGreyEnd">
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
	        <tr>
	            <td valign="top" class="resultsBoxWhite"><b>Libraries and Tissues (from EST data)</b></td>
	            <td valign="top" class="resultsBoxWhiteEnd">TBD
	            </td>
	        </tr>
	        <tr>
	            <td valign="top" class="resultsBoxGrey"><b>Protein Similarities (from UniGene)</b></td>
	            <td valign="top" class="resultsBoxGreyEnd">TBD
	           	</td>
	        </tr>
		</c:if>
	</TABLE>
</td></tr>
</TABLE>
</c:forEach>
</c:if>
<!-- End Targeted modification-->

<!-- Induced Mutation-->
<c:if test="${not empty inducedMutColl}">
<c:forEach var="im" items="${inducedMutColl}">
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
		<tr>
			<td class="formTitle" height="20" colspan="2">
				Induced Mutation - Model:<c:out value="${mdl.modelDescriptor}"/>
			</td>
		</tr>
        <tr>
            <td class="GreyBox"><b>Description of the induced mutation</b></td>
            <td class="GreyBoxRightEnd">&nbsp;<c:out value="${im.description}"/></td>
        </tr>
                 
        <tr>
            <td class="resultsBoxWhite"><b>Inducing Agent Category</b></td>
            <td class="resultsBoxWhiteEnd">&nbsp;
				<c:choose>
					<c:when test="${empty im.environmentalFactor.type}">
						<c:out value="${im.environmentalFactor.typeUnctrlVocab}"/>
					</c:when>
					<c:otherwise>
			            <c:out value="${im.environmentalFactor.type}"/>
					</c:otherwise>
				</c:choose>
            </td>
        </tr>
                 
        <tr>
            <td class="resultsBoxGrey"><b>Inducing Agent Name</b></td>
            <td class="resultsBoxGreyEnd">&nbsp;
				<c:choose>
					<c:when test="${empty im.environmentalFactor.name}">
						<c:out value="${im.environmentalFactor.nameUnctrlVocab}"/>
					</c:when>
					<c:otherwise>
			            <c:out value="${im.environmentalFactor.name}"/>
					</c:otherwise>
				</c:choose>
            </td>
        </tr>

		<c:if test="${not empty im.geneticAlterationCollection}">
	        <tr>
				<td class="greySubTitle" align="center"><b>Mutated Locus/Gene (Observation)<b></td>
				<td class="greySubTitle" align="center"><b>Method of Observation<b></td>
	        </tr>
			<c:forEach var="gene" items="${im.geneticAlterationCollection}">
		        <tr>
		            <td class="resultsBoxWhite"><c:out value="${gene.observation}"/></td>
		            <td class="resultsBoxWhiteEnd"><c:out value="${gene.methodOfObservation}"/></td>
		        </tr>
			</c:forEach>
		</c:if>
        <tr>
			<td class="GreyBox" width="35%"><b>MGI Number</b></td>
			<td class="GreyBoxRightEnd" width="65%">&nbsp;<c:out value="${im.mutationIdentifier.numberMGI}"/></td>
		</tr>
		<tr>
			<td class="WhiteBox" width="35%"><b>Comments</b></td>
			<td class="WhiteBoxRightEnd" width="65%"><c:out value="${im.comments}"/>&nbsp;</td>
		</tr>
	</TABLE>
</td></tr>
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