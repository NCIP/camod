/*
 * SexDistributionManagerImplTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:37 PM
 */

package unit.gov.nih.nci.camod.service.impl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author piparom
 */
public class SexDistributionManagerImplTest extends TestCase {
  
  public SexDistributionManagerImplTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(SexDistributionManagerImplTest.class);
    
    return suite;
  }

  /**
   * Test of getByType method, of class gov.nih.nci.camod.service.impl.SexDistributionManagerImpl.
   */
  public void testGetByType() {
    System.out.println("testGetByType");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
