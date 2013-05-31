/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 *  
 * $Id$
 *
 * $Log$
 * 
 * Revision 1.1  2006/05/12 17:00:28  guptaa
 * New Ajax tag value pair file
 *
 * 
 */
package gov.nih.nci.camod.util;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * Class which implements tag/value pairs for Ajax calls
 *
 */
public class AjaxTagValuePair implements Serializable
{
    private static final long serialVersionUID = 5258525453779404751L;
    private String target;
    private String source;

    /**
     * Constructor for AjaxTagValuePair.
     */
    public AjaxTagValuePair()
    {
        super();
    }

    /**
     * @return Returns the target.
     */
    public String getTarget()
    {
        return this.target;
    }

    /**
     * @param source The source to set.
     */
    public void setSource(String source)
    {
        this.source = source;
    }

    /**
     * @return Returns the source.
     */
    public String getSource()
    {
        return this.source;
    }

    /**
     * @param target The target to set.
     */
    public void setTarget(String target)
    {
        this.target = target;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return new ToStringBuilder(this).append("target", target).append("source", source).toString();
    }

}
