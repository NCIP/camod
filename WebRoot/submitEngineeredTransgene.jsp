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
		<td class="formTitle" height="20" colspan="3">Engineered Transgene </td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field3">Transgene Integration</label></td>
		<td class="formField">
			<input id="radio1" name="radio5" checked="checked" type="radio"> <label for="field5">Random</label>
			<br>
			<input id="radio2" name="radio5" type="radio"> <label for="field5">Targeted</label>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Location of Integration *</label><br>(Required field when "Targeted" is selected<br>for the "Transgene Integration" field)</td>
		<td class="formField"><input class="formFieldSized" type="text" name="field1" id="field1" size="30" /></td>
	</tr>

	<tr>
		<td class="formTitle" colspan="3">Transgene (coding sequence only)</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Transgene 1</label></td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">Transgene<br>
						<input type="text" name="field1" id="field1" size="20" />
					</td>
					<td class="standardText" width="33%">Species of Origin<br>
						<select name="field3" id="field3" size="1">
                                                        <OPTION value="">
                                                        <OPTION value="0" >Other
                                                        <OPTION value="1" >(Human)Homo Sapiens
                                                        <OPTION value="2" >(Mouse)Mus Musculus
                                                        <OPTION value="3" >(Cattle)Bos Taurus
                                                        <OPTION value="4" >(Dog)Canis Familiaris
                                                        <OPTION value="5" >(Goat)Capra Hircus
                                                        <OPTION value="6" >(Horse)Equus Caballus
                                                        <OPTION value="7" >(Sheep)Ovis Aries
                                                        <OPTION value="8" >(Rat)Rattus Norvegicus
                                                        <OPTION value="9" >(Rat)Rattus Rattus
                                                        <OPTION value="10" >(Pig)Sus Scrofa
                                                        <OPTION value="11" >(Cat)Felis Catus
						</select>						
					</td>
					<td class="standardText" width="33%">Other Species<br>
						<input type="text" name="field1" id="field1" size="20" />
					</td>
				</tr>
			</TABLE>	
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Transgene 2</label></td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">
						<input type="text" name="field1" id="field1" size="20" />
					</td>
					<td class="standardText" width="33%">
						<select name="field3" id="field3" size="1">
                                                        <OPTION value="">
                                                        <OPTION value="0" >Other
                                                        <OPTION value="1" >(Human)Homo Sapiens
                                                        <OPTION value="2" >(Mouse)Mus Musculus
                                                        <OPTION value="3" >(Cattle)Bos Taurus
                                                        <OPTION value="4" >(Dog)Canis Familiaris
                                                        <OPTION value="5" >(Goat)Capra Hircus
                                                        <OPTION value="6" >(Horse)Equus Caballus
                                                        <OPTION value="7" >(Sheep)Ovis Aries
                                                        <OPTION value="8" >(Rat)Rattus Norvegicus
                                                        <OPTION value="9" >(Rat)Rattus Rattus
                                                        <OPTION value="10" >(Pig)Sus Scrofa
                                                        <OPTION value="11" >(Cat)Felis Catus
						</select>						
					</td>
					<td class="standardText" width="33%">
						<input type="text" name="field1" id="field1" size="20" />
					</td>
				</tr>
			</TABLE>
		</td>
	</tr>
	
	<tr>
		<td class="formTitle" colspan="3">Transcriptional (Promoter)</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">*</td>
		<td class="formRequiredLabel"><label for="field1">Transcriptional 1</label></td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">Regulatory Element<br>
						<input type="text" name="field1" id="field1" size="20" />
					</td>
					<td class="standardText" width="33%">Species of Origin<br>
						<select name="field3" id="field3" size="1">
                                                        <OPTION value="">
                                                        <OPTION value="0" >Other
                                                        <OPTION value="1" >(Human)Homo Sapiens
                                                        <OPTION value="2" >(Mouse)Mus Musculus
                                                        <OPTION value="3" >(Cattle)Bos Taurus
                                                        <OPTION value="4" >(Dog)Canis Familiaris
                                                        <OPTION value="5" >(Goat)Capra Hircus
                                                        <OPTION value="6" >(Horse)Equus Caballus
                                                        <OPTION value="7" >(Sheep)Ovis Aries
                                                        <OPTION value="8" >(Rat)Rattus Norvegicus
                                                        <OPTION value="9" >(Rat)Rattus Rattus
                                                        <OPTION value="10" >(Pig)Sus Scrofa
                                                        <OPTION value="11" >(Cat)Felis Catus
						</select>						
					</td>
					<td class="standardText" width="33%">Other Species<br>
						<input type="text" name="field1" id="field1" size="20" />
					</td>
				</tr>
			</TABLE>	
		</td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Transcriptional 2</label></td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">
						<input type="text" name="field1" id="field1" size="20" />
					</td>
					<td class="standardText" width="33%">
						<select name="field3" id="field3" size="1">
                                                        <OPTION value="">
                                                        <OPTION value="0" >Other
                                                        <OPTION value="1" >(Human)Homo Sapiens
                                                        <OPTION value="2" >(Mouse)Mus Musculus
                                                        <OPTION value="3" >(Cattle)Bos Taurus
                                                        <OPTION value="4" >(Dog)Canis Familiaris
                                                        <OPTION value="5" >(Goat)Capra Hircus
                                                        <OPTION value="6" >(Horse)Equus Caballus
                                                        <OPTION value="7" >(Sheep)Ovis Aries
                                                        <OPTION value="8" >(Rat)Rattus Norvegicus
                                                        <OPTION value="9" >(Rat)Rattus Rattus
                                                        <OPTION value="10" >(Pig)Sus Scrofa
                                                        <OPTION value="11" >(Cat)Felis Catus
						</select>						
					</td>
					<td class="standardText" width="33%">
						<input type="text" name="field1" id="field1" size="20" />
					</td>
				</tr>
			</TABLE>
		</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Transcriptional 3</label></td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">
						<input type="text" name="field1" id="field1" size="20" />
					</td>
					<td class="standardText" width="33%">
						<select name="field3" id="field3" size="1">
                                                        <OPTION value="">
                                                        <OPTION value="0" >Other
                                                        <OPTION value="1" >(Human)Homo Sapiens
                                                        <OPTION value="2" >(Mouse)Mus Musculus
                                                        <OPTION value="3" >(Cattle)Bos Taurus
                                                        <OPTION value="4" >(Dog)Canis Familiaris
                                                        <OPTION value="5" >(Goat)Capra Hircus
                                                        <OPTION value="6" >(Horse)Equus Caballus
                                                        <OPTION value="7" >(Sheep)Ovis Aries
                                                        <OPTION value="8" >(Rat)Rattus Norvegicus
                                                        <OPTION value="9" >(Rat)Rattus Rattus
                                                        <OPTION value="10" >(Pig)Sus Scrofa
                                                        <OPTION value="11" >(Cat)Felis Catus
						</select>						
					</td>
					<td class="standardText" width="33%">
						<input type="text" name="field1" id="field1" size="20" />
					</td>
				</tr>
			</TABLE>
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Poly A Signal</label></td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">
						<input type="text" name="field1" id="field1" size="20" />
					</td>
					<td class="standardText" width="33%">
						<select name="field3" id="field3" size="1">
                                                        <OPTION value="">
                                                        <OPTION value="0" >Other
                                                        <OPTION value="1" >(Human)Homo Sapiens
                                                        <OPTION value="2" >(Mouse)Mus Musculus
                                                        <OPTION value="3" >(Cattle)Bos Taurus
                                                        <OPTION value="4" >(Dog)Canis Familiaris
                                                        <OPTION value="5" >(Goat)Capra Hircus
                                                        <OPTION value="6" >(Horse)Equus Caballus
                                                        <OPTION value="7" >(Sheep)Ovis Aries
                                                        <OPTION value="8" >(Rat)Rattus Norvegicus
                                                        <OPTION value="9" >(Rat)Rattus Rattus
                                                        <OPTION value="10" >(Pig)Sus Scrofa
                                                        <OPTION value="11" >(Cat)Felis Catus
						</select>						
					</td>
					<td class="standardText" width="33%">
						<input type="text" name="field1" id="field1" size="20" />
					</td>
				</tr>
			</TABLE>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Splice Sites / Intron</label></td>
		<td class="formField">
			<TABLE cellpadding="0" cellspacing="5" border="0" width="100%">
				<tr>
					<td class="standardText" width="33%">
						<input type="text" name="field1" id="field1" size="20" />
					</td>
					<td class="standardText" width="33%">
						<select name="field3" id="field3" size="1">
                                                        <OPTION value="">
                                                        <OPTION value="0" >Other
                                                        <OPTION value="1" >(Human)Homo Sapiens
                                                        <OPTION value="2" >(Mouse)Mus Musculus
                                                        <OPTION value="3" >(Cattle)Bos Taurus
                                                        <OPTION value="4" >(Dog)Canis Familiaris
                                                        <OPTION value="5" >(Goat)Capra Hircus
                                                        <OPTION value="6" >(Horse)Equus Caballus
                                                        <OPTION value="7" >(Sheep)Ovis Aries
                                                        <OPTION value="8" >(Rat)Rattus Norvegicus
                                                        <OPTION value="9" >(Rat)Rattus Rattus
                                                        <OPTION value="10" >(Pig)Sus Scrofa
                                                        <OPTION value="11" >(Cat)Felis Catus
						</select>						
					</td>
					<td class="standardText" width="33%">
						<input type="text" name="field1" id="field1" size="20" />
					</td>
				</tr>
			</TABLE>
		</td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2"><a href="http://www.informatics.jax.org/">MGI Number</a></label></td>
		<td class="formField"><input class="formFieldUnSized" type="text" name="field1" id="field1" size="10" /></td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Gene Functions<br>(seperate each entry by a comma)</label></td>
		<td class="formField"><textarea class="formFieldSized" name="field2" id="field2" cols="32" rows="4"></textarea></td>
	</tr>
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field3">Conditional?</label></td>
		<td class="formField">
			<input id="radio1" name="radio5" checked="checked" type="radio"> <label for="field5">Conditional</label>
			<br>
			<input id="radio2" name="radio5" type="radio"> <label for="field5">Not Conditional</label>
		</td>
	</tr>	

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Conditional Description</label></td>
		<td class="formField"><textarea class="formFieldSized" name="field2" id="field2" cols="32" rows="4"></textarea></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Additional Features</label></td>
		<td class="formField"><textarea class="formFieldSized" name="field2" id="field2" cols="32" rows="4"></textarea></td>
	</tr>
	
	<tr>
		<td class="formTitle" colspan="3">&nbsp;</td>
	</tr>	
	
	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field1">Upload Construct Map (Image)</label></td>
		<td class="formField">
			<input class="formFieldSized" type="text" name="field1" id="field1" size="30" />
			<input class="actionButton" type="submit" value="Browse" />
		</td>			
	</tr>		

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Title of Construct<br>(Enter info only when uploading image)</label></td>
		<td class="formField"><textarea class="formFieldSized" name="field2" id="field2" cols="32" rows="4"></textarea></td>
	</tr>

	<tr>
		<td class="formRequiredNotice" width="5">&nbsp;</td>
		<td class="formLabel"><label for="field2">Description of Construct<br>(Enter info only when uploading image)</label></td>
		<td class="formField"><textarea class="formFieldSized" name="field2" id="field2" cols="32" rows="4"></textarea></td>
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




