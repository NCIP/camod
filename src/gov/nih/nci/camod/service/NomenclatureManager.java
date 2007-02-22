/**
 * 
 * $Id: NomenclatureManager.java,v 1.2 2007-02-22 21:02:00 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2006/10/17 16:14:18  pandyas
 * modified during development of caMOD 2.2 - various
 *
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
    
    public Nomenclature get(String id) throws Exception;    

	public List getAll() throws Exception;
	
    public Nomenclature getByName(String inName) throws Exception;
	
    public Nomenclature getOrCreate(String inName) throws Exception;	
	
}
