<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	

<bean:define id="mdl" name="animalmodel"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" 
			align="center" width="100%">	
			<tr>
				<td class="formTitle" height="20" colspan="2">
				Cell Lines - Model:
				<c:out value="${mdl.modelDescriptor}"/></td>				
			</tr>
			<c:forEach var="p" items="${mdl.cellLineCollection}" 
				       varStatus="stat">
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
			<tr>
				<td class="formTitleBlue" height="20" colspan="2">
				<c:out value="${p.name}"/>
				</td>
			</tr>
			<tr>
				<td class="greySubTitleLeft" width="25%">Name of Cell Line</td>
				<td class="<c:out value="${tdClass}"/>End">
					<c:out value="${p.name}"/>
				</td>
			</tr>
			<tr>
				<td class="greySubTitleLeft" width="25%">Organ / Tissue</td>
				<td class="<c:out value="${tdClass}"/>End">&nbsp;
					<c:out value="${p.organ.conceptCode}"/>
				</td>
			</tr>
			<tr>
				<td class="greySubTitleLeft" width="25%">Experiment</td>
				<td class="<c:out value="${tdClass}"/>End">
					<c:out value="${p.experiment}"/>
				</td>
			</tr>
			<tr>
				<td class="greySubTitleLeft" width="25%">Results</td>
				<td class="<c:out value="${tdClass}"/>End">
					<c:out value="${p.results}"/>
				</td>
			</tr>
			<tr>
				<td class="greySubTitleLeft" width="25%">Comments</td>
				<td class="<c:out value="${tdClass}"/>End">&nbsp;
					<c:out value="${p.comments}"/>
				</td>
			</tr>
			<tr><td colspan="2">&nbsp;</td></tr>
			</c:forEach>
			<tr>
				<td class="WhiteBoxEnd" width="100%" colspan="2">
				<a href='javascript: rs("commentWin","submitComment.jsp",415,250);'><IMG src="images/comment.gif" border=0 align=middle> <b>Place your comment here</b></a></td>
			</tr>		
			</TABLE>
		</td></tr></TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>