<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<bean:define id="mdl" name="animalmodel"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="center" width="100%">	

			<tr>
				<td class="formTitle" height="20">
				Microarrays - Model:
				<c:out value="${mdl.modelDescriptor}"/></td>
			</tr>			
			
			<tr>
				<td class="resultsBoxWhiteEnd">Microarray data is stored in caArray.</td> 
			</tr>

			<tr>
				<td class="resultsBoxGreyEnd">
                <ul>
					<bean:define id="maColl" name="mdl" property="microArrayDataCollection"/>
					<c:forEach var="ma" items="${maColl}">
	                	<li>
	                	<a href="caarray-db.nci.nih.gov">
						<c:out value="${ma.experimentName}"/>
	                	</a>
	                	</li>
					</c:forEach>
                </ul>
				</td>
			</tr>
			
			<tr>
				<td class="WhiteBox" width="100%" colspan="9">
				<a href='javascript: rs("commentWin","submitComment.jsp",415,250);'><IMG src="images/comment.gif" border=0 align=middle>
				<b>Place your comment here</b></a>
				</td>
			</tr>
			
			</TABLE>
		</td>
		</tr>
		</TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>