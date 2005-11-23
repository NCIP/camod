/*
 * AgentTargetManagerSingletonTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:35 PM
 */

package gov.nih.nci.camod.service.impl;

import junit.framework.*;
import gov.nih.nci.camod.service.AgentTargetManager;

/**
 *
 * @author piparom
 */
public class AgentTargetManagerSingletonTest extends TestCase {
  
  public AgentTargetManagerSingletonTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(AgentTargetManagerSingletonTest.class);
    
    return suite;
  }

  /**
   * Test of instance method, of class gov.nih.nci.camod.service.impl.AgentTargetManagerSingleton.
   */
  public void testInstance() {
    System.out.println("testInstance");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
