/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.CellLine;
import gov.nih.nci.camod.service.CellLineManager;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CellLineManagerImpl extends BaseManager implements CellLineManager {
	
	public List getCellLines() {		
		List cellLines = null;		
		return cellLines;
	}
	
	public CellLine getCellLine(String id) {
		CellLine cellLine = null;		
		return cellLine;
    }

    public void saveCellLine(CellLine cellLine) {    	
    }

    public void removeCellLine(String id) {    	
    }
}
