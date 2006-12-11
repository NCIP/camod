/**
 *  
 *   The caMOD Software License, Version 1.0
 *
 *   Copyright 2005-2006 SAIC. This software was developed in conjunction with the National Cancer
 *   Institute, and so to the extent government employees are co-authors, any rights in such works
 *   shall be subject to Title 17 of the United States Code, section 105.
 *
 *   Redistribution and use in source and binary forms, with or without modification, are permitted
 *   provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice, this list of conditions
 *   and the disclaimer of Article 3, below.  Redistributions in binary form must reproduce the above
 *   copyright notice, this list of conditions and the following disclaimer in the documentation and/or
 *   other materials provided with the distribution.
 *
 *   2.  The end-user documentation included with the redistribution, if any, must include the
 *   following acknowledgment:
 *
 *   "This product includes software developed by the SAIC and the National Cancer
 *   Institute."
 *
 *   If no such end-user documentation is to be included, this acknowledgment shall appear in the
 *   software itself, wherever such third-party acknowledgments normally appear.
 *
 *   3. The names "The National Cancer Institute", "NCI" and "SAIC" must not be used to endorse or
 *   promote products derived from this software.
 *
 *   4. This license does not authorize the incorporation of this software into any third party proprietary
 *   programs.  This license does not authorize the recipient to use any trademarks owned by either
 *   NCI or SAIC.
 *
 *
 *   5. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED
 *   WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 *   MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE) ARE
 *   DISCLAIMED.  IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE, SAIC, OR
 *   THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *   EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *   PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 *   PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
 *   OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *   NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *   SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * $Id: SelectElementServlet.java,v 1.2 2006-12-11 19:29:41 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2006/12/11 13:21:12  pandyas
 * demo files
 *
 * 
 */
package gov.nih.nci.camod.webapp.servlet;

import gov.nih.nci.camod.util.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.SortedSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.ajaxtags.servlets.BaseAjaxServlet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * Servlet used to handle select elememt dropdwon request for CI from the GUI
 * 
 */
public class SelectElementServlet extends BaseAjaxServlet {
	private static int ourNumMatchesToReturn;

	private static final long serialVersionUID = 3257296453788404152L;
	
    static private final Log log = LogFactory.getLog(SelectElementServlet.class);	

	static {
		Properties camodProperties = new Properties();
		try {

			String camodPropertiesFileName = null;

			camodPropertiesFileName = System.getProperty("gov.nih.nci.camod.camodProperties");
			
			try {
			
			FileInputStream in = new FileInputStream(camodPropertiesFileName);
			camodProperties.load(in);
	
			} 
			catch (FileNotFoundException e) {
				log.error("Caught exception finding file for properties: ", e);
				e.printStackTrace();			
			} catch (IOException e) {
				log.error("Caught exception finding file for properties: ", e);
				e.printStackTrace();			
			}
			String ajaxNumber = camodProperties
					.getProperty("ajax.num_matches_to_return");
			ourNumMatchesToReturn = Integer.parseInt(ajaxNumber);
		}

		// Default to 100 on an exception
		catch (Exception e) {
			System.err.println("Error loading system.properties file");
			e.printStackTrace();
			ourNumMatchesToReturn = 100;
		}
	}

	/**
	 * Get the XML autocomplete content based on parameters passed in
	 */
	public String getXmlContent(HttpServletRequest inRequest,
			HttpServletResponse inResponse) throws Exception {
		log.info("<SelectElementServlet> Entering getXmlContent");
        

		String theCarcinogenicIntervention = inRequest.getParameter("carcinogenicIntervention");
        log.info("theCarcinogenicIntervention: " + theCarcinogenicIntervention);

		List theAgentNameList = null;

		if (theCarcinogenicIntervention != null
				&& theCarcinogenicIntervention.trim().length() > 0) {
			log.info("Searching for agent names matching: "
					+ theCarcinogenicIntervention);
			theAgentNameList = getMatchingAgentNames(
					theCarcinogenicIntervention, ourNumMatchesToReturn);
		} else {
			log.error("No known parameter passed in for agent type");
		}

		String theXmlResponse = new AjaxXmlBuilder().addItems(
				theAgentNameList, "source", "target").toString();

		log.info("Number matched: " + theAgentNameList.size());
		log.debug("Exiting getXmlContent");

		return theXmlResponse;
	}

	/**
	 * Return a list of agent names for the selected agent type
	 * 
	 * @param inAgentType
	 *            the agent type to match
	 * @param inNumToReturn
	 *            the maximum number of values to return
	 * @return a list of AjaxTagValue pairs
	 * @throws Exception
	 *             when an error occurs
	 */
	private List<AjaxTagValuePair> getMatchingAgentNames(
			String inAgentType, int inNumToReturn) throws Exception {
		
		log.info("<SelectElementServlet> Entering getMatchingAgentNames");
        log.info("inAgentType: " + inAgentType); 
        log.info("inNumToReturn: " + inNumToReturn); 
        
		SortedSet<String> theMatchingAgentNames = SelectElementUtil
				.getMatchingAgentNames(inAgentType, inNumToReturn);

		List<AjaxTagValuePair> theModelNameList = new ArrayList<AjaxTagValuePair>();
        log.info("Inital size of List: " + theModelNameList.size());
        
		for (String theModelName : theMatchingAgentNames) {
			AjaxTagValuePair theTagValue = new AjaxTagValuePair();
			theTagValue.setTarget(theModelName);
			theTagValue.setSource(theModelName);
			theModelNameList.add(theTagValue);
            log.info("New size of List: " + theModelNameList.size());
		}
		log.info("<SelectElementServlet> Exiting getMatchingAgentNames");
		return theModelNameList;
	}
}
