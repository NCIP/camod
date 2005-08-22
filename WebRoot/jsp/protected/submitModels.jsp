<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.Constants" %>
<%@ page import="java.util.List" %>

<%      
	List results = (List) request.getSession().getAttribute( Constants.USERMODELLIST );
	int size = results.size();
	System.out.println( "SIZE: " + size );
%>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" width="100%">

		<tr>
			<td class="formTitle" height="20" colspan="4">Submit and Edit Models</td>		
		</tr>

		<tr>
			<td class="resultsBoxGreyEnd">
			
				<TABLE width="100%">
					<tr>
						<td>
							  Welcome back <b><%= session.getAttribute("camod.loggedon.username") %></b>.<br>
						      To edit one of your existing models click on the name of the model.<br>
						      To add a new model select "<html:link action="submitNewModel">Add new Model</html:link>".<br>
							<br>
						      If you are unfamiliar with the submission process please refer to <a href="help.jsp">HELP</a>.<br>
							<br>
						      There are <%= size %> records returned.
						     <br>						    
								<logic:messagesPresent>
								  <html:messages id="errors">
									<%=errors %>
								  </html:messages>
								</logic:messagesPresent>						     			
					      </td>				      
					      <td>
					  	  		<img src="images/submit.gif">  
					      </td>
				      </tr>
			      </TABLE>
			</td>
		</tr>
	</TABLE>

	<br>	

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" width="100%">	
		<tr>
			<td class="formTitleBlue" height="20" colspan="5">Your Models</td>				
		</tr>

		<tr>
			<td class="greySubTitleLeft" width="10%">Duplicate<br>Record</td>
			<td class="greySubTitleLeft" width="40%">Model Descriptor</td>
			<td class="greySubTitleLeft" width="30%">Entered on</td>
			<td class="greySubTitle" width="10%">Remove</td>
		</tr>

		<tr>
			<td class="resultsBoxWhite" width="10%">&nbsp;</td>
			<td class="resultsBoxWhite" width="40%"><html:link action="submitNewModel"><font color=red><b>Add New Model</font></b></html:link></td>
			<td class="resultsBoxWhite" width="30%"><%= new java.util.Date() %></td>
			<td class="resultsBoxWhiteEnd" width="10%">&nbsp;</td>
		</tr>
						
		<% if ( size > 0 ) { %>
		<logic:iterate id="aModel" name="usermodellist" type="AnimalModel">
	         <TR>
	             <td class="resultsBoxGrey" width="10%">
	             	<center>
	             		<html:link action="AnimalModelAction.do?method=duplicateModel" paramId="aModelID" paramName="aModel" paramProperty="id" onclick="return confirm('Are you sure you want to duplicate this record?');"><IMG src="images/dupRecord.gif" border=0></html:link>
	             	</center>
	             </td>
	             <td class="resultsBoxGrey" width="40%">
					<html:link action="SubmitAction.do?method=setModelConstants" paramId="aModelID" paramName="aModel" paramProperty="id"><bean:write name="aModel" property="modelDescriptor" filter="true"/> (<bean:write name="aModel" property="state" filter="true"/>)</html:link>	             
	             </td>
	             <td class="resultsBoxGrey" width="30%">
	                <bean:write name="aModel" property="availability.enteredDate" filter="true"/>
	             </td>
	             <td class="resultsBoxGreyEnd" width="10%">
	                <center>
		                <bean:parameter id="modID" name="aModel" value="id"/>		               
	                	<html:link action="AnimalModelAction.do?method=deleteModel" paramId="aModelID" paramName="aModel" paramProperty="id" onclick="return confirm('Are you sure you want to delete this record?');"><IMG src="images/remove.gif" border=0></html:link>	                	
	                </center>
	             </td>                     
	         </TR>
		</logic:iterate>
	    <%} else { %>
		     <TR>
		  		<TD class="resultsBoxGreyEnd" colspan=4><B><I>No models found!</I></B> 
		   		</TD>
		     </TR>
		<%}%>		
				
	</TABLE>		
</td></tr></TABLE>	

<%@ include file="/jsp/footer.jsp" %>





