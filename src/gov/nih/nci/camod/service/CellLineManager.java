/**
 * @author pandyas
 * 
 * $Id: CellLineManager.java,v 1.8 2005-11-09 00:17:06 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2005/11/01 18:14:28  schroedn
 * Implementing 'Enter Publication' for CellLines and Therapy, fixed many bugs with Publication. Remaining known bug with "Fill in Fields" button
 *
 * Revision 1.6  2005/10/20 20:28:37  pandyas
 * added javadocs
 *
 *
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.CellLine;
import gov.nih.nci.camod.webapp.form.CellLineData;

import java.util.List;


public interface CellLineManager {
	
	public List getAll() throws Exception;
	
	public CellLine get(String id) throws Exception;
	
	public void save(CellLine cellLine) throws Exception;
	
    public void remove(String id, AnimalModel inAnimalModel) throws Exception;	
	
	public CellLine create(CellLineData inCellLineData ) throws Exception;
	
	public void update(CellLineData inCellLineData, CellLine inCellLine) throws Exception;	
    
}
