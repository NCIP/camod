/*
 * CurrencyConverterTest.java
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
public class CurrencyConverterTest extends TestCase {
  
  public CurrencyConverterTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(CurrencyConverterTest.class);
    
    return suite;
  }

  /**
   * Test of convert method, of class gov.nih.nci.camod.util.CurrencyConverter.
   */
  public void testConvert() {
    System.out.println("testConvert");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
