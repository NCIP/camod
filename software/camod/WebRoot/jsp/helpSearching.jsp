<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" >
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	

			<tr>
				<td class="formTitle" height="20">Help - Searching</td>				
			</tr>			
		
			<tr>
				<td class="resultsBoxGreyEnd">
					<table width="85%" align="center" cellpadding="5" cellspacing="5">
					<tr><td><h2 align="center">Help for Searching the Cancer Models Database</h2></td></tr>
					<tr><td><ul><li><a href="#simple">Simple Search Mode</a></li>
					<li><a href="#advanced">Advanced Search Mode</a></li>
					<li><a href="#combine">Combining Search Criteria</a></li>

					<li><a href="#plug-in">How to view MrSid images</a></li>
					</ul></td></tr>

					<tr><td><hr noshade></td></tr>

					<tr><td class="label"><a name="simple"></a>
					Simple Search Mode
					</td></tr>
					<tr><td>In the simple search mode you can enter search criteria for <ul><li><b>Model Descriptor / Model Name</b><br>
					Enter the name or parts of the model name. The search will return all models that contain the word or letter combination you entered.

					<li><b>Site of the Tumor</b><br>
					Select for models that develop lesions in specific organs. Selecting the organ is a two-step process: Select the organ system in the first drop-down list. The content of the second drop-down list will change depending on your selection. </li>
					<li><b>Species of the Model</b><br>Select the species of the model.</li>

					</ul> </td></tr>
					<tr><td class="label"><a name="advanced"></a>Advanced Search Mode</td></tr>
					<tr><td>The Advanced Search Mode can be used in addition to the simple search. It allows you to specify your search.<br>
					In the Advanced Search Mode you can search for:
					<ul>
					<li><b>Genetic Description</b>
					<br>Enter the Gene Name and specify if the gene was used as transgene, was knocked out (targeted mutation) or was the targeted transgene. You can select all three method of genome manipulation, but you can also leave all boxed unchecked and retrieve all models with manipulations of this gene.

					<li><b>Carcinogenic Agents</b><br>The carcinogenic agents are divided in different categories. A drop-down list shows items sorted by category. The lists contain only agents and treatments that were used with models in the database. Therefore, selecting one of these list items will retrieve at least one matching record. 
					<ul>
					<li><b>Chemical / Drug</b><br>Search for a listed chemical or drug. This will retrieve models that were exposed to this chemical or drug.
					<li><b>Growth Factor</b><br>Search for a listed growth factor. This will retrieve models that were treated with the growth factor.

					<li><b>Hormone</b><br>Search for a listed hormone. This will retrieve models that were exposed to this hormone.
					<li><b>Radiation</b><br>Search for a listed type of radiation. This will retrieve models that were exposed to this type of radiation.
					<li><b>Virus</b><br>Search for a listed virus. This will retrieve models that were exposed to this virus.
					<li><b>Surgery</b><br>Search for a listed surgeries and other treatments.
					</ul>

					<li><b>Cell Lines</b><br>
					Enter the name or parts of the name to search for a specific cell line and/or select the species that the cell line was derived from.
					<li><b>Therapeutic Approaches</b><br>
					Enter the name or parts of the name of the compound that was used in therapeutic experiments.

					</ul>

					</td></tr>
					<tr><td class="label"><a name="combine"></a>Combining Search Criteria</td></tr>
					<tr><td>You can combine search criteria. They will be connected by an &quot;AND&quot; statement. That means if you search e.g. for models where the model descriptor / name contains &quot;WAP&quot; the search might return 6 matches. 
					If you search for models where the model descriptor / name contains &quot;WAP&quot; and the the tumor site is mammary gland the search might return less than 6 matches, because both search criteria need to be fulfilled in order to get matching records. This is an easy way to narrow the number of retrieved records.</li></td></tr>
					<tr><td class="label"><a name="plug-in">How to view MrSid images</a></td></tr>
					<tr><td>MrSid images are high-qualitiy images that have been compressed. The images are shown in the MrSid image viewer. The software allows the user to zoom in and out, move the pan to the left, right, up and down, or show the complete image (full size view). Depending on the selection made by the user the specific part of the image will be downloaded from the server. This might take a moment. Please be patient. </td></tr>

					<tr><td align="center"><img src="images/image_viewer.jpg" alt="" width="300" height="309" border="0"></td></tr>
					</table>
				</td>
			<tr>
			</TABLE>
		</td></tr></TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="footer.jsp" %>