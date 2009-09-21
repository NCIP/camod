/*
 * TimestampConverterTest.java
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
public class TimestampConverterTest extends TestCase {
  
  public TimestampConverterTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(TimestampConverterTest.class);
    
    return suite;
  }

  /**
   * Test of convertToDate method, of class gov.nih.nci.camod.util.TimestampConverter.
   */
  public void testConvertToDate() {
    System.out.println("testConvertToDate");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of convertToString method, of class gov.nih.nci.camod.util.TimestampConverter.
   */
  public void testConvertToString() {
    System.out.println("testConvertToString");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
