/*
 * SpontaneousMutationManagerSingletonTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:37 PM
 */

package gov.nih.nci.camod.service.impl;

import junit.framework.*;
import gov.nih.nci.camod.service.SpontaneousMutationManager;

/**
 *
 * @author piparom
 */
public class SpontaneousMutationManagerSingletonTest extends TestCase {
  
  public SpontaneousMutationManagerSingletonTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(SpontaneousMutationManagerSingletonTest.class);
    
    return suite;
  }

  /**
   * Test of instance method, of class gov.nih.nci.camod.service.impl.SpontaneousMutationManagerSingleton.
   */
  public void testInstance() {
    System.out.println("testInstance");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
