<%
System.setProperty("log4j.configuration", "../WEB-INF/conf/log4j.properties");

	// skin parameter hard-coded in all jsp to pass security scan
	// Must find another way to toggle skins - keep parameter out of jsp pages
    String treeParams     = (String) request.getParameter("treeParams");
    String windowTitle    = (String) request.getParameter("windowTitle");
    
    // windowTitle parameter hard-coded in jsp to pass security scan
    if (windowTitle != "WebTree Navigator v1.2") 
    windowTitle = "WebTree Navigator v1.2";
    
    // URL encode parameters

    if (treeParams != null) treeParams = java.net.URLEncoder.encode(treeParams);    

%>

<HTML>
<script language="JavaScript1.2" src="skins/evsMOD/JavaScript.js"></script>
<HEAD> 
<TITLE><%=windowTitle%></TITLE>
<META http-equiv="Content-Type" content="text/html;">
</HEAD>
<frameset rows="126,*" framespacing="0" frameborder="0" name="master">
  <frame src="skins/evsMOD/TopHeader.html" name="headerFrame" frameborder="no" scrolling="no" noresize marginwidth="0" marginheight="0" id="headerFrame">
  <frameset rows="*" cols="400,*" framespacing="0" frameborder="NO" border="0">
    <frameset rows="52,*" framespacing="0" frameborder="no" border="0">
      <frame src="skins/evsMOD/SearchHeader.html" name="searchHeader" scrolling="NO" noresize id="searchHeader" >
      <frameset rows="120,*" cols="*" framespacing="0" frameborder="no" border="0">
        <frameset rows="3,*" framespacing="0" frameborder="no" border="0">
          <frame src="DividerFrame.jsp?skin=evsMOD" name="divider1" scrolling="NO" noresize>
          <frameset rows="*,3" cols="*" framespacing="0" frameborder="NO" border="0">
            <frame src="SearchTree.jsp?skin=evsMOD" name="searchInput" frameborder="no" scrolling="no" marginwidth="10" marginheight="10" id="searchContent">
            <frame src="DividerFrame.jsp?skin=evsMOD" name="divider2" scrolling="NO" noresize>
          </frameset>
        </frameset>
        <frameset rows="52,*" cols="*" framespacing="0" frameborder="no" border="0">
          <frame src="skins/evsMOD/ResultsHeader.html" name="resultsHeader" frameborder="no" scrolling="NO" noresize marginwidth="0" id="resultsHeader" >
          <frameset rows="3,*" cols="*" framespacing="0" frameborder="NO" border="0">
            <frame src="DividerFrame.jsp?skin=evsMOD" name="divider3" scrolling="NO" noresize>
            <frame src="SearchResults.jsp?skin=evsMOD" name="searchResults" frameborder="no" marginwidth="15" marginheight="10" id="resultsContent">
          </frameset>
        </frameset>
      </frameset>
    </frameset>
    <frameset rows="*" cols="3,*" framespacing="0" frameborder="no" border="0">
      <frame src="DividerFrame.jsp?skin=evsMOD" name="divider4" scrolling="NO" noresize>
      <frameset rows="52,*" frameborder="NO" border="0" framespacing="0">
        <frame src="skins/evsMOD/TreeBrowserHeader.html" name="treeHeader" scrolling="NO" noresize id="treeHeader" >
        <frameset rows="3,*" frameborder="NO" border="0" framespacing="0">
          <frame src="DividerFrame.jsp?skin=evsMOD" name="divider5" scrolling="NO" noresize>
          <frame src="WebTreeLoader.jsp?treeParams=<%=treeParams%>&skin=evsMOD" name="tree" frameborder="no" marginwidth="20" marginheight="10" id="treeContent">
        </frameset>
      </frameset>
    </frameset>
  </frameset>
</frameset>
<noframes><body>
To view this page, your browser must be set to accept FRAMES. Please modify your browser settings.
</body></noframes>    

</HTML>
