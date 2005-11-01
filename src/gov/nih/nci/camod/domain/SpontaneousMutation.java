/*
 * Created on May 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.HashCodeUtil;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SpontaneousMutation extends BaseObject implements Comparable, Serializable, Duplicatable {

    private static final long serialVersionUID = 3258605453799404851L;
    
    private String name;
    private String comments;
    private List geneticAlterationCollection = new ArrayList();
    private MutationIdentifier mutationIdentifier;

    /**
     * @return Returns the comments.
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments
     *            The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return Returns the geneticAlterationCollection.
     */
    public List getGeneticAlterationCollection() {
        return geneticAlterationCollection;
    }

    /**
     * @param geneticAlterationCollection
     *            The geneticAlterationCollection to set.
     */
    public void setGeneticAlterationCollection(List geneticAlterationCollection) {
        this.geneticAlterationCollection = geneticAlterationCollection;
    }

    /**
     * @param geneticAlteration
     *            The geneticAlteration to add.
     */
    public void addGeneticAlteration(GeneticAlteration geneticAlteration) {
        geneticAlterationCollection.add(geneticAlteration);
    }

    /**
     * @return Returns the mutationIdentifier.
     */
    public MutationIdentifier getMutationIdentifier() {
        return mutationIdentifier;
    }

    /**
     * @param mutationIdentifier
     *            The mutationIdentifier to set.
     */
    public void setMutationIdentifier(MutationIdentifier mutationIdentifier) {
        this.mutationIdentifier = mutationIdentifier;
    }

 
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

     /**
     * @see java.lang.Object#toString()
     */
     public String toString() {
       String result = super.toString() + " - ";      
       result += this.getName();
       return result;
     }  
      
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false; 
      final SpontaneousMutation obj = (SpontaneousMutation) o;
      if (HashCodeUtil.notEqual(this.getName(), obj.getName())) return false;
      return true;
    }
     
    public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash(result, this.getName());    
      return result + super.hashCode();    
    }  
    
    public int compareTo(Object o) {
      if ((o instanceof SpontaneousMutation) && (this.getName() != null) && (((SpontaneousMutation)o).getName() != null)) {   
        int result = this.getName().compareTo( ((SpontaneousMutation)o).getName() );
        if (result != 0) { return result; }               
      }

      return super.compareTo(o);
    }    
}
