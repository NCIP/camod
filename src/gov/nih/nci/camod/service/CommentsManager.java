/**
 * 
 * $Id: CommentsManager.java,v 1.1 2005-10-10 14:06:56 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Comments;
import gov.nih.nci.camod.domain.Person;

import java.util.List;

/**
 * Interface for the CommentManager class. See implementing classes for details.
 */
public interface CommentsManager {

	public List getAll() throws Exception;

	public List getAllByStateForPerson(String inState, Person inPerson) throws Exception;
	
	public List getAllBySection(String inSection, Person inPerson, AnimalModel inModel) throws Exception;

	public Comments get(String id) throws Exception;
}
