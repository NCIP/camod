/*
 * PersonManagerImplTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:37 PM
 */

package gov.nih.nci.camod.service.impl;

import junit.framework.*;
import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.service.PersonManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author piparom
 */
public class PersonManagerImplTest extends TestCase {
  
  public PersonManagerImplTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(PersonManagerImplTest.class);
    
    return suite;
  }

  /**
   * Test of getAll method, of class gov.nih.nci.camod.service.impl.PersonManagerImpl.
   */
  public void testGetAll() {
    System.out.println("testGetAll");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of get method, of class gov.nih.nci.camod.service.impl.PersonManagerImpl.
   */
  public void testGet() {
    System.out.println("testGet");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of getByUsername method, of class gov.nih.nci.camod.service.impl.PersonManagerImpl.
   */
  public void testGetByUsername() {
    System.out.println("testGetByUsername");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of getByRole method, of class gov.nih.nci.camod.service.impl.PersonManagerImpl.
   */
  public void testGetByRole() {
    System.out.println("testGetByRole");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of save method, of class gov.nih.nci.camod.service.impl.PersonManagerImpl.
   */
  public void testSave() {
    System.out.println("testSave");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of remove method, of class gov.nih.nci.camod.service.impl.PersonManagerImpl.
   */
  public void testRemove() {
    System.out.println("testRemove");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
