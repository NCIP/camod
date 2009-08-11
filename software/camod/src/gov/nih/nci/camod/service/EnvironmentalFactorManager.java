/**
 * 
 * $Id: EnvironmentalFactorManager.java,v 1.4 2005-11-07 20:43:29 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2005/10/20 20:42:23  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.EnvironmentalFactor;
import java.util.List;

public interface EnvironmentalFactorManager {
	public List getAll() throws Exception;
	public EnvironmentalFactor get(String id) throws Exception;
    public void save(EnvironmentalFactor environmentalFactor) throws Exception;
    public void remove(String id) throws Exception;
}
