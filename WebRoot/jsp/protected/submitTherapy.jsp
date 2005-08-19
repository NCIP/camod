<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
<!-- -->

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left">

	<tr>
		<td class="formMessage" colspan="3">* indicates a required field</td>
	</tr>

	<tr>
		<td class="formTitle" height="20" colspan="3">Enter Information for Therapies:</td>
	</tr>
	
	<TR align="LEFT" valign="TOP">
		<td class="formRequiredNotice" width="5">*</td>	
		<TD class="formRequiredLabel">Drug / Compound Name:</TD>
		<TD class="formField">
			<INPUT name="required_Drug / Compound" type="TEXT" size="40" maxlength="255" value="">			
		</TD>
	</TR>
	
	<tr>    
                <FORM name="input" action="http://dtp.nci.nih.gov/dtpstandard/chemname/index.jsp" method="get">
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">NSC number:</label></td>
		<td class="formField"><input class="actionButton" type="submit" value="Find NSC #" />&nbsp;<input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" /></td>
                </FORM>
	</tr>	

	<tr>
                <FORM name="input" action="http://chemfinder.cambridgesoft.com/" method="get">
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">CAS number:</label></td>
		<td class="formField"><input class="actionButton" type="submit" value="Find CAS #" />&nbsp;<input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" /></td>
                </FORM>
	</tr>		

        <TR align="LEFT" valign="TOP">
                <td class="formRequiredNotice" width="5">&nbsp;</td>        
                <TD class="formLabel">Toxicity Grade:</TD>
                <TD class="formField">
                    <SELECT name="chemical_class" size="1">
                            <OPTION value="1">Mild</OPTION>
                            <OPTION value="2">Moderate</OPTION>
                            <OPTION value="3">Severe</OPTION>
                    </SELECT>
               </td>
	</tr>
	
        <TR align="LEFT" valign="TOP">
		<td class="formRequiredNotice" width="5">&nbsp;</td>        
		<TD class="formLabel">Chemical Class:</TD>
		<TD class="formField">
			<SELECT name="chemical_class" size="5"  multiple width="150" >
				<OPTION value="1">Alkylating Agents</OPTION>
				<OPTION value="2">Antimetabolites</OPTION>
				<OPTION value="3">Biologicals</OPTION>
				<OPTION value="4">Hormones and Steroids</OPTION>
				<OPTION value="5">Plant Alkaloids and Antibiotics</OPTION>
				<OPTION value="6">Synthetics</OPTION>
				<OPTION value="">&nbsp;</OPTION>
			</SELECT>

			<INPUT type="button" onClick="move(this.form.chemical_class2,this.form.chemical_class)" value="<<">
			<INPUT type="button" onClick="move(this.form.chemical_class,this.form.chemical_class2)" value=">>">
			<SELECT name="chemical_class2" size="5"  multiple width="150" >
				<OPTION value="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
			</SELECT>
		</TD>
	</TR>

	<TR align="LEFT" valign="TOP">
		<td class="formRequiredNotice" width="5">&nbsp;</td>	
		<TD class="formLabel">Biological Process:</TD>
     		<TD class="formField"> 	
			<SELECT name="biological_process" size="5" >
				<OPTION value="1">Adhesion, Molility, Invasion, Metastasis</OPTION>
				<OPTION value="2">Angiogenesis</OPTION>
				<OPTION value="3">Apoptosis</OPTION>
				<OPTION value="4">Carbohydrate and Lipid Synthesis</OPTION>
				<OPTION value="5">Cell Cycle</OPTION>
				<OPTION value="6">Chromosomal Dynamics and Mitosis</OPTION>
				<OPTION value="7">Differentiation</OPTION>
				<OPTION value="8">DNA Replication, Repair, and Modification</OPTION>
				<OPTION value="9">Gene Expression (transciption, translation, RNA processing)</OPTION>
				<OPTION value="10">Gene Modification (methylation, alkylation)</OPTION>
				<OPTION value="11">Immortalization (telomerase)</OPTION>
				<OPTION value="12">Immune Response</OPTION>
				<OPTION value="13">Signal Transduction</OPTION>
				<OPTION value="14">Other</OPTION>
				<OPTION value="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
			</SELECT>

			<INPUT type="button" onClick="move(this.form.biological_process2,this.form.biological_process)" value="<<">
			<INPUT type="button" onClick="move(this.form.biological_process,this.form.biological_process2)" value=">>">

			<SELECT name="biological_process2" size="5"  multiple width="150" >
					<OPTION value="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</SELECT>
		</TD>
	</TR>
	
	<TR align="LEFT" valign="TOP">
		<td class="formRequiredNotice" width="5">&nbsp;</td>	
		<TD class="formLabel">Target:</TD>
		<TD class="formField">	
			<SELECT name="target" size="5"  multiple width="150" >
				<OPTION value="1">Angiogenesis Targets</OPTION>
				<OPTION value="2">Apoptosis (caspases)</OPTION>
				<OPTION value="3">Cyclins</OPTION>
				<OPTION value="4">Cytokines and Chemokines</OPTION>
				<OPTION value="5">Cytoskeletal Proteins</OPTION>
				<OPTION value="6">DNA Replication and Repair Enzymes</OPTION>
				<OPTION value="7">Drug Transporters</OPTION>
				<OPTION value="8">Growth Factors and Receptors</OPTION>
				<OPTION value="9">Oncogenes</OPTION>
				<OPTION value="10">Immune System</OPTION>
				<OPTION value="11">Proteases</OPTION>
				<OPTION value="12">Protein Kinases/Phosphatases</OPTION>
				<OPTION value="13">Suppressor Genes</OPTION>
				<OPTION value="14">Transcriptional and Translational Factors</OPTION>
				<OPTION value="15">Other</OPTION>
				<OPTION value="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
			</SELECT>

			<INPUT type="button" onClick="move(this.form.target2,this.form.target)" value="<<">
			<INPUT type="button" onClick="move(this.form.target,this.form.target2)" value=">>">

			<SELECT name="target2" size="5"  multiple width="150" >
				<OPTION value="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
			</SELECT>
		</TD>
	</TR>		
	
       <TR>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Dose (Number only):</label></td>
		<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" />&nbsp;
                    <select class="formFieldUnSized" name="field3" id="field3" size="1">
                            <OPTION value="">mg
                            <OPTION value="0" >kg
                            <OPTION value="552" >cg
                    </select>
		</td>
	</TR>	
	
