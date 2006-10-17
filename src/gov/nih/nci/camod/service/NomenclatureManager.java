/**
 * 
 * $Id: NomenclatureManager.java,v 1.1 2006-10-17 16:14:18 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Nomenclature;
import java.util.List;

/**
 * @author pandyas
 * 
 * interface for the NomenclatureManager
 */
public interface NomenclatureManager {

	public List getAll() throws Exception;
	
    public Nomenclature getByName(String inName) throws Exception;
	
    public Nomenclature getOrCreate(String inName) throws Exception;	
	
}
