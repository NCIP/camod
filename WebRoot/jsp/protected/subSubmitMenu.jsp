<%@ include file="/common/taglibs.jsp"%>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.Constants" %>

<TR><TD class=subMenuPrimaryTitle height=22>SUBMIT & EDIT MODELS</TD></TR>
<TR><TD class=subMenuPrimaryGreyTitle height=27>Editing Model:<b><%= request.getSession().getAttribute( Constants.MODELDESCRIPTOR ) %> ( <%= request.getSession().getAttribute( Constants.MODELID ) %> )<br><a href="submitOverview.jsp">View Model Status ( <%= request.getSession().getAttribute( Constants.MODELSTATUS ) %> )</a></b></TD></TR>
<TR><TD class=subMenuPrimaryItems>

<DIV id="masterdiv">

	<div id="menu1" class="masterTitle" onclick="SwitchMenu('sub1')" onmouseover="ChangeClass('menu1','masterTitleOver')" onmouseout="ChangeClass('menu1','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> MODEL CHARACTERISTICS</div>
	<span class="submasterdiv" id="sub1">		
		&nbsp;&nbsp;&nbsp;- <html:link styleClass="subMenuBlue" action="AnimalModelPopulateAction.do?method=populate"><%= request.getSession().getAttribute( Constants.MODELDESCRIPTOR ) %></html:link>
		<br><br>		
	</span>

	<div id="menu2" class="masterTitle" onclick="SwitchMenu('sub2')" onmouseover="ChangeClass('menu2','masterTitleOver')" onmouseout="ChangeClass('menu2','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> GENETIC DESCRIPTION</div>
	<span class="submasterdiv" id="sub2">
		- <a class="subMenuRed" href="submitEngineeredTransgene.jsp">Enter Engineered Transgene</a><br>
		- <a class="subMenuRed" href="submitGenomicSegment.jsp">Enter Genomic Segment</a><br>
		&nbsp;&nbsp;&nbsp;- <a class="subMenuBlue" href="submitGenomicSegment.jsp">Segment 12p</a><br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- <a class="subMenuDarkRed" href="submitAssocExpression.jsp">Enter Assoc Expression</a><br>
		- <a class="subMenuRed" href="submitTargetedModification.jsp">Enter Targeted Modification</a><br>
		- <a class="subMenuRed" href="submitInducedMutation.jsp">Enter Induced Mutation</a><br>
		- <a class="subMenuRed" href="submitSpontaneousMutation.jsp">Enter Spontaneous Mutation</a><br><br>
	</span>

	<div id="menu3" class="masterTitle" onclick="SwitchMenu('sub3')" onmouseover="ChangeClass('menu3','masterTitleOver')" onmouseout="ChangeClass('menu3','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> CARCINOGENIC INTERVENTIONS</div>
	<span class="submasterdiv" class="submenu"  id="sub3">
		- <a class="subMenuRed" href="submitChemicalDrug.jsp">Enter Chemical/Drug</a><br>
		- <a class="subMenuRed" href="submitEnvironmentalFactors.jsp">Enter Environmental Factors</a><br>		
		- <a class="subMenuRed" href="submitGeneDelivery.jsp">Enter Gene Delivery</a><br>
		- <a class="subMenuRed" href="submitGrowthFactors.jsp">Enter Growth Factors</a><br>		
		- <a class="subMenuRed" href="submitHormone.jsp">Enter Hormone</a><br>
		- <a class="subMenuRed" href="submitNutritionalFactors.jsp">Enter Nutritional Factors</a><br>
		- <a class="subMenuRed" href="submitRadiation.jsp">Enter Radiation</a><br>	
		- <a class="subMenuRed" href="submitSurgeryOther.jsp">Enter Surgery/Other</a><br>	
		- <a class="subMenuRed" href="submitViralTreatment.jsp">Enter Viral Treatment</a><br><br>
	</span>	
	
	<div id="menu4" class="masterTitle" onclick="SwitchMenu('sub4')" onmouseover="ChangeClass('menu4','masterTitleOver')" onmouseout="ChangeClass('menu4','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> PUBLICATIONS</div>
	<span class="submasterdiv" id="sub4">
		- <a class="subMenuRed" href="submitPublications.jsp">Enter Publications</a><br><br>
	</span>

	<div id="menu5" class="masterTitle" onclick="SwitchMenu('sub5')" onmouseover="ChangeClass('menu5','masterTitleOver')" onmouseout="ChangeClass('menu5','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> HISTOPATHOLOGY</div>
	<span class="submasterdiv" id="sub5">
		- <a class="subMenuRed" href="submitHistopathology.jsp">Enter Histopathology</a><br>
		&nbsp;&nbsp;&nbsp;- <a class="subMenuBlue" href="submitHistopathology.jsp">Mammary Gland</a><br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- <a class="subMenuDarkRed" href="submitAssocMetastasis.jsp">Enter Assoc Metastasis</a><br>
		&nbsp;&nbsp;&nbsp;- <a class="subMenuBlue" href="submitHistopathology.jsp">Liver</a><br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- <a class="subMenuDarkRed" href="submitAssocMetastasis.jsp">Enter Assoc Metastasis</a><br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- <a class="subMenuBlue" href="submitHistopathology.jsp">Heart (Metastasis)</a><br>
		- <a class="subMenuRed" href="submitClinicalMarkers.jsp">Enter Clinical Marker</a><br><br>
	</span>

	<div id="menu6" class="masterTitle" onclick="SwitchMenu('sub6')" onmouseover="ChangeClass('menu6','masterTitleOver')" onmouseout="ChangeClass('menu6','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> THERAPEUTIC APPROACHES</div>
	<span class="submasterdiv" id="sub6">
		- <a class="subMenuRed" href="submitTherapy.jsp">Enter Therapy</a><br><br>
	</span>

	<div id="menu7" class="masterTitle" onclick="SwitchMenu('sub7')" onmouseover="ChangeClass('menu7','masterTitleOver')" onmouseout="ChangeClass('menu7','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> CELL LINES</div>
	<span class="submasterdiv" id="sub7">
		- <a class="subMenuRed" href="submitCellLines.jsp">Enter Cell Lines</a><br><br>
	</span>
	
	<div id="menu8" class="masterTitle" onclick="SwitchMenu('sub8')" onmouseover="ChangeClass('menu8','masterTitleOver')" onmouseout="ChangeClass('menu8','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> IMAGES</div>
	<span class="submasterdiv" id="sub8">
		- <a class="subMenuRed" href="submitImages.jsp">Enter Images</a><br><br>
	</span>
	
	<div id="menu9" class="masterTitle" onclick="SwitchMenu('sub9')" onmouseover="ChangeClass('menu9','masterTitleOver')" onmouseout="ChangeClass('menu9','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> MICROARRAYS</div>
	<span class="submasterdiv" id="sub9">
		- <a class="subMenuRed" href="submitMicroarrayData.jsp">Enter Microarray Data</a><br><br>
	</span>

	<div id="menu10" class="masterTitle" onclick="SwitchMenu('sub10')" onmouseover="ChangeClass('menu10','masterTitleOver')" onmouseout="ChangeClass('menu10','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> MODEL AVAILABILITY</div>
	<span class="submasterdiv" id="sub10">
		- <a class="subMenuRed" href="submitJacksonLab.jsp">Available from Jackson Lab.</a><br>
		- <a class="subMenuRed" href="submitMMHCCRepo.jsp">Available from MMHCC Repo.</a><br>
		- <a class="subMenuRed" href="submitInvestigator.jsp">Available from Investigator</a><br>
		- <a class="subMenuRed" href="submitIMSR.jsp">Available from IMSR</a><br><br>
	</span>
        
	<div id="menu11" class="masterTitle" onclick="SwitchMenu('sub11')" onmouseover="ChangeClass('menu11','masterTitleOver')" onmouseout="ChangeClass('menu11','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> XENOGRAFT</div>
	<span class="submasterdiv" id="sub11">
		- <a class="subMenuRed" href="submitTransplantXenograft.jsp">Enter Transplant/Xenograft</a><br>	
	</span>	
</DIV>

</TD></TR>