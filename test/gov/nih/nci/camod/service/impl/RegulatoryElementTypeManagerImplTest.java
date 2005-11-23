/*
 * RegulatoryElementTypeManagerImplTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:37 PM
 */

package gov.nih.nci.camod.service.impl;

import junit.framework.*;
import gov.nih.nci.camod.domain.RegulatoryElementType;
import gov.nih.nci.camod.service.RegulatoryElementTypeManager;
import gov.nih.nci.common.persistence.Search;
import java.util.List;

/**
 *
 * @author piparom
 */
public class RegulatoryElementTypeManagerImplTest extends TestCase {
  
  public RegulatoryElementTypeManagerImplTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(RegulatoryElementTypeManagerImplTest.class);
    
    return suite;
  }

  /**
   * Test of getByType method, of class gov.nih.nci.camod.service.impl.RegulatoryElementTypeManagerImpl.
   */
  public void testGetByType() {
    System.out.println("testGetByType");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
