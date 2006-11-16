<%
/*
 * $Id: adminEditModels.jsp,v 1.6 2006-11-16 13:04:47 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2006/11/10 22:02:27  pandyas
 * Format change - addded &nbsp; before help icon
 *
 * Revision 1.4  2006/10/23 14:15:25  pandyas
 * removed variables not used in final version of jsp
 *
 * Revision 1.3  2006/10/17 16:08:12  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.2  2006/08/17 15:38:09  pandyas
 * updated on-line help from Robohelp to ePublisher - added link
 *
 * Revision 1.1  2005/11/18 21:07:44  georgeda
 * Defect #130, added superuser
 *
 *
 */
%>
<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<%@ page import="gov.nih.nci.camod.Constants" %>
<%@ page buffer="32kb"%>

<!-- adminEditModels.jsp -->
<!-- Main Content Begins -->
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" width="100%">
		
		<tr>
			<td class="formMessage" colspan="3">
				<logic:messagesPresent>
				  <ul>
				    <font color="red">
				      <html:messages id="error">
				        <li><%=error %></li>
				      </html:messages>
				    </font>
				  </ul>
				</logic:messagesPresent>
			</td>
		</tr>
		<tr>
		    <td class="formTitle" height="20" colspan="4">Edit Models (SuperUser)&nbsp;
		    	<camod:cshelp topic="screening_model_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>		
		</tr>
		<html:form action="AdminEditModelsPopulateAction">
		    
			    <td class=resultsBoxGreyNoEnd>			
			        <html:select property="currentState">
			            <html:options name="<%=Constants.Dropdowns.CURATIONSTATESDROP%>"/>
		            </html:select>
			    </td>
		    
		
	        <td  class="resultsBoxGreyNoStart" align="right" colspan="3">
				<TABLE cellpadding="4" cellspacing="0" border="0">	
			        <html:submit styleClass="actionButton">
					    <bean:message key="button.submit"/>
				    </html:submit>	
				</TABLE>
			</td>
		</html:form>
	 </TABLE>
	<br>
	
    <c:if test="${not empty adminModelSearchResults}">
	<display:table id="row" name="${sessionScope.adminModelSearchResults}"
      pagesize = "15"
	  cellpadding="5" 
	  cellspacing="0" 
	  border="1"
 	  width = "100%"> 	 

 	    <display:column title="No." >
 	        <c:out value="${row_rowNum}"/>
 	    </display:column> 
 	    <display:column title="Duplicate" >
	             <center>
	             	<c:set var="dupLink" value="return confirm('Are you sure you want to duplicate this record (${row.modelDescriptor})?');"/>   
	                <c:set var="uri" value="/camod/DuplicateAdminAnimalModelAction.do?method=duplicate&aModelID=${row.modelId}"/>
	                <a href='<c:out value="${uri}"/>' onclick='<c:out value="${dupLink}"/>' ><IMG src="/camod/images/dupRecord.gif" border=0></a>  
	       	     </center>     
 	    </display:column>
		<display:column href="/camod/SubmitAction.do?method=setModelConstants&" paramId="aModelID" paramProperty="id" title="Model Descriptor" >
			<c:out escapeXml="false" value="${row.modelDescriptor}"/>
		</display:column>
		<display:column title="Submitter's Name">
			<c:out escapeXml="false" value="${row.submitterName}"/>
		</display:column>     
		<display:column title="Entered on">
			<c:out value="${row.submittedDate}"/>
		</display:column>
		<c:choose>
			<c:when test="${row.state == 'Inactive'}">
			</c:when>
		<c:otherwise>
			<display:column title="Inactivate">
	         	<center>
	             	<c:set var="inactiveLink" value="return confirm('Are you sure you want to inactivate this record (${row.modelDescriptor})?');"/>	         	
				    <c:set var="uri" value="ChangeAnimalModelToInactiveAction.do?&aEvent=inactivate&aModelID=${row.modelId}"/>
				         <a href='<c:out value="${uri}"/>' onclick='<c:out value="${inactiveLink}"/>' ><IMG src="/camod/images/remove.gif" border=0></a>
				 </center>
	       	 	</display:column>
			</c:otherwise>
		</c:choose>            
	</display:table>
	</c:if>
    </td></tr>
   
</TABLE>	

<%@ include file="/jsp/footer.jsp" %>





