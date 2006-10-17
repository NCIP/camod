/**
 * 
 * $Id: TransientInterferenceManager.java,v 1.1 2006-10-17 16:14:18 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2006/05/03 20:03:10  pandyas
 * Modified to add Morpholino object data to application
 *
 * Revision 1.4  2006/04/17 19:13:16  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.service;

import java.util.List;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.TransientInterference;
import gov.nih.nci.camod.webapp.form.TransientInterferenceData;

/**
 * See implementing classes for details
 */
public interface TransientInterferenceManager {
    
    public List getAll() throws Exception;    
    
    public TransientInterference get(String id) throws Exception;

    public void save(TransientInterference transientInterference) throws Exception;

    public void remove(String id, AnimalModel inAnimalModel) throws Exception;
    
    public TransientInterference create(AnimalModel inAnimalModel, TransientInterferenceData inTransientInterferenceData) throws Exception;
    
    public void update(AnimalModel inAnimalModel, TransientInterferenceData inTransientInterferenceData, TransientInterference inTransientInterference) throws Exception;        

}
