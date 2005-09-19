/**
 * @author dgeorge
 * 
 * $Id: AnimalModelStateForm.java,v 1.3 2005-09-19 13:39:57 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

/**
 * 
 * Form used to change the state of an animal model during curation
 *
 */
public class AnimalModelStateForm extends ValidatorForm implements Serializable {

    private static final long serialVersionUID = 3257850969634190134L;

    protected String myModelDescriptor;
    protected String myComment;
    protected String myModelId;
    protected String myAssignedTo;
    protected String myEvent;
    protected List myAssignees;

    public AnimalModelStateForm() {
        myEvent = "";
    }

    public String getModelDescriptor() {
        return myModelDescriptor;
    }

    public void setModelDescriptor(String inModelDescriptor) {
        myModelDescriptor = inModelDescriptor;
    }

    public String getComment() {
        return myComment;
    }

    public void setComment(String inComment) {
        myComment = inComment;
    }

    public String getModelId() {
        return myModelId;
    }

    public void setModelId(String inModelId) {
        myModelId = inModelId;
    }

    public String getAssignedTo() {
        return myAssignedTo;
    }

    public void setAssignedTo(String inAssignedTo) {
        myAssignedTo = inAssignedTo;
    }

    public String getEvent() {
        return myEvent;
    }

    public void setEvent(String inEvent) {
        myEvent = inEvent;
    }

    public List getAssignees() {
        return myAssignees;
    }

    public void setAssignees(List inAssignees) {
        myAssignees = inAssignees;
    }
}
