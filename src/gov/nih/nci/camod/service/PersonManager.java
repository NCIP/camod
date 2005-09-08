/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
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
	public List getAll();
	public Person get(String id);
    public Person getByUsername(String inUsername);
    public void save(Person person);
    public void remove(String id);
}
