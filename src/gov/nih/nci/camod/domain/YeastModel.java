/*
 * Created on May 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


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
    
    public List getTargetedModificationCollectionSorted() {      
      if (targetedModificationCollection != null) return new ArrayList(new TreeSet(targetedModificationCollection));
      return null;
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
    
    public List getScreeningResultCollectionSorted() {      
      if (screeningResultCollection != null) return new ArrayList(new TreeSet(screeningResultCollection));
      return null;
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
     * @see java.lang.Object#toString()
     */
     public String toString() {
       String result = super.toString();             
       return result;
     }  
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
}
