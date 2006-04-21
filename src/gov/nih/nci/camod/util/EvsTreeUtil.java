/**
 *  @author georgeda 
 *  
 *  $Id: EvsTreeUtil.java,v 1.4 2006-04-21 13:42:12 georgeda Exp $  
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.3  2005/11/03 21:47:56  georgeda
 *  Changed EVS api
 *
 *  Revision 1.2  2005/09/22 13:04:31  georgeda
 *  Added app server call
 *
 *  Revision 1.1  2005/09/21 20:34:59  georgeda
 *  Create util for fetching/caching EVS data
 *
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
public class EvsTreeUtil
{
    static private final Log log = LogFactory.getLog(EvsTreeUtil.class);
    static private Map<String, String> ourDescriptions = new HashMap<String, String>();

    private EvsTreeUtil()
    {}

    /**
     * Get a preferred name based on a concept code. Will return the cached
     * value if it has been fetched before.
     * 
     * @param inConceptCode
     *            the concept code to get the preferred name for.
     * 
     * @return the preferred name, or an empty string if something goes wrong.
     */
    public static synchronized String getEVSPreferedDescription(String inConceptCode)
    {
        log.trace("Entering getEVSPreferedDescription");

        String theDescription = "";

        if (ourDescriptions.containsKey(inConceptCode))
        {
            theDescription = (String) ourDescriptions.get(inConceptCode);
        }
        else
        {
            try
            {
                ApplicationService theAppService = getApplicationService();

                EVSQuery theConceptNameQuery = new EVSQueryImpl();
                theConceptNameQuery.getConceptNameByCode(Constants.Evs.NAMESPACE, inConceptCode);

                List theConceptNames = (List) theAppService.evsSearch(theConceptNameQuery);

                // Should only be one
                if (theConceptNames.size() > 0)
                {
                    String theDisplayName = (String) theConceptNames.get(0);

                    EVSQuery theDisplayNameQuery = new EVSQueryImpl();
                    theDisplayNameQuery.getPropertyValues(Constants.Evs.NAMESPACE, theDisplayName, Constants.Evs.DISPLAY_NAME_TAG);

                    // Should only be one
                    List theDisplayNameList = (List) theAppService.evsSearch(theDisplayNameQuery);

                    if (theDisplayNameList.size() > 0)
                    {
                        theDescription = (String) theDisplayNameList.get(0);

                        // Cache for next time
                        ourDescriptions.put(inConceptCode, theDescription);
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                log.error("Exception getting preferred description: ", e);
            }
        }
        log.trace("Exiting getEVSPreferedDescription");

        return theDescription;
    }

    /**
     * Get the application service based on the properties file
     * 
     * @return the preferred name, or an empty string if something goes wrong.
     */
    public static ApplicationService getApplicationService()
    {
        // Get the app service uri
        ResourceBundle theBundle = ResourceBundle.getBundle(Constants.CAMOD_BUNDLE);
        return ApplicationService.getRemoteInstance(theBundle.getString(Constants.Evs.URI_KEY));
    }
}
