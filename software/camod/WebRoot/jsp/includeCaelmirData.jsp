<%

/**
 * 
 * $Id: includeCaelmirData.jsp,v 1.3 2007-12-27 01:17:47 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2007/12/18 13:28:32  pandyas
 * Modified column config to fit with caELMIRE data for integration of Study data
 *
 * Revision 1.1  2007/11/25 23:31:01  pandyas
 * Initial version for feature #8816  	Connection to caELMIR - retrieve data for therapy search page
 *
 *
 */

%>

<%@ page import='net.sf.json.JSONArray' %>
<%@ page import='net.sf.json.JSONObject' %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="gov.nih.nci.camod.Constants" %>
<%@ page import="java.util.Vector" %>

<%@ include file="/common/taglibs.jsp"%>

<bean:define id="ceData" name="caelmirStudyData" />
		<logic:iterate id="ced" name="ceData" indexId="idx">
			<tr>
				<td class="formTitleBlue" height="20" colspan="2">
					  Summary of the caELMIR study: <c:out value="${ced.studyName}" escapeXml="false"/>
				</td>				
			</tr>
			<tr>
				<td class="WhiteBox" width="30%"><b>Study Name:</b></td>
				<td class="WhiteBoxRightEnd" width="70%">
				
					<A href="JavaScript:void window.open('<c:out value="${ced.url}" escapeXml="false"/>');">
					<c:out value="${ced.studyName}" escapeXml="false"/></a>&nbsp;
				</td>
			</tr>
			<tr>
				<td class="GreyBox" width="30%"><b>Hypothesis:</b></td>
				<td class="GreyBoxRightEnd" width="70%"><c:out value="${ced.hypothesis}" escapeXml="false"/>&nbsp;</td>
			</tr>
			<tr>
				<td class="WhiteBox" width="30%"><b>Description:</b></td>
				<td class="WhiteBoxRightEnd" width="70%"><c:out value="${ced.description}" escapeXml="false"/>&nbsp;</td>
			</tr>
			<tr>
				<td class="GreyBox" width="30%"><b>Principal Investigator:</b></td>
				<td class="GreyBoxRightEnd" width="70%"><c:out value="${ced.investigatorName}" escapeXml="false"/>&nbsp;
				(<a href="mailto:<c:out value="${ced.email}"/>">  
										<c:out value="${ced.email}" escapeXml="false"/></a>)				
				&nbsp;<c:out value="${ced.institution}" escapeXml="false"/>
				</td>
			</tr>
			<tr><td><br>
			</td></tr>									
		</logic:iterate>			   	 





			
