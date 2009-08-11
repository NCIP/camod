<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="gov.nih.nci.camod.Constants.CaImage" %>
<%@ page import="java.util.*" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	String aFileServerLocation = request.getParameter( "aFileServerLocation" );
	     
	// Retrieve ftp data from a resource bundle
	ResourceBundle theBundle = ResourceBundle.getBundle("camod");
	
	// Retrieve the server location
	String caImageServer = theBundle.getString(CaImage.CAIMAGESERVERVIEW);

	System.out.println( "caImageServer=" + caImageServer );

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'viewLizardImage.jsp' starting page</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
  </head>
  
  <body>
	The filename is:  <%= aFileServerLocation %> <br>
	<Img src="<%= caImageServer %><%= aFileServerLocation %>" width="400" height="400" border="0" /><br><br>			

  </body>
</html>
