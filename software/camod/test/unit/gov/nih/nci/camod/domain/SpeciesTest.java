/*
 * SpeciesTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 1:19 PM
 */

package unit.gov.nih.nci.camod.domain;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author piparom
 */
public class SpeciesTest extends TestCase {
  
  public SpeciesTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(SpeciesTest.class);
    
    return suite;
  }

  /**
   * Test of getName method, of class gov.nih.nci.camod.domain.Species.
   */
  public void testGetName() {
    System.out.println("testGetName");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of setName method, of class gov.nih.nci.camod.domain.Species.
   */
  public void testSetName() {
    System.out.println("testSetName");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
