/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 *  
 * $Id: AutocompleteUtil.java,v 1.6 2008-08-18 13:54:53 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2008/08/14 17:12:48  pandyas
 * remove debug lines
 *
 * Revision 1.4  2008/01/31 22:25:02  pandyas
 * modified log printouts for dev build
 *
 * Revision 1.3  2006/05/18 13:06:43  guptaa
 * added disease
 *
 * Revision 1.2  2006/05/17 21:17:03  guptaa
 * organ tree changes
 *
 * Revision 1.1  2006/05/12 12:49:49  georgeda
 * Initial revision
 *
 * 
 */

package gov.nih.nci.camod.util;


import gov.nih.nci.camod.service.impl.QueryManagerSingleton;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * Utility class used for the autocomplete servlet
 * 
 */
public class AutocompleteUtil {
	private static Log ourLog = null;

	private static SortedSet<String> ourNSCNumbers = null;

	private static long ourNSCReloadTime;

	static {
		ourLog = LogFactory.getLog(AutocompleteUtil.class);

		try {
			initializeNSCNumbers();
		}
		// Nothing we can do.
		catch (Exception ignore) {
		}
	}

	// Common method used to initialize the NSC numbers. The query takes
	// a long time, but the data doesn't change often making it a good
	// candidate for caching.
	private static synchronized void initializeNSCNumbers() throws Exception {
		ourLog.debug("Entering initializeNSCNumbers");

		if (ourNSCNumbers == null
				|| System.currentTimeMillis() > ourNSCReloadTime) {
			ourLog.debug("NSC numbers need to be loaded");

			ourNSCNumbers = new TreeSet<String>();

			List theNSCNumbers = QueryManagerSingleton.instance()
					.getNSCNumbers();

			// Convert them to strings and grab a current timestamp
			for (Object theObject : theNSCNumbers) {
				ourNSCNumbers.add(theObject.toString());
			}
			ourNSCReloadTime = System.currentTimeMillis() + 24 * 60 * 60 * 1000;
		}

		ourLog.debug("Exiting initializeNSCNumbers");
	}

	/**
	 * Get all NSC numbers that match the passed in prefix. The numbers are
	 * treated as strings for this comparison.
	 * 
	 * @param inPrefix
	 *            the prefix to match
	 * @param inNumToReturn
	 *            the maximum number of results to return
	 * 
	 * @return a sorted set of matching NSC numbers
	 * 
	 * @throws Exception
	 */
	public static synchronized SortedSet<String> getMatchingNSCNumbers(
			String inPrefix, int inNumToReturn) throws Exception {
		ourLog.debug("Entering getMatchingNSCNumbers");
		ourLog.debug("Prefix: (" + inPrefix + " ) - Num to return ("
				+ inNumToReturn + ")");

		SortedSet<String> theReturnSet = null;

		initializeNSCNumbers();

		Pattern thePattern = Pattern.compile("^" + inPrefix + "[0-9]*");

		SortedSet<String> theMatchingSet = ourNSCNumbers.tailSet(inPrefix);

		// Grab all of the items after the prefix
		if (theMatchingSet.size() > 0) {
			String theStartItem = theMatchingSet.first();
			String theEndItem = null;

			int theCount = 0;

			// Loop through until the item doesn't match the regex, or
			// the limit of number of objects to return has been reached
			for (String theItem : theMatchingSet) {
				Matcher theMatcher = thePattern.matcher(theItem);

				if (!theMatcher.matches() || theCount++ >= inNumToReturn) {
					theEndItem = theItem;
					break;
				}
			}

			if (theEndItem != null) {
				theReturnSet = ourNSCNumbers.subSet(theStartItem, theEndItem);
			} else {
				theReturnSet = theMatchingSet;
			}
		}
		ourLog.debug("Number returned: " + theReturnSet.size());
		ourLog.debug("Exiting getMatchingNSCNumbers");

		return theReturnSet;
	}

