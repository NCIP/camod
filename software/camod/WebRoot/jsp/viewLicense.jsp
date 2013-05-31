<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.Constants" %>

<%@ page buffer="32kb"%>

<% pageContext.getSession().setAttribute("READDISCLAIMER", "true");  %>


<head>
<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
</head>

<!-- needed for tooltips -->
<script language="JavaScript" src="scripts/global.js"></script>
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>	

<!-- login.jsp -->
<!-- Main Content Begins -->  
	  <table summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="55%" height="90%">
	  <!-- Took this out of sidebar.jsp and has to go here to format correctly - width must be < 75% above to display correctly -->
	  <%@ include file="/jsp/homeMenu.jsp" %>
              <!-- banner begins -->
              <tr>
                  <td valign=top class="bannerHome"><img alt="Banner" src="/camod/images/banner.gif"></td>
              </tr>
              <!-- banner begins -->

              <tr>
                  <td height="100%">
                      <table summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" height="100%" >
                          <tr>
                              <td width="70%">
                              
                                  <!-- welcome begins -->
                                  <table summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" height="100%">
                                      <tr><td class="welcomeTitle" height="20">Welcome to the Cancer Models Database</td>
                                      </tr>
                                      <tr>
                                          <td class="welcomeContent" valign="top">
<a href="/camod/SimpleSearchPopulateAction.do?unprotected_method=populate">Search Models</a>
 - Query the Cancer Models database for models submitted by fellow researchers. Retrieve information about the making of models, their genetic description, histopathology, derived cell lines, associated images, carcinogenic agents, and therapeutic trials. Links to associated publications and other resources are provided.
