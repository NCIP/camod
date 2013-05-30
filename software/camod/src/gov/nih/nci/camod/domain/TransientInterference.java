/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2007/10/31 15:41:14  pandyas
 * Fixed #8188 	Rename UnctrlVocab items to AlternEntry
 *
 * Revision 1.3  2007/04/04 13:17:05  pandyas
 * Modified name for conditioning regimen and target site
 *
 * Revision 1.2  2007/03/19 18:56:11  pandyas
 * Object Model changes for caMOD 2.3 - dee design doc for details
 *
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
 * $Id: TransientInterference.java,v 1.5 2008-10-16 13:55:44 schroedn Exp $
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.SafeHTMLUtil;

import java.io.Serializable;

public class TransientInterference extends BaseObject implements Serializable, Duplicatable
{

    private static final long serialVersionUID = 4259745453799404851L;

    private String source;
    private String sourceAlternEntry;
    private String type;
    private Long absCancerModelId;
    private String sequenceDirection;
    private String targetedRegion;
    private String concentration;
    private String concentrationUnit;
    private String deliveryMethod;
    private String deliveryMethodAlternEntry;
    private String visualLigand;
    private String visualLigandAlternEntry;
    private String comments;
    private String targetSite;    
    private TransientInterferenceMethod transientInterferenceMethod;

    /**
     * @return Returns the display name for the source
     */
    public String getSourceDisplayName()
    {
        if (source != null && source.length() > 0)
        {
            return SafeHTMLUtil.cleanMinimal(source);
        }
        else
        {
            return SafeHTMLUtil.cleanMinimal(sourceAlternEntry);
        }
    }

    /**
     * @return Returns the source
     */
    public String getSource()
    {
        return SafeHTMLUtil.cleanMinimal(source);
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
     * @return Returns the type
     */
    public String getType()
    {
        return SafeHTMLUtil.cleanMinimal(type);
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
        return SafeHTMLUtil.cleanMinimal(sequenceDirection);
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
        return SafeHTMLUtil.cleanMinimal(targetedRegion);
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
        return SafeHTMLUtil.cleanMinimal(concentration);
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
        return SafeHTMLUtil.cleanMinimal(concentrationUnit);
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
        return SafeHTMLUtil.cleanMinimal(deliveryMethod);
    }

    /**
     * @return Returns the display name for the delivery method
     */
    public String getDeliveryMethodDisplayName()
    {
        if (deliveryMethod != null && deliveryMethod.length() > 0)
        {
            return SafeHTMLUtil.cleanMinimal(deliveryMethod);
        }
        else
        {
            return SafeHTMLUtil.cleanMinimal(deliveryMethodAlternEntry);
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
     * @return Returns the display name for the visual ligand
     */
    public String getVisualLigandDisplayName()
    {
        if (visualLigand != null && visualLigand.length() > 0)
        {
            return SafeHTMLUtil.cleanMinimal(visualLigand);
        }
        else
        {
            return SafeHTMLUtil.cleanMinimal(visualLigandAlternEntry);
        }
    }
    
    /**
     * @return Returns the visualLigand.
     */
    public String getVisualLigand()
    {
        return SafeHTMLUtil.cleanMinimal(visualLigand);
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
     * @return Returns the comments.  Comment is a reserved word so 
     * we must use the plural form comments.
     */
    public String getComments()
    {
        return SafeHTMLUtil.cleanMinimal(comments);
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

	public String getTargetSite() {
		return SafeHTMLUtil.cleanMinimal(targetSite);
	}

	public void setTargetSite(String targetSite) {
		this.targetSite = targetSite;
	}

	/**
	 * @return the deliveryMethodAlternEntry
	 */
	public String getDeliveryMethodAlternEntry() {
		return SafeHTMLUtil.cleanMinimal(deliveryMethodAlternEntry);
	}

	/**
	 * @param deliveryMethodAlternEntry the deliveryMethodAlternEntry to set
	 */
	public void setDeliveryMethodAlternEntry(String deliveryMethodAlternEntry) {
		this.deliveryMethodAlternEntry = deliveryMethodAlternEntry;
	}

	/**
	 * @return the visualLigandAlternEntry
	 */
	public String getVisualLigandAlternEntry() {
		return SafeHTMLUtil.cleanMinimal(visualLigandAlternEntry);
	}

	/**
	 * @param visualLigandAlternEntry the visualLigandAlternEntry to set
	 */
	public void setVisualLigandAlternEntry(String visualLigandAlternEntry) {
		this.visualLigandAlternEntry = visualLigandAlternEntry;
	}

	/**
	 * @return the sourceAlternEntry
	 */
	public String getSourceAlternEntry() {
		return SafeHTMLUtil.cleanMinimal(sourceAlternEntry);
	}

	/**
	 * @param sourceAlternEntry the sourceAlternEntry to set
	 */
	public void setSourceAlternEntry(String sourceAlternEntry) {
		this.sourceAlternEntry = sourceAlternEntry;
	}

	/**
	 * @return the absCancerModelId
	 */
	public Long getAbsCancerModelId() {
		return absCancerModelId;
	}

	/**
	 * @param absCancerModelId the absCancerModelId to set
	 */
	public void setAbsCancerModelId(Long absCancerModelId) {
		this.absCancerModelId = absCancerModelId;
	}


}
