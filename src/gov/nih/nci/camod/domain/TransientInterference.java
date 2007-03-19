/*
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2006/10/17 16:14:36  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.2  2006/05/09 18:56:19  georgeda
 * Changes for searching on transient interfaces
 *
 * Revision 1.1  2006/05/03 20:02:53  pandyas
 * Modified to add Morpholino object data to application
 *
 * 
 * $Id: TransientInterference.java,v 1.2 2007-03-19 18:56:11 pandyas Exp $
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.Duplicatable;
import java.io.Serializable;

public class TransientInterference extends BaseObject implements Serializable, Duplicatable
{

    private static final long serialVersionUID = 4259745453799404851L;

    private String source;
    private String sourceUnctrVocab;
    private String type;
    private String sequenceDirection;
    private String targetedRegion;
    private String concentration;
    private String concentrationUnit;
    private String deliveryMethod;
    private String deliveryMethodUnctrlVocab;
    private String visualLigand;
    private String visualLigandUnctrlVocab;
    private String comments;
    private String site;    
    private TransientInterferenceMethod transientInterferenceMethod;

    /**
     * @return Returns the display name for the source
     */
    public String getSourceDisplayName()
    {
        if (source != null && source.length() > 0)
        {
            return source;
        }
        else
        {
            return sourceUnctrVocab;
        }
    }

    /**
     * @return Returns the source
     */
    public String getSource()
    {
        return source;
    }

    /**
     * @param source
     *            The source to set.
     */
    public void setSource(String source)
    {
        this.source = source;
    }

    /**
     * @return Returns the sourceUnctrVocab
     */
    public String getSourceUnctrVocab()
    {
        return sourceUnctrVocab;
    }

    /**
     * @param sourceUnctrVocab
     *            The source to set.
     */
    public void setSourceUnctrVocab(String sourceUnctrVocab)
    {
        this.sourceUnctrVocab = sourceUnctrVocab;
    }

    /**
     * @return Returns the type
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param type
     *            The type to set.
     */
    public void setType(String type)
    {
        this.type = type;
    }


    /**
     * @return Returns the sequenceDirection.
     */
    public String getSequenceDirection()
    {
        return sequenceDirection;
    }

    /**
     * @param sequenceDirection
     *            The sequenceDirection to set.
     */
    public void setSequenceDirection(String sequenceDirection)
    {
        this.sequenceDirection = sequenceDirection;
    }


    /**
     * @return Returns the targetedRegion. 
     */
    public String getTargetedRegion()
    {
        return targetedRegion;
    }

    /**
     * @param targetedRegion
     *            The targetedRegion to set.
     */
    public void setTargetedRegion(String targetedRegion)
    {
        this.targetedRegion = targetedRegion;
    }


    /**
     * @return Returns the concentration.
     */
    public String getConcentration()
    {
        return concentration;
    }

    /**
     * @param concentration
     *            The concentration to set.
     */
    public void setConcentration(String concentration)
    {
        this.concentration = concentration;
    }

    /**
     * @return Returns the concentrationUnit.
     */
    public String getConcentrationUnit()
    {
        return concentrationUnit;
    }

    /**
     * @param concentrationUnit
     *            The concentrationUnit to set.
     */
    public void setConcentrationUnit(String concentrationUnit)
    {
        this.concentrationUnit = concentrationUnit;
    }


    /**
     * @return Returns the deliveryMethod.
     */
    public String getDeliveryMethod()
    {
        return deliveryMethod;
    }

    /**
     * @return Returns the display name for the delivery method
     */
    public String getDeliveryMethodDisplayName()
    {
        if (deliveryMethod != null && deliveryMethod.length() > 0)
        {
            return deliveryMethod;
        }
        else
        {
            return deliveryMethodUnctrlVocab;
        }
    }
    
    /**
     * @param deliveryMethod
     *            The deliveryMethod to set.
     */
    public void setDeliveryMethod(String deliveryMethod)
    {
        this.deliveryMethod = deliveryMethod;
    }


    /**
     * @return Returns the deliveryMethodUnctrlVocab.
     */
    public String getDeliveryMethodUnctrlVocab()
    {
        return deliveryMethodUnctrlVocab;
    }

    /**
     * @param deliveryMethodUnctrlVocab
     *            The deliveryMethodUnctrlVocab to set.
     */
    public void setDeliveryMethodUnctrlVocab(String deliveryMethodUnctrlVocab)
    {
        this.deliveryMethodUnctrlVocab = deliveryMethodUnctrlVocab;
    }

    /**
     * @return Returns the display name for the visual ligand
     */
    public String getVisualLigandDisplayName()
    {
        if (visualLigand != null && visualLigand.length() > 0)
        {
            return visualLigand;
        }
        else
        {
            return visualLigandUnctrlVocab;
        }
    }
    
    /**
     * @return Returns the visualLigand.
     */
    public String getVisualLigand()
    {
        return visualLigand;
    }

    /**
     * @param visualLigand
     *            The visualLigand to set.
     */
    public void setVisualLigand(String visualLigand)
    {
        this.visualLigand = visualLigand;
    }

    /**
     * @return Returns the visualLigandUnctrlVocab.
     */
    public String getVisualLigandUnctrlVocab()
    {
        return visualLigandUnctrlVocab;
    }

    /**
     * @param visualLigandUnctrlVocab
     *            The visualLigandUncrtlVocab to set.
     */
    public void setVisualLigandUnctrlVocab(String visualLigandUnctrlVocab)
    {
        this.visualLigandUnctrlVocab = visualLigandUnctrlVocab;
    }
    
    /**
     * @return Returns the comments.  Comment is a reserved word so 
     * we must use the plural form comments.
     */
    public String getComments()
    {
        return comments;
    }

    /**
     * @param comments
     *            The comments to set.
     */
    public void setComments(String comments)
    {
        this.comments = comments;
    }    
    
    /**
     * @return Returns the transientInterferenceMethod.
     */
    public TransientInterferenceMethod getTransientInterferenceMethod()
    {
        return transientInterferenceMethod;
    }

    /**
     * @param transientInterferenceMethod
     *            The transientInterferenceMethod to set.
     */
    public void setTransientInterferenceMethod(TransientInterferenceMethod transientInterferenceMethod)
    {
        this.transientInterferenceMethod = transientInterferenceMethod;
    } 
    
    /**
     * @return Returns the site.
     */
    public String getSite()
    {
        return site;
    }

    /**
     * @param site The site to set.
     */
    public void setSite(String site)
    {
        this.site = site;
    }    

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getSource();
        return result;
    }


    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        return true;
    }


}
