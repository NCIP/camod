/**
 * 
 * $Id: TransplantationManagerSingleton.java,v 1.4 2009-03-25 16:23:52 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2008/01/16 18:30:22  pandyas
 * Renamed value to Transplant for #8290
 *
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
 * Singleton class for the TransplantationManagerImpl
 */
public class TransplantationManagerSingleton {

    private static TransplantationManagerImpl ourManager = new TransplantationManagerImpl();

    /**
     * @return the global instance of the TransplantationManager
     */
    public static synchronized TransplantationManagerImpl instance() {
        return ourManager;
    }
}
