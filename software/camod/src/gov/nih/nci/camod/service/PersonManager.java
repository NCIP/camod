/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * Created on Jun 17, 2005
 *
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
