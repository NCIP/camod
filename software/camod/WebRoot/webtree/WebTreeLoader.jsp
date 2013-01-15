<%
  	// get parameters
    String treeParams     = (String) request.getParameter("treeParams");

    // URL encode parameters
    if (treeParams != null) treeParams = java.net.URLEncoder.encode(treeParams);    
    
%>

<script language="JavaScript1.2">
  var now = new Date();
  var glob = now.getHours()+now.getSeconds()+now.getMilliseconds();
  window.document.write("<br>Building tree, please wait...<br> <br>");  
  var targetURL = "WebTree.jsp?&treeParams=<%=treeParams%>&skin=evsMOD&glob="+glob;
  window.location.href = targetURL;     
</script>
