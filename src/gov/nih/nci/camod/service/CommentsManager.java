/**
 * 
 * $Id: CommentsManager.java,v 1.2 2005-10-11 18:12:29 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/10/10 14:06:56  georgeda
 * Initial revision
 *
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.webapp.form.CommentsData;

import java.util.List;

/**
 * Interface for the CommentManager class. See implementing classes for details.
 */
public interface CommentsManager {

    public Comments create(CommentsData inCommentsData) throws Exception;
    
	public List getAll() throws Exception;

	public List getAllByStateForPerson(String inState, Person inPerson) throws Exception;
	
	public List getAllBySection(String inSection, Person inPerson, AnimalModel inModel) throws Exception;

	public Comments get(String id) throws Exception;
}
