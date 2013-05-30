/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
*
* $Id: StrutsActionTests.java,v 1.1 2009-07-13 17:45:12 pandyas Exp $
*
* $Log: not supported by cvs2svn $
* Revision 1.1  2009/07/07 17:46:54  pandyas
* modified according to the recommended directory layout for BDA
*
* Revision 1.1  2005/12/28 16:41:57  georgeda
* Changes for testing
*
*/
package unit.gov.nih.nci.camod.webapp.action;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * @author georgeda
 *
 */
public class StrutsActionTests
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite();

        suite.addTest(AdminEditUserActionTest.suite());
        suite.addTest(LoginActionTest.suite());
        suite.addTest(ReturnUserModelsTest.suite());

        return suite;
    }

    public static void main(String args[])
    {
        TestRunner.run(suite());
    }
}
