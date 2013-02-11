<%@ include file="/common/taglibs.jsp"%>
<%@ page import="gov.nih.nci.camod.Constants" %>

<TR><TD class=subMenuPrimaryTitle height=22>SEARCH MODELS</TD></TR>
<TR><TD class=subMenuPrimaryItems>
<DIV>
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5>
	&nbsp;<html:link styleClass="subMenuPrimary" action="SearchTableOfContentsPopulateAction?unprotected_method=populate">TABLE OF CONTENTS</html:link>
	<BR>
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5>
	&nbsp;<html:link styleClass="subMenuPrimary" action="SimpleSearchPopulateAction?unprotected_method=populate">SIMPLE SEARCH</html:link>
	<BR>	
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5>
	&nbsp;<html:link styleClass="subMenuPrimary" action="AdvancedSearchPopulateAction?unprotected_method=populate">ADVANCED SEARCH</html:link>
	<BR>
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5>
	&nbsp;<html:link styleClass="subMenuPrimary" action="searchDrugScreening">DRUG SCREENING</html:link>
	<BR>	
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5>
	&nbsp;<html:link styleClass="subMenuPrimary" action="searchResults">SEARCH RESULTS</html:link>
	<BR>	
	<IMG height=5 alt="" src="/camod/images/subMenuArrow.gif" width=5>
	&nbsp;<html:link styleId="<%=Constants.USER_GUIDE_WIKI_LINK%>"  styleClass="subMenuPrimary" href='javascript:void(0)' onclick="myRef = window.open(this.id,'mywin',
															'left=20,top=20,width=700,height=500,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()">HELP</html:link>
	<BR>		
</DIV>
</TD></TR>




