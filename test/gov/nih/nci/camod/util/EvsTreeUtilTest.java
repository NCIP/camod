/*
 * EvsTreeUtilTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:13 PM
 */

package gov.nih.nci.camod.util;

import junit.framework.*;
import gov.nih.nci.camod.Constants;
import gov.nih.nci.evs.query.EVSQuery;
import gov.nih.nci.evs.query.EVSQueryImpl;
import gov.nih.nci.system.applicationservice.ApplicationService;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author piparom
 */
public class EvsTreeUtilTest extends TestCase {
  
  public EvsTreeUtilTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(EvsTreeUtilTest.class);
    
    return suite;
  }

  /**
   * Test of getEVSPreferedDescription method, of class gov.nih.nci.camod.util.EvsTreeUtil.
   */
  public void testGetEVSPreferedDescription() {
    System.out.println("testGetEVSPreferedDescription");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of getApplicationService method, of class gov.nih.nci.camod.util.EvsTreeUtil.
   */
  public void testGetApplicationService() {
    System.out.println("testGetApplicationService");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
