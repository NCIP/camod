
<HTML>
<HEAD>
<TITLE>Result Page</TITLE>
</HEAD>
<%@ page import="java.net.*,
				java.util.*" %>
				
<% 
String params="";
Enumeration p = request.getParameterNames();
   	while(p.hasMoreElements())
   	{
   		String param = (String)p.nextElement();
   		String value = request.getParameter(param);
   		params += param + "=" + value + "&";
        //System.out.println("param = " + params);
   	}
 String query = "Result.jsp?" + params.substring(0, params.length() - 1);
 System.out.println("query: " + query);
 %>  	
<FRAMESET rows="10%, 80%">  
      <FRAME name="fixed" src="Return.jsp" frameborder="0">      
      <FRAME name="dynamic_content" src="<%=query %>" frameborder="0">   
</FRAMESET>
</HTML>

