/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * UtilSuite.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:14 PM
 */

package gov.nih.nci.camod.util;

import junit.framework.*;

/**
 *
 * @author piparom
 */
public class UtilSuite extends TestCase {
  
  public UtilSuite(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  /**
   * suite method automatically generated by JUnit module
   */

  /**
   * suite method automatically generated by JUnit module
   */
  public static Test suite() {
    TestSuite suite = new TestSuite("UtilSuite");
    suite.addTest(gov.nih.nci.camod.util.AuthenticationFilterTest.suite());
    suite.addTest(gov.nih.nci.camod.util.ConvertUtilTest.suite());
    suite.addTest(gov.nih.nci.camod.util.CurrencyConverterTest.suite());
    suite.addTest(gov.nih.nci.camod.util.CustomRequestProcessorTest.suite());
    suite.addTest(gov.nih.nci.camod.util.DateConverterTest.suite());
    suite.addTest(gov.nih.nci.camod.util.DateUtilTest.suite());
    suite.addTest(gov.nih.nci.camod.util.DrugScreenResultTest.suite());
    suite.addTest(gov.nih.nci.camod.util.DuplicatableTest.suite());
    suite.addTest(gov.nih.nci.camod.util.DuplicateUtilTest.suite());
    suite.addTest(gov.nih.nci.camod.util.EvsTreeUtilTest.suite());
    suite.addTest(gov.nih.nci.camod.util.FtpUtilTest.suite());
    suite.addTest(gov.nih.nci.camod.util.GUIDGeneratorTest.suite());
    suite.addTest(gov.nih.nci.camod.util.HashCodeUtilTest.suite());
    suite.addTest(gov.nih.nci.camod.util.LDAPUtilTest.suite());
    suite.addTest(gov.nih.nci.camod.util.LabelValueTest.suite());
    suite.addTest(gov.nih.nci.camod.util.MailUtilTest.suite());
    suite.addTest(gov.nih.nci.camod.util.PopulatePubMedUtilTest.suite());
    suite.addTest(gov.nih.nci.camod.util.RandomGUIDTest.suite());
    suite.addTest(gov.nih.nci.camod.util.StringUtilTest.suite());
    suite.addTest(gov.nih.nci.camod.util.TimestampConverterTest.suite());
    return suite;
  }
  
}
