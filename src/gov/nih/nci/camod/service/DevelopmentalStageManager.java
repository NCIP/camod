/**
 *
 * $Id: DevelopmentalStageManager.java,v 1.1 2007-05-16 12:28:58 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.DevelopmentalStage;
import java.util.List;

/**
 * @author pandyas
 */
public interface DevelopmentalStageManager {

    public List getAll() throws Exception;

    public DevelopmentalStage get(String id) throws Exception;

    public DevelopmentalStage getByName(String inName) throws Exception;
    
    public DevelopmentalStage getOrCreate(String inConceptCode, String inName) throws Exception; 	
	
}
