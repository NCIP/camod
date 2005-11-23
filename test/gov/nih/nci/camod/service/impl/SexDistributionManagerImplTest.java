/*
 * SexDistributionManagerImplTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:37 PM
 */

package gov.nih.nci.camod.service.impl;

import junit.framework.*;
import gov.nih.nci.camod.domain.SexDistribution;
import gov.nih.nci.camod.service.SexDistributionManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;
import java.util.List;

/**
 *
 * @author piparom
 */
public class SexDistributionManagerImplTest extends TestCase {
  
  public SexDistributionManagerImplTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(SexDistributionManagerImplTest.class);
    
    return suite;
  }

  /**
   * Test of getByType method, of class gov.nih.nci.camod.service.impl.SexDistributionManagerImpl.
   */
  public void testGetByType() {
    System.out.println("testGetByType");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
