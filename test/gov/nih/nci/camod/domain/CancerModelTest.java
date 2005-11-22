/*
 * CancerModelTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 1:19 PM
 */

package gov.nih.nci.camod.domain;

import junit.framework.*;

/**
 *
 * @author piparom
 */
public class CancerModelTest extends TestCase {
  
  public CancerModelTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(CancerModelTest.class);
    
    return suite;
  }

  /**
   * Generated implementation of abstract class gov.nih.nci.camod.domain.CancerModel. Please fill dummy bodies of generated methods.
   */
  private class CancerModelImpl implements CancerModel {

    public java.lang.String getState() {
      // TODO fill the body in order to provide useful implementation
      
      return null;
    }

    public void setState(java.lang.String state) {
      // TODO fill the body in order to provide useful implementation
      
    }
  }

  
}
