/*
 * EmailActionImplTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:37 PM
 */

package gov.nih.nci.camod.service.impl;

import junit.framework.*;
import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Curateable;
import gov.nih.nci.camod.domain.Log;
import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.service.CurateableAction;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.AnimalModelStateData;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author piparom
 */
public class EmailActionImplTest extends TestCase {
  
  public EmailActionImplTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(EmailActionImplTest.class);
    
    return suite;
  }

  /**
   * Test of create method, of class gov.nih.nci.camod.service.impl.EmailActionImpl.
   */
  public void testCreate() {
    System.out.println("testCreate");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of execute method, of class gov.nih.nci.camod.service.impl.EmailActionImpl.
   */
  public void testExecute() {
    System.out.println("testExecute");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
