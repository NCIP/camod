<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp"%>
<!-- Main Content Begins -->
<TABLE class="contentPage" cellspacing="0" cellpadding="0" width="700" summary="" border="0">
	<TBODY>
		<TR>
			<TD valign="top">
				<TABLE class="contentBegins" height="100%" cellspacing="0" cellpadding="10" width="100%" border="0">
					<TBODY>
						<TR>
							<TD>
								<TABLE class="contentPage" height="100%" cellspacing="0" cellpadding="0" width="100%" summary="" border="0">
									<TBODY>
										<TR>
											<TD valign="top">
												<TABLE class="contentBegins" cellspacing="0" cellpadding="0" width="100%" border="0">
													<TBODY>
														<TR>
															<TD>
																<TABLE cellspacing="0" cellpadding="3" width="100%" align="center" summary="" border="0">
																	<TBODY>
																		<TR>
																			<TD class="formTitle" colspan="6" height="20">
																				What's New?
																			</TD>
																		</TR>
																	</TBODY>
																</TABLE>
																
																<br>


<TABLE cellspacing="0" cellpadding="3" width="100%" align="center" summary="" border="0">
																	<TBODY>
																		<TR>
																			<TD class="formTitleBlue" colspan="2" height="20">
																				Release of caMOD 2.5.1 - June 2009
																			</TD>
																		</TR>
																		<TR>
																		<TR>
																			<TD style="BORDER-LEFT: #5c5c5c 1px solid; BORDER-BOTTOM: #5c5c5c 1px solid; BORDER-TOP: #5c5c5c 1px solid; PADDING-LEFT: 1em; FONT-SIZE: 0.7em; FONT-FAMILY: verdana, arial, helvetica, sans-serif; BACKGROUND-COLOR: #cccccc" width="20%">
																				Added Features
																			</TD>
																			<TD
																				style="BORDER-LEFT: #5c5c5c 1px solid; BORDER-RIGHT: #5c5c5c 1px solid; BORDER-BOTTOM: #5c5c5c 1px solid; BORDER-TOP: #5c5c5c 1px solid; PADDING-LEFT: 1em; FONT-SIZE: 0.7em; FONT-FAMILY: verdana, arial, helvetica, sans-serif; BACKGROUND-COLOR: #cccccc"
																				width="80%">
																				How to use the new feature?
																			</TD>
																		</TR>																	
																		
																		<TR>
																			<TD style="PADDING-LEFT: 0.8em; BORDER-LEFT: #5c5c5c 1px solid; FONT-SIZE: 0.8em; COLOR: #000000; BORDER-BOTTOM: #5c5c5c 1px solid; FONT-FAMILY: arial,helvetica,verdana,sans-serif; BACKGROUND-COLOR: #ffffff" width="20%">
																				EVS API upgrade
																			</TD>
																			<TD
																				style="PADDING-LEFT: 0.8em; BORDER-RIGHT: #5c5c5c 1px solid; BORDER-LEFT: #5c5c5c 1px solid; FONT-SIZE: 0.8em; COLOR: #000000; BORDER-BOTTOM: #5c5c5c 1px solid; FONT-FAMILY: arial,helvetica,verdana,sans-serif; BACKGROUND-COLOR: #ffffff"
																				width="80%">
																				The EVS (formerly part of caCORE ) API is a public domain, open source wrapper that provides 
																	            full access to the LexBIG Terminology Server. LexBIG hosts the NCI Thesaurus, the 
																	            NCI Metathesaurus, and several other vocabularies (like Zebrafish).
            
																	            The EVS 3.2 model, exposed as part of caCORE 3.2, has been re-released with
																	            LexBIG as the back-end terminology service in place of the proprietary Apelon DTS
																	            back end. The SDK 4.0 was used to generate the EVS 3.2 Java API, as well as the SOAP
																	            and HTTP REST interfaces.  The only difference between the EVS 3.2 API exposed as part of the caCORE EVS 4.x
																	            and the API exposed as part of caCORE 3.2 is the back-end terminology server used to
																	            retrieve the vocabulary data.             
																	            
																	            caMOD will upgrade to use the EVS 4.2 API to get the Display Name or Preferred Name for its
																	            vocabulary entries. 
																	             
																	            EVSTree has also been upgraded to retrieve all 8 vocabularies from the enw EVS 4.2 API.            
																			</TD>
																		</TR>					
																		
																		
																		
																		
																		<TR>
																			<TD style="PADDING-LEFT: 0.8em; BORDER-LEFT: #5c5c5c 1px solid; FONT-SIZE: 0.8em; COLOR: #000000; BORDER-BOTTOM: #5c5c5c 1px solid; FONT-FAMILY: arial,helvetica,verdana,sans-serif; BACKGROUND-COLOR: #ffffff" width="20%">
																				caBIO API upgrade
																			</TD>
																			<TD
																				style="PADDING-LEFT: 0.8em; BORDER-RIGHT: #5c5c5c 1px solid; BORDER-LEFT: #5c5c5c 1px solid; FONT-SIZE: 0.8em; COLOR: #000000; BORDER-BOTTOM: #5c5c5c 1px solid; FONT-FAMILY: arial,helvetica,verdana,sans-serif; BACKGROUND-COLOR: #ffffff"
																				width="80%">
																				The caMOD application connects to caBIO to retrieve additional data for the Therapeutic Approaches 
																	            and Genetic Description search results screens.  On the Therapeutic Approaches search results screen caMOD 
																	            displays clinical trials data from caBIO.  On the Genetic Description search results screen caMOD displays 
																	            additional data within the Targeted Modification section for "Gene Info" (official nomenclature of the gene), 
																	            Database Links to UniGene and CGAP, "Gene Ontology" (controlled vocabulary to describe gene and gene product attributes), 
																	            and "BioCarta Pathways".  
																	
																	            caMOD will upgrade to use the caBIO 4.0 API (jars match EVS 4.2) to get additional data from caBIO.
																				
																			</TD>
																		</TR>
																		<TR>
																			<TD style="PADDING-LEFT: 0.8em; BORDER-LEFT: #5c5c5c 1px solid; FONT-SIZE: 0.8em; COLOR: #000000; BORDER-BOTTOM: #5c5c5c 1px solid; FONT-FAMILY: arial,helvetica,verdana,sans-serif; BACKGROUND-COLOR: #ffffff" width="20%">
																				Additional Clinical Trials Information
																			</TD>
																			<TD
																				style="PADDING-LEFT: 0.8em; BORDER-RIGHT: #5c5c5c 1px solid; BORDER-LEFT: #5c5c5c 1px solid; FONT-SIZE: 0.8em; COLOR: #000000; BORDER-BOTTOM: #5c5c5c 1px solid; FONT-FAMILY: arial,helvetica,verdana,sans-serif; BACKGROUND-COLOR: #ffffff"
																				width="80%">Therapeutic experiments in animals have previously been linked to information about human clinical trials. caMOD now links to pages which provide details about the clinical trials. <br>
																				 
																				 
																			</TD>
																		</TR>
																																				<TR>
																			<TD style="PADDING-LEFT: 0.8em; BORDER-LEFT: #5c5c5c 1px solid; FONT-SIZE: 0.8em; COLOR: #000000; BORDER-BOTTOM: #5c5c5c 1px solid; FONT-FAMILY: arial,helvetica,verdana,sans-serif; BACKGROUND-COLOR: #ffffff" width="20%">
																				New species added
																			</TD>
																			<TD
																				style="PADDING-LEFT: 0.8em; BORDER-RIGHT: #5c5c5c 1px solid; BORDER-LEFT: #5c5c5c 1px solid; FONT-SIZE: 0.8em; COLOR: #000000; BORDER-BOTTOM: #5c5c5c 1px solid; FONT-FAMILY: arial,helvetica,verdana,sans-serif; BACKGROUND-COLOR: #ffffff"
																				width="80%">
																				caMOD supports information about rabbit models for cancer.
																			</TD>
																		</TR>
																		
																	</TBODY>
																</TABLE>
																<br>






																
						</TD>
							<td><br></TD>
						</TR>
					</TBODY>
				</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
<!-- Main Content Ends -->

<%@ include file="/jsp/footer.jsp"%>
