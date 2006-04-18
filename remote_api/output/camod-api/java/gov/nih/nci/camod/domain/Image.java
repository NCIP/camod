

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * Images related to the animal model e.g. histology images, blots, and graphics.
   */

public  class Image 
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
	
	   
	   public String fileServerLocation;
	   public String getFileServerLocation(){
	      return fileServerLocation;
	   }
	   public void setFileServerLocation(String fileServerLocation){
	      this.fileServerLocation = fileServerLocation;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.StainingMethod stainingMethod;
			public gov.nih.nci.camod.domain.StainingMethod getStainingMethod(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Image thisIdSet = new gov.nih.nci.camod.domain.Image();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.StainingMethod", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                stainingMethod = (gov.nih.nci.camod.domain.StainingMethod)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Image:getStainingMethod throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return stainingMethod;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setStainingMethod(gov.nih.nci.camod.domain.StainingMethod stainingMethod){
		this.stainingMethod = stainingMethod;
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