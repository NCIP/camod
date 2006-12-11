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
 * $Id: SelectElementUtil.java,v 1.1 2006-12-11 19:28:26 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 *
 * 
 */

package gov.nih.nci.camod.util;


import gov.nih.nci.camod.service.impl.QueryManagerSingleton;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.commons.logging.Log;


/**
 * 
 * Utility class used for the SelectElementUtil servlet
 * 
 */
public class SelectElementUtil {
	private static Log log = null;
    

	/**
	 * Get all AgentNames that match the passed in agent type.
	 * 
	 * @param inPrefix
	 *            the prefix to match
	 * @param inNumToReturn
	 *            the maximum number of results to return
	 * 
	 * @return a sorted set of matching animal model names
	 * 
	 * @throws Exception
	 */
	public static synchronized SortedSet<String> getMatchingAgentNames(
			String inAgentType, int inNumToReturn) throws Exception {

       
		log.info("<SelectElementUtil> Entering theMatchingAgentNames");
        log.info("inAgentType: " + inAgentType);

		SortedSet<String> theReturnSet = null;

        log.info("Going to QueryManagerSingleton.instance().getMatchingAgentNames");
		List theMatchingAgentNames = QueryManagerSingleton.instance()
				.getMatchingAgentNames(inAgentType);
        
        log.info("Back from QueryManagerSingleton theMatchingAgentNames.size: " +  theMatchingAgentNames.size());

		// Only return the number requested
		if (theMatchingAgentNames.size() > inNumToReturn) {
            theMatchingAgentNames = theMatchingAgentNames.subList(
					0, inNumToReturn);
		}
		theReturnSet = new TreeSet<String>(theMatchingAgentNames);

		log.info("Number returned: " + theReturnSet.size());
		log.debug("Exiting getMatchingAnimalModelNames");

		return theReturnSet;
	}



	public static void main(String[] inArgs) {
		try {
			SortedSet<Object> theMatchingNSCNumbers = AutocompleteUtil
					.getMatchingTumorClassifications("a", 10);
			System.out.println(new Date() + " - Number matched for p: "
					+ theMatchingNSCNumbers.size());
			/*
			theMatchingNSCNumbers = AutocompleteUtil.getMatchingGeneNames(
					"p53", 10);
			System.out.println(new Date() + " - Number matched for p53: "
					+ theMatchingNSCNumbers.size());
			theMatchingNSCNumbers = AutocompleteUtil.getMatchingGeneNames("d",
					10);
			System.out.println(new Date() + " - Number matched for d: "
					+ theMatchingNSCNumbers.size());
			theMatchingNSCNumbers = AutocompleteUtil.getMatchingGeneNames("55",
					10000);
			System.out.println(new Date() + " - Number matched for 55: "
					+ theMatchingNSCNumbers.size());
			theMatchingNSCNumbers = AutocompleteUtil.getMatchingGeneNames("y",
					10000);
			System.out.println(new Date() + " - Number matched for y: "
					+ theMatchingNSCNumbers.size());
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
