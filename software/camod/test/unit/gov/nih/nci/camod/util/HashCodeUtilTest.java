/*
 * HashCodeUtilTest.java
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
public class HashCodeUtilTest extends TestCase {
  
  public HashCodeUtilTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(HashCodeUtilTest.class);
    
    return suite;
  }

  /**
   * Test of hash method, of class gov.nih.nci.camod.util.HashCodeUtil.
   */
  public void testHash() {
    System.out.println("testHash");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of notEqual method, of class gov.nih.nci.camod.util.HashCodeUtil.
   */
  public void testNotEqual() {
    System.out.println("testNotEqual");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
