/**
 * @author pandyas
 * 
 * $Id: CellLineManager.java,v 1.6 2005-10-20 20:28:37 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.CellLine;
import gov.nih.nci.camod.webapp.form.CellLineData;
import java.util.List;


public interface CellLineManager {
	
	public List getAll() throws Exception;
	
	public CellLine get(String id) throws Exception;
	
	public void save(CellLine cellLine) throws Exception;
	
    public void remove(String id) throws Exception;	
	
	public CellLine create(CellLineData inCellLineData ) throws Exception;
	
	public void update(CellLineData inCellLineData, CellLine inCellLine) throws Exception;	
    
}
