/*
 * $Id: ExpressionLevelDesc.java,v 1.7 2006-04-17 19:13:46 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;


/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ExpressionLevelDesc extends BaseObject implements Serializable
{
    private static final long serialVersionUID = 3259415453799404851L;

    private String expressionLevel;

    /**
     * @return Returns the expressionLevel.
     */
    public String getExpressionLevel()
    {
        return expressionLevel;
    }

    /**
     * @param expressionLevel
     *            The expressionLevel to set.
     */
    public void setExpressionLevel(String expressionLevel)
    {
        this.expressionLevel = expressionLevel;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getExpressionLevel();
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