<br><br>
<a href="/camod/ReturnUserModels.do?method=returnUserModels">Submit Models</a>
 - Submit your model for human cancer here. Animal models recapitulate many aspects of the genesis, progression, and clinical course of human cancers and are valuable resources to cancer researchers engaged in a variety of investigations
                                          </td>
                                      </tr>
                                  </table>	
                                  <!-- welcome ends -->

                              </td>
                              <td valign="top" width="30%">

                                  <!-- sidebar begins -->
                                  <table summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" height="100%">
                                     <%                      
                                      // if user is already logged in, do not display the username/password login fields
                                      if ( request.getSession().getAttribute( "READDISCLAIMER" )== null ) { 
                                      %>	
                                      <!-- login begins -->
                                      <tr>
                                          <td valign="top">

                                              <table summary="This table is used to format page content" cellpadding="2" cellspacing="0" border="0" width="100%" class="sidebarSection">
                                                  <tr>
                                                      <td class="sidebarTitle" height="20">LOGIN TO caMOD &nbsp; 
                                                      <camod:cshelp topic="login_page_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/>&nbsp;
                                                      </td>
                                                   </tr>
                                                  <tr>
                                                      <td colspan="3">
                                                          <table summary="This table is used to format page content" cellpadding="2" cellspacing="0" border="0">                                                                                                                                                              					                                                                                                                   
                                                              <logic:messagesPresent>
	                                                                  <tr>
						                                                  
								                                          <b><font color=red>
								                                              <html:messages id="errors">
									                                              <%=errors %>
								                                              </html:messages>
								                                          </font></b>
				                                                      </tr>
				                                              </logic:messagesPresent>
				                                              <c:if test="${loggedin == 'false'}">
				                                                  <c:set var="loggedin" value="true" scope="session"/>
				                                      			  <tr><td colspan="3">					                                      				
					                                      		      <span id="errorsHeader">
					                                      			      <bean:message key="error.login.required"/>
				                                      				  </span>
			                                      				  </td></tr>
				                                      		  </c:if>
				                                      		  <c:if test="${loginfailed == 'true'}">
					                                      	      <c:set var="loginfailed" value="false" scope="session"/>
				                                      		      <tr><td colspan="3">
				                                      		      		<html:errors/>					                                      				
					                                      		      <span id="errorsHeader">
					                                      		          <bean:message key="error.login.invalid"/>
				                                      			      </span>
			                                      				  </td></tr>
					                                      	  </c:if> 
                                                              <tr>                                      
                                                                  <html:form action="LoginAction.do" focus="username">                                      
                                                                  <td class="sidebarLogin" align="right"><label for="loginID">Login ID</label></td>
                                                                  <td class="formFieldLogin"><html:text property="username" size="15" maxlength="15" /></td>
                                                              </tr>
                                                              <tr>
                                                                  <td class="sidebarLogin" align="right"><label for="password">Password</label></td>
                                                                  <td class="formFieldLogin"><html:password property="password" size="15" maxlength="15" /></td>
                                                              </tr>
                                                              <tr>
                                                                  <td>
                                                                      <html:submit value="Login"/>
                                                                      </html:form>
                                                                  </td>				      
                                                              </tr>                                                             
                                                          </table>
                                                          
                                                      </td>
                                                  </tr>
                                              </table>
                                          </td>                                         
                                      </tr>
                                   
                                       <% } else { } %>
                                      <!-- login ends -->
                                      
                                     

                                      <!-- what's new begins -->
                                      <tr>
                                          <td valign="top">
                                              <table summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" width="100%" class="sidebarSection">
                                                  <tr>
                                                      <td class="sidebarTitle" height="20">WHAT'S NEW</td>
                                                  </tr>

                                                  <tr>
                                                      <td class="sidebarContent"><IMG alt="What's new" src="/camod/images/bullet_point.gif" border=0>&nbsp;<html:link action="infoWhatsNew">View What's New</html:link></td>
                                                  </tr>
                                              </table>
                                          </td>
                                      </tr>
                                      <!-- what's new ends -->

                                      <!-- did you know? begins -->
                                      <tr>
                                          <td valign="top">
                                              <table summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" width="100%" height="100%" class="sidebarSection">
                                                  <tr>
                                                      <td class="sidebarTitle" height="20">LEGAL RULES OF THE ROAD</td>
                                                  </tr>
                                                  <tr>
                                                      <td class="sidebarContent" valign="top">
                                                      Please read this license agreement carefully before using this website.&nbsp;&nbsp;
                                                      By accessing or using this website, you signify your agreement to the terms of use.<br/><br/>
                                                      <html:link action="infoViewLicense.do">View License</html:link><br/><br/>
                                                      </td>
                                                  </tr>                                              
                                                  <tr>
                                                      <td class="sidebarTitle" height="20">DID YOU KNOW?</td>
                                                  </tr>
                                                  <tr>
                                                      <td class="sidebarContent" valign="top">caMOD 2.7.1 was released in September 2012.<br/><br/>
                                                      <html:link action="infoReleaseNotes.do">Release Notes</html:link><br/><br/>
                                                      </td>
                                                  </tr>
                                                  
                                                <tr>
                                                   <td class="sidebarContent" >                                                   
													   <% 
												    	// get and clean header to prevent SQL injection
												    	String isloginpage = ((HttpServletRequest) request).getRequestURI();
												        boolean isRequestedSessionIdFromURL = ((HttpServletRequest) request).isRequestedSessionIdFromURL();
													    if(isloginpage!=null && !isRequestedSessionIdFromURL &&(
													    		isloginpage.endsWith("/camod/")   ))	{
												       %>
														<!-- works when opening URL -->
														<input type="submit" value="User Guide" onClick="myRef = window.open('./WebHelp/caMOD_Online_Help/UserGuide.pdf','mywin',
															'left=20,top=20,width=900,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"/>
														&nbsp;
														<input type="submit" value="Help" onClick="myRef = window.open('./WebHelp/caMOD_Online_Help/index.html','mywin',
															'left=20,top=20,width=700,height=500,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"/><br/><br/>												                                                            


														<% } else { %>
                                                        <!--  works from search link for Home and submit link for HOME -->
														<input type="submit" value="User Guide" onClick="myRef = window.open('../WebHelp/caMOD_Online_Help/UserGuide.pdf','mywin',
															'left=20,top=20,width=900,height=700,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()"/>
														&nbsp;
														<input type="submit" value="Help" onClick="myRef = window.open('../WebHelp/caMOD_Online_Help/index.html','mywin',
															'left=20,top=20,width=700,height=500,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()"/><br/><br/>														

				                                        <% } %>
				                                        <div style="padding:5px;margin:5px; background-color:#fff; border:1px solid #416599;">
															To view the User Guide, you may need to install the Adobe PDF Reader for your browser. Please click <a target="_new" href="http://get.adobe.com/reader/">here</a> to download this free plug-in.<br/>
														</div>
													</td>                                                                  
                                              	</tr>
                                              </table>
                                          </td>
                                      </tr>
                                      <!-- did you know? ends -->
										
                                      <!-- spacer cell begins (keep for dynamic expanding) -->
                                      <tr><td valign="top" height="100%">
                                          <table summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" width="100%" height="100%" class="sidebarSection">

                                              <tr>
                                                  <td class="sidebarContent" valign="top">&nbsp;</td>
                                              </tr>
                                          </table>
                                      </td></tr>
                                      <!-- spacer cell ends -->

                                  </table>
                                  <!-- sidebar ends -->

                              </td>
                          </tr>
                      </table>
                  </td>
              </tr>
	  </table>

<!-- Main Content Ends  -->

<%@ include file="/jsp/footer.jsp" %>