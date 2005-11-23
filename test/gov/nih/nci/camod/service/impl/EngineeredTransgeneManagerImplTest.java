/*
 * EngineeredTransgeneManagerImplTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:37 PM
 */

package gov.nih.nci.camod.service.impl;

import junit.framework.*;
import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Conditionality;
import gov.nih.nci.camod.domain.EngineeredGene;
import gov.nih.nci.camod.domain.ExpressionFeature;
import gov.nih.nci.camod.domain.ExpressionLevelDesc;
import gov.nih.nci.camod.domain.GeneFunction;
import gov.nih.nci.camod.domain.Image;
import gov.nih.nci.camod.domain.MutationIdentifier;
import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.domain.RegulatoryElement;
import gov.nih.nci.camod.domain.RegulatoryElementType;
import gov.nih.nci.camod.domain.Taxon;
import gov.nih.nci.camod.domain.Transgene;
import gov.nih.nci.camod.service.EngineeredTransgeneManager;
import gov.nih.nci.camod.util.EvsTreeUtil;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.AssociatedExpressionData;
import gov.nih.nci.camod.webapp.form.EngineeredTransgeneData;
import gov.nih.nci.camod.webapp.form.ImageForm;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author piparom
 */
public class EngineeredTransgeneManagerImplTest extends TestCase {
  
  public EngineeredTransgeneManagerImplTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(EngineeredTransgeneManagerImplTest.class);
    
    return suite;
  }

  /**
   * Test of getAll method, of class gov.nih.nci.camod.service.impl.EngineeredTransgeneManagerImpl.
   */
  public void testGetAll() {
    System.out.println("testGetAll");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of get method, of class gov.nih.nci.camod.service.impl.EngineeredTransgeneManagerImpl.
   */
  public void testGet() {
    System.out.println("testGet");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of save method, of class gov.nih.nci.camod.service.impl.EngineeredTransgeneManagerImpl.
   */
  public void testSave() {
    System.out.println("testSave");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of remove method, of class gov.nih.nci.camod.service.impl.EngineeredTransgeneManagerImpl.
   */
  public void testRemove() {
    System.out.println("testRemove");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of create method, of class gov.nih.nci.camod.service.impl.EngineeredTransgeneManagerImpl.
   */
  public void testCreate() {
    System.out.println("testCreate");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of update method, of class gov.nih.nci.camod.service.impl.EngineeredTransgeneManagerImpl.
   */
  public void testUpdate() {
    System.out.println("testUpdate");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of createAssocExpression method, of class gov.nih.nci.camod.service.impl.EngineeredTransgeneManagerImpl.
   */
  public void testCreateAssocExpression() {
    System.out.println("testCreateAssocExpression");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }

  /**
   * Test of updateAssociatedExpression method, of class gov.nih.nci.camod.service.impl.EngineeredTransgeneManagerImpl.
   */
  public void testUpdateAssociatedExpression() {
    System.out.println("testUpdateAssociatedExpression");
    
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
}
