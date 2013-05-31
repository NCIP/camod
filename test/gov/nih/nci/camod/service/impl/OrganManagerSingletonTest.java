/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * OrganManagerSingletonTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:37 PM
 */

package gov.nih.nci.camod.service.impl;

import junit.framework.*;
import gov.nih.nci.camod.service.OrganManager;

/**
 *
 * @author piparom
 */
public class OrganManagerSingletonTest extends TestCase {
  
  public OrganManagerSingletonTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(OrganManagerSingletonTest.class);
    
    return suite;
  }

  /**
   * Test of instance method, of class gov.nih.nci.camod.service.impl.OrganManagerSingleton.
   */
  public void testInstance() {
    System.out.println("testInstance");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
