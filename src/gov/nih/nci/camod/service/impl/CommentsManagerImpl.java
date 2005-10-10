/**
 * @author dgeorge
 * 
 * $Id: CommentsManagerImpl.java,v 1.1 2005-10-10 14:07:31 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Comments;
import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.service.CommentsManager;

import java.util.List;

/**
 * Manages fetching/saving/updating of comments
 */
public class CommentsManagerImpl extends BaseManager implements CommentsManager {

	/**
	 * Get all of the comments in the DB
	 * 
	 * @return the list of all animal models
	 * 
	 * @exception throws
	 *                an Exception if an error occurred
	 */
	public List getAll() throws Exception {
		log.trace("In CommentsManagerImpl.getAll");
		return super.getAll(AnimalModel.class);
	}

	/**
	 * Get all of the comments of a specific state
	 * 
	 * @param inState
	 *            the state to query for
	 * 
	 * @return the list of comments
	 * 
	 * @exception Exception
	 *                if an error occurred
	 */
	public List getAllByStateForPerson(String inState, Person inPerson) throws Exception {

		log.trace("In CommentsManagerImpl.getAllByStateForPerson");

		return QueryManagerSingleton.instance().getCommentsByStateForPerson(inState, inPerson);
	}

	/**
	 * Get all of the comments for a specifed section by person
	 * 
	 * @param inSection
	 *            the section the comment is associated with
	 * @param inPerson
	 *            the person the comment is associated with (allows users to
	 *            view unrelease comments if they are their own)
	 * 
	 * @return the list of comments
	 * 
	 * @exception Exception
	 *                if an error occurred
	 */
	public List getAllBySection(String inState, Person inPerson, AnimalModel inModel) throws Exception {

		log.trace("In CommentsManagerImpl.getAllBySectionForPerson");

		return QueryManagerSingleton.instance().getCommentsBySection(inState, inPerson, inModel);
	}

	/**
	 * Get a specific comment
	 * 
	 * @param id
	 *            The unique id for the comments
	 * 
	 * @return the comment if found, null otherwise
	 * @throws Exception
	 * 
	 * @exception Exception
	 *                if an error occurred
	 */
	public Comments get(String id) throws Exception {
		log.trace("In CommentsManagerImpl.get");
		return (Comments) super.get(id, Comments.class);
	}
}
