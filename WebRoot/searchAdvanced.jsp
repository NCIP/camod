<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>

<FORM name="input" action="searchResults.jsp" method="get">

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	
	<TABLE summary="" cellpadding="3" cellspacing="0" border="0">
                <tr>
                        <td class="formTitleBlue" height="20" colspan="3">Keyword Search:&nbsp;&nbsp;<input type="text" name="field3" id="field3" size="55" />&nbsp;&nbsp;<input class="actionButton" type="submit" value="Search" /></td>
                </tr>
                
                <tr><td>&nbsp;</td></tr>
                
                <tr>
			<td class="formTitleBlue" height="20" colspan="3">Advanced Search</td>		
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formRequiredLabel"><label for="field1">Model Name / Model Descriptor</label></td>
			<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">PI's Name</label></td>
			
			<td class="formField">
			<select class="formFieldSized"  name="field2" id="field2" size="1">
				<option value="option1"></option>
                                <OPTION value="65">Abate-Chen, Cory</option>
                                <OPTION value="403">Al-Ubaidi, Muayyad</option>
                                <OPTION value="562">Almholt, Kasper</option>
                                <OPTION value="184">Arbeit, Jeff</option>
                                <OPTION value="306">Arnold, Andrew</option>
                                <OPTION value="293">Arteaga, Carlos</option>
                                <OPTION value="539">Ben-Neriah, Yinon</option>
                                <OPTION value="74">Balmain, Allan</option>
                                <OPTION value="570">Baron, Beverly</option>
                                <OPTION value="571">Basson, Craig</option>
                                <OPTION value="602">Baylin, Stephen</option>
                                <OPTION value="250">Benezra, Robert</option>
                                <OPTION value="502">Blasco, Maria</option>
                                <OPTION value="178">Bradley, Allan</option>
                                <OPTION value="498">Bremner, Rod</option>
			</select>			
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Site of primary Lesion/Tumor</label>&nbsp;<a href='javascript: rs("commentWin","evsTreeImage.jsp",1025,700);'><IMG src="images\selectUP.gif" align=middle  border=0></a></td>
			<td class="formField"><input class="formFieldSized" type="text" disabled="true" name="field3" id="field3" size="25" /></td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Diagnosis</label>&nbsp;<a href='javascript: rs("commentWin","evsTreeImage.jsp",1025,700);'><IMG src="images\selectUP.gif" align=middle  border=0></a></td>
			<td class="formField"><input class="formFieldSized" type="text" disabled="true" name="field3" id="field3" size="25" /></td>
		</tr>
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Species</label></td>
			
			<td class="formField">
			<select class="formFieldSized"  name="field2" id="field2" size="1">
                                <OPTION value=""></OPTION>
                                <OPTION value="1">Mouse(Mus musculus)</OPTION>
                                <OPTION value="8">Rat(Rattus norvegicus)</OPTION>
                                <OPTION value="9">Rat(Rattus rattus)</OPTION>
                                <OPTION value="12">Mongolian gerbil (Meriones unguiculatus)</OPTION>
                                <OPTION value="13">Syrian (golden) hamster (Mesocricetus auratus)</OPTION>
                                <OPTION value="14">Guinea pig (Cavia porcellus)</OPTION>
                                <OPTION value="16">Mouse(in vivo)</OPTION>
			</select>			
			</td>
		</tr>

		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Phenotype:</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field1">Phenotype:</label>
			</td>

			<td class="formField">
				<input class="formFieldSized" type="text" name="field1" id="field1" size="30" />
			</td>
		</tr>

		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Genetic Description:</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>

			<td class="formLabel">
				<label for="field1">Gene Name:</label>
			</td>
			<td class="formField">
				<input class="formFieldSized" type="text" name="field1" id="field1" size="30" />
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field1">&nbsp;</label>
			</td>
			<td class="formField">
				<input type="checkbox" name="box1" id="box1" checked="checked" /> <label for="box1">Engineered Transgene</label>
				<br />
				<input type="checkbox" name="box2" id="box2" /> <label for="box2">Targeted Modification</label>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field1">Genomic Segment Designator:</label>
			</td>

			<td class="formField">
				<input class="formFieldSized" type="text" name="field1" id="field1" size="30" />
			</td>
		</tr>
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field3">Select Inducing Agent for Induced Mutation</label>
			</td>

			<td class="formField">
				<select class="formFieldSized" name="field3" id="field3" size="1">
                                    <OPTION value="">
                                    <OPTION value="1">Chemical
                                    <OPTION value="2">Radiation
                                    <OPTION value="3">Transgene
				</select>
			</td>
		</tr>

		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Carcinogenic Agents:</td>
		</tr>

		<tr>	
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field1">Models with Carcinogenic Interventions:</label>
			</td>

			<td class="formField">
				<input type="checkbox" name="box1" id="box1" checked="checked" /> 
				<label for="box1">Check here to search for models with <br>Carcinogenic interventions data</label>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field3">Select Chemical/Drug</label>
			</td>
			<td class="formField">
				<select class="formFieldSized" name="field3" id="field3" size="1">
                                        <OPTION value=""></OPTION> 
                                        <OPTION value="0" >Other</OPTION>
                                        <OPTION value="8" >1,1-dimethylhydrazine (UDMH)</OPTION>
                                        <OPTION value="37" >1,2,5,6-dibenzanthracene</OPTION>
                                        <OPTION value="33" >1,2-dibromoethane</OPTION>
                                        <OPTION value="35" >1,2-dimethylhydrazine-di-HCl (DMH)</OPTION>
                                        <OPTION value="38" >1,3-butadiene</OPTION>
                                        <OPTION value="39" >1,4-dimethanesulfonoxybutane (myleran)</OPTION>
                                        <OPTION value="1" >1-aminobenzo[a]pyrene (1-ABaP)</OPTION>
                                        <OPTION value="3" >1-ethyl-1-nitrosourea (ethylnitrosourea) (ENU)</OPTION>
                                        <OPTION value="36" >12-O-tetradecanoylphorbol-13-acetate (TPA)</OPTION>
                                        <OPTION value="45" >2,3,7,8-tetrachorodibenzo-p-dioxin (TCDD)</OPTION>
                                        <OPTION value="48" >3-methylcholanthrene (MCA) (MC)</OPTION>
                                        <OPTION value="471" >4-hydroxytamoxifen (4OHT; Sigma)</OPTION>
                                        <OPTION value="59" >7,12-dimethylbenz[a]anthracene (DMBA)</OPTION>
                                        <OPTION value="60" >9,10-dimethyl-1,2-benzanthracene (DMBA)</OPTION>
                                        <OPTION value="440" >AP20187</OPTION>
                                        <OPTION value="441" >AP20187</OPTION>
                                        <OPTION value="472" >Diethylnitrosamine</OPTION>
                                        <OPTION value="638" >Goitrogen</OPTION>
                                        <OPTION value="102" >N-butyl-N-(4-hydroxybutyl)nitrosamine (BBN)</OPTION>
                                        <OPTION value="103" >N-diethylnitrosamine (N,N-diethylnitrosamine) (N-nitrosodiethylamine) (DEN) (NDEA)</OPTION>
                                        <OPTION value="104" >N-ethyl-N'-nitro-N-nitrosoguanidine</OPTION>
                                        <OPTION value="106" >N-ethyl-N-nitrosourea (N-nitrosoethylurea) (ENU)</OPTION>
                                        <OPTION value="436" >N-nitroso-bis-(2-oxopropyl) amine (BOP)</OPTION>
                                        <OPTION value="636" >PPARg ligand troglitazone</OPTION>
                                        <OPTION value="466" >RU486</OPTION>
                                        <OPTION value="467" >RU486</OPTION>
                                        <OPTION value="469" >RU486</OPTION>
                                        <OPTION value="463" >SB203580 hydrochloride</OPTION>
                                        <OPTION value="473" >amphiregulin (AR)</OPTION>
                                        <OPTION value="64" >azoxymethane (AOM)</OPTION>
                                        <OPTION value="67" >benzo[a]pyrene (BP) (BaP) (B[a]P)</OPTION>
                                        <OPTION value="408" >cyclophosphamide</OPTION>
                                        <OPTION value="85" >dextran sulfate sodium (DSS)</OPTION>
                                        <OPTION value="439" >diethylnitrosamine</OPTION>
                                        <OPTION value="645" >diethylnitrosamine (DEN)</OPTION>
                                        <OPTION value="651" >diethylnitrosamine (DEN)</OPTION>
                                        <OPTION value="435" >diethylnitrosamine (DEN) [55-18-5] </OPTION>
                                        <OPTION value="89" >dimethylbenzanthracene (DMBA)</OPTION>
                                        <OPTION value="646" >dimethylnitrosamine (DMN) </OPTION>
                                        <OPTION value="608" >doxycycline</OPTION>
				</select>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field3">Select Growth Factor:</label>
			</td>
			<td class="formField">
				<select class="formFieldSized" name="field3" id="field3" size="1">
                                        <OPTION value="">
                                        <OPTION value="0" >Other
                                        <OPTION value="650" >562
                                        <OPTION value="412" >bFGF
                                        <OPTION value="9" >insulin-like growth factor 1 (IGF1) (IGF-1) (human recombinant)
				</select>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field3">Select Hormone:</label>
			</td>
			<td class="formField">
				<select class="formFieldSized" name="field3" id="field3" size="1">
                                        <OPTION value="">
                                        <OPTION value="0" >Other
                                        <OPTION value="10" >19-nor-progesterone
                                        <OPTION value="11" >androsterone
                                        <OPTION value="146" >diethylstilbestrol (DES)
                                        <OPTION value="147" >dihydrotestosterone (DHT)
                                        <OPTION value="148" >estradiol (17beta-estradiol) (E2)
                                        <OPTION value="151" >estrogen
                                        <OPTION value="163" >progesterone
				</select>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field3">Select Radiation:</label>
			</td>
			<td class="formField">
				<select class="formFieldSized" name="field3" id="field3" size="1">
                                        <OPTION value="">
                                        <OPTION value="0" >Other
                                        <OPTION value="25" >Iodine-131 (I-131) (131I)
                                        <OPTION value="168" >Radium-224 (Ra-224) (224Ra)
                                        <OPTION value="175" >X-radiation
                                        <OPTION value="628" >cobalt-60
                                        <OPTION value="23" >gamma-radiation
                                        <OPTION value="173" >ultraviolet-B radiation (UVB)
				</select>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field3">Select Virus:</label>
			</td>

			<td class="formField">
				<select class="formFieldSized" name="field3" id="field3" size="1">
                                        <OPTION value="">
                                        <OPTION value="0" >Other
                                        <OPTION value="15" >A-MuLV (Abelson murine leukemia virus)
                                        <OPTION value="460" >Adeno-Cre
                                        <OPTION value="458" >Adeno-Cre 
                                        <OPTION value="16" >Adenovirus (recombinant, expressing Cre recombinase)
                                        <OPTION value="17" >Adenovirus (recombinant, expressing LacZ)
                                        <OPTION value="413" >Adenovirus Serotype 5 expressing bFGF
                                        <OPTION value="19" >Friend leukemia virus
                                        <OPTION value="176" >Friend leukemia virus - "regressing" (RFV)
                                        <OPTION value="178" >Graffi MuLV (Graffi murine leukemia virus)
                                        <OPTION value="180" >MLV (M-MuLV) (MoMuLV) (Moloney murine leukemia virus)
                                        <OPTION value="437" >MOL4070LTR
                                        <OPTION value="407" >RCAS-Akt (Constitutively acitve, form P. Vogt)
                                        <OPTION value="406" >RCAS-Ras (K-Ras 4B, G12D, from T. Jacks)
                                        <OPTION value="616" >adenovirus vector encoding human XPA gene
				</select>
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field3">Select Surgery:</label>
			</td>
			<td class="formField">
				<select class="formFieldSized" name="field3" id="field3" size="1">
                                            <OPTION value="">
                                            <OPTION value="0" >Other
                                            <OPTION value="27" >adrenalectomy
                                            <OPTION value="29" >mechanical stimulation
                                            <OPTION value="434" >orthotopic ovary implantation
                                            <OPTION value="30" >splenectomy
				</select>
			</td>
		</tr>

		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Cell Lines</td>
		</tr>

		<tr>

			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field1">Cell Line:</label>
			</td>
			<td class="formField">
				<input class="formFieldSized" type="text" name="field1" id="field1" size="30" />
			</td>
		</tr>

		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Therapeutic Approaches</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="field1">Compound/Drug:</label>
			</td>
			<td class="formField">
				<input class="formFieldSized" type="text" name="field1" id="field1" size="30" />
			</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">Models with Therapeutic Approaches</td>
			<td class="formField">
				<input type="checkbox" name="box1" id="box1" checked="checked" /> 
				<label for="box1">Check here to search for models with <br>therapeutic approaches data</label>
			</td>
		</tr>

		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Histopathology</td>
		</tr>		
		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">Models with Metastasis</td>
			<td class="formField">
				<input type="checkbox" name="box1" id="box1" checked="checked" /> 
				<label for="box1">Check here to search for models with Metastasis</label>
			</td>
		</tr>		

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel"><label for="field2">Search for Models with Metastasis in Organ</label>&nbsp;<IMG src="images\selectUP.gif" align=middle></td>
			<td class="formField"><input class="formFieldSized" type="text" disabled="true" name="field3" id="field3" size="25" /></td>
		</tr>		
		
		<tr>
			<td class="formTitleBlue" height="10" colspan="3">Microarray Data</td>
		</tr>

		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">Microarray Data</td>
			<td class="formField">
				<input type="checkbox" name="box1" id="box1" checked="checked" /> 
				<label for="box1">Check here to search for models with microarray data</label>
			</td>
		</tr>

		<tr>

		<td align="right" colspan="3">

			<!-- action buttons begins -->
			<TABLE cellpadding="4" cellspacing="0" border="0">
			<tr>
				<td>
					<input class="actionButton" type="submit" value="Search" />
				</td>
				<td>
					<input class="actionButton" type="reset" value="Reset" />
				</td>
			</tr>
			</TABLE>

		<!-- action buttons end -->
	</td></tr></TABLE>
</td></tr></TABLE>	

<%@ include file="footer.jsp" %>

