<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" >
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	

			<tr>
				<td class="formTitle" height="20">Accessibility</td>				
			</tr>			
			
			<tr>
				<td class="resultsBoxWhiteEnd">

					<a name="top"></a>
					<h2><!--<a href="index.html"><img src="home.gif" alt="HOME" 
					width="40" height="38" border="0" align="right" hspace="5"></a> --> 
					Web Accessibility Feedback Form</h2>



					<h3 class="alt">About Web Site Accessibility at <abbr title="National 
					Cancer Institute">NCI</abbr></h3>

					<p>The Web sites at the <a href="http://www.cancer.gov/">National 
					Cancer Institute</a> have been designed to
					be accessible to all users, compliant with the 508 standards, and
					compatible with screen readers and other assistive technologies.
					However, this is an ongoing
					process and it is possible that some users with disabilities may 
					encounter problems accessing
					some pages.</p>
					<h3 class="alt">To Ask Questions About Cancer</h3>
					<p>If you would like additional help accessing or understanding 
					information on an NCI Web page, or to receive a personalized response 
					to your specific questions about cancer, we encourage U.S. residents 
					to call NCI's <a 
					href="http://cis.nci.nih.gov/contact/contact.html">Cancer Information 
					Service (CIS)</a> at <strong>1-800-4-CANCER (1-800-422-6237)</strong> 
					Monday through Friday from 9:00 a.m. to 4:30 p.m. local time to speak 
					with a Cancer Information Specialist. Deaf and hard of hearing 
					callers with TTY equipment may call 1-800-332-8615. </p>
					<h3 class="alt">To Ask Questions About Web Accessibility for People 
					With Disabilities</h3>

					<p>For questions about NCI's continuing efforts to make its Web-based 
					information accessible to all users, or to report an accessibility 
					problem on any of our pages, please complete this form. 
					Alternatively, you may e-mail <a 
					href="mailto:nci508@mail.nih.gov">nci508@mail.nih.gov</a>. 
					</p><p><strong><em>** Please note: this form is for visitors with 
					disabilities who have comments about the accessibility of NCI's Web 
					pages. If you have questions about cancer, please contact the <a 
					href="http://cis.nci.nih.gov/contact/contact.html">Cancer Information 
					Service</a>. **</em></strong> </p>


					  <form action="http://fmp.cit.nih.gov/508comments/FMPro" 
					method="post" name="CommentForm" onsubmit="return isReady(this)">
					<input type=hidden name=-DB value="508Comments.fp5">
					<input type=hidden name=-Lay value="web">
					<input type=hidden name="-format" value="ncithankyou.htm">
					<input type=hidden name="-error" value="new_error.htm">
					<input type="hidden" name="-Script" value="sendMail">

						<table border="0" cellspacing="10">
									<tr>
										<td valign="top" nowrap 
					align="right"><label for="fullname">Name:</label></td><td 
					valign="top"><input type=text name="name" size=40 id="fullname"></td>
												</tr>
												<tr>
													<td 
					valign="top" nowrap align="right"><label for="email">E-mail 
					(required):</label></td><td valign="top"><input type=text 
					name="email" size=30 id="email">
					<!--<tr>
													<td 
					valign="top" nowrap><label for="nihemp">Are you an NIH 
					Employee?</label></td><td valign="top"> <input type=radio 
					name="nihEmployee" value="Yes" id="nihemp">Yes  <input type=radio 
					name="nihEmployee" value="No" id="nihemp">No  </td>
												</tr>-->
					<input type ="hidden" name="IC" value="NCI"></td>

												</tr>
					<tr>
										<td valign="top" 
					align="right" nowrap><label for="url">Address of the page<br>
					on which you are<br>
					commenting (you can<br>
					select and type over to change:)</label></td><td valign="top"><input 
					type="text" name="Ref_URL" value="" size="50" id="url"></td>
												</tr>


												<tr>

													<td 
					valign="top" nowrap  align="right"><label 
					for="comment">Comment:</label></td><td valign="top"><textarea 
					name="comment" rows=7 cols=50 WRAP="VIRTUAL" 
					id="comment"></textarea></td>
												</tr>

													<tr><td>
													</td>
												<td 
					valign="top"><input type=submit name="-New" value="Submit Comment"></td>
												</tr>
												</table>

										</form>
			
				
		
				</td>
			</tr>
			
			</TABLE>
		</td></tr></TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="footer.jsp" %>