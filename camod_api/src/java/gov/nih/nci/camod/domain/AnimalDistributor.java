

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * Commercial or non-commercial distributor of laboratory animals.
   */

public  class AnimalDistributor 
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
	
	   
	   public String name;
	   public String getName(){
	      return name;
	   }
	   public void setName(String name){
	      this.name = name;
	   }
	

	
	   
	   
	   
	      

			private java.util.Collection animalAvailabilityCollection = new java.util.HashSet();
			public java.util.Collection getAnimalAvailabilityCollection(){
			try{
			   if(animalAvailabilityCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.AnimalDistributor thisIdSet = new gov.nih.nci.camod.domain.AnimalDistributor();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.AnimalAvailability", thisIdSet);				 
				 	animalAvailabilityCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("AnimalDistributor:getAnimalAvailabilityCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return animalAvailabilityCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setAnimalAvailabilityCollection(java.util.Collection animalAvailabilityCollection){
	   		this.animalAvailabilityCollection = animalAvailabilityCollection;
	        }	
	   
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof AnimalDistributor) {
				AnimalDistributor c =(AnimalDistributor)obj; 			 
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