	/**
	 * Get all modelDescriptors that match the passed in prefix.
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
	public static synchronized SortedSet<String> getMatchingAnimalModelNames(
			String inPrefix, int inNumToReturn) throws Exception {
		ourLog.debug("Entering getMatchingAnimalModelNames");

		SortedSet<String> theReturnSet = null;

		List theMatchingAnimalModelNames = QueryManagerSingleton.instance()
				.getMatchingAnimalModelNames(inPrefix);

		// Only return the number requested
		if (theMatchingAnimalModelNames.size() > inNumToReturn) {
			theMatchingAnimalModelNames = theMatchingAnimalModelNames.subList(
					0, inNumToReturn);
		}
		theReturnSet = new TreeSet<String>(theMatchingAnimalModelNames);

		ourLog.debug("Number returned: " + theReturnSet.size());
		ourLog.debug("Exiting getMatchingAnimalModelNames");

		return theReturnSet;
	}

	/**
	 * Get all gene names that match the passed in prefix.
	 * 
	 * @param inPrefix
	 *            the prefix to match
	 * @param inNumToReturn
	 *            the maximum number of results to return
	 * 
	 * @return a sorted set of matching gene names
	 * 
	 * @throws Exception
	 */
	public static synchronized SortedSet<String> getMatchingGeneNames(
			String inPrefix, int inNumToReturn) throws Exception {
		ourLog.debug("Entering getMatchingGeneNames");

		SortedSet<String> theReturnSet = null;

		List theMatchingGeneNames = QueryManagerSingleton.instance()
				.getMatchingGeneNames(inPrefix);

		// Only return the number requested
		if (theMatchingGeneNames.size() > inNumToReturn) {
			theMatchingGeneNames = theMatchingGeneNames.subList(0,
					inNumToReturn);
		}
		theReturnSet = new TreeSet<String>(theMatchingGeneNames);

		ourLog.debug("Number returned: " + theReturnSet.size());
		ourLog.debug("Exiting getMatchingGeneNames");

		return theReturnSet;
	}

	public static synchronized SortedSet<Object> getMatchingOrgans(
			String inPrefix, int inNumToReturn) throws Exception {
		ourLog.debug("Entering getMatchingOrganNames");

		SortedSet<Object> theReturnSet = null;
				 
		List theMatchingOrganNames = QueryManagerSingleton.instance().getMatchingOrgans(inPrefix);
				
		// Only return the number requested
		if (theMatchingOrganNames.size() > inNumToReturn) {
			theMatchingOrganNames = theMatchingOrganNames.subList(0,
					inNumToReturn);
		}
		theReturnSet = new TreeSet<Object>(theMatchingOrganNames);

		ourLog.debug("Number returned: " + theReturnSet.size());
		ourLog.debug("Exiting getMatchingOrganNames");
		ourLog.debug("list values"+theReturnSet);

		return theReturnSet;
	}
	
	public static synchronized SortedSet<Object> getMatchingTumorClassifications(
			String inPrefix, int inNumToReturn) throws Exception {
		ourLog.debug("Entering getMatchingOrganNames");

		SortedSet<Object> theReturnSet = null;
				 
		List theMatchingTumorClassificationsNames = QueryManagerSingleton.instance().getMatchingTumorClassifications(inPrefix);
				
		// Only return the number requested
		if (theMatchingTumorClassificationsNames.size() > inNumToReturn) {
			theMatchingTumorClassificationsNames = theMatchingTumorClassificationsNames.subList(0,
					inNumToReturn);
		}
		theReturnSet = new TreeSet<Object>(theMatchingTumorClassificationsNames);

		ourLog.debug("Number returned: " + theReturnSet.size());
		ourLog.debug("Exiting getMatchingOrganNames");
		ourLog.debug("list values"+theReturnSet);

		return theReturnSet;
	}

	public static void main(String[] inArgs) {
		try {
			SortedSet<Object> theMatchingNSCNumbers = AutocompleteUtil
					.getMatchingTumorClassifications("a", 10);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
