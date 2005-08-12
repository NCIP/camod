/*
 * Created on Jun 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MicroArrayDataForm extends BaseForm implements Serializable {
	/**
	 * Default empty constructor
	 * @author rajputs
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public MicroArrayDataForm() {}
	
	// id and experimentId are of type String since they come from the presentation layer
	protected String id;
	protected String experimentName;
	protected String experimentId;
	
	/**
	 * @return Returns the experimentId.
	 */
	public String getExperimentId() {
		return experimentId;
	}
	/**
	 * @param experimentId The experimentId to set.
	 */
	public void setExperimentId(String experimentId) {
		this.experimentId = experimentId;
	}
	/**
	 * @return Returns the experimentName.
	 */
	public String getExperimentName() {
		return experimentName;
	}
	/**
	 * @param experimentName The experimentName to set.
	 */
	public void setExperimentName(String experimentName) {
		this.experimentName = experimentName;
	}
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false
    }
}
