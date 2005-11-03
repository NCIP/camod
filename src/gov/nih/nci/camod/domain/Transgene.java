/*
 * Created on May 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.util.*;


/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Transgene extends EngineeredGene {

    private static final long serialVersionUID = 3258505453799404851L;

    private String locationOfIntegration;
    private List regulatoryElementCollection = new ArrayList();
    private List taxonCollection = new ArrayList();

    /**
     * @return Returns the taxonCollection.
     */
    public List getTaxonCollection() {
        return taxonCollection;
    }
    
    public List getTaxonCollectionSorted() {      
      if (taxonCollection != null) return new ArrayList(new TreeSet(taxonCollection));
      return null;
    }    
    

    /**
     * @param taxonCollection
     *            The taxonCollection to set.
     */
    public void setTaxonCollection(List taxonCollection) {
        this.taxonCollection = taxonCollection;
    }

    /**
     * @param taxon
     *            The taxon to add.
     */
    public void addTaxon(Taxon taxon) {
        taxonCollection.add(taxon);
    }

    /**
     * @return Returns the regulatoryElementCollection.
     */
    public List getRegulatoryElementCollection() {         
        return regulatoryElementCollection;                       
    }

    public List getRegulatoryElementCollectionSorted() {      
      if (regulatoryElementCollection != null) return new ArrayList(new TreeSet(regulatoryElementCollection));
      return null;
    }   
    
    /**
     * @param regulatoryElementCollection
     *            The regulatoryElementCollection to set.
     */
    public void setRegulatoryElementCollection(List regulatoryElementCollection) {
        this.regulatoryElementCollection = regulatoryElementCollection;
    }

    /**
     * @param regulatoryElement
     *            The regulatoryElement to add.
     */
    public void addRegulatoryElement(RegulatoryElement regulatoryElement) {
        regulatoryElementCollection.add(regulatoryElement);
    }

    /**
     * @return Returns the locationOfIntegration.
     */
    public String getLocationOfIntegration() {
        return locationOfIntegration;
    }

    /**
     * @param locationOfIntegration
     *            The locationOfIntegration to set.
     */
    public void setLocationOfIntegration(String locationOfIntegration) {
        this.locationOfIntegration = locationOfIntegration;
    }

     /**
     * @see java.lang.Object#toString()
     */
     public String toString() {
       String result = super.toString() + " - ";      
       result += this.getLocationOfIntegration();
       return result;
     }  
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
}
