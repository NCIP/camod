package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Curateable;

import java.util.List;


/**
 * This interface describes a realized/implementing CurationManager.
 */
public interface CurateableAction {
    public void execute(List inArgs, Curateable inObject);
    public CurateableAction create();
}
