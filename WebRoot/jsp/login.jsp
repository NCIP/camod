<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.Constants" %>

<%@ page buffer="32kb"%>

<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<SCRIPT src="/scripts/TipMessages.js" type=text/javascript></SCRIPT>	

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
                                              Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi.
                                              <a href="login.jsp"  onMouseOver="stm(Text[0],Style[0])" onMouseOut="htm()">Tool Tip Test 1</a>
                                              <a href='javascript: rs("commentWin","submitComment.jsp",435,265);'>Comment Test</a>
                                              <br>
                                              Tag Test: <bean:message key="mainMenu.message"/> :Tag Test	<br>
                                              Strut Tag Test: <camod:cshelp key="ABS_CANCER_MODEL.MODEL_DESCRIPTOR" image="images/iconHelp.gif" text="Tool Tip Test 1" />:tag test		    		                              
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
                                                      <td class="sidebarTitle" height="20">LOGIN TO caMOD</td>
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
                                                            <%
					                                      		if( session.getAttribute( Constants.LOGINFAILED ) == "true" ) {
					                                      			session.setAttribute( Constants.LOGINFAILED , null );
					                                      			%>
					                                      				<tr><td colspan="3">					                                      				
						                                      				<span id="errorsHeader">
						                                      					<bean:message key="error.login.required"/>
					                                      					</span>
				                                      				   </td></tr>
				                                      				<%
					                                      		}		                                      		
					                                      	%>  
					                                      	
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
                                                                      <html:submit>
                                                                          <bean:message key="button.submit"/>
                                                                      </html:submit>
                                                                      </html:form>
                                                                  </td>
				      
                                                                  <td>		      		
                                                                      <FORM name="input" action="register.jsp" method="get">
                                                                          <CENTER><input class="actionButton" type="submit" value="Register" /></CENTER>
                                                                      </FORM>			      		
                                                                  </td>
				      
                                                                  <td>					    
                                                                      <FORM name="input" action="help.jsp" method="get">
                                                                          <input class="actionButton" type="submit" value="Help" />
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
                                                      <td class="sidebarContent"><html:link action="infoWhatsNew"><IMG src="images/bullet_point.gif" border=0>&nbsp;View What's New</html:link></td>
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
                                                      <td class="sidebarContent" valign="top">Did you know message. Did you know message. Did you know message. Did you know message. </td>

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