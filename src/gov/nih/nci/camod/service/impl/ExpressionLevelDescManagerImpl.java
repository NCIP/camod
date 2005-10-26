package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.ExpressionLevelDesc;
import gov.nih.nci.camod.service.ExpressionLevelDescManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;

import java.util.List;

public class ExpressionLevelDescManagerImpl extends BaseManager implements ExpressionLevelDescManager {
   
	public List getAll() throws Exception {
		log.trace("In ExpressionLevelDescManagerImpl.getAll");
		return super.getAll(ExpressionLevelDesc.class);
	}
	
	/**
     * Get the ExpressionLevelDesc by it's name
     * 
     * @param inName
     *            the name of the ExpressionLevelDesc
     * 
     * @return the ExpressionLevelDesc that matches the name
     */
    public ExpressionLevelDesc getByName(String inName) {

    	ExpressionLevelDesc theExpressionLevelDesc = null;

        try {

            // The following two objects are needed for eQBE.
        	ExpressionLevelDesc theQueryObj = new ExpressionLevelDesc();
            theQueryObj.setExpressionLevel(inName);

            // Apply evaluators to object properties
            Evaluation theEvaluation = new Evaluation();
            theEvaluation.addEvaluator("ExpressionLevelDesc.ExpressionLevelDescName", Evaluator.EQUAL);

            List theList = Search.query(theQueryObj, theEvaluation);

            if (theList != null && theList.size() > 0) {
                theExpressionLevelDesc = (ExpressionLevelDesc) theList.get(0);
            }

        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in ExpressionLevelDescManagerImpl.getByType");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in ExpressionLevelDescManagerImpl.getByType");
            e.printStackTrace();
        }

        return theExpressionLevelDesc;
    }		
}
