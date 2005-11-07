package gov.nih.nci.camod.service;

import java.util.List;

import gov.nih.nci.camod.domain.ExpressionLevelDesc;

public interface ExpressionLevelDescManager {

	public List getAll() throws Exception;
	
    public ExpressionLevelDesc getByName(String inName) throws Exception;

}
