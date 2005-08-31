<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.domain.Histopathology" %>	
<%@ page import="gov.nih.nci.camod.Constants" %>
<%@ page import="java.util.List" %>

<bean:define id="mdl" name="animalmodel"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">

		<tr>
			<td class="formTitle" height="20" colspan="3">
				Model Characteristics - Model:
				<bean:write name="mdl" property="modelDescriptor"/>
			</td>
		</tr>
		
		<tr>
			<td class="GreyBox" width="20%"><b>Model Descriptor</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				<bean:write name="mdl" property="modelDescriptor"/>
			</td>			
		</tr>
		
		<tr>
			<td class="WhiteBox" width="20%"><b>Official Nomenclature</b></td>
			<td class="WhiteBoxRightEnd" width="80%">
				TBD
			</td>
		</tr>

		<tr>
			<td class="GreyBox" width="20%"><b>Genotype</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				TBD
			</td>
		</tr>

		<tr>
			<td class="WhiteBox" width="20%"><b>Species</b></td>
			<td class="WhiteBoxRightEnd" width="80%">
				<bean:write name="mdl" property="species.scientificName"/>
			</td>
		</tr>
		
		<tr>
			<td class="GreyBox" width="20%"><b>Strain</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				<bean:write name="mdl" property="species.ethnicityStrain"/>
			</td>
		</tr>		
		
		<tr>
			<td class="WhiteBox" width="20%"><b>Experimental Design</b></td>
			<td class="WhiteBoxRightEnd" width="80%">			
				<P>
					<bean:write name="mdl" property="experimentDesign"/>
				</P>			
			</td>
		</tr>		               

		<tr>
			<td class="GreyBox" width="20%"><b>Phenotype</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				<P>
					<bean:write name="mdl" property="phenotype.description"/>
				</P>		
			</td>
		</tr>		

		<tr>
			<td class="WhiteBox" width="20%"><b>Sex Distribution of the Phenotype</b></td>
			<td class="WhiteBoxRightEnd" width="80%">
				<bean:write name="mdl" property="phenotype.sexDistribution.type"/>
			</td>
		</tr>		               
        
		<tr>
			<td class="GreyBox" width="20%"><b>Submitted by</b></td>
			<td class="GreyBoxRightEnd" width="80%">
				<a href="#">
					TBD
				</a>
			</td>
		</tr>

		<tr>
			<td class="WhiteBox" width="20%"><b>Principal Investigator Lab</b></td>
			<td class="WhiteBoxRightEnd" width="80%">
				<a href=""> TBD
				</a>
			</td>
		</tr>		               

<%      
	final List avlbty = ((AnimalModel)mdl).getAnimalAvailabilityCollection();
	final int cc = avlbty.size();
%>

		<% if ( cc > 0 ) { %>
		<tr><td>&nbsp;</td></tr>
                
                <tr>
			<td class="formTitle" height="20" colspan="2">Model Availability</td>		
		</tr>
		
		<logic:iterate id="av" 
						name="avlbty" 
						indexId="idx">
				<% 
					int intIdx = idx.intValue() + 1;
					final String tdClass = ((intIdx%2)==0)?"WhiteBox":"GreyBox";
				%>
                
		<tr>
			<td class="<%= tdClass %>" width="20%">
				<b>
					<bean:write name="av" property="name"/>
					- Stock #:
					<bean:write name="av" property="stockNumber"/>
				</b>
			</td>
			<td class="<%= tdClass %>End" width="80%">
				<b>Strain:</b> TBD
			</td>
		</tr>
		
		</logic:iterate>
		<%}%>		
		<tr>
			<td class="WhiteBox" width="100%" colspan="2"><a href='javascript: rs("commentWin","submitComment.jsp",415,250);'><IMG src="images/comment.gif" border=0 align=middle> <b>Place your comment here</b></a></td>
		</tr>

	</TABLE>
	
</td></tr>
</TABLE>
