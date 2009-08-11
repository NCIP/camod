/*
 * $Id: CaelmirStudyData.java,v 1.1 2007-12-27 22:35:27 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 *
 */

package gov.nih.nci.camod.domain;

import java.io.Serializable;
import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.HashCodeUtil;

public class CaelmirStudyData extends BaseObject implements Comparable, Serializable, Duplicatable{

    private static final long serialVersionUID = 3259275453799404851L;
    
    private String studyName;
    private String hypothesis;
    private String url;
    private String description;
    private String investigatorName;
    private String email;
    private String institution;
    private String cancerModel;
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the hypothesis
	 */
	public String getHypothesis() {
		return hypothesis;
	}
	/**
	 * @param hypothesis the hypothesis to set
	 */
	public void setHypothesis(String hypothesis) {
		this.hypothesis = hypothesis;
	}
	/**
	 * @return the institution
	 */
	public String getInstitution() {
		return institution;
	}
	/**
	 * @param institution the institution to set
	 */
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	/**
	 * @return the investigatorName
	 */
	public String getInvestigatorName() {
		return investigatorName;
	}
	/**
	 * @param investigatorName the investigatorName to set
	 */
	public void setInvestigatorName(String investigatorName) {
		this.investigatorName = investigatorName;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	} 

    /**
     * @return Returns the cancerModel.
     */
    public String getCancerModel()
    {
        return cancerModel;
    }

    /**
     * @param cancerModel
     *            The cancerModel to set.
     */
    public void setCancerModel(String cancerModel)
    {
        this.cancerModel = cancerModel;
    }    
    
    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getStudyName();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        final CaelmirStudyData obj = (CaelmirStudyData) o;
        if (HashCodeUtil.notEqual(this.getStudyName(), obj.getStudyName()))
            return false;
        return true;
    }

    public int hashCode()
    {
        int result = HashCodeUtil.SEED;
        result = HashCodeUtil.hash(result, this.getStudyName());
        return result + super.hashCode();
    }
	/**
	 * @return the studyName
	 */
	public String getStudyName() {
		return studyName;
	}
	/**
	 * @param studyName the studyName to set
	 */
	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}

}
