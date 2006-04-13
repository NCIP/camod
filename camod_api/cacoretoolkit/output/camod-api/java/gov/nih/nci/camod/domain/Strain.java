

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * An object representing a population or type of organisms that is geneticaly different from others 
   * of the same species and possessing a set of defined characteristics. 
   * 
   */

public  class Strain 
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
	
	   
	   public java.lang.String name;
	   public  java.lang.String getName(){
	      return name;
	   }
	   public void setName( java.lang.String name){
	      this.name = name;
	   }
	
	   
	   public java.lang.String nameUnctrlVocab;
	   public  java.lang.String getNameUnctrlVocab(){
	      return nameUnctrlVocab;
	   }
	   public void setNameUnctrlVocab( java.lang.String nameUnctrlVocab){
	      this.nameUnctrlVocab = nameUnctrlVocab;
	   }
	
	   
	   public java.lang.String abbreviation;
	   public  java.lang.String getAbbreviation(){
	      return abbreviation;
	   }
	   public void setAbbreviation( java.lang.String abbreviation){
	      this.abbreviation = abbreviation;
	   }
	
	   
	   public java.lang.String conceptCode;
	   public  java.lang.String getConceptCode(){
	      return conceptCode;
	   }
	   public void setConceptCode( java.lang.String conceptCode){
	      this.conceptCode = conceptCode;
	   }
	

	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.MutationIdentifier mutationIdentifier;
			public gov.nih.nci.camod.domain.MutationIdentifier getMutationIdentifier(){
			
              ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Strain thisIdSet = new gov.nih.nci.camod.domain.Strain();
			  thisIdSet.setId(this.getId());
			  try {
			  java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.MutationIdentifier", thisIdSet);				 
			 
			  if (resultList!=null && resultList.size()>0) {
			     mutationIdentifier = (gov.nih.nci.camod.domain.MutationIdentifier)resultList.get(0);
			     }
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Strain:getMutationIdentifier throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return mutationIdentifier;			
			 		
              }
                        
	      
	               
	   
	   
	   
	   public void setMutationIdentifier(gov.nih.nci.camod.domain.MutationIdentifier mutationIdentifier){
		this.mutationIdentifier = mutationIdentifier;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Species species;
			public gov.nih.nci.camod.domain.Species getSpecies(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Strain thisIdSet = new gov.nih.nci.camod.domain.Strain();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Species", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                species = (gov.nih.nci.camod.domain.Species)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Strain:getSpecies throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return species;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setSpecies(gov.nih.nci.camod.domain.Species species){
		this.species = species;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Strain) {
				Strain c =(Strain)obj; 			 
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