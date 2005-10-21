/**
 * @author
 * 
 * $Id: SexDistributionManagerImpl.java,v 1.6 2005-10-21 17:55:58 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2005/09/23 14:55:16  georgeda
 * Made SexDistribution a reference table
 *
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.SexDistribution;
import gov.nih.nci.camod.service.SexDistributionManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;

import java.util.List;

/**
 * Manager provides lookup by type
 */
public class SexDistributionManagerImpl extends BaseManager implements SexDistributionManager {

    /**
     * Get the SexDistribution by it's type
     * 
     * @param inType the type of the sex-distribution
     * 
     * @return the sex distribution that matches the type
     */
    public SexDistribution getByType(String inType) {

        SexDistribution sexDistribution = null;

        try {

            // The following two objects are needed for eQBE.
            SexDistribution theSexDistribution = new SexDistribution();
            theSexDistribution.setType(inType);

            // Apply evaluators to object properties
            Evaluation theEvaluation = new Evaluation();
            theEvaluation.addEvaluator("sexDistribution.type", Evaluator.EQUAL);

            List theList = Search.query(theSexDistribution, theEvaluation);

            if (theList != null && theList.size() > 0) {
                sexDistribution = (SexDistribution) theList.get(0);
            }

        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in SexDistributionManagerImpl.getByType");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in SexDistributionManagerImpl.getByType");
            e.printStackTrace();
        }

        return sexDistribution;
    }
}
