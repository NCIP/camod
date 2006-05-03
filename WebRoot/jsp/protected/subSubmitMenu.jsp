<%
		/*
		 * $Id: subSubmitMenu.jsp,v 1.53 2006-05-03 20:07:44 pandyas Exp $
		 *
		 * $Log: not supported by cvs2svn $
		 * Revision 1.52  2006/04/27 15:09:10  pandyas
		 * Modified while testing caMod 2.1
		 *
		 * Revision 1.51  2006/04/19 15:12:47  georgeda
		 * Fixed issue w/ display of induced mutation
		 *
		 * Revision 1.50  2006/04/17 19:07:33  pandyas
		 * caMod 2.1 OM changes
		 *
		 * Revision 1.49  2005/12/29 19:42:01  georgeda
		 * Defect #297, fixed therapeutic approaches shorten
		 *
		 * Revision 1.48  2005/12/21 16:12:30  pandyas
		 * Modified name of publication link for cell line and therapy so test script can find links
		 *
		 * Revision 1.47  2005/12/06 18:49:58  georgeda
		 * Defect #247 - real fix this time for the problem
		 *
		 * Revision 1.46  2005/12/02 14:18:21  georgeda
		 * Defect #241, handle truncated HTML tags
		 *
		 * Revision 1.45  2005/11/29 16:28:19  georgeda
		 * Defect #193, missed xenograft
		 *
		 * Revision 1.44  2005/11/28 20:00:07  georgeda
		 * Defect #214 - user the gene name instead of the viral vector
		 *
		 *
		 */
%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel"%>
<%@ page import="gov.nih.nci.camod.domain.Publication"%>
<%@ page import="gov.nih.nci.camod.domain.GeneDelivery"%>
<%@ page import="gov.nih.nci.camod.domain.Therapy"%>
<%@ page import="gov.nih.nci.camod.domain.CellLine"%>
<%@ page import="gov.nih.nci.camod.domain.Xenograft"%>
<%@ page import="gov.nih.nci.camod.domain.InducedMutation"%>
<%@ page import="gov.nih.nci.camod.domain.EngineeredGene"%>
<%@ page import="gov.nih.nci.camod.domain.ExpressionFeature"%>
<%@ page import="gov.nih.nci.camod.domain.Image"%>
<%@ page import="gov.nih.nci.camod.domain.Transgene"%>
<%@ page import="gov.nih.nci.camod.domain.GenomicSegment"%>
<%@ page import="gov.nih.nci.camod.domain.EnvironmentalFactor"%>
<%@ page import="gov.nih.nci.camod.domain.SpontaneousMutation"%>
<%@ page import="gov.nih.nci.camod.domain.TargetedModification"%>
<%@ page import="gov.nih.nci.camod.domain.AnimalAvailability"%>
<%@ page import="gov.nih.nci.camod.domain.Histopathology"%>
<%@ page import="gov.nih.nci.camod.domain.ClinicalMarker"%>
<%@ page import="gov.nih.nci.camod.domain.CarcinogenExposure"%>
<%@ page import="gov.nih.nci.camod.domain.Morpholino"%>
<%@ page import="gov.nih.nci.camod.Constants"%>

<TR>
	<TD class=subMenuPrimaryTitle height=22>SUBMIT & EDIT MODELS</TD>
</TR>
<TR>
	<TD class=subMenuPrimaryGreyTitle height=27>Editing Model:<b><%= request.getSession().getAttribute(Constants.MODELDESCRIPTOR)%>
	( <%= request.getSession().getAttribute(Constants.MODELID)%> )<br>
	<html:link styleClass="subMenuRed"
		action="SubmitOverviewPopulateAction">
		<b>View Model Status ( <%= request.getSession().getAttribute(Constants.MODELSTATUS)%>
		)</b>
	</html:link></TD>
