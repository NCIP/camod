/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.CellLine;
import gov.nih.nci.camod.webapp.form.CellLineData;
import java.util.List;

/**
 * @author pandyas
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface CellLineManager {
	
	public List getAll() throws Exception;
	
	public CellLine get(String id) throws Exception;
	
	public void save(CellLine cellLine) throws Exception;
	
    public void remove(String id) throws Exception;	
	
	public CellLine create(CellLineData inCellLineData ) throws Exception;
	
	public void update(CellLineData inCellLineData, CellLine inCellLine) throws Exception;	
    
}
