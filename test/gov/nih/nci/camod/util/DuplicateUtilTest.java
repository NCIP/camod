/*
 * DuplicateUtilTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:13 PM
 */

package gov.nih.nci.camod.util;

import junit.framework.*;
import java.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author piparom
 */
public class DuplicateUtilTest extends TestCase {
  
  public DuplicateUtilTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(DuplicateUtilTest.class);
    
    return suite;
  }

  /**
   * Test of duplicateBean method, of class gov.nih.nci.camod.util.DuplicateUtil.
   */
  public void testDuplicateBean() {
    System.out.println("testDuplicateBean");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
