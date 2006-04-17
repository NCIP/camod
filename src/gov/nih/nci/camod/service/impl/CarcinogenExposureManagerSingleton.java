/**
 * @author pandyas
 * 
 * $Id: CarcinogenExposureManagerSingleton.java,v 1.1 2006-04-17 19:11:05 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.CarcinogenExposureManager;

public class CarcinogenExposureManagerSingleton
{
    private static CarcinogenExposureManager ourManager = new CarcinogenExposureManagerImpl();

    /**
     * @return the global instance of the CarcinogenExposureManager
     */
    public static synchronized CarcinogenExposureManager instance() {
        return ourManager;
    }
}
