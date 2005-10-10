/**
 * @author dgeorge
 * 
 * $Id: AnimalModelStateForm.java,v 1.4 2005-10-10 14:13:00 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2005/09/19 13:39:57  georgeda
 * Cleaned up parameter passing
 *
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
public class AnimalModelStateForm extends ValidatorForm implements AnimalModelStateData, Serializable {

    private static final long serialVersionUID = 3257850969634190134L;

    protected String myModelDescriptor;
    protected String myNote;
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

    public String getNote() {
        return myNote;
    }

    public void setNote(String inNote) {
        myNote = inNote;
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
