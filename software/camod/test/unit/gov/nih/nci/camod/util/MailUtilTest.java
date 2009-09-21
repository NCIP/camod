/*
 * MailUtilTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:13 PM
 */

package unit.gov.nih.nci.camod.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author piparom
 */
public class MailUtilTest extends TestCase {
  
  public MailUtilTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(MailUtilTest.class);
    
    return suite;
  }

  /**
   * Test of sendMail method, of class gov.nih.nci.camod.util.MailUtil.
   */
  public void testSendMail() {
    System.out.println("testSendMail");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of main method, of class gov.nih.nci.camod.util.MailUtil.
   */
  public void testMain() {
    System.out.println("testMain");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
