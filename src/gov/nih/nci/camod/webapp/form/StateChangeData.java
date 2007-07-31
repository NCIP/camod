/**
 * @author dgeorge
 * 
 * $Id: StateChangeData.java,v 1.3 2007-07-31 12:01:54 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2005/10/24 13:28:30  georgeda
 * Cleanup changes
 *
 * Revision 1.1  2005/10/10 14:12:37  georgeda
 * Initial revision
 *
 * Revision 1.3  2005/09/19 13:39:57  georgeda
 * Cleaned up parameter passing
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

/**
 * 
 * Interface for state changes
 * 
 */
public interface StateChangeData {

	public String getRemark();

	public void setRemark(String inComment);

	public String getModelId();

	public void setModelId(String inModelId);

	public String getAssignedTo();

	public void setAssignedTo(String inAssignedTo);

	public String getEvent();

	public void setEvent(String inEvent);

	public String getModelDescriptor();

	public void setModelDescriptor(String inModelDescriptor);
}
