package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.AnimalModelManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the AnimalModelManager
 */
public class AnimalModelManagerSingleton
{

    private static AnimalModelManager ourManager = new AnimalModelManagerImpl();

    /**
     * @return the global instance of the AnimalModelManager
     */
    public static synchronized AnimalModelManager instance()
    {
        return ourManager;
    }
}

/*
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/09/08 17:37:10  georgeda
 * Initial revision
 *
 */
