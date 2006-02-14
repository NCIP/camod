

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**

   */

public  class Comment 
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
	
	   
	   public String comment;
	   public String getComment(){
	      return comment;
	   }
	   public void setComment(String comment){
	      this.comment = comment;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.Availability availability;
			public gov.nih.nci.camod.domain.Availability getAvailability(){
			
              ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Comment thisIdSet = new gov.nih.nci.camod.domain.Comment();
			  thisIdSet.setId(this.getId());
			  try {
			  java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Availability", thisIdSet);				 
			 
			  if (resultList!=null && resultList.size()>0) {
			     availability = (gov.nih.nci.camod.domain.Availability)resultList.get(0);
			     }
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Comment:getAvailability throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return availability;			
			 		
              }
                        
	      
	               
	   
	   
	   
	   public void setAvailability(gov.nih.nci.camod.domain.Availability availability){
		this.availability = availability;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Person submitter;
			public gov.nih.nci.camod.domain.Person getSubmitter(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Comment thisIdSet = new gov.nih.nci.camod.domain.Comment();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Person", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                submitter = (gov.nih.nci.camod.domain.Person)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Comment:getSubmitter throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return submitter;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setSubmitter(gov.nih.nci.camod.domain.Person submitter){
		this.submitter = submitter;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ModelSection modelSection;
			public gov.nih.nci.camod.domain.ModelSection getModelSection(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Comment thisIdSet = new gov.nih.nci.camod.domain.Comment();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.ModelSection", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                modelSection = (gov.nih.nci.camod.domain.ModelSection)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Comment:getModelSection throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return modelSection;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setModelSection(gov.nih.nci.camod.domain.ModelSection modelSection){
		this.modelSection = modelSection;
	   }	
	   
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Comment) {
				Comment c =(Comment)obj; 			 
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