/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: LoginTest.java,v 1.1 2009-07-13 17:45:12 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2009/07/07 17:46:54  pandyas
 * modified according to the recommended directory layout for BDA
 *
 * Revision 1.2  2005/12/14 20:15:42  pandyas
 * Added JavaDocs
 *
 * 
 */

package unit.web;

import junit.framework.Test;
import junit.framework.TestSuite;
import unit.web.base.BaseHttpTest;

/** This is a simple example of using HttpUnit to read and understand web pages. * */
public class LoginTest extends BaseHttpTest {

    public LoginTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
        logoutOfApplication();
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(LoginTest.class);
        return suite;
    }

    public void testLogin() throws Exception {
    	System.out.println("test/unit/web/LoginTest.testLogin");
        loginToApplication("camodcurator2", "10and10=20");
        assertCurrentPageContains("Submit and Edit Models");
        logoutOfApplication();
    }

    public void testBadPassword() throws Exception {
        loginToApplication("camodcurator2", "10and10=20");
        assertCurrentPageContains("Invalid username and/or password"); 
    }
}
