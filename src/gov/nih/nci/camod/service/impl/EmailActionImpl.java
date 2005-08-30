package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Curateable;
import gov.nih.nci.camod.service.CurateableAction;

import java.util.List;

public class EmailActionImpl implements CurateableAction {

    protected EmailActionImpl() {
    }

    public CurateableAction create() {
        return new EmailActionImpl();
    }

    public void execute(List inArgs, Curateable inObject) {
        System.out.println("Executing EmailActionImpl: " + inArgs);
        
        AnimalModel theAnimalModel = (AnimalModel) inObject;
        System.out.println("Current state for email: " + theAnimalModel.getState());
        // TODO: How do we get the editor/etc for this model?
    }
}
