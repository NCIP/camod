/*
 * BaseManagerTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:35 PM
 */

package gov.nih.nci.camod.service.impl;

import junit.framework.*;
import gov.nih.nci.camod.service.Manager;
import gov.nih.nci.common.persistence.Persist;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.HibernateUtil;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author piparom
 */
public class BaseManagerTest extends TestCase {
  
  public BaseManagerTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(BaseManagerTest.class);
    
    return suite;
  }

  /**
   * Test of getAll method, of class gov.nih.nci.camod.service.impl.BaseManager.
   */
  public void testGetAll() {
    System.out.println("testGetAll");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of get method, of class gov.nih.nci.camod.service.impl.BaseManager.
   */
  public void testGet() {
    System.out.println("testGet");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of save method, of class gov.nih.nci.camod.service.impl.BaseManager.
   */
  public void testSave() {
    System.out.println("testSave");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of remove method, of class gov.nih.nci.camod.service.impl.BaseManager.
   */
  public void testRemove() {
    System.out.println("testRemove");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
