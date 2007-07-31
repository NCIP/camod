/**
 * @author dgeorge
 * 
 * $Id: CommentsStateForm.java,v 1.4 2007-07-31 12:02:06 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2005/11/28 13:51:43  georgeda
 * Defect #192, handle back arrow for curation changes
 *
 * Revision 1.2  2005/10/24 13:28:30  georgeda
 * Cleanup changes
 *
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

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 * 
 * Form used to change the state of an animal model during curation
 * 
 */
public class CommentsStateForm extends ValidatorForm implements CommentsStateData, Serializable {

	private static final long serialVersionUID = 3257850969634190134L;

	protected String myModelDescriptor;
	protected String myRemark;
	protected String myModelId;
	protected String myCommentsId;
	protected String myAssignedTo;
	protected String myEvent;

	public CommentsStateForm() {
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

	public String getCommentsId() {
		return myCommentsId;
	}

	public void setCommentsId(String inCommentsId) {
		myCommentsId = inCommentsId;
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
		myCommentsId = null;
		myEvent = null;
	}
}
