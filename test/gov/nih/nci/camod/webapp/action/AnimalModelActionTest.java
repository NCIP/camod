/*
 * AnimalModelActionTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 1:59 PM
 */

package gov.nih.nci.camod.webapp.action;

import junit.framework.*;
import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.CurationManager;
import gov.nih.nci.camod.service.impl.CurationManagerImpl;
import gov.nih.nci.camod.webapp.form.ModelCharacteristicsForm;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;
import servletunit.struts.*;

/**
 *
 * @author piparom
 */
public class AnimalModelActionTest extends MockStrutsTestCase {
  
  public AnimalModelActionTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(AnimalModelActionTest.class);
    
    return suite;
  }

  /**
   * Test of save method, of class gov.nih.nci.camod.webapp.action.AnimalModelAction.
   */
  public void testSave() {
    System.out.println("testSave");
        
    // set the request path
    setRequestPathInfo("/AnimalModelAction");
    
    // set the request parameters
    addRequestParameter("method", "save");
    //addRequestParameter("param_name", "value");
    //addRequestParameter("param_name", "value");
    
    // execute the action
    actionPerform();
    
     // verify if the expected forward is set
    verifyForward("AnimalModelTreePopulateAction"); 
    
    // verify any expected values
    //assertEquals(expected_value, actual_value);
    
    // verify no action errors occured
    verifyNoActionErrors();
    //or verify specific action errors occured    
    //verifyActionErrors("error.errorname.etc...");      
  }

  /**
   * Test of edit method, of class gov.nih.nci.camod.webapp.action.AnimalModelAction.
   */
  public void testEdit() {
    System.out.println("testEdit");
    
    // set the request path
    setRequestPathInfo("/AnimalModelAction");
    
    // set the request parameters
    addRequestParameter("method", "edit");
    //addRequestParameter("param_name", "value");
    //addRequestParameter("param_name", "value");
    
    // execute the action
    actionPerform();
    
     // verify if the expected forward is set
    verifyForward("AnimalModelTreePopulateAction"); 
    
    // verify any expected values
    //assertEquals(expected_value, actual_value);
    
    // verify no action errors occured
    verifyNoActionErrors();
    //or verify specific action errors occured    
    //verifyActionErrors("error.errorname.etc...");      
  }

  /**
   * Test of duplicate method, of class gov.nih.nci.camod.webapp.action.AnimalModelAction.
   */
  public void testDuplicate() {
    System.out.println("testDuplicate");
    
   // set the request path
    setRequestPathInfo("/AnimalModelAction");
    
    // set the request parameters
    addRequestParameter("method", "duplicate");
    //addRequestParameter("param_name", "value");
    //addRequestParameter("param_name", "value");
    
    // execute the action
    actionPerform();
    
     // verify if the expected forward is set
    verifyForward("AnimalModelTreePopulateAction"); 
    
    // verify any expected values
    //assertEquals(expected_value, actual_value);
    
    // verify no action errors occured
    verifyNoActionErrors();
    //or verify specific action errors occured    
    //verifyActionErrors("error.errorname.etc...");      
  }

  /**
   * Test of delete method, of class gov.nih.nci.camod.webapp.action.AnimalModelAction.
   */
  public void testDelete() {
    System.out.println("testDelete");
    
    // set the request path
    setRequestPathInfo("/AnimalModelAction");
    
    // set the request parameters
    addRequestParameter("method", "delete");
    //addRequestParameter("param_name", "value");
    //addRequestParameter("param_name", "value");
    
    // execute the action
    actionPerform();
    
     // verify if the expected forward is set
    verifyForward("AnimalModelTreePopulateAction"); 
    
    // verify any expected values
    //assertEquals(expected_value, actual_value);
    
    // verify no action errors occured
    verifyNoActionErrors();
    //or verify specific action errors occured    
    //verifyActionErrors("error.errorname.etc...");      
  }

  /**
   * Test of returnUserModels method, of class gov.nih.nci.camod.webapp.action.AnimalModelAction.
   */
  public void testReturnUserModels() {
    System.out.println("testReturnUserModels");
    
     // set the request path
    setRequestPathInfo("/AnimalModelAction");
    
    // set the request parameters
    addRequestParameter("method", "returnUserModels");
    //addRequestParameter("param_name", "value");
    //addRequestParameter("param_name", "value");
    
    // execute the action
    actionPerform();
    
     // verify if the expected forward is set
    verifyForward("AnimalModelTreePopulateAction"); 
    
    // verify any expected values
    //assertEquals(expected_value, actual_value);
    
    // verify no action errors occured
    verifyNoActionErrors();
    //or verify specific action errors occured    
    //verifyActionErrors("error.errorname.etc...");      
  }
  
}
