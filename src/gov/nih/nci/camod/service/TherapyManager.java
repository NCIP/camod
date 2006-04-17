/**
 * @author dgeorge
 * 
 * $Id: TherapyManager.java,v 1.9 2006-04-17 19:13:16 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.8  2005/11/09 00:17:06  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.7  2005/11/02 19:03:25  pandyas
 * Added e-mail functionality
 *
 * Revision 1.6  2005/10/06 19:28:14  pandyas
 * modified for Therapy screen
 *
 * Revision 1.5  2005/09/28 21:19:49  georgeda
 * Finished up converting to new manager
 *
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.webapp.form.*;

/**
 * Interface for managing the therapy objects
 */
public interface TherapyManager {

    public Therapy get(String id) throws Exception;

    public void save(Therapy therapy) throws Exception;

    public void remove(String id, AnimalModel inAnimalModel) throws Exception;

    public Therapy create(AnimalModel inAnimalModel, TherapyData inTherapyData) throws Exception;
    
    public void update(AnimalModel inAnimalModel, TherapyData inTherapyData, Therapy inTherapy) throws Exception;    
}
