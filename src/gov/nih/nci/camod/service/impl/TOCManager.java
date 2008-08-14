/**
 *  @author dgeorge
 *  
 *  $Id: TOCManager.java,v 1.4 2008-08-14 16:38:49 pandyas Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.3  2007/08/08 16:38:33  pandyas
 *  removed reference to camod_dev - project name change to camod
 *
 *  Revision 1.2  2006/05/08 13:33:54  georgeda
 *  Clean up warnings
 *
 *  Revision 1.1  2005/10/20 19:28:58  georgeda
 *  Added TOC functionality
 *
 *  Revision 1.5  2005/09/19 13:09:00  georgeda
 *  Slight change to interface
 *
 *  
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.webapp.form.SearchForm;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.*;

/**
 * CurationManager implementation.
 */
public class TOCManager {

    protected final Log log = LogFactory.getLog(TOCManager.class);

    private String myQueryFile;

    /**
     * Constructor. Takes in a query definition XML
     * 
     * @param inQueryFile
     *            the XML file defining the TOC queries
     */
    public TOCManager(String inQueryFile) {
        myQueryFile = inQueryFile;
    }

    public List process() {

        List<TOCQueryGroup> theQueryGroups = new ArrayList<TOCQueryGroup>();

        Document theQueryConfig = null;

        // ///////////////////////////////////////////////////
        // Read in query config file.
        // ///////////////////////////////////////////////////
        try {

            // Create an instance of the DocumentBuilderFactory
            DocumentBuilderFactory theDocumentBuilderFactory = DocumentBuilderFactory.newInstance();

            theDocumentBuilderFactory.setValidating(false);
            theDocumentBuilderFactory.setNamespaceAware(true);

            // Retrieve the DocumentBuilder from the factory.
            DocumentBuilder theDocumentBuilder = theDocumentBuilderFactory.newDocumentBuilder();

            // Turn it into an in-memory object
            theQueryConfig = theDocumentBuilder.parse(myQueryFile);

            Element theQueryGroupElements = theQueryConfig.getDocumentElement();
            NodeList theQueryGroupList = theQueryGroupElements.getElementsByTagName("query_group");

            for (int i = 0; i < theQueryGroupList.getLength(); i++) {

                Element theQueryElement = (Element) theQueryGroupList.item(i);
                TOCQueryGroup theGroup = createQueryGroup(theQueryElement);
                theQueryGroups.add(theGroup);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return theQueryGroups;
    }

    private TOCQueryGroup createQueryGroup(Element inElement) throws Exception {

        TOCQueryGroup theGroup = new TOCQueryGroup();

        NodeList theQueryList = inElement.getElementsByTagName("query");
        String theGroupDescription = (String) inElement.getElementsByTagName("description").item(0).getFirstChild()
                .getNodeValue();

        theGroup.setDescription(theGroupDescription);

        for (int i = 0; i < theQueryList.getLength(); i++) {

            Element theQueryElement = (Element) theQueryList.item(i);

            SearchForm theSearchForm = new SearchForm();

            // Note, these will always be there
            String theDescription = (String) theQueryElement.getElementsByTagName("description").item(0)
                    .getFirstChild().getNodeValue();
            String theKey = (String) theQueryElement.getElementsByTagName("key").item(0).getFirstChild().getNodeValue();

            NodeList theParametersList = theQueryElement.getElementsByTagName("parameters");

            // Should only be one
            if (theParametersList.getLength() == 1) {

                NodeList theParameterNodes = theParametersList.item(0).getChildNodes();

                for (int j = 0; j < theParameterNodes.getLength(); j++) {

                    if ("parameter".equals(theParameterNodes.item(j).getNodeName())) {
                        NodeList theChildNodes = theParameterNodes.item(j).getChildNodes();

                        String theParameterKey = null;
                        String theParameterValue = null;
                        for (int k = 0; k < theChildNodes.getLength(); k++) {

                            if ("name".equals(theChildNodes.item(k).getNodeName())) {
                                theParameterKey = theChildNodes.item(k).getFirstChild().getNodeValue();
                            }

                            if ("value".equals(theChildNodes.item(k).getNodeName())) {
                                theParameterValue = theChildNodes.item(k).getFirstChild().getNodeValue();
                            }
                        }
                        BeanUtils.setProperty(theSearchForm, theParameterKey, theParameterValue);
                    }
                }
            }

            try {
                int theSize = QueryManagerSingleton.instance().countMatchingAnimalModels(theSearchForm);
                theGroup.addQuery(new TOCQuery(theDescription, theKey, theSearchForm, theSize));
            } catch (Exception e) {
                log.error("Unable to generate results for query: " + theDescription, e);
            }
        }

        return theGroup;
    }

    /**
     * Test main
     */
    public static void main(java.lang.String[] args) {
        TOCManager theTOCManager = new TOCManager("C:/dev/workspace/camod/WebRoot/config/TOCQueryConfig.xml");

        List theResults = theTOCManager.process();

        try {

            TOCQueryGroup theTOCQueryGroup = (TOCQueryGroup) theResults.get(0);

            List theQueries = theTOCQueryGroup.getQueries();
            TOCQuery theQuery = (TOCQuery) theQueries.get(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
