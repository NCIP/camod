/**
 * 
 * $Id: EngineeredTransgeneManager.java,v 1.6 2006-04-17 19:13:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.webapp.form.AssociatedExpressionData;
import gov.nih.nci.camod.webapp.form.EngineeredTransgeneData;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface EngineeredTransgeneManager {
	
	public List getAll() throws Exception;

    public Transgene get(String id) throws Exception;

    public void save(Transgene engineeredGene) throws Exception;

    public void remove(String id, AnimalModel inAnimalModel) throws Exception;

    public Transgene create(EngineeredTransgeneData inEngineeredTransgeneData, HttpServletRequest request) throws Exception;

    public void update(EngineeredTransgeneData inEngineeredTransgeneData, Transgene inEngineeredTransgene, HttpServletRequest request) throws Exception;

    public void createAssocExpression( AssociatedExpressionData inAssociatedExpressionData, EngineeredGene inEngineeredTransgene ) throws Exception;		
    
    public void updateAssociatedExpression( AssociatedExpressionData inAssociatedExpressionData, EngineeredGene inEngineeredTransgene ) throws Exception;
    
}
