/*
 * DuplicatableTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:13 PM
 */

package gov.nih.nci.camod.util;

import junit.framework.*;

/**
 *
 * @author piparom
 */
public class DuplicatableTest extends TestCase {
  
  public DuplicatableTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(DuplicatableTest.class);
    
    return suite;
  }

  /**
   * Generated implementation of abstract class gov.nih.nci.camod.util.Duplicatable. Please fill dummy bodies of generated methods.
   */
  private class DuplicatableImpl implements Duplicatable {
  }

  
}
