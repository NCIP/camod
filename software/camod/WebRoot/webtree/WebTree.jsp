<%@ page import="javax.swing.tree.*"%>
<%@ page import="java.util.*"%>
<%@ page import="gov.nih.nci.evs.app.webtree.EvsWebTreeImpl"%>
<%@ page import="gov.nih.nci.evs.app.webtree.WebNode"%>
<%@ page import="gov.nih.nci.evs.app.webtree.WebTree"%>
<%@ page import="gov.nih.nci.evs.app.webtree.WebTreeService"%>
<%@ page import="java.io.*"%>

<%
  // get parameters
  String treeParams     = (String) request.getParameter("treeParams");  
  String targetId       = (String) request.getParameter("targetId");  
  String targetName      = (String) request.getParameter("targetName");
  String treeAction     = (String) request.getParameter("treeAction");  
  String skin = (String)request.getParameter("skin");
  Vector highlightId = new Vector();
 
 // added for SQL injection of skin parameter
  if (skin != "evsMOD"){
	  skin = "evsMOD";	  
	   if (skin == null){
	    HttpSession userSession = request.getSession();
	    skin =  (String)userSession.getAttribute("skin");
	    Hashtable allhash =  (Hashtable)userSession.getAttribute("allhash");
	
	    for ( Enumeration e = allhash.keys() ; e.hasMoreElements() ; )
	    {
	        // retrieve the object_key
	        String object_key = (String) e.nextElement();
	        // retrieve the object associated with the key
	        String string_object = (String) allhash.get ( object_key );
	        if(string_object.equals(targetName)){
	          highlightId.add(object_key);
	        }
	    }	
	  }
  }

  // cross browser parameter null checks
  if (treeAction != null && treeAction.equals("null")) treeAction = null;
      
  try 
  {    
    WebTreeService webTree = null;    
    HttpSession userSession = request.getSession(false);     
  
    // get current web tree from session 
    if (userSession != null)
    {
    	if(treeParams != null && skin != null){
	    	webTree = new WebTreeService(treeParams, skin);	    
	    } else {   	
        	webTree = (WebTreeService) userSession.getAttribute("webTree");
        }

    }
    
    // if no treeAction specified, build a new tree
    if (treeAction == null)
    {
      // request/create new session
      userSession = request.getSession(true);
      // construct new web tree
      webTree = new WebTreeService(treeParams, skin);
      }

    if (webTree != null)
    {
    if(targetId != null) {
    	highlightId.add(targetId);    	
    }
    
     DefaultMutableTreeNode displayTreeRoot = null;
        // ** build/update display tree **
	if(highlightId.size()>1){
		for (int i = 0; i < highlightId.size(); i++) {
			targetId = (String)highlightId.elementAt(i);
			displayTreeRoot = webTree.getDisplayTree(treeAction, targetId, userSession);
     	}
	} else  {
		displayTreeRoot = webTree.getDisplayTree(treeAction, targetId, userSession);
	}
      // set updated tree in session   
      userSession.setAttribute("webTree", webTree);  
%>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="skins/<%=skin%>/TreeBrowser.css" />
		<script language="JavaScript1.2" src="skins/<%=skin%>/JavaScript.js"></script>
	</head>
	<body>
		<table border="0">
	  <%  // render display tree
      Enumeration displayTree = displayTreeRoot.preorderEnumeration();
	
      while (displayTree.hasMoreElements())  
      {
        DefaultMutableTreeNode displayNode = (DefaultMutableTreeNode) displayTree.nextElement();        
        WebNode displayWebNode = (WebNode) displayNode.getUserObject();
        
        if (displayWebNode != null)
        {       
        String myName = displayWebNode.getName();
        String myId   = displayWebNode.getId();
                  
        // get info used for mouse-over hover pop-up info
        String webNodeInfo = displayWebNode.getInfo();

        // check for highligh match to show background color highlight
        // if (treeAction != null && treeAction.equals("highlight") && myId.equals(targetId))	
	
		 if (treeAction != null && treeAction.equals("highlight")  && highlightId.contains(myId) )
         {
       %> 
			<tr class="highlightNode">
	   <%}  else  { 	   
	   %>
	    	<tr>
	  <% } %>
		<td><table border=0><tr>
		
	  <%  
		     // check for highlight match to show marker chevrons
	         //if (treeAction != null && treeAction.equals("highlight") && myId.equals(targetId) && displayNode.getLevel() > 0  )
	         if (treeAction != null && treeAction.equals("highlight") && displayNode.getLevel() >= 0 && highlightId.contains(myId) )
	         { 
	            %>
				<td width="<%=30*displayNode.getLevel()%>-3">
				<img src="skins/evsMOD/images/transparent.gif" border=0 width="<%=30*displayNode.getLevel()%>-3" height="1" />
				</td>
		  <% }  else  { %>
				<td width="<%=30*displayNode.getLevel()%>">
				<img src="skins/evsMOD/images/transparent.gif" border=0 width="<%=30*displayNode.getLevel()%>" height="1" />
				</td>
		  <% } %>
		  <%
	          // render node icon and associated url
	          if (displayWebNode.getIconAction().length() > 0)
	          {
	          // this web node has an override icon graphic and action, use that instead of the automated folder and leaf icons %>
				<td width="40" align="right">
				<a onClick="<%=displayWebNode.getIconAction()%>"><img src="skins/evsMOD/images/<%=displayWebNode.getIconGraphic()%>" vspace="0" hspace="0" border="0" height="20" width="30" alt="<%=webNodeInfo%>" /></a>
				</td>
		   <% }              
	          else if (displayWebNode.hasChildren() && !displayNode.isLeaf())
	          { 
	          %>
				<td width="40" align="right">
				<a href="WebTree.jsp?targetId=<%=java.net.URLEncoder.encode(myId)%>&treeAction=collapse&amp;skin=<%=skin%>"><img src="skins/evsMOD/images/folderOpen.gif" vspace="0" hspace="0" border="0" height="20" width="30" alt="<%=webNodeInfo%>" /></a>
				</div>
				</td>
		   <% }
	          else if (displayWebNode.hasChildren())           
	          { 
	          // no children currently displayed, but has children %>
				<td width="40" align="right">
				<a href="WebTree.jsp?targetId=<%=java.net.URLEncoder.encode(myId)%>&amp;treeAction=expand&amp;skin=<%=skin%>"><img src="skins/evsMOD/images/folderClosed.gif" vspace="0" hspace="0" border="0" height="20" width="30" alt="<%=webNodeInfo%>" /></a>
				</div>
				</td>
			<% }   
	           else
	           {
	           // no children display, and has not children at all, this is a leaf %>
					<td width="40" align="right">
					<img src="skins/evsMOD/images/leaf.gif" vspace="0" hspace="0" border="0" height="20" width="30" alt="<%=webNodeInfo%>" />
					</td>
			<% }   
	 
	          // render name with action (if defined)
	          String linkAction = displayWebNode.getAction();
	          if (linkAction.length() > 0)
	          {  %>
					<td>
					<a target="_top" name="<%=myId%>" href="javascript:" onClick="<%=linkAction%>"><%=myName%></a>
					</td>
			<% }  
	           else
	           { %>
					<td>
					<a name="<%=myId%>" onFocus="blur();"></a>
					<%=myName%>
					</td>
			<% } %>
		</tr>
	</tr></table></td>
	<% } // end webNode is null check
      } // end while loop
    %>
</table>
 <% } 
    else
    {  // null webTree object %>       

		<table>
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<b>Your Tree Session has Expired!</b>
				</td>
			</tr>
			<tr>
				<td>
					Click the link below to close this window.
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<a target="_top" href="javascript:top.window.close()">Close Window</a>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
		<%  }
  } // end try
  catch (Exception e)
  {
 %>
		<table>
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<b>Server Error!</b>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					Click the link below to close this window.
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<a target="_top" href="javascript:top.window.close()">Close Window</a>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr valign="bottom">
				<td>
					<%=e.getMessage()%>
				</td>
			</tr>

		</table>
<%
  } // end catch
%>
	</body>
</html>






