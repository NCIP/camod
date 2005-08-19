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
		<td class="formTitle" height="20" colspan="3">Chemical/Drug </td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Chemical/Drug</label></td>
		<td class="formField">
			<br>
			<label for="field3">(if Chemical/Drug is not listed, then please<br>select "Other" from the list and specify it below)</label>
			<br>
			<br>
			<select class="formFieldSized" name="field3" id="field3" multiple="true" size="4">
                                <OPTION value="">
                                <OPTION value="0" >Other
                                <OPTION value="8" >1,1-dimethylhydrazine (UDMH)
                                <OPTION value="37" >1,2,5,6-dibenzanthracene
                                <OPTION value="33" >1,2-dibromoethane
                                <OPTION value="34" >1,2-dimethylhydrazine (DMH)
                                <OPTION value="35" >1,2-dimethylhydrazine-di-HCl (DMH)

                                <OPTION value="38" >1,3-butadiene
                                <OPTION value="39" >1,4-dimethanesulfonoxybutane (myleran)
                                <OPTION value="1" >1-aminobenzo[a]pyrene (1-ABaP)
                                <OPTION value="2" >1-aminobenzo[a]pyrene (1-ABaP)
                                <OPTION value="3" >1-ethyl-1-nitrosourea (ethylnitrosourea) (ENU)
                                <OPTION value="4" >1-methyl-1-nitrosourea (MNU)
                                <OPTION value="5" >1-nitrobenzo[a]pyrene (1-NBaP)
                                <OPTION value="6" >1-nitrobenzo[a]pyrene trans-7,8-dihydrodiol (1-NBaP trans-7,8-dihydrodiol)
                                <OPTION value="7" >1-nitropyrene (1-NP)
                                <OPTION value="36" >12-O-tetradecanoylphorbol-13-acetate (TPA)
                                <OPTION value="45" >2,3,7,8-tetrachorodibenzo-p-dioxin (TCDD)
                                <OPTION value="46" >2,6-diaminopurine
                                <OPTION value="40" >2-acetylaminofluorene
                                <OPTION value="41" >2-amino-1-methyl-6-phenylimidazo[4,5-b]pyridine (PhIP)
                                <OPTION value="42" >2-amino-3,4-dimethylmidazole[4,5-f]quinoline (MeIQ)
                                <OPTION value="43" >2-fluoroadenine
                                <OPTION value="44" >20-methylcholanthrene (MCA)

                                <OPTION value="51" >3,4-benzpyrene
                                <OPTION value="47" >3-aminobenzo[a]pyrene (3-ABaP)
                                <OPTION value="48" >3-methylcholanthrene (MCA) (MC)
                                <OPTION value="49" >3-nitrobenzo[a]pyrene (3-NBaP)
                                <OPTION value="50" >3-nitrobenzo[a]pyrene trans-7,8-dihydrodiol (3-NBaP trans-7,8-dihydrodiol)
                                <OPTION value="52" >4-(methylnitrosamino)-1-(3-pyridyl)-1-butanone (NNK)
                                <OPTION value="54" >4-nitroquinoline N-oxide
                                <OPTION value="53" >4-nitroquinoline-1-oxide (4NQO)
                                <OPTION value="55" >4-o-tolylazo-o-toluidine
                                <OPTION value="56" >5-methylchrysene (5-MeC)
                                <OPTION value="57" >6-nitrochrysene (6-NC)
                                <OPTION value="58" >6-thioguanine
                                <OPTION value="59" >7,12-dimethylbenz[a]anthracene (DMBA)
                                <OPTION value="60" >9,10-dimethyl-1,2-benzanthracene (DMBA)
                                <OPTION value="76" >Cetrorelix acetate (SB-75)
                                <OPTION value="101" >N-amyl-N-methylnitrosoamine (AMN)
                                <OPTION value="102" >N-butyl-N-(4-hydroxybutyl)nitrosamine (BBN)

                                <OPTION value="103" >N-diethylnitrosamine (N,N-diethylnitrosamine) (N-nitrosodiethylamine) (DEN) (NDEA)
                                <OPTION value="105" >N-ethyl-N'-nitro-N-nitroguanidine (ENNG)
                                <OPTION value="104" >N-ethyl-N'-nitro-N-nitrosoguanidine
                                <OPTION value="106" >N-ethyl-N-nitrosourea (N-nitrosoethylurea) (ENU)
                                <OPTION value="107" >N-methyl-N-nitrosourea (methylnitrosourea) (MNU)
			</select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Chemical/Drug:</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Dose:</label></td>
		<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" />&nbsp;
                    <select class="formFieldUnSized" name="field3" id="field3" size="1">
                            <OPTION value="">mg
                            <OPTION value="0" >kg
                            <OPTION value="552" >cg
                    </select>
		</td>
	</tr>
	
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
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Administrative Routes:</label></td>
		<td class="formField">
		<br>
		<label for="field3">- if Administration Route is not listed, <br>then please select "Other" and then specify it below:</label>
		<br>
		<br>
		<select class="formFieldSized" name="field3" id="field3" size="1">
                        <OPTION value="">
                        <OPTION value="0" >Other
                        <OPTION value="552" >ad lib in water
                        <OPTION value="553" >inhalation
                        <OPTION value="554" >intramuscular
                        <OPTION value="555" >intraperitoneal
                        <OPTION value="556" >intravenous
                        <OPTION value="557" >oral (nonfasting)
                        <OPTION value="558" >oral with prior fast
                        <OPTION value="551" >pellet
                        <OPTION value="550" >slow-release pellet
                        <OPTION value="559" >subcutaneous
                        <OPTION value="560" >topical
		</select>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Other Administrative Route:</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Treatment Regimen:</label></td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Age:</label></td>
		<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" />&nbsp;
                    <select class="formFieldUnSized" name="field3" id="field3" size="1">
                            <OPTION value="">Months
                            <OPTION value="0" >Days
                            <OPTION value="552" >Weeks
                            <OPTION value="553" >Years
                    </select>
		</td>
		
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Gender:</label></td>
		<td class="formField">
			<select class="formFieldSized" name="field3" id="field3" size="1">
				<br>
				<option value="option1">male</option>
				<option value="option2">female</option>
			</select>
		</td>
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