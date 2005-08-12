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
public class TransgeneForm extends BaseForm implements Serializable {
	/**
	 * Default empty constructor
	 * @author rajputs
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public TransgeneForm() {}
	
	// engineeredGeneId is of type String since it comes from the presentation layer
	protected String engineeredGeneId;
	protected String locationOfIntegration;	
	
	/**
	 * @return Returns the engineeredGeneId.
	 */
	public String getEngineeredGeneId() {
		return engineeredGeneId;
	}
	/**
	 * @param engineeredGeneId The engineeredGeneId to set.
	 */
	public void setEngineeredGeneId(String engineeredGeneId) {
		this.engineeredGeneId = engineeredGeneId;
	}
	/**
	 * @return Returns the locationOfIntegration.
	 */
	public String getLocationOfIntegration() {
		return locationOfIntegration;
	}
	/**
	 * @param locationOfIntegration The locationOfIntegration to set.
	 */
	public void setLocationOfIntegration(String locationOfIntegration) {
		this.locationOfIntegration = locationOfIntegration;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false
    }
}
