<%@ include file="/common/taglibs.jsp"%>

<TR><TD class=subMenuPrimaryTitle height=22>MODEL DETAILS</TD></TR>
<TR><TD class=subMenuPrimaryGreyTitle height=27>Viewing Model: <b>PB-p53(R172L)</b></TD></TR>
<TR><TD class=subMenuPrimaryItems>
<DIV>
	<bean:parameter id="mdl" name="aModelID"/>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="viewModelAction.do?aModelID=<%=mdl%>&page=ModelCharacteristics" styleClass="subMenuPrimary">MODEL CHARACTERISTICS</a>
	<BR>	
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="viewModelAction.do?aModelID=<%=mdl%>&page=GeneticDescription" styleClass="subMenuPrimary">GENETIC DESCRIPTION</a>	
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="viewModelAction.do?aModelID=<%=mdl%>&page=CarcinogenicInterventions" styleClass="subMenuPrimary">CARCINOGENIC INTERVENTIONS</a>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="viewModelAction.do?aModelID=<%=mdl%>&page=Publications" styleClass="subMenuPrimary">PUBLICATIONS</a>	
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="viewModelAction.do?aModelID=<%=mdl%>&page=Histopathology" styleClass="subMenuPrimary">HISTOPATHOLOGY</a>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="viewModelAction.do?aModelID=<%=mdl%>&page=TherapeuticApproaches" styleClass="subMenuPrimary">THERAPEUTIC APPROACHES</a>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="viewModelAction.do?aModelID=<%=mdl%>&page=CellLines" styleClass="subMenuPrimary">CELL LINES</a>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="viewModelAction.do?aModelID=<%=mdl%>&page=Images" styleClass="subMenuPrimary">IMAGES</a>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="viewModelAction.do?aModelID=<%=mdl%>&page=Microarrays" styleClass="subMenuPrimary">MICROARRAYS</a>	
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<a href="viewModelAction.do?aModelID=<%=mdl%>&page=TransplantXenograft" styleClass="subMenuPrimary">TRANSPLANT/XENOGRAFT</a>	
	<BR>
	<BR>
	<!-- Add checks to see if in submission mode, if so add VIEW MY MODELS option -->
	
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;<html:link styleClass="subMenuPrimary" action="searchResults"> BACK TO SEARCH RESULTS</html:link>	
</DIV>
</TD></TR>