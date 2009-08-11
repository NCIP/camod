/**
 * @author dgeorge
 * 
 * $Id: CommentsData.java,v 1.1 2005-10-11 18:15:37 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/10/10 14:12:38  georgeda
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
 * Interface used to change the state of an animal model during curation
 * 
 */
public interface CommentsData {

    public String getModelId();

    public void setModelId(String inModelId);

    public String getSectionName();

    public void setSectionName(String inSectionName);

    public String getRemark();

    public void setRemark(String inRemark);
    
    public String getSubmitter();

    public void setSubmitter(String inSubmitter);
    
    public String getState();

    public void setState(String inState);
    
}
