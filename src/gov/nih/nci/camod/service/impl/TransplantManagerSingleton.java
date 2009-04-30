/**
 * 
 * $Id: TransplantManagerSingleton.java,v 1.1 2008-01-16 18:30:22 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2007/11/01 15:40:53  pandyas
 * Fixed #8290     Rename graft object into transplant object
 *
 * Revision 1.1  2007/11/01 15:40:07  pandyas
 * Fixed #8290     Rename graft object into transplant object
 *
 * Revision 1.2  2007/08/07 18:30:06  pandyas
 * Renamed to GRAFT as per VCDE comments
 *
 * Revision 1.1  2007/07/31 12:02:21  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.2  2005/11/11 16:27:53  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.service.impl;

/**
 * @author dgeorge
 * 
 * Singleton class for the TransplantManagerImpl
 */
public class TransplantManagerSingleton {

    private static TransplantManagerImpl ourManager = new TransplantManagerImpl();

    /**
     * @return the global instance of the TransplantManager
     */
    public static synchronized TransplantManagerImpl instance() {
        return ourManager;
    }
}
