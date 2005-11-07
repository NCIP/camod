/**
 * 
 * $Id: TreatmentManager.java,v 1.4 2005-11-07 20:43:29 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2005/10/20 20:43:37  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Treatment;


public interface TreatmentManager {

    public Treatment get(String id) throws Exception;

    public void save(Treatment treatment) throws Exception;

    public void remove(String id) throws Exception;
}
