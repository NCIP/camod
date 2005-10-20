/**
 * 
 * $Id: EnvironmentalFactorManager.java,v 1.3 2005-10-20 20:42:23 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.EnvironmentalFactor;
import java.util.List;

public interface EnvironmentalFactorManager {
	public List getAll();
	public EnvironmentalFactor get(String id);
    public void save(EnvironmentalFactor environmentalFactor);
    public void remove(String id);
}
