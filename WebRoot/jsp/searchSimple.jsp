<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<!-- needed for tooltips -->
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>

<script language="JavaScript" src="scripts/EVSTreeScript.js"></script>

<script>
    function blankKeyword() {
        document.searchForm.keyword.value = '';
    }
</script>
<html:form action="SearchSimpleAction.do" focus="keyword">

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">
        <tr>
            <td class="formTitleBlue" height="20" colspan="3">
                Keyword Search:&nbsp;&nbsp;
                <html:text styleClass="formFieldSized" property="keyword" size="45"/>
                &nbsp;&nbsp;
                <input class="actionButton" type="submit" value="Search" />
            </td>
        </tr>
        
        <tr><td>&nbsp;</td></tr>
                
		<tr>
			<td class="formTitleBlue" height="20" colspan="3">Simple Search</td>
			<!-- <td class="formMessage" align="left" FONT="9"><a href="advancedsearch.html">Advanced Search <a> </td> -->
		</tr>


		<tr>
		    <td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field1">Model Name / Model Descriptor  
				<camod:cshelp mapId="simple_search_help" key="SEARCH.MODEL_DESCRIPTOR" image="images/iconHelp.gif" text="Tool Tip Test 1" />
				</label>
			</td>
			<td class="formField">			
					<html:text styleClass="formFieldSized" property="modelDescriptor" size="30"/>
			</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formLabel"><label for="field2">Principal Investigator's Name</label></td>
			
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="piName" >
					<html:options name="<%= Dropdowns.PRINCIPALINVESTIGATORQUERYDROP %>" />										
				</html:select>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formLabel">
				<label for="field2">Site of Lesion/Tumor</label>
				&nbsp;
				<camod:cshelp mapId="simple_search_help" key="SEARCH.SITE_OF_TUMOR" image="images/iconHelp.gif" text="Tool Tip Test 1" />
		  	    <a href="javascript:showTissueTree('searchForm', 'descendants=true;isaFlag=true;preferredName=true;depthLevel=6;roleType=Anatomic_Structure_is_Physical_Part_of')">
				<IMG src="images\selectUP.gif" align=middle border=0>
				</a>
				<INPUT name="organTissueName" type="hidden"/>
		 		<INPUT name="organTissueCode" type="hidden"/>
			</td>
			<td class="formField"><input class="formFieldSized" type="text" disabled="true" name="organ" id="organFieldId" size="25" /></td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field3">Species</label></td>
			<td class="formField">				
				<html:select styleClass="formFieldSized" size="1" property="species" >
					<html:optionsCollection name="<%= Dropdowns.NEWSPECIESDROP %>" />										
				</html:select>				
			</td>
		</tr>
	
		<tr>
			<td align="right" colspan="3">
				<!-- action buttons begins -->
				<TABLE cellpadding="4" cellspacing="0" border="0">
					
					  <html:submit styleClass="actionButton" onclick="blankKeyword()">
						  Search
					  </html:submit>
	  				  
	  				  <html:submit property="<%=Constants.Parameters.ACTION%>" styleClass="actionButton">
					  	  <bean:message key="button.clear"/>
	  				  </html:submit>
	  				  
				  </html:form>			
				  	
				</TABLE>
			</td>
		</tr>
	</TABLE>
	
</td></tr>
</TABLE>	

<%@ include file="/jsp/footer.jsp" %>