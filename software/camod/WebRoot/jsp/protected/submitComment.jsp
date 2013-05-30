<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%@ include file="/common/taglibs.jsp"%>
<%@ page import='gov.nih.nci.camod.Constants' %>
<%@ page import='gov.nih.nci.camod.Constants.*' %>
<%@ page import='gov.nih.nci.camod.webapp.form.CommentsForm' %>

<HTML>
<HEAD>
	<TITLE>Submit a Comment</TITLE>
	<LINK href="styles/styleSheet.css" type=text/css rel=stylesheet>
</HEAD>

<BODY bgcolor="#E3E3EB" onunload="window.close()">

	<TABLE summary="This table is used to format page content" width="100%" height="100%" border="0">
	    <html:form action="AddCommentsAction" focus="remark" >
		<tr>
			<td><b><label for="remark">Comments</label><br>The comment field holds maximum 2000 characters.</b></td>
		</tr>
		<tr>
			<td>
			    <html:textarea styleId="remark" property="remark" name="formdata" cols="40" rows="10"/>			
			</td>
		</tr>
        <html:hidden property="modelId" name="formdata" />
        <html:hidden property="sectionName" name="formdata" />
        
		<tr>
			<td align="center" colspan="2">
				<TABLE summary="This table is used to format page content" cellpadding="4" cellspacing="0" border="0">
				
					  <html:submit styleClass="actionButton">
						  <bean:message key="button.submit"/>
					  </html:submit>
					  
					  <html:cancel styleClass="actionButton">
					  	  <bean:message key="button.cancel"/>
	  				  </html:cancel>
				  
				  		
				</TABLE>
			</td>
		</tr>
		</html:form>	
	</TABLE>	

</BODY>
</HTML>