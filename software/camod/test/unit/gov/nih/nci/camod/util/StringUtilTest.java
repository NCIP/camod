/*
 * StringUtilTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:14 PM
 */

package unit.gov.nih.nci.camod.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author piparom
 */
public class StringUtilTest extends TestCase {
  
  public StringUtilTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(StringUtilTest.class);
    
    return suite;
  }

  /**
   * Test of encodePassword method, of class gov.nih.nci.camod.util.StringUtil.
   */
  public void testEncodePassword() {
    System.out.println("testEncodePassword");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of encodeString method, of class gov.nih.nci.camod.util.StringUtil.
   */
  public void testEncodeString() {
    System.out.println("testEncodeString");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of decodeString method, of class gov.nih.nci.camod.util.StringUtil.
   */
  public void testDecodeString() {
    System.out.println("testDecodeString");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
