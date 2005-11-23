/*
 * ModificationTypeManagerImplTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:37 PM
 */

package gov.nih.nci.camod.service.impl;

import junit.framework.*;
import gov.nih.nci.camod.domain.ModificationType;
import gov.nih.nci.camod.service.ModificationTypeManager;
import gov.nih.nci.common.persistence.Search;
import java.util.List;

/**
 *
 * @author piparom
 */
public class ModificationTypeManagerImplTest extends TestCase {
  
  public ModificationTypeManagerImplTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(ModificationTypeManagerImplTest.class);
    
    return suite;
  }

  /**
   * Test of getByName method, of class gov.nih.nci.camod.service.impl.ModificationTypeManagerImpl.
   */
  public void testGetByName() {
    System.out.println("testGetByName");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
