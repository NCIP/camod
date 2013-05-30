/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 *
 * $Id: AllTests.java,v 1.1 2009-07-13 17:45:12 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2009/07/07 17:46:54  pandyas
 * modified according to the recommended directory layout for BDA
 *
 * Revision 1.4  2007/02/09 16:23:57  pandyas
 * Removed
 *
 * Revision 1.3  2006/10/23 17:08:25  pandyas
 * modified
 *
 * Revision 1.2  2005/12/28 16:41:57  georgeda
 * Changes for testing
 *
 * Revision 1.1  2005/12/27 15:04:33  georgeda
 * Test cleanup
 *
 */
package unit.alltests;

/**
 * @author georgeda
 */
import unit.gov.nih.nci.camod.webapp.action.AdminEditUserActionTest;
import unit.gov.nih.nci.camod.webapp.action.LoginActionTest;
import unit.gov.nih.nci.camod.webapp.action.ReturnUserModelsTest;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import unit.gov.nih.nci.camod.webapp.action.StrutsActionTests;

public class AllTests
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite();

        //suite.addTest(AdminEditUserActionTest.suite());
        //suite.addTest(LoginActionTest.suite());
        suite.addTest(StrutsActionTests.suite());


        return suite;
    }

    public static void main(String args[])
    {
        TestRunner.run(suite());
    }
}
