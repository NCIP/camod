/*
 * TaxonManagerSingletonTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:38 PM
 */

package unit.gov.nih.nci.camod.service.impl;

import junit.framework.*;

/**
 *
 * @author piparom
 */
public class TaxonManagerSingletonTest extends TestCase {
  
  public TaxonManagerSingletonTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(TaxonManagerSingletonTest.class);
    
    return suite;
  }

  /**
   * Test of instance method, of class gov.nih.nci.camod.service.impl.TaxonManagerSingleton.
   */
  public void testInstance() {
    System.out.println("testInstance");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
