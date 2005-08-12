/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.ContactInfo;
import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.service.PersonManager;
import gov.nih.nci.common.persistence.*;
import gov.nih.nci.common.persistence.exception.PersistenceException;

import java.util.Iterator;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PersonManagerImpl extends BaseManager implements PersonManager {
	
	public List getPersons() {		
		List persons = null;
		
		try {
			persons = Search.query(Person.class);
			
			
			
			
			
			if (persons != null && !persons.isEmpty()) {				
				System.out.println("\n *** PERSONS ***");				
				Iterator personIterator = persons.iterator();			
				while (personIterator.hasNext()) {
					Person person = (Person) personIterator.next();
					
					// Print the persons
					System.out.println("FirstName: " + person.getFirstName());
					System.out.println("LastName: " + person.getLastName());
					
					// Print the contact infos
					if (person.getContactInfoCollection() != null &&
						!person.getContactInfoCollection().isEmpty()) {
						
						System.out.println("\n *** CONTACT INFOS ***");
				
						Iterator ciIterator = person.getContactInfoCollection().iterator();
						while (ciIterator.hasNext()) {
							ContactInfo contactInfo = (ContactInfo) ciIterator.next();
							System.out.println("Zip: " + contactInfo.getZip());
						}
					} else {
						System.out.println("\n*** NO CONTACT INFOS RETRIEVED!!! ***\n");
					}
				}
		    } else {
		    	System.out.println("\n*** NO PARTIES RETRIEVED!!! ***\n");
		    }

			
		} catch (Exception e) {
			System.out.println("Exception in PersonManagerImpl.getPersons");
		}
		
		return persons;
	}
	
	public Person getPerson(String id) {
		Person person = null;
		
		try {
			person = (Person) Search.queryById(Person.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in PersonManagerImpl.getPerson");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in PersonManagerImpl.getPerson");
			e.printStackTrace();
		}
		
		return person;
    }

    public void savePerson(Person person) {
    	try {
			Persist.save(person);
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in PersonManagerImpl.savePerson");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in PersonManagerImpl.savePerson");
			e.printStackTrace();
		}
    }

    public void removePerson(String id) {
    	try {
			Persist.deleteById(Person.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in PersonManagerImpl.removePerson");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in PersonManagerImpl.removePerson");
			e.printStackTrace();
		}
    }
}
