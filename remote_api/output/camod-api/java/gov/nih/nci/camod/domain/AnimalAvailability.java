

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * Availability of laboratories from commercial or non-commercial sources.
   */

public  class AnimalAvailability 
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
	
	   
	   public String stockNumber;
	   public String getStockNumber(){
	      return stockNumber;
	   }
	   public void setStockNumber(String stockNumber){
	      this.stockNumber = stockNumber;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.AnimalDistributor animalDistributor;
			public gov.nih.nci.camod.domain.AnimalDistributor getAnimalDistributor(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.AnimalAvailability thisIdSet = new gov.nih.nci.camod.domain.AnimalAvailability();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.AnimalDistributor", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                animalDistributor = (gov.nih.nci.camod.domain.AnimalDistributor)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("AnimalAvailability:getAnimalDistributor throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return animalDistributor;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setAnimalDistributor(gov.nih.nci.camod.domain.AnimalDistributor animalDistributor){
		this.animalDistributor = animalDistributor;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof AnimalAvailability) {
				AnimalAvailability c =(AnimalAvailability)obj; 			 
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