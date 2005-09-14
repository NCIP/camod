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
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	

			<tr>
				<td class="formTitle" height="20" colspan="9">
					Publications - Model:
					<c:out value="${mdl.modelDescriptor}"/>
				</td>				
			</tr>			
			
			<tr>
				<td class="greySubTitleLeft" width="10%">Publication Status</td>
				<td class="greySubTitleLeft" width="15%">First Author</td>
				<td class="greySubTitleLeft" width="30%">Title</td>
				<td class="greySubTitleLeft" width="10%">Journal</td>
				<td class="greySubTitleLeft" width="5%">Year</td>
				<td class="greySubTitleLeft" width="10%">Volume</td>
				<td class="greySubTitleLeft" width="10%">Pages</td>
				<td class="greySubTitle" width="10%">Abstract in PubMed</td>
			</tr>
			
			<bean:define id="pubColl" name="mdl" property="publicationCollection"/>
			<c:forEach var="p" items="${pubColl}" varStatus="stat">
			<tr>
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
				<td class="<c:out value="${tdClass}"/>" width="10%">
					<c:out value="${p.publicationStatus.name}"/>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="15%">
					<c:out value="${p.authors}"/>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="30%">
					<c:out value="${p.title}"/>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="10%">
					<c:out value="${p.journal}"/>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="5%">
					<c:out value="${p.year}"/>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="10%">
					<c:out value="${p.volume}"/>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="10%">
					<c:out value="${p.startPage}"/> - 
					<c:out value="${p.endPage}"/> 
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="10%">
					<a href="">
					<IMG src="images/pubmed_70.gif" align="middle">
					</a>
				</td>					
			</tr>
			</c:forEach>
			
			<tr>
				<td class="WhiteBox" width="100%" colspan="9"> <a href='javascript: rs("commentWin","submitComment.jsp",415,250);'><IMG src="images/comment.gif" border=0 align=middle> <b>Place your comment here</b></a></td>
			</tr>
			
			</TABLE>
		</td></tr></TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>