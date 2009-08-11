/**
 * 
 * $Id: AssociatedExpressionManager.java,v 1.3 2006-04-17 19:13:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.EngineeredGene;
import gov.nih.nci.camod.domain.ExpressionFeature;
import gov.nih.nci.camod.webapp.form.AssociatedExpressionData;

import java.util.List;

public interface AssociatedExpressionManager {
	
	public List getAll() throws Exception;
	
	public ExpressionFeature get(String id) throws Exception;

	public void save(ExpressionFeature expressionFeature) throws Exception;

	public void remove(String id, EngineeredGene inTransgene) throws Exception;
	
	public ExpressionFeature create( AssociatedExpressionData inAssociatedExpressionData ) throws Exception;
	
	public void update( AssociatedExpressionData inAssociatedExpressionData, ExpressionFeature inExpressionFeature ) throws Exception;
}
