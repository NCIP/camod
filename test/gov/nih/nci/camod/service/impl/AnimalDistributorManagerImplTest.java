/*
 * AnimalDistributorManagerImplTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:35 PM
 */

package gov.nih.nci.camod.service.impl;

import junit.framework.*;
import java.util.List;
import gov.nih.nci.camod.domain.AnimalDistributor;
import gov.nih.nci.camod.service.AnimalDistributorManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;

/**
 *
 * @author piparom
 */
public class AnimalDistributorManagerImplTest extends TestCase {
  
  public AnimalDistributorManagerImplTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(AnimalDistributorManagerImplTest.class);
    
    return suite;
  }

  /**
   * Test of getByName method, of class gov.nih.nci.camod.service.impl.AnimalDistributorManagerImpl.
   */
  public void testGetByName() {
    System.out.println("testGetByName");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
