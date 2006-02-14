

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


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
