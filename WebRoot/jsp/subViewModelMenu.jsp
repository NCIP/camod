<%@ include file="/common/taglibs.jsp"%>

<bean:define id="mdl" name="animalmodel"/>
<TR><TD class=subMenuPrimaryTitle height=22>MODEL DETAILS</TD></TR>
<TR><TD class=subMenuPrimaryGreyTitle height=27>
	Viewing Model: <b>
	<c:out value="${mdl.modelDescriptor}"/></b>
</TD></TR>
<TR><TD class=subMenuPrimaryItems>
<DIV>
	<bean:parameter id="mdl" name="aModelID"/>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="ViewModelCharacteristicsAction.do?aModelID=<%=mdl%>" styleClass="subMenuPrimary">MODEL CHARACTERISTICS</a>
	<BR>	
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="ViewGeneticDescriptionAction.do?aModelID=<%=mdl%>" styleClass="subMenuPrimary">GENETIC DESCRIPTION</a>	
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="ViewCarcinogenicInterventionsAction.do?aModelID=<%=mdl%>" styleClass="subMenuPrimary">CARCINOGENIC INTERVENTIONS</a>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="ViewPublicationsAction.do?aModelID=<%=mdl%>" styleClass="subMenuPrimary">PUBLICATIONS</a>	
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="ViewHistopathologyAction.do?aModelID=<%=mdl%>" styleClass="subMenuPrimary">HISTOPATHOLOGY</a>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="ViewTherapeuticApproachesAction.do?aModelID=<%=mdl%>" styleClass="subMenuPrimary">THERAPEUTIC APPROACHES</a>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="ViewCellLinesAction.do?aModelID=<%=mdl%>" styleClass="subMenuPrimary">CELL LINES</a>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="ViewImagesAction.do?aModelID=<%=mdl%>" styleClass="subMenuPrimary">IMAGES</a>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="ViewMicroarraysAction.do?aModelID=<%=mdl%>" styleClass="subMenuPrimary">MICROARRAYS</a>	
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="ViewTransplantXenograftAction.do?aModelID=<%=mdl%>" styleClass="subMenuPrimary">TRANSPLANT/XENOGRAFT</a>	
	<BR>
	<BR>
	<!-- Add checks to see if in submission mode, if so add VIEW MY MODELS option -->
	
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;<html:link styleClass="subMenuPrimary" action="searchResults"> BACK TO SEARCH RESULTS</html:link>	
</DIV>
</TD></TR>