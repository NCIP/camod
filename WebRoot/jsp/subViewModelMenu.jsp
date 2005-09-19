<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.Constants" %>
<%@ page import="java.util.List" %>

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
		<a href="ViewModelAction.do?unprotected_method=populateModelCharacteristics&aModelID=<%=mdl%>" styleClass="subMenuPrimary">MODEL CHARACTERISTICS</a>
	<BR>	
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<% 
			AnimalModel am = (AnimalModel)request.getSession().getAttribute(Constants.ANIMALMODEL);
			List l = am.getEngineeredGeneCollection();
			int cc = (l!=null)?l.size():0;
			if ( cc > 0 ) { %>
		<a href="ViewModelAction.do?unprotected_method=populateEngineeredGene&aModelID=<%=mdl%>" styleClass="subMenuPrimary">GENETIC DESCRIPTION</a>	
	    <%} else { %>
		GENETIC DESCRIPTION
		<%}%>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<% 
			l = am.getEnvironmentalFactorCollection();
			cc = (l!=null)?l.size():0;
			if ( cc > 0 ) { 
		%>
		<a href="ViewModelAction.do?unprotected_method=populateCarcinogenicInterventions&aModelID=<%=mdl%>" styleClass="subMenuPrimary">CARCINOGENIC INTERVENTIONS</a>
	    <%} else { %>
		CARCINOGENIC INTERVENTIONS
		<%}%>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<% 
			l = am.getPublicationCollection();
			cc = (l!=null)?l.size():0;
			if ( cc > 0 ) { 
		%>
		<a href="ViewModelAction.do?unprotected_method=populatePublications&aModelID=<%=mdl%>" styleClass="subMenuPrimary">PUBLICATIONS</a>	
	    <%} else { %>
		PUBLICATIONS
		<%}%>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<% 
			l = am.getHistopathologyCollection();
			cc = (l!=null)?l.size():0;
			if ( cc > 0 ) { 
		%>
		<a href="ViewModelAction.do?unprotected_method=populateHistopathology&aModelID=<%=mdl%>" styleClass="subMenuPrimary">HISTOPATHOLOGY</a>
	    <%} else { %>
		HISTOPATHOLOGY
		<%}%>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<% 
			l = am.getTherapyCollection();
			cc = (l!=null)?l.size():0;
			if ( cc > 0 ) { 
		%>
		<a href="ViewModelAction.do?unprotected_method=populateTherapeuticApproaches&aModelID=<%=mdl%>" styleClass="subMenuPrimary">THERAPEUTIC APPROACHES</a>
	    <%} else { %>
		THERAPEUTIC APPROACHES
		<%}%>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<% 
			l = am.getCellLineCollection();
			cc = (l!=null)?l.size():0;
			if ( cc > 0 ) { 
		%>
		<a href="ViewModelAction.do?unprotected_method=populateCellLines&aModelID=<%=mdl%>" styleClass="subMenuPrimary">CELL LINES</a>
	    <%} else { %>
		CELL LINES
		<%}%>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<% 
			l = am.getImageCollection();
			cc = (l!=null)?l.size():0;
			if ( cc > 0 ) { 
		%>
		<a href="ViewModelAction.do?unprotected_method=populateImages&aModelID=<%=mdl%>" styleClass="subMenuPrimary">IMAGES</a>
	    <%} else { %>
		IMAGES
		<%}%>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<% 
			l = am.getMicroArrayDataCollection();
			cc = (l!=null)?l.size():0;
			if ( cc > 0 ) { 
		%>
		<a href="ViewModelAction.do?unprotected_method=populateMicroarrays&aModelID=<%=mdl%>" styleClass="subMenuPrimary">MICROARRAYS</a>	
	    <%} else { %>
		MICROARRAYS
		<%}%>
	<BR>
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;
		<% 
			l = am.getXenograftCollection();
			cc = (l!=null)?l.size():0;
			if ( cc > 0 ) {
		%>
		<a href="ViewModelAction.do?unprotected_method=populateTransplantXenograft&aModelID=<%=mdl%>" styleClass="subMenuPrimary">TRANSPLANT/XENOGRAFT</a>	
	    <%} else { %>
			TRANSPLANT/XENOGRAFT
		<%}%>
	<BR>
	<BR>
	<!-- Add checks to see if in submission mode, if so add VIEW MY MODELS option -->
	
	<IMG height=5 alt="" src="images/subMenuArrow.gif" width=5>&nbsp;&nbsp;<html:link styleClass="subMenuPrimary" action="searchResults"> BACK TO SEARCH RESULTS</html:link>	
</DIV>
</TD></TR>