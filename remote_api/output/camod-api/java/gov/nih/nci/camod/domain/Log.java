

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * A written record of events.
   */

public  class Log 
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
	
	   
	   public String notes;
	   public String getNotes(){
	      return notes;
	   }
	   public void setNotes(String notes){
	      this.notes = notes;
	   }
	
	   
	   public String type;
	   public String getType(){
	      return type;
	   }
	   public void setType(String type){
	      this.type = type;
	   }
	
	   
	   public String subsystem;
	   public String getSubsystem(){
	      return subsystem;
	   }
	   public void setSubsystem(String subsystem){
	      this.subsystem = subsystem;
	   }
	
	   
	   public String timestamp;
	   public String getTimestamp(){
	      return timestamp;
	   }
	   public void setTimestamp(String timestamp){
	      this.timestamp = timestamp;
	   }
	

	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.Comments comment;
			public gov.nih.nci.camod.domain.Comments getComment(){
			
              ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Log thisIdSet = new gov.nih.nci.camod.domain.Log();
			  thisIdSet.setId(this.getId());
			  try {
			  java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Comments", thisIdSet);				 
			 
			  if (resultList!=null && resultList.size()>0) {
			     comment = (gov.nih.nci.camod.domain.Comments)resultList.get(0);
			     }
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Log:getComment throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return comment;			
			 		
              }
                        
	      
	               
	   
	   
	   
	   public void setComment(gov.nih.nci.camod.domain.Comments comment){
		this.comment = comment;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Person submitter;
			public gov.nih.nci.camod.domain.Person getSubmitter(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Log thisIdSet = new gov.nih.nci.camod.domain.Log();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Person", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                submitter = (gov.nih.nci.camod.domain.Person)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Log:getSubmitter throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return submitter;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setSubmitter(gov.nih.nci.camod.domain.Person submitter){
		this.submitter = submitter;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.AbstractCancerModel cancerModel;
			public gov.nih.nci.camod.domain.AbstractCancerModel getCancerModel(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Log thisIdSet = new gov.nih.nci.camod.domain.Log();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.AbstractCancerModel", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                cancerModel = (gov.nih.nci.camod.domain.AbstractCancerModel)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Log:getCancerModel throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return cancerModel;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setCancerModel(gov.nih.nci.camod.domain.AbstractCancerModel cancerModel){
		this.cancerModel = cancerModel;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Log) {
				Log c =(Log)obj; 			 
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