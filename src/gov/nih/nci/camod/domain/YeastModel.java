/*
 * Created on May 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.*;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class YeastModel extends AbstractCancerModel {

    private static final long serialVersionUID = 3257425453799404851L;

    private List screeningResultCollection = new ArrayList();
    private List targetedModificationCollection = new ArrayList();

    /**
     * @return Returns the targetedModificationCollection.
     */
    public List getTargetedModificationCollection() {
        return targetedModificationCollection;
    }

    /**
     * @param targetedModificationCollection
     *            The targetedModificationCollection to set.
     */
    public void setTargetedModificationCollection(List targetedModificationCollection) {
        this.targetedModificationCollection = targetedModificationCollection;
    }

    public void addTargetedModification(TargetedModification targetedModification) {
        targetedModificationCollection.add(targetedModification);
    }

    /**
     * @return Returns the screeningResultCollection.
     */
    public List getScreeningResultCollection() {
        return screeningResultCollection;
    }

    /**
     * @param screeningResultCollection
     *            The screeningResultCollection to set.
     */
    public void setScreeningResultCollection(List screeningResultCollection) {
        this.screeningResultCollection = screeningResultCollection;
    }

    public void addScreeningResult(ScreeningResult screeningResult) {
        screeningResultCollection.add(screeningResult);
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof YeastModel)) {
            return false;
        }
        YeastModel rhs = (YeastModel) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.targetedModificationCollection,
                rhs.targetedModificationCollection).append(this.screeningResultCollection,
                rhs.screeningResultCollection).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-913323447, -593888737).appendSuper(super.hashCode()).append(
                this.targetedModificationCollection).append(this.screeningResultCollection).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("species", this.getSpecies()).append("state", this.getState()).append(
                "targetedModificationCollection", this.targetedModificationCollection).append("id", this.getId())
                .append("availability", this.getAvailability()).append("screeningResultCollection",
                        this.screeningResultCollection).append("experimentDesign", this.getExperimentDesign()).append(
                        "submitter", this.getSubmitter()).append("principalInvestigator",
                        this.getPrincipalInvestigator()).append("publicationCollection",
                        this.getPublicationCollection()).append("modelDescriptor", this.getModelDescriptor())
                .toString();
    }
}
