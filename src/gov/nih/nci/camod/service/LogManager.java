package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.*;

import java.util.List;

/**
 * Interface for the Log object
 * 
 * See implementing classes for details
 */
public interface LogManager {

    public Log getCurrentByModelAndAssigned(AnimalModel inModel, Person inAssignedUser);

    public Log getCurrentByModel(AnimalModel inModel);

    public List getAll();

    public Log get(String inId);

    public void save(Log inLog);

    public void save(String inAssignedPersonId, String inModelId, String inState, String inNotes);

    public void remove(String inId);
}
