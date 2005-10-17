/**
 * @author dgeorge
 * 
 * $Id: CurationAssignmentForm.java,v 1.1 2005-10-17 13:23:22 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/09/19 19:54:06  georgeda
 * New model assignment functionality
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

/**
 * 
 * Form for querying for model assignment
 *
 */
public class CurationAssignmentForm extends ValidatorForm implements Serializable {

    private static final long serialVersionUID = 6227851969634170134L;

    protected List myStates;
    protected String myCurrentState;

    public List getStates() {
        return myStates;
    }

    public void setStates(List inStates) {
        myStates = inStates;
    }

    public String getCurrentState() {
        return myCurrentState;
    }

    public void setCurrentState(String inCurrentState) {
        myCurrentState = inCurrentState;
    }
}
