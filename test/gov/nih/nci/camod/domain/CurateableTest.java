/*
 * CurateableTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 1:19 PM
 */

package gov.nih.nci.camod.domain;

import junit.framework.*;
import java.io.Serializable;

/**
 *
 * @author piparom
 */
public class CurateableTest extends TestCase {
  
  public CurateableTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(CurateableTest.class);
    
    return suite;
  }

  /**
   * Test of getState method, of class gov.nih.nci.camod.domain.Curateable.
   */
  public void testGetState() {
    System.out.println("testGetState");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of setState method, of class gov.nih.nci.camod.domain.Curateable.
   */
  public void testSetState() {
    System.out.println("testSetState");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Generated implementation of abstract class gov.nih.nci.camod.domain.Curateable. Please fill dummy bodies of generated methods.
   */
  private class CurateableImpl implements Curateable {

    public java.lang.String getState() {
      // TODO fill the body in order to provide useful implementation
      
      return null;
    }

    public void setState(java.lang.String state) {
      // TODO fill the body in order to provide useful implementation
      
    }
  }

  
}
