/*
 * AgentTargetManagerImplTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:35 PM
 */

package gov.nih.nci.camod.service.impl;

import junit.framework.*;
import gov.nih.nci.camod.domain.AgentTarget;
import gov.nih.nci.camod.service.AgentTargetManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;
import java.util.List;

/**
 *
 * @author piparom
 */
public class AgentTargetManagerImplTest extends TestCase {
  
  public AgentTargetManagerImplTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(AgentTargetManagerImplTest.class);
    
    return suite;
  }

  /**
   * Test of getByName method, of class gov.nih.nci.camod.service.impl.AgentTargetManagerImpl.
   */
  public void testGetByName() {
    System.out.println("testGetByName");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
