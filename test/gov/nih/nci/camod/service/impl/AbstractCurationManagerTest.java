/*
 * AbstractCurationManagerTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:34 PM
 */

package gov.nih.nci.camod.service.impl;

import junit.framework.*;
import gov.nih.nci.camod.domain.Curateable;
import gov.nih.nci.camod.service.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.*;

/**
 *
 * @author piparom
 */
public class AbstractCurationManagerTest extends TestCase {
  
  public AbstractCurationManagerTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(AbstractCurationManagerTest.class);
    
    return suite;
  }

  /**
   * Test of init method, of class gov.nih.nci.camod.service.impl.AbstractCurationManager.
   */
  public void testInit() {
    System.out.println("testInit");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of getAllStateNames method, of class gov.nih.nci.camod.service.impl.AbstractCurationManager.
   */
  public void testGetAllStateNames() {
    System.out.println("testGetAllStateNames");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of getDefaultState method, of class gov.nih.nci.camod.service.impl.AbstractCurationManager.
   */
  public void testGetDefaultState() {
    System.out.println("testGetDefaultState");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of changeState method, of class gov.nih.nci.camod.service.impl.AbstractCurationManager.
   */
  public void testChangeState() {
    System.out.println("testChangeState");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of applyActionsForState method, of class gov.nih.nci.camod.service.impl.AbstractCurationManager.
   */
  public void testApplyActionsForState() {
    System.out.println("testApplyActionsForState");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Generated implementation of abstract class gov.nih.nci.camod.service.impl.AbstractCurationManager. Please fill dummy bodies of generated methods.
   */
  private class AbstractCurationManagerImpl extends AbstractCurationManager {

    public java.lang.String getDefaultState() {
      // TODO fill the body in order to provide useful implementation
      
      return null;
    }

    public java.util.List getAllStateNames() {
      // TODO fill the body in order to provide useful implementation
      
      return null;
    }

    public void changeState(gov.nih.nci.camod.domain.Curateable inCuratableObject, java.lang.String inEvent) {
      // TODO fill the body in order to provide useful implementation
      
    }

    public void applyActionsForState(gov.nih.nci.camod.domain.Curateable inCuratableObject, java.util.Map inArgs) {
      // TODO fill the body in order to provide useful implementation
      
    }
  }

  
}
