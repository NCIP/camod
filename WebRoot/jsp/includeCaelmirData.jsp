<%

/**
 * 
 * $Id: includeCaelmirData.jsp,v 1.2 2007-12-18 13:28:32 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2007/11/25 23:31:01  pandyas
 * Initial version for feature #8816  	Connection to caELMIR - retrieve data for therapy search page
 *
 *
 */

%>

<%@ page import='net.sf.json.JSONArray' %>
<%@ page import='net.sf.json.JSONObject' %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="gov.nih.nci.camod.Constants" %>
<%@ page import="java.util.Vector" %>

<%@ include file="/common/taglibs.jsp"%>

<%  
    JSONArray jsonArray = new JSONArray();    
	jsonArray = (JSONArray)request.getSession().getAttribute( Constants.CAELMIR_STUDY_DATA );
	java.util.HashMap map = new java.util.HashMap();
	Vector h = new Vector();	
	if ( jsonArray != null ) 
	{	
		System.out.println("jsonArray.length(): " + jsonArray.length());
	    for (int i = 1; i < jsonArray.length(); i++) {
	    	JSONObject jsonObj = (JSONObject) jsonArray.get(i);	    	
	    	
	    	String studyName = jsonObj.getString("studyName");
	    	map.put("Name", studyName);	
	    	h.add(studyName);
	    	System.out.println("Name: " + studyName);
	    	
	    	String studyUrl = jsonObj.getString("studyUrl");
	    	map.put("Url", studyUrl);
	    	h.add(studyUrl);
	    	System.out.println("Url: " + studyUrl);	
	    	
	    	String studyHypothesis = jsonObj.getString("studyHypothesis");
	    	map.put("Hypothesis", studyHypothesis);
	    	h.add(studyHypothesis);
	    	System.out.println("Hypothesis: " + studyHypothesis);	    	

	    	String studyDescription = jsonObj.getString("studyDescription");
	    	map.put("Description", studyDescription);
	    	h.add(studyDescription);
	    	System.out.println("Description: " + studyDescription);	
	    	
	    	String primaryInvestigator = jsonObj.getString("primaryInvestigator");
	    	String email = jsonObj.getString("email");
	    	String institution = jsonObj.getString("institution");
	    	String PI = primaryInvestigator + " " + "(" + email + ")" +  " " + institution;
	    	map.put("PrincipalInvestigator", PI);
	    	h.add(PI);
	    	System.out.println("Principal Investigator: " + primaryInvestigator);  
	    	
  		    		    		    		
		}
		System.out.println("map injsp: " + map);
		pageContext.setAttribute("map", map);
		request.setAttribute("vector", h);
	}	    		    	
%>	

			<tr>
				 <td class="formTitle" height="20" colspan="4">
				    caELMIR Study Summary
				 </td>				
			</tr>
			<tr>
				<td class="greySubTitleLeft" width="20%">Name</td>
				<td class="greySubTitleLeft" width="20%">Hypothesis</td>
				<td class="greySubTitleLeft" width="20%">Description</td>
				<td class="greySubTitleLeftEnd" width="40%">Principal Investigator</td>				
			</tr>	
			
				   
			   <!--logic:iterate id="vector" name="vector" type="java.util.Vector"-->
			   <c:forEach var='item' items='${map}'>	 
				<tr>
					<td class="WhiteBox">
						<a href='<c:out value='${map["Url"]}'/>'>
						<!--c:out value="${Name}"/-->
						<c:out value='${map["Name"]}'/>
					</td>
					
					<td class="WhiteBoxRightEnd">
						<c:out value='${map["Hypothesis"]}'/>
						<!--c:out value='${map["Hypothesis"]}'/-->
					</td>
					
					<td class="WhiteBoxRightEnd">
						<c:out value='${map["Description"]}'/>					
						<!--c:out value='${map["Description"]}'/-->
					</td>

					<td class="WhiteBoxRightEnd">
						<c:out value='${map["PrincipalInvestigator"]}'/>					
						<!--c:out value='${map["PrincipalInvestigator"]}'/-->					
					</td>													
				</tr>
				</c:forEach>
			<!--/logic:iterate-->



			
