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
import java.util.TreeSet;

import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.HashCodeUtil;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AnimalAvailability extends BaseObject implements Comparable, Serializable, Duplicatable {
    
    private static final long serialVersionUID = 4259705453799404851L;
    	
	private String name;
	private String stockNumber;
	private List animalDistributorCollection = new ArrayList();
	
	/**
	 * @return Returns the animalDistributorCollection.
	 */
	public List getAnimalDistributorCollection() {
		return animalDistributorCollection;
	}
        
        public List getAnimalDistributorCollectionSorted() {      
          if (animalDistributorCollection != null) return new ArrayList(new TreeSet(animalDistributorCollection));
          return null;
        }    
        
	/**
	 * @param animalDistributorCollection The animalDistributorCollection to set.
	 */
	public void setAnimalDistributorCollection(List animalDistributorCollection) {
		this.animalDistributorCollection = animalDistributorCollection;
	}
	/**
	 * @param animalDistributor The animalDistributor to add.
	 */
	public void addAnimalDistributor(AnimalDistributor animalDistributor) {
		animalDistributor.getAnimalAvailabilityCollection().add(this);
		animalDistributorCollection.add(animalDistributor);
	}
	
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the stockNumber.
	 */
	public String getStockNumber() {
		return stockNumber;
	}
	/**
	 * @param stockNumber The stockNumber to set.
	 */
	public void setStockNumber(String stockNumber) {
		this.stockNumber = stockNumber;
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
      final AnimalAvailability obj = (AnimalAvailability) o;
      if (HashCodeUtil.notEqual(this.getName(), obj.getName())) return false;
      return true;
    }
     
    public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash(result, this.getName());    
      return result + super.hashCode();    
    }  
    
    public int compareTo(Object o) {
      if ((o instanceof AnimalAvailability) && (this.getName() != null) && (((AnimalAvailability)o).getName() != null)) {   
        int result = this.getName().compareTo( ((AnimalAvailability)o).getName() );
        if (result != 0) { return result; }               
      }

      return super.compareTo(o);
    }      

        
}
