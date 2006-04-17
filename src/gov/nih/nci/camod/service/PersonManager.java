/*
 * $Id: PersonManager.java,v 1.6 2006-04-17 19:13:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Person;
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

    public void remove(String id) throws Exception;

    public void save(Person person) throws Exception;
}