</TR>
<TR>
	<TD class=subMenuPrimaryItems>

	<DIV id="masterdiv">

	<div id="menu1" class="masterTitle" onclick="SwitchMenu('sub1')"
		onmouseover="ChangeClass('menu1','masterTitleOver')"
		onmouseout="ChangeClass('menu1','masterTitle')"><IMG height=5 alt=""
		src="images/subMenuArrow.gif" width=5> MODEL CHARACTERISTICS</div>
	<span class="submasterdiv" id="sub1"> &nbsp;&nbsp;&nbsp;<img
		src="images/aquadot.jpg" border="0"> <html:link
		styleClass="subMenuBlue"
		action="AnimalModelPopulateAction.do?method=populate">
		<camod:shorten>
			<%= request.getSession().getAttribute(Constants.MODELDESCRIPTOR)%>
		</camod:shorten>
	</html:link> <br>
	<br>
	</span>

	<div id="menu2" class="masterTitle" onclick="SwitchMenu('sub2')"
		onmouseover="ChangeClass('menu2','masterTitleOver')"
		onmouseout="ChangeClass('menu2','masterTitle')"><IMG height=5 alt=""
		src="images/subMenuArrow.gif" width=5> GENETIC DESCRIPTION</div>
	<span class="submasterdiv" id="sub2"> <img src="images/right_arrow.gif"
		border="0"> <html:link styleClass="subMenuRed"
		action="EngineeredTransgenePopulateAction.do?method=dropdown">Enter Transgene</html:link><br>

	<logic:iterate id="aEngineeredTransgene"
		name="engineeredtransgene_list" type="Transgene">
			  &nbsp;&nbsp;&nbsp;&nbsp;
			  <img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="EngineeredTransgenePopulateAction.do?method=populate"
			paramId="aEngineeredTransgeneID" paramName="aEngineeredTransgene"
			paramProperty="id">
			<camod:shorten>
				<bean:write name="aEngineeredTransgene" property="name"
					filter="false" />
			</camod:shorten>
		</html:link>
		<br>
			      
			      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			      <img src="images/right_arrow.gif" border="0">
		<html:link styleClass="subMenuDarkRed"
			action="AssociatedExpressionPopulateAction.do?method=dropdown"
			paramId="aEngineeredTransgeneID" paramName="aEngineeredTransgene"
			paramProperty="id">Enter Assoc Expression</html:link>
		<br>

		<bean:define id="expressionFeatureList" name="aEngineeredTransgene"
			property="expressionFeatureCollection" />
		<logic:iterate id="aExpressionFeature" name="expressionFeatureList"
			type="ExpressionFeature">

			<bean:define id="aAssociatedExpressionID" name="aExpressionFeature"
				property="id" />
			<bean:define id="aEngineeredTransgeneID" name="aEngineeredTransgene"
				property="id" />
					
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img src="images/aquadot_red.jpg" border="0">
			<a class="subMenuMedRed"
				href="AssociatedExpressionPopulateAction.do?method=populate&aAssociatedExpressionID=<c:out value='${ aAssociatedExpressionID }' />&aEngineeredTransgeneID=<c:out value='${ aEngineeredTransgeneID }' />">
			<camod:shorten length="21">
				<bean:write name="aExpressionFeature" property="organ.name"
					filter="false" />
			</camod:shorten> </a>
			<br>

		</logic:iterate>
	</logic:iterate> <img src="images/right_arrow.gif" border="0"> <html:link
		styleClass="subMenuRed"
		action="GenomicSegmentPopulateAction.do?method=dropdown">Enter Genomic Segment</html:link><br>
	<logic:iterate id="aGenomicSegment" name="genomicsegment_list"
		type="GenomicSegment">
		
			  &nbsp;&nbsp;&nbsp;&nbsp;
			  <img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="GenomicSegmentPopulateAction.do?method=populate"
			paramId="aGenomicSegmentID" paramName="aGenomicSegment"
			paramProperty="id">
			<camod:shorten>
				<bean:write name="aGenomicSegment" property="cloneDesignator"
					filter="false" />
			</camod:shorten>
		</html:link>
		<br>
			      
			      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			      <img src="images/right_arrow.gif" border="0">
		<html:link styleClass="subMenuDarkRed"
			action="AssociatedExpressionPopulateAction.do?method=dropdown"
			paramId="aGenomicSegmentID" paramName="aGenomicSegment"
			paramProperty="id">Enter Assoc Expression</html:link>
		<br>

		<bean:define id="expressionFeatureList" name="aGenomicSegment"
			property="expressionFeatureCollection" />
		<logic:iterate id="aExpressionFeature" name="expressionFeatureList"
			type="ExpressionFeature">

			<bean:define id="aAssociatedExpressionID" name="aExpressionFeature"
				property="id" />
			<bean:define id="aGenomicSegmentID" name="aGenomicSegment"
				property="id" />
					
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img src="images/aquadot_red.jpg" border="0">
			<a class="subMenuMedRed"
				href="AssociatedExpressionPopulateAction.do?method=populate&aAssociatedExpressionID=<c:out value='${ aAssociatedExpressionID }' />&aGenomicSegmentID=<c:out value='${ aGenomicSegmentID }' />">
			<camod:shorten length="21">
				<bean:write name="aExpressionFeature" property="organ.name"
					filter="false" />
			</camod:shorten> </a>
			<br>

		</logic:iterate>
	</logic:iterate> <img src="images/right_arrow.gif" border="0"> <html:link
		styleClass="subMenuRed"
		action="TargetedModificationPopulateAction.do?method=dropdown">Enter Targeted Modification</html:link><br>
	<logic:iterate id="aTargetedModification"
		name="targetedmodification_list" type="TargetedModification">
			  
			  &nbsp;&nbsp;&nbsp;&nbsp;
			  <img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="TargetedModificationPopulateAction.do?method=populate"
			paramId="aTargetedModificationID" paramName="aTargetedModification"
			paramProperty="id">
			<camod:shorten>
				<bean:write name="aTargetedModification" property="name"
					filter="false" />
			</camod:shorten>
		</html:link>
		<br>
			      
			      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			      <img src="images/right_arrow.gif" border="0">
		<html:link styleClass="subMenuDarkRed"
			action="AssociatedExpressionPopulateAction.do?method=dropdown"
			paramId="aTargetedModificationID" paramName="aTargetedModification"
			paramProperty="id">Enter Assoc Expression</html:link>
		<br>

		<bean:define id="expressionFeatureList" name="aTargetedModification"
			property="expressionFeatureCollection" />
		<logic:iterate id="aExpressionFeature" name="expressionFeatureList"
			type="ExpressionFeature">

			<bean:define id="aAssociatedExpressionID" name="aExpressionFeature"
				property="id" />
			<bean:define id="aTargetedModificationID"
				name="aTargetedModification" property="id" />
					
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img src="images/aquadot_red.jpg" border="0">
			<a class="subMenuMedRed"
				href="AssociatedExpressionPopulateAction.do?method=populate&aAssociatedExpressionID=<c:out value='${ aAssociatedExpressionID }' />&aTargetedModificationID=<c:out value='${ aTargetedModificationID }' />">
			<camod:shorten length="21">
				<bean:write name="aExpressionFeature" property="organ.name"
					filter="false" />
			</camod:shorten> </a>
			<br>

		</logic:iterate>
	</logic:iterate> <img src="images/right_arrow.gif" border="0"> <html:link
		styleClass="subMenuRed"
		action="InducedMutationPopulateAction.do?method=dropdown">Enter Induced Mutation</html:link><br>
	<logic:iterate id="aInducedMutation" name="inducedmutation_list"
		type="InducedMutation">
			  &nbsp;&nbsp;&nbsp;&nbsp;
			  <img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="InducedMutationPopulateAction.do?method=populate"
			paramId="aInducedMutationID" paramName="aInducedMutation"
			paramProperty="id">
			<camod:shorten>
				<bean:write name="aInducedMutation"
					property="environmentalFactor.displayNameIM" filter="false" />
			</camod:shorten>
		</html:link>
		<br>
	</logic:iterate> <img src="images/right_arrow.gif" border="0"> <html:link
		styleClass="subMenuRed"
		action="SpontaneousMutationPopulateAction.do?method=dropdown">Enter Spontaneous Mutation</html:link><br>
	<logic:iterate id="aSpontaneousMutation"
		name="spontaneousmutation_list" type="SpontaneousMutation">
			  &nbsp;&nbsp;&nbsp;&nbsp;
			  <img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="SpontaneousMutationPopulateAction.do?method=populate"
			paramId="aSpontaneousMutationID" paramName="aSpontaneousMutation"
			paramProperty="id">
			<camod:shorten>
				<bean:write name="aSpontaneousMutation" property="name"
					filter="false" />
			</camod:shorten>
		</html:link>
		<br>
	</logic:iterate> <br>
	</span>

	<div id="menu3" class="masterTitle" onclick="SwitchMenu('sub3')"
		onmouseover="ChangeClass('menu3','masterTitleOver')"
		onmouseout="ChangeClass('menu3','masterTitle')"><IMG height=5 alt=""
		src="images/subMenuArrow.gif" width=5> CARCINOGENIC INTERVENTIONS</div>
	<span class="submasterdiv" class="submenu" id="sub3"> <img
		src="images/right_arrow.gif" border="0"> <html:link
		styleClass="subMenuRed"
		action="ChemicalDrugPopulateAction.do?method=dropdown">Enter Chemical/Drug</html:link><br>
	<logic:iterate id="aCarcinogenExposure" name="chemicaldrug_list"
		type="CarcinogenExposure">
				  &nbsp;&nbsp;&nbsp;&nbsp;<img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="ChemicalDrugPopulateAction.do?method=populate"
			paramId="aCarcinogenExposureID" paramName="aCarcinogenExposure"
			paramProperty="id">
			<camod:shorten>
				<bean:write name="aCarcinogenExposure"
					property="environmentalFactor.displayName" filter="false" />
			</camod:shorten>
		</html:link>
		<br>
	</logic:iterate> <img src="images/right_arrow.gif" border="0"> <html:link
		styleClass="subMenuRed"
		action="EnvironmentalFactorPopulateAction.do?method=dropdown">Enter Environmental Factor</html:link><br>
	<logic:iterate id="aCarcinogenExposure" name="environmentalfactor_list"
		type="CarcinogenExposure">
				  &nbsp;&nbsp;&nbsp;&nbsp;<img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="EnvironmentalFactorPopulateAction.do?method=populate"
			paramId="aCarcinogenExposureID" paramName="aCarcinogenExposure"
			paramProperty="id">
			<camod:shorten>
				<bean:write name="aCarcinogenExposure"
					property="environmentalFactor.displayName" filter="false" />
			</camod:shorten>
		</html:link>
		<br>
	</logic:iterate> <img src="images/right_arrow.gif" border="0"> <html:link
		styleClass="subMenuRed"
		action="GeneDeliveryPopulateAction.do?method=dropdown">Enter Gene Delivery</html:link><br>
	<logic:iterate id="aCarcinogenExposure" name="genedelivery_list"
		type="GeneDelivery">
				  &nbsp;&nbsp;&nbsp;&nbsp;<img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="GeneDeliveryPopulateAction.do?method=populate"
			paramId="aCarcinogenExposureID" paramName="aCarcinogenExposure"
			paramProperty="id">
			<camod:shorten>
				<bean:write name="aCarcinogenExposure" property="geneInVirus"
					filter="false" />
			</camod:shorten>
		</html:link>
		<br>
	</logic:iterate> <img src="images/right_arrow.gif" border="0"> <html:link
		styleClass="subMenuRed"
		action="GrowthFactorPopulateAction.do?method=dropdown">Enter Growth Factor</html:link><br>
	<logic:iterate id="aCarcinogenExposure" name="growthfactors_list"
		type="CarcinogenExposure">
				  &nbsp;&nbsp;&nbsp;&nbsp;<img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="GrowthFactorPopulateAction.do?method=populate"
			paramId="aCarcinogenExposureID" paramName="aCarcinogenExposure"
			paramProperty="id">
			<camod:shorten>
				<bean:write name="aCarcinogenExposure"
					property="environmentalFactor.displayName" filter="false" />
			</camod:shorten>
		</html:link>
		<br>
	</logic:iterate> <img src="images/right_arrow.gif" border="0"> <html:link
		styleClass="subMenuRed"
		action="HormonePopulateAction.do?method=dropdown">Enter Hormone</html:link><br>
	<logic:iterate id="aCarcinogenExposure" name="hormone_list"
		type="CarcinogenExposure">
				  &nbsp;&nbsp;&nbsp;&nbsp;<img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="HormonePopulateAction.do?method=populate"
			paramId="aCarcinogenExposureID" paramName="aCarcinogenExposure"
			paramProperty="id">
			<camod:shorten>
				<bean:write name="aCarcinogenExposure"
					property="environmentalFactor.displayName" filter="false" />
			</camod:shorten>
		</html:link>
		<br>
	</logic:iterate> <img src="images/right_arrow.gif" border="0"> <html:link
		styleClass="subMenuRed"
		action="NutritionalFactorPopulateAction.do?method=dropdown">Enter Nutritional Factor</html:link><br>
	<logic:iterate id="aCarcinogenExposure" name="nutritionalfactors_list"
		type="CarcinogenExposure">
				  &nbsp;&nbsp;&nbsp;&nbsp;<img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="NutritionalFactorPopulateAction.do?method=populate"
			paramId="aCarcinogenExposureID" paramName="aCarcinogenExposure"
			paramProperty="id">
			<camod:shorten>
				<bean:write name="aCarcinogenExposure"
					property="environmentalFactor.displayName" filter="false" />
			</camod:shorten>
		</html:link>
		<br>
	</logic:iterate> <img src="images/right_arrow.gif" border="0"> <html:link
		styleClass="subMenuRed"
		action="RadiationPopulateAction.do?method=dropdown">Enter Radiation</html:link><br>
	<logic:iterate id="aCarcinogenExposure" name="radiation_list"
		type="CarcinogenExposure">
				  &nbsp;&nbsp;&nbsp;&nbsp;<img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="RadiationPopulateAction.do?method=populate"
			paramId="aCarcinogenExposureID" paramName="aCarcinogenExposure"
			paramProperty="id">
			<camod:shorten>
				<bean:write name="aCarcinogenExposure"
					property="environmentalFactor.displayName" filter="false" />
			</camod:shorten>
		</html:link>
		<br>
	</logic:iterate> <img src="images/right_arrow.gif" border="0"> <html:link
		styleClass="subMenuRed"
		action="SurgeryPopulateAction.do?method=dropdown">Enter Surgery/Other</html:link><br>
	<logic:iterate id="aCarcinogenExposure" name="surgeryother_list"
		type="CarcinogenExposure">
				  &nbsp;&nbsp;&nbsp;&nbsp;<img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="SurgeryPopulateAction.do?method=populate"
			paramId="aCarcinogenExposureID" paramName="aCarcinogenExposure"
			paramProperty="id">
			<camod:shorten>
				<bean:write name="aCarcinogenExposure"
					property="environmentalFactor.displayName" filter="false" />
			</camod:shorten>
		</html:link>
		<br>
	</logic:iterate> <img src="images/right_arrow.gif" border="0"> <html:link
		styleClass="subMenuRed"
		action="ViralTreatmentPopulateAction.do?method=dropdown">Enter Viral Treatment</html:link><br>
	<logic:iterate id="aCarcinogenExposure" name="viraltreatment_list"
		type="CarcinogenExposure">
			  	&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="ViralTreatmentPopulateAction.do?method=populate"
			paramId="aCarcinogenExposureID" paramName="aCarcinogenExposure"
			paramProperty="id">
			<camod:shorten>
				<bean:write name="aCarcinogenExposure"
					property="environmentalFactor.displayName" filter="false" />
			</camod:shorten>
		</html:link>
		<br>
	</logic:iterate> <br>
	</span>

	<div id="menu4" class="masterTitle" onclick="SwitchMenu('sub4')"
		onmouseover="ChangeClass('menu4','masterTitleOver')"
		onmouseout="ChangeClass('menu4','masterTitle')"><IMG height=5 alt=""
		src="images/subMenuArrow.gif" width=5>TRANSIENT INTERFERENCE</div>
	<span class="submasterdiv" id="sub4"> <img src="images/right_arrow.gif"
		border="0"> <html:link styleClass="subMenuRed"
		action="MorpholinoPopulateAction.do?method=dropdown">Enter Morpholino</html:link><br>
	<logic:iterate id="aMorpholino" name="morpholino_list" type="Morpholino">
			 	 &nbsp;&nbsp;&nbsp;&nbsp;<img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="MorpholinoPopulateAction.do?method=populate"
			paramId="aMorpholinoID" paramName="aMorpholino" paramProperty="id">
			<camod:shorten>
				<bean:write name="aMorpholino" property="targetedRegion" filter="false" />
			</camod:shorten>
		</html:link>
		<br>
	</logic:iterate> <br>
	</span>

	<div id="menu5" class="masterTitle" onclick="SwitchMenu('sub4')"
		onmouseover="ChangeClass('menu4','masterTitleOver')"
		onmouseout="ChangeClass('menu4','masterTitle')"><IMG height=5 alt=""
		src="images/subMenuArrow.gif" width=5> PUBLICATIONS</div>
	<span class="submasterdiv" id="sub4"> <img src="images/right_arrow.gif"
		border="0"> <html:link styleClass="subMenuRed"
		action="PublicationPopulateAction.do?method=dropdown">Enter Publications</html:link><br>
	<logic:iterate id="aPub" name="publication_list" type="Publication">
			 	 &nbsp;&nbsp;&nbsp;&nbsp;<img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="PublicationPopulateAction.do?method=populate"
			paramId="aPubID" paramName="aPub" paramProperty="id">
			<camod:shorten>
				<bean:write name="aPub" property="authors" filter="false" />
			</camod:shorten>
		</html:link>
		<br>
	</logic:iterate> <br>
	</span>

	<div id="menu6" class="masterTitle" onclick="SwitchMenu('sub5')"
		onmouseover="ChangeClass('menu5','masterTitleOver')"
		onmouseout="ChangeClass('menu5','masterTitle')"><IMG height=5 alt=""
		src="images/subMenuArrow.gif" width=5> HISTOPATHOLOGY</div>
	<span class="submasterdiv" id="sub5"> <img src="images/right_arrow.gif"
		border="0"> <html:link styleClass="subMenuRed"
		action="HistopathologyPopulateAction.do?method=dropdown">Enter Histopathology</html:link><br>

	<logic:iterate id="aHistopathology" name="histopathology_list"
		type="Histopathology">
			  &nbsp;&nbsp;&nbsp; <img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="HistopathologyPopulateAction.do?method=populate"
			paramId="aHistopathologyID" paramName="aHistopathology"
			paramProperty="id">
			<camod:shorten>
				<bean:write name="aHistopathology" property="organ.name"
					filter="false" />
			</camod:shorten>
		</html:link>
		<br>

		<!-- Begin Associated Metastasis Loop -->			      
			      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img
			src="images/right_arrow.gif" border="0">
		<html:link styleClass="subMenuDarkRed"
			action="AssociatedMetastasisPopulateAction.do?method=dropdown"
			paramId="aHistopathologyID" paramName="aHistopathology"
			paramProperty="id">Enter Assoc Metastasis</html:link>
		<br>

		<bean:define id="associatedMetastasisList" name="aHistopathology"
			property="metastasisCollection" />
		<logic:iterate id="aAssociatedMetastasis"
			name="associatedMetastasisList" type="Histopathology">

			<bean:define id="aAssociatedMetastasisID"
				name="aAssociatedMetastasis" property="id" />
			<bean:define id="aHistopathologyID" name="aHistopathology"
				property="id" />
					
				  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img
				src="images/aquadot_red.jpg" border="0">
			<a class="subMenuMedRed"
				href="AssociatedMetastasisPopulateAction.do?method=populate&aAssociatedMetastasisID=<c:out value='${ aAssociatedMetastasisID }' />&aHistopathologyID=<c:out value='${ aHistopathologyID }' /> ">
			<camod:shorten length="21">
				<bean:write name="aAssociatedMetastasis" property="organ.name"
					filter="false" />
			</camod:shorten> </a>
			<br>
		</logic:iterate>
		<!-- End Associated Metastasis Loop -->

		<!-- Begin Clinical Marker Loop -->
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img
			src="images/right_arrow.gif" border="0">
		<html:link styleClass="subMenuDarkRed"
			action="ClinicalMarkerPopulateAction.do?method=dropdown"
			paramId="aHistopathologyID" paramName="aHistopathology"
			paramProperty="id">Enter Clinical Marker</html:link>
		<br>

		<bean:define id="clinicalMarkerList" name="aHistopathology"
			property="clinicalMarkerCollection" />
		<logic:iterate id="aClinicalMarker" name="clinicalMarkerList"
			type="ClinicalMarker">

			<bean:define id="aClinicalMarkerID" name="aClinicalMarker"
				property="id" />
			<bean:define id="aHistopathologyID" name="aHistopathology"
				property="id" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img
				src="images/aquadot_red.jpg" border="0">

			<a class="subMenuMedRed"
				href="ClinicalMarkerPopulateAction.do?method=populate&aClinicalMarkerID=<c:out value='${ aClinicalMarkerID }' />&aHistopathologyID=<c:out value='${ aHistopathologyID }' /> ">
			<camod:shorten length="21">
				<bean:write name="aClinicalMarker" property="displayName"
					filter="false" />
			</camod:shorten> </a>
			<br>
		</logic:iterate>
		<!-- End Clinical Marker Loop -->
	</logic:iterate> <br>
	</span>

	<div id="menu7" class="masterTitle" onclick="SwitchMenu('sub6')"
		onmouseover="ChangeClass('menu6','masterTitleOver')"
		onmouseout="ChangeClass('menu6','masterTitle')"><IMG height=5 alt=""
		src="images/subMenuArrow.gif" width=5> THERAPEUTIC APPROACHES</div>
	<span class="submasterdiv" id="sub6"> <img src="images/right_arrow.gif"
		border="0"> <html:link styleClass="subMenuRed"
		action="TherapyPopulateAction.do?method=dropdown">Enter Therapy</html:link><br>
	<logic:iterate id="aTherapy" name="therapy_list" type="Therapy">
				  &nbsp;&nbsp;&nbsp;&nbsp;<img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="TherapyPopulateAction.do?method=populate"
			paramId="aTherapyID" paramName="aTherapy" paramProperty="id">
			<camod:shorten>
				<bean:write name="aTherapy" property="agent.name" filter="false" />
			</camod:shorten>
		</html:link>
		<br>
			 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img src="images/right_arrow.gif" border="0">
		<html:link styleClass="subMenuDarkRed"
			action="PublicationPopulateAction.do?method=dropdown"
			paramId="aTherapyID" paramName="aTherapy" paramProperty="id">Enter Publication for Therapy</html:link>
		<br>

		<bean:define id="publicationList" name="aTherapy"
			property="publicationCollection" />
		<logic:iterate id="aPublication" name="publicationList"
			type="Publication">

			<bean:define id="aPublicationID" name="aPublication" property="id" />
			<bean:define id="aTherapyID" name="aTherapy" property="id" />
				
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img src="images/aquadot_red.jpg" border="0">
			<a class="subMenuMedRed"
				href="PublicationPopulateAction.do?method=populate&aPubID=<c:out value='${ aPublicationID }' />&aTherapyID=<c:out value='${aTherapyID}'/>"><camod:shorten
				length="21">
				<c:out value="${aPublication.authors}" />
			</camod:shorten></a>
			<br>

		</logic:iterate>
	</logic:iterate> <br>
	</span>

	<div id="menu8" class="masterTitle" onclick="SwitchMenu('sub7')"
		onmouseover="ChangeClass('menu7','masterTitleOver')"
		onmouseout="ChangeClass('menu7','masterTitle')"><IMG height=5 alt=""
		src="images/subMenuArrow.gif" width=5> CELL LINES</div>
	<span class="submasterdiv" id="sub7"> <img src="images/right_arrow.gif"
		border="0"> <html:link styleClass="subMenuRed"
		action="CellLinePopulateAction.do?method=dropdown">Enter Cell Lines</html:link><br>
	<logic:iterate id="aCell" name="cellline_list" type="CellLine">			 
				 &nbsp;&nbsp;&nbsp;&nbsp;
				 <img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="CellLinePopulateAction.do?method=populate" paramId="aCellID"
			paramName="aCell" paramProperty="id">
			<camod:shorten>
				<bean:write name="aCell" property="name" filter="false" />
			</camod:shorten>
		</html:link>
		<br>		
				 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    <img src="images/right_arrow.gif" border="0">
		<html:link styleClass="subMenuDarkRed"
			action="PublicationPopulateAction.do?method=dropdown"
			paramId="aCellID" paramName="aCell" paramProperty="id">Enter Publication for Cell Line</html:link>
		<br>

		<bean:define id="publicationList" name="aCell"
			property="publicationCollection" />
		<logic:iterate id="aPublication" name="publicationList"
			type="Publication">

			<bean:define id="aPublicationID" name="aPublication" property="id" />
			<bean:define id="aCellID" name="aCell" property="id" />
				
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img src="images/aquadot_red.jpg" border="0">
			<a class="subMenuMedRed"
				href="PublicationPopulateAction.do?method=populate&aPubID=<c:out value='${ aPublicationID }' />&aCellID=<c:out value='${aCellID }'/>"><camod:shorten
				length="21">
				<bean:write name="aPublication" property="authors" filter="false" />
			</camod:shorten></a>
			<br>

		</logic:iterate>
	</logic:iterate> <br>
	</span>

	<div id="menu9" class="masterTitle" onclick="SwitchMenu('sub8')"
		onmouseover="ChangeClass('menu8','masterTitleOver')"
		onmouseout="ChangeClass('menu8','masterTitle')"><IMG height=5 alt=""
		src="images/subMenuArrow.gif" width=5> IMAGES</div>
	<span class="submasterdiv" id="sub8"> <img src="images/right_arrow.gif"
		border="0"> <html:link styleClass="subMenuRed"
		action="ImagePopulateAction.do?method=dropdown">Enter Images</html:link><br>
	<logic:iterate id="aImage" name="image_list" type="Image">
			 &nbsp;&nbsp;&nbsp;&nbsp;<img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="ImagePopulateAction.do?method=populate" paramId="aImageID"
			paramName="aImage" paramProperty="id">
			<camod:shorten>
				<bean:write name="aImage" property="title" filter="false" />
			</camod:shorten>
		</html:link>
		<br>
	</logic:iterate> <br>
	</span>

	<div id="menu10" class="masterTitle" onclick="SwitchMenu('sub9')"
		onmouseover="ChangeClass('menu9','masterTitleOver')"
		onmouseout="ChangeClass('menu9','masterTitle')"><IMG height=5 alt=""
		src="images/subMenuArrow.gif" width=5> MICROARRAYS</div>
	<span class="submasterdiv" id="sub9"> <img src="images/right_arrow.gif"
		border="0"> <html:link styleClass="subMenuRed"
		action="submitMicroarrayData">Enter Microarray Data</html:link><br>
	<br>
	</span>

	<div id="menu11" class="masterTitle" onclick="SwitchMenu('sub10')"
		onmouseover="ChangeClass('menu10','masterTitleOver')"
		onmouseout="ChangeClass('menu10','masterTitle')"><IMG height=5 alt=""
		src="images/subMenuArrow.gif" width=5> MODEL AVAILABILITY</div>
	<span class="submasterdiv" id="sub10"> <img
		src="images/right_arrow.gif" border="0"> <html:link
		styleClass="subMenuRed"
		action="JacksonLabPopulateAction.do?method=dropdown&lab=Jackson Laboratory">Available from Jackson Lab.</html:link><br>
	<logic:iterate id="aAvailability" name="jacksonlab_list"
		type="AnimalAvailability">
			 &nbsp;&nbsp;&nbsp;&nbsp;<img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="JacksonLabPopulateAction.do?method=populate"
			paramId="aAvailabilityID" paramName="aAvailability"
			paramProperty="id">
			<camod:shorten>
				<bean:write name="aAvailability" property="displayName"
					filter="false" />
			</camod:shorten>
		</html:link>
		<br>
	</logic:iterate> <img src="images/right_arrow.gif" border="0"> <html:link
		styleClass="subMenuRed"
		action="MMHCCRepoPopulateAction.do?method=dropdown&lab=MMHCC Repository">Available from MMHCC Repo.</html:link><br>
	<logic:iterate id="aAvailability" name="mmhcc_list"
		type="AnimalAvailability">
			 &nbsp;&nbsp;&nbsp;&nbsp;<img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="MMHCCRepoPopulateAction.do?method=populate"
			paramId="aAvailabilityID" paramName="aAvailability"
			paramProperty="id">
			<camod:shorten>
				<bean:write name="aAvailability" property="displayName"
					filter="false" />
			</camod:shorten>
		</html:link>
		<br>
	</logic:iterate> <img src="images/right_arrow.gif" border="0"> <html:link
		styleClass="subMenuRed"
		action="InvestigatorPopulateAction.do?method=dropdown&lab=Investigator">Available from Investigator</html:link><br>
	<logic:iterate id="aAvailability" name="investigator_list"
		type="AnimalAvailability">
			 &nbsp;&nbsp;&nbsp;&nbsp;<img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="InvestigatorPopulateAction.do?method=populate"
			paramId="aAvailabilityID" paramName="aAvailability"
			paramProperty="id">
			<camod:shorten>
				<bean:write name="aAvailability" property="displayName"
					filter="false" />
			</camod:shorten>
		</html:link>
		<br>
	</logic:iterate> <img src="images/right_arrow.gif" border="0"> <html:link
		styleClass="subMenuRed"
		action="IMSRPopulateAction.do?method=dropdown&lab=IMSR">Available from IMSR</html:link><br>
	<logic:iterate id="aAvailability" name="imsr_list"
		type="AnimalAvailability">
			 &nbsp;&nbsp;&nbsp;&nbsp;<img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="IMSRPopulateAction.do?method=populate"
			paramId="aAvailabilityID" paramName="aAvailability"
			paramProperty="id">
			<camod:shorten>
				<bean:write name="aAvailability" property="displayName"
					filter="false" />
			</camod:shorten>
		</html:link>
		<br>
	</logic:iterate> <br>
	</span>

	<div id="menu12" class="masterTitle" onclick="SwitchMenu('sub11')"
		onmouseover="ChangeClass('menu11','masterTitleOver')"
		onmouseout="ChangeClass('menu11','masterTitle')"><IMG height=5 alt=""
		src="images/subMenuArrow.gif" width=5> XENOGRAFT</div>
	<span class="submasterdiv" id="sub11"> <img
		src="images/right_arrow.gif" border="0"> <html:link
		styleClass="subMenuRed"
		action="XenograftPopulateAction.do?method=dropdown">Enter Transplant/Xenograft</html:link><br>
	<logic:iterate id="aXenograft" name="xenograft_list" type="Xenograft">
			 &nbsp;&nbsp;&nbsp;&nbsp;<img src="images/aquadot.jpg" border="0">
		<html:link styleClass="subMenuBlue"
			action="XenograftPopulateAction.do?method=populate"
			paramId="aXenograftID" paramName="aXenograft" paramProperty="id">
			<camod:shorten>
				<bean:write name="aXenograft" property="xenograftName"
					filter="false" />
			</camod:shorten>
		</html:link>
		<br>
	</logic:iterate> <br>
	</span></DIV>

	</TD>
</TR>
