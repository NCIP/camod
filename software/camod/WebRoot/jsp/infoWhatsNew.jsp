<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp"%>
<!-- Main Content Begins -->
<TABLE class="contentPage" cellspacing="0" cellpadding="0" width="100%" summary="This table is used to format page content" border="0">
	<!-- Took this out of sidebar.jsp and has to go here to format correctly -->
	<%@ include file="/jsp/searchMenu.jsp" %>
	<TBODY><TR><TD valign="top">
		<TABLE summary="This table is used to format page content" class="contentBegins" height="100%" cellspacing="0" cellpadding="10" width="60%" border="0">
			<TBODY><TR><TD>
				<TABLE class="contentPage" height="100%" cellspacing="0" cellpadding="0" width="100%" summary="This table is used to format page content" border="0">
						<TBODY><TR><TD valign="top">
								<TABLE summary="This table is used to format page content" class="contentBegins" cellspacing="0" cellpadding="0" width="100%" border="0">
										<TBODY><TR><TD>
												<TABLE cellspacing="0" cellpadding="3" width="100%" align="center" summary="This table is used to format page content" border="0">
													<TBODY>
														<TR><TD class="formTitle" colspan="6" height="20">
																What's New?	</TD>
														</TR>
													</TBODY>
												</TABLE><br>


												<TABLE cellspacing="0" cellpadding="3" width="100%" align="center" summary="This table is used to format page content" border="0">
													<TBODY>
														   <TR>													
																<TR>
																	<TD class="formTitleBlue" colspan="2" height="20">
																		Release of caMOD 2.7.2 - October 2012
																	</TD>
																</TR>

																<TR>
																	<TD style="BORDER-LEFT: #5c5c5c 1px solid; BORDER-BOTTOM: #5c5c5c 1px solid; BORDER-TOP: #5c5c5c 1px solid; PADDING-LEFT: 1em; FONT-SIZE: 0.7em; FONT-FAMILY: verdana, arial, helvetica, sans-serif; BACKGROUND-COLOR: #cccccc" width="20%">
																		Added Features
																	</TD>
																	<TD
																		style="BORDER-LEFT: #5c5c5c 1px solid; BORDER-RIGHT: #5c5c5c 1px solid; BORDER-BOTTOM: #5c5c5c 1px solid; BORDER-TOP: #5c5c5c 1px solid; PADDING-LEFT: 1em; FONT-SIZE: 0.7em; FONT-FAMILY: verdana, arial, helvetica, sans-serif; BACKGROUND-COLOR: #cccccc"
																		width="80%">
																		Description of the new feature?
																	</TD>
																</TR>
																<TR>
																	<TD style="PADDING-LEFT: 0.8em; BORDER-LEFT: #5c5c5c 1px solid; FONT-SIZE: 0.8em; COLOR: #000000; BORDER-BOTTOM: #5c5c5c 1px solid; FONT-FAMILY: arial,helvetica,verdana,sans-serif; BACKGROUND-COLOR: #ffffff" width="20%">
																		Model Connection Changes
																	</TD>
																	<TD
																		style="PADDING-LEFT: 0.8em; BORDER-RIGHT: #5c5c5c 1px solid; BORDER-LEFT: #5c5c5c 1px solid; FONT-SIZE: 0.8em; COLOR: #000000; BORDER-BOTTOM: #5c5c5c 1px solid; FONT-FAMILY: arial,helvetica,verdana,sans-serif; BACKGROUND-COLOR: #ffffff"
																		width="80%">
																		Models are considered to be related when they have one or more of the following attributes have common values. They are the following: <br />
																		<ul>
																			<li>Entez Gene ID</li>
																			<li>Allele identifier</li>
																			<li>PMID number</li>
																			<li>NSC number</li>
																			<li>Microarray experiment data</li>
																		</ul>  
																		caMOD has been enhanced to display the related models as links within various pages. The following pages will display the related models if available.<br/>
																		<ul>
																			<li>GENETIC DESCRIPTION</li>
																			<li>PUBLICATIONS</li>
																			<li>THERAPEUTIC APPROACHES</li> 
																			<li>MICROARRAYS</li>
																		</ul>
																	</TD>
																</TR>																
																<TR>
																	<TD style="PADDING-LEFT: 0.8em; BORDER-LEFT: #5c5c5c 1px solid; FONT-SIZE: 0.8em; COLOR: #000000; BORDER-BOTTOM: #5c5c5c 1px solid; FONT-FAMILY: arial,helvetica,verdana,sans-serif; BACKGROUND-COLOR: #ffffff" width="20%">
																		Various bug-fixes
																	</TD>
																	<TD
																		style="PADDING-LEFT: 0.8em; BORDER-RIGHT: #5c5c5c 1px solid; BORDER-LEFT: #5c5c5c 1px solid; FONT-SIZE: 0.8em; COLOR: #000000; BORDER-BOTTOM: #5c5c5c 1px solid; FONT-FAMILY: arial,helvetica,verdana,sans-serif; BACKGROUND-COLOR: #ffffff"
																		width="80%">
																		Issue with duplication does not work for some fields has been fixed. (JIRA# CAMOD-656) <br/>
																		Issue with images are not being displayed for some models has been fixed. (JIRA# CAMOD-994) <br/>
																		Home Page link broken while viewing search results has been fixed. (JIRA# CAMOD-1010) <br/>
																	</TD>
																</TR>
														</TR>			
												    </TBODY>
											    </TABLE><br>																
						                </TD></TR></TBODY>
						        </TABLE>    
				      <TD><br></TD>
			      </TABLE>
	      </TD></TR></TBODY>
      </TABLE>
   </TD></TR></TBODY>
</TABLE>      
<!-- Main Content Ends -->

<%@ include file="/jsp/footer.jsp"%>
