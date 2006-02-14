

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * Availability of laboratories from commercial or non-commercial sources.
   */

public  class AnimalAvailability 
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
	
	   
	   public String stockNumber;
	   public String getStockNumber(){
	      return stockNumber;
	   }
	   public void setStockNumber(String stockNumber){
	      this.stockNumber = stockNumber;
	   }
	

	
	   
	   
	   
	      

			private java.util.Collection animalDistributorCollection = new java.util.HashSet();
			public java.util.Collection getAnimalDistributorCollection(){
			try{
			   if(animalDistributorCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.AnimalAvailability thisIdSet = new gov.nih.nci.camod.domain.AnimalAvailability();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.AnimalDistributor", thisIdSet);				 
				 	animalDistributorCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("AnimalAvailability:getAnimalDistributorCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return animalDistributorCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setAnimalDistributorCollection(java.util.Collection animalDistributorCollection){
	   		this.animalDistributorCollection = animalDistributorCollection;
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