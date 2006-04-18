

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class RegulatoryElement 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	
	   
	   public String name;
	   public String getName(){
	      return name;
	   }
	   
	   public void setName(String name){
	      this.name = name;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.RegulatoryElementType regulatoryElementType;
			public gov.nih.nci.camod.domain.ws.RegulatoryElementType getRegulatoryElementType(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setRegulatoryElementType(gov.nih.nci.camod.domain.ws.RegulatoryElementType regulatoryElementType){
		this.regulatoryElementType = regulatoryElementType;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.Species species;
			public gov.nih.nci.camod.domain.ws.Species getSpecies(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setSpecies(gov.nih.nci.camod.domain.ws.Species species){
		this.species = species;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof RegulatoryElement) {
				RegulatoryElement c =(RegulatoryElement)obj; 			 
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
