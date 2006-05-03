/**
 * 
 * $Id: MorpholinoManager.java,v 1.1 2006-05-03 20:03:10 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2006/04/17 19:13:16  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.service;

import java.util.List;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Morpholino;
import gov.nih.nci.camod.webapp.form.MorpholinoData;

/**
 * See implementing classes for details
 */
public interface MorpholinoManager {
    
    public List getAll() throws Exception;    
    
    public Morpholino get(String id) throws Exception;

    public void save(Morpholino morpholino) throws Exception;

    public void remove(String id, AnimalModel inAnimalModel) throws Exception;
    
    public Morpholino create(AnimalModel inAnimalModel, MorpholinoData inMorpholinoData) throws Exception;
    
    public void update(AnimalModel inAnimalModel, MorpholinoData inMorpholinoData, Morpholino inMorpholino) throws Exception;        


}
