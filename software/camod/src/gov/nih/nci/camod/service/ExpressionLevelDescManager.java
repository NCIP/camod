/**
 * 
 * $Id: ExpressionLevelDescManager.java,v 1.4 2006-10-17 16:14:18 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2006/04/17 19:13:16  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.service;

import java.util.List;
import gov.nih.nci.camod.domain.ExpressionLevelDesc;

public interface ExpressionLevelDescManager {

	public List getAll() throws Exception;
	
    public ExpressionLevelDesc getByName(String inName) throws Exception;

}
