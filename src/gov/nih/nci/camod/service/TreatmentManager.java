/**
 * 
 * $Id: TreatmentManager.java,v 1.3 2005-10-20 20:43:37 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Treatment;


public interface TreatmentManager {

    public Treatment get(String id);

    public void save(Treatment treatment);

    public void remove(String id);
}
