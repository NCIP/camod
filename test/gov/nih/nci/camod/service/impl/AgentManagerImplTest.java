/*
 * AgentManagerImplTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:35 PM
 */

package gov.nih.nci.camod.service.impl;

import junit.framework.*;
import gov.nih.nci.cabio.domain.impl.AgentImpl;
import gov.nih.nci.camod.domain.Agent;
import gov.nih.nci.camod.service.AgentManager;
import gov.nih.nci.camod.util.DrugScreenResult;
import gov.nih.nci.camod.util.EvsTreeUtil;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.system.applicationservice.ApplicationService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author piparom
 */
public class AgentManagerImplTest extends TestCase {
  
  public AgentManagerImplTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(AgentManagerImplTest.class);
    
    return suite;
  }

  /**
   * Test of get method, of class gov.nih.nci.camod.service.impl.AgentManagerImpl.
   */
  public void testGet() {
    System.out.println("testGet");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of save method, of class gov.nih.nci.camod.service.impl.AgentManagerImpl.
   */
  public void testSave() {
    System.out.println("testSave");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of remove method, of class gov.nih.nci.camod.service.impl.AgentManagerImpl.
   */
  public void testRemove() {
    System.out.println("testRemove");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of getClinicalProtocols method, of class gov.nih.nci.camod.service.impl.AgentManagerImpl.
   */
  public void testGetClinicalProtocols() {
    System.out.println("testGetClinicalProtocols");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of getYeastResults method, of class gov.nih.nci.camod.service.impl.AgentManagerImpl.
   */
  public void testGetYeastResults() {
    System.out.println("testGetYeastResults");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
