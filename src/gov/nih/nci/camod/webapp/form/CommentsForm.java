/**
 * @author dgeorge
 * 
 * $Id: CommentsForm.java,v 1.1 2005-10-11 18:15:37 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/10/10 14:12:36  georgeda
 * Initial revision
 *
 * Revision 1.3  2005/09/19 13:39:57  georgeda
 * Cleaned up parameter passing
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

/**
 * 
 * Form used to change the state of an animal model during curation
 *
 */
public class CommentsForm extends ValidatorForm implements CommentsData, Serializable {

    private static final long serialVersionUID = 3257850969634190134L;

    protected String mySectionName;
    protected String myModelId;
    protected String myRemark;
    protected String mySubmitter;
    protected String myState;


    public String getSectionName() {
        return mySectionName;
    }

    public void setSectionName(String inSectionName) {
        mySectionName = inSectionName;
    }

    public String getRemark() {
        return myRemark;
    }

    public void setRemark(String inRemark) {
        myRemark = inRemark;
    }

    public String getModelId() {
        return myModelId;
    }

    public void setModelId(String inModelId) {
        myModelId = inModelId;
    }
    
    public String getSubmitter() {
        return mySubmitter;
    }

    public void setSubmitter(String inSubmitter) {
        mySubmitter = inSubmitter;
    }
    
    public String getState() {
        return myState;
    }

    public void setState(String inState) {
        myState = inState;
    }
}
