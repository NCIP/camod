/**
 * 
 * $Id: GenotypeManager.java,v 1.1 2006-10-17 16:14:18 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Genotype;
import java.util.List;

/**
 * @author pandyas
 * 
 * interface for the GenotypeManager
 */
public interface GenotypeManager {
	
	public List getAll() throws Exception;
	
    public Genotype getByName(String inName) throws Exception;
	
    public Genotype getOrCreate(String inName) throws Exception;
}
