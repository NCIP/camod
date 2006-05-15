/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.webapp.form.EditUserData;

import java.util.List;

/**
 * @author rajputs
 * 
 * See implementing classes for details
 */
public interface PersonManager {

    public List getAll() throws Exception;

    public Person get(String id) throws Exception;

    public Person getByUsername(String inUsername) throws Exception;
    
    public List getByRole(String inRole) throws Exception;
    
    public void addContactInfo(Person inPerson, EditUserData inData) throws Exception;

    public void remove(String id) throws Exception;

    public void save(Person person) throws Exception;
}
