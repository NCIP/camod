

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


public  class Transgene 
    extends EngineeredGene
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public String locationOfIntegration;
	   public String getLocationOfIntegration(){
	      return locationOfIntegration;
	   }
	   
	   public void setLocationOfIntegration(String locationOfIntegration){
	      this.locationOfIntegration = locationOfIntegration;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection regulatoryElementCollection = new java.util.HashSet();
			public java.util.Collection getRegulatoryElementCollection(){
	              return regulatoryElementCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setRegulatoryElementCollection(java.util.Collection regulatoryElementCollection){
	   		this.regulatoryElementCollection = regulatoryElementCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.ws.TaxonImpl species;
			public gov.nih.nci.camod.domain.ws.TaxonImpl getSpecies(){
			  return species;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setSpecies(gov.nih.nci.camod.domain.ws.TaxonImpl species){
		this.species = species;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Transgene) {
				Transgene c =(Transgene)obj; 			 
				Long thisId = getId();		
				
					if(thisId != null && thisId.equals(c.getId())) {
					   eq = true;
				    }		
				
			}
			return eq;
		}
		
		public int hashCode(){
			int h = 0;
			
			if(getId() != null) {
				h += getId().hashCode();
			}
			
			return h;
	}
	
	
}
