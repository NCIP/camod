/*
 * Created on May 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.Duplicatable;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ExpressionFeature extends BaseObject implements Serializable, Duplicatable {

    private static final long serialVersionUID = 3259435453799404851L;
    
    private Organ organ;
    private ExpressionLevelDesc expressionLevelDesc;

    /**
     * @return Returns the expressionLevelDesc.
     */
    public ExpressionLevelDesc getExpressionLevelDesc() {
        return expressionLevelDesc;
    }

    /**
     * @param expressionLevelDesc
     *            The expressionLevelDesc to set.
     */
    public void setExpressionLevelDesc(ExpressionLevelDesc expressionLevelDesc) {
        this.expressionLevelDesc = expressionLevelDesc;
    }

    /**
     * @return Returns the organ.
     */
    public Organ getOrgan() {
        return organ;
    }

    /**
     * @param organ
     *            The organ to set.
     */
    public void setOrgan(Organ organ) {
        this.organ = organ;
    }


    /**
     * @see java.lang.Object#toString()
     */
     public String toString() {
       String result = super.toString() + " - ";      
       result += this.getOrgan();   
       return result;
     }    
}
