/*
 * LDAPUtilTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:13 PM
 */

package gov.nih.nci.camod.util;

import junit.framework.*;
import gov.nih.nci.camod.Constants;
import java.util.*;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;

/**
 *
 * @author piparom
 */
public class LDAPUtilTest extends TestCase {
  
  public LDAPUtilTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(LDAPUtilTest.class);
    
    return suite;
  }

  /**
   * Test of getEmailAddressForUser method, of class gov.nih.nci.camod.util.LDAPUtil.
   */
  public void testGetEmailAddressForUser() {
    System.out.println("testGetEmailAddressForUser");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of main method, of class gov.nih.nci.camod.util.LDAPUtil.
   */
  public void testMain() {
    System.out.println("testMain");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
