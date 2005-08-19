<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>

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
					<li><a href="viewEngineeredTransgene.jsp">ETransgene 1</a>
					<li><a href="viewEngineeredTransgene.jsp">ETransgene 2</a>
				</ul>
			</td>			
		</tr>

		<tr>
			<td class="WhiteBox" width="20%"><b>Genomic Segment</b></td>
			<td class="WhiteBoxRightEnd" width="80%">
				<ul>
					<li><a href="viewGenomicSegment.jsp">Seg-1</a>
					<li><a href="viewGenomicSegment.jsp">Seg-2</a>
					<li><a href="viewGenomicSegment.jsp">Seg-3</a>
				</ul>
			</td>			
		</tr>

		<tr>
			<td class="GreyBox" width="20%"><b>Targeted Modification</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				<ul>
					<li><a href="viewTargetedModification.jsp">LiL 12p</a>
					<li><a href="viewTargetedModification.jsp">Kras</a>
					<li><a href="viewTargetedModification.jsp">P-120</a>
				</ul>
			</td>			
		</tr>
		
		<tr>
			<td class="WhiteBox" width="20%"><b>Induced Mutation</b></td>
			<td class="WhiteBoxRightEnd" width="80%">
				<ul>
					<li><a href="viewInducedMutation.jsp">Agent X</a>
				</ul>
			</td>			
		</tr>
		
		<tr>
			<td class="GreyBox" width="20%"><b>Spontaneous Mutation</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				<ul>
					<li><a href="viewSpontaneousMutation.jsp">Mutation X</a>
				</ul>
			</td>			
		</tr>			
		
                <tr>
			<td class="WhiteBox" width="100%" colspan="2"><a href='javascript: rs("commentWin","submitComment.jsp",415,250);'><IMG src="images/comment.gif" border=0 align=middle> <b>Place your comment here</b></a></td>
		</tr>
		
	</TABLE>
	
</td></tr>
</TABLE>


<%@ include file="footer.jsp" %>