/**
 *  @author dgeorge
 *  
 *  $Id: LogManager.java,v 1.4 2005-10-10 14:07:19 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.3  2005/09/16 15:52:54  georgeda
 *  Changes due to manager re-write
 *
 *  
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.*;

import java.util.List;

/**
 * Interface for the Log object
 * 
 * See implementing classes for details
 */
public interface LogManager {

	public Log getCurrentByCommentsAndAssigned(Comments inComments, Person inAssignedUser) throws Exception;
	
    public Log getCurrentByModelAndAssigned(AnimalModel inModel, Person inAssignedUser) throws Exception;

    public Log getCurrentByModel(AnimalModel inModel) throws Exception;

    public List getAll() throws Exception;

    public Log get(String inId) throws Exception;

    public void save(Log inLog) throws Exception;
    
    public Log create(String inAssignedPersonId, String inModelId, String inState, String inNotes) throws Exception;

    public void remove(String inId) throws Exception;
}
