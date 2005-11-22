/*
 * RandomGUIDTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:14 PM
 */

package gov.nih.nci.camod.util;

import junit.framework.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author piparom
 */
public class RandomGUIDTest extends TestCase {
  
  public RandomGUIDTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(RandomGUIDTest.class);
    
    return suite;
  }

  /**
   * Test of toString method, of class gov.nih.nci.camod.util.RandomGUID.
   */
  public void testToString() {
    System.out.println("testToString");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of main method, of class gov.nih.nci.camod.util.RandomGUID.
   */
  public void testMain() {
    System.out.println("testMain");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
