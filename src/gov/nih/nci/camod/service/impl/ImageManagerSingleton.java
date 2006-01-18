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
