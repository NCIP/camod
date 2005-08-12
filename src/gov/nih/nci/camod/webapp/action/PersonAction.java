package gov.nih.nci.camod.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.PersonManager;
import gov.nih.nci.camod.webapp.form.PersonForm;

/**
 * Action class to handle CRUD on a Person object
 *
 * @struts.action name="personForm" path="/persons" scope="request"
 *  validate="false" parameter="method" input="mainMenu"
 * @struts.action name="personForm" path="/editPerson" scope="request"
 *  validate="false" parameter="method" input="list"
 * @struts.action name="personForm" path="/savePerson" scope="request"
 *  validate="true" parameter="method" input="edit"
 * 
 * @struts.action-forward name="edit" path="/WEB-INF/pages/personForm.jsp"
 * @struts.action-forward name="list" path="/WEB-INF/pages/personList.jsp"
 */
public final class PersonAction extends BaseAction {
	
	/**
	 * Cancel
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
        return search(mapping, form, request, response);
    }

    /**
     * Delete
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'delete' method");
        }

        ActionMessages messages = new ActionMessages();
        PersonForm personForm = (PersonForm) form;

        // Exceptions are caught by ActionExceptionHandler
        PersonManager mgr = (PersonManager) getBean("personManager");
        mgr.removePerson(personForm.getId());

        messages.add(ActionMessages.GLOBAL_MESSAGE,
                     new ActionMessage("person.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);

        return search(mapping, form, request, response);
    }

    /**
     * Edit
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'edit' method");
        }

        PersonForm personForm = (PersonForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        if (personForm.getId() != null) {
            PersonManager mgr = (PersonManager) getBean("personManager");
            Person person = mgr.getPerson(personForm.getId());
            personForm = (PersonForm) convert(person);
            updateFormBean(mapping, request, personForm);
        }

        return mapping.findForward("edit");
    }

    /**
     * Save
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'save' method");
        }

        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        PersonForm personForm = (PersonForm) form;
        boolean isNew = ("".equals(personForm.getId()) || personForm.getId() == null);

        PersonManager mgr = (PersonManager) getBean("personManager");
        Person person = (Person) convert(personForm);
        person.setFirstName("Sumeet");
        person.setLastName("Rajput");
        mgr.savePerson(person);

        // TEST CODE
        AnimalModel animalModel = new AnimalModel();
        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        animalModelManager.saveAnimalModel(animalModel);
        // ABOVE IS TEST CODE        
        
        
        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("person.added"));

            // save messages in session to survive a redirect
            saveMessages(request.getSession(), messages);

            return search(mapping, form, request, response);
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("person.updated"));
            saveMessages(request, messages);

            return mapping.findForward("edit");
        }
    }

    /**
     * Search
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'search' method");
        }

        PersonManager mgr = (PersonManager) getBean("personManager");
        request.setAttribute(Constants.PERSON_LIST, mgr.getPersons());

        return mapping.findForward("list");
    }
    
    /**
     * Unspecified
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
        throws Exception {
        return search(mapping, form, request, response);
    }
}