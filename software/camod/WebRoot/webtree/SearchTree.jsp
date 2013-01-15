  <%
    String algorithm = (String) request.getSession().getAttribute("algorithm");

    String check_e = "", check_s = "" , check_c ="";
    if (algorithm == null || algorithm.compareTo("Contains") == 0)
      check_c = "checked";
    else if (algorithm.compareTo("exactMatch") == 0)
      check_e = "checked";  
    else
      check_s = "checked";
  %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="skins/evsMOD/SearchTree.css"/>
</head>
<body>
<table>
	<tr><td valign="top">
      <form name="search" focus="contains" action="SearchResults.jsp?skin=evsMOD" method="post" target="searchResults">
        <tr><td>
            <table>       
              <tr>
                <td><input name="searchTerm" size="35"/>&nbsp;
                <a href="javascript:document.forms['search'].submit()"><img border="0" src="skins/evsMOD/images/searchButton.gif"/></a>                
              </tr>
			    <tr valign="top">
			      <td>
			        <input type="radio" name="algorithm" value="contains" alt="Contains" <%=check_c%>>Contains			      
			        <input type="radio" name="algorithm" value="exactMatch" alt="Exact Match" <%=check_e%>>Exact Match&nbsp;
       				<input type="radio" name="algorithm" value="startsWith" alt="Begins With" <%=check_s%>>Begins With&nbsp;			        
			      </td>
			    </tr>
            </table>                
          </td></tr>
      </form>
     </td></tr>   
</table>
</body>
</html>
