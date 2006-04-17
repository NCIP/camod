/**
 * 
 * $Id: ExpressionLevelDescManager.java,v 1.3 2006-04-17 19:13:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service;

import java.util.List;

import gov.nih.nci.camod.domain.ExpressionLevelDesc;

public interface ExpressionLevelDescManager {

	public List getAll() throws Exception;
	
    public ExpressionLevelDesc getByName(String inName) throws Exception;

}
