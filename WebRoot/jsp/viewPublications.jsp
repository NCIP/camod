<%

/**
 * 
 * $Id: viewPublications.jsp,v 1.37 2008-10-21 06:09:16 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.36  2008/10/16 14:07:28  schroedn
 * Bug #9127 Added RGD number next to the link for RGD
 *
 * Revision 1.35  2008/09/22 16:08:29  schroedn
 * Bug #9127
 * Removed non-breaking space tags in front of RGD
 *
 * Revision 1.34  2008/08/14 19:03:42  schroedn
 * Changes for rgdPubID to rgdPubId
 *
 * Revision 1.33  2008/08/14 06:33:29  schroedn
 * new features added
 *
 * Revision 1.32  2008/02/01 16:28:47  pandyas
 * Removed authors anchor inside detail sections
 *
 * Revision 1.31  2008/01/31 21:18:16  pandyas
 * Removed space before J Number to line up text
 *
 * Revision 1.30  2007/12/04 13:46:57  pandyas
 * Rotate publication data and rename column heading
 *
 * Revision 1.29  2007/10/31 19:33:32  pandyas
 * Fixed #8355 	Add comments field to every submission page
 * Rotated screen to allow for additional field and look better
 *
 * Revision 1.28  2007/06/25 16:37:53  pandyas
 * Fixed code to check for empty ZFIN and J numbers and only display MTB or MGI when a J Number exists
 *
 * Revision 1.27  2007/05/16 18:20:12  pandyas
 * Modified column header for J Number, MGI, MTB, and ZFIN publication links
 * Modified code to get the ZFIN code to work
 *
 * Revision 1.26  2007/05/16 17:48:57  pandyas
 * Fixed ZFIN Pub link on viewPublicaiton screen
 *
 * Revision 1.25  2007/05/07 16:49:04  pandyas
 * Added code to display publication from zfin.org for Zebrafish models
 *
 * Revision 1.24  2006/11/13 20:23:34  pandyas
 * Modified IMG SRC location to include complete location (added /camod/...)
 *
 * Revision 1.23  2006/11/13 20:20:03  pandyas
 * Modified IMG SRC location to include complete location (added /camod/...)
 *
 * Revision 1.22  2006/11/13 17:13:58  pandyas
 * #468 - remove width and height variable for mtb image icon on header of serach results and view pages with Jax data
 *
 * Revision 1.21  2006/11/08 19:11:17  pandyas
 * added MTB logo onto view screens for Jackson Lab models
 *
 * Revision 1.20  2006/10/27 18:31:16  pandyas
 * Fixed fields in display page to allow for html markup
 *
 * Revision 1.19  2006/05/25 18:33:57  pandyas
 * added break after MGI number
 *
 * Revision 1.18  2006/05/25 17:34:04  pandyas
 * added break after jax number
 *
 * Revision 1.17  2006/05/25 16:10:58  pandyas
 * updated hyperlink for jax number in publications with MTB and MGI links
 *
 * Revision 1.16  2006/05/25 16:04:17  pandyas
 * updated hyperlink for jax number in publications with MTB and MGI links
 *
 * Revision 1.15  2006/05/25 15:57:33  pandyas
 * updated hyperlink for jax number in publications with MTB and MGI links
 *
 * Revision 1.14  2006/05/25 15:45:11  pandyas
 * adding hyperlink for jax number in publications with MTB and MGI links
 *
 * Revision 1.13  2006/04/28 19:52:10  schroedn
 * Defect #55
 * Added Keyword Highlighting to this jsp
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
<bean:define id="pubColl" name="publications"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left" width="100%">

	<tr>
		<td class="formTitle" height="20" colspan="3">Publications - Model:
			<camod:highlight><c:out value="${mdl.modelDescriptor}" escapeXml="false"/>
					<c:if test="${mdl.externalSource == 'Jax MTB'}">
							<IMG src="/camod/images/mtb_logo.jpg" >
						</c:if>							
					</camod:highlight>
		</td>
	</tr>			

<!-- Summary table start -->

			<tr>
				<td class="greySubTitleLeft" width="30%">First Author</td>
				<td class="greySubTitleLeft" width="55%">Journal</td>
				<td class="greySubTitleLeftEnd" width="15%">Year</td>
			</tr>
			
		<logic:iterate id="p" name="pubColl" indexId="idx">			
			<tr>
	
				<td class="WhiteBox" width="30%">
				<camod:highlight>
					<a href="<c:out value="#pub_${idx}" escapeXml="false"/>">
						<bean:write name="p" property="authors" filter="false"/>
					</a>
				</camod:highlight>
				</td>
				
				<td class="WhiteBoxRightEnd" width="55%">
					<camod:highlight><c:out value="${p.journal}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
				
				<td class="WhiteBoxRightEnd" width="15%">
					<camod:highlight><c:out value="${p.year}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
		</logic:iterate>

	</TABLE>				
</td></tr>
</TABLE>														
<!-- Summary table end -->

<c:forEach var="p" items="${pubColl}" varStatus="stat">
<TABLE cellpadding="5" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<a name="<c:out value="pub_${count}"/>"/>&nbsp;
	<c:set var="count" value="${count + 1}"/>	
	<TABLE summary="" cellpadding="5" cellspacing="0" border="0" align="left" width="100%">
		<tr>
			<td class="GreyBoxTop" width="30%"><b>Publication Status:</b></td>
			<td class="GreyBoxTopRightEnd" width="65%"><c:out value="${p.publicationStatus.name}" escapeXml="false"/>&nbsp;</td>
		</tr>
			       
		<tr>
			<td class="WhiteBox" width="30%"><b>First Author:</b></td>
			<td class="WhiteBoxRightEnd" width="70%"><c:out value="${p.authors}" escapeXml="false"/>&nbsp;</td>
		</tr>

		<tr>
			<td class="GreyBox" width="30%"><b>References:</b></td>
				<!-- Two choose required so we can check for emtpy ZFIN or J Numbers-->			
				<td class="GreyBoxRightEnd" width="70%">
					<c:choose>
						<c:when test="${not empty p.zfinPubId}">
								<a target="_blank" href="http://zfin.org/cgi-bin/webdriver?MIval=aa-pubview2.apg&OID=<c:out value="${p.zfinPubId}"/>">ZFIN</a>
								<br/>
						</c:when>				
						<c:otherwise>					
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${not empty p.rgdPubId}">
								<c:out value="${p.rgdPubId}"/><br/><a target="_blank" href="http://rgd.mcw.edu/tools/references/references_view.cgi?id=<c:out value="${p.rgdPubId}"/>">RGD</a>
								<br/>
						</c:when>				
						<c:otherwise>					
						</c:otherwise>
					</c:choose>					
					<c:choose>
							<c:when test="${not empty p.jaxJNumber}">										
								<c:out value="${p.jaxJNumber}"/><br/>												
									<a target="_blank" href="http://www.informatics.jax.org/searches/accession_report.cgi?id=<c:out value="${p.jaxJNumber}"/>">MGI</a>
									<br/>
									<a target="_blank" href="http://tumor.informatics.jax.org/mtbwi/referenceDetails.do?accId=<c:out value="${p.jaxJNumber}"/>">MTB</a>
							</c:when>				
						<c:otherwise>						
						</c:otherwise>
					</c:choose>
			</td>					
		</tr>
			       
		<tr>
			<td class="WhiteBox" width="30%"><b>Title:</b></td>
			<td class="WhiteBoxRightEnd" width="70%"><c:out value="${p.title}" escapeXml="false"/>&nbsp;</td>
		</tr>
		
		<tr>
			<td class="GreyBox" width="30%"><b>Journal:</b></td>
			<td class="GreyBoxRightEnd" width="70%"><camod:highlight><c:out value="${p.journal}" escapeXml="false"/>&nbsp;</camod:highlight></td>
		</tr>
			       
		<tr>
			<td class="WhiteBox" width="30%"><b>Year:</b></td>
			<td class="WhiteBoxRightEnd" width="70%"><camod:highlight><c:out value="${p.year}" escapeXml="false"/>&nbsp;</camod:highlight></td>
		</tr>	
		
		<tr>
			<td class="GreyBox" width="30%"><b>Volume:</b></td>
			<td class="GreyBoxRightEnd" width="70%"><camod:highlight><c:out value="${p.volume}" escapeXml="false"/>&nbsp;</camod:highlight></td>
		</tr>

		<tr>
			<td class="WhiteBox" width="30%"><b>Pages:</b></td>
			<td class="WhiteBoxRightEnd" width="70%"><camod:highlight><c:out value="${p.startPage}"/> - </camod:highlight>
					<camod:highlight><c:out value="${p.endPage}"/></camod:highlight></td>
		</tr>
			       
		<tr>
			<td class="GreyBox" width="30%"><b>Abstract in PubMed:</b></td>
			<td class="GreyBoxRightEnd" width="70%">
					<a target="_pubmed" href=" http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?cmd=retrieve&db=pubmed&dopt=abstract&list_uids=<c:out value="${p.pmid}"/>">
					<IMG src="/camod/images/pubmed_70.gif" align="middle">
					</a>
			</td>
		</tr>
				
		<tr>
			<td class="WhiteBox" width="30%"><b>Comment:</b></td>
			<td class="WhiteBoxRightEnd" width="70%"><camod:highlight><c:out value="${p.comments}" escapeXml="false"/>&nbsp;</camod:highlight></td>
		</tr>

	</TABLE>
	</td></tr>
    <td colspan="2" align="right"><a href="#">Top</a></td>	
</TABLE>
	</c:forEach>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
    <% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.PUBLICATIONS); %>
    <%@ include file="/jsp/includeComments.jsp" %>
    </TABLE>
</TABLE>

<%@ include file="/jsp/footer.jsp" %>