/*
 * AdminEditUserActionTest.java
 * JUnit based test
 *
 * Created on November 22, 2005, 2:18 PM
 */

package gov.nih.nci.camod.webapp.action;

import junit.framework.*;
import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.service.impl.PersonManagerSingleton;
import gov.nih.nci.camod.webapp.form.EditUserForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;
import servletunit.struts.*;

/**
 *
 * @author piparom
 */
public class AdminEditUserActionTest extends MockStrutsTestCase {
  
  public AdminEditUserActionTest(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(AdminEditUserActionTest.class);
    
    return suite;
  }

  /**
   * Test of execute method, of class gov.nih.nci.camod.webapp.action.AdminEditUserAction.
   */
  public void testExecute() {
    System.out.println("testExecute");
    
    // setup request path
    setRequestPathInfo("/AdminEditUserAction");    
    //addRequestParameter("param_name", "value");
    //addRequestParameter("param_name", "value");
    
    // execute the action
    actionPerform();
  
    // verify if the expected forward is set
    verifyForward("next"); 
    
    // verify form content
    EditUserForm form = (EditUserForm) getActionForm();
    assertEquals("username_expected_value", form.getUsername());     

    // verify any expected values
    //assertEquals(expected_value, actual_value);
    
    // verify no action errors occured
    verifyNoActionErrors();
    //or verify specific action errors occured    
    //verifyActionErrors("error.errorname.etc...");      
  }
  
}
