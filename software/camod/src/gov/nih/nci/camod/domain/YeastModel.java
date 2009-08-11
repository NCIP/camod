/*
 * $Id: YeastModel.java,v 1.7 2006-04-19 17:37:37 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import java.util.*;

/**
 * @author rajputs
 */
public class YeastModel extends AbstractCancerModel
{
    private static final long serialVersionUID = 3257425453799404851L;

    private Set<ScreeningResult> screeningResultCollection = new TreeSet<ScreeningResult>();
    private Set<TargetedModification> targetedModificationCollection = new TreeSet<TargetedModification>();

    /**
     * @return Returns the targetedModificationCollection.
     */
    public Set<TargetedModification> getTargetedModificationCollection()
    {
        return targetedModificationCollection;
    }

    /**
     * @param targetedModificationCollection
     *            The targetedModificationCollection to set.
     */
    public void setTargetedModificationCollection(Set<TargetedModification> targetedModificationCollection)
    {
        this.targetedModificationCollection = targetedModificationCollection;
    }

    public void addTargetedModification(TargetedModification targetedModification)
    {
        targetedModificationCollection.add(targetedModification);
    }

    /**
     * @return Returns the screeningResultCollection.
     */
    public Set<ScreeningResult> getScreeningResultCollection()
    {
        return screeningResultCollection;
    }

    /**
     * @param screeningResultCollection
     *            The screeningResultCollection to set.
     */
    public void setScreeningResultCollection(Set<ScreeningResult> screeningResultCollection)
    {
        this.screeningResultCollection = screeningResultCollection;
    }

    public void addScreeningResult(ScreeningResult screeningResult)
    {
        screeningResultCollection.add(screeningResult);
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString();
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
