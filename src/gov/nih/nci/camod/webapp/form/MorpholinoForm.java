/**
 * 
 * @author pandyas
 * 
 * $Id: MorpholinoForm.java,v 1.1 2006-05-03 20:05:25 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

public class MorpholinoForm extends BaseForm implements Serializable, MorpholinoData  {
    
    private static final long serialVersionUID = 3257225453799404851L;
    
	/**
	 * Default empty constructor
	 * @author pandyas
	 */
	public MorpholinoForm() {}

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

	
}
