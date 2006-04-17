/**
 * 
 * $Id: SpontaneousMutationForm.java,v 1.5 2006-04-17 19:09:19 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
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
    protected String mgiNumber;
    protected String comments;
    protected String observation;
    protected String methodOfObservation;
    protected String geneId;

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
     * @return Returns the mgiNumber.
     */
    public String getMgiNumber()
    {
        return mgiNumber;
    }

    /**
     * @param mgiNumber The mgiNumber to set.
     */
    public void setMgiNumber(String mgiNumber)
    {
        this.mgiNumber = mgiNumber;
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

    /**
     * @return Returns the geneId.
     */
    public String getGeneId()
    {
        return geneId;
    }

    /**
     * @param geneId The geneId to set.
     */
    public void setGeneId(String geneId)
    {
        this.geneId = geneId;
    }
}
