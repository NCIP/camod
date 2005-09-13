package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Curateable;

import java.util.Map;


/**
 * This interface describes a realized/implementing CurationManager.
 */
public interface CurateableAction {
    public void execute(Map inArgs, Curateable inObject);
    public CurateableAction create();
}
