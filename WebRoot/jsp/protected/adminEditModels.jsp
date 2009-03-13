<%
/*
 * $Id: adminEditModels.jsp,v 1.12 2009-03-13 15:02:40 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.11  2008/08/12 19:24:54  pandyas
 * Fixed #12108  	Admin - View Model Assignment is not working
 *
 * Revision 1.10  2007/09/07 16:44:08  pandyas
 * Removed duplicate on adminEditModels as per Ulli request
 *
 * Revision 1.9  2007/09/07 16:10:55  pandyas
 * Modified button label for model id search in admin edit models filter
 *
 * Revision 1.8  2007/08/07 15:40:05  pandyas
 * Fixed clear button on adminEditModels.jsp
 *
 * Revision 1.7  2007/07/31 12:00:10  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.6  2006/11/16 13:04:47  pandyas
 * Modified IMG SRC location to include complete location (added /camod/...)
 *
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

<%@ page import='gov.nih.nci.camod.Constants.*' %>
<%@ page buffer="32kb"%>
<script type="text/javascript" src="js/prototype-1.4.0.js"></script>
<script type="text/javascript" src="js/scriptaculous.js"></script>
<script type="text/javascript" src="js/ajaxtags-1.2-beta2.js"></script>

<SCRIPT LANGUAGE="JavaScript">	
	function blankModelId() {
        document.curationAssignmentForm.modelId.value = '';
    }
    
	function blankCurrentState() {
        document.curationAssignmentForm.currentState.value = '';
    }    			
</SCRIPT>

<html:form action="SearchAdminAction.do" focus="modelId">

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
		    <td class="formTitle" height="20" colspan="8">Edit Models (SuperUser)&nbsp;
		    	<camod:cshelp topic="screening_model_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>		
		</tr>

		
			<tr>
			    <td class="formRequiredNotice" >&nbsp;</td>
				<td class="formLabel"><label for="field1">Model Id:</label>
				</td>
				<td class="formField" colspan="4">			
					<html:text styleClass="formFieldSized" styleId="modelId" property="modelId" size="20"/>
					 &nbsp;&nbsp;
                	<input class="actionButton" type="submit" value="Search by Model Id" />	
				</td>
			</tr>
			
			<tr>
			    <td class="formRequiredNotice" >&nbsp;</td>
				<td class="formLabel"><label for="field1">Model Name / Model Descriptor: </label>
				</td>
				<td class="formField" >			
					<html:text styleClass="formFieldSized" styleId="modelDescriptor" property="modelDescriptor" size="20"/>
					<ajax:autocomplete baseUrl="/camod/autocomplete.view" source="modelDescriptor" target="modelDescriptor"
	  				parameters="modelDescriptor={modelDescriptor}" className="autocomplete" minimumCharacters="1" />	
				</td>&nbsp;&nbsp;
				
				<td class="formRequiredNotice" width="15">&nbsp;</td>
				<td class="formLabel"><label for="field3">State:</label></td>
				<td class="formField">
			        <html:select styleClass="formFieldSized" size="1" property="currentState">
			            <html:options name="<%=Dropdowns.CURATIONSTATESWITHBLANKDROP%>"/>
		            </html:select>
				</td>				
			</tr>	
			
			<tr>
			    <td class="formRequiredNotice" >&nbsp;</td>
			    <td class="formLabel"><label for="field1">Screener:</label></td>
			    <td class="formField">			
		            <html:select property="screener">
			            <html:options name="<%=Dropdowns.USERSFORSCREENERROLEDROP%>"/>
		            </html:select>
			    </td>&nbsp;&nbsp;
				
			    <td class="formRequiredNotice" >&nbsp;</td>
			    <td class="formLabel"><label for="field1">Editor:</label></td>
			    <td class="formField">			
		            <html:select property="editor">
			            <html:options name="<%=Dropdowns.USERSFOREDITORROLEDROP%>"/>
		            </html:select>
			    </td>				
			</tr>	
			
			<tr>
			    <td class="formRequiredNotice" >&nbsp;</td>
				<td class="formLabel"><label for="field3">External Data Source:</label></td>
				<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="externalSource" >
					<html:options name="<%=Dropdowns.EXTERNALSOURCEQUERYDROP %>" />										
				</html:select>
			    </td>&nbsp;&nbsp;
				
			    <td class="formRequiredNotice" >&nbsp;</td>
				<td class="formLabel"><label for="field2">PI's Name:</label></td>
				
				<td class="formField">				
					<html:select styleClass="formFieldSized" size="1" property="principalInvestigator" >
						<html:options name="<%=Dropdowns.PRINCIPALINVESTIGATORQUERYALLDROP %>" />										
					</html:select>			
				</td>				
			</tr>
			
			<tr>
			    <td class="formRequiredNotice" >&nbsp;</td>
				<td class="formLabel"><label for="field3">Species:</label></td>
				<td class="formField">				
					<html:select property="species">
						<html:optionsCollection name="<%= Dropdowns.NONHUMANSPECIESDROP %>" />										
					</html:select>			
				</td>&nbsp;&nbsp;				
			</tr>						
			
			
		<tr>
			<td colspan="8" align="right">	
				<TABLE cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td align="right">
						  <html:submit styleClass="actionButton" onclick="blankModelId()">
							  Search
						  </html:submit>
					  
						  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton">
						  	  <bean:message key="button.clear"/>
						  </html:submit>						  
						  
				  		</html:form>			
				  		</td>
			  		</tr>
				</TABLE>
			</td>
		</tr>			

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

<SCRIPT LANGUAGE="JavaScript">
	blankCurrentState()();
</SCRIPT>	

<%@ include file="/jsp/footer.jsp" %>





