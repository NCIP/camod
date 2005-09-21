/**
 *  @author georgeda 
 *  
 *  $Id: EvsTreeUtil.java,v 1.1 2005-09-21 20:34:59 georgeda Exp $  
 *  
 *  $Log: not supported by cvs2svn $
 *  
 */
package gov.nih.nci.camod.util;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.evs.query.EVSQuery;
import gov.nih.nci.evs.query.EVSQueryImpl;
import gov.nih.nci.system.applicationservice.ApplicationService;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Static helper class for caching EVS values.
 * 
 */
public class EvsTreeUtil {

    static private final Log log = LogFactory.getLog(EvsTreeUtil.class);

    static private Map ourOrganDescriptions = new HashMap();

    private EvsTreeUtil() {
    }

    /**
     * Get a preferred name based on a concept code. Will return the cached
     * value if it has been fetched before.
     * 
     * @param inConceptCode the concept code to get the preferred name for.
     * 
     * @return the preferred name, or an empty string if something goes wrong.
     */
    public static synchronized String getEVSPreferedOrganDescription(String inConceptCode) {

        log.trace("Entering getEVSPreferedOrganDescription");

        String theDescription = "";

        if (ourOrganDescriptions.containsKey(inConceptCode)) {
            theDescription = (String) ourOrganDescriptions.get(inConceptCode);
        } else {

            try {
                // Get the app service uri
                ResourceBundle theBundle = ResourceBundle.getBundle("camod");

                ApplicationService appService = ApplicationService.getRemoteInstance(theBundle
                        .getString(Constants.Evs.URI_KEY));

                EVSQuery theConceptNameQuery = new EVSQueryImpl();
                theConceptNameQuery.getConceptNameByCode(Constants.Evs.NAMESPACE, inConceptCode);

                List theConceptNames = (List) appService.evsSearch(theConceptNameQuery);

                // Should only be one
                if (theConceptNames.size() > 0) {

                    String theDisplayName = (String) theConceptNames.get(0);

                    EVSQuery theDisplayNameQuery = new EVSQueryImpl();
                    theDisplayNameQuery.getPropertyValues(Constants.Evs.NAMESPACE, theDisplayName,
                            Constants.Evs.DISPLAY_NAME_TAG);

                    // Should only be one
                    List theDisplayNameList = (List) appService.evsSearch(theDisplayNameQuery);

                    if (theDisplayNameList.size() > 0) {
                        theDescription = (String) theDisplayNameList.get(0);

                        // Cache for next time
                        ourOrganDescriptions.put(inConceptCode, theDescription);
                    }
                }

            } catch (Exception e) {
                log.error("Exception getting preferred description: ", e);
            }
        }
        log.trace("Exiting getEVSPreferedOrganDescription");

        return theDescription;
    }
}
