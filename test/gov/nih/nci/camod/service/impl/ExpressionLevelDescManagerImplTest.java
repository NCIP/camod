/*
 * ExpressionLevelDescManagerImplTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:37 PM
 */

package gov.nih.nci.camod.service.impl;

import junit.framework.*;
import gov.nih.nci.camod.domain.ExpressionLevelDesc;
import gov.nih.nci.camod.service.ExpressionLevelDescManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;
import java.util.List;

/**
 *
 * @author piparom
 */
public class ExpressionLevelDescManagerImplTest extends TestCase {
  
  public ExpressionLevelDescManagerImplTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(ExpressionLevelDescManagerImplTest.class);
    
    return suite;
  }

  /**
   * Test of getAll method, of class gov.nih.nci.camod.service.impl.ExpressionLevelDescManagerImpl.
   */
  public void testGetAll() {
    System.out.println("testGetAll");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of getByName method, of class gov.nih.nci.camod.service.impl.ExpressionLevelDescManagerImpl.
   */
  public void testGetByName() {
    System.out.println("testGetByName");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
