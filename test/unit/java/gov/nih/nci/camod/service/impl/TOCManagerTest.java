/*
 * TOCManagerTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:37 PM
 */

package gov.nih.nci.camod.service.impl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author piparom
 */
public class TOCManagerTest extends TestCase {
  
  public TOCManagerTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(TOCManagerTest.class);
    
    return suite;
  }

  /**
   * Test of process method, of class gov.nih.nci.camod.service.impl.TOCManager.
   */
  public void testProcess() {
    System.out.println("testProcess");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of main method, of class gov.nih.nci.camod.service.impl.TOCManager.
   */
  public void testMain() {
    System.out.println("testMain");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