<!--	
        <tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Units of Measure<br>(Such as mg/kg)</label></td>
		<TD class="formField">
                    <SELECT name="chemical_class" size="1">
                            <OPTION value="1"></OPTION>
                            <OPTION value="2">mg</OPTION>
                            <OPTION value="3">kg</OPTION>
                    </SELECT>
               </td>
	</tr>
-->

        <TR align="LEFT" valign="TOP">
                <td class="formRequiredNotice" width="5">&nbsp;</td>        
                <TD class="formLabel">Gender:</TD>
                <TD class="formField">
                    <SELECT name="chemical_class" size="1">
                            <OPTION value="1">Male</OPTION>
                            <OPTION value="2">Female</OPTION>
                            <OPTION value="3">Mixed</OPTION>
                    </SELECT>
               </td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Mouse Age:</label></td>
		<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" />&nbsp;
                    <select class="formFieldUnSized" name="field3" id="field3" size="1">
                            <OPTION value="">Months
                            <OPTION value="0" >Days
                            <OPTION value="552" >Weeks
                            <OPTION value="553" >Years
                    </select>
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Adminstration Route:</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>				
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Biomarker:</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>			
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Tumor Progression:</label></td>
		<td class="formField">Time to preneoplastic lesion malignancy metastais<br><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" />&nbsp;
                    <select class="formFieldUnSized" name="field3" id="field3" size="1">
                            <OPTION value="">Months
                            <OPTION value="0" >Days
                            <OPTION value="552" >Weeks
                            <OPTION value="553" >Years
                    </select>
		</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Experiment \ Treatment Regiment:</label></td>
		<td class="formField"><textarea class="formFieldSized" name="field2" id="field2" cols="32" rows="5"></textarea></td>
	</tr>		
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Results:</label></td>
		<td class="formField"><textarea class="formFieldSized" name="field2" id="field2" cols="32" rows="5"></textarea></td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Comments:</label></td>
		<td class="formField"><textarea class="formFieldSized" name="field2" id="field2" cols="32" rows="5"></textarea></td>
	</tr>		
	
	<tr>
		<td align="right" colspan="3">
			<!-- action buttons begins -->
			<TABLE cellpadding="4" cellspacing="0" border="0">
				<tr>
					<td><input class="actionButton" type="submit" value="Submit" /></td>
					<td><input class="actionButton" type="reset" value="Reset" /></td>
				</tr>
			</TABLE>
			<!-- action buttons end -->
		</td>
	</tr>

	</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="footer.jsp" %>