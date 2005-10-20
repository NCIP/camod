/**
 * 
 * $Id: OrganManager.java,v 1.2 2005-10-20 20:42:43 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Organ;
import java.util.List;

public interface OrganManager {
	public List getAll();
	public Organ get(String id);
    public Organ getByName(String inType);
    public void save(Organ organ);
    public void remove(String id);
}
