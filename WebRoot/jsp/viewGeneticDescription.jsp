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
				&nbsp;
				<ul>
					<logic:iterate id="eg" name="tgc" indexId="idx">
					<li>
						<html:link action="viewEngineeredTransgene">
							<bean:write name="eg" property="name"/>
						</html:link>
					</logic:iterate>
				</ul>
			</td>			
		</tr>

		<tr>
			<td class="WhiteBox" width="20%"><b>Genomic Segment</b></td>
			<td class="WhiteBoxRightEnd" width="80%">
				&nbsp;
				<ul>
					<logic:iterate id="eg" name="gsc" indexId="idx">
					<li>
						<html:link action="viewEngineeredTransgene">
							<bean:write name="eg" property="name"/>
						</html:link>
					</logic:iterate>
				</ul>
			</td>			
		</tr>

		<tr>
			<td class="GreyBox" width="20%"><b>Targeted Modification</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				&nbsp;
				<ul>
					<logic:iterate id="eg" name="tmc" indexId="idx">
					<li>
						<html:link action="viewEngineeredTransgene">
							<bean:write name="eg" property="name"/>
						</html:link>
					</logic:iterate>
				</ul>
			</td>			
		</tr>
		
		<tr>
			<td class="WhiteBox" width="20%"><b>Induced Mutation</b></td>
			<td class="WhiteBoxRightEnd" width="80%">
				&nbsp;
				<ul>
					<logic:iterate id="eg" name="mdl" property="engineeredGeneCollection" indexId="idx">
					<% if ( eg instanceof gov.nih.nci.camod.domain.InducedMutation ) { %>
					<li>
						<html:link action="viewEngineeredTransgene">
							<bean:write name="eg" property="name"/>
						</html:link>
					<%}%>
					</logic:iterate>
				</ul>
			</td>			
		</tr>
		
		<tr>
			<td class="GreyBox" width="20%"><b>Spontaneous Mutation</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				&nbsp;
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
				</ul>
		<%}%>
			</td>			
		</tr>			

        <tr>
			<td class="WhiteBox" width="100%" colspan="2"><a href='javascript: rs("commentWin","submitComment.jsp",415,250);'><IMG src="images/comment.gif" border=0 align=middle> <b>Place your comment here</b></a></td>
		</tr>
		
	</TABLE>
	
</td></tr>
</TABLE>

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

		<tr>
			<td class="GreyBox" width="35%">
			<b>Transgene Integration</b></td>
			<td class="GreyBoxRightEnd" width="65%">&nbsp;
			TBD
			</td>
		</tr>

		<tr>
			<td class="WhiteBox" width="35%"><b>Location of Integration</b></td>
			<td class="WhiteBoxRightEnd" width="65%">&nbsp;
			<c:out value="${tg.locationOfIntegration}"/>
			</td>
		</tr>

		<tr>
			<td class="GreyBox" width="35%"><b>Transgene</b></td>
			<td class="GreyBoxRightEnd" width="65%">&nbsp;
			<c:out value="${tg.name}"/>
			</td>
		</tr>
		
		<tr>
			<td class="WhiteBox" width="35%"><b>Transgene Species of Origin</b></td>
			<td class="WhiteBoxRightEnd" width="65%">&nbsp;
			TBD
			</td>
		</tr>

		<bean:define id="remColl" name="tg" property="regulatoryElementCollection"/>
		<c:forEach var="rem" items="${remColl}">
		<tr>
			<td class="GreyBox" width="35%"><b>Transcriptional (Promoter)</b></td>
			<td class="GreyBoxRightEnd" width="65%">&nbsp;
			<c:out value="${rem.name}"/>
			</td>
		</tr>
		
		<tr>
			<td class="WhiteBox" width="35%"><b>Species of Origin for Transcriptional (Promoter)</b></td>
			<td class="WhiteBoxRightEnd" width="65%">&nbsp;
			<c:out value="${rem.taxon.scientificName}"/>&nbsp;
			<c:out value="${rem.taxon.ethnicityStrain}"/>
			</td>
		</tr>	
		</c:forEach>

		<tr>
			<td class="GreyBox" width="35%"><b>Construct Map ( Image )</b></td>
			<td class="GreyBoxRightEnd" width="65%">
			<a href='javascript: rs("commentWin","zoomifyImage.jsp",1025,625);'><Img src="images/image1.jpg" width=50 height=50 border=0></a><br>( Click to view Image )
			</td>			
		</tr>

		<tr>
			<td class="WhiteBox" width="35%"><b>Organ / Tissue Gene is Expressed in and Expression Level</b></td>
			<td class="WhiteBoxRightEnd" width="65%">Heart : not detected<br>Prostate Glands </td>
		</tr>	
		
        <tr>
			<td class="GreyBox" width="35%"><b>MGI Number</b></td>
			<td class="GreyBoxRightEnd" width="65%">&nbsp;
			<c:out value="${tg.mutationIdentifier.numberMGI}"/>
			</td>			
		</tr>
				
		<tr>
			<td class="WhiteBox" width="100%" colspan="2"><a href='javascript: rs("commentWin","submitComment.jsp",415,250);'><IMG src="images/comment.gif" border=0 align=middle> Place your comment here</a></td>
		</tr>
		
	</TABLE>
	
</td></tr>
</TABLE>
</c:forEach>
<%}%>



<%@ include file="/jsp/footer.jsp" %>