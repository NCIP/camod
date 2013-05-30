/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 *  
 * $Id$
 *
 * $Log$
 * Revision 1.9  2007/09/12 19:36:46  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.8  2006/11/09 17:17:28  pandyas
 * Commented out debug code
 *
 * Revision 1.7  2006/08/17 17:58:26  pandyas
 * Defect# 410: Externalize properties files - Code changes to get properties
 *
 * Revision 1.6  2006/05/18 13:06:15  guptaa
 * added disease
 *
 * Revision 1.5  2006/05/17 21:17:31  guptaa
 * organ tree changes
 *
 * Revision 1.4  2006/05/15 19:00:43  georgeda
 * Code review changes
 *
 * 
 * Revision 1.3  2006/05/12 21:03:14  guptaa
 * formatted and added the  serial version id
 *
 * Revision 1.2  2006/05/12 17:49:54  guptaa
 * fixed nsc number
 *
 * Revision 1.1  2006/05/12 17:01:55  guptaa
 * Ajax servlet file
 * 
 */
package gov.nih.nci.camod.webapp.servlet;

import gov.nih.nci.camod.domain.Disease;
import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.util.AjaxTagValuePair;
import gov.nih.nci.camod.util.AutocompleteUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
 * Servlet used to handle Ajay autocompletion requests from the GUI
 * 
 */
public class AutocompleteServlet extends BaseAjaxServlet {
	private static int ourNumMatchesToReturn;

	private static final long serialVersionUID = 3257296453788404152L;
	
    static private final Log log = LogFactory.getLog(AutocompleteServlet.class);	

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
		log.debug("Entering getXmlContent");

		String theModelDescriptor = inRequest.getParameter("modelDescriptor");
		String theGeneName = inRequest.getParameter("geneName");
		String theTherapeuticApproach = inRequest
				.getParameter("therapeuticApproach");
		String theNSCNumber = inRequest.getParameter("NSCNumber");
		String theOrgan = inRequest.getParameter("organ");
		String theTumorClassification = inRequest
				.getParameter("tumorClassification");

		List theGenericDescriptorList = null;

		if (theModelDescriptor != null
				&& theModelDescriptor.trim().length() > 0) {
			log.debug("Searching for model descriptors matching: "
					+ theModelDescriptor);
			theModelDescriptor = theModelDescriptor.trim().toLowerCase();
			theGenericDescriptorList = getMatchingModelDescriptors(
					theModelDescriptor, ourNumMatchesToReturn);
		} else if (theGeneName != null && theGeneName.trim().length() > 0) {
			log.debug("Searching for gene name matching: " + theGeneName);
			theGeneName = theGeneName.trim().toLowerCase();
			theGenericDescriptorList = getMatchingGeneNames(theGeneName,
					ourNumMatchesToReturn);
		} else if (theTherapeuticApproach != null
				&& theTherapeuticApproach.trim().length() > 0) {
			log.debug("Searching for therapeutic approach matching: "
					+ theTherapeuticApproach);
			theTherapeuticApproach = theTherapeuticApproach.trim()
					.toLowerCase();
			theGenericDescriptorList = getMatchineTherapeuticApproachs(
					theTherapeuticApproach, ourNumMatchesToReturn);
		} else if (theNSCNumber != null && theNSCNumber.trim().length() > 0) {
			log.debug("Searching for nsc number matching: " + theNSCNumber);
			theNSCNumber = theNSCNumber.trim().toLowerCase();
			theGenericDescriptorList = getMatchingNSCNumbers(theNSCNumber,
					ourNumMatchesToReturn);
		} else if (theOrgan != null && theOrgan.trim().length() > 0) {
			log.debug("Searching for organ matching: " + theOrgan);
			theOrgan = theOrgan.trim().toLowerCase();
			theGenericDescriptorList = getMatchingOrganNames(theOrgan,
					ourNumMatchesToReturn);
		} else if (theTumorClassification != null
				&& theTumorClassification.trim().length() > 0) {
			log.debug("Searching for tumor classification matching: "
					+ theTumorClassification);
			theTumorClassification = theTumorClassification.trim()
					.toLowerCase();
			theGenericDescriptorList = getMatchingTumorClassifications(
					theTumorClassification, ourNumMatchesToReturn);
		} else {
			log.error("No known parameter passed in for autocompletion");
		}

		String theXmlResponse = new AjaxXmlBuilder().addItems(
				theGenericDescriptorList, "source", "target").toString();

		log.debug("Number matched: " + theGenericDescriptorList.size());
		log.debug("Exiting getXmlContent");

