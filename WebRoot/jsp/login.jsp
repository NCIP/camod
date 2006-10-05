<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.Constants" %>

<%@ page buffer="32kb"%>

<% pageContext.getSession().setAttribute("READDISCLAIMER", "true");  %>

<!-- needed for tooltips -->
<script language="JavaScript" src="scripts/global.js"></script>
<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>	

<!-- login.jsp -->
<!-- Main Content Begins -->  
	  <table summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">

              <!-- banner begins -->
              <tr>
                  <td valign=top class="bannerHome"><img src="images/banner.gif"></td>
              </tr>
              <!-- banner begins -->

              <tr>
                  <td height="100%">
                      <table summary="" cellpadding="0" cellspacing="0" border="0" height="100%">
                          <tr>
                              <td width="70%">
                              
                                  <!-- welcome begins -->
                                  <table summary="" cellpadding="0" cellspacing="0" border="0" height="100%">
                                      <tr><td class="welcomeTitle" height="20">Welcome to the Cancer Models Database</td>
                                      </tr>
                                      <tr>
                                          <td class="welcomeContent" valign="top">
<a href="/camod/SimpleSearchPopulateAction.do?unprotected_method=populate">Search Models</a>
 - Query the Cancer Models database for models submitted by fellow researchers. Retrieve information about the making of models, their genetic description, histopathology, derived cell lines, associated images, carcinogenic agents, and therapeutic trials. Links to associated publications and other resources are provided.
<br><br>
<a href="/camod/ReturnUserModels.do?method=returnUserModels">Submit Models</a>
 - Submit your model for human cancer here. Rodent models recapitulate many aspects of the genesis, progression, and clinical course of human cancers and are valuable resources to cancer researchers engaged in a variety of investigations
                                          </td>
                                      </tr>
                                  </table>	
                                  <!-- welcome ends -->

                              </td>
                              <td valign="top" width="30%">

                                  <!-- sidebar begins -->
                                  <table summary="" cellpadding="0" cellspacing="0" border="0" height="100%">
                                     <%                      
                                      // if user is already logged in, do not display the username/password login fields
                                      if ( request.getSession().getAttribute( "camod.loggedon.username" )== null ) { 
                                      %>	
                                      <!-- login begins -->
                                      <tr>
                                          <td valign="top">

                                              <table summary="" cellpadding="2" cellspacing="0" border="0" width="100%" class="sidebarSection">
                                                  <tr>
                                                      <td class="sidebarTitle" height="20">LOGIN TO caMOD &nbsp 
                                                      <camod:cshelp topic="login_page_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/></td>
                                                  </tr>
                                                  <tr>
                                                      <td colspan="3">
                                                          <table cellpadding="2" cellspacing="0" border="0">                                                                                                                                                              					                                                                                                                   
                                                              <logic:messagesPresent>
	                                                                  <tr>
						                                                  
								                                          <b><font color=red>
								                                              <html:messages id="errors">
									                                              <%=errors %>
								                                              </html:messages>
								                                          </font></b>
				                                                      </tr>
				                                              </logic:messagesPresent>
				                                              <c:if test="${notloggedin == 'true'}">
				                                                  <c:set var="notloggedin" value="false" scope="session"/>
				                                      			  <tr><td colspan="3">					                                      				
					                                      		      <span id="errorsHeader">
					                                      			      <bean:message key="error.login.required"/>
				                                      				  </span>
			                                      				  </td></tr>
				                                      		  </c:if>
				                                      		  <c:if test="${loginfailed == 'true'}">
					                                      	      <c:set var="loginfailed" value="false" scope="session"/>
				                                      		      <tr><td colspan="3">					                                      				
					                                      		      <span id="errorsHeader">
					                                      		          <bean:message key="errors.validation.header"/>
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
				      
                                                                  <td>		      		
                                                                      <FORM name="input" action="/camod/RegisterUserPopulateAction.do" method="get">
                                                                          <input class="actionButton" type="submit" value="Register" />
                                                                      </FORM>			      		
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
                                              <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%" class="sidebarSection">
                                                  <tr>
                                                      <td class="sidebarTitle" height="20">WHAT'S NEW</td>
                                                  </tr>

                                                  <tr>
                                                      <td class="sidebarContent"><IMG src="images/bullet_point.gif" border=0>&nbsp;<html:link action="infoWhatsNew">View What's New</html:link></td>
                                                  </tr>
                                              </table>
                                          </td>
                                      </tr>
                                      <!-- what's new ends -->

                                      <!-- did you know? begins -->
                                      <tr>
                                          <td valign="top">
                                              <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%" height="100%" class="sidebarSection">
                                                  <tr>
                                                      <td class="sidebarTitle" height="20">DID YOU KNOW?</td>
                                                  </tr>
                                                  <tr>
                                                      <td class="sidebarContent" valign="top">caMOD 2.1 was released to production on May 30th.<br/><br/><html:link action="infoReleaseNotes.do">Release Notes</html:link></td>
                                                  </tr>
                                                  <tr>
													<td> &nbsp; 
														<input type="submit" value="User Guide" onClick="myRef = window.open('WebHelp/!SSL!/UserGuide.pdf','mywin',
															'left=20,top=20,width=700,height=500,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()">
														</input>&nbsp;
														<input type="submit" value="Help" onClick="myRef = window.open('WebHelp/!SSL!/index.html','mywin',
															'left=20,top=20,width=700,height=500,status=1,scrollbars=1,toolbar=1,resizable=0');myRef.focus()">
														</input>
													</td>                                                                  
                                              	</tr>
                                              </table>
                                          </td>
                                      </tr>
                                      <!-- did you know? ends -->
										
                                      <!-- spacer cell begins (keep for dynamic expanding) -->
                                      <tr><td valign="top" height="100%">
                                          <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%" height="100%" class="sidebarSection">

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

<%@ include file="footer.jsp" %>