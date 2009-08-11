/**
 * 
 * $Id: ImageManagerSingleton.java,v 1.3 2006-04-17 19:11:05 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.ImageManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the ImageManager
 */
public class ImageManagerSingleton
{
    private static ImageManager ourManager = new ImageManagerImpl();

    /**
     * @return the global instance of the ImageManager
     */
    public static synchronized ImageManager instance()
    {
        return ourManager;
    }
}
