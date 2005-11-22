/*
 * BaseObjectTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 1:19 PM
 */

package gov.nih.nci.camod.domain;

import junit.framework.*;
import java.io.Serializable;
import gov.nih.nci.camod.util.HashCodeUtil;
import gov.nih.nci.camod.util.GUIDGenerator;

/**
 *
 * @author piparom
 */
public class BaseObjectTest extends TestCase {
  
  public BaseObjectTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(BaseObjectTest.class);
    
    return suite;
  }

  /**
   * Test of getId method, of class gov.nih.nci.camod.domain.BaseObject.
   */
  public void testGetId() {
    System.out.println("testGetId");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of equals method, of class gov.nih.nci.camod.domain.BaseObject.
   */
  public void testEquals() {
    System.out.println("testEquals");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of hashCode method, of class gov.nih.nci.camod.domain.BaseObject.
   */
  public void testHashCode() {
    System.out.println("testHashCode");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of toString method, of class gov.nih.nci.camod.domain.BaseObject.
   */
  public void testToString() {
    System.out.println("testToString");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of compareTo method, of class gov.nih.nci.camod.domain.BaseObject.
   */
  public void testCompareTo() {
    System.out.println("testCompareTo");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Generated implementation of abstract class gov.nih.nci.camod.domain.BaseObject. Please fill dummy bodies of generated methods.
   */
  private class BaseObjectImpl extends BaseObject {

    public int compareTo(java.lang.Object p0) {
      // TODO fill the body in order to provide useful implementation
      
      return 0;
    }
  }

  
}
