/*
 * GUIDGeneratorTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:13 PM
 */

package gov.nih.nci.camod.util;

import junit.framework.*;
import java.util.*;
import java.net.InetAddress;

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
