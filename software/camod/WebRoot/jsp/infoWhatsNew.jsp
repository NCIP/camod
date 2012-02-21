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
																		Release of caMOD 2.7 - Feb 2011
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
																		LexEVS connection upgraded in EVSTree
																	</TD>
																	<TD
																		style="PADDING-LEFT: 0.8em; BORDER-RIGHT: #5c5c5c 1px solid; BORDER-LEFT: #5c5c5c 1px solid; FONT-SIZE: 0.8em; COLOR: #000000; BORDER-BOTTOM: #5c5c5c 1px solid; FONT-FAMILY: arial,helvetica,verdana,sans-serif; BACKGROUND-COLOR: #ffffff"
																		width="80%">
																		The connection to the LexEVS API was upgraded to use version 5.1 in the EVSTree application.  The data displayed to the user remains the same. 
																		The search functionality was upgraded. 
																	</TD>
																</TR>
																<TR>
																	<TD style="PADDING-LEFT: 0.8em; BORDER-LEFT: #5c5c5c 1px solid; FONT-SIZE: 0.8em; COLOR: #000000; BORDER-BOTTOM: #5c5c5c 1px solid; FONT-FAMILY: arial,helvetica,verdana,sans-serif; BACKGROUND-COLOR: #ffffff" width="20%">
																		Various bug fixes related to the LexEVS 5.1 upgrade.
																	</TD>
																	<TD
																		style="PADDING-LEFT: 0.8em; BORDER-RIGHT: #5c5c5c 1px solid; BORDER-LEFT: #5c5c5c 1px solid; FONT-SIZE: 0.8em; COLOR: #000000; BORDER-BOTTOM: #5c5c5c 1px solid; FONT-FAMILY: arial,helvetica,verdana,sans-serif; BACKGROUND-COLOR: #ffffff"
																		width="80%">
																		There were various fixes made that directly related to the changes in the LexEVS API.  The organs are being retrieved from the database for performance and the Therapy sections were upgraded.  In addition, metastatis data was loaded from a previous source.  
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
