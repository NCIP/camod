

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


public  class Phenotype 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public Long id;
	   public Long getId(){
	      return id;
	   }
	   
	   public void setId(Long id){
	      this.id = id;
	   }
	
	   
	   public String breedingNotes;
	   public String getBreedingNotes(){
	      return breedingNotes;
	   }
	   
	   public void setBreedingNotes(String breedingNotes){
	      this.breedingNotes = breedingNotes;
	   }
	
	   
	   public String description;
	   public String getDescription(){
	      return description;
	   }
	   
	   public void setDescription(String description){
	      this.description = description;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.SexDistributionImpl sexDistribution;
			public gov.nih.nci.camod.domain.ws.SexDistributionImpl getSexDistribution(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setSexDistribution(gov.nih.nci.camod.domain.ws.SexDistributionImpl sexDistribution){
		this.sexDistribution = sexDistribution;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Phenotype) {
				Phenotype c =(Phenotype)obj; 			 
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
