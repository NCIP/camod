/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * GUIDGeneratorTest.java
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
public class GUIDGeneratorTest extends TestCase {
  
  public GUIDGeneratorTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(GUIDGeneratorTest.class);
    
    return suite;
  }

  /**
   * Test of getInstance method, of class gov.nih.nci.camod.util.GUIDGenerator.
   */
  public void testGetInstance() {
    System.out.println("testGetInstance");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of genNewGuid method, of class gov.nih.nci.camod.util.GUIDGenerator.
   */
  public void testGenNewGuid() {
    System.out.println("testGenNewGuid");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
