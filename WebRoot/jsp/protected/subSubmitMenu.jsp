<%@ include file="/common/taglibs.jsp"%>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.Constants" %>

<TR><TD class=subMenuPrimaryTitle height=22>SUBMIT & EDIT MODELS</TD></TR>
<TR><TD class=subMenuPrimaryGreyTitle height=27>Editing Model:<b><%= request.getSession().getAttribute( Constants.MODELDESCRIPTOR ) %> ( <%= request.getSession().getAttribute( Constants.MODELID ) %> )<br><html:link styleClass="subMenuRed" action="submitOverview"><b>View Model Status ( <%= request.getSession().getAttribute( Constants.MODELSTATUS ) %> )</b></html:link></TD></TR>
<TR><TD class=subMenuPrimaryItems>

<DIV id="masterdiv">

	<div id="menu1" class="masterTitle" onclick="SwitchMenu('sub1')" onmouseover="ChangeClass('menu1','masterTitleOver')" onmouseout="ChangeClass('menu1','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> MODEL CHARACTERISTICS</div>
	<span class="submasterdiv" id="sub1">		
		&nbsp;&nbsp;&nbsp;- <html:link styleClass="subMenuBlue" action="AnimalModelPopulateAction.do?method=populate"><%= request.getSession().getAttribute( Constants.MODELDESCRIPTOR ) %></html:link>
		<br><br>		
	</span>

	<div id="menu2" class="masterTitle" onclick="SwitchMenu('sub2')" onmouseover="ChangeClass('menu2','masterTitleOver')" onmouseout="ChangeClass('menu2','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> GENETIC DESCRIPTION</div>
	<span class="submasterdiv" id="sub2">
		- <html:link styleClass="subMenuRed" action="submitEngineeredTransgene">Enter Engineered Transgene</html:link><br>
		- <html:link styleClass="subMenuRed" action="submitGenomicSegment">Enter Genomic Segment</html:link><br>
		&nbsp;&nbsp;&nbsp;- <html:link styleClass="subMenuBlue" action="submitGenomicSegment">Segment 12p</html:link><br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- <html:link styleClass="subMenuDarkRed" action="submitAssocExpression">Enter Assoc Expression</html:link><br>
		- <html:link styleClass="subMenuRed" action="submitTargetedModification">Enter Targeted Modification</html:link><br>
		- <html:link styleClass="subMenuRed" action="submitInducedMutation">Enter Induced Mutation</html:link><br>
		- <html:link styleClass="subMenuRed" action="submitSpontaneousMutation">Enter Spontaneous Mutation</html:link><br><br>
	</span>

	<div id="menu3" class="masterTitle" onclick="SwitchMenu('sub3')" onmouseover="ChangeClass('menu3','masterTitleOver')" onmouseout="ChangeClass('menu3','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> CARCINOGENIC INTERVENTIONS</div>
	<span class="submasterdiv" class="submenu"  id="sub3">
		- <html:link styleClass="subMenuRed" action="submitChemicalDrug.jsp">Enter Chemical/Drug</html:link><br>
		- <html:link styleClass="subMenuRed" action="submitEnvironmentalFactors">Enter Environmental Factors</html:link><br>		
		- <html:link styleClass="subMenuRed" action="submitGeneDelivery">Enter Gene Delivery</html:link><br>
		- <html:link styleClass="subMenuRed" action="submitGrowthFactors">Enter Growth Factors</html:link><br>		
		- <html:link styleClass="subMenuRed" action="submitHormone">Enter Hormone</html:link><br>
		- <html:link styleClass="subMenuRed" action="submitNutritionalFactors">Enter Nutritional Factors</html:link><br>
		- <html:link styleClass="subMenuRed" action="submitRadiation">Enter Radiation</html:link><br>	
		- <html:link styleClass="subMenuRed" action="submitSurgeryOther">Enter Surgery/Other</html:link><br>	
		- <html:link styleClass="subMenuRed" action="submitViralTreatment">Enter Viral Treatment</html:link><br><br>
	</span>	
	
	<div id="menu4" class="masterTitle" onclick="SwitchMenu('sub4')" onmouseover="ChangeClass('menu4','masterTitleOver')" onmouseout="ChangeClass('menu4','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> PUBLICATIONS</div>
	<span class="submasterdiv" id="sub4">
		- <html:link styleClass="subMenuRed" action="submitPublications">Enter Publications</html:link><br><br>
	</span>

	<div id="menu5" class="masterTitle" onclick="SwitchMenu('sub5')" onmouseover="ChangeClass('menu5','masterTitleOver')" onmouseout="ChangeClass('menu5','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> HISTOPATHOLOGY</div>
	<span class="submasterdiv" id="sub5">
		- <html:link styleClass="subMenuRed" action="submitHistopathology">Enter Histopathology</html:link><br>
		&nbsp;&nbsp;&nbsp;- <html:link styleClass="subMenuBlue" action="submitHistopathology">Mammary Gland</html:link><a class="subMenuBlue" href="submitHistopathology.jsp"></a><br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- <html:link styleClass="subMenuDarkRed" action="submitAssocMetastasis">Enter Assoc Metastasis</html:link><br>
		&nbsp;&nbsp;&nbsp;- <html:link styleClass="subMenuBlue" action="submitHistopathology">Liver</html:link><br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- <html:link styleClass="subMenuDarkRed" action="submitAssocMetastasis">Enter Assoc Metastasis</html:link><br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- <html:link styleClass="subMenuBlue" action="submitHistopathology">Heart (Metastasis)</html:link><br>
		- <html:link styleClass="subMenuRed" action="submitClinicalMarkers">Enter Clinical Marker</html:link><br><br>
	</span>

	<div id="menu6" class="masterTitle" onclick="SwitchMenu('sub6')" onmouseover="ChangeClass('menu6','masterTitleOver')" onmouseout="ChangeClass('menu6','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> THERAPEUTIC APPROACHES</div>
	<span class="submasterdiv" id="sub6">
		- <html:link styleClass="subMenuRed" action="submitTherapy">Enter Therapy</html:link><br><br>
	</span>

	<div id="menu7" class="masterTitle" onclick="SwitchMenu('sub7')" onmouseover="ChangeClass('menu7','masterTitleOver')" onmouseout="ChangeClass('menu7','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> CELL LINES</div>
	<span class="submasterdiv" id="sub7">
		- <html:link styleClass="subMenuRed" action="submitCellLines">Enter Cell Lines</html:link><br><br>
	</span>
	
	<div id="menu8" class="masterTitle" onclick="SwitchMenu('sub8')" onmouseover="ChangeClass('menu8','masterTitleOver')" onmouseout="ChangeClass('menu8','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> IMAGES</div>
	<span class="submasterdiv" id="sub8">
		- <html:link styleClass="subMenuRed" action="submitImages">Enter Images</html:link><br><br>
	</span>
	
	<div id="menu9" class="masterTitle" onclick="SwitchMenu('sub9')" onmouseover="ChangeClass('menu9','masterTitleOver')" onmouseout="ChangeClass('menu9','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> MICROARRAYS</div>
	<span class="submasterdiv" id="sub9">
		- <html:link styleClass="subMenuRed" action="submitMicroarrayData">Enter Microarray Data</html:link><br><br>
	</span>

	<div id="menu10" class="masterTitle" onclick="SwitchMenu('sub10')" onmouseover="ChangeClass('menu10','masterTitleOver')" onmouseout="ChangeClass('menu10','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> MODEL AVAILABILITY</div>
	<span class="submasterdiv" id="sub10">
		- <html:link styleClass="subMenuRed" action="submitJacksonLab">Available from Jackson Lab.</html:link><br>
		- <html:link styleClass="subMenuRed" action="submitMMHCCRepo">Available from MMHCC Repo.</html:link><br>
		- <html:link styleClass="subMenuRed" action="submitInvestigator">Available from Investigator</html:link><br>
		- <html:link styleClass="subMenuRed" action="submitIMSR">Available from IMSR</html:link><br><br>
	</span>
        
	<div id="menu11" class="masterTitle" onclick="SwitchMenu('sub11')" onmouseover="ChangeClass('menu11','masterTitleOver')" onmouseout="ChangeClass('menu11','masterTitle')"><IMG height=5 alt="" src="images/subMenuArrow.gif" width=5> XENOGRAFT</div>
	<span class="submasterdiv" id="sub11">
		- <html:link styleClass="subMenuRed" action="submitTransplantXenograft">Enter Transplant/Xenograft</html:link><br>	
	</span>	
</DIV>

</TD></TR>