

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * Phenotype displayed by the animal model such as neoplastic lesions, other diseases, behavioral 
   * problem. 
   * 
   */

public  class Phenotype 
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
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.SexDistribution sexDistribution;
			public gov.nih.nci.camod.domain.SexDistribution getSexDistribution(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Phenotype thisIdSet = new gov.nih.nci.camod.domain.Phenotype();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.SexDistribution", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                sexDistribution = (gov.nih.nci.camod.domain.SexDistribution)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Phenotype:getSexDistribution throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return sexDistribution;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setSexDistribution(gov.nih.nci.camod.domain.SexDistribution sexDistribution){
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