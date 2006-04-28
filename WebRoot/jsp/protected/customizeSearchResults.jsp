<%

/**
 * 
 * $Id: customizeSearchResults.jsp,v 1.1 2006-04-28 19:41:32 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page buffer="32kb"%>
<%@ page import="gov.nih.nci.camod.webapp.form.CustomizeSearchResultsForm" %>
<%@ page import="gov.nih.nci.camod.webapp.util.NewDropdownUtil" %>
<%@ page import="gov.nih.nci.camod.Constants.*" %>
<%@ page import="java.util.List" %>

<script language="JavaScript" src="scripts/global.js"></script>
<script language="JavaScript" src="scripts/initIt.js"></script>

<%
//Check for error messages
String errorMessage = (String) request.getSession().getAttribute( Constants.ERRORMESSAGE );
//reset
request.getSession().setAttribute( Constants.ERRORMESSAGE, null );

%>

<SCRIPT>

	function selectUnselectMatchingOptions(obj,regex,which,only){if(window.RegExp){if(which == "select"){var selected1=true;var selected2=false;}else if(which == "unselect"){var selected1=false;var selected2=true;}else{return;}var re = new RegExp(regex);for(var i=0;i<obj.options.length;i++){if(re.test(obj.options[i].text)){obj.options[i].selected = selected1;}else{if(only == true){obj.options[i].selected = selected2;}}}}}
	function selectMatchingOptions(obj,regex){selectUnselectMatchingOptions(obj,regex,"select",false);}
	function selectOnlyMatchingOptions(obj,regex){selectUnselectMatchingOptions(obj,regex,"select",true);}
	function unSelectMatchingOptions(obj,regex){selectUnselectMatchingOptions(obj,regex,"unselect",false);}
	function sortSelect(obj){var o = new Array();if(obj.options==null){return;}for(var i=0;i<obj.options.length;i++){o[o.length] = new Option( obj.options[i].text, obj.options[i].value, obj.options[i].defaultSelected, obj.options[i].selected) ;}if(o.length==0){return;}o = o.sort(
	function(a,b){if((a.text+"") <(b.text+"")){return -1;}if((a.text+"") >(b.text+"")){return 1;}return 0;});for(var i=0;i<o.length;i++){obj.options[i] = new Option(o[i].text, o[i].value, o[i].defaultSelected, o[i].selected);}}
	function selectAllOptions(obj){for(var i=0;i<obj.options.length;i++){obj.options[i].selected = true;}}
	function moveSelectedOptions(from,to){if(arguments.length>3){var regex = arguments[3];if(regex != ""){unSelectMatchingOptions(from,regex);}}for(var i=0;i<from.options.length;i++){var o = from.options[i];if(o.selected){to.options[to.options.length] = new Option( o.text, o.value, false, false);}}for(var i=(from.options.length-1);i>=0;i--){var o = from.options[i];if(o.selected){from.options[i] = null;}}if((arguments.length<3) ||(arguments[2]==true)){}from.selectedIndex = -1;to.selectedIndex = -1;}
	function copySelectedOptions(from,to){var options = new Object();for(var i=0;i<to.options.length;i++){options[to.options[i].text] = true;}for(var i=0;i<from.options.length;i++){var o = from.options[i];if(o.selected){if(options[o.text] == null || options[o.text] == "undefined"){to.options[to.options.length] = new Option( o.text, o.value, false, false);}}}if((arguments.length<3) ||(arguments[2]==true)){}from.selectedIndex = -1;to.selectedIndex = -1;}
	function moveAllOptions(from,to){selectAllOptions(from);if(arguments.length==2){moveSelectedOptions(from,to);}else if(arguments.length==3){moveSelectedOptions(from,to,arguments[2]);}else if(arguments.length==4){moveSelectedOptions(from,to,arguments[2],arguments[3]);}}
	function copyAllOptions(from,to){selectAllOptions(from);if(arguments.length==2){copySelectedOptions(from,to);}else if(arguments.length==3){copySelectedOptions(from,to,arguments[2]);}}
	function swapOptions(obj,i,j){var o = obj.options;var i_selected = o[i].selected;var j_selected = o[j].selected;var temp = new Option(o[i].text, o[i].value, o[i].defaultSelected, o[i].selected);var temp2= new Option(o[j].text, o[j].value, o[j].defaultSelected, o[j].selected);o[i] = temp2;o[j] = temp;o[i].selected = j_selected;o[j].selected = i_selected;}
	function moveOptionUp(obj){var selectedCount=0;for(i=0;i<obj.options.length;i++){if(obj.options[i].selected){selectedCount++;}}if(selectedCount!=1){return;}var i = obj.selectedIndex;if(i == 0){return;}swapOptions(obj,i,i-1);obj.options[i-1].selected = true;}
	function moveOptionDown(obj){var selectedCount=0;for(i=0;i<obj.options.length;i++){if(obj.options[i].selected){selectedCount++;}}if(selectedCount != 1){return;}var i = obj.selectedIndex;if(i ==(obj.options.length-1)){return;}swapOptions(obj,i,i+1);obj.options[i+1].selected = true;}
	function removeSelectedOptions(from){for(var i=(from.options.length-1);i>=0;i--){var o=from.options[i];if(o.selected){from.options[i] = null;}}from.selectedIndex = -1;}

	function removeAllSelected() {
	    removeSelected(document.forms[0].selectedColumnsToDisplay, document.forms[0].columnsToDisplay);
	    unselectAll();
	}
	
	function selectAll() {
	    select(document.forms[0].selectedColumnsToDisplay);
	    
	}
	
	function unselectAll() {
	    unselect(document.forms[0].selectedColumnsToDisplay);
	}
	
	function select(group)
	{   
	    for (var i=0; i < group.options.length; i++)  
	    {  
	        group.options[i].selected = true;    
	    }
	}
</SCRIPT>

<html:form action="CustomizeSearchResultsAction.do" focus="columnsToDisplay" onsubmit="selectAll()">


										
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	
	<% if ( errorMessage != null ) { %>
		<tr><td>
			<font color="red"><%= errorMessage %></font>							
		</td></tr>
	<%} %>
	
	<tr>
		<td>
	
			<TABLE summary="" cellpadding="5" cellspacing="0" border="0" align="left">
				<tr>
					<td class="formTitleBlue" height="20" colspan="4">
						Customize Search Results
					</td>
				</tr>
				
				<tr>
			    	<td class="formFieldLeftOnly">
						Available Columns
				    </td>
		
					<td class="formFieldNone">						
						&nbsp;					
					</td>	
		
		            <td class="formFieldNone">
						Columns to Display (5 max)
				    </td>
							    
		            <td  class="formFieldRightOnly">
						&nbsp;
				    </td>					    
				</tr>
						
				<tr>
			    	<td class="formFieldLeftOnly">
					    <html:select styleClass="formFieldUnSized" size="8" multiple="true" style="width: 225px"  property="columnsToDisplay">												
					   		 <html:options name="<%= Dropdowns.SEARCHRESULTCOLUMNS %>"/>					
					    </html:select>
					    <br>&nbsp;
				    </td>
		
					<td class="formFieldNone">						
					    <a href="javascript: moveSelectedOptions(document.forms[0].columnsToDisplay, document.forms[0].selectedColumnsToDisplay);"><img border="0" src="/camod/images/rightarrow.gif"></a>
					    <br>
					    <a href="javascript: moveSelectedOptions(document.forms[0].selectedColumnsToDisplay, document.forms[0].columnsToDisplay);"><img border="0" src="/camod/images/leftarrow.gif"></a>						
					</td>	
		
		            <td class="formFieldNone">		         
					    <html:select styleClass="formFieldUnSized" size="8" multiple="true" style="width: 225px" property="selectedColumnsToDisplay">												
					        <html:options name="<%= Dropdowns.SELECTEDSEARCHRESULTCOLUMNS %>"/>
					    </html:select><br>
				       	* Model Descriptor <I>(required)</I><br>
					    <br>
				    </td>
							    
		            <td  class="formFieldRightOnly">
					    <a href="javascript: moveOptionUp(document.forms[0].selectedColumnsToDisplay);"><img border="0" src="/camod/images/uparrow.gif"></a>
					    <br>
					    <a href="javascript: moveOptionDown(document.forms[0].selectedColumnsToDisplay);"><img border="0" src="/camod/images/downarrow.gif"></a>						
				    </td>					    
				</tr>
								
				<tr>
					<td class="formFieldRightLeftOnly" height="20" colspan="4">																		
					    <html:select styleClass="formFieldUnSized" size="1" property="itemsPerPage">												
					        <html:options name="<%= Dropdowns.ITEMSPERPAGE %>"/>
					    </html:select>&nbsp;Items per page						
						<br>
					</td>
				</tr>
							
				<tr>
					<td class="formFieldLeftRightBottomOnly" height="20" colspan="4">
					  	<html:submit styleClass="actionButton">Save</html:submit>					  								
					</td>
				</tr>
																																			
			</TABLE>
		</td>
	</tr>
</TABLE>
</html:form>	

<SCRIPT>
    removeAllSelected();
</SCRIPT>

<%@ include file="/jsp/footer.jsp" %>