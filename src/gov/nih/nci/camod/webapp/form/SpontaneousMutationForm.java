package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

public class SpontaneousMutationForm extends BaseForm implements Serializable, SpontaneousMutationData  {
    
    private static final long serialVersionUID = 3257055453799404851L;
    
	/**
	 * Default empty constructor
	 * @author pandyas
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public SpontaneousMutationForm() {}
	
	protected String name;
	protected String numberMGI;
	protected String comments;
	protected String observation;
	protected String methodOfObservation;
	
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}	
	/**
	 * @return Returns the numberMGI.
	 */
	public String getNumberMGI() {
		return numberMGI;
	}
	/**
	 * @param numberMGI The numberMGI to set.
	 */
	public void setNumberMGI(String numberMGI) {
		this.numberMGI = numberMGI;
	}	
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the observation.
	 */
	public String getObservation() {
		return observation;
	}
	/**
	 * @param observation The observation to set.
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}
	/**
	 * @return Returns the methodOfObservation.
	 */
	public String getMethodOfObservation() {
		return methodOfObservation;
	}
	/**
	 * @param methodOfObservation The methodOfObservation to set.
	 */
	public void setMethodOfObservation(String methodOfObservation) {
		this.methodOfObservation = methodOfObservation;
	}	
}

