/**
 * 
 * $Id: AnimalModelManager.java,v 1.6 2005-09-23 14:54:58 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2005/09/16 15:52:54  georgeda
 * Changes due to manager re-write
 *
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Log;
import gov.nih.nci.camod.webapp.form.ModelCharacteristics;

import java.util.List;

/**
 * Interface for the AnimalModelManager class. See implementing classes for
 * details.
 */
public interface AnimalModelManager {

    public List getAll() throws Exception;

    public List getAllByUser(String username) throws Exception;

    public List getAllByState(String inState) throws Exception;

    public AnimalModel get(String id) throws Exception;

    public void save(AnimalModel animalModel) throws Exception;

    public void updateAndAddLog(AnimalModel inAnimalModel, Log inLog) throws Exception;

    public void update(ModelCharacteristics inModelCharacteristics, AnimalModel inAnimalModel) throws Exception;

    public AnimalModel create(ModelCharacteristics inModelCharacteristics, String inUsername) throws Exception;

    public void remove(String id) throws Exception;

    public List search() throws Exception;
}
