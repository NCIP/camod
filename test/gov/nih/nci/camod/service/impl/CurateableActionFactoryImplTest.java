/*
 * CurateableActionFactoryImplTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:37 PM
 */

package gov.nih.nci.camod.service.impl;

import junit.framework.*;
import gov.nih.nci.camod.service.CurateableAction;
import gov.nih.nci.camod.service.CurateableActionFactory;
import java.util.HashMap;

/**
 *
 * @author piparom
 */
public class CurateableActionFactoryImplTest extends TestCase {
  
  public CurateableActionFactoryImplTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(CurateableActionFactoryImplTest.class);
    
    return suite;
  }

  /**
   * Test of registerAction method, of class gov.nih.nci.camod.service.impl.CurateableActionFactoryImpl.
   */
  public void testRegisterAction() {
    System.out.println("testRegisterAction");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of getAction method, of class gov.nih.nci.camod.service.impl.CurateableActionFactoryImpl.
   */
  public void testGetAction() {
    System.out.println("testGetAction");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
