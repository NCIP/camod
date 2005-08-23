<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">

		<tr>
			<td class="formTitle" height="20" colspan="3">Genetic Description - Model:PB-p53(R172L)</td>		
		</tr>
		
		<tr>
			<td class="GreyBox" width="20%"><b>Engineered Transgene</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				<ul>
					<li><html:link action="viewEngineeredTransgene">ETransgene 1</html:link>
					<li><html:link action="viewEngineeredTransgene">ETransgene 2</html:link>
				</ul>
			</td>			
		</tr>

		<tr>
			<td class="WhiteBox" width="20%"><b>Genomic Segment</b></td>
			<td class="WhiteBoxRightEnd" width="80%">
				<ul>
					<li><html:link action="viewGenomicSegment">Seg-1</html:link>
					<li><html:link action="viewGenomicSegment">Seg-2</html:link>
					<li><html:link action="viewGenomicSegment">Seg-3</html:link>
				</ul>
			</td>			
		</tr>

		<tr>
			<td class="GreyBox" width="20%"><b>Targeted Modification</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				<ul>
					<li><html:link action="viewTargetedModification">LiL 12p</html:link>
					<li><html:link action="viewTargetedModification">Kras</html:link>
					<li><html:link action="viewTargetedModification">P-120</html:link>
				</ul>
			</td>			
		</tr>
		
		<tr>
			<td class="WhiteBox" width="20%"><b>Induced Mutation</b></td>
			<td class="WhiteBoxRightEnd" width="80%">
				<ul>
					<li><html:link action="viewInducedMutation">Agent X</html:link>
				</ul>
			</td>			
		</tr>
		
		<tr>
			<td class="GreyBox" width="20%"><b>Spontaneous Mutation</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				<ul>
					<li><html:link action="viewSpontaneousMutation">Mutation X</html:link>
				</ul>
			</td>			
		</tr>			
		
                <tr>
			<td class="WhiteBox" width="100%" colspan="2"><a href='javascript: rs("commentWin","submitComment.jsp",415,250);'><IMG src="images/comment.gif" border=0 align=middle> <b>Place your comment here</b></a></td>
		</tr>
		
	</TABLE>
	
</td></tr>
</TABLE>


<%@ include file="/jsp/footer.jsp" %>