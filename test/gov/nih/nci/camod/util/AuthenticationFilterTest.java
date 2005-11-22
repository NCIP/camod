/*
 * AuthenticationFilterTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:13 PM
 */

package gov.nih.nci.camod.util;

import junit.framework.*;
import gov.nih.nci.camod.Constants;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author piparom
 */
public class AuthenticationFilterTest extends TestCase {
  
  public AuthenticationFilterTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(AuthenticationFilterTest.class);
    
    return suite;
  }

  /**
   * Test of init method, of class gov.nih.nci.camod.util.AuthenticationFilter.
   */
  public void testInit() {
    System.out.println("testInit");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of doFilter method, of class gov.nih.nci.camod.util.AuthenticationFilter.
   */
  public void testDoFilter() {
    System.out.println("testDoFilter");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of destroy method, of class gov.nih.nci.camod.util.AuthenticationFilter.
   */
  public void testDestroy() {
    System.out.println("testDestroy");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
