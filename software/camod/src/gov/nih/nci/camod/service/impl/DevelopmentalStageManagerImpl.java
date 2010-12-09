/**
 *
 * $Id: DevelopmentalStageManagerImpl.java,v 1.3 2009-05-20 17:27:02 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2007/09/12 19:36:03  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.1  2007/05/16 12:29:10  pandyas
 * Added developmental stage evs tree to Therapy when species is Zebrafsih
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.DevelopmentalStage;
import gov.nih.nci.camod.service.DevelopmentalStageManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;
import java.util.List;

/**
 * @author pandyas
 */
public class DevelopmentalStageManagerImpl extends BaseManager implements DevelopmentalStageManager {

    /**
     * Get all DevelopmentalStage objects
     * 
     * 
     * @return the matching DevelopmentalStage objects, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public List getAll() throws Exception
    {
        log.debug("In DevelopmentalStageManagerImpl.getAll");
        return super.getAll(DevelopmentalStage.class);
    }	

    /**
     * Get a specific DevelopmentalStage by id
     * 
     * @param id
     *            the unique id for a DevelopmentalStage
     * 
     * @return the matching DevelopmentalStage object, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public DevelopmentalStage get(String id) throws Exception
    {
        log.debug("In DevelopmentalStageManagerImpl.get");
        return (DevelopmentalStage) super.get(id, DevelopmentalStage.class);
    }
    
    /**
     * Get a specific DevelopmentalStage by name
     * 
     * @param name
     *            the unique name for a DevelopmentalStage
     * 
     * @return the matching DevelopmentalStage, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public DevelopmentalStage getByName(String inName) throws Exception
    {
    	DevelopmentalStage devStage = null;

        if (inName != null && inName.length() > 0)
        {
            try
            {
                // The following two objects are needed for eQBE.
            	DevelopmentalStage theDevStage = new DevelopmentalStage();
            	theDevStage.setName(inName);

                // Apply evaluators to object properties
                Evaluation theEvaluation = new Evaluation();
                theEvaluation.addEvaluator("DevelopmentalStage.name", Evaluator.EQUAL);

                List theList = Search.query(theDevStage, theEvaluation);

                if (theList != null && theList.size() > 0)
                {
                	devStage = (DevelopmentalStage) theList.get(0);
                }
            }
            catch (PersistenceException pe)
            {
                log.error("PersistenceException in getByName", pe);
                throw pe;
            }
            catch (Exception e)
            {
                log.error("Exception in getByName", e);
                throw e;
            }
        }
        return devStage;
    } 
    
    /**
     * Get or create a specific DevelopmentalStage
     * 
     * @param inConceptCode
     *            the concept code for an DevelopmentalStage
     * @param inName
     *            the name of the DevelopmentalStage and not the preferred desc. from EVS (removed in LexEVS 5.1 upgrade)
     * 
     * @return the matching DevelopmentalStage, or create a new one if nothing matched
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public DevelopmentalStage getOrCreate(String inConceptCode,
                             String inName) throws Exception
    {
        log.debug("Entered DevelopmentalStageManagerImpl.getOrCreate");   

        DevelopmentalStage theQBEStage = new DevelopmentalStage();
        DevelopmentalStage theStage = null;
        
        theQBEStage.setConceptCode(inConceptCode);        
        
        try {
        	List theList = Search.query(theQBEStage);
        	
            // Does exist, return object. 
            if (theList != null && theList.size() > 0)
            {
            	theStage = (DevelopmentalStage) theList.get(0);
            } 
            //  Doesn't exist. Create object with either name 
            else
            {
            	theStage = theQBEStage;
                theQBEStage.setName(inName);
            }        	
        }
        catch (Exception e)
        {
            log.error("Error querying for matching dev stage object.  Creating new one.", e);
        }        
        log.debug("DevelopmentalStageManagerImpl getOrCreate theDevStage: " + theStage.toString()); 
        return theStage;
    }    
}
