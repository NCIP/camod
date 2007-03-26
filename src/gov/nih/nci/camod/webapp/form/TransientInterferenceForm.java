/**
 * 
 * @author pandyas
 * 
 * $Id: TransientInterferenceForm.java,v 1.3 2007-03-26 12:03:10 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2006/10/23 14:17:10  pandyas
 * changed to conform to conceptCode format in all other classes
 *
 * Revision 1.1  2006/10/17 16:10:47  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.1  2006/05/03 20:05:25  pandyas
 * Modified to add Morpholino object data to application
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

public class TransientInterferenceForm extends BaseForm implements Serializable, TransientInterferenceData  {
    
    private static final long serialVersionUID = 3257225453799404851L;
    
	/**
	 * Default empty constructor
	 * @author pandyas
	 */
	public TransientInterferenceForm() {}

    protected String source;
    protected String otherSource;
    protected String type;    
    protected String sequenceDirection;
    protected String targetedRegion;
    protected String concentration;
    protected String concentrationUnit;
    protected String deliveryMethod;
    protected String otherDeliveryMethod;
    protected String visualLigand;
    protected String otherVisualLigand;
    protected String conceptCode;
	protected String comments;
	protected String site;	
	
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
     * @return Returns the otherSource
     */
    public String getOtherSource()
    {
        return otherSource;
    }

    /**
     * @param otherSource
     *            The otherSource to set.
     */
    public void setOtherSource(String otherSource)
    {
        this.otherSource = otherSource;
    }


    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            The type to set.
     */
    public void setType(String type) {
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
    public String getTargetedRegion() {
        return targetedRegion;
    }

    /**
     * @param targetedRegion
     *            The targetedRegion to set.
     */
    public void setTargetedRegion(String targetedRegion) {
        this.targetedRegion = targetedRegion;
    }


    /**
     * @return Returns the concentration.
     */
    public String getConcentration() {
        return concentration;
    }

    /**
     * @param concentration
     *            The concentration to set.
     */
    public void setConcentration(String concentration) {
        this.concentration = concentration;
    }



    /**
     * @return Returns the concentrationUnit.
     */
    public String getConcentrationUnit() {
        return concentrationUnit;
    }

    /**
     * @param concentrationUnit
     *            The concentrationUnit to set.
     */
    public void setConcentrationUnit(String concentrationUnit) {
        this.concentrationUnit = concentrationUnit;
    }

    
    /**
     * @return Returns the deliveryMethod.
     */
    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    /**
     * @param deliveryMethod
     *            The deliveryMethod to set.
     */
    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }


    /**
     * @return Returns the otherDeliveryMethod.
     */
    public String getOtherDeliveryMethod() {
        return otherDeliveryMethod;
    }

    /**
     * @param otherDeliveryMethod
     *            The otherDeliveryMethod to set.
     */
    public void setOtherDeliveryMethod(String otherDeliveryMethod) {
        this.otherDeliveryMethod = otherDeliveryMethod;
    }


    /**
     * @return Returns the visualLigand.
     */
    public String getVisualLigand() {
        return visualLigand;
    }

    /**
     * @param visualLigand
     *            The visualLigand to set.
     */
    public void setVisualLigand(String visualLigand) {
        this.visualLigand = visualLigand;
    }

    /**
     * @return Returns the otherVisualLigand.
     */
    public String getOtherVisualLigand() {
        return otherVisualLigand;
    }

    /**
     * @param otherVisualLigand
     *            The otherVisualLigand to set.
     */
    public void setOtherVisualLigand(String otherVisualLigand) {
        this.otherVisualLigand = otherVisualLigand;
    }

    /*
    * @return Returns the conceptCode for transient interference method.
    */
   public String getConceptCode() {
       return conceptCode;
   }

   /**
    * @param conceptCode
    *            The conceptCode to set.
    */
   public void setConceptCode(String conceptCode) {
       this.conceptCode = conceptCode;
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

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}   
}
