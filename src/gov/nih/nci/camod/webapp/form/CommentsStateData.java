/**
 * @author dgeorge
 * 
 * $Id: CommentsStateData.java,v 1.1 2005-10-10 14:12:38 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2005/09/19 13:39:57  georgeda
 * Cleaned up parameter passing
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;


/**
 * 
 * Interface used to change the state of an animal model during curation
 * 
 */
public interface CommentsStateData extends StateChangeData {

	public String getCommentsId();

	public void setCommentsId(String inCommentsId);
}
