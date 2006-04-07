

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

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
	
	   
	   public java.lang.Boolean isRandom;
	   public  java.lang.Boolean getIsRandom(){
	      return isRandom;
	   }
	   
	   public void setIsRandom( java.lang.Boolean isRandom){
	      this.isRandom = isRandom;
	   }
	
	   
	   public java.lang.String constructSequence;
	   public  java.lang.String getConstructSequence(){
	      return constructSequence;
	   }
	   
	   public void setConstructSequence( java.lang.String constructSequence){
	      this.constructSequence = constructSequence;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection regulatoryElementCollection = new java.util.HashSet();
			public java.util.Collection getRegulatoryElementCollection(){
	              return regulatoryElementCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setRegulatoryElementCollection(java.util.Collection regulatoryElementCollection){
	   		this.regulatoryElementCollection = regulatoryElementCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.ws.Species species;
			public gov.nih.nci.camod.domain.ws.Species getSpecies(){
			  return species;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setSpecies(gov.nih.nci.camod.domain.ws.Species species){
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
