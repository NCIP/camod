package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.CurationManager;

/**
 * CurationManager implementation.
 */
public class CurationManagerImpl extends AbstractCurationManager {

    public CurationManagerImpl(String inWorkflowFile) {
        super.init(inWorkflowFile);
        init();
    }

    private void init() {
        myActionFactory.registerAction("email", new EmailActionImpl());
    }

    public static void main(java.lang.String[] args) {
        CurationManager theCurationManager = new CurationManagerImpl(
                "C:/dev/workspace/camod_dev/WebRoot/config/CurationConfig.xml");

        AnimalModel theAnimalModel = new AnimalModel();

        theAnimalModel.setState(theCurationManager.getDefaultState());

        System.out.println("1) Current state: " + theAnimalModel.getState());
        theCurationManager.changeState(theAnimalModel, "");
        System.out.println("2) Current state: " + theAnimalModel.getState());
        theCurationManager.changeState(theAnimalModel, "");
        System.out.println("3) Current state: " + theAnimalModel.getState());
        theCurationManager.changeState(theAnimalModel, "approved");
        System.out.println("4) Current state: " + theAnimalModel.getState());
        theCurationManager.changeState(theAnimalModel, "");
        System.out.println("5) Current state: " + theAnimalModel.getState());
        theCurationManager.changeState(theAnimalModel, "rejected");
        System.out.println("6) Current state: " + theAnimalModel.getState());
        theCurationManager.changeState(theAnimalModel, "rejected");
        System.out.println("7) Current state: " + theAnimalModel.getState());
        theCurationManager.changeState(theAnimalModel, "approved");
        System.out.println("8) Current state: " + theAnimalModel.getState());
    }
}
