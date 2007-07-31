/**
 * @author dgeorge
 * 
 * $Id: AnimalModelStateForm.java,v 1.6 2007-07-31 12:02:03 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2005/10/24 13:28:30  georgeda
 * Cleanup changes
 *
 * Revision 1.4  2005/10/10 14:13:00  georgeda
 * Changes for comment curation
 *
 * Revision 1.3  2005/09/19 13:39:57  georgeda
 * Cleaned up parameter passing
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 * 
 * Form used to change the state of an animal model during curation
 * 
 */
public class AnimalModelStateForm extends BaseForm implements AnimalModelStateData, Serializable {

	private static final long serialVersionUID = 3257850969634190134L;

	protected String myModelDescriptor;

	protected String myRemark;

	protected String myModelId;

	protected String myAssignedTo;

	protected String myEvent;

	public AnimalModelStateForm() {
		myEvent = "";
	}

	public String getModelDescriptor() {
		return myModelDescriptor;
	}

	public void setModelDescriptor(String inModelDescriptor) {
		myModelDescriptor = inModelDescriptor;
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

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		myModelDescriptor = null;
		myRemark = null;
		myModelId = null;
		myAssignedTo = null;
		myEvent = null;
	}
}
