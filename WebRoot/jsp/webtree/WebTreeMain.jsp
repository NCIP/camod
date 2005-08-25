<%
    String treeClass      = (String) request.getParameter("treeClass");
    String treeParams     = (String) request.getParameter("treeParams");
    String skin           = (String) request.getParameter("skin");
    String treeDirective  = (String) request.getParameter("treeDirective");
    String windowTitle    = (String) request.getParameter("windowTitle");
    
    if (skin != null && skin.equals("null")) skin = null;
    if (skin == null) skin = "default";

    if (windowTitle != null && windowTitle.equals("null")) windowTitle = null;
    if (windowTitle == null) windowTitle = "WebTree Navigator v1.1";

    // URL encode parameters
    if (treeClass != null) treeClass = java.net.URLEncoder.encode(treeClass);    
    if (treeParams != null) treeParams = java.net.URLEncoder.encode(treeParams);    
    if (skin != null) skin = java.net.URLEncoder.encode(skin);    
    if (treeDirective != null) treeDirective = java.net.URLEncoder.encode(treeDirective);    
%>

<HTML>
<script language="JavaScript1.2" src="skins/<%=skin%>/JavaScript.js"></script>
<HEAD> 
<TITLE><%=windowTitle%></TITLE>
<META http-equiv="Content-Type" content="text/html;">
</HEAD>
<frameset rows="126,*" framespacing="0" frameborder="0" name="master">
  <frame src="skins/<%=skin%>/TopHeader.html" name="headerFrame" frameborder="no" scrolling="no" noresize marginwidth="0" marginheight="0" id="headerFrame">
  <frameset rows="*" cols="400,*" framespacing="0" frameborder="NO" border="0">
    <frameset rows="52,*" framespacing="0" frameborder="no" border="0">
      <frame src="skins/<%=skin%>/SearchHeader.html" name="searchHeader" scrolling="NO" noresize id="searchHeader" >
      <frameset rows="100,*" cols="*" framespacing="0" frameborder="no" border="0">
        <frameset rows="3,*" framespacing="0" frameborder="no" border="0">
          <frame src="DividerFrame.jsp?skin=<%=skin%>" name="divider1" scrolling="NO" noresize>
          <frameset rows="*,3" cols="*" framespacing="0" frameborder="NO" border="0">
            <frame src="SearchTree.jsp?skin=<%=skin%>" name="searchInput" frameborder="no" scrolling="no" marginwidth="10" marginheight="10" id="searchContent">
            <frame src="DividerFrame.jsp?skin=<%=skin%>" name="divider2" scrolling="NO" noresize>
          </frameset>
        </frameset>
        <frameset rows="52,*" cols="*" framespacing="0" frameborder="no" border="0">
          <frame src="skins/<%=skin%>/ResultsHeader.html" name="resultsHeader" frameborder="no" scrolling="NO" noresize marginwidth="0" id="resultsHeader" >
          <frameset rows="3,*" cols="*" framespacing="0" frameborder="NO" border="0">
            <frame src="DividerFrame.jsp?skin=<%=skin%>" name="divider3" scrolling="NO" noresize>
            <frame src="SearchResults.jsp?skin=<%=skin%>" name="searchResults" frameborder="no" marginwidth="15" marginheight="10" id="resultsContent">
          </frameset>
        </frameset>
      </frameset>
    </frameset>
    <frameset rows="*" cols="3,*" framespacing="0" frameborder="no" border="0">
      <frame src="DividerFrame.jsp?skin=<%=skin%>" name="divider4" scrolling="NO" noresize>
      <frameset rows="52,*" frameborder="NO" border="0" framespacing="0">
        <frame src="skins/<%=skin%>/TreeBrowserHeader.html" name="treeHeader" scrolling="NO" noresize id="treeHeader" >
        <frameset rows="3,*" frameborder="NO" border="0" framespacing="0">
          <frame src="DividerFrame.jsp?skin=<%=skin%>" name="divider5" scrolling="NO" noresize>
          <frame src="WebTreeLoader.jsp?treeClass=<%=treeClass%>&treeParams=<%=treeParams%>&skin=<%=skin%>&treeDirective=<%=treeDirective%>" name="tree" frameborder="no" marginwidth="20" marginheight="10" id="treeContent">
        </frameset>
      </frameset>
    </frameset>
  </frameset>
</frameset>
<noframes><body>
To view this page, your browser must be set to accept FRAMES. Please modify your browser settings.
</body></noframes>    

</HTML>