		return theXmlResponse;
	}

	/**
	 * Return a list of matching model descriptors
	 * 
	 * @param inModelDescriptor
	 *            the model descriptor to match
	 * @param inNumToReturn
	 *            the maximum number of values to return
	 * @return a list of AjaxTagValue pairs
	 * @throws Exception
	 *             when an error occurs
	 */
	private List<AjaxTagValuePair> getMatchingModelDescriptors(
			String inModelDescriptor, int inNumToReturn) throws Exception {
		SortedSet<String> theModelMatchingName = AutocompleteUtil
				.getMatchingAnimalModelNames(inModelDescriptor, inNumToReturn);

		List<AjaxTagValuePair> theModelNameList = new ArrayList<AjaxTagValuePair>();
		for (String theModelName : theModelMatchingName) {
			AjaxTagValuePair theTagValue = new AjaxTagValuePair();
			theTagValue.setTarget(theModelName);
			theTagValue.setSource(theModelName);
			theModelNameList.add(theTagValue);
		}
		return theModelNameList;
	}

	/**
	 * Return a list of matching gene names
	 * 
	 * @param inGeneName
	 *            the gene name to match
	 * @param inNumToReturn
	 *            the maximum number of values to return
	 * @return a list of AjaxTagValue pairs
	 * @throws Exception
	 *             when an error occurs
	 */
	private List<AjaxTagValuePair> getMatchingGeneNames(String inGeneName,
			int inNumToReturn) throws Exception {
		SortedSet<String> theMatchingGeneNames = AutocompleteUtil
				.getMatchingGeneNames(inGeneName, inNumToReturn);

		List<AjaxTagValuePair> theGeneNameList = new ArrayList<AjaxTagValuePair>();
		for (String theGeneName : theMatchingGeneNames) {
			AjaxTagValuePair theTagValue = new AjaxTagValuePair();
			theTagValue.setTarget(theGeneName);
			theTagValue.setSource(theGeneName);
			theGeneNameList.add(theTagValue);
		}
		return theGeneNameList;

	}

	/**
	 * Return a list of matching therapeutic approach names
	 * 
	 * @param inTherapeuticApproach
	 *            the name to match
	 * @param inNumToReturn
	 *            the maximum number of values to return
	 * @return a list of AjaxTagValue pairs
	 * @throws Exception
	 *             when an error occurs
	 */
	private List<AjaxTagValuePair> getMatchineTherapeuticApproachs(
			String inTherapeuticApproach, int inNumToReturn) throws Exception {
		List<AjaxTagValuePair> theTherapeuticApproachList = new ArrayList<AjaxTagValuePair>();
		return theTherapeuticApproachList;
	}

	/**
	 * Return a list of matching nsc numbers
	 * 
	 * @param inNSCNumber
	 *            the name to match
	 * @param inNumToReturn
	 *            the maximum number of values to return
	 * @return a list of AjaxTagValue pairs
	 * @throws Exception
	 *             when an error occurs
	 */
	private List<AjaxTagValuePair> getMatchingNSCNumbers(String inNSCNumber,
			int inNumToReturn) throws Exception {
		SortedSet<String> theMatchingNSCNumbers = AutocompleteUtil
				.getMatchingNSCNumbers(inNSCNumber, inNumToReturn);

		List<AjaxTagValuePair> theNSCNumberList = new ArrayList<AjaxTagValuePair>();
		for (String theNSCNumberName : theMatchingNSCNumbers) {
			AjaxTagValuePair theTagValue = new AjaxTagValuePair();
			theTagValue.setTarget(theNSCNumberName);
			theTagValue.setSource(theNSCNumberName);
			theNSCNumberList.add(theTagValue);
		}

		return theNSCNumberList;
	}

	/**
	 * Return a list of matching organ names
	 * 
	 * @param inNSCNumber
	 *            the name to match
	 * @param inNumToReturn
	 *            the maximum number of values to return
	 * @return a list of AjaxTagValue pairs
	 * @throws Exception
	 *             when an error occurs
	 */
	private List getMatchingOrganNames(String inOrgan, int inNumToReturn)
			throws Exception {
		SortedSet<Object> theMatchingOrgans = AutocompleteUtil
				.getMatchingOrgans(inOrgan, inNumToReturn);

		List<AjaxTagValuePair> theOrganList = new ArrayList<AjaxTagValuePair>();
		Iterator iterate = theMatchingOrgans.iterator();
		while (iterate.hasNext()) {
			Organ organ = (Organ) iterate.next();
			AjaxTagValuePair theTagValue = new AjaxTagValuePair();
			theTagValue.setTarget(organ.getConceptCode());
			theTagValue.setSource(organ.getName());
			theOrganList.add(theTagValue);
		}

		return theOrganList;
	}

	/**
	 * Return a list of matching tumor names
	 * 
	 * @param inTumorClassification
	 *            the name to match
	 * @param inNumToReturn
	 *            the maximum number of values to return
	 * @return a list of AjaxTagValue pairs
	 * @throws Exception
	 *             when an error occurs
	 */
	private List getMatchingTumorClassifications(String inTumorClassification,
			int inNumToReturn) throws Exception {
		SortedSet<Object> theMatchingTumorClassifications = AutocompleteUtil
		.getMatchingTumorClassifications(inTumorClassification, inNumToReturn);
		
		List<AjaxTagValuePair> theTumorList = new ArrayList<AjaxTagValuePair>();
		Iterator iterate = theMatchingTumorClassifications.iterator();
		while (iterate.hasNext()) {
			Disease disease = (Disease) iterate.next();
			AjaxTagValuePair theTagValue = new AjaxTagValuePair();
			theTagValue.setTarget(disease.getConceptCode());
			theTagValue.setSource(disease.getName());
			theTumorList.add(theTagValue);
		}
		return theTumorList;
	}
}
