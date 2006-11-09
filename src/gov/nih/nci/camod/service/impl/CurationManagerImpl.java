/**
 *  @author dgeorge
 *  
 *  $Id: CurationManagerImpl.java,v 1.7 2006-11-09 17:37:07 pandyas Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.6  2006/01/18 14:24:24  georgeda
 *  TT# 376 - Updated to use new Java 1.5 features
 *
 *  Revision 1.5  2005/09/19 13:09:00  georgeda
 *  Slight change to interface
 *
 *  
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.CurationManager;

/**
 * CurationManager implementation.
 */
public class CurationManagerImpl extends AbstractCurationManager
{
    /**
     * Constructor. Takes in a curation XML
     * 
     * @param inWorkflowFile
     *            the XML file defining the curation flow
     */
    public CurationManagerImpl(String inWorkflowFile)
    {
        super.init(inWorkflowFile);
        init();
    }

    // Register any events associated w/ the curation flow
    private void init()
    {
        myActionFactory.registerAction("emailAssignee", new EmailActionImpl());
    }

    /**
     * Test main
     */
    public static void main(java.lang.String[] args)
    {
        CurationManager theCurationManager = new CurationManagerImpl("C:/dev/workspace/camod_dev/WebRoot/config/CurationConfig.xml");

        AnimalModel theAnimalModel = new AnimalModel();

        theAnimalModel.setState(theCurationManager.getDefaultState());

        //System.out.println("1) Current state: " + theAnimalModel.getState());
        theCurationManager.changeState(theAnimalModel, "");
        //System.out.println("2) Current state: " + theAnimalModel.getState());
        theCurationManager.changeState(theAnimalModel, "");
        //System.out.println("3) Current state: " + theAnimalModel.getState());
        theCurationManager.changeState(theAnimalModel, "approve");
        //System.out.println("4) Current state: " + theAnimalModel.getState());
        theCurationManager.changeState(theAnimalModel, "");
        //System.out.println("5) Current state: " + theAnimalModel.getState());
        theCurationManager.changeState(theAnimalModel, "need_more_info");
        //System.out.println("6) Current state: " + theAnimalModel.getState());
        theCurationManager.changeState(theAnimalModel, "approve");
        //System.out.println("7) Current state: " + theAnimalModel.getState());
    }
}
