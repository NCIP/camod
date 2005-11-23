/*
 * BaseCurateableActionTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:35 PM
 */

package gov.nih.nci.camod.service.impl;

import junit.framework.*;
import gov.nih.nci.camod.domain.Curateable;
import gov.nih.nci.camod.service.CurateableAction;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author piparom
 */
public class BaseCurateableActionTest extends TestCase {
  
  public BaseCurateableActionTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(BaseCurateableActionTest.class);
    
    return suite;
  }

  /**
   * Test of create method, of class gov.nih.nci.camod.service.impl.BaseCurateableAction.
   */
  public void testCreate() {
    System.out.println("testCreate");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of execute method, of class gov.nih.nci.camod.service.impl.BaseCurateableAction.
   */
  public void testExecute() {
    System.out.println("testExecute");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Generated implementation of abstract class gov.nih.nci.camod.service.impl.BaseCurateableAction. Please fill dummy bodies of generated methods.
   */
  private class BaseCurateableActionImpl extends BaseCurateableAction {

    public gov.nih.nci.camod.service.CurateableAction create() {
      // TODO fill the body in order to provide useful implementation
      
      return null;
    }

    public void execute(java.util.Map inArgs, gov.nih.nci.camod.domain.Curateable inObject) {
      // TODO fill the body in order to provide useful implementation
      
    }
  }

  
}
