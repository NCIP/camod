/**
 * @author dgeorge
 * 
 * $Id: StateChangeData.java,v 1.1 2005-10-10 14:12:37 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2005/09/19 13:39:57  georgeda
 * Cleaned up parameter passing
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

import java.util.List;


/**
 * 
 * Interface for state changes
 * 
 */
public interface StateChangeData {

	public String getNote();

	public void setNote(String inComment);

	public String getModelId();

	public void setModelId(String inModelId);

	public String getAssignedTo();

	public void setAssignedTo(String inAssignedTo);

	public String getEvent();

	public void setEvent(String inEvent);
	
	public String getModelDescriptor();

	public void setModelDescriptor(String inModelDescriptor);

	public List getAssignees();

	public void setAssignees(List inAssignees);
}
