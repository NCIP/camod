<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

 <script language="JavaScript">
    var skinName = 'evs';
    windowTitle = "NCI Center for Bioinformatics";

    // targetFormName (String) - The name of the form with the target form fields (organ, organTissueName, organTissueCode) * NO SPACES *
    // species (String) - desired species * NO SPACES *

    function showTissueTree(targetFormName, input)
    {
      var now = new Date();
      var glob = now.getHours()+now.getSeconds()+now.getMilliseconds();
      var targetURL;
      var rootNode = "Murine_Tissue_Type";
      input = 'rootNode='+rootNode+";"+input;

      targetURL = '/EVSTree/webtree/WebTreeMain.jsp?treeParams='+escape(input)+';formName='+escape(targetFormName)+';type=tissue;&skin='+skinName+'&windowTitle='+windowTitle+'&rand='+glob;

      // open target window
      windowOpen(targetURL, 810, 500, 'Tissue Select');
    }

    // pop-up window utilitiy function
    function windowOpen(url,w,h,title)
    {
      window.name = 'root';
      remote = window.open(url,'none','width= '+w+',height='+h+', resizable=yes,scrollbars=yes');
      if (remote != null) {
  		remote.title = title;
        if (remote.opener == null) {
          remote.opener = self;
          remote.name = 'popup';
        }
      }
      remote.focus();
    }
 </Script>

<FORM name="input" action="/searchResults.do" method="get">

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">
                <tr>
                        <td class="formTitle" height="20" colspan="3">Keyword Search:&nbsp;&nbsp;<input type="text" name="field3" id="field3" size="45" />&nbsp;&nbsp;<input class="actionButton" type="submit" value="Search" /></td>
                </tr>
                
                <tr><td>&nbsp;</td></tr>
                
		<tr>
			<td class="formTitle" height="20" colspan="3">Simple Search</td>
			<!-- <td class="formMessage" align="left" FONT="9"><a href="advancedsearch.html">Advanced Search <a> </td> -->
		</tr>

		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formRequiredLabel"><label for="field1">Model Name / Model Descriptor</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formLabel"><label for="field2">PI's Name</label></td>
			
			<td class="formField">
			<select class="formFieldSized"  name="field2" id="field2" size="1">
				<option value="option1"></option>
                                <OPTION value="65">Abate-Chen, Cory</option>
                                <OPTION value="403">Al-Ubaidi, Muayyad</option>
                                <OPTION value="562">Almholt, Kasper</option>
                                <OPTION value="184">Arbeit, Jeff</option>
                                <OPTION value="306">Arnold, Andrew</option>
                                <OPTION value="293">Arteaga, Carlos</option>
                                <OPTION value="539">Ben-Neriah, Yinon</option>
                                <OPTION value="74">Balmain, Allan</option>
                                <OPTION value="570">Baron, Beverly</option>
                                <OPTION value="571">Basson, Craig</option>
                                <OPTION value="602">Baylin, Stephen</option>
                                <OPTION value="250">Benezra, Robert</option>
                                <OPTION value="502">Blasco, Maria</option>
                                <OPTION value="178">Bradley, Allan</option>
                                <OPTION value="498">Bremner, Rod</option>
			</select>			
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="0">&nbsp;</td>
			<td class="formLabel">
				<label for="field2">Site of Lesion/Tumor</label>
				&nbsp;
<!--				<a href='javascript: rs("commentWin","evsTreeImage.jsp",1025,700);'> -->
		  	    <a href="javascript:showTissueTree('input', 'descendants=true;isaFlag=false;depthLevel=6;roleType=Anatomic_Structure_is_Physical_Part_of')">
				<IMG src="images\selectUP.gif" align=middle border=0>
				<INPUT name="organTissueName" type="hidden"/>
		 		<INPUT name="organTissueCode" type="hidden"/>
				</a>
			</td>
			<td class="formField"><input class="formFieldSized" type="text" disabled="true" name="organ" id="organFieldId" size="25" /></td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Species</label></td>
			
			<td class="formField">
			<select class="formFieldSized"  name="field2" id="field2" size="1">
                                <OPTION value=""></OPTION>
                                <OPTION value="1">Mouse(Mus musculus)</OPTION>
                                <OPTION value="8">Rat(Rattus norvegicus)</OPTION>
                                <OPTION value="9">Rat(Rattus rattus)</OPTION>
                                <OPTION value="12">Mongolian gerbil (Meriones unguiculatus)</OPTION>
                                <OPTION value="13">Syrian (golden) hamster (Mesocricetus auratus)</OPTION>
                                <OPTION value="14">Guinea pig (Cavia porcellus)</OPTION>
                                <OPTION value="16">Mouse(in vivo)</OPTION>
			</select>			
			</td>
		</tr>

		<tr>
			<td align="right" colspan="3">
				<!-- action buttons begins -->
				<TABLE cellpadding="4" cellspacing="0" border="0">
					<tr>
						<td><input class="actionButton" type="submit" value="Search" /></td>
						<td><input class="actionButton" type="reset" value="Reset" /></td>
					</tr>
				</TABLE>
			</td>
		</tr>
	</TABLE>
	
</td></tr>
</TABLE>	

<%@ include file="/jsp/footer.jsp" %>