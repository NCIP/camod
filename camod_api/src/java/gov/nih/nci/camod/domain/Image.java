

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * Images related to the animal model e.g. histology images, blots, and graphics.
   */

public  class Image 
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
	
	   
	   public String title;
	   public String getTitle(){
	      return title;
	   }
	   public void setTitle(String title){
	      this.title = title;
	   }
	
	   
	   public String description;
	   public String getDescription(){
	      return description;
	   }
	   public void setDescription(String description){
	      this.description = description;
	   }
	
	   
	   public String staining;
	   public String getStaining(){
	      return staining;
	   }
	   public void setStaining(String staining){
	      this.staining = staining;
	   }
	
	   
	   public String fileServerLocation;
	   public String getFileServerLocation(){
	      return fileServerLocation;
	   }
	   public void setFileServerLocation(String fileServerLocation){
	      this.fileServerLocation = fileServerLocation;
	   }
	
	   
	   private String stainingUnctrlVocab;
	   public String getStainingUnctrlVocab(){
	      return stainingUnctrlVocab;
	   }
	   public void setStainingUnctrlVocab(String stainingUnctrlVocab){
	      this.stainingUnctrlVocab = stainingUnctrlVocab;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Availability availability;
			public gov.nih.nci.camod.domain.Availability getAvailability(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Image thisIdSet = new gov.nih.nci.camod.domain.Image();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Availability", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                availability = (gov.nih.nci.camod.domain.Availability)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Image:getAvailability throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return availability;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setAvailability(gov.nih.nci.camod.domain.Availability availability){
		this.availability = availability;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Image) {
				Image c =(Image)obj; 			 
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