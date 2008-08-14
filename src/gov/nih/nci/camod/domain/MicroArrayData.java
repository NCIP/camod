/*
 * $Id: MicroArrayData.java,v 1.9 2008-08-14 20:09:18 schroedn Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.8  2006/04/19 17:37:37  pandyas
 * Removed TODO text
 *
 * Revision 1.7  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.HashCodeUtil;

/**
 * @author rajputs
 */
public class MicroArrayData extends BaseObject implements Comparable, Serializable, Duplicatable
{
    private static final long serialVersionUID = 3259195453799404851L;

    private String url;
    private Long experimentId;
    private String experimentName;
    private AbstractCancerModel cancerModel;


    /**
     * @return Returns the url.
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @param url
     *            The otherLocationURL to set.
     */
    public void setUrl(String url)
    {
        this.url = url;
    }

    /**
     * @return Returns the experimentId.
     */
    public Long getExperimentId()
    {
        return experimentId;
    }

    /**
     * @param experimentId
     *            The experimentId to set.
     */
    public void setExperimentId(Long experimentId)
    {
        this.experimentId = experimentId;
    }

    /**
     * @return Returns the experimentName.
     */
    public String getExperimentName()
    {
        return experimentName;
    }

    /**
     * @param experimentName
     *            The experimentName to set.
     */
    public void setExperimentName(String experimentName)
    {
        this.experimentName = experimentName;
    }

    /**
     * @return Returns the cancerModel.
     */
    public AbstractCancerModel getCancerModel()
    {
        return cancerModel;
    }

    /**
     * @param cancerModel
     *            The cancerModel to set.
     */
    public void setCancerModel(AbstractCancerModel cancerModel)
    {
        this.cancerModel = cancerModel;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getExperimentName();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        final MicroArrayData obj = (MicroArrayData) o;
        if (HashCodeUtil.notEqual(this.getExperimentName(), obj.getExperimentName()))
            return false;
        return true;
    }

    public int hashCode()
    {
        int result = HashCodeUtil.SEED;
        result = HashCodeUtil.hash(result, this.getExperimentName());
        return result + super.hashCode();
    }

    public int compareTo(Object o)
    {
        if ((o instanceof MicroArrayData) && (this.getExperimentName() != null) && (((MicroArrayData) o).getExperimentName() != null))
        {
            int result = this.getExperimentName().compareTo(((MicroArrayData) o).getExperimentName());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }
}
