/**
 * 
 * $Id: SpontaneousMutationForm.java,v 1.8 2007-10-31 18:01:31 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2007/04/04 13:23:37  pandyas
 * modified names for mutation identifier fields (number changed to id)
 *
 * Revision 1.6  2007/03/26 12:03:10  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.5  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

public class SpontaneousMutationForm extends BaseForm implements Serializable, SpontaneousMutationData
{

    private static final long serialVersionUID = 3257055453799404851L;

    /**
     * Default empty constructor
     * @author pandyas
     */
    public SpontaneousMutationForm()
    {}

    protected String name;
	protected String mgiId;
	protected String zfinId;
	protected String rgdId;	
    protected String comments;
    protected String observation;
    protected String methodOfObservation;
	protected String geneIdentifier;

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return Returns the comments.
     */
    public String getComments()
    {
        return comments;
    }

    /**
     * @param comments The comments to set.
     */
    public void setComments(String comments)
    {
        this.comments = comments;
    }

    /**
     * @return Returns the observation.
     */
    public String getObservation()
    {
        return observation;
    }

    /**
     * @param observation The observation to set.
     */
    public void setObservation(String observation)
    {
        this.observation = observation;
    }

    /**
     * @return Returns the methodOfObservation.
     */
    public String getMethodOfObservation()
    {
        return methodOfObservation;
    }

    /**
     * @param methodOfObservation The methodOfObservation to set.
     */
    public void setMethodOfObservation(String methodOfObservation)
    {
        this.methodOfObservation = methodOfObservation;
    }

	public String getMgiId() {
		return mgiId;
	}

	public void setMgiId(String mgiId) {
		this.mgiId = mgiId;
	}

	public String getRgdId() {
		return rgdId;
	}

	public void setRgdId(String rgdId) {
		this.rgdId = rgdId;
	}

	public String getZfinId() {
		return zfinId;
	}

	public void setZfinId(String zfinId) {
		this.zfinId = zfinId;
	}

	/**
	 * @return the geneIdentifier
	 */
	public String getGeneIdentifier() {
		return geneIdentifier;
	}

	/**
	 * @param geneIdentifier the geneIdentifier to set
	 */
	public void setGeneIdentifier(String geneIdentifier) {
		this.geneIdentifier = geneIdentifier;
	}
}
