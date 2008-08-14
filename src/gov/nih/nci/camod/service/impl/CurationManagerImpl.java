/**
 *  @author dgeorge
 *  
 *  $Id: CurationManagerImpl.java,v 1.9 2008-08-14 16:32:36 pandyas Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.8  2007/08/08 16:38:29  pandyas
 *  removed reference to camod_dev - project name change to camod
 *
 *  Revision 1.7  2006/11/09 17:37:07  pandyas
 *  Commented out debug code
 *
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
        CurationManager theCurationManager = new CurationManagerImpl("C:/dev/workspace/camod/WebRoot/config/CurationConfig.xml");

        AnimalModel theAnimalModel = new AnimalModel();

        theAnimalModel.setState(theCurationManager.getDefaultState());

        theCurationManager.changeState(theAnimalModel, "");
        theCurationManager.changeState(theAnimalModel, "");
        theCurationManager.changeState(theAnimalModel, "approve");
        theCurationManager.changeState(theAnimalModel, "");
        theCurationManager.changeState(theAnimalModel, "need_more_info");
        theCurationManager.changeState(theAnimalModel, "approve");
    }
}